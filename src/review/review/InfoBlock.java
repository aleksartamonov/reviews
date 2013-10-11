package review.review;

import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 10.10.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class InfoBlock {

    public abstract <T extends InfoBlock> T extractInfoFormat1(Elements e, String url);

    public abstract void logAboutFail();

    protected String extractTextFromTag(Elements tag, String htmlClassName) {
        String s;
        Elements tempTag = tag.select("." + htmlClassName);
        s = !tempTag.isEmpty() ? tempTag.first().text() : null;
        return s;
    }
    protected String extractHrefValFromTag(Elements tag, String htmlClassName, String url) {

        Elements tempTag = tag.select("." + htmlClassName);
        return !tempTag.isEmpty() ? url + tempTag.attr("href") : null;

    }
}