package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    static Faker faker = new Faker(new Locale("en"));

    static Faker fakerCyrillic = new Faker(new Locale("ru"));


    public static String generateApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String generateDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String generateInvalidCardNumberIfLessSymbols() {
        return "4444 4444 4444";
    }

    public static String generateInvalidCardNumberIfOutOfBase() {
        return "6758 4827 4657 1109";
    }

    public static String generateMonth(int monthShift) {
        return (LocalDate.now().plusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM")));
    }

    public static String generateUnrealMonth() {
        var date = new String[]{
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"
        };
        return date[new Random().nextInt(date.length)];
    }

    public static String generateMonthInPast(int monthShift) {
        return (LocalDate.now().minusMonths(monthShift).format(DateTimeFormatter.ofPattern("MM")));
    }

    public static String generateYear(int yearShift) {
        return (LocalDate.now().plusYears(yearShift).format(DateTimeFormatter.ofPattern("yy")));
    }

    public static String generateYearInPast(int yearShift) {
        return (LocalDate.now().minusYears(yearShift).format(DateTimeFormatter.ofPattern("yy")));
    }

    public static String generateDateZeros() {
        return "00";
    }

    public static String generateOneFigureDate() {

        var date = new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };
        return date[new Random().nextInt(date.length)];

    }

    public static String generateValidHolderName() {
        return (faker.address().firstName() + " " + faker.address().lastName());
    }

    public static String generateCyrillicHolderName() {
        return (fakerCyrillic.address().firstName() + " " + fakerCyrillic.address().lastName());
    }

    public static String generateSymbolicHolderName() {
        var name = new String[]{
                "*%&^@", "#@$", "!@%"
        };
        return name[new Random().nextInt(name.length)];
    }

    public static String generateNumericHolderName() {
        return (faker.address().streetAddressNumber() + " " +  faker.address().streetAddressNumber());
    }

    public static String generateInsufficientHolderName() {
        return (faker.address().firstName());
    }

    public static String generateExtensiveHolderName() {
        return (faker.address().firstName() + " " + faker.address().firstName()
                + " " + faker.address().firstName());
    }

    public static String generateCVV() {
        return (faker.address().streetAddressNumber() + "1");
    }

    public static String generateCVVZeros() {
        return "000";
    }

    public static String generateCVVLessThanThreeFigures(int figure) {
        return ("0" + figure);
    }

    public static class Payment {
        private Payment() {
        }

        public static CardData generateValidApprovedCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateValidDeclinedCard(int monthShift, int yearShift) {

            return new CardData(
                    generateDeclinedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateEmptyFieldsCard() {

            return new CardData("", "", "", "", "");
        }

        public static CardData generateInsufficientNumberCard(int monthShift, int yearShift) {

            return new CardData(
                    generateInvalidCardNumberIfLessSymbols(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateOutOfBaseNumberCard(int monthShift, int yearShift) {

            return new CardData(
                    generateInvalidCardNumberIfOutOfBase(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateOneFigureMonthCard(int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateOneFigureDate(),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateOneFigureYearCard(int monthShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateYear(monthShift),
                    generateOneFigureDate(),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateZeroDateCard() {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateDateZeros(),
                    generateDateZeros(),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generatePastDateCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonthInPast(monthShift),
                    generateYearInPast(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateUnrealMonthCard(int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateUnrealMonth(),
                    generateYear(yearShift),
                    generateValidHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateInsufficientHolderCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateInsufficientHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateExtensiveHolderCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateExtensiveHolderName(),
                    generateCVV()
            );
        }

        public static CardData generateCyrillicHolderCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateCyrillicHolderName(),
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
                    generateValidHolderName(),
                    generateCVVLessThanThreeFigures(figure)
            );
        }

        public static CardData generateZeroCvvCard(int monthShift, int yearShift) {

            return new CardData(
                    generateApprovedCardNumber(),
                    generateMonth(monthShift),
                    generateYear(yearShift),
                    generateValidHolderName(),
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