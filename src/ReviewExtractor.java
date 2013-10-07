/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 14:34
 * To change this template use File | Settings | File Templates.
 */
public class ReviewExtractor {
    public static void main(String[] args) {

        PageParser p = new PageParser();
        Review res = p.getReviewFromPage("http://adekb.ru/cars/reviews/?ID=532682");
    }
}
