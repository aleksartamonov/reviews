/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 08.10.13
 * Time: 10:53
 * To change this template use File | Settings | File Templates.
 */
public class Rating {
    String value;
    String worst;
    String best;


    public Rating(String value, String worst, String best) {
        this.value = value;
        this.worst = worst;
        this.best = best;
    }
    public Rating(){
        this.value = Review.badString;
        this.best = Review.badString;
        this.worst =Review.badString;
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
