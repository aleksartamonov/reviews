import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class ReviewExtractor {

    public final static String mainPageUrl = "http://adekb.ru";

    public static void main(String[] args) throws JSONException {

        PageParser p = new PageParser();
        Review res = p.getReviewFromPage("http://adekb.ru/cars/reviews/?ID=582534");
        //Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping().create();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Gson formattedGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(res);
        String formattedJson = formattedGson.toJson(res);
        JSONObject o = new JSONObject(formattedJson);
        String xml = XML.toString(o);
        XmlFormatter formatter = new XmlFormatter();
        String formattedXml = formatter.formatXml("<?xml version=\"1.0\"?><o>"+xml+"</o>");
        System.out.println(formattedXml);
        System.out.println(json);

    }
}
