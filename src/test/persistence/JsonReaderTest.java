package persistence;

import model.entries.Entry;
import model.entries.File;
import model.entries.Password;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            File file = reader.read("password", "STORE");
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (GeneralSecurityException e) {
            fail(e.getClass().getSimpleName() + " should not have been thrown.");
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/test/testReaderEmptyFile.json");
        try {
            File file = reader.read("passwordEmptyFile", "STORE");
            assertEquals(0, file.getSizeOfEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GeneralSecurityException e) {
            fail(e.getClass().getSimpleName() + " should not have been thrown.");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/test/testReaderGeneralFile.json");
        try {
            File file = reader.read("passwordGeneralFile", "STORE");
            List<Entry> entries = file.getEntries();

            assertEquals(2, entries.size());
            checkEntry("Google", "union@gmail.com",
                    new Password("stowing-attest-flogging-trillion-subgroup-salvage"), "www.google.com",
                    "none", entries.get(0));
            checkEntry("Bing", "random@outlook.com",
                    new Password("rescuer-gulp-hunger-attention-folk"), "www.bing.ca", "none1",
                    entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GeneralSecurityException e) {
            fail(e.getClass().getSimpleName() + " should not have been thrown.");
        }
    }
}