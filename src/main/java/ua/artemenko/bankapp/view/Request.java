package ua.artemenko.bankapp.view;


public class Request {

    private String key;
    private String request;
    private int maxValue;

    public Request() {
    }

    public Request(String key) {
        this.key = key;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getMaxValue() {
        return maxValue;
    }


    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request1 = (Request) o;

        if (maxValue != request1.maxValue) return false;
        if (key != null ? !key.equals(request1.key) : request1.key != null) return false;
        return request != null ? request.equals(request1.request) : request1.request == null;

    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (request != null ? request.hashCode() : 0);
        result = 31 * result + maxValue;
        return result;
    }

    @Override
    public String toString() {
        return request;
    }

}
