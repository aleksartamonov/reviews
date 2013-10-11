package parser;

import logger.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import review.InfoBlock;
import review.Review;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */

/**
 * Instance of PageParser for parse and get review information for example from adekb.ru
 */
public class PageParserFormat1 implements PageParser {

    String mainPageUrl;
    String reviewPageUrl;

    private static String getMainPage(String url) throws URISyntaxException {
        URI uri = new URI(url);
        return uri.getHost();
    }

    private void setUrls(String url) throws URISyntaxException {
        mainPageUrl = getMainPage(url);
        reviewPageUrl = url;
    }

    /**
     * Extract special review information from block of html
     *
     * @param e      block of html
     * @param tClass for making instance of returnable class
     * @param <T>    extends InfoBlock
     * @return object of class T of null, if some troubles
     */
    public <T extends InfoBlock> T extractInfo(Elements e, Class<T> tClass) {

        T t = null;
        try {
            t = tClass.newInstance();
            if (!e.isEmpty()) {
                t = t.extractInfoFormat1(e, mainPageUrl);
            } else {
                t.logAboutFail(reviewPageUrl);
                t = null;
            }
        } catch (InstantiationException e1) {
            e1.printStackTrace();
            Logger.LOG.error(e1);
        } catch (IllegalAccessException e1) {
            e1.printStackTrace();
            Logger.LOG.error(e1);
        }

        return t;
    }

    private Review constructReview(Document doc) {

        Elements eReview = doc.select(".hreview");
        return extractInfo(eReview, Review.class);
    }


    public Review getReviewFromPage(String url) {
        // получение review.review.review.Review.review.review.Review со страницы adEkb

        Review review = null;
        Document doc;
        try {
            setUrls(url);
        } catch (URISyntaxException e) {
            Logger.LOG.error("Use correct url", e);
        }
        try {
            // get doc

            doc = Jsoup.connect(url).get();
            //construct review.review.review.Review.review.review.Review

            review = constructReview(doc);

        } catch (IOException e) {
            Logger.LOG.error("Cannot connect to " + url, e);
        }
        return review;
    }

}
