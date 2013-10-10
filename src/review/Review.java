package review;

import com.google.gson.annotations.Expose;
import logger.Logger;
import org.jsoup.select.Elements;
import parser.PageParserFormat1;

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
public class Review extends InfoBlock {
    public final static String DEFAULT_STRING = null;

    @Expose
    private String summary;
    @Expose
    private String description;
    @Expose
    private String pro;
    @Expose
    private String contra;
    @Expose
    private String dtReviewed;
    @Expose
    private String permalink;
    @Expose
    private String type;
    @Expose
    private String owningTime;
    @Expose
    private String reviewsUrl;
    @Expose
    private Rating rating;
    @Expose
    private Reviewer reviewer;
    @Expose
    private Item item;

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
        return "review.review.review.Review.review.review.Review{" +
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

    @Override
    public Review extractInfoFormat1(Elements e, String mainPageUrl) {
        summary = extractTextFromTag(e, "summary");
        description = extractTextFromTag(e, "description");
        pro = extractTextFromTag(e, "pro");
        contra = extractTextFromTag(e, "contra");
        dtReviewed = extractTextFromTag(e, "dtReviewed");
        reviewsUrl = extractTextFromTag(e, "reviewsUrl");
        type = extractTextFromTag(e, "type");
        owningTime = extractTextFromTag(e, "owningTime");
        permalink = extractHrefValFromTag(e, "permalink", mainPageUrl);

        PageParserFormat1 parser = new PageParserFormat1();

        Elements eRating = e.select(".rating");
        Rating rating =parser.extractInfo(eRating, Rating.class);

        Elements eReviewer = e.select(".reviewer");
        Reviewer reviewer = parser.extractInfo(eReviewer, Reviewer.class);

        Elements eItem = e.select(".item");
        Item item = parser.extractInfo(eItem, Item.class);


        return new Review(summary, description, pro, contra, dtReviewed, permalink,
                type, owningTime, reviewsUrl, rating, reviewer, item);
    }

    @Override
    public void logAboutFail(String reviewPageUrl) {
        Logger.LOG.warn("Review Info couldn't extract from " + reviewPageUrl);
    }
}

