package ua.artemenko.bankapp.controller;

import ua.artemenko.bankapp.service.InputScreen;
import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.WorkScreen;

import java.util.InputMismatchException;
import java.util.Locale;

public class LanguageScreenController implements ScreenController,ObservedLanguageController {

    private Messages messages;
    private InputScreen inputScreen;
    private Observer observer;
    private WorkScreen workScreen;
    private String nameScreenForExit;
    private Locale locale;

    public LanguageScreenController() {
    }

    @Override
    public boolean eventHandler(String event) {
        inputInWordScreen();
        return true;
    }

    private int inputInWordScreen() {
        boolean endWork = false;
        int inputNum = 0;
        while (!endWork) {
            try {
                inputNum = inputScreen.inputInt();
                checkInputLanguage(inputNum);
                endWork = true;
            } catch (InputMismatchException e) {
                messages.printString(messages.getIncorrect_input());
                inputScreen.next();
            }
        }
        return inputNum;
    }

    private void checkInputLanguage(int num) {
        switch (num) {
            case 1:
                Locale.setDefault(new Locale("en", "EN"));
                messages.setLocale(Locale.getDefault());
                alertChangeLanguage();
                alertObserver(nameScreenForExit);
                break;
            case 2:
                Locale.setDefault(new Locale("ru", "RU"));
                messages.setLocale(Locale.getDefault());
                locale = Locale.getDefault();
                alertChangeLanguage();
                alertObserver(nameScreenForExit);
                break;
            case 3:
                Locale.setDefault(new Locale("uk", "UA"));
                messages.setLocale(Locale.getDefault());
                locale = Locale.getDefault();
                alertChangeLanguage();
                alertObserver(nameScreenForExit);
                break;
            case 4:
                alertObserver(nameScreenForExit);
                break;
            default:
                messages.printString(messages.getIncorrect_input_language());
                alertObserver(nameScreenForExit);
                break;
        }
    }

    @Override
    public void alertObserver(String name) {
        observer.handleEvent(nameScreenForExit);
    }

    @Override
    public void alertChangeLanguage() {
        observer.handChangeLanguage();
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
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

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public WorkScreen getWorkScreen() {
        return workScreen;
    }

    public void setWorkScreen(WorkScreen workScreen) {
        this.workScreen = workScreen;
    }

    public String getNameScreenForExit() {
        return nameScreenForExit;
    }

    public void setNameScreenForExit(String nameScreenForExit) {
        this.nameScreenForExit = nameScreenForExit;
    }
}
