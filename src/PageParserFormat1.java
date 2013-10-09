import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */

public class PageParserFormat1 implements PageParser {

    String mainPageUrl;
    String reviewPageUrl;

    public static String getMainPage(String url) throws URISyntaxException {
        URI uri = new URI(url);
        String domain = uri.getHost();
        return domain.startsWith("www.") ? domain.substring(4) : domain;
    }

    public void setUrls(String url) throws URISyntaxException {
        this.mainPageUrl = getMainPage(url);
        this.reviewPageUrl = url;
    }



    private String extractTextFromTag(Elements tag, String htmlClassName) {
        String s;
        Elements tempTag = tag.select("." + htmlClassName);
        s = !tempTag.isEmpty() ? tempTag.first().text() : Review.DEFAULT_STRING;
        return s;
    }

    private String extractHrefValFromTag(Elements tag, String htmlClassName, String url) {

        Elements tempTag = tag.select("." + htmlClassName);
        return !tempTag.isEmpty() ? url + tempTag.attr("href") : Review.DEFAULT_STRING;

    }

    private Rating extractRatingInfo(Elements eRating) {

        String val, best, worst;

        val = eRating.text();

        best = extractTextFromTag(eRating, "best");
        worst = extractTextFromTag(eRating, "worst");

        return new Rating(val, worst, best);
    }

    private Vcard extractVcard(Elements eVcard) {
        String val, fn;

        val = eVcard.first().text();

        fn = extractTextFromTag(eVcard, "fn");

        return new Vcard(val, fn);
    }

    private Reviewer extractReviewerInfo(Elements eReviewer) {
        String val;
        Vcard vcard;

        val = eReviewer.first().text();

        Elements eVcard = eReviewer.select(".vcard");

        if (!eVcard.isEmpty()) {
            vcard = extractVcard(eVcard);
        } else {
            vcard = new Vcard();
            ReviewExtractor.LOG.warn("There is no Vcard info");
        }

        return new Reviewer(val, vcard);
    }

    private HashMap<String, String> pullIdentifiers(Elements elements) {

        HashMap<String, String> identifier = new HashMap<String, String>();
        String key, value;
        for (Element elem : elements) {
            key = elem.select(".type").select(".value-title").attr("title");
            value = elem.text();
            identifier.put(key, value);
        }

        return identifier;
    }

    private Hproduct extractHproductInfo(Elements eHproduct) {
        String val, category, photo, url, brand, fn;
        HashMap<String, String> identifier;

        val = eHproduct.first().text();
        category = extractTextFromTag(eHproduct, "category");
        photo = extractTextFromTag(eHproduct, "photo");
        brand = extractTextFromTag(eHproduct, "brand");
        fn = extractTextFromTag(eHproduct, "fn");
        Elements eIdentifiers = eHproduct.select(".identifier");

        url = extractHrefValFromTag(eHproduct, "url", this.mainPageUrl);

        identifier = pullIdentifiers(eIdentifiers);

        return new Hproduct(val, category, photo, url, brand, fn, identifier);
    }

    private Item extractItemInfo(Elements eItem, String url) {
        String val;
        Hproduct hproduct;

        val = eItem.first().text();

        Elements eHproduct = eItem.select(".hproduct");

        if (!eHproduct.isEmpty()) {
            hproduct = extractHproductInfo(eHproduct);
        } else {
            hproduct = new Hproduct();
            ReviewExtractor.LOG.warn("There is no hproduct info on " + url);
        }

        return new Item(val, hproduct);
    }

    Review constructReview(Document doc, String url) {

        String summary, description, pro, contra, dtReviewed, permalink, type, owningTime, reviewsUrl;

        Reviewer reviewer;
        Rating rating;
        Item item;
        Review review;
        // top-level tags extracting

        Elements tag = doc.select(".hreview");

        if (!tag.isEmpty()) {

            summary = extractTextFromTag(tag, "summary");
            description = extractTextFromTag(tag, "description");
            pro = extractTextFromTag(tag, "pro");
            contra = extractTextFromTag(tag, "contra");
            dtReviewed = extractTextFromTag(tag, "dtReviewed");
            reviewsUrl = extractTextFromTag(tag, "reviewsUrl");
            type = extractTextFromTag(tag, "type");
            owningTime = extractTextFromTag(tag, "owningTime");
            permalink = extractHrefValFromTag(tag, "permalink", this.mainPageUrl);
            Elements eRating = tag.select(".rating");
            if (eRating != null) {

                rating = extractRatingInfo(eRating);

            } else {
                rating = new Rating();
                ReviewExtractor.LOG.warn("There is no rating info on " + url);
            }

            Elements eReviewer = tag.select(".reviewer");

            if (!eReviewer.isEmpty()) {
                reviewer = extractReviewerInfo(eReviewer);
            } else {
                reviewer = new Reviewer();
                ReviewExtractor.LOG.warn("There is no reviewer info on " + url);
            }

            Elements eItem = tag.select(".item");

            if (!eItem.isEmpty()) {

                item = extractItemInfo(eItem, url);

            } else {
                item = new Item();
                ReviewExtractor.LOG.warn("There is no item info on " + url);
            }


            review = new Review(summary, description, pro, contra, dtReviewed, permalink,
                    type, owningTime, reviewsUrl, rating, reviewer, item);

        } else {
            review = null;
            ReviewExtractor.LOG.warn("There is no review on " + url);
        }
        return review;
    }


    public Review getReviewFromPage(String url) {
        // получение Review со страницы adEkb
        Review review = null;
        Document doc;
        try {
            setUrls(url);
        } catch (URISyntaxException e) {
            ReviewExtractor.LOG.error("Use correct url", e);
        }
        try {
            // get doc

            doc = Jsoup.connect(url).get();
            //construct Review
            review = constructReview(doc, url);

        } catch (IOException e) {
            ReviewExtractor.LOG.error("Cannot connect to " + url, e);
        }
        return review;
    }

}