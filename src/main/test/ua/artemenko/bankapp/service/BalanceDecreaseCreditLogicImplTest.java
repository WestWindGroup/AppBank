package ua.artemenko.bankapp.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.*;

public class BalanceDecreaseCreditLogicImplTest {
    Credit credit;
    BalanceDecreaseCreditLogicImpl creditLogic;

    @Before
    public void setUp() throws Exception {
        creditLogic = new BalanceDecreaseCreditLogicImpl();
        credit = new Credit();
        credit.setAmountOfCredit("1000");
        credit.setSumOfIndebtedness("1000");
        credit.setDurationOfContract(12);

    }

    @Test
    public void calculateListPayments() throws Exception {
        credit.setInterestRate(10);
        List<String> listPayments = creditLogic.calculateListPayments(credit);
        Assert.assertTrue(listPayments.size() == 13);
    }

    @Test
    public void calculateMinPayment() throws Exception {
        GregorianCalendar calendar = new GregorianCalendar();
        double percent = 10;
        credit.setInterestRate(percent);
        String strHelp = String.valueOf(CreditLogic.daysInMonth(calendar) * credit.getInterestRate()/100);
        BigDecimal rateHelp = new BigDecimal(strHelp);

        BigDecimal duration = new BigDecimal(String.valueOf(credit.getDurationOfContract()));
        BigDecimal body = credit.getAmountOfCredit().divide(duration,BigDecimal.ROUND_HALF_EVEN);

        BigDecimal rate = credit.getSumOfIndebtedness().multiply(rateHelp);
        rate = rate.divide(new BigDecimal(CreditLogic.daysInYear(calendar)),BigDecimal.ROUND_HALF_EVEN);
        BigDecimal helpValue = body.add(rate);
        BigDecimal minPayment = creditLogic.calculateMinPayment(credit);
        System.out.println(helpValue + "  " + minPayment);
        Assert.assertTrue(minPayment.compareTo(helpValue) == 0);
    }

    @Test
    public void returnRatePaymentOfCredit() throws Exception {
        credit.setInterestRate(0);
        BigDecimal minPayment = creditLogic.returnRatePaymentOfCredit(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 0);
        credit.setInterestRate(1);
        minPayment = creditLogic.returnRatePaymentOfCredit(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 1);
    }

}