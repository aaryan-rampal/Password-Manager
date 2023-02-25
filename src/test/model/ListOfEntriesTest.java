package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfEntriesTest {
    private ListOfEntries testList;
    private ArrayList<Entry> entryArrayList;
    private Entry e;

    @BeforeEach
    void runBefore() {
        testList = new ListOfEntries();
        entryArrayList = new ArrayList<>();
        e = new Entry("Bing", "a@outlook.com", new Password("helloworld123"),
                "www.bing.ca", "none");

        entryArrayList.add(e);
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(testList.getSizeOfEntries(), 0);
    }

    @Test
    void testNotEmptyConstructor() {
        testList = new ListOfEntries(entryArrayList);

        ArrayList<Entry> actualList = testList.getEntries();
        assertEquals(1, actualList.size());
        assertEquals(e, actualList.get(0));
    }
}
