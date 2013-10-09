import org.apache.log4j.Logger;
import org.json.JSONException;

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
    static final Logger LOG = Logger.getLogger(PageParserFormat1.class);
    public static void main(String[] args) throws JSONException, URISyntaxException {
        LOG.debug("Start processing");
        WebSite adEkb = new AdEkb();
        HashMap<String,String> argsuments = new HashMap<String, String>();
        for(String s: args){
            argsuments.put(s.substring(2).split("=")[0],s.substring(2).split("=")[1]);
        }
        adEkb.getAllReviews(argsuments.get("format"), argsuments.get("outfile"));
    }
}
