package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a file which will contain a list of entries
public class File implements Writable {
    private ArrayList<Entry> entries;
    private String masterPassword;

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
    }

    public void removeEntry(int index) {
        entries.remove(index);
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

    public ArrayList<Entry> getEntries() {
        return entries;
    }

    /**
     * @EFFECTS: returns contents of entries arraylist to JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("entries", entriesToJson());
        return json;
    }

    /**
     * @REQUIRES: masterPassword and entries are not null
     * @MODIFIES: entries
     * @EFFECTS: uses the masterPassword to add the encrypted entries to a JSONArray which it then returns
     */
    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : entries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
