package ua.artemenko.bankapp.service;

import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.util.*;

public class BalanceDecreaseCreditLogicImpl extends CreditLogic {

    @Override
    public List<String> calculateListPayments(Credit credit) {
        BigDecimal debt = credit.getAmountOfCredit();
        BigDecimal convertDuration = new BigDecimal(credit.getDurationOfContract());
        BigDecimal bodyPay = credit.getAmountOfCredit().divide(convertDuration,BigDecimal.ROUND_HALF_EVEN);
        List<String> listPayments = new ArrayList<>();
        String paymentToString = formatStringFirst();
        listPayments.add(paymentToString);
        for (int i = 1; i <= credit.getDurationOfContract(); i++) {
            calendar.add(Calendar.MONTH, 1);
            BigDecimal ratePay = ratePayment(credit.getInterestRate(),debt);
            BigDecimal payment = ratePay.add(bodyPay);
            debt = debt.subtract(bodyPay);
            paymentToString = formatString(ratePay, bodyPay, payment);
            listPayments.add(paymentToString);
        }
        calendar = new GregorianCalendar();
        return listPayments;
    }

    @Override
    public BigDecimal calculateMinPayment(Credit credit) {
        BigDecimal convertDuration = new BigDecimal(String.valueOf(credit.getDurationOfContract()));
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
        BigDecimal big = sumOfIndebtedness.multiply(new BigDecimal(helpValue));
        big = big.divide(new BigDecimal(100));
        return big.divide(new BigDecimal(daysInYear(calendar)), BigDecimal.ROUND_HALF_EVEN).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}