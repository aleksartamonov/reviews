import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class Rating {

    @Expose
    String value;
    @Expose
    String worst;
    @Expose
    String best;


    public Rating(String value, String worst, String best) {
        this.value = value;
        this.worst = worst;
        this.best = best;
    }

    public Rating() {
        this.value = Review.DEFAULT_STRING;
        this.best = Review.DEFAULT_STRING;
        this.worst = Review.DEFAULT_STRING;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "value='" + value + '\'' +
                ", worst='" + worst + '\'' +
                ", best='" + best + '\'' +
                '}';
    }
}
