package model;

import model.entries.PasswordGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static model.entries.PasswordGenerator.CharacterTypes;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashSet;

public class PasswordGeneratorTest {
    PasswordGenerator pg;
    ArrayList<Boolean> characterTypesBoolean;

    @BeforeEach
    void runBefore() {
        pg = PasswordGenerator.getInstance();
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
        characterTypesBoolean.add(false);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(true);
        characterTypesBoolean.add(false);

        ArrayList<CharacterTypes> ct = pg.addCharacterTypes(characterTypesBoolean);
        assertEquals(ct.size(), 2);
        assertTrue(ct.get(0) == CharacterTypes.UPPERCASE_ALPHA);
        assertTrue(ct.get(1) == CharacterTypes.NUMERIC);
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

    // The implementation of HashSet was learned through the Javadocs
    // https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html

    @Test
    void testGenerateRandomPasswordOneType() {
        HashSet<Character> lowerCase = addStringToSet("1234567890");
        ArrayList<CharacterTypes> ct = new ArrayList<>();
        ct.add(CharacterTypes.NUMERIC);

        String random = pg.generateRandomPassword(ct, 20);
        assertEquals(random.length(), 20);
        assertTrue(setContainsAllCharacters(random, lowerCase));
    }

    @Test
    void testGenerateRandomPasswordAllTypes() {
        HashSet<Character> allTypes =
                addStringToSet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLNOPQRSTUVWXYZ1234567890!@#$%^&*()");
        ArrayList<CharacterTypes> ct = new ArrayList<>();
        ct.add(CharacterTypes.LOWERCASE_ALPHA);
        ct.add(CharacterTypes.UPPERCASE_ALPHA);
        ct.add(CharacterTypes.NUMERIC);
        ct.add(CharacterTypes.SYMBOLS);

        String random = pg.generateRandomPassword(ct, 50);
        assertEquals(random.length(), 50);
        assertTrue(setContainsAllCharacters(random, allTypes));
    }

    @Test
    void testGenerateRandomPasswordMultipleTypes() {
        HashSet<Character> upperCaseAndSymbols = addStringToSet("ABCDEFGHIJKLNOPQRSTUVWXYZ!@#$%^&*()");
        ArrayList<CharacterTypes> ct = new ArrayList<>();
        ct.add(CharacterTypes.UPPERCASE_ALPHA);
        ct.add(CharacterTypes.SYMBOLS);

        String random = pg.generateRandomPassword(ct, 20);
        assertTrue(setContainsAllCharacters(random, upperCaseAndSymbols));
    }

    /**
     * @REQUIRES: s is not null and not empty
     * @EFFECTS: adds all the characters of s to a hashset and returns the set
     */
    private HashSet<Character> addStringToSet(String s) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }
        return set;
    }

    /**
     * @REQUIRES: set is not empty
     * @EFFECTS: returns true if all characters of s are contained in set, false otherwise
     */
    private boolean setContainsAllCharacters(String s, HashSet<Character> set) {
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
