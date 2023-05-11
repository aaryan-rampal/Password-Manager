package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTest {
    private File testList;
    private ArrayList<Entry> entryArrayList;
    private Entry e1;
    private Entry e2;

    @BeforeEach
    void runBefore() {
        testList = new File();
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
        testList = new File(entryArrayList);

        List<Entry> actualList = testList.getEntries();
        assertEquals(actualList, entryArrayList);
    }

    @Test
    void testGetEntryAtIndexZero() {
        testList = new File(entryArrayList);

        assertEquals(e1, testList.getEntryAtIndex(0));
    }

    @Test
    void testGetEntryAtIndexLast() {
        testList = new File(entryArrayList);

        assertEquals(e2, testList.getEntryAtIndex(1));
    }

    @Test
    void testAddEntry() {
        testList.addEntry(e1);

        assertEquals(testList.getSizeOfEntries(), 1);
        assertEquals(testList.getEntryAtIndex(0), e1);
    }

    @Test
    void testRemoveEntry() {
        testList = new File(entryArrayList);

        testList.removeEntry(0);
        assertEquals(testList.getSizeOfEntries(), 1);
        assertEquals(testList.getEntries().get(0), e2);
    }

    @Test
    void testToJson() throws JsonProcessingException {
        testList = new File(entryArrayList);
        ObjectMapper mapper = new ObjectMapper();

        String jsonData = testList.toJson();
        List<Entry> loadedFromJson = mapper.readValue(jsonData, new TypeReference<List<Entry>>() { });

        for (int i = 0; i < loadedFromJson.size(); i++) {
            assertEquals(loadedFromJson.get(i), entryArrayList.get(i));
        }
    }

}

