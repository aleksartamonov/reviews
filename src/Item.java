import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class Item {

    String val;
    @Expose Hproduct hproduct;

    public Item(String val, Hproduct hproduct) {
        this.val = val;
        this.hproduct = hproduct;
    }

    public Item() {
        this.val = Review.DEFAULT_STRING;
        this.hproduct = new Hproduct();
    }

    @Override
    public String toString() {
        return "Item{" +
                "val='" + val + '\'' +
                ", hproduct=" + hproduct +
                '}';
    }

}
