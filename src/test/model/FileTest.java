package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    private File testFile;
    private ArrayList<Entry> entryArrayList;
    private Entry e1;
    private Entry e2;

    @BeforeEach
    void runBefore() {
        testFile = new File();
        entryArrayList = new ArrayList<>();
        e1 = new Entry("Bing", "a@outlook.com", new Password("helloworld123"),
                "www.bing.ca", "none");
        e2 = new Entry("Adobe", "a@outlook.com", new Password("goodpassword"),
                "www.adobe.ca", "none");

        entryArrayList.add(e1);
        entryArrayList.add(e2);
    }

    @Test
    void testEmptyConstructor() {
        assertEquals(testFile.getSizeOfEntries(), 0);
    }

    @Test
    void testNotEmptyConstructor() {
        testFile = new File(entryArrayList);

        ArrayList<Entry> actualList = testFile.getEntries();
        assertEquals(2, actualList.size());
        assertEquals(e1, actualList.get(0));
        assertEquals(e2, actualList.get(1));
    }

    @Test
    void testGetEntryAtIndexZero() {
        testFile = new File(entryArrayList);

        assertEquals(e1, testFile.getEntryAtIndex(0));
    }

    @Test
    void testGetEntryAtIndexLast() {
        testFile = new File(entryArrayList);

        assertEquals(e2, testFile.getEntryAtIndex(1));
    }

    @Test
    void testAddEntry() {
        testFile.addEntry(e1);

        assertEquals(testFile.getSizeOfEntries(), 1);
        assertEquals(testFile.getEntryAtIndex(0), e1);
    }
}
