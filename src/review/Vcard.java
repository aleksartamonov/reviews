package review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class Vcard extends InfoBlock {

    private String val;
    @Expose
    private String fn;

    public Vcard(String val, String fn) {
        this.val = val;
        this.fn = fn;
    }

    public Vcard() {}

    @Override
    public String toString() {
        return "review.review.Vcard{" +
                ", fn='" + fn + '\'' +
                '}';
    }

    @Override
    public Vcard extractInfoFormat1(Elements eVcard, String url) {
        String val, fn;

        val = eVcard.first().text();

        fn = extractTextFromTag(eVcard, "fn");

        return new Vcard(val, fn);
    }

    @Override
    public void logAboutFail(String reviewPageUrl) {
        Logger.LOG.warn("Reviewer Info couldn't extract " + reviewPageUrl);
    }
}
