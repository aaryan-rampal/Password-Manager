package persistence;

import model.Entry;
import model.File;
import model.Password;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            File file = new File();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            File file = new File();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFile.json");
            writer.open();
            writer.write(file);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFile.json");
            file = reader.read();
            assertEquals(0, file.getSizeOfEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            File file = new File();
            file.addEntry(new Entry("Reddit", "atom9", new Password("password101"), "www.reddit.com", "old"));
            file.addEntry(new Entry("Twitch", "piedipier", new Password("asddjakdsl"), "www.twitch.tv", "young"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFile.json");
            writer.open();
            writer.write(file);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFile.json");
            file = reader.read();
            List<Entry> entries = file.getEntries();
            assertEquals(2, entries.size());
            checkEntry("Reddit", "atom9", new Password("password101"), "www.reddit.com",
                    "old", entries.get(0));
            checkEntry("Twitch", "piedipier", new Password("asddjakdsl"), "www.twitch.tv",
                    "young", entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}