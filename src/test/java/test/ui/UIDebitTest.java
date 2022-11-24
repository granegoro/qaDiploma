package test.ui;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class UIDebitTest {

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
    void shouldPassIfValidApprovedCard() {

        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateValidApprovedCard( 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findSuccessMessage();
    }

    @Test
    void shouldFailIfValidDeclinedCard() {

        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateValidDeclinedCard( 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findPushedContinueButton();
        paymentPage.findFailureMessage();
    }

    // Blank fields

    @Test
    void shouldFailIfBlankFields() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateEmptyFieldsCard();
        paymentPage.makePayment(card);
        paymentPage.findEmptyFieldErrors();
    }

    //Card number field validation

    @Test
    void shouldFailIfInsufficientCardNumber() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCredit();
        var card = DataHelper.Payment
                .generateInsufficientNumberCard( 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfOutOfBaseCardNumber() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateOutOfBaseNumberCard( 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findFailureMessage();
    }

    // Date
    @Test
    void shouldFailIfOneFigureMonthCardNumber() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateOneFigureMonthCard( 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
     void shouldFailIfOneFigureYearCardNumber() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateOneFigureYearCard( 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfZerosDate() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateZeroDateCard();
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfMonthInPast() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generatePastDateCard(3, 0);
        paymentPage.makePayment(card);
        paymentPage.findExpiredDateError();
    }

    @Test
    void shouldFailIfYearInPast() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generatePastDateCard(0, 3);
        paymentPage.makePayment(card);
        paymentPage.findExpiredDateError();
    }

    @Test
    void shouldFailIfUnrealMonth() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateUnrealMonthCard(3);
        paymentPage.makePayment(card);
        paymentPage.findInvalidDateError();
    }

    @Test
    void shouldFailIfTooFarFutureYear() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidApprovedCard(3, 10);
        paymentPage.makePayment(card);
        paymentPage.findInvalidDateError();
    }

    //Holder name field validation

    @Test
    void shouldFailIfInsufficientHolder() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateInsufficientHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfExtensiveHolder() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateExtensiveHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfCyrillicHolder() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateCyrillicHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfNumericHolder() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateNumericHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfSymbolicHolder() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateSymbolicHolderCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    //CVV

    @Test
    void shouldFailIfInsufficientCvv() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateInsufficientCvvCard(3, 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }

    @Test
    void shouldFailIfIfZerosCvv() {
        var mainPage = open(System.getProperty("sut.url"), MainPage.class);
        var paymentPage = mainPage.payWithCard();
        var card = DataHelper.Payment.generateZeroCvvCard(3, 3);
        paymentPage.makePayment(card);
        paymentPage.findImproperFormatError();
    }
}