/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class Hproduct {

    String val;
    String category;
    String photo;
    String url;
    String brand;
    String fn;
    String identifier;

    public Hproduct(String val, String category, String photo, String url, String brand, String fn,
                            String identifier) {
        this.val = val;
        this.category = category;
        this.photo = photo;
        this.url = url;
        this.brand = brand;
        this.fn = fn;
        this.identifier = identifier;
    }
    public Hproduct(){
        this.val = Review.badString;
        this.category = Review.badString;
        this.photo = Review.badString;
        this.url = Review.badString;
        this.brand = Review.badString;
        this.fn = Review.badString;
        this.identifier = Review.badString;
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
