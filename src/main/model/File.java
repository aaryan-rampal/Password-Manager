package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

// Represents a file which will contain a list of entries
public class File {

    private List<Entry> entries;

    /**
     * @EFFECTS: creates a new file with no entries
     */
    public File() {
        entries = new ArrayList<>();
    }

    /**
     * @REQUIRES: entries is not null
     * @EFFECTS: creates a new file with existing entries
     */
    public File(List<Entry> entries) {
        this.entries = entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: adds the entry parameter to the end of the entries list
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
        EventLog.getInstance().logEvent(new Event("Added entry #" + entries.size()
                + " with name " + entry.getName() + "."));
    }

    public void removeEntry(int index) {
        String nameOfEntry = entries.get(index).getName();
        entries.remove(index);
        EventLog.getInstance().logEvent(new Event("Removed entry #" + ++index
                + " with name " + nameOfEntry + "."));
    }

    /**
     * @EFFECTS: returns the size of the list
     */
    public int getSizeOfEntries() {
        return entries.size();
    }

    /**
     * @REQUIRES: entries has at least one element; i < entries.getSizeOfEntries()
     * @EFFECTS: returns the entry at the specified index of the entries list
     */
    public Entry getEntryAtIndex(int i) {
        return entries.get(i);
    }

    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * @EFFECTS: returns contents of entries arraylist to JSONObject
     */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(entries);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
