package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import javax.xml.crypto.Data;
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

    private final SelenideElement successMessage = $(byText("Операция одобрена Банком."));
    private final SelenideElement failureMessage = $(byText("Ошибка! Банк отказал в проведении операции."));

    private final SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private final SelenideElement pushedContinueButton = $(".button_disabled");

    private final ElementsCollection errorFieldSet = $$(".input_invalid .input__sub");
    private final SelenideElement emptyCardFieldError = errorFieldSet.get(0);
    private final SelenideElement emptyMonthFieldError = errorFieldSet.get(1);
    private final SelenideElement emptyYearFieldError = errorFieldSet.get(2);
    private final SelenideElement emptyHolderFieldError = errorFieldSet.get(3);
    private final SelenideElement emptyCvvFieldError = errorFieldSet.get(4);


    public void makePayment(DataHelper.Payment.CardData cardData) {

        cardNumberField.setValue(cardData.getNumber());
        monthField.setValue(cardData.getMonth());
        yearField.setValue(cardData.getYear());
        cardholderField.setValue(cardData.getHolder());
        cvvField.setValue(cardData.getCvv());
        continueButton.click();
    }


    public void findPushedContinueButton() {
        pushedContinueButton.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void findSuccessMessage() {
        successMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    public void findFailureMessage() {
        failureMessage.shouldBe(visible, Duration.ofSeconds(30));
    }

    public void findImproperFormatError() {
        improperFormatError.shouldBe(visible);
    }

    public void findEmptyFieldError() {
        emptyFieldError.shouldBe(visible);
    }


    public void findEmptyFieldErrors() {
        emptyCardFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
        emptyMonthFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
        emptyYearFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
        emptyHolderFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
        emptyCvvFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
    }

//    public void findEmptyHolderFieldError() {
//        emptyHolderFieldError.shouldHave(text("Поле обязательно для заполнения")).shouldBe(visible);
//    }

    public void findInvalidDateError() {
        invalidDateError.shouldBe(visible);
    }

    public void findExpiredDateError() {
        expiredDateError.shouldBe(visible);
    }
}