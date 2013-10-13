package review;

import org.jsoup.select.Elements;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 10.10.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class InfoBlock {
    /**
     * Extract info in Format1 for PageParserFormat1
     *
     * @param e   block of html code
     * @param url for fill information about source
     * @param <T>
     * @return object of class T which contains extracted info or null
     */
    public abstract <T extends InfoBlock> T extractInfoFormat1(Elements e, String url);

    /**
     * print to log in some bad cases
     *
     * @param reviewPageUrl
     */
    public abstract void logAboutFail(String reviewPageUrl);

    protected String extractTextFromTag(Elements tag, String htmlClassName) {
        String s;
        Elements tempTag = tag.select("." + htmlClassName);
        s = !tempTag.isEmpty() ? tempTag.first().text().replaceAll("\u00a0","") : null;
        return s;
    }

    protected String extractHrefValFromTag(Elements tag, String htmlClassName, String url) {

        Elements tempTag = tag.select("." + htmlClassName);
        return !tempTag.isEmpty() ? url + tempTag.attr("href") : null;

    }
    protected String extractValFromTag(Elements tag,String className){
        Elements tempTag = tag.select("."+className);
        return !tempTag.isEmpty() ? tempTag.select(".value-title").attr("title") : null;
    }
}