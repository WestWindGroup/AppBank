package ua.artemenko.bankapp.controller;

import ua.artemenko.bankapp.view.Messages;
import ua.artemenko.bankapp.view.Request;
import ua.artemenko.bankapp.view.WorkScreen;

import java.util.*;

public class ViewController implements ObservedView {

    private Observer observer;
    private WorkScreen activeScreen;
    private Map<String, WorkScreen> workScreenMap;
    private Locale locale;
    private Messages messages;
    private boolean showScreen;

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
        if (showScreen) {
            activeScreen.showScreen();
            alertObserver(activeScreen.getHead().getKey(),
                    activeScreen.getHead().getKey());
        }
    }


    private void checkRequest() {
        if (activeScreen.getRequestList() != null) {
            startRequestList();
        }
    }

    private void startRequestList() {
        for (Request request : activeScreen.getRequestList()) {
            if (showScreen) {
                activeScreen.showRequest(request.getRequest());
                showScreen = alertObserver(activeScreen.getHead().getKey(), request.getKey());
            } else {
                break;
            }
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
    public boolean alertObserver(String controller, String event) {
        return observer.handleEvent(controller, event);
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

    public boolean isShowScreen() {
        return showScreen;
    }

    public void setShowScreen(boolean showScreen) {
        this.showScreen = showScreen;
    }
}

