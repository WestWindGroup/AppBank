package ua.artemenko.bankapp.model;


import ua.artemenko.bankapp.service.AnnuityCreditLogicImpl;
import ua.artemenko.bankapp.service.BalanceDecreaseCreditLogicImpl;
import ua.artemenko.bankapp.service.CreditLogic;

import java.math.BigDecimal;

public class Credit {

    private long idContract;

    private BigDecimal amountOfCredit;

    private BigDecimal sumOfIndebtedness;

    private int durationOfContract;

    private int typePayment;

    private double interestRate;

    private CreditLogic creditLogic;

    public Credit() {
    }

    public Credit(BigDecimal amountOfCredit, int maturityDate, int typePayment, double interestRate) {
        this.amountOfCredit = amountOfCredit;
        this.sumOfIndebtedness = amountOfCredit;
        this.durationOfContract = maturityDate;
        this.typePayment = typePayment;
        this.interestRate = interestRate;
    }


    public long getIdContract() {
        return idContract;
    }

    public void setIdContract(long idContract) {
        this.idContract = idContract;
    }

    public BigDecimal getAmountOfCredit() {
        return amountOfCredit;
    }

    public void setAmountOfCredit(BigDecimal amountOfCredit) {
        this.amountOfCredit = amountOfCredit;
    }

    public void setAmountOfCredit(String amountOfCredit) {
        this.amountOfCredit = new BigDecimal(amountOfCredit);
    }

    public BigDecimal getSumOfIndebtedness() {
        return sumOfIndebtedness;
    }

    public void setSumOfIndebtedness(BigDecimal sumOfIndebtedness) {
        this.sumOfIndebtedness = sumOfIndebtedness;
    }

    public void setSumOfIndebtedness(String sumOfIndebtedness) {
        this.sumOfIndebtedness = new BigDecimal(sumOfIndebtedness);
    }

    public int getDurationOfContract() {
        return durationOfContract;
    }

    public void setDurationOfContract(int durationOfContract) {
        this.durationOfContract = durationOfContract;
    }

    public int getTypePayment() {
        return typePayment;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setTypePayment(int typePayment) {
        this.typePayment = typePayment;
        if (typePayment == 1) {
            creditLogic = new AnnuityCreditLogicImpl();
        } else if (typePayment == 2) {
            creditLogic = new BalanceDecreaseCreditLogicImpl();
        }
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public CreditLogic getCreditLogic() {
        return creditLogic;
    }

    public void setCreditLogic(CreditLogic creditLogic) {
        this.creditLogic = creditLogic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credit credit = (Credit) o;

        if (idContract != credit.idContract) return false;
        if (durationOfContract != credit.durationOfContract) return false;
        if (typePayment != credit.typePayment) return false;
        if (Double.compare(credit.interestRate, interestRate) != 0) return false;
        if (amountOfCredit != null ? !amountOfCredit.equals(credit.amountOfCredit) : credit.amountOfCredit != null)
            return false;
        if (sumOfIndebtedness != null ? !sumOfIndebtedness.equals(credit.sumOfIndebtedness) : credit.sumOfIndebtedness != null)
            return false;
        return creditLogic != null ? creditLogic.equals(credit.creditLogic) : credit.creditLogic == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (idContract ^ (idContract >>> 32));
        result = 31 * result + (amountOfCredit != null ? amountOfCredit.hashCode() : 0);
        result = 31 * result + (sumOfIndebtedness != null ? sumOfIndebtedness.hashCode() : 0);
        result = 31 * result + durationOfContract;
        result = 31 * result + typePayment;
        temp = Double.doubleToLongBits(interestRate);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (creditLogic != null ? creditLogic.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "idContract=" + idContract +
                ", amountOfCredit=" + amountOfCredit +
                ", sumOfIndebtedness=" + sumOfIndebtedness +
                ", durationOfContract=" + durationOfContract +
                ", typePayment=" + typePayment +
                ", interestRate=" + interestRate +
                ", creditLogic=" + creditLogic;
    }
}