package ua.artemenko.bankapp.controller;

import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.Request;
import ua.artemenko.bankapp.view.WorkScreen;

import java.util.*;

public class ViewController implements ObservedView {

    private boolean endWork;
    private Observer observer;
    private WorkScreen activeScreen;
    private Map<String, WorkScreen> workScreenMap;
    private Locale locale;
    private Messages messages;

    public ViewController() {
    }

    public ViewController(Map<String, WorkScreen> workScreenMap) {
        this.workScreenMap = workScreenMap;
    }


    public void startWorkScreen(String nameScreen) {
        activeScreen = workScreenMap.get(nameScreen);
        activationScreen();
    }

    private void activationScreen() {
        activeScreen.showHead();
        checkRequest();
        activeScreen.showScreen();
        alertObserver(activeScreen.getHead().getKey(),
                        activeScreen.getHead().getKey());
    }


    private void checkRequest() {
        if (activeScreen.getRequestList() != null) {
            startRequestList();
        }
    }

    private void startRequestList() {
        for (Request request : activeScreen.getRequestList()) {
            activeScreen.showRequest(request.getRequest());
            alertObserver(activeScreen.getHead().getKey(),request.getKey());
        }
    }

    public void initStringWorkScreenMap() {
        locale = Locale.getDefault();
        for (Map.Entry<String, WorkScreen> entry : workScreenMap.entrySet()) {
            entry.getValue().setLocale(locale);
            entry.getValue().initWorkScreen();
        }
    }

    @Override
    public void alertObserver(String controller,String event) {
        observer.handleEvent(controller,event);
    }

    public boolean isEndWork() {
        return endWork;
    }

    public void setEndWork(boolean endWork) {
        this.endWork = endWork;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public WorkScreen getActiveScreen() {
        return activeScreen;
    }

    public void setActiveScreen(WorkScreen activeScreen) {
        this.activeScreen = activeScreen;
    }

    public Map<String, WorkScreen> getWorkScreenMap() {
        return workScreenMap;
    }

    public void setWorkScreenMap(Map<String, WorkScreen> workScreenMap) {
        this.workScreenMap = workScreenMap;
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
}

