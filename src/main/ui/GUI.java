package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    
    private JPanel cardPanel;
    private JPanel introMenu;
    private JButton createButton;
    private JButton loadButton;
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

    private PasswordManager passwordManager;

    private CardLayout cl;

    private static final String CREATE_BUTTON = "CREATE BUTTON";
    private static final String LOAD_BUTTON = "LOAD BUTTON";
    private static final String NEXT_BUTTON = "NEXT BUTTON";
    private static final String GENERATE_PASSWORD_BUTTON = "GENERATE_PASSWORD_BUTTON ";
    private static final String CUSTOM_PASSWORD_BUTTON = "CUSTOM_PASSWORD_BUTTON ";
    private static final String PASSWORD_BUTTON = "PASSWORD_BUTTON ";
    private static final String PASSPHRASE_BUTTON = "PASSPHRASE_BUTTON";
    private static final String BUTTON_TO_MAIN_MENU = "BUTTON TO MAIN MENU";
    private static final String BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD = "BUTTON TO MAIN MENU FROM CUSTOM PASSWORD";

    public GUI() {
        super("Password Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension((int) (600 * 1.5), (int) (500 * 1.5)));
        setContentPane(cardPanel);
        setVisible(true);

        passwordManager = new PasswordManager();

        setupCardLayout();
        addActionToButtons();
    }

    private void setupCardLayout() {
        cl = (CardLayout) cardPanel.getLayout();
        cl.first(cardPanel);
    }

    public void addActionToButtons() {
        activate(createButton, CREATE_BUTTON);
        activate(loadButton, LOAD_BUTTON);
        activate(nextButton, NEXT_BUTTON);
        activate(generatePasswordRadioButton, GENERATE_PASSWORD_BUTTON);
        activate(customPasswordRadioButton, CUSTOM_PASSWORD_BUTTON);
        activate(passphraseRadioButton, PASSPHRASE_BUTTON);
        activate(passwordRadioButton, PASSWORD_BUTTON);
        activate(nextButton2, BUTTON_TO_MAIN_MENU);
        activate(nextButton3, BUTTON_TO_MAIN_MENU);
        activate(nextButton4, BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD);
        activate(createANewEntryButton, CREATE_BUTTON);
        activate(loadEntriesFromFileButton, LOAD_BUTTON);
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
                cl.show(cardPanel, "Card2");
                break;
            case LOAD_BUTTON:
                break;
            case NEXT_BUTTON:
                cl.show(cardPanel, "Card3");
                break;
            case CUSTOM_PASSWORD_BUTTON:
                cl.show(cardPanel, "Card4");
                break;
            case GENERATE_PASSWORD_BUTTON:
                cl.show(cardPanel, "Card5");
                break;
            case PASSWORD_BUTTON:
                cl.show(cardPanel, "Card6");
                makeRadioButtonsGroup();
                break;
            case PASSPHRASE_BUTTON:
                cl.show(cardPanel, "Card7");
                break;
            case BUTTON_TO_MAIN_MENU:
                cl.show(cardPanel, "Card8");
                break;
            case BUTTON_TO_MAIN_MENU_FROM_CUSTOM_PASSWORD:
//                if (passwordField1.getPassword() == null) {
//                    cl.show(cardPanel, )
//                }
            default:
                System.out.println();
                break;
        }
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
