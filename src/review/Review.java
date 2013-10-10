package review;

import com.google.gson.annotations.Expose;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 09.10.13
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class Review {

    public final static String DEFAULT_STRING = null;
    @Expose
    String summary;
    @Expose
    String description;
    @Expose
    String pro;
    @Expose
    String contra;
    @Expose
    String dtReviewed;
    @Expose
    String permalink;
    @Expose
    String type;
    @Expose
    String owningTime;
    @Expose
    String reviewsUrl;
    @Expose
    Rating rating;
    @Expose
    Reviewer reviewer;
    @Expose
    Item item;

    public Review(String summary, String description, String pro, String contra, String dtReviewed, String permalink,
                  String type, String owningTime, String reviewsUrl, Rating rating, Reviewer reviewer, Item item) {
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

    public Review() {
        this.summary = DEFAULT_STRING;
        this.description = DEFAULT_STRING;
        this.pro = DEFAULT_STRING;
        this.contra = DEFAULT_STRING;
        this.dtReviewed = DEFAULT_STRING;
        this.permalink = DEFAULT_STRING;
        this.type = DEFAULT_STRING;
        this.owningTime = DEFAULT_STRING;
        this.reviewsUrl = DEFAULT_STRING;
        this.rating = null;
        this.reviewer = null;
        this.item = null;
    }

    @Override
    public String toString() {
        return "review.review.Review.review.Review{" +
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


    public String getPermalink() {
        return permalink;
    }
}

