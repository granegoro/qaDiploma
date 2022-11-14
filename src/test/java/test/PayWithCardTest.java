package test;

import com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.apache.http.io.SessionOutputBuffer;
import org.junit.jupiter.api.*;
import page.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;
import static data.DataHelper.*;

public class PayWithCardTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

//    @BeforeEach
//    void setUpSutUrl() {
//        open("http://localhost:8080", MainPage.class);
//    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
//        SQLHelper.cleanDatabase();
    }


    @Test
    void shouldSuccessfullyPayWithCard() {

        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage= mainPage.payWithCard();
        var card = DataHelper.Payment
                .generateValidApprovedCard("en", 3, 3);
        paymentPage.makePayment(card);
        paymentPage.findSuccessMessage();
    }

    @Test
    void shouldFailIfBlankFields() {
        var mainPage = open("http://localhost:8080", MainPage.class);
        var paymentPage= mainPage.payWithCard();
        var card = DataHelper.Payment.generateEmptyFieldsCard();
        paymentPage.makePayment(card);
        paymentPage.findEmptyFieldErrors();



    }
//    var loginPage = open("http://localhost:9999", LoginPageV2.class);
//    var authInfo = DataHelper.getAuthInfo();
//    var verificationPage = loginPage.validLogin(authInfo);
//    var verificationCode = DataHelper.getVerificationCode();
//    var dashboardPage = verificationPage.validVerify(verificationCode);
//    var firstCardInfo = getFirstCardInfo();
//    var secondCardInfo = getSecondCardInfo();
//    var firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
//    var secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
//    var amount = generateValidAmount(firstCardBalance);
//    var expectedBalanceFirstCard = firstCardBalance - amount;
//    var expectedBalanceSecondCard = secondCardBalance + amount;
}
