package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class CreditLogic {

    protected GregorianCalendar calendar = new GregorianCalendar();

    abstract public List<String> calculateListPayments(Credit credit);

    abstract public BigDecimal calculateMinPayment(Credit credit);

    abstract public BigDecimal returnRatePaymentOfCredit(Credit credit);

    public static int daysInMonth(GregorianCalendar calendar) {

        int [] daysInMonths = {31,28,31,30,31,30,31,31,30,31,30,31};

        daysInMonths[1] += calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR)) ? 1 : 0;

        return daysInMonths[calendar.get(GregorianCalendar.MONTH)];

    }

    public static int daysInYear(GregorianCalendar calendar) {
        return calendar.isLeapYear(calendar.get(GregorianCalendar.YEAR)) ? 366 : 365;
    }

}
