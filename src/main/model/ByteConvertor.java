package model;

import com.google.crypto.tink.subtle.Base64;

public class ByteConvertor {

    public ByteConvertor() {
    }

    public String bytesToString(byte[] bytes) {
        return Base64.encode(bytes);
    }

    public byte[] stringToBytes(String s) {
        return Base64.decode(s);
    }


}
