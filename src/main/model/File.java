package model;

import java.util.ArrayList;

public class File {

    ArrayList<Entry> entries;

    public File() {
        entries = new ArrayList<>();
    }

    public File(ArrayList<Entry> entries) {
        this.entries = entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public int getSizeOfEntries() {
        return entries.size();
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
