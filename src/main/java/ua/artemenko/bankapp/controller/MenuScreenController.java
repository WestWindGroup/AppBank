package ua.artemenko.bankapp.controller;

import ua.artemenko.bankapp.model.Credit;
import ua.artemenko.bankapp.service.InputScreen;
import ua.artemenko.bankapp.view.MenuItem;
import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.WorkScreen;

import java.util.InputMismatchException;
import java.util.List;

public class MenuScreenController implements ScreenController {

    private Observer observer;
    private Messages messages;
    private InputScreen inputScreen;
    private WorkScreen workScreen;

    public MenuScreenController() {
    }

    public MenuScreenController(Messages messages, InputScreen inputScreen, WorkScreen workScreen) {
        this.messages = messages;
        this.inputScreen = inputScreen;
        this.workScreen = workScreen;
    }

    @Override
    public void eventHandler(String event) {
        int size = workScreen.getMenuItemList().size();
        int num = inputNumMenu(size);
        String nameScreen = nameItemInListWorkScreen(num);
        alertObserver(nameScreen);
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

    private int inputNumMenu(int size) {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                inputNum = inputScreen.inputInt();
                boolean isTrue = checkInput(inputNum, size);
                if (isTrue) {
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


    private void showListCredit(List<Credit> list) {
        String headlines = String.format("|  %15s  |  %20s  |  %20s  |  %15s  |  %15s  |  %15s  |",
                "id contract", "amount of credit", "sum of indebtedness",
                "duration of contract", "type payment", "interestRate");
        System.out.println(headlines);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }




    @Override
    public void alertObserver(String nameScreen) {
        observer.handleEvent(nameScreen);
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

    public WorkScreen getWorkScreen() {
        return workScreen;
    }

    public void setWorkScreen(WorkScreen workScreen) {
        this.workScreen = workScreen;
    }


}
