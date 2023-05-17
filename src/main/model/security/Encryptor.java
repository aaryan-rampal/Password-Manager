package model.security;

import java.security.SecureRandom;

public class Encryptor {
    private static Encryptor encryptor;

    private Encryptor() {
    }

    public static Encryptor getInstance() {
        if (encryptor == null) {
            encryptor = new Encryptor();
        }

        return encryptor;
    }

    public String encrypt(String plaintext, Keyset keySet, byte[] salt) {
        byte[] cipherBytes = keySet.encrypt(plaintext, salt);
        return ByteConvertor.bytesToString(cipherBytes);
    }


    /**
     * @EFFECTS: creates an array of 16 random bytes which will be used as the salt
     */
    public byte[] createSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return saltBytes;
    }

}
