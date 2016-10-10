package ua.artemenko.bankapp.controller;


import ua.artemenko.bankapp.view.Messages;

public class Controller implements Observer {

    private LanguageScreenController languageScreenController;
    private MenuScreenController menuScreenController;
    private NewCreditScreenController newCreditScreenController;
    private PaymentLoanScreenController paymentLoanScreenController;
    private ViewController viewController;
    private Messages messages;

    public Controller() {
    }

    public void start() {
        startScreen(messages.getMenu());
    }

    @Override
    public void handleEvent(String handScreen,String event) {

        if(handScreen.equals(messages.getMenu())){
            menuScreenController.eventHandler(event);
        }else if(handScreen.equals(messages.getNew_credit())){
            newCreditScreenController.eventHandler(event);
        }else if(handScreen.equals(messages.getPayment_loan())){
            paymentLoanScreenController.eventHandler(event);
        }else if(handScreen.equals(messages.getLanguage_selection())){
            languageScreenController.eventHandler(event);
        }

    }

    public void startScreen(String nameScreen) {
        viewController.startWorkScreen(nameScreen);
    }


    @Override
    public void handleEvent(String nameScreen) {
        if(!nameScreen.equals(messages.getExit())){
            startScreen(nameScreen);
        }
    }


    @Override
    public void handChangeLanguage() {
        viewController.initStringWorkScreenMap();
    }

    public LanguageScreenController getLanguageScreenController() {
        return languageScreenController;
    }

    public void setLanguageScreenController(LanguageScreenController languageScreenController) {
        this.languageScreenController = languageScreenController;
    }

    public MenuScreenController getMenuScreenController() {
        return menuScreenController;
    }

    public void setMenuScreenController(MenuScreenController menuScreenController) {
        this.menuScreenController = menuScreenController;
    }

    public NewCreditScreenController getNewCreditScreenController() {
        return newCreditScreenController;
    }

    public void setNewCreditScreenController(NewCreditScreenController newCreditScreenController) {
        this.newCreditScreenController = newCreditScreenController;
    }

    public PaymentLoanScreenController getPaymentLoanScreenController() {
        return paymentLoanScreenController;
    }

    public void setPaymentLoanScreenController(PaymentLoanScreenController paymentLoanScreenController) {
        this.paymentLoanScreenController = paymentLoanScreenController;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public void setViewController(ViewController viewController) {
        this.viewController = viewController;
    }

    public Messages getMessages() {
        return messages;
    }

    public void setMessages(Messages messages) {
        this.messages = messages;
    }
}
