package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class UITest {

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

    //Debit

    @Test
    void shouldPassIfValidApprovedCard() {

        var mainPage = open(sutUrl, MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidApprovedCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findSuccessMessage();
    }

    @Test
    void shouldFailIfValidDeclinedCard() {

        var mainPage = open(sutUrl, MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidDeclinedCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findFailureMessage();
    }

    //Credit

    @Test
    void shouldPassIfValidApprovedCredit() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCredit();
        var card = DataHelper.Payment
                .generateValidDeclinedCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findSuccessMessage();
    }
    @Test
    void shouldFailIfValidDeclinedCredit() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCredit();
        var card = DataHelper.Payment
                .generateValidDeclinedCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findFailureMessage();
    }

    // Blank fields

    @Test
    void shouldFailIfBlankFields() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateEmptyFieldsCard();
        paymentPage.makePayment(card);
        paymentPage.findEmptyFieldErrors();
    }

    //Card number field validation

    @Test
    void shouldFailIfInsufficientCardNumber() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCredit();
        var card = DataHelper.Payment
                .generateInsufficientNumberCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfOutOfBaseCardNumber() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateOutOfBaseNumberCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findFailureMessage();
    }

    // Date
    @Test
    void shouldFailIfOneFigureMonthCardNumber() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateOneFigureMonthCard("en", 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
     void shouldFailIfOneFigureYearCardNumber() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateOneFigureYearCard("en", 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfZerosDate() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateZeroDateCard("en");
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfMonthInPast() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generatePastDateCard("en", 3, 0);
        paymentPage.makePayment(card);
        paymentPage.findExpiredDateError();
    }

    @Test
    void shouldFailIfYearInPast() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generatePastDateCard("en", 0, 3);
        paymentPage.makePayment(card);
        paymentPage.findExpiredDateError();
    }

    @Test
    void shouldFailIfUnrealMonth() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateUnrealMonthCard("en",  3);
        paymentPage.makePayment(card);
        paymentPage.findInvalidDateError();
    }

    @Test
    void shouldFailIfTooFarFutureYear() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidApprovedCard("en", 3, 10);
        paymentPage.makePayment(card);
        paymentPage.findInvalidDateError();
    }

    //Holder name field validation

    @Test
    void shouldFailIfInsufficientHolder() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateInsufficientHolderCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfExtensiveHolder() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateExtensiveHolderCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfCyrillicHolder() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateCyrillicHolderCard("ru", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfNumericHolder() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateNumericHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfSymbolicHolder() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateSymbolicHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    //CVV

    @Test
    void shouldFailIfInsufficientCvv() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateInsufficientCvvCard(3, 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfIfZerosCvv() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateZeroCvvCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }
}