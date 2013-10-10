package website;

import logger.Logger;
import org.json.JSONException;
import parser.PageParser;
import parser.PageParserFactory;
import parser.ParseFormat1Factory;
import printer.Format;
import printer.Printer;
import review.Review;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */
public class AdEkb implements WebSite {
    public final static String reviewPage = "http://adekb.ru/cars/reviews";
    PageParserFactory factory = new ParseFormat1Factory();

    public Review getReview(String url) {
        PageParser p = factory.getParser();
        try {
            return p.getReviewFromPage(url);
        } catch (URISyntaxException e) {
            Logger.LOG.error(e);
            return null;
        }

    }


    private Integer getLastReviewID() {

        Review review = getReview(reviewPage);
        String permalink = review.getPermalink();
        return Integer.parseInt(permalink.split("ID=")[1]);

    }

    private List<String> getRecentPages(int n) {

        Integer lastReviewID = getLastReviewID();
        String UrlBegin = reviewPage + "/?ID=";
        ArrayList<String> pages = new ArrayList<String>();

        for (Integer i = lastReviewID; i > lastReviewID - n; i--) {
            pages.add(UrlBegin + i.toString());
        }

        return pages;
    }

    @Override
    public void getAllReviews(Format format, String filename) {

        List<String> allPages = getRecentPages(10);
        Printer printer = format.getPrinter();
        if (printer == null) {
            Logger.LOG.error("Unknown format for output");
        } else {
            for (String page : allPages) {

                Review review = getReview(page);
                if (review != null) {
                    try {
                        printer.write(review, filename);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Logger.LOG.error(e);  //To change body of catch statement use File | Settings | File Templates.
                    } catch (IOException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                        Logger.LOG.error("Invalid path");
                        return;
                    }
                }
            }
            printer.formatOut(filename);
        }

    }
}
