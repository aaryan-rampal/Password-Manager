package model;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.subtle.AesGcmJce;

import javax.crypto.AEADBadTagException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Represents a custom Keyset object to use with the Tink library that is connected to a string (essentially the master
// password of the file)
public class Keyset {

    private AesGcmJce aead;
    private ByteConvertor bc;

    public Keyset(String password, String algorithm) throws GeneralSecurityException {
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
        byte[] cipherBytes;
        try {
            cipherBytes = aead.encrypt(plainText.getBytes(StandardCharsets.UTF_8), saltBytes);
        } catch (Exception e) {
            return null;
        }
        return cipherBytes;
    }

    /**
     * @REQUIRES: cipherBytes and saltBytes are not null
     * @EFFECTS: decrypts the encrypted byte array into a plain text string
     */
    public byte[] decrypt(byte[] cipherBytes, byte[] saltBytes) throws GeneralSecurityException {
        byte[] decryptedBytes;
        decryptedBytes = aead.decrypt(cipherBytes, saltBytes);
        return decryptedBytes;
    }


}
