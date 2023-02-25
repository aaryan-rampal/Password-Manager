package model;

import me.gosimple.nbvcxz.resources.Generator;

import java.security.SecureRandom;
import java.util.ArrayList;

public class PasswordGenerator extends Generator {

    /**
     * @REQUIRES: characterTypes is a valid enum value (ALPHA, ALPHANUMERIC, ALPHANUMERICSYMBOL, or NUMERIC),
     * length > 0
     * @EFFECTS: generates a password of the given length; password includes all of the character types that are
     * included in the list parameter
     */
    public String generateRandomPassword(ArrayList<CharacterTypes> characterTypes, int length) {
        StringBuffer buffer = new StringBuffer();
        String characters = createCharacterSequence(characterTypes);

        int charactersLength = characters.length();
        SecureRandom rnd = new SecureRandom();

        for (int i = 0; i < length; ++i) {
            double index = (double) rnd.nextInt(charactersLength);
            buffer.append(characters.charAt((int) index));
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
                case NUMERIC:
                    sb.append("1234567890");
                    break;
            }
        }

        return sb.toString();
    }

    /**
     * @REQUIRES: characterTypesBoolean has 4 elements
     */
    public ArrayList<CharacterTypes> addCharacterTypes(ArrayList<Boolean> characterTypesBoolean) {
        ArrayList<CharacterTypes> ct = new ArrayList<>();

        if (characterTypesBoolean.get(0)) {
            ct.add(CharacterTypes.LOWERCASE_ALPHA);
        }
        if (characterTypesBoolean.get(1)) {
            ct.add(CharacterTypes.UPPERCASE_ALPHA);
        }
        if (characterTypesBoolean.get(2)) {
            ct.add(CharacterTypes.NUMERIC);
        }
        if (characterTypesBoolean.get(3)) {
            ct.add(CharacterTypes.SYMBOLS);
        }

        return ct;
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
