/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:28
 * To change this template use File | Settings | File Templates.
 */
public class Review {

    public final static String badString = "";

    class Rating {
        String value;
        String worst;
        String best;

        public void setRating(String value, String worst, String best) {
            this.value = value;
            this.worst = worst;
            this.best = best;
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

    public class Reviewer {
        String val;
        Vcard vcard;

        public void setReviewer(String val) {
            this.val = val;
            this.vcard = new Vcard();
        }

        @Override
        public String toString() {
            return "Reviewer{" +
                    "val='" + val + '\'' +
                    ", vcard=" + vcard +
                    '}';
        }

        public class Vcard {

            String val;
            String fn;

            public void setVcard(String val,String fn) {
                this.val = val;
                this.fn = fn;
            }

            @Override
            public String toString() {
                return "Vcard{" +
                        "val='" + val + '\'' +
                        ", fn='" + fn + '\'' +
                        '}';
            }
        }
    }
    public class Item {

        String val;
        Hproduct hproduct;

        public void setItem(String val) {
            this.val = val;
            this.hproduct = new Hproduct();
        }

        @Override
        public String toString() {
            return "Item{" +
                    "val='" + val + '\'' +
                    ", hproduct=" + hproduct +
                    '}';
        }

        public class Hproduct {

            String val;
            String category;
            String photo;
            String url;
            String brand;
            String fn;
            String identifier;

            public void setHproduct(String val, String category, String photo, String url, String brand, String fn,
                            String identifier) {
                this.val = val;
                this.category = category;
                this.photo = photo;
                this.url = url;
                this.brand = brand;
                this.fn = fn;
                this.identifier = identifier;
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
    }
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
                     String type, String owningTime, String reviewsUrl) {
        this.summary = summary;
        this.description = description;
        this.pro = pro;
        this.contra = contra;
        this.dtReviewed = dtReviewed;
        this.permalink = permalink;
        this.type = type;
        this.owningTime = owningTime;
        this.reviewsUrl = reviewsUrl;
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
