package model;

import java.util.ArrayList;

public class ListOfEntries {

    ArrayList<Entry> entries;

    public ListOfEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public Entry getEntryAtIndex(int i) {
        return entries.get(i);
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entry> entries) {
        this.entries = entries;
    }
}
