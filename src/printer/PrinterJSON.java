package printer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONException;
import printer.Printer;
import review.Review;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class PrinterJSON implements Printer {
    static boolean first = true;

    @Override
    public void write(Review review, String filename) throws JSONException {

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().disableHtmlEscaping().create();
        String json = gson.toJson(review);

        try {
            File file = new File(filename);
            BufferedWriter output = new BufferedWriter(new FileWriter(file, !first));
            output.write("{review:" + json + "}\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        first = false;
    }

    @Override
    public void formatOut(String filename) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
