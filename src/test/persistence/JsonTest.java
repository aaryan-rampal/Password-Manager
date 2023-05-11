package persistence;

import model.entries.Entry;
import model.entries.Password;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkEntry(String name, String username, Password password, String url, String notes, Entry entry) {
        assertEquals(name, entry.getName());
        assertEquals(username, entry.getUsername());
        assertEquals(password.getPassword(), entry.getPassword().getPassword());
        assertEquals(url, entry.getUrl());
        assertEquals(notes, entry.getNotes());
    }

}
