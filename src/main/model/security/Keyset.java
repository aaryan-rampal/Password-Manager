package model.security;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.subtle.AesGcmJce;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

// Represents a custom Keyset object to use with the Tink library that is connected to a string (essentially the master
// password of the file)
public class Keyset {

    private AesGcmJce aead;
    private ByteConvertor bc;

    /**
     * @REQUIRES: password and algorithm are not null, algorithm is a valid algorithm that MessageDigest recognizes
     * @EFFECTS: creates a keyset that is linked to the password given
     */
    public Keyset(String password, String algorithm)
            throws GeneralSecurityException {
        bc = new ByteConvertor();
        AeadConfig.register();
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] key128Bit = convertTo128Bits(messageDigest.digest());
        aead = new AesGcmJce(key128Bit);
    }

    /**
     * @REQUIRES: digest has at least 16 elements
     * @EFFECTS: concatenates digest to be 128 bits long
     */
    private byte[] convertTo128Bits(byte[] digest) {
        byte[] key128Bit = new byte[16];
        System.arraycopy(digest, 0, key128Bit, 0, 16);
        return key128Bit;
    }

    /**
     * @REQUIRES: plainText and saltBytes are not null
     * @EFFECTS: encrypts the plain text into an encrypted byte array
     */
    public byte[] encrypt(String plainText, byte[] saltBytes) {
        try {
            return aead.encrypt(plainText.getBytes(StandardCharsets.UTF_8),
                    saltBytes);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @REQUIRES: cipherBytes and saltBytes are not null
     * @EFFECTS: decrypts the encrypted byte array into a plain text string
     */
    public byte[] decrypt(byte[] cipherBytes, byte[] saltBytes)
            throws GeneralSecurityException {
        return aead.decrypt(cipherBytes, saltBytes);
    }


}
