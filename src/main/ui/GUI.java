package ui;

import model.Entry;
import model.File;
import model.Password;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JButton deleteAnEntryButton;
    private JTextField textField3;
    private JButton deleteButton;
    private JPanel deleteEntry;
    private JTable table1;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JRadioButton a4RadioButton;
    private JRadioButton allRadioButton;
    private JRadioButton a0RadioButton;
    private JButton backButton2;

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
    private static final String LOAD_ENTRIES_BUTTON = "LOAD ENTRIES BUTTON";
    private static final String LIST_ENTRIES = "LIST ENTRIES";
    private static final String DELETE_BUTTON = "DELETE BUTTON";
    private static final String GO_TO_DELETE_BUTTON = "GO_TO_DELETE_BUTTON";
    private static final String A0_BUTTON = "A0 BUTTON";
    private static final String A1_BUTTON = "A1 BUTTON";
    private static final String A2_BUTTON = "A2 BUTTON";
    private static final String A3_BUTTON = "A3 BUTTON";
    private static final String A4_BUTTON = "A4 BUTTON";
    private static final String ALL_BUTTON = "ALL BUTTON";
    private static final String BACK_FROM_LIST_BUTTON = "BACK FROM LIST BUTTON";


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
        activate(loadButton1, LOAD_ENTRIES_BUTTON);
        activate(listAllEntriesButton, LIST_ENTRIES);
        activate(deleteButton, DELETE_BUTTON);
        activate(deleteAnEntryButton, GO_TO_DELETE_BUTTON);
        activate(a0RadioButton, A0_BUTTON);
        activate(a1RadioButton, A1_BUTTON);
        activate(a2RadioButton, A2_BUTTON);
        activate(a3RadioButton, A3_BUTTON);
        activate(a4RadioButton, A4_BUTTON);
        activate(allRadioButton, ALL_BUTTON);
        activate(backButton2, BACK_FROM_LIST_BUTTON);
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
                break;
            case CUSTOM_PASSWORD_BUTTON:
                cl.show(cardPanel, "customPassword");
                clearRadioButton(customPasswordRadioButton);
                break;
            case GENERATE_PASSWORD_BUTTON:
                cl.show(cardPanel, "choosePasswordOrPassphrase");
                clearRadioButton(generatePasswordRadioButton);
                break;
            case PASSWORD_BUTTON:
                cl.show(cardPanel, "passwordSpecifications");
                makeRadioButtonsGroup();
                clearRadioButton(passwordRadioButton);
                break;
            case PASSPHRASE_BUTTON:
                cl.show(cardPanel, "passphraseSpecifications");
                clearRadioButton(passphraseRadioButton);
                break;
            case BUTTON_TO_MAIN_MENU_FROM_PASSPHRASE:
                callCreateEntry(1);
                clearRandomPassphraseMenu();
                cl.show(cardPanel, "mainMenu");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_PASSWORD:
                callCreateEntry(2);
                clearRandomPasswordMenu();
                cl.show(cardPanel, "mainMenu");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD:
                callCreateEntry(3);
                clearCustomPasswordMenu();
                cl.show(cardPanel, "mainMenu");
            case BACK_BUTTON:
                clearTextField(passwordField2);
                cl.show(cardPanel, "mainMenu");
                break;
            case BACK_FROM_LOAD_BUTTON:
                clearTextField(passwordField3);
                if (loadFromIntro) {
                    cl.show(cardPanel, "introMenu");
                } else {
                    cl.show(cardPanel, "mainMenu");
                }
                break;
            case LIST_ENTRIES:
                listEntries(passwordManager.getFile().getEntries());
                cl.show(cardPanel, "listEntries");
                break;
            case SAVE_BUTTON:
                save();
                clearTextField(passwordField2);
                cl.show(cardPanel, "mainMenu");
                break;
            case GO_TO_SAVE_BUTTON:
                cl.show(cardPanel, "saveEntries");
                break;
            case EXIT_BUTTON:
                System.exit(0);
                break;
            case LOAD_ENTRIES_BUTTON:
                load();
                clearTextField(passwordField3);
                cl.show(cardPanel, "mainMenu");
                break;
            case DELETE_BUTTON:
                deleteEntry();
                cl.show(cardPanel, "mainMenu");
                break;
            case GO_TO_DELETE_BUTTON:
                cl.show(cardPanel, "deleteEntry");
                break;
            case A0_BUTTON:
                filterTable(0);
                break;
            case A1_BUTTON:
                filterTable(1);
                break;
            case A2_BUTTON:
                filterTable(2);
                break;
            case A3_BUTTON:
                filterTable(3);
                break;
            case A4_BUTTON:
                filterTable(4);
                break;
            case ALL_BUTTON:
                filterTable(5);
                break;
            case BACK_FROM_LIST_BUTTON:
                filterTable(5);
                clearRadioButtonGroup(a1RadioButton.getModel().getGroup());
                cl.show(cardPanel, "mainMenu");
                break;
            default:
                System.out.println();
                break;
        }
    }

    private void filterTable(int i) {
        File file = passwordManager.getFile();
        ArrayList<Entry> entries = file.getEntries();
        if (i == 5) {
            listEntries(entries);
        } else {
            ArrayList<Entry> filteredEntries = new ArrayList<>();
            for (Entry e : entries) {
                if (e.getPassword().findScore() == i) {
                    filteredEntries.add(e);
                }
            }
            listEntries(filteredEntries);
        }
    }

    private void deleteEntry() {
        passwordManager.removeEntryForGUI(Integer.parseInt(textField3.getText()));
    }

    private void listEntries(ArrayList<Entry> entries) {
//        File file = passwordManager.getFile();
//        ArrayList<Entry> entries = file.getEntries();
        DefaultTableModel tableModel = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table1.setModel(tableModel);

        tableModel.addColumn("Name");
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");
        tableModel.addColumn("URL");
        tableModel.addColumn("Notes");

        tableModel.addRow(new Object[]{"Name","Username","Password","URL","Notes"});

        fillTableWithEntries(tableModel, entries);
        makePasswordScoreRadioButtons();
//        this.add(new JScrollPane(table1));
    }

    private void makePasswordScoreRadioButtons() {
        ArrayList<JRadioButton> buttons = new ArrayList<>();
        buttons.add(a1RadioButton);
        buttons.add(a2RadioButton);
        buttons.add(a3RadioButton);
        buttons.add(a4RadioButton);
        buttons.add(allRadioButton);
        makePairOfRadioButtons(buttons);
    }

    private void fillTableWithEntries(DefaultTableModel tableModel, ArrayList<Entry> entries) {
        for (int i = 0; i < entries.size(); i++) {
            Entry e = entries.get(i);
            String[] entryData = new String[]{e.getName(), e.getUsername(), e.getPassword().getPassword(), e.getUrl(), e.getNotes()};
            tableModel.addRow(entryData);
        }
    }

    private void save() {
        String masterPassword = new String(passwordField2.getPassword());
        passwordManager.saveFileFromGUI(masterPassword);
    }

    private void load() {
        String masterPassword = new String(passwordField3.getPassword());
        passwordManager.loadFileFromGUI(masterPassword);
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

        clearCreateFieldMenu();
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
        clearTextField(nameTextField);
        clearTextField(userNameTextField);
        clearTextField(urlTextField);
        clearTextField(notesTextField);
    }

    private void clearCustomPasswordMenu() {
        clearTextField(passwordField1);
    }

    private void clearRandomPasswordMenu() {
        clearTextField(textField1);
        clearRadioButtonGroup(lowercaseNo.getModel().getGroup());
        clearRadioButtonGroup(uppercaseNo.getModel().getGroup());
        clearRadioButtonGroup(numbersNo.getModel().getGroup());
        clearRadioButtonGroup(symbolsNo.getModel().getGroup());
    }

    private void clearRandomPassphraseMenu() {
        clearTextField(textField2);
    }

    private void clearRadioButton(JRadioButton radioButton) {
        radioButton.setSelected(false);
    }

    private void clearRadioButtonGroup(ButtonGroup buttonGroup) {
        buttonGroup.clearSelection();
    }

    private void clearTextField(JTextField textField) {
        textField.setText("");
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

    private void makePairOfRadioButtons(ArrayList<JRadioButton> buttons) {
        ButtonGroup group = new ButtonGroup();
        for (JRadioButton button : buttons) {
            group.add(button);
        }
    }
}
