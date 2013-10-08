import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */

public class PageParser {

    private String extractTextFromTag(Elements tag,String htmlClassName){
        String s;
        Elements tempTag = tag.select("."+htmlClassName);
        s =  !tempTag.isEmpty() ? tempTag.first().text() : Review.badString;
        return s;
    }
    private String extractHrefValFromTag(Elements tag,String htmlClassName, String url){

        Elements tempTag = tag.select("."+htmlClassName);
        return !tempTag.isEmpty() ? url + tempTag.attr("href") : Review.badString;

    }
    private Rating extractRatingInfo(Elements eRating) {

        String val,best,worst;

        val = eRating.text();

        best = extractTextFromTag(eRating,"best");
        worst = extractTextFromTag(eRating,"worst");

        return new Rating(val,worst,best);
    }

    private Vcard extractVcard(Elements eVcard){
        String val,fn;

        val = eVcard.first().text();

        fn = extractTextFromTag(eVcard,"fn");

        return new Vcard(val,fn);
    }

    private Reviewer extractReviewerInfo(Elements eReviewer) {
        String val;
        Vcard vcard;

        val = eReviewer.first().text();

        Elements eVcard = eReviewer.select(".vcard");

        if(!eVcard.isEmpty() ){
            vcard = extractVcard(eVcard);
        }else{
            vcard = new Vcard();
            // TODO : out to log
        }

        return new Reviewer(val,vcard);
    }

    private Hproduct extractHproductInfo(Elements eHproduct,String big_url){
        String val,category,photo,url,brand,fn,identifier;

        val = eHproduct.first().text();
        category= extractTextFromTag(eHproduct,"category");
        photo= extractTextFromTag(eHproduct,"photo");
        brand= extractTextFromTag(eHproduct,"brand");
        fn= extractTextFromTag(eHproduct,"fn");
        identifier = extractTextFromTag(eHproduct,"identifier");
        url = extractHrefValFromTag(eHproduct,"url",big_url);


        return new Hproduct(val,category,photo,url,brand,fn,identifier);
    }

    private Item extractItemInfo(Elements eItem,String url){
        String val;
        Hproduct hproduct;

        val = eItem.first().text();

         Elements eHproduct = eItem.select(".hproduct");

        if(!eHproduct.isEmpty()){
            hproduct = extractHproductInfo(eHproduct,url);
        } else {
            hproduct = new Hproduct();
            // TODO : out to log
        }

        return new Item(val,hproduct);
    }

    Review constructReview(Document doc, String url){

        String summary,description,pro,contra,dtReviewed,permalink,type,owningTime,reviewsUrl;

        Reviewer reviewer;
        Rating rating;
        Item item;

        Review review;


        // top-level tags extracting

        Elements tag = doc.select(".hreview");


        if(tag != null){

            System.out.println(tag);

            summary = extractTextFromTag(tag,"summary");
            description = extractTextFromTag(tag,"description");
            pro = extractTextFromTag(tag,"pro");
            contra = extractTextFromTag(tag,"contra");
            dtReviewed = extractTextFromTag(tag,"dtReviewed");
            reviewsUrl = extractTextFromTag(tag,"reviewsUrl");
            type = extractTextFromTag(tag,"type");
            owningTime = extractTextFromTag(tag,"owningTime");
            permalink = extractHrefValFromTag(tag,"permalink",url);
            Elements eRating = tag.select(".rating") ;

            System.out.println(contra);

            if(eRating != null){

                rating = extractRatingInfo(eRating);

            }else {
                rating = new Rating();
                // TODO : out to log
            }

            Elements eReviewer = tag.select(".reviewer");

            if(eReviewer != null){
                reviewer = extractReviewerInfo(eReviewer);
            }
            else{
                // TODO : out to log
                reviewer = new Reviewer();
            }

            Elements eItem = tag.select(".item");

            if(eItem != null){

                item = extractItemInfo(eItem,url);

            }
            else{
                item = new Item();
                //TODO : out to log
            }


            review = new Review(summary,description,pro,contra,dtReviewed,permalink,
                    type,owningTime,reviewsUrl,rating,reviewer,item);

        } else {
            review = new Review();
            // TODO : out to log
        }


        return review;
    }


    Review getReviewFromPage(String url){
        // полуение Review со страницы adEkb
        Review review = null;
        Document doc;
        try {
            // get doc

            doc = Jsoup.connect(url).get();


            //construct Review
            review = constructReview(doc,url);


        }catch (IOException e){
            //TODO : out to log
            System.out.println("could not connect");
        }
        System.out.println("All page info");
        System.out.println(review);
        return review;
    }
}
