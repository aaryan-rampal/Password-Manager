package ui;

import model.Password;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {
    
    private JPanel cardPanel;
    private JPanel introMenu;
    private JButton createButton;
    private JButton loadFromIntroButton;
    private JPanel createFieldMenu;
    private JTextField nameTextField;
    private JTextField userNameTextField;
    private JTextField urlTextField;
    private JTextField notesTextField;
    private JButton nextButton;
    private JPanel chooseCustomOrRandom;
    private JRadioButton customPasswordRadioButton;
    private JRadioButton generatePasswordRadioButton;
    private JPanel customPassword;
    private JPasswordField passwordField1;
    private JButton nextButton4;
    private JPanel choosePasswordOrPassphrase;
    private JRadioButton passphraseRadioButton;
    private JRadioButton passwordRadioButton;
    private JPanel passwordSpecifications;
    private JTextField textField1;
    private JButton nextButton2;
    private JRadioButton lowercaseNo;
    private JRadioButton uppercaseNo;
    private JRadioButton numbersNo;
    private JRadioButton symbolsNo;
    private JRadioButton lowercaseYes;
    private JRadioButton uppercaseYes;
    private JRadioButton numbersYes;
    private JRadioButton symbolsYes;
    private JPanel passphraseSpecifications;
    private JTextField textField2;
    private JButton nextButton3;
    private JPanel mainMenu;
    private JButton createANewEntryButton;
    private JButton listAllEntriesButton;
    private JButton saveEntriesToFileButton;
    private JButton loadEntriesFromFileButton;
    private JButton exitButton;
    private JList list1;
    private JPanel listEntries;
    private JPanel saveEntries;
    private JPasswordField passwordField3;
    private JButton loadButton1;
    private JButton backButton;
    private JPasswordField passwordField2;
    private JButton saveButton;
    private JButton backButton1;
    private JPanel loadEntries;

    private PasswordManager passwordManager;
    private CardLayout cl;
    private boolean loadFromIntro;

    private static final String CREATE_BUTTON = "CREATE BUTTON";
    private static final String LOAD_BUTTON = "LOAD BUTTON";
    private static final String LOAD_FROM_INTRO_BUTTON = "LOAD_FROM_INTRO_BUTTON";
    private static final String GO_TO_SAVE_BUTTON = "GO TO SAVE BUTTON";
    private static final String SAVE_BUTTON = "SAVE BUTTON";
    private static final String NEXT_BUTTON = "NEXT BUTTON";
    private static final String GENERATE_PASSWORD_BUTTON = "GENERATE_PASSWORD_BUTTON ";
    private static final String CUSTOM_PASSWORD_BUTTON = "CUSTOM_PASSWORD_BUTTON ";
    private static final String PASSWORD_BUTTON = "PASSWORD_BUTTON ";
    private static final String PASSPHRASE_BUTTON = "PASSPHRASE_BUTTON";
    private static final String BUTTON_TO_MAIN_MENU_FROM_PASSWORD = "BUTTON_TO_MAIN_MENU_FROM_PASSWORD";
    private static final String BUTTON_TO_MAIN_MENU_FROM_PASSPHRASE = "BUTTON_TO_MAIN_MENU_FROM_PASSPHRASE";
    private static final String BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD = "BUTTON TO MAIN MENU FROM CUSTOM PASSWORD";
    private static final String BACK_FROM_LOAD_BUTTON = "BACK_FROM_LOAD_BUTTON";
    private static final String BACK_BUTTON = "BACK_BUTTON";
    private static final String EXIT_BUTTON = "EXIT BUTTON";

    public GUI() {
        super("Password Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension((int) (600 * 1.5), (int) (500 * 1.5)));
        setContentPane(cardPanel);
        setVisible(true);

        passwordManager = new PasswordManager();
        passwordManager.start();

        setupCardLayout();
        addActionToButtons();
    }

    private void setupCardLayout() {
        cl = (CardLayout) cardPanel.getLayout();
        cl.first(cardPanel);
    }

    public void addActionToButtons() {
        activate(createButton, CREATE_BUTTON);
        activate(loadFromIntroButton, LOAD_FROM_INTRO_BUTTON);
        activate(nextButton, NEXT_BUTTON);
        activate(generatePasswordRadioButton, GENERATE_PASSWORD_BUTTON);
        activate(customPasswordRadioButton, CUSTOM_PASSWORD_BUTTON);
        activate(passphraseRadioButton, PASSPHRASE_BUTTON);
        activate(passwordRadioButton, PASSWORD_BUTTON);
        activate(nextButton2, BUTTON_TO_MAIN_MENU_FROM_PASSWORD);
        activate(nextButton3, BUTTON_TO_MAIN_MENU_FROM_PASSPHRASE);
        activate(nextButton4, BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD);
        activate(createANewEntryButton, CREATE_BUTTON);
        activate(loadEntriesFromFileButton, LOAD_BUTTON);
        activate(saveEntriesToFileButton, GO_TO_SAVE_BUTTON);
        activate(backButton, BACK_FROM_LOAD_BUTTON);
        activate(backButton1, BACK_BUTTON);
        activate(saveButton, SAVE_BUTTON);
        activate(exitButton, EXIT_BUTTON);
    }

    public void activate(JRadioButton button, String actionCommand) {
        button.addActionListener(this);
        button.setActionCommand(actionCommand);
    }

    public void activate(JButton button, String actionCommand) {
        button.addActionListener(this);
        button.setActionCommand(actionCommand);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()) {
            case CREATE_BUTTON:
                cl.show(cardPanel, "createFieldMenu");
                break;
            case LOAD_BUTTON:
                cl.show(cardPanel, "loadEntries");
                loadFromIntro = false;
                break;
            case LOAD_FROM_INTRO_BUTTON:
                cl.show(cardPanel, "loadEntries");
                loadFromIntro = true;
                break;
            case NEXT_BUTTON:
                cl.show(cardPanel, "chooseCustomOrRandom");
                clearCreateFieldMenu();
                break;
            case CUSTOM_PASSWORD_BUTTON:
                cl.show(cardPanel, "customPassword");
                break;
            case GENERATE_PASSWORD_BUTTON:
                cl.show(cardPanel, "choosePasswordOrPassphrase");
                break;
            case PASSWORD_BUTTON:
                cl.show(cardPanel, "passwordSpecifications");
                makeRadioButtonsGroup();
                break;
            case PASSPHRASE_BUTTON:
                cl.show(cardPanel, "passphraseSpecifications");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_PASSPHRASE:
                callCreateEntry(1);
                cl.show(cardPanel, "mainMenu");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_PASSWORD:
                callCreateEntry(2);
                cl.show(cardPanel, "mainMenu");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD:
                callCreateEntry(3);
                cl.show(cardPanel, "mainMenu");
            case BACK_BUTTON:
                cl.show(cardPanel, "mainMenu");
                break;
            case BACK_FROM_LOAD_BUTTON:
                if (loadFromIntro) {
                    cl.show(cardPanel, "introMenu");
                } else {
                    cl.show(cardPanel, "mainMenu");
                }
                break;
            case SAVE_BUTTON:
                save();
                cl.show(cardPanel, "mainMenu");
                break;
            case GO_TO_SAVE_BUTTON:
                cl.show(cardPanel, "saveEntries");
                break;
            case EXIT_BUTTON:
                System.exit(0);
                break;
            default:
                System.out.println();
                break;
        }
    }

    private void save() {
        String masterPassword = new String(passwordField2.getPassword());
        passwordManager.saveFileFromGUI(masterPassword);
    }

    private void callCreateEntry(int passwordType) {
        String name = nameTextField.getText();
        String username = userNameTextField.getText();
        String url = urlTextField.getText();
        String notes = notesTextField.getText();
        Password password;
        if (passwordType == 3) {
            password = new Password(new String(passwordField1.getPassword()));
        } else {
            password = getPassword(passwordType);
        }
        passwordManager.createEntry(name, username, password, url, notes);
    }

    private Password getPassword(int passwordType) {
        String passwordText = null;
        if (passwordType == 1) {
            passwordText = passwordManager.generatePassphraseForGUI(Integer.parseInt(textField2.getText()));
        } else {
            ArrayList<Boolean> characterTypesBoolean = new ArrayList<>();
            characterTypesBoolean.add(returnBooleanValue(lowercaseYes));
            characterTypesBoolean.add(returnBooleanValue(uppercaseYes));
            characterTypesBoolean.add(returnBooleanValue(numbersYes));
            characterTypesBoolean.add(returnBooleanValue(symbolsYes));
            passwordText = passwordManager.generatePasswordForGUI(characterTypesBoolean,
                    Integer.parseInt(textField1.getText()));
        }
        return new Password(passwordText);
    }

    private boolean returnBooleanValue(JRadioButton yes) {
        return yes.isSelected();
    }

    private void clearCreateFieldMenu() {
        nameTextField.setText("");
        userNameTextField.setText("");
        urlTextField.setText("");
        notesTextField.setText("");
    }

    private void makeRadioButtonsGroup() {
        makePairOfRadioButtons(lowercaseNo, lowercaseYes);
        makePairOfRadioButtons(uppercaseNo, uppercaseYes);
        makePairOfRadioButtons(numbersNo, numbersYes);
        makePairOfRadioButtons(symbolsNo, symbolsYes);
    }

    // https://stackoverflow.com/a/2253626
    private void makePairOfRadioButtons(JRadioButton buttonA, JRadioButton buttonB) {
        ButtonGroup group = new ButtonGroup();
        group.add(buttonA);
        group.add(buttonB);
    }

}
