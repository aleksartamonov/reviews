import org.json.JSONException;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */
interface Printer {
    void write(Review review, String filename) throws JSONException, IOException;

    void formatOut(String filename);
}
