package printer;

import org.json.JSONException;
import review.Review;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 19:04
 * To change this template use File | Settings | File Templates.
 */

public interface Printer {
    /**
     * Method for print parsing result from Page
     *
     * @param review   Review for printing
     * @param filename /path/to/output.file
     * @throws JSONException
     * @throws IOException
     */
    void write(Review review, String filename) throws JSONException, IOException;

    /**
     * Method for finally format output
     *
     * @param filename filename for format
     */
    void formatOut(String filename);
}
