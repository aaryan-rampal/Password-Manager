package persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads file object from stored JSON data
public class JsonReader {
    private String source;

    /**
     * @EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
    }

    /**
     * @EFFECTS: reads file object from JSON data and returns it; throws IOException if an
     * error occurs reading data from file
     */
    public File read() throws IOException {
        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
        ObjectMapper mapper = new ObjectMapper();

        List<Entry> loadedEntries = mapper.readValue(jsonData, new TypeReference<List<Entry>>() { });
        EventLog.getInstance().logEvent(new Event("Loaded entries from workroom.json."));

        return parseFile(loadedEntries);
    }

    /**
     * @EFFECTS: reads source file as string and returns it
     */
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    /**
     * @EFFECTS: parses file from JSON object and returns it
     */
    private File parseFile(List<Entry> loadedEntries) {
        File f = new File();
        f.setEntries(loadedEntries);
        return f;
    }

    /**
     * @MODIFIES: f
     * @EFFECTS: parses entries from JSON object and adds them to file
     */
    private void addEntries(File f, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(f, nextEntry);
        }
    }

    /**
     * @MODIFIES: f
     * @EFFECTS: parses a single entry from JSON object and adds it to file
     */
    private void addEntry(File f, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String username = jsonObject.getString("username");
        Password password = new Password(jsonObject.getString("password"));
        String url = jsonObject.getString("url");
        String notes = jsonObject.getString("notes");

        Entry entry = new Entry(name, username, password, url, notes);
        f.addEntry(entry);
    }

}
