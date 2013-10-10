package review;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class Vcard {

    String val;
    @Expose
    String fn;

    public Vcard(String val, String fn) {
        this.val = val;
        this.fn = fn;
    }

    public Vcard() {
        this.val = Review.DEFAULT_STRING;
        this.fn = Review.DEFAULT_STRING;
    }

    @Override
    public String toString() {
        return "review.Vcard{" +
                "val='" + val + '\'' +
                ", fn='" + fn + '\'' +
                '}';
    }
}
