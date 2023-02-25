package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
