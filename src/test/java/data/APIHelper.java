package data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIHelper {

    public static void paymentActionDebit(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = System.getProperty("sut.url");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/pay");
    }

    public static void paymentActionCredit(DataHelper.Payment.CardData cardData) {
        RestAssured.baseURI = System.getProperty("sut.url");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(cardData)
                .post("/api/v1/credit");
    }
}
