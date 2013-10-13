package parser.impl;

import review.*;

import java.util.HashMap;

/**
* Created with IntelliJ IDEA.
* User: aleksey
* Date: 13.10.13
* Time: 20:47
*/

/**
* test this review
* http://adekb.ru/cars/reviews/?ID=582200
*/
public class PageParserFormat1Test extends AssertionError{

    private Review review;

    @org.junit.Before
    public void setUp() throws Exception {
        String summary = null;
        String description = "Приходится ездить не только по асфальту, но и по грунтовке, грейдеру и т.п. Машина везде проходит. Езжу конечно щадяще, это все-таки не внедорожник. Обслуживание копейки. Глюкнулась штатная магнитола, так я ее выкинул вообще и заказал 2-диновую с навигатором и камерой заднего вида. Стоит конечно не мало (12 тыс.), но зато очень удобно. За год прошел 35 тыс., полет нормальный.";
        String pro = "Комфорт, вместительность, большой багажник, доступное обслуживание, хорошая управляемость, хорошая эргономика.";
        String contra = "Немного шумноват.";
        String dtReviewed = "2013-09-25";
        String permalink = "adekb.ru/cars/reviews/?ID=582200";
        String type = "auto";
        String owningTime = null;
        String reviewsUrl= null;
        Rating rating = new Rating("4",null,null);
        Reviewer reviewer = new Reviewer(null,new Vcard(null,"Виктор"));

        String val = null;
        String category = "auto";
        String photo = null;
        String url = "adekb.ru/cars/catalogue/lifan/solano-2010/";
        String brand = "Lifan";
        String fn = "Solano";

        HashMap<String, String> identifier = new HashMap<String,String>();

        identifier.put("prodyear","2012");
        identifier.put("displacement","1.6");
        identifier.put("transmission","МКПП");
        identifier.put("body-type","седан");
        identifier.put("run","35000");

        Hproduct hproduct = new Hproduct(val,category,photo,url,brand,fn,identifier);
        Item item = new Item(null,hproduct);

        review = new Review(summary,description,pro,contra,dtReviewed,permalink,type,owningTime,reviewsUrl,
                            rating,reviewer,item);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        review = null;
    }

    @org.junit.Test
    public void testExtractInfo() throws Exception {

    }

    @org.junit.Test
    public void testGetReviewFromPage() throws Exception {
        PageParserFormat1 parser = new PageParserFormat1();
        Review myReview = parser.getReviewFromPage("http://adekb.ru/cars/reviews/?ID=582200");
        System.out.println(review.toString());
        System.out.println(myReview.toString());
        assert(review.toString().equals(myReview.toString()));
    }
}
