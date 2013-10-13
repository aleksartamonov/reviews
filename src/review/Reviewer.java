package review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.select.Elements;
import parser.impl.PageParserFormat1;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class Reviewer extends InfoBlock {

    private String val;
    @Expose
    private Vcard vcard;

    public Reviewer(String val, Vcard vcard) {
        this.val = val;
        this.vcard = vcard;
    }

    public Reviewer() {}

    @Override
    public String toString() {
        return "review.review.Reviewer{" +
                "val='" + val + '\'' +
                ", vcard=" + vcard +
                '}';
    }

    @Override
    public Reviewer extractInfoFormat1(Elements eReviewer, String url) {

        PageParserFormat1 parser = new PageParserFormat1();

        String val = eReviewer.first().text();

        Elements eVcard = eReviewer.select(".vcard");

        Vcard vcard = parser.extractInfo(eVcard, Vcard.class);

        return new Reviewer(val, vcard);
    }

    @Override
    public void logAboutFail(String reviewPageUrl) {
        Logger.LOG.warn("Reviewer Info couldn't extract  " + reviewPageUrl);
    }
}

