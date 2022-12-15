package graph;

import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import code.*;

public class Fenetre extends JFrame
{
    Socket socket;
    Client client;
    JTextField userField;
    JButton userButton;
    JLabel userName;
    JTextArea textArea;
    JTextField textField;
    JButton button;
    Listener ecoute = new Listener(this);

///getters & setters
    public JTextArea getTextArea() {
        return textArea;
    }
    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }
    public Socket getSocket() {
        return socket;
    }
    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public JTextField getUserField() {
        return userField;
    }
    public void setUserField(JTextField userField) {
        this.userField = userField;
    }
    public JButton getUserButton() {
        return userButton;
    }
    public void setUserButton(JButton userButton) {
        this.userButton = userButton;
    }
    public JLabel getUserName() {
        return userName;
    }
    public void setUserName(JLabel userName) {
        this.userName = userName;
    }
    public JTextField getTextField() {
        return textField;
    }
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }
    public JButton getButton() {
        return button;
    }
    public void setButton(JButton button) {
        this.button = button;
    }

///constructor
    public Fenetre()
    {
        super("Chat");

        try {
            socket = new Socket("localhost", 1820);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userField = new JTextField();
        userButton = new JButton("Connect");
        userName = new JLabel("");
        textArea = new JTextArea();
        textField = new JTextField();
        button = new JButton("Send");

        userField.setBounds(10, 10, 300, 30);
        userButton.setBounds(320, 10, 100, 30);
        userName.setBounds(470, 10, 100, 30);
        textArea.setBounds(10, 50, 560, 450);
        textField.setBounds(10, 520, 450, 30);
        button.setBounds(470, 520, 100, 30);

        userButton.addActionListener(ecoute);
        button.addActionListener(ecoute);

        add(userField);
        add(userButton);
        add(userName);
        add(textArea);
        add(textField);
        add(button);

        setLayout(null);
        setSize(600, 600);
        setVisible(true);
    }
}
