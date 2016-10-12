package ua.artemenko.bankapp.controller;

import ua.artemenko.bankapp.model.Credit;
import ua.artemenko.bankapp.model.CreditFactory;
import ua.artemenko.bankapp.service.CreditServiceImpl;
import ua.artemenko.bankapp.service.InputScreen;
import ua.artemenko.bankapp.view.MenuItem;
import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.Request;
import ua.artemenko.bankapp.view.WorkScreen;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;

public class NewCreditScreenController implements ScreenController {
    private Observer observer;
    private Messages messages;
    private InputScreen inputScreen;
    private CreditServiceImpl serviceImp;
    private WorkScreen workScreen;
    private List<Credit> creditList;
    private Credit credit;
    private CreditFactory creditCreator;
    private String nameScreenForExit;

    @Override
    public void eventHandler(String event) {
        if(event.equals(messages.getInput_sum())){
            inputSumHandler();
        }else if(event.equals(messages.getInput_number_month())){
            inputNumberMonthHandler();
        }else if(event.equals(messages.getInput_type())){
            inputTypeHandler();
        }else if(event.equals(messages.getNew_credit())){
            newCreditHandler();
        }

    }

    private void inputSumHandler() {
        credit = creditCreator.createCredit();
        int max = maxInRequest(messages.getInput_sum());
        messages.printString(messages.getTo(),0,max);
        BigDecimal sum = inputInRequest(max).
                setScale(2, BigDecimal.ROUND_HALF_EVEN);
        credit.setAmountOfCredit(sum);
        credit.setSumOfIndebtedness(sum);
    }

    private void inputNumberMonthHandler() {
        int max = maxInRequest(messages.getInput_number_month());
        messages.printString(messages.getTo(),0,max);
        int count = inputInt(max);
        credit.setDurationOfContract(count);
    }

    private void inputTypeHandler() {
        int max = maxInRequest(messages.getInput_type());
        int numType = inputInt(max);
        credit.setTypePayment(numType);
    }

    private void newCreditHandler() {
        int num = inputInWordScreen();
        String nameScreen = nameItemInListWorkScreen(num);
        if (nameScreen.equals(messages.getCreate())) {
            createCredit();
        } else if (nameScreen.equals(messages.getExit()))
            alertExit();
    }

    private void createCredit() {
        int id = serviceImp.addCredit(credit);
        messages.printString(messages.getYour_id());
        System.out.println("№" + id);
        List<String> paymentList =
                credit.getCreditLogic().calculateListPayments(credit);
        showPayments(paymentList);
        credit.setIdContract(id);
        creditList.add(credit);
        alertExit();
    }

    private void alertExit() {
        alertObserver(nameScreenForExit);
    }

    private String nameItemInListWorkScreen(int num) {
        String result = null;
        for (MenuItem item : workScreen.getMenuItemList()) {
            if (item.getNumItem() == num) {
                result = item.getKey();
                break;
            }
        }
        return result;
    }

    private int inputInWordScreen() {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                inputNum = inputScreen.inputInt();
                if (checkInput(inputNum, workScreen.getMenuItemList().size())) {
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private int maxInRequest(String name) {
        int max = 0;
        for (Request request : workScreen.getRequestList()) {
            if (request.getKey().equals(name)) {
                max = request.getMaxValue();
                break;
            }
        }
        return max;
    }

    private BigDecimal inputInRequest(int max) {
        boolean endWork = false;
        BigDecimal inputNum = new BigDecimal(0);
        while (!endWork) {
            try {
                inputNum = inputScreen.inputBigDecimal();
                if (checkInput(inputNum, max)) {
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private int inputInt(int max) {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                inputNum = inputScreen.inputInt();
                if (checkInput(inputNum, max)) {
                    endWork = true;
                }
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private boolean checkInput(int inputNum, int size) {
        if ((inputNum > 0) && (inputNum <= size)) {
            return true;
        } else {
            messages.printString(messages.getIncorrect_input());
            return false;
        }
    }

    private boolean checkInput(BigDecimal inputNum, int size) {
        BigDecimal sizeHelp = new BigDecimal(size);
        if ((inputNum.signum() == 1) && (inputNum.compareTo(sizeHelp)) == -1) {
            return true;
        } else {
            messages.printString(messages.getIncorrect_input());
            return false;
        }
    }

    private void showPayments(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
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

    public CreditServiceImpl getServiceImp() {
        return serviceImp;
    }

    public void setServiceImp(CreditServiceImpl serviceImp) {
        this.serviceImp = serviceImp;
    }

    public WorkScreen getWorkScreen() {
        return workScreen;
    }

    public void setWorkScreen(WorkScreen workScreen) {
        this.workScreen = workScreen;
    }

    public List<Credit> getCreditList() {
        return creditList;
    }

    public void setCreditList(List<Credit> creditList) {
        this.creditList = creditList;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public CreditFactory getCreditCreator() {
        return creditCreator;
    }

    public void setCreditCreator(CreditFactory creditCreator) {
        this.creditCreator = creditCreator;
    }

    public String getNameScreenForExit() {
        return nameScreenForExit;
    }

    public void setNameScreenForExit(String nameScreenForExit) {
        this.nameScreenForExit = nameScreenForExit;
    }
}
