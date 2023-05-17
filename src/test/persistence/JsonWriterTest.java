package persistence;

import model.entries.Entry;
import model.entries.File;
import model.entries.Password;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
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
            writer.write(file, "password");
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFile.json");
            file = reader.read("password", "STORE");
            assertEquals(0, file.getSizeOfEntries());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GeneralSecurityException e) {
            fail("GeneralSecurityException not expected");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        String password = "passwordGeneralWorkroom";
        try {
            File file = new File();
            file.addEntry(new Entry("Reddit", "atom9", new Password("password101"), "www.reddit.com", "old"));
            file.addEntry(new Entry("Twitch", "piedipier", new Password("asddjakdsl"), "www.twitch.tv", "young"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFile.json");
            writer.open();
            writer.write(file, password);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFile.json");
            file = reader.read(password, "STORE");
            List<Entry> entries = file.getEntries();
            assertEquals(2, entries.size());
            checkEntry("Reddit", "atom9", new Password("password101"), "www.reddit.com",
                    "old", entries.get(0));
            checkEntry("Twitch", "piedipier", new Password("asddjakdsl"), "www.twitch.tv",
                    "young", entries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GeneralSecurityException e) {
            fail(e.getClass() + " not expected");
        }
    }
}