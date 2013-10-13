package website.impl;

import logger.Logger;
import org.json.JSONException;
import parser.PageParser;
import parser.factory.PageParserFactory;
import parser.factory.impl.ParseFormat1Factory;
import printer.Format;
import printer.Printer;
import review.Review;
import website.WebSite;

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

    private Review getReview(String url) {
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

        n = (n == -1) ? lastReviewID : n;

        for (Integer i = lastReviewID; i > lastReviewID - n; i--) {
            pages.add(UrlBegin + i.toString());
        }

        return pages;
    }

    /**
     * @param format Output Format
     * @param filename  output file
     * @param n        (depth of extraction "-1" for try extract al reviews )
     *                 note: this is not number of n last reviews, this is number of pages for trying parse.
     *                 for example you can put n is 60 but get only 6 review
     */
    @Override
    public void getAllReviews(Format format, String filename, int n) {

        List<String> allPages = getRecentPages(n);
        Printer printer = format.getPrinter();
        if (printer == null) {
            Logger.LOG.error("Unknown format for output");
        } else {
            for (String page : allPages) {

                Review review = getReview(page);
                if (review != null) {
                    try {
                        printer.write(review, filename);
                        Logger.LOG.debug("From " + page + " successfully extracted review");
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
