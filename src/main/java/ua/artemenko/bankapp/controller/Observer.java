package ua.artemenko.bankapp.controller;


public interface Observer {
    void handleEvent(String name,String event);
    void handleEvent(String name);
    void handChangeLanguage();
}
