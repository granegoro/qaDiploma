package data;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APIHelper {

    static String sutUrl = "http://localhost:8080";

    static String requestBodyApproved = "{\n" +
            "  \"number\": \"4444 4444 4444 4441\",\n" +
            "  \"year\": \"25\",\n" +
            "  \"month\": \"09\",\n" +
            "  \"holder\": \"One Two\",\n" +
            "  \"cvc\": \"123\"" + "}";

   static String requestBodyDeclined = "{\n" +
            "  \"number\": \"4444 4444 4444 4442\",\n" +
            "  \"year\": \"25\",\n" +
            "  \"month\": \"09\",\n" +
            "  \"holder\": \"One Two\",\n" +
            "  \"cvc\": \"123\"" + "}";

    public static void paymentActionDebitApproved() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyApproved)
                .post("/api/v1/pay");
    }

    public static void paymentActionDebitDeclined() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyDeclined)
                .post("/api/v1/pay");
    }

    public static void paymentActionCreditApproved() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyApproved)
                .post("/api/v1/credit");
    }

    public static void paymentActionCreditDeclined() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyDeclined)
                .post("/api/v1/credit");
    }
}
