package website;

import printer.Format;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 16:30
 * To change this template use File | Settings | File Templates.
 */

public interface WebSite {
    /**
     * @param format   format of output
     * @param filename /path/to/output
     * @param n        (depth of extraction "-1" for try extract al reviews )
     *                 note: this is not number of n last reviews, this is number of pages for trying parse.
     *                 for example you can put n is 60 but get only 6 review
     */
    void getAllReviews(Format format, String filename, int n);

}
