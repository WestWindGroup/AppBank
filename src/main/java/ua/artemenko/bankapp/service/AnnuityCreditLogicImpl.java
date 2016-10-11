package ua.artemenko.bankapp.service;


import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.util.*;

public class AnnuityCreditLogicImpl extends CreditLogic {

    @Override
    public List<String> calculateListPayments(Credit credit) {

        BigDecimal debt = credit.getAmountOfCredit();
        BigDecimal rate = rate(credit.getInterestRate());
        BigDecimal payment = calculateMinPayment(credit). setScale(2, BigDecimal.ROUND_HALF_EVEN);
        List<String> listPayments = new ArrayList<>();
        String paymentToString = formatStringFirst();
        listPayments.add(paymentToString);
        for (int i = 1; i <= credit.getDurationOfContract(); i++) {
            calendar.add(Calendar.MONTH, 1);
            BigDecimal ratePay = rateInPayment(rate, debt).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal bodyPay = bodyInPayment(ratePay, payment).setScale(2, BigDecimal.ROUND_HALF_EVEN);
            debt = newDebt(ratePay, debt, payment);
            paymentToString = formatString(ratePay, bodyPay, payment);

            listPayments.add(paymentToString);
        }

        calendar = new GregorianCalendar();
        return listPayments;
    }

    @Override
    public BigDecimal calculateMinPayment(Credit credit) {
        int percentMax = 100;
        BigDecimal rate = new BigDecimal(String.valueOf(credit.getInterestRate() / percentMax)).
                setScale(5, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal factor = factorСompute(rate, credit.getDurationOfContract());

        return paymentMonth(factor, credit.getSumOfIndebtedness()).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    @Override
    public BigDecimal returnRatePaymentOfCredit(Credit credit) {
        BigDecimal rate = rate(credit.getInterestRate()).multiply(credit.getSumOfIndebtedness());
        return rate.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


    private BigDecimal rate(double interestRate) {
        int percentMax = 100;
        int countMonthInYear = 12;
        return new BigDecimal(String.valueOf(interestRate / percentMax / countMonthInYear)).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal rateInPayment(BigDecimal rate, BigDecimal amountOfCredit) {
        return rate.multiply(amountOfCredit).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal bodyInPayment(BigDecimal rate, BigDecimal payment) {
        return payment.subtract(rate).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal newDebt(BigDecimal rate, BigDecimal debt, BigDecimal payment) {
        BigDecimal sbtract = payment.subtract(rate);
        return debt.subtract(sbtract).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal paymentMonth(BigDecimal factor, BigDecimal sumOfIndebtedness) {
        return factor.multiply(sumOfIndebtedness).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    private BigDecimal factorСompute(BigDecimal rate, int durationOfContract) {
        int countMonthInYear = 12;
        BigDecimal i = rate.divide(new BigDecimal(countMonthInYear), BigDecimal.ROUND_HALF_EVEN);
        BigDecimal groupPow = new BigDecimal(1).add(i).pow(durationOfContract);
        BigDecimal up = i.multiply(groupPow);
        BigDecimal down = groupPow.subtract(new BigDecimal(1));
        return up.divide(down, BigDecimal.ROUND_HALF_EVEN).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

}
