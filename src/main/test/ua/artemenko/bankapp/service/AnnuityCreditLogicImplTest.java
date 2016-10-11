package ua.artemenko.bankapp.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.artemenko.bankapp.model.Credit;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class AnnuityCreditLogicImplTest {

    Credit credit;
    AnnuityCreditLogicImpl creditLogic;

    @Before
    public void setUp() throws Exception {
        creditLogic = new AnnuityCreditLogicImpl();
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
        double percent = 10;
        credit.setInterestRate(percent);
        percent = percent / 100;
        double min = percent * Math.pow(1 + percent,credit.getDurationOfContract()) /
                (Math.pow(1 + percent,credit.getDurationOfContract()) - 1);
        BigDecimal helpValue = new BigDecimal(String.valueOf(min)).multiply(credit.getAmountOfCredit()).
                setScale(2, BigDecimal.ROUND_DOWN);
        BigDecimal minPayment = creditLogic.calculateMinPayment(credit);
        Assert.assertTrue(minPayment.compareTo(helpValue) == 0);
    }

    @Test
    public void returnRatePaymentOfCredit() throws Exception {
        credit.setInterestRate(0);
        BigDecimal minPayment = creditLogic.returnRatePaymentOfCredit(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 0);
        credit.setInterestRate(10);
        minPayment = creditLogic.returnRatePaymentOfCredit(credit);
        Assert.assertTrue(minPayment.compareTo(new BigDecimal("0")) == 1);
    }

}