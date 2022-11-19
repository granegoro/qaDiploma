package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static data.APIHelper.*;
import static data.SQLHelper.*;
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