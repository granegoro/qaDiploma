package data;

import lombok.Value;

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



}
