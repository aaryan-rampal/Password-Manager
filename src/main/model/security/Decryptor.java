package model.security;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Decryptor {
    private static Decryptor decryptor;

    private Decryptor() {}

    public static Decryptor getInstance() {
        if (decryptor == null) {
            decryptor = new Decryptor();
        }

        return decryptor;
    }

    /**
     * @REQUIRES: field, salt, and keyset are not null
     * @EFFECTS: decrypts the field using keyset and returns the decrypted string
     */
    public String decrypt(String field, byte[] salt, Keyset keyset) throws GeneralSecurityException,
            GeneralSecurityException {
        byte[] cipherBytes = ByteConvertor.stringToBytes(field);
        byte[] decryptedBytes = keyset.decrypt(cipherBytes, salt);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
