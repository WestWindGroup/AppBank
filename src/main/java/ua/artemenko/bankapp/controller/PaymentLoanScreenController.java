package ua.artemenko.bankapp.controller;

import org.springframework.dao.DataAccessException;
import ua.artemenko.bankapp.model.Credit;
import ua.artemenko.bankapp.service.CreditServiceImp;
import ua.artemenko.bankapp.service.InputScreen;
import ua.artemenko.bankapp.view.MenuItem;
import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.WorkScreen;

import java.math.BigDecimal;
import java.util.InputMismatchException;

public class PaymentLoanScreenController implements ScreenController {
    private Observer observer;
    private Messages messages;
    private InputScreen inputScreen;
    private CreditServiceImp serviceImp;
    private WorkScreen workScreen;
    private Credit credit;
    private String nameScreenForExit;

    @Override
    public void eventHandler(String event) {
        if(event.equals(messages.getInput_id())){
            serviceInputId();
        }else if(event.equals(messages.getInter_sum_payment())){
            serviceInputSumPayment();
        } else if (event.equals(messages.getPayment_loan())) {
            paymentLoan();
        }
    }

    private void alertExit(){
        alertObserver(nameScreenForExit);
    }

    private void paymentLoan() {
        int sizeList = workScreen.getMenuItemList().size();
        int num = inputInWordScreen(sizeList);
        String nameScreen = nameItemInListWorkScreen(num);
        if (nameScreen.equals(messages.getPay())) {
            serviceImp.updateCredit(credit);
            alertExit();
        } else if (nameScreen.equals(messages.getExit()))
            alertExit();
    }

    private String nameItemInListWorkScreen(int num) {
        String result = null;
        for (MenuItem item : workScreen.getMenuItemList()) {
            if (item.getNumItem() == num) {
                result = item.getKey();
            }
        }
        return result;
    }

    private void serviceInputSumPayment() {
        BigDecimal minPayment = credit.getCreditLogic().calculateMinPayment(credit);
        BigDecimal payment = inputInRequest(minPayment,credit.getSumOfIndebtedness());
        BigDecimal rateInPayment = credit.getCreditLogic().returnRatePaymentOfCredit(credit);
        BigDecimal bodyPayment = payment.subtract(rateInPayment);
        credit.setSumOfIndebtedness(credit.getSumOfIndebtedness().subtract(bodyPayment));
    }

    private void serviceInputId() {
        boolean work = true;
        while (work){
            try {
                int id = inputInt();
                credit = serviceImp.getCreditById(id);
                BigDecimal minPayment = credit.getCreditLogic().calculateMinPayment(credit);
                messages.printString(messages.getMin_payment());
                System.out.println(minPayment);
                work = false;
            } catch (DataAccessException e) {
                messages.printString(messages.getIncorrect_input());
            }
        }
    }

    private BigDecimal inputInRequest(BigDecimal min,BigDecimal max) {
        boolean endWork = false;
        BigDecimal inputNum = new BigDecimal(0);
        while (!endWork) {
            try {
                inputNum = inputScreen.inputBigDecimal();
                if (checkInput(inputNum,min, max)) {
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private int inputInt() {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                inputNum = inputScreen.inputInt();
                if (checkInput(inputNum,Integer.MAX_VALUE)) {
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private boolean checkInput(int inputNum,int size) {
        if ((inputNum > 0) && (inputNum <= size)) {
            return true;
        } else {
            messages.printString(messages.getIncorrect_input());
            return false;
        }
    }

    private boolean checkInput(BigDecimal inputNum,BigDecimal min, BigDecimal max) {
        if ((inputNum.compareTo(min) >= 0) && (inputNum.compareTo(max) <= 0)){
            return true;
        } else {
            messages.printString(messages.getIncorrect_input());
            return false;
        }
    }

    private int inputInWordScreen(int size) {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                int h = inputScreen.inputInt();
                if (checkInput(h, size)) {
                    inputNum = h;
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    @Override
    public void alertObserver(String name) {
        observer.handleEvent(name);
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }

    public InputScreen getInputScreen() {
        return inputScreen;
    }

    public void setInputScreen(InputScreen inputScreen) {
        this.inputScreen = inputScreen;
    }

    public CreditServiceImp getServiceImp() {
        return serviceImp;
    }

    public void setServiceImp(CreditServiceImp serviceImp) {
        this.serviceImp = serviceImp;
    }

    public WorkScreen getWorkScreen() {
        return workScreen;
    }

    public void setWorkScreen(WorkScreen workScreen) {
        this.workScreen = workScreen;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public String getNameScreenForExit() {
        return nameScreenForExit;
    }

    public void setNameScreenForExit(String nameScreenForExit) {
        this.nameScreenForExit = nameScreenForExit;
    }
}
