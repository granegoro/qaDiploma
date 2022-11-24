package test.api;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static data.APIHelper.*;
import static data.SQLHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackendDebitTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDatabase();
    }

    @Test
    void shouldGetStatusWhenApprovedDebit() {

        var card = DataHelper.Payment
                .generateValidApprovedCard( 3, 3);

        paymentActionDebit(card);

        var expectedStatus = "APPROVED";
        var actualStatus = getCardStatusForDebit();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void shouldGetStatusWhenDeclinedDebit() {

        var card = DataHelper.Payment
                .generateValidDeclinedCard( 3, 3);

        paymentActionDebit(card);

        var expectedStatus = "DECLINED";
        var actualStatus = getCardStatusForDebit();
        assertEquals(expectedStatus, actualStatus);
    }
}