import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class AdEkb implements WebSite {
    String reviewPage = "http://adekb.ru/cars/reviews";
    PageParserFactory factory = new ParseFormat1Factory();

    public Review getReview(String url) {
        PageParser p = this.factory.getParser();
        try {
            return p.getReviewFromPage(url);
        } catch (URISyntaxException e) {
            return null;
        }

    }


    private Integer getLastReviewID() {

        Review review = getReview(this.reviewPage);
        String permalink = review.permalink;
        return Integer.parseInt(permalink.split("ID=")[1]);

    }

    private ArrayList<String> getRecentPages(int n) {

        Integer lastReviewID = getLastReviewID();
        String UrlBegin = this.reviewPage + "/?ID=";

        ArrayList<String> pages = new ArrayList<String>();

        for (Integer i = lastReviewID; i > lastReviewID - n; i--) {
            pages.add(UrlBegin + i.toString());
        }

        return pages;
    }

    @Override
    public void getAllReviews(String format, String filename) {

        ArrayList<String> allPages = getRecentPages(10);
        Printer printer = null;
        if(format.equals(Format.XMl.val())){
            printer = new PrinterXML();
        }
        if(format.equals(Format.JSON.val())){
            printer = new PrinterJSON();
        }

        if(printer == null){
            ReviewExtractor.LOG.error("Unknown format for output");
        }

        else{
            for (String page : allPages) {
                Review review = getReview(page);
                if (review != null) {
                    try {

                        printer.write(review, filename);

                    } catch (JSONException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        ReviewExtractor.LOG.error("Invalid path");
                        return;
                    }
                }
            }
            printer.formatOut(filename);
        }

    }
}
