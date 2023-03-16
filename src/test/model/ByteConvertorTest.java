package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteConvertorTest {

    ByteConvertor bc;

    @BeforeEach
    void setup() {
        bc = new ByteConvertor();
    }

    @Test
    void testStringToBytes() {
        String test = "blah";
        byte[] testBytes = bc.stringToBytes(test);
        assertEquals(testBytes, bc.stringToBytes(test));
    }
}
