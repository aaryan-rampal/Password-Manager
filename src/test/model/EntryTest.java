package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
    private Entry testEntry;
    Password p;

    @BeforeEach
    void runBefore() {
        p = new Password("password");
        testEntry = new Entry("Google", "a@gmail.com", p,
                "www.google.com", "made for school");
    }

    @Test
    void testConstructor() {
        assertEquals("Google", testEntry.getName());
        assertEquals("a@gmail.com", testEntry.getUsername());
        assertEquals(p, testEntry.getPassword());
        assertEquals("www.google.com", testEntry.getUrl());
        assertEquals("made for school", testEntry.getNotes());
    }

    @Test
    void testSetAlgorithm() {
        testEntry.setAlgorithm("invalid");
        assertEquals(testEntry.getAlgorithm(), "invalid");
    }

    @Test
    void testExceptionInToJson() {
        testEntry.setAlgorithm("invalid");
        assertNull(testEntry.toJson());
    }

    @Test
    void testToJson() {
        testEntry.setMasterPassword("password");
        JSONObject testJson = testEntry.toJson();
        Set<String> jsonKeys = testJson.keySet();
        assertTrue(jsonKeys.contains("salt"));
        assertTrue(jsonKeys.contains("name"));
        assertTrue(jsonKeys.contains("username"));
        assertTrue(jsonKeys.contains("password"));
        assertTrue(jsonKeys.contains("url"));
        assertTrue(jsonKeys.contains("notes"));
    }

}
