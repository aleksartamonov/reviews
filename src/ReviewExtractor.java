import logger.Logger;
import org.json.JSONException;
import printer.Format;
import website.WebSite;
import website.impl.AdEkb;

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

    static Integer DEFAULT_DEPTH = 10;

    static boolean argumentsAreValid(HashMap<String,String> map){
        boolean res;

        res = map.containsKey("format");
        if (!res){
            Logger.LOG.error("cant find format argument --format, read README");
            return res;
        }
        Format f = new Format();
        res = res && f.printerMap.containsKey(map.get("format"));
        if(!res){
            Logger.LOG.error("Unknown format, read README");
        }
        res = res && map.containsKey("outfile");
        if(!res){
            Logger.LOG.error("cant find format argument --outfile, read README");
            return res;
        }
        if (map.containsKey("depth")){
            if (Integer.parseInt(map.get("depth")) < -1){
                Logger.LOG.error("Invalid depth, read README");
                return false;
            }
        }

        return res;
    }

    public static void main(String[] args) throws JSONException, URISyntaxException {
        logger.Logger.LOG.debug("Start processing");
        WebSite adEkb = new AdEkb();

        HashMap<String, String> arguments = new HashMap<String, String>();
        arguments.put("depth",DEFAULT_DEPTH.toString());
        for (String s : args) {
            String[] ls = s.substring(2).split("=");
            arguments.put(ls[0], ls[1]);
        }
        if (ReviewExtractor.argumentsAreValid(arguments)){
            adEkb.getAllReviews(new Format(arguments.get("format")), arguments.get("outfile"),
                    Integer.parseInt(arguments.get("depth")));
        } else {
            System.out.println("Something bad. You can see logs in out.log");
            System.exit(1);
        }
    }
}
