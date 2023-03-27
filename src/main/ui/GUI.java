package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private JPanel mainMenu;
    private JLabel title;
    private JButton createButton;
    private JButton loadButton;
    private PasswordManager passwordManager;

    public GUI () {
        super("Password Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(600, 500));
        setVisible(true);

        passwordManager = new PasswordManager();
        passwordManager.start();
    }

    public void addActionToButtons() {
        createButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
