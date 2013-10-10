package parser;

import review.Review;

import java.net.URISyntaxException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 15:43
 * To change this template use File | Settings | File Templates.
 */
/**
 *   Interface for Parsers of Pages
 */
public interface PageParser {

    Review getReviewFromPage(String url) throws URISyntaxException;
}
