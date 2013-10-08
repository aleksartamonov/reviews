/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class Review {

    public final static String badString = "";

    String summary;
    String description;
    String pro;
    String contra;
    String dtReviewed;
    String permalink;
    String type;
    String owningTime;
    String reviewsUrl;
    Rating rating;
    Reviewer reviewer;
    Item item;

    public Review(String summary, String description, String pro, String contra, String dtReviewed, String permalink,
                     String type, String owningTime, String reviewsUrl,Rating rating,Reviewer reviewer,Item item) {
        this.summary = summary;
        this.description = description;
        this.pro = pro;
        this.contra = contra;
        this.dtReviewed = dtReviewed;
        this.permalink = permalink;
        this.type = type;
        this.owningTime = owningTime;
        this.reviewsUrl = reviewsUrl;
        this.rating = rating;
        this.reviewer = reviewer;
        this.item = item;
    }
    public Review(){
        this.summary = badString;
        this.description = badString;
        this.pro = badString;
        this.contra = badString;
        this.dtReviewed = badString;
        this.permalink = badString;
        this.type = badString;
        this.owningTime = badString;
        this.reviewsUrl = badString;
        this.rating = new Rating();
        this.reviewer = new Reviewer();
        this.item = new Item();
    }

    @Override
    public String toString() {
        return "Review{" +
                "summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", pro='" + pro + '\'' +
                ", contra='" + contra + '\'' +
                ", dtReviewed='" + dtReviewed + '\'' +
                ", permalink='" + permalink + '\'' +
                ", type='" + type + '\'' +
                ", owningTime='" + owningTime + '\'' +
                ", reviewsUrl='" + reviewsUrl + '\'' +
                ", rating=" + rating +
                ", reviewer=" + reviewer +
                ", item=" + item +
                '}';
    }




}
