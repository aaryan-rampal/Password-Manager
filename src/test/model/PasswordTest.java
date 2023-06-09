package model;

import model.entries.Password;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordTest {
    Password password;

    @BeforeEach
    void runBefore() {
        password = new Password("password");
    }

    @Test
    void testConstructor() {
        assertEquals(password.getPasswordText(), "password");
    }

    @Test
    void testFindScoreBadPassword() {
        assertEquals(0, password.findScore());
    }

    @Test
    void testFindScoreGoodPassword() {
        password = new Password("daf4u32508unv84b234072bd872eb0cf7w0f78b08e7r1240r7kd08k2");
        assertEquals(4, password.findScore());
    }
}
