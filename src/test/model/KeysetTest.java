package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class KeysetTest {

    Keyset k;
    byte[] saltBytes = createSalt();
    boolean setupCompleted = false;
    ByteConvertor bc;

    @BeforeEach
    // will never throw GeneralSecurityException since "SHA-256" is a valid algorithm
    void setup() throws GeneralSecurityException {
        if (!setupCompleted) {
            k = new Keyset("test-password", "SHA-256");
            bc = new ByteConvertor();
            setupCompleted = true;
        }
    }

    @Test
    void testEncryptAndDecrypt() {
        try {
            String plainText = "test-string";
            byte[] encryptedBytes = k.encrypt(plainText, saltBytes);
            assertEquals(encryptedBytes.length, 39);

            byte[] ab = k.decrypt(encryptedBytes, saltBytes);
            String plainText2 = new String(ab, StandardCharsets.UTF_8);
            assertEquals(plainText2, "test-string");
        } catch (GeneralSecurityException e) {
            fail("Shouldn't have thrown exception.");
        }
    }

    @Test
    void testExceptionForEncrypt() {
        String plainText = null;
        byte[] encryptedBytes = k.encrypt(plainText, Arrays.copyOfRange(saltBytes, 0, 1));
        assertNull(encryptedBytes);
    }


    private byte[] createSalt() {
        int[] saltInt = new int[] {106, 53, -116, 19, -69, 59, 44, -4, 103, -55, -71, 91, -58, 47, -78, 32};
        byte[] salt = new byte[saltInt.length];
        for (int i = 0; i < saltInt.length; i++) {
            salt[i] = (byte) saltInt[i];
        }
        return salt;
    }

//    @Test
//    void testExceptionInConstructor() {
//        try {
//            Keyset failed = new Keyset("test-password", "not-valid");
//            fail("Should not have constructed object");
//        } catch (GeneralSecurityException e) {
//            // passes
//        }
//    }
}
