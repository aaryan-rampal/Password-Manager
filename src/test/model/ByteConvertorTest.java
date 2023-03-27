package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ByteConvertorTest {

    ByteConvertor bc;

    @BeforeEach
    void setup() {
        bc = new ByteConvertor();
    }

    @Test
    void testStringToBytes() {
        String test = "testing";
        byte[] testBytes = {(byte) 0xB5, (byte) 0xEB, 0x2D, (byte) 0x8A, 0x78};
        assertTrue(Arrays.equals(bc.stringToBytes(test), testBytes));
    }

//    @Test
//    void testBytesToString() {
//        byte[] testBytes = {(byte) 0xDB, (byte) 0x5D, (byte) 0x29, (byte) 0xAE, (byte) 0x88, (byte) 0xDE, (byte) 0x72};
//        String test = "210project";
//        byte[] aaa = bc.stringToBytes(test);
//        String a = bc.bytesToString(aaa);
//        System.out.println("");
//        assertEquals(bc.bytesToString(testBytes), test);
//    }
}
