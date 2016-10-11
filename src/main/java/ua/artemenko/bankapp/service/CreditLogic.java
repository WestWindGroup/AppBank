package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class CreditLogic {

    protected GregorianCalendar calendar = new GregorianCalendar();

    abstract public List<String> calculateListPayments(Credit credit);

    abstract public BigDecimal calculateMinPayment(Credit credit);

    abstract public BigDecimal returnRatePaymentOfCredit(Credit credit);

    public static int daysInMonth(GregorianCalendar calendar) {

        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};
        if(calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR))){
            daysInMonths[1] += 1;
        }

        return daysInMonths[calendar.get(GregorianCalendar.MONTH)];
    }

    public static int daysInYear(GregorianCalendar calendar) {
        int countDaysInYear = 365;
        int countDaysInLeapYear = 366;

        if(calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR))){
            return countDaysInLeapYear;
        }else{
            return countDaysInYear;
        }
    }

    protected String formatString(BigDecimal ratePay, BigDecimal bodyPay, BigDecimal payment) {
        String curStringDate = new SimpleDateFormat("MM.yyyy").format(calendar.getTime());
        return String.format("| %7s |  %15s  |  %15s  |  %15s  |",
                curStringDate, ratePay, bodyPay, payment);
    }

    protected String formatStringFirst() {

        return String.format("| %7s |  %15s  |  %15s  |  %15s  |", "Data", "RatePay", "BodyPay", "Payment");
    }

}
