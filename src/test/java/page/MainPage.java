package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private static final SelenideElement payWithCardButton = $$("button").find(exactText("Купить"));
    private static final SelenideElement payWithCreditButton = $$("button").find(exactText("Купить в кредит"));
    private static final SelenideElement paymentHeading = $("#root > div > h3");

    public PaymentPage payWithCard() {
        payWithCardButton.click();
        paymentHeading.shouldHave(text("Оплата по карте"));
        return new PaymentPage();
    }

    public PaymentPage payWithCredit() {
        payWithCreditButton.click();
        paymentHeading.shouldHave(text("Кредит по данным карты"));
        return new PaymentPage();
    }
}
