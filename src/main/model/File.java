package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a file which will contain a list of entries
public class File implements Writable {
    private String name;
    private ArrayList<Entry> entries;

    /**
     * @EFFECTS: creates a new file with no entries
     */
    public File(String name) {
        this.name = name;
        entries = new ArrayList<>();
    }

    /**
     * @REQUIRES: entries is not null
     * @EFFECTS: creates a new file with existing entries
     */
    public File(String name, ArrayList<Entry> entries) {
        this.name = name;
        this.entries = entries;
    }

    public String getName() {
        return this.name;
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: adds the entry parameter to the end of the entries list
     */
    public void addEntry(Entry entry) {
        entries.add(entry);
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

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("entries", entriesToJson());
        return json;
    }

    private JSONArray entriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Entry e : entries) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
