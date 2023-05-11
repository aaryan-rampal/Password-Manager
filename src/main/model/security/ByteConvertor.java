package model.security;

import com.google.crypto.tink.subtle.Base64;

// Represents a standardized converter between byte arrays and strings
public class ByteConvertor {

    public ByteConvertor() {
    }

    /**
     * @REQUIRES: bytes is not null
     * @EFFECTS: converts a byte array into a String object
     */
    public static String bytesToString(byte[] bytes) {
        return Base64.encode(bytes);
    }

    /**
     * @REQUIRES: s is not null
     * @EFFECTS: converts a String object into a byte array
     */
    public static byte[] stringToBytes(String s) {
        return Base64.decode(s);
    }


}
