package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.PasswordGenerator.CharacterTypes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

public class PasswordGeneratorTest {

    PasswordGenerator pg;
    ArrayList<Boolean> characterTypesBoolean;

    @BeforeEach
    void setup() {
        pg = new PasswordGenerator();
        characterTypesBoolean = new ArrayList<>();
    }

    @Test
    void testAddCharacterTypesOneType() {
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(false);
        characterTypesBoolean.add(false);
        characterTypesBoolean.add(false);

        ArrayList<CharacterTypes> ct = pg.addCharacterTypes(characterTypesBoolean);
        assertEquals(ct.size(), 1);
        assertTrue(ct.get(0) == CharacterTypes.LOWERCASE_ALPHA);
    }

    @Test
    void testAddCharacterTypesMultipleTypes() {
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(false);

        ArrayList<CharacterTypes> ct = pg.addCharacterTypes(characterTypesBoolean);
        assertEquals(ct.size(), 3);
        assertTrue(ct.get(0) == CharacterTypes.LOWERCASE_ALPHA);
        assertTrue(ct.get(1) == CharacterTypes.UPPERCASE_ALPHA);
        assertTrue(ct.get(2) == CharacterTypes.NUMERIC);
    }

    @Test
    void testAddCharacterTypesAllTypes() {
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);

        ArrayList<CharacterTypes> ct = pg.addCharacterTypes(characterTypesBoolean);
        assertEquals(ct.size(), 4);
        assertTrue(ct.get(0) == CharacterTypes.LOWERCASE_ALPHA);
        assertTrue(ct.get(1) == CharacterTypes.UPPERCASE_ALPHA);
        assertTrue(ct.get(2) == CharacterTypes.NUMERIC);
        assertTrue(ct.get(3) == CharacterTypes.SYMBOLS);
    }
}
