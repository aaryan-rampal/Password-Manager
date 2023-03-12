package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    private File testList;
    private ArrayList<Entry> entryArrayList;
    private Entry e1;
    private Entry e2;

    @BeforeEach
    void runBefore() {
        testList = new File("testing");
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
        assertEquals(testList.getSizeOfEntries(), 0);
    }

    @Test
    void testNotEmptyConstructor() {
        testList = new File("test", entryArrayList);

        ArrayList<Entry> actualList = testList.getEntries();
        assertEquals(2, actualList.size());
        assertEquals(e1, actualList.get(0));
        assertEquals(e2, actualList.get(1));
    }

    @Test
    void testGetEntryAtIndexZero() {
        testList = new File("test", entryArrayList);

        assertEquals(e1, testList.getEntryAtIndex(0));
    }

    @Test
    void testGetEntryAtIndexLast() {
        testList = new File("test", entryArrayList);

        assertEquals(e2, testList.getEntryAtIndex(1));
    }

    @Test
    void testAddEntry() {
        testList.addEntry(e1);

        assertEquals(testList.getSizeOfEntries(), 1);
        assertEquals(testList.getEntryAtIndex(0), e1);
    }
}
