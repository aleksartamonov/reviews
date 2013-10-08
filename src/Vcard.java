/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class Vcard {

    String val;
    String fn;

    public Vcard(String val,String fn) {
        this.val = val;
        this.fn = fn;
    }
    public Vcard() {
        this.val = Review.badString;
        this.fn = Review.badString;
    }

    @Override
    public String toString() {
        return "Vcard{" +
                "val='" + val + '\'' +
                ", fn='" + fn + '\'' +
                '}';
    }
}
