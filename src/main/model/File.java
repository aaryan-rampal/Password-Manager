package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a file which will contain a list of entries
public class File implements Writable {
    private ArrayList<Entry> entries;

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
    public File(ArrayList<Entry> entries) {
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

    public void setMasterPassword(String masterPassword) {
        this.masterPassword = masterPassword;
    }

    /**
     * @REQUIRES: entries has at least one element; i < entries.getSizeOfEntries()
     * @EFFECTS: returns the entry at the specified index of the entries list
     */
    public Entry getEntryAtIndex(int i) {
        return entries.get(i);
    }

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * @EFFECTS: returns contents of entries arraylist to JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entries", entriesToJson(masterPassword));
        return json;
    }

    /**
     * @REQUIRES: masterPassword and entries are not null
     * @MODIFIES: entries
     * @EFFECTS: uses the masterPassword to add the encrypted entries to a JSONArray which it then returns
     */
    private JSONArray entriesToJson(String masterPassword) {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : entries) {
            e.setMasterPassword(masterPassword);
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }
    }
}
