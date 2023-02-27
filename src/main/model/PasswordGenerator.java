package model;

import me.gosimple.nbvcxz.resources.Generator;
import java.security.SecureRandom;
import java.util.ArrayList;

// Represents a random password generator. It extends the Generator class included in the Nbvcxz library and overrides
// some methods to be better utilized by the password manager.
public class PasswordGenerator extends Generator {
    /**
     * @REQUIRES: characterTypes is a valid enum value (LOWERCASE_ALPHA, UPPERCASE_ALPHA, NUMERIC, or SYMBOLS);
     * length > 0
     * @EFFECTS: randomly generates a password of the given length; password can contain any of the character types
     * that are included in the characterTypes
     */
    public String generateRandomPassword(ArrayList<CharacterTypes> characterTypes, int length) {
        StringBuilder buffer = new StringBuilder();
        String characters = createCharacterSequence(characterTypes);

        int charactersLength = characters.length();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = rnd.nextInt(charactersLength);
            buffer.append(characters.charAt(index));
        }

        return buffer.toString();
    }

    /**
     * @REQUIRES: characterTypes has at least 1 element
     * @EFFECTS: creates a string with all the possible characters types that the user wants in their password
     */
    private String createCharacterSequence(ArrayList<CharacterTypes> characterTypes) {
        StringBuilder sb = new StringBuilder();

        for (CharacterTypes ct : characterTypes) {
            switch (ct) {
                case LOWERCASE_ALPHA:
                    sb.append("abcdefghijklmnopqrstuvwxyz");
                    break;
                case UPPERCASE_ALPHA:
                    sb.append("ABCDEFGHIJKLNOPQRSTUVWXYZ");
                    break;
                case SYMBOLS:
                    sb.append("!@#$%^&*()");
                    break;
                default:
                    sb.append("1234567890");
                    break;
            }
        }

        return sb.toString();
    }

    /**
     * @REQUIRES: characterTypesBoolean has 4 elements
     * @EFFECTS: adds the specified characterTypes the user wants to the arraylist that is returned
     */
    public ArrayList<CharacterTypes> addCharacterTypes(ArrayList<Boolean> characterTypesBoolean) {
        ArrayList<CharacterTypes> ct = new ArrayList<>();

        addCharacterTypes(characterTypesBoolean, 0, CharacterTypes.LOWERCASE_ALPHA, ct);
        addCharacterTypes(characterTypesBoolean, 1, CharacterTypes.UPPERCASE_ALPHA, ct);
        addCharacterTypes(characterTypesBoolean, 2, CharacterTypes.NUMERIC, ct);
        addCharacterTypes(characterTypesBoolean, 3, CharacterTypes.SYMBOLS, ct);

        return ct;
    }

    /**
     * @REQUIRES: characterTypesBoolean has 4 elements; 0 <= index <= 3
     * @EFFECTS: adds the consumed characterType to ct if the boolean at index of characterTypesBoolean is true
     */
    private void addCharacterTypes(ArrayList<Boolean> characterTypesBoolean, int index,
                                   CharacterTypes characterType, ArrayList<CharacterTypes> ct) {
        if (characterTypesBoolean.get(index)) {
            ct.add(characterType);
        }
    }

    public enum CharacterTypes {
        UPPERCASE_ALPHA,
        LOWERCASE_ALPHA,
        SYMBOLS,
        NUMERIC;

        private CharacterTypes() {
        }
    }
}
