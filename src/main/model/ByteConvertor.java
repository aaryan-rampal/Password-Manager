package model;

import com.google.crypto.tink.subtle.Base64;

public class ByteConvertor {

    public ByteConvertor() {

    }

    public String bytesToString(byte[] bytes) {
        String base64String = Base64.encode(bytes);
        return base64String;
    }

    public byte[] stringToBytes(String s) {
        byte[] bytes = Base64.decode(s);
        return bytes;
    }


}
