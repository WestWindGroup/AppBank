package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class BalanceDecreaseCreditLogicImp extends CreditLogic {

    private GregorianCalendar calendar = new GregorianCalendar();


    @Override
    public List<String> calculateListPayments(Credit credit) {
        BigDecimal debt = credit.getAmountOfCredit();
        BigDecimal payment = calculateMinPayment(credit). setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal convertDuration = new BigDecimal(credit.getDurationOfContract());
        BigDecimal bodyPay = credit.getAmountOfCredit().divide(convertDuration,BigDecimal.ROUND_HALF_EVEN);
        List<String> listPayments = new ArrayList<>();
        String paymentToString = formatStringFirst();
        listPayments.add(paymentToString);
        for (int i = 1; i <= credit.getDurationOfContract(); i++) {
            calendar.add(Calendar.MONTH, 1);
            BigDecimal ratePay = ratePayment(credit.getInterestRate(),debt);
            debt = debt.subtract(bodyPay);
            paymentToString = formatString(ratePay, bodyPay, payment);
            listPayments.add(paymentToString);
        }
        calendar = new GregorianCalendar();
        return listPayments;
    }

    @Override
    public BigDecimal calculateMinPayment(Credit credit) {
        BigDecimal convertDuration = new BigDecimal(credit.getDurationOfContract());
        BigDecimal percentCredit = returnRatePaymentOfCredit(credit);
        BigDecimal bodyPayment = credit.getAmountOfCredit().divide(convertDuration, BigDecimal.ROUND_HALF_EVEN);
        return percentCredit.add(bodyPayment).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


    @Override
    public BigDecimal returnRatePaymentOfCredit(Credit credit) {

        return ratePayment(credit.getInterestRate(),credit.getSumOfIndebtedness());
    }

    private BigDecimal ratePayment(double interestRate,BigDecimal sumOfIndebtedness){
        int countDayOnMonth = daysInMonth(calendar);
        String helpValue = String.valueOf(countDayOnMonth * interestRate);
        BigDecimal bigDecimal = sumOfIndebtedness.multiply(new BigDecimal(helpValue));
        bigDecimal = bigDecimal.divide(new BigDecimal(100));
        return bigDecimal.divide(new BigDecimal(daysInYear(calendar)), BigDecimal.ROUND_HALF_EVEN).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private String formatString(BigDecimal ratePay, BigDecimal bodyPay, BigDecimal payment) {
        String curStringDate = new SimpleDateFormat("MM.yyyy").format(calendar.getTime());
        return String.format("| %7s |  %15s  |  %15s  |  %15s  |",
                curStringDate, ratePay, bodyPay, payment);
    }

    private String formatStringFirst() {

        return String.format("| %7s |  %15s  |  %15s  |  %15s  |", "Data", "RatePay", "BodyPay", "Payment");
    }


}