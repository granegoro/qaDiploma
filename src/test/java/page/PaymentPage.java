package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final ElementsCollection fieldSet = $$(".input__control");
    private final SelenideElement cardholderField = fieldSet.get(3);
    private final SelenideElement cvvField = $("input[placeholder='999']");

    private final SelenideElement improperFormatError = $(byText("Неверный формат"));
    private final SelenideElement emptyFieldError = $(byText("Поле обязательно для заполнения"));
    private final SelenideElement invalidDateError = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement expiredDateError = $(byText("Истёк срок действия карты"));

    private final SelenideElement statusMessage = $(".notification");

    private final SelenideElement successMessage = $(byText("Операция одобрена Банком"));

    private final SelenideElement failureMessage = $(byText("Ошибка! Банк отказал в проведении операции."));

    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));

    private final SelenideElement pushedContinueButton = $(".button_disabled");

    public void makePayment(DataHelper.CardData cardData) {
        cardNumberField.setValue(cardData.getNumber());
        monthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        cardholderField.setValue(cardData.getHolder());
        cvvField.setValue(cardData.getCvv());
        continueButton.click();
    }


    public void findPushedContinueButton() {pushedContinueButton.shouldBe(visible);}

    public void findSuccessMessage() {
        statusMessage.shouldHave(text("Операция одобрена Банком")).shouldBe(visible, Duration.ofSeconds(15));
    }
    public void findFailureMessage() {
        statusMessage.shouldHave(text("Ошибка! Банк отказал в проведении операции."))
                .shouldBe(visible, Duration.ofSeconds(15));
    }

    public void findImproperFormatError() {
        improperFormatError.shouldBe(visible);
    }

    public void findEmptyFieldError() {
        emptyFieldError.shouldBe(visible);
    }

    public void findInvalidDateError() {
        invalidDateError.shouldBe(visible);
    }

    public void findExpiredDateError() {
        expiredDateError.shouldBe(visible);
    }


    //



}
