package ua.artemenko.bankapp.view;

import org.springframework.context.MessageSource;

import java.math.BigDecimal;
import java.util.Locale;

public class Messages {
    private String exit;
    private String menu;
    private String language_selection;
    private String payment_loan;
    private String new_credit;
    private String input_English;
    private String input_Russian;
    private String input_Ukrainian;
    private String input_sum;
    private String input_number_month;
    private String input_type;
    private String input_id;
    private String min_payment;
    private String inter_sum_payment;
    private String incorrect_input_language;
    private String incorrect_input;
    private String create;
    private String credits_null;
    private String pay;
    private String your_id;
    private String to;
    private MessageSource messageSource;
    private Locale locale;


    public void printString(String ob) {
        locale = Locale.getDefault();
        System.out.println(messageSource.getMessage(ob, null, locale));
    }
    public void printString(String ob, BigDecimal one, BigDecimal two) {
        locale = Locale.getDefault();
        System.out.println(messageSource.getMessage(ob, new String[] {one.toString(), two.toString()}, locale));
    }

    public void printString(String ob, int one, int two) {
        locale = Locale.getDefault();
        System.out.println(messageSource.getMessage(ob,
                new String[] {String.valueOf(one), String.valueOf(two).toString()}, locale));
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getLanguage_selection() {
        return language_selection;
    }

    public void setLanguage_selection(String language_selection) {
        this.language_selection = language_selection;
    }

    public String getPayment_loan() {
        return payment_loan;
    }

    public void setPayment_loan(String payment_loan) {
        this.payment_loan = payment_loan;
    }

    public String getNew_credit() {
        return new_credit;
    }

    public void setNew_credit(String new_credit) {
        this.new_credit = new_credit;
    }

    public String getInput_English() {
        return input_English;
    }

    public void setInput_English(String input_English) {
        this.input_English = input_English;
    }

    public String getInput_Russian() {
        return input_Russian;
    }

    public void setInput_Russian(String input_Russian) {
        this.input_Russian = input_Russian;
    }

    public String getInput_Ukrainian() {
        return input_Ukrainian;
    }

    public void setInput_Ukrainian(String input_Ukrainian) {
        this.input_Ukrainian = input_Ukrainian;
    }

    public String getInput_sum() {
        return input_sum;
    }

    public void setInput_sum(String input_sum) {
        this.input_sum = input_sum;
    }

    public String getInput_number_month() {
        return input_number_month;
    }

    public void setInput_number_month(String input_number_month) {
        this.input_number_month = input_number_month;
    }

    public String getInput_type() {
        return input_type;
    }

    public void setInput_type(String input_type) {
        this.input_type = input_type;
    }

    public String getInput_id() {
        return input_id;
    }

    public void setInput_id(String input_id) {
        this.input_id = input_id;
    }

    public String getMin_payment() {
        return min_payment;
    }

    public void setMin_payment(String min_payment) {
        this.min_payment = min_payment;
    }

    public String getInter_sum_payment() {
        return inter_sum_payment;
    }

    public void setInter_sum_payment(String inter_sum_payment) {
        this.inter_sum_payment = inter_sum_payment;
    }

    public String getIncorrect_input_language() {
        return incorrect_input_language;
    }

    public void setIncorrect_input_language(String incorrect_input_language) {
        this.incorrect_input_language = incorrect_input_language;
    }

    public String getIncorrect_input() {
        return incorrect_input;
    }

    public void setIncorrect_input(String incorrect_input) {
        this.incorrect_input = incorrect_input;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getCredits_null() {
        return credits_null;
    }

    public void setCredits_null(String credits_null) {
        this.credits_null = credits_null;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getYour_id() {
        return your_id;
    }

    public void setYour_id(String your_id) {
        this.your_id = your_id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
