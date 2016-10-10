package ua.artemenko.bankapp.view;


public class Head{

    private String key;
    private String head;

    public Head(){
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Head(String head) {
        this.head = head;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Head head1 = (Head) o;

        return head != null ? head.equals(head1.head) : head1.head == null;

    }

    @Override
    public int hashCode() {
        return head != null ? head.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "     " + head + "\n";
    }
}
