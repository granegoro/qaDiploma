package data;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Locale;

public class APIHelper {

    static Faker faker = new Faker(new Locale("en"));

    static String sutUrl = "http://localhost:8080";

//    static String requestBodyApproved = "{\n" +
//            "  \"number\": \"4444 4444 4444 4441\",\n" +
//            "  \"year\": \"25\",\n" +
//            "  \"month\": \"09\",\n" +
//            "  \"holder\": \"One Two\",\n" +
//            "  \"cvc\": \"123\"" + "}";
//
//   static String requestBodyDeclined = "{\n" +
//            "  \"number\": \"4444 4444 4444 4442\",\n" +
//            "  \"year\": \"25\",\n" +
//            "  \"month\": \"09\",\n" +
//            "  \"holder\": \"One Two\",\n" +
//            "  \"cvc\": \"123\"" + "}";

    public static void paymentActionDebitApproved(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/pay");
    }

    public static void paymentActionDebitDeclined(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/pay");
    }

    public static void paymentActionCreditApproved(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/credit");
    }

    public static void paymentActionCreditDeclined(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/credit");
    }
}
