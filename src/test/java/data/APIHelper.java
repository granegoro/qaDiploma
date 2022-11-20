package data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIHelper {

    static String sutUrl = "http://localhost:8080";

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
