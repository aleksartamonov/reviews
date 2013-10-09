import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class PrinterXML implements Printer {
    static boolean first = true;
    @Override
    public void write(Review review, String filename) throws JSONException, IOException {

        Gson formattedGson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().disableHtmlEscaping().create();
        String formattedJson = formattedGson.toJson(review);
        JSONObject o = new JSONObject(formattedJson);
        String xml = XML.toString(o);
        try {
            File file = new File(filename);
            BufferedWriter output = new BufferedWriter(new FileWriter(file, !first));
            output.write("<review>" + xml + "</review>");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
            ReviewExtractor.LOG.error("Invalid path");
            throw e;
        }
        first = false;
    }

    public void formatOut(String filename) {
        BufferedReader br = null;
        try {

            String sCurrentLine;
            String xml = "";
            br = new BufferedReader(new FileReader(filename));

            while ((sCurrentLine = br.readLine()) != null) {
                xml += sCurrentLine;
            }
            XmlFormatter formatter = new XmlFormatter();
            xml = formatter.formatXml("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<reviews>" + xml + "</reviews>");
            try {
                File file = new File(filename);
                BufferedWriter output = new BufferedWriter(new FileWriter(file));
                output.write(xml);
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

}
