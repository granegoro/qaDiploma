package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.MainPage;
import static com.codeborne.selenide.Selenide.open;

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
        SQLHelper.cleanDatabase();
    }


    @Test
    void shouldSuccessfullyPayWithCard() {

        var mainPage = open(localhost)


    }

}
