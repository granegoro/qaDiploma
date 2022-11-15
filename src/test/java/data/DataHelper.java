package data;

import com.github.javafaker.Faker;
import lombok.Value;
import org.openqa.selenium.Keys;
import page.MainPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    static String clearAll = Keys.chord(Keys.SHIFT, Keys.HOME) + Keys.DELETE;

    static Faker faker = new Faker(new Locale("en"));

    static Faker fakerCyrillic = new Faker(new Locale("ru"));


    public static String generateApprovedCardNumber() {
        String number = ("4444 4444 4444 4441");
        return number;
    }

    public static String generateDeclinedCardNumber() {
        String number = ("4444 4444 4444 4442");
        return number;
    }

    public static String generateInvalidCardNumberIfLessSymbols() {
        String number = ("4444 4444 4444");
        return number;
    }

    public static String generateInvalidCardNumberIfOutOfBase() {
        String number = ("6758 4827 4657 1109");
        return number;
    }

    public static String generateMonth(int monthShift) {
        String month = LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String generateUnrealMonth() {
        var date = new String[]{
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"
        };
        return date[new Random().nextInt(date.length)];
    }

    public static String generateMonthInPast(int monthShift) {
        String month = LocalDate.now().minusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM"));
        return month;
    }

    public static String generateYear(int yearShift) {
        String year = LocalDate.now().plusYears(yearShift).format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String generateYearInPast(int yearShift) {
        String year = LocalDate.now().minusYears(yearShift).format(DateTimeFormatter.ofPattern("yy"));
        return year;
    }

    public static String generateDateZeros() {
        String zeroDate = "00";
        return zeroDate;
    }

    public static String generateOneFigureDate() {

        var date = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        return date[new Random().nextInt(date.length)];

    }

    public static String generateValidHolderName(String locale) {
        String name = faker.address().firstName() + " " + faker.address().lastName();
        return name;
    }

    public static String generateCyrillicHolderName(String locale) {
        String name = fakerCyrillic.address().firstName() + fakerCyrillic.address().lastName();
        return name;
    }

    public static String generateSymbolicHolderName() {
        var name = new String[]{
                "*%&^@", "#@$", "!@%"
        };
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNumericHolderName() {
        String name = faker.address().streetAddressNumber() + faker.address().streetAddressNumber();
        return name;
    }

    public static String generateInsufficientHolderName(String locale) {
        String name = faker.address().firstName();
        return name;
    }

    public static String generateExtensiveHolderName(String locale) {
        String name = faker.address().firstName() + faker.address().firstName() + faker.address().firstName();
        return name;
    }

    public static String generateCVV() {
        String cvv = faker.address().streetAddressNumber();
        return cvv;
    }

    public static String generateCVVZeros() {
        String cvv = "000";
        return cvv;
    }

    public static String generateCVVLessThanThreeFigures(int figure) {
        String cvv = "0" + figure;
        return cvv;
    }

    public static class Payment {
        private Payment() {
        }

        public static CardData generateValidApprovedCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateValidDeclinedCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateDeclinedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateEmptyFieldsCard() {

            return new CardData("", "", "", "", "");
        }

        public static CardData generateInsufficientNumberCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateInvalidCardNumberIfLessSymbols(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateOutOfBaseNumberCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateInvalidCardNumberIfOutOfBase(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateOneFigureMonthCard(String locale, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateOneFigureDate(),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateOneFigureYearCard(String locale, int monthShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateYear(monthShift),
                    generateOneFigureDate(),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateZeroDateCard(String locale) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateDateZeros(),
                    generateDateZeros(),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generatePastDateCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonthInPast(monthShift),
                    generateYearInPast(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateUnrealMonthCard(String locale, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateUnrealMonth(),
                    generateYear(yearShift),
                    generateValidHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateInsufficientHolderCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateInsufficientHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateExtensiveHolderCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateExtensiveHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateCyrillicHolderCard(String locale, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateCyrillicHolderName(locale),
                    generateCVV()
            );
        }

        public static CardData generateNumericHolderCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateNumericHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateSymbolicHolderCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateSymbolicHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateInsufficientCvvCard(int figure, int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateNumericHolderName(),
                    generateCVVLessThanThreeFigures(figure)
            );
        }

        public static CardData generateZeroCvvCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateNumericHolderName(),
                    generateCVVZeros()
            );
        }


        @Value
        public static class CardData {
            String number;
            String month;
            String year;
            String holder;
            String cvv;
        }
    }
}
