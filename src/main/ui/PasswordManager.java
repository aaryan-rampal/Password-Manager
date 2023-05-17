package ui;

import me.gosimple.nbvcxz.resources.Generator;
import model.entries.Entry;
import model.entries.File;
import model.entries.Password;
import model.entries.PasswordGenerator;
import model.event.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static model.entries.PasswordGenerator.CharacterTypes;
import static ui.Input.*;

// Represents the password manager application with the file currently open
public class PasswordManager {
    private static final String JSON_STORE = "./data/workroom.json";
    private File file;
    private Scanner scan;
    private PasswordGenerator passwordGenerator;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String masterPassword;

    /**
     * @MODIFIES: this
     * @EFFECTS: starts the password manager application
     */
    public void start() {
        file = new File();
        scan = new Scanner(System.in);
        passwordGenerator = new PasswordGenerator();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        displayIntroduction();
    }

    /**
     * @EFFECTS: displays the introduction menu and handles user input
     */
    private void displayIntroduction() {
        boolean breakCondition = false;
        do {
            System.out.println("Welcome to your password manager\n"
                    + "Enter " + CREATE + " to create a new entry.\n"
                    + "Enter " + VIEW + " to view an entry.\n"
                    + "Enter " + LIST + " to list all entries.\n"
                    + "Enter " + SAVE + " to save your file.\n"
                    + "Enter " + LOAD + " to load your file.\n"
                    + "Enter " + EXIT + " to exit.");

            breakCondition = parseInput(scan.nextLine());
        } while (!breakCondition);
    }

    /**
     * @EFFECTS: parse the user input and show the correct menu based on the input, returns boolean value which
     * indicates whether user wants to exit or not
     */
    private boolean parseInput(String input) {
        switch (Input.findCorrespondingEnum(input)) {
            case CREATE:
                createEntry();
                System.out.println();
                break;
            case LIST:
                listAllEntries();
                System.out.println();
                break;
            case EXIT:
                System.out.println("Thanks for using the password manager!");
                System.out.println("\nLog:");
                EventLog.printLog();
                return true;
            case SAVE:
                saveFile();
                break;
            case LOAD:
                loadFile();
                break;
            case VIEW:
                viewEntry();
                break;
            default:
                System.out.println("Sorry, I didn't understand that command. Please try again.");
                break;
        }
        return false;
    }

    private void viewEntry() {
        System.out.println("Which entry number would you like to view?");
        int index = nextInt() - 1;
        try {
            String output = file.viewEntry(index);
            System.out.println(output);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item #" + ++index + " does not exist.");
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
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < file.getSizeOfEntries(); i++) {
                sb.append("----------------------------------------------------------------------------\n");

                Entry e = file.getEntryAtIndex(i);
                sb.append(e.toString(i));
            }
            System.out.print(sb);
        }
    }

    /**
     * @MODIFIES: file
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

        Input i;
        do {
            System.out.println("Enter " + CUSTOM + " to create a custom password.\n"
                    + "Enter " + RANDOM + " to generate a random password.");
            input = scan.nextLine();

            i = Input.findCorrespondingEnum(input);

            switch (i) {
                case CUSTOM:
                    System.out.println("Enter your password.");
                    String passwordText = scan.nextLine();
                    password = new Password(passwordText);
                    break;
                case RANDOM:
                    password = generateRandomPassword();
                    break;
                default:
                    System.out.println("Sorry, I didn't understand that command. Please try again.");
                    break;
            }
        } while (i != CUSTOM && i != RANDOM);

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
        System.out.println("Enter " + PASSPHRASE + " to generate a passphrase.\n"
                + "Enter " + PASSWORD + " to generate a password.");
        String input = scan.nextLine();
        String passwordText = null;

        Input i = Input.findCorrespondingEnum(input);

        if (i == PASSWORD) {
            ArrayList<Boolean> characterTypesBoolean = promptAndStoreInput();
            System.out.println("How many characters do you want?");
            int length = nextInt();
            passwordText = passwordGenerator.generatePassword(characterTypesBoolean, length);
        } else if (i == PASSPHRASE) {
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

    /**
     * @EFFECTS: saves the file object
     */
    private void saveFile() {
        try {
            if (masterPassword == null) {
                System.out.println("Enter your master password: ");
                masterPassword = scan.nextLine();
            }
            jsonWriter.open();
            jsonWriter.write(file, masterPassword);
            jsonWriter.close();
            System.out.println("Saved file to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /**
     * @MODIFIES: this
     * @EFFECTS: loads saved file object
     */
    private void loadFile() {
        try {
            System.out.println("Enter your master password: ");
            masterPassword = scan.nextLine();
            file = jsonReader.read(masterPassword, JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
