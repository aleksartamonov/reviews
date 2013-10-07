import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: aleksey
 * Date: 07.10.13
 * Time: 18:47
 * To change this template use File | Settings | File Templates.
 */
public class PageParser {

    Review getReviewFromPage(String url){
        // полуение Review со страницы adEkb
        Review review = null;
        Document doc;
        try {
            // get doc

            doc = Jsoup.connect(url).get();
            Elements tag = doc.select(".hreview");
            System.out.println(tag);

            // prepare string for constructor of Review
            String summary;
            try{
                summary = tag.select(".summary").first().text();
            } catch (NullPointerException e){
                summary = Review.badString;
            }
            String description;
            try{
                description = tag.select(".description").first().text();
            }catch (NullPointerException e){
                description =  Review.badString;
            }
            String pro;
            try{
                pro = tag.select(".pro").first().text();
            }catch (NullPointerException e){
                pro =  Review.badString;
            }
            String contra;
            try{
                contra = tag.select(".contra").first().text();
            }catch (NullPointerException e){
                contra =  Review.badString;
            }

            String dtReviewed;
            try{
                dtReviewed = tag.select(".dtreviewed").first().text();
            }catch (NullPointerException e){
                dtReviewed =  Review.badString;
            }
            String permalink;
            try{
                permalink = url + tag.select(".permalink").attr("href");
            }catch (NullPointerException e){
                permalink =  Review.badString;
            }
            String type;
            try{
                type = tag.select(".type").first().text();
            }catch (NullPointerException e){
                type =  Review.badString;
            }
            String owningTime;
            try{
                owningTime = tag.select(".owningTime").first().text();
            }catch (NullPointerException e){
                owningTime =  Review.badString;
            }
            String reviewsUrl;
            try{
                reviewsUrl = tag.select(".reviewsUrl").first().text();
            }catch (NullPointerException e){
                reviewsUrl =  Review.badString;
            }

            //construct Review
            review = new Review(summary,description,pro,contra,dtReviewed,permalink,type,owningTime,reviewsUrl);

            //prepare strings for set Rating info in Review
            Elements eRating = tag.select(".rating");
            String ratVal;
            String ratWorst;
            String ratBest;
            try{
                ratVal = eRating.first().text();
                try{
                    ratBest = eRating.select(".best").first().text();

                }catch (NullPointerException e){
                    ratBest =  Review.badString;
                }

                try{
                    ratWorst = eRating.select(".worst").first().text();

                }catch (NullPointerException e){
                    ratWorst = Review.badString;
                }

            }catch (NullPointerException e){
                ratVal =  Review.badString;
                ratBest =  Review.badString;
                ratWorst = Review.badString;

            }


            // set Rating params of review
            review.rating.setRating(ratVal,ratWorst,ratBest);

            //prepare Reviewer params for Review

            Elements eReviewer = tag.select(".reviewer");
            String revVal,vcardVal,vcardFn;
           // System.out.println(eReviewer);
            try{
                revVal = eReviewer.first().text();

                Elements eVcard = eReviewer.select(".vcard");
                try{
                    vcardVal = eVcard.first().text();
                    Elements eFn = eRating.select(".fn");
                    try{
                        vcardFn = eFn.first().text();
                    }catch (NullPointerException e){
                        vcardFn = Review.badString;
                    }

                }catch (NullPointerException e){
                    vcardVal = Review.badString;
                    vcardFn = Review.badString;
                }
            } catch (NullPointerException e){
                revVal = Review.badString;
                vcardVal = Review.badString;
                vcardFn = Review.badString;

            }
            //set reviewer params to review
            review.reviewer.setReviewer(revVal);
            review.reviewer.vcard.setVcard(vcardVal,vcardFn);

            //prepare to set car params to review
            Elements eItems = tag.select(".item");
            String itemVal,hProductVal,pCategory,pPhoto,productUrl,productBrand,productFn,pIdentifier;
            //System.out.println(eItems);
            try{
                itemVal = eItems.first().text();
                Elements eProducts = eItems.select(".hproduct");
                try{
                    hProductVal = eProducts.first().text();
                    try{
                        pCategory = eProducts.select(".category").first().text();
                    } catch (NullPointerException e){
                        pCategory = Review.badString;
                    }
                    try{
                        pPhoto = eProducts.select(".photo").first().text();
                    } catch (NullPointerException e){
                        pPhoto = Review.badString;
                    }
                    try{
                        Elements productUrlTag = eProducts.select(".url");
                        productUrl = productUrlTag.attr("href");
                    } catch (NullPointerException e){
                        productUrl = Review.badString;
                    }
                    try{
                        productBrand = eProducts.select(".brand").first().text();
                    } catch (NullPointerException e){
                        productBrand = Review.badString;
                    }
                    try{
                        productFn = eProducts.select(".fn").first().text();
                    } catch (NullPointerException e){
                        productFn = Review.badString;
                    }
                    try{
                        pIdentifier = eProducts.select(".identifier").text();
                    } catch (NullPointerException e){
                        pIdentifier = Review.badString;
                    }


                } catch (NullPointerException e){
                    hProductVal = Review.badString;
                    pCategory = Review.badString;
                    pPhoto = Review.badString;
                    productUrl = Review.badString;
                    productBrand = Review.badString;
                    productFn = Review.badString;
                    pIdentifier = Review.badString;
                }

            }catch (NullPointerException e){
                itemVal = Review.badString;
                hProductVal = Review.badString;
                pCategory = Review.badString;
                pPhoto = Review.badString;
                productUrl = Review.badString;
                productBrand = Review.badString;
                productFn = Review.badString;
                pIdentifier = Review.badString;
            }

            //set car params to review

            review.item.setItem(itemVal);
            review.item.hproduct.setHproduct(hProductVal,pCategory,pPhoto,productUrl,productBrand,productFn,pIdentifier);


        }catch (IOException e){

        }
        System.out.println("All page info");
        System.out.println(review);
        return review;
    }
}
