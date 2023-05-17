package persistence;

import model.entries.Entry;
import model.entries.File;
import model.event.Event;
import model.event.EventLog;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of a file object to file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    /**
     * @EFFECTS: constructs writer to write to destination file
     */
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: writes JSON representation of file object to file
     */
    public void write(File f, String masterPassword) {
        Entry.instantiateKeySet(masterPassword);
        String json = f.toJson();
        saveToFile(json);
        EventLog.getInstance()
                .logEvent(new Event("Saved entries to workroom.json."));
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: writes string to file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: closes writer
     */
    public void close() {
        writer.close();
    }
}
