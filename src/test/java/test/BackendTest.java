package test;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static data.SQLHelper.getCardStatusForCredit;
import static data.SQLHelper.getCardStatusForDebit;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDatabase();
    }

    String sutUrl = "http://localhost:8080";

    String requestBodyApproved = "{\n" +
            "  \"number\": \"4444 4444 4444 4441\",\n" +
            "  \"year\": \"25\",\n" +
            "  \"month\": \"09\",\n" +
            "  \"holder\": \"One Two\",\n" +
            "  \"cvc\": \"123\"" + "}";

    String requestBodyDeclined = "{\n" +
            "  \"number\": \"4444 4444 4444 4442\",\n" +
            "  \"year\": \"25\",\n" +
            "  \"month\": \"09\",\n" +
            "  \"holder\": \"One Two\",\n" +
            "  \"cvc\": \"123\"" + "}";

    public void paymentActionDebitApproved() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyApproved)
                .post("/api/v1/pay");
    }

    public void paymentActionDebitDeclined() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyDeclined)
                .post("/api/v1/pay");
    }

    public void paymentActionCreditApproved() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyApproved)
                .post("/api/v1/credit");
    }

    public void paymentActionCreditDeclined() {
        RestAssured.baseURI = sutUrl;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBodyDeclined)
                .post("/api/v1/credit");
    }

    //Debit

    @Test
    void shouldGetStatusWhenApprovedDebit() {

        paymentActionDebitApproved();

        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForDebit();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void shouldGetStatusWhenDeclinedDebit() {

        paymentActionDebitDeclined();

        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForDebit();
        assertEquals(expectedStatus, actualStatus);
    }

    //Credit

    @Test
    void shouldGetStatusWhenApprovedCredit() {

        paymentActionCreditApproved();

        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForCredit();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void shouldGetStatusWhenDeclinedCredit() {

        paymentActionCreditDeclined();

        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForCredit();
        assertEquals(expectedStatus, actualStatus);
    }
}