package ua.artemenko.bankapp.controller;


public interface Observer {
    boolean handleEvent(String name,String event);
    void handleEvent(String name);
    void handChangeLanguage();
}
