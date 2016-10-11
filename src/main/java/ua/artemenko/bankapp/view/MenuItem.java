package ua.artemenko.bankapp.view;


public class MenuItem{
    private int numItem;
    private String key;
    private String item;

    public MenuItem (){
    }

    public MenuItem(int numItem, String key) {
        this.numItem = numItem;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuItem menuItem = (MenuItem) o;

        if (numItem != menuItem.numItem) return false;
        if (key != null ? !key.equals(menuItem.key) : menuItem.key != null) return false;
        return item != null ? item.equals(menuItem.item) : menuItem.item == null;

    }

    @Override
    public int hashCode() {
        int result = numItem;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "" + numItem + " " + item + "\n";
    }

}
