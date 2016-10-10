package ua.artemenko.bankapp.view;

import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

public class WorkScreen {

    private Head head;
    private List<Request> requestList;
    private List<MenuItem> menuItemList;
    private MessageSource messageSource;
    private Locale locale;

    public WorkScreen() {
    }

    public WorkScreen(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public WorkScreen(Head head, List<MenuItem> menuItem, MessageSource messageSource) {
        this(messageSource);
        this.head = head;
        this.menuItemList = menuItem;
        this.messageSource = messageSource;
    }

    public WorkScreen(Head head, List<Request> requestList, List<MenuItem> menuItem, MessageSource messageSource) {
        this(head, menuItem, messageSource);
        this.requestList = requestList;
    }


    public void initWorkScreen() {
        if (head != null) {
            head.setHead(messageSource.getMessage(head.getKey(), null, locale));
        }
        if (requestList != null) {
            for (Request requestHelp : requestList) {
                requestHelp.setRequest(messageSource.getMessage(requestHelp.getKey(), null, locale));
            }
        }
        if (menuItemList != null) {
            for (MenuItem itemHelp : menuItemList) {
                itemHelp.setItem(messageSource.getMessage(itemHelp.getKey(), null, locale));
            }
        }
    }

    public void showRequest(String request) {
        System.out.println(request);
    }

    public void showHead() {
        System.out.println();
        System.out.println(head);
    }

    public void showScreen() {
        for (MenuItem item : menuItemList) {
            System.out.println(item);
        }
    }

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkScreen that = (WorkScreen) o;

        if (head != null ? !head.equals(that.head) : that.head != null) return false;
        if (requestList != null ? !requestList.equals(that.requestList) : that.requestList != null) return false;
        if (menuItemList != null ? !menuItemList.equals(that.menuItemList) : that.menuItemList != null) return false;
        if (messageSource != null ? !messageSource.equals(that.messageSource) : that.messageSource != null)
            return false;
        return locale != null ? locale.equals(that.locale) : that.locale == null;

    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + (requestList != null ? requestList.hashCode() : 0);
        result = 31 * result + (menuItemList != null ? menuItemList.hashCode() : 0);
        result = 31 * result + (messageSource != null ? messageSource.hashCode() : 0);
        result = 31 * result + (locale != null ? locale.hashCode() : 0);
        return result;
    }
}