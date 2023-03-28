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
    private PasswordManager passwordManager;

    private final String CREATE_BUTTON = "CREATE BUTTON";
    private final String LOAD_BUTTON = "LOAD BUTTON";

    private final String MAIN_MENU = "MAIN MENU";
    private final String CREATE_FIELD_MENU = "CREATE FIELD MENU";

    public GUI() {
        super("Password Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));
        setContentPane(cardPanel);
        setVisible(true);

        passwordManager = new PasswordManager();
        passwordManager.start();

        setupCardLayout();
        addActionToButtons();
    }

    private void setupCardLayout() {
        cardPanel.add(mainMenu, MAIN_MENU);
        setupMainMenu();
        cardPanel.add(createFieldMenu, CREATE_FIELD_MENU);
        setupCreateFieldMenu();
//        final CardLayout cl = (CardLayout) cardPanel.getLayout();
//        cl.show(cardPanel, CREATE_FIELD_MENU);
        ((CardLayout) cardPanel.getLayout()).show(cardPanel, MAIN_MENU);
    }

    private void setupCreateFieldMenu() {
        createFieldMenu.add(nameTextField);
        createFieldMenu.add(userNameTextField);
        createFieldMenu.add(urlTextField);
        createFieldMenu.add(notesTextField);
    }

    private void setupMainMenu() {
        mainMenu.add(createButton);
        mainMenu.add(loadButton);
    }

    public void addActionToButtons() {
        createButton.addActionListener(this);
        createButton.setActionCommand(CREATE_BUTTON);
        loadButton.addActionListener(this);
        loadButton.setActionCommand(LOAD_BUTTON);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        final CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, CREATE_FIELD_MENU);
    }

}
