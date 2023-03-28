package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JPanel cardPanel;
    private JPanel mainMenu;
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
    private JButton nextButton1;
    private PasswordManager passwordManager;

    private CardLayout cl;

    private static final String CREATE_BUTTON = "CREATE BUTTON";
    private static final String LOAD_BUTTON = "LOAD BUTTON";
    private static final String NEXT_BUTTON = "NEXT BUTTON";
    private static final String GENERATE_PASSWORD_BUTTON = "GENERATE_PASSWORD_BUTTON ";
    private static final String CUSTOM_PASSWORD_BUTTON = "CUSTOM_PASSWORD_BUTTON ";

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
            default:
                System.out.println();
                break;
        }
    }

}
