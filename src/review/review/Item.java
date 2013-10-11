package review.review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.select.Elements;
import parser.impl.PageParserFormat1;
import review.review.Hproduct;
import review.review.InfoBlock;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class Item extends InfoBlock {

    private String val;
    @Expose
    private Hproduct hproduct;

    public Item(String val, Hproduct hproduct) {
        this.val = val;
        this.hproduct = hproduct;
    }

    @Override
    public String toString() {
        return "review.review.Item{" +
                "val='" + val + '\'' +
                ", hproduct=" + hproduct +
                '}';
    }

    @Override
    public Item extractInfoFormat1(Elements eItem, String url) {

        PageParserFormat1 parser = new PageParserFormat1();
        String val = eItem.first().text();

        Elements eHproduct = eItem.select(".hproduct");

        Hproduct hproduct = parser.extractInfo(eHproduct, Hproduct.class);

        return new Item(val, hproduct);
    }

    @Override
    public void logAboutFail() {
        Logger.LOG.warn("Item Info couldn't extract");
    }
}
