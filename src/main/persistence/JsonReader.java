package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads file object from stored JSON data
public class JsonReader {
    private String source;
    private ByteConvertor bc;

    /**
     * @EFFECTS: constructs reader to read from source file
     */
    public JsonReader(String source) {
        this.source = source;
        bc = new ByteConvertor();
    }

    /**
     * @EFFECTS: reads file object from JSON data and returns it; throws IOException if an
     * error occurs reading data from file
     */
    public File read(String masterPassword) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFile(jsonObject, masterPassword);
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
    private File parseFile(JSONObject jsonObject, String masterPassword) {
        File f = new File();
        addEntries(f, jsonObject, masterPassword);
        return f;
    }

    /**
     * @MODIFIES: f
     * @EFFECTS: parses entries from JSON object and adds them to file
     */
    private void addEntries(File f, JSONObject jsonObject, String masterPassword) {
        JSONArray jsonArray = jsonObject.getJSONArray("entries");
        for (Object json : jsonArray) {
            JSONObject nextEntry = (JSONObject) json;
            addEntry(f, nextEntry, masterPassword);
        }
    }

    /**
     * @MODIFIES: f
     * @EFFECTS: parses a single entry from JSON object and adds it to file
     */
    private void addEntry(File f, JSONObject jsonObject, String masterPassword) {
        String salt = jsonObject.getString("salt");
        byte[] saltBytes = bc.stringToBytes(salt);
        Keyset keyset = new Keyset(masterPassword);

        String name = decryptField(jsonObject.getString("name"), saltBytes, keyset);
        String username = decryptField(jsonObject.getString("username"), saltBytes, keyset);
        Password password = new Password(decryptField(jsonObject.getString("password"), saltBytes, keyset));
        String url = decryptField(jsonObject.getString("url"), saltBytes, keyset);
        String notes = decryptField(jsonObject.getString("notes"), saltBytes, keyset);

        Entry entry = new Entry(name, username, password, url, notes);
        f.addEntry(entry);
    }

    private String decryptField(String field, byte[] salt, Keyset keyset) {
        byte[] cipherBytes = bc.stringToBytes(field);
        byte[] decryptedBytes = keyset.decrypt(cipherBytes, salt);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
