package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
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

    @Test
    void testBytesToString() {
        byte[] testBytes = {(byte) 0xB5, (byte) 0xEB, 0x2D, (byte) 0x8A, 0x78};
        String test = "testing=";

        String test2 = bc.bytesToString(testBytes);
        assertEquals(test2, test);
    }
}
