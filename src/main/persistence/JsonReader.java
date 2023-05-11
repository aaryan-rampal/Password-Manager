package persistence;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.entries.Entry;
import model.entries.Password;
import model.event.Event;
import model.event.EventLog;
import model.entries.File;
import model.security.Decryptor;
import model.security.Keyset;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
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
        ObjectMapper mapper = new ObjectMapper();

        List<Entry> encryptedLoadedEntries = mapper.readValue(jsonData, new TypeReference<List<Entry>>() { });
        List<Entry> loadedEntries = new ArrayList<>();
        decryptEntries(encryptedLoadedEntries, loadedEntries);
        EventLog.getInstance().logEvent(new Event("Loaded entries from workroom.json."));


        return parseFile(loadedEntries);
    }

    private void decryptEntries(List<Entry> encryptedLoadedEntries, List<Entry> loadedEntries) {
        for (Entry e : encryptedLoadedEntries) {
            loadedEntries.add(decryptEntry(e));
        }
    }

    private Entry decryptEntry(Entry e) {
        Decryptor decryptor = Decryptor.getInstance();
        Keyset keyset = null;
        try {
            keyset = new Keyset("password", "SHA-256");
            String name = decryptor.decrypt(e.getName(), e.getSaltBytes(), keyset);
            String username = decryptor.decrypt(e.getUsername(), e.getSaltBytes(), keyset);
            String password = decryptor.decrypt(e.getPasswordText(), e.getSaltBytes(), keyset);
            String url = decryptor.decrypt(e.getUrl(), e.getSaltBytes(), keyset);
            String notes = decryptor.decrypt(e.getNotes(), e.getSaltBytes(), keyset);
            return new Entry(name, username, new Password(password), url, notes);
        } catch (GeneralSecurityException ex) {
            throw new RuntimeException(ex);
        }
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

}
