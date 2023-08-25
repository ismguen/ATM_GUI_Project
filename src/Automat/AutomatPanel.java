package Automat;

import Register.RegisterFrame;
import Register.RegisterPanel;
import User.User;
import User.UserFrame;
import User.UserPanel;


import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AutomatPanel extends JPanel {

    static final int WIDTH = 700;
    static final int HEIGHT = 500;
    static final Dimension dim = new Dimension(WIDTH,HEIGHT);
    public static ArrayList<User> accounts = new ArrayList<User>();
    int valueOfAttempts = 3;
    static boolean access = false;
    static boolean locked = false;
    User user;


    JLabel headLabel;
    JLabel userLabel;
    JLabel pinLabel;
    JLabel attemptsLabel;

    JTextField userField;
    JTextField pinField;

    JButton login;
    JButton cancel;
    JButton register;

    public AutomatPanel(){
        inits();
        setLabels();
        setButtons();
        setTextfield();
        demoAccounts();

        this.setPreferredSize(dim);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        this.add(headLabel);
        this.add(userLabel);
        this.add(pinLabel);
        this.add(attemptsLabel);

        this.add(userField);
        this.add(pinField);

        this.add(login);
        this.add(cancel);
        this.add(register);


    }


    public void setLabels(){

        headLabel.setText("ATM 2021");
        headLabel.setFont(new Font("Arial",Font.PLAIN,36));
        FontMetrics metrics = getFontMetrics(headLabel.getFont());
        headLabel.setBounds((WIDTH - metrics.stringWidth("ATM 2021"))/2,20,200,40);

        userLabel.setText("Username");
        userLabel.setFont(new Font("Arial",Font.PLAIN,20));
        FontMetrics metrics2 = getFontMetrics(userLabel.getFont());
        userLabel.setBounds((200 - metrics2.stringWidth("Username")),125,100,40);

        pinLabel.setText("PIN");
        pinLabel.setFont(new Font("Arial",Font.PLAIN,20));
        FontMetrics metrics3 = getFontMetrics(pinLabel.getFont());
        pinLabel.setBounds((200 - metrics3.stringWidth("PIN")),175,100,40);

        attemptsLabel.setText("Attempts : " + valueOfAttempts);
        attemptsLabel.setFont(new Font("Arial",Font.PLAIN,15));
        FontMetrics metrics4 = getFontMetrics(attemptsLabel.getFont());
        attemptsLabel.setBounds((150 - metrics4.stringWidth("Attempts : " + valueOfAttempts)),300,650,40);

    }


    public void setTextfield(){

        userField.setText("");
        userField.setFont(new Font("Arial",Font.PLAIN,20));
        userField.setBounds(225,125,200,35);

        pinField.setText("");
        pinField.setFont(new Font("Arial",Font.PLAIN,20));
        pinField.setBounds(225,175,200,35);

    }

    public void setButtons(){

        login.setText("Login");
        login.setBounds(275,350,150,100);
        login.addActionListener(e -> {
            access = false;
            for(int i = 0; i<accounts.size(); i++){
                if(userField.getText().equals(accounts.get(i).getUsername())){
                    if(pinField.getText().equals(accounts.get(i).getPin())){
                        JComponent comp = (JComponent) e.getSource();
                        Window win = SwingUtilities.getWindowAncestor(comp);
                        win.dispose();
                        access = true;
                        user = accounts.get(i);
                        new UserFrame(user);
                    }
             }
            }
            if(!locked) {
                if (!access) {
                    System.out.println("Account is not included");
                    valueOfAttempts--;
                    attemptsLabel.setText("Attempts : " + valueOfAttempts);

                }
            }

            if(valueOfAttempts == 0){
                login.setEnabled(false);
                locked = true;
                attemptsLabel.setText("Attempts : Your account has been locked please contact customer service!");
            }

        });

        cancel.setText("Cancel");
        cancel.setBounds(450,350,150,100);
        cancel.addActionListener(e -> System.exit(1));


        register.setText("Register");
        register.setBounds(50,400,100,50);
        register.addActionListener(e -> {
            JComponent comp = (JComponent) e.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
            new RegisterFrame();
        });



    }

    public void inits(){

        headLabel = new JLabel();
        userLabel = new JLabel();
        pinLabel = new JLabel();
        attemptsLabel = new JLabel();

        userField = new JTextField();
        pinField = new JPasswordField();

        login = new JButton();
        cancel = new JButton();
        register = new JButton();
    }

    public void demoAccounts(){
        try {

            BufferedReader bf = new BufferedReader(new FileReader("database.txt"));
            String line;

            while ((line = bf.readLine()) != null){
               String[] user = line.split(",");
               accounts.add(new User(user[0],user[1],user[2],user[3],Double.parseDouble(user[4])));
               System.out.println();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
