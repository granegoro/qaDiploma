package test;

import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.getCardStatusForPayment;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @Test
    void shouldPassIfValidApprovedCard() {

        var mainPage = open(sutUrl, MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidApprovedCard("en", 3, 3);
        paymentPage.makePayment(card);


        val expectedStatus = "APPROVED";
        val actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void shouldFailIfValidDeclinedCard() {

        var mainPage = open(sutUrl, MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidDeclinedCard("en", 3, 3);
        paymentPage.makePayment(card);


        val expectedStatus = "DECLINED";
        val actualStatus = getCardStatusForPayment();
        assertEquals(expectedStatus, actualStatus);
    }
}