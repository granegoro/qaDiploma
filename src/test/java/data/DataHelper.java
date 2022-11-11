package data;

import lombok.Value;
import page.MainPage;

public class DataHelper {

    @Value
    public static class CardData {
        String number;
        String month;
        String year;
        String holder;
        String cvv;
    }

    public static CardData getApprovedCard() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivan Ivanov", "123");
    }

    public static CardData getDeclinedCard() {
        return new CardData("4444 4444 4444 4442", "10", "26", "Ivan Ivanov", "123");
    }

    //

    public static CardData getEmptyFieldsCard() {
        return new CardData("", "", "", "", "");
    }

    //

    public static CardData getInvalidCardNumberIfLess16Sym() {
        return new CardData("4444 4444 4444", "10", "26", "Ivan Ivanov", "123");
    }

    public static CardData getInvalidCardNumberIfOutOfBase() {
        return new CardData("5469 4444 4444 4441", "10", "26", "Ivan Ivanov", "123");
    }

    //

    public static CardData getInvalidNumberOfMonthIfOneSym() {
        return new CardData("4444 4444 4444 4441", "1", "26", "Ivan Ivanov", "123");
    }

    public static CardData getInvalidNumberOfMonthIfMore12() {
        return new CardData("4444 4444 4444 4441", "20", "26", "Ivan Ivanov", "123");
    }

    public static CardData getInvalidNumberOfMonthIfZero() {
        return new CardData("4444 4444 4444 4441", "00", "26", "Ivan Ivanov", "123");
    }

    //

    public static CardData getInvalidYearIfOneSym() {
        return new CardData("4444 4444 4444 4441", "10", "2", "Ivan Ivanov", "123");
    }

    public static CardData getInvalidYearIfBeforeCurrentYear() {
        return new CardData("4444 4444 4444 4441", "10", "19", "Ivan Ivanov", "123");
    }

    public static CardData getInvalidYearIfZero() {
        return new CardData("4444 4444 4444 4441", "10", "00", "Ivan Ivanov", "123");
    }

    //

    public static CardData getInvalidCardholderNameIfLessThanTwo() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivan", "123");
    }

    public static CardData getInvalidCardholderNameIfMoreThanTwo() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivanov Ivan Ivan", "123");
    }

    public static CardData getInvalidCardholderNameIfCyrillic() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Иван Иванов", "123");
    }

    public static CardData getInvalidCardholderNameIfNumeric() {
        return new CardData("4444 4444 4444 4441", "10", "26", "0000", "123");
    }

    public static CardData getInvalidCardholderNameIfSymbol() {
        return new CardData("4444 4444 4444 4441", "10", "26", "#%№", "123");
    }

    //

    public static CardData getInvalidCvvIfOneSym() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivan Ivanov", "1");
    }

    public static CardData getInvalidCvvIfTwoSym() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivan Ivanov", "12");
    }

    public static CardData getInvalidCvvIfThreeZero() {
        return new CardData("4444 4444 4444 4441", "10", "26", "Ivan Ivanov", "000");
    }




}
