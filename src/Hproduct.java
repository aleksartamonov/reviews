import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Hproduct {

    String val;
    @Expose String category;
    @Expose String photo;
    @Expose String url;
    @Expose String brand;
    @Expose String fn;
    @Expose Identifier identifier;

    public Hproduct(String val, String category, String photo, String url, String brand, String fn,
                    Identifier identifier) {
        this.val = val;
        this.category = category;
        this.photo = photo;
        this.url = url;
        this.brand = brand;
        this.fn = fn;
        this.identifier = identifier;
    }

    public Hproduct() {
        this.val = Review.DEFAULT_STRING;
        this.category = Review.DEFAULT_STRING;
        this.photo = Review.DEFAULT_STRING;
        this.url = Review.DEFAULT_STRING;
        this.brand = Review.DEFAULT_STRING;
        this.fn = Review.DEFAULT_STRING;
        this.identifier = new Identifier();
    }

    @Override
    public String toString() {
        return "Hproduct{" +
                "val='" + val + '\'' +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                ", url='" + url + '\'' +
                ", brand='" + brand + '\'' +
                ", fn='" + fn + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}
