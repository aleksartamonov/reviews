package review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.impl.PageParserFormat1;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Hproduct extends InfoBlock {

    private String val;
    @Expose
    private String category;
    @Expose
    private String photo;
    @Expose
    private String url;
    @Expose
    private String brand;
    @Expose
    private String fn;
    @Expose
    private HashMap<String, String> identifier;

    public Hproduct(String val, String category, String photo, String url, String brand, String fn,
                    HashMap<String, String> identifier) {
        this.val = val;
        this.category = category;
        this.photo = photo;
        this.url = url;
        this.brand = brand;
        this.fn = fn;
        this.identifier = identifier;
    }

    public Hproduct() {}

    @Override
    public String toString() {
        return "review.review.Hproduct{" +
                "val='" + val + '\'' +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                ", url='" + url + '\'' +
                ", brand='" + brand + '\'' +
                ", fn='" + fn + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
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

    @Override
    public Hproduct extractInfoFormat1(Elements eHproduct, String in_url) {

        PageParserFormat1 parser = new PageParserFormat1(in_url);
        String val, category, photo, url, brand, fn;
        HashMap<String, String> identifier;
        val = eHproduct.first().text();
        category = extractTextFromTag(eHproduct, "category");
        photo = extractTextFromTag(eHproduct, "photo");
        brand = extractTextFromTag(eHproduct, "brand");
        fn = extractTextFromTag(eHproduct, "fn");
        Elements eIdentifiers = eHproduct.select(".identifier");
        url = extractHrefValFromTag(eHproduct, "url", parser.mainPageUrl);
        identifier = pullIdentifiers(eIdentifiers);
        return new Hproduct(val, category, photo, url, brand, fn, identifier);

    }

    @Override
    public void logAboutFail(String reviewPageUrl) {
        Logger.LOG.warn("Hproduct Info couldn't extract from " + reviewPageUrl);
    }
}
