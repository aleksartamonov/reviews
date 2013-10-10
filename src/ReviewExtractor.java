import logger.Logger;
import org.json.JSONException;
import printer.Format;
import website.AdEkb;
import website.WebSite;

import java.net.URISyntaxException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class ReviewExtractor {

    static int depth = 60;

    public static void main(String[] args) throws JSONException, URISyntaxException {
        logger.Logger.LOG.debug("Start processing");
        WebSite adEkb = new AdEkb();

        HashMap<String, String> arguments = new HashMap<String, String>();
        for (String s : args) {
            arguments.put(s.substring(2).split("=")[0], s.substring(2).split("=")[1]);
        }
        try {
            adEkb.getAllReviews(new Format(arguments.get("format")), arguments.get("outfile"),depth);
        } catch (NullPointerException e) {
            Logger.LOG.error("Read description for run");
            System.exit(1);
        }
    }
}
