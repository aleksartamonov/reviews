package review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class Rating extends InfoBlock {

    @Expose
    private String value;
    @Expose
    private String worst;
    @Expose
    private String best;


    public Rating(String value, String worst, String best) {
        this.value = value;
        this.worst = worst;
        this.best = best;
    }

    public Rating() {
        this.value = Review.DEFAULT_STRING;
        this.best = Review.DEFAULT_STRING;
        this.worst = Review.DEFAULT_STRING;
    }

    @Override
    public String toString() {
        return "review.review.Rating{" +
                "value='" + value + '\'' +
                ", worst='" + worst + '\'' +
                ", best='" + best + '\'' +
                '}';
    }

    @Override
    public Rating extractInfoFormat1(Elements eRating, String url) {

        String val = eRating.text();
        String best = extractTextFromTag(eRating, "best");
        String worst = extractTextFromTag(eRating, "worst");

        return new Rating(val, worst, best);
    }

    @Override
    public void logAboutFail(String reviewPageUrl) {
        Logger.LOG.warn("Rating Info couldn't extract " + reviewPageUrl);
    }
}
