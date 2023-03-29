package model;

import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.subtle.AesGcmJce;

import javax.crypto.AEADBadTagException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Keyset {

    private AesGcmJce aead;
    private ByteConvertor bc;

    public Keyset(String password) {
        try {
            bc = new ByteConvertor();
            AeadConfig.register();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] key128Bit = convertTo128Bits(messageDigest.digest());
            aead = new AesGcmJce(key128Bit);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] convertTo128Bits(byte[] digest) {
        byte[] key128Bit = new byte[16];
        System.arraycopy(digest, 0, key128Bit, 0, 16);
        return key128Bit;
    }

    public byte[] encrypt(String plainText, byte[] saltBytes) {
        byte[] cipherBytes;
        try {
            cipherBytes = aead.encrypt(plainText.getBytes(StandardCharsets.UTF_8), saltBytes);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return cipherBytes;
    }

    public byte[] decrypt(byte[] cipherBytes, byte[] saltBytes) throws GeneralSecurityException {
        byte[] decryptedBytes;
        decryptedBytes = aead.decrypt(cipherBytes, saltBytes);
        return decryptedBytes;
    }


}
