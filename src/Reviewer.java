/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class Reviewer {
    String val;
    Vcard vcard;

    public Reviewer(String val, Vcard vcard) {
        this.val = val;
        this.vcard = vcard;
    }
    public Reviewer(){
        this.val = Review.badString;
        this.vcard = new Vcard();
    }

    @Override
    public String toString() {
        return "Reviewer{" +
                "val='" + val + '\'' +
                ", vcard=" + vcard +
                '}';
    }
}
