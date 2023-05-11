package model.entries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.event.EventLog;

import java.util.ArrayList;
import java.util.List;

// Represents a file which will contain a list of entries
public class File {

    private List<Entry> entries;
    private EventLog eventLog = EventLog.getInstance();

    /**
     * @EFFECTS: creates a new file with no entries
     */
    public File() {
        entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * @REQUIRES: entries is not null
     * @EFFECTS: creates a new file with existing entries
     */
    public File(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * @REQUIRES: entries is not null
     * @MODIFIES: entries
     * @EFFECTS: setter for entries
     */
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: adds the entry parameter to the end of the entries list
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
        eventLog.addEntry(entries.size(), entry.getName());
    }

    /**
     * @REQUIRES: i exists in entries and is not null
     * @MODIFIES: entries
     * @EFFECTS: removes entry with index i
     */
    public void removeEntry(int i) {
        String nameOfEntry = entries.get(i).getName();
        entries.remove(i);
        eventLog.removeEntry(++i, nameOfEntry);
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

    /**
     * @EFFECTS: returns contents of entries arraylist into parse-able JSON data
     */
    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
//            return mapper.writeValueAsString(entries);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entries);
        } catch (JsonProcessingException e) {
            System.out.println("Error in saving entries. Entries were not saved.");
        }
        return null;
    }

    public String viewEntry(int index) throws IndexOutOfBoundsException {
        Entry target = entries.get(index);
        StringBuilder sb = new StringBuilder();
        sb.append(target.toString(index));
        sb.append(target.detailedView().toString());
        return sb.toString();
    }
}
