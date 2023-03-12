package ui;

import me.gosimple.nbvcxz.resources.Generator;
import model.Entry;
import model.File;
import model.Password;
import model.PasswordGenerator;
import static model.PasswordGenerator.CharacterTypes;
import java.util.ArrayList;
import java.util.Scanner;

// Represents the password manager application with the file currently open
public class PasswordManager {
    private File file;
    private Scanner scan;
    private PasswordGenerator passwordGenerator;
    private static final String CREATE_COMMAND = "create";
    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "exit";
    private static final String CUSTOM_COMMAND = "custom";
    private static final String RANDOM_COMMAND = "random";
    private static final String PASSPHRASE_COMMAND = "passphrase";
    private static final String PASSWORD_COMMAND = "password";

    /**
     * @MODIFIES: this
     * @EFFECTS: starts the password manager application
     */
    public void start() {
        file = new File();
        scan = new Scanner(System.in);
        passwordGenerator = new PasswordGenerator();
        displayIntroduction();
    }

    /**
     * @EFFECTS: displays the introduction menu and handles user input
     */
    private void displayIntroduction() {
        boolean breakCondition = false;
        do {
            System.out.println("Welcome to your password manager\n"
                    + "Enter " + CREATE_COMMAND + " to create a new entry.\n"
                    + "Enter " + LIST_COMMAND + " to list all entries.\n"
                    + "Enter " + EXIT_COMMAND + " to exit.");

            breakCondition = parseInput(scan.nextLine());
        } while (!breakCondition);
    }

    /**
     * @EFFECTS: parse the user input and show the correct menu based on the input, returns boolean value which
     * indicates whether user wants to exit or not
     */
    private boolean parseInput(String input) {
        switch (input) {
            case CREATE_COMMAND:
                createEntry();
                System.out.println();
                return false;
            case LIST_COMMAND:
                listAllEntries();
                System.out.println();
                return false;
            case EXIT_COMMAND:
                System.out.println("Thanks for using the password manager!");
                return true;
            default:
                System.out.println("Sorry, I didn't understand that command. Please try again.");
                return false;
        }
    }

    /**
     * @EFFECTS: lists entry number, name, username, password, password score, url, and notes of each entry in the
     * file arraylist
     */
    private void listAllEntries() {
        if (file.getSizeOfEntries() == 0) {
            System.out.println("You have no entries.");
        } else {
            for (int i = 0; i < file.getSizeOfEntries(); i++) {
                System.out.println("----------------------------------------------------------------------------");

                Entry e = file.getEntryAtIndex(i);
                Password p = e.getPassword();

                System.out.println("Entry #" + (i + 1));
                System.out.println("Name: " + e.getName());
                System.out.println("Username: " + e.getUsername());
                System.out.println("Password: " + p.getPassword());
                System.out.println("Password score: " + p.findScore());
                System.out.println("URL: " + e.getUrl());
                System.out.println("Notes: " + e.getNotes());
            }
        }
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: takes user input and assigns it to a new entry object which is added to file
     */
    private void createEntry() {
        String name = editFields("name");
        String username = editFields("username");
        Password password = handlePasswordPrompts();
        String url = editFields("url");
        String notes = editFields("notes");

        Entry entry = new Entry(name, username, password, url, notes);
        file.addEntry(entry);
    }

    /**
     * @EFFECTS: takes a string and prompts the user to enter that string's value for the entry
     */
    private String editFields(String text) {
        System.out.println("Please enter the " + text + " for this entry: ");
        return scan.nextLine();
    }

    /**
     * @EFFECTS: takes user input regarding a custom or random password and calls the specified methods for the user
     * choice
     */
    private Password handlePasswordPrompts() {
        Password password = null;
        String input;

        do {
            System.out.println("Enter " + CUSTOM_COMMAND + " to create a custom password.\n"
                    + "Enter " + RANDOM_COMMAND + " to generate a random password.");
            input = scan.nextLine();

            switch (input) {
                case CUSTOM_COMMAND:
                    System.out.println("Enter your password.");
                    String passwordText = scan.nextLine();
                    password = new Password(passwordText);
                    break;
                case RANDOM_COMMAND:
                    password = generateRandomPassword();
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command. Please try again.");
                    break;
            }
        } while (!input.equals(CUSTOM_COMMAND) && !input.equals(RANDOM_COMMAND));

        return password;
    }

    /**
     * @EFFECTS: prompts the user for their choice of character types included in their password
     */
    private void promptUserForCharacterTypes(String text) {
        System.out.println("Do you want " + text + " in your password?");
    }

    /**
     * @EFFECTS: creates random password or passphrase depending on user input and returns the password object
     */
    private Password generateRandomPassword() {
        System.out.println("Enter " + PASSPHRASE_COMMAND + " to generate a passphrase.\n"
                + "Enter " + PASSWORD_COMMAND + " to generate a password.");
        String input = scan.nextLine();
        String passwordText = null;

        if (input.equals(PASSWORD_COMMAND)) {
            ArrayList<Boolean> characterTypesBoolean = promptAndStoreInput();
            System.out.println("How many characters do you want?");
            int length = nextInt();
            ArrayList<CharacterTypes> ct = passwordGenerator.addCharacterTypes(characterTypesBoolean);
            passwordText = generatePassword(ct, length);
        } else if (input.equals(PASSPHRASE_COMMAND)) {
            System.out.println("How many words do you want your passphrase to be?");
            int words = nextInt();
            passwordText = Generator.generatePassphrase("-", words);
        } else {
            System.out.println("Sorry, I didn't understand that. Please try again.");
            return generateRandomPassword();
        }

        return new Password(passwordText);
    }

    /**
     * @EFFECTS: returns a random password given the character types that are available and the length specified
     */
    private String generatePassword(ArrayList<CharacterTypes> ct, int length) {
        return passwordGenerator.generateRandomPassword(ct, length);
    }

    /**
     * @EFFECTS: collects user input for the different character types that are possible in the generation of the
     * password and stores those values into a boolean arraylist
     */
    private ArrayList<Boolean> promptAndStoreInput() {
        ArrayList<Boolean> characterTypesBoolean = new ArrayList<>();

        promptUserForCharacterTypes("lowercase alphabets");
        characterTypesBoolean.add(convertInputToBoolean());
        promptUserForCharacterTypes("uppercase alphabets");
        characterTypesBoolean.add(convertInputToBoolean());
        promptUserForCharacterTypes("numbers");
        characterTypesBoolean.add(convertInputToBoolean());
        promptUserForCharacterTypes("symbols");
        characterTypesBoolean.add(convertInputToBoolean());

        if (areAllFalse(characterTypesBoolean)) {
            System.out.println("Please enter yes for at least one category.");
            characterTypesBoolean = promptAndStoreInput();
        }

        return characterTypesBoolean;
    }

    /**
     * @EFFECTS: returns true if all boolean values in arr are false, false otherwise
     */
    private boolean areAllFalse(ArrayList<Boolean> arr) {
        for (Boolean b : arr) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    /**
     * @EFFECTS: scans the next integer followed by a next line to consume the next line character
     */
    private int nextInt() {
        int input = scan.nextInt();
        scan.nextLine();
        return input;
    }

    /**
     * @EFFECTS: converts a yes input into a true and a no input into a false boolean value and returns it
     */
    private boolean convertInputToBoolean() {
        String input = scan.nextLine();
        switch (input) {
            case "yes":
                return true;
            case "no":
                return false;
            default:
                System.out.println("Sorry, I didn't understand that. Please enter yes or no.");
                convertInputToBoolean();
        }
        return false;
    }
}