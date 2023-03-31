package persistence;

import model.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    private final static String masterPassword = "password";

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            File file = reader.read(masterPassword);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (GeneralSecurityException e) {
            fail();
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFile.json");
        try {
            File file = reader.read(masterPassword);
            assertEquals(0, file.getSizeOfEntries());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GeneralSecurityException e) {
            fail();
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFile.json");
        try {
            File file = reader.read(masterPassword);
            List<Entry> entries = file.getEntries();

            assertEquals(2, entries.size());
            checkEntry("Google", "a@gmail.com",
                    new Password("password123"), "www.google.ca",
                    "none", entries.get(0));
            checkEntry("Bing", "b@outlook.com",
                    new Password("engaging-iodine-quicksand-canola"), "www.bing.ca", "none",
                    entries.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GeneralSecurityException e) {
            fail();
        }
    }
}