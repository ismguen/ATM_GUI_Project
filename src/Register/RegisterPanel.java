package Register;

import Automat.AutomatFrame;
import Automat.AutomatPanel;
import User.User;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterPanel extends JPanel {

        static final int WIDTH = 700;
        static final int HEIGHT = 500;
        static final Dimension dim = new Dimension(WIDTH,HEIGHT);
        User user;

        JLabel nameLabel;
        JLabel lastnameLabel;
        JLabel usernameLabel;
        JLabel pinLabel;
        JLabel amountLabel;

        JTextField nameField;
        JTextField lastnameField;
        JTextField usernameField;
        JTextField pinField;
        JTextField amountField;


        JButton register;
        JButton cancel;
        JButton homemenu;


    public RegisterPanel(){
            inits();
            setLabels();
            setButtons();
            setTextfield();

            this.add(nameLabel);
            this.add(lastnameLabel);
            this.add(usernameLabel);
            this.add(pinLabel);
            this.add(amountLabel);

            this.add(nameField);
            this.add(lastnameField);
            this.add(usernameField);
            this.add(pinField);
            this.add(amountField);

            this.add(register);
            this.add(cancel);
            this.add(homemenu);

            this.setPreferredSize(dim);
            this.setLayout(null);
            this.setBackground(Color.lightGray);



        }


        public void setLabels(){

            nameLabel.setText("Name");
            nameLabel.setFont(new Font("Arial",Font.PLAIN,22));
            FontMetrics metrics = getFontMetrics(nameLabel.getFont());
            nameLabel.setBounds((200 - metrics.stringWidth("Name")),70,400,50);

            lastnameLabel.setText("Lastname");
            lastnameLabel.setFont(new Font("Arial",Font.PLAIN,22));
            FontMetrics metrics2 = getFontMetrics(lastnameLabel.getFont());
            lastnameLabel.setBounds((200 - metrics2.stringWidth("Lastname")),130,400,50);

            usernameLabel.setText("Username");
            usernameLabel.setFont(new Font("Arial",Font.PLAIN,22));
            FontMetrics metrics3 = getFontMetrics(usernameLabel.getFont());
            usernameLabel.setBounds((200 - metrics3.stringWidth("Username")),190,400,50);

            pinLabel.setText("PIN");
            pinLabel.setFont(new Font("Arial",Font.PLAIN,22));
            FontMetrics metrics4 = getFontMetrics(pinLabel.getFont());
            pinLabel.setBounds((200 - metrics4.stringWidth("PIN")),250,400,50);

            amountLabel.setText("Amount (€)");
            amountLabel.setFont(new Font("Arial",Font.PLAIN,22));
            FontMetrics metrics5 = getFontMetrics(amountLabel.getFont());
            amountLabel.setBounds((200 - metrics5.stringWidth("Amount (€)")),310,400,50);


        }


        public void setTextfield(){

            nameField.setText("");
            nameField.setFont(new Font("Arial",Font.PLAIN,22));
            nameField.setBounds(250,75,220,40);


            lastnameField.setText("");
            lastnameField.setFont(new Font("Arial",Font.PLAIN,22));
            lastnameField.setBounds(250,135,220,40);



            usernameField.setText("");
            usernameField.setFont(new Font("Arial",Font.PLAIN,22));
            usernameField.setBounds(250,195,220,40);



            pinField.setText("");
            pinField.setFont(new Font("Arial",Font.PLAIN,22));
            pinField.setBounds(250,255,220,40);



            amountField.setText("");
            amountField.setFont(new Font("Arial",Font.PLAIN,22));
            amountField.setBounds(250,315,220,40);


        }

        public void setButtons(){

            register.setText("Register");
            register.setBounds(275,370,150,100);
            register.addActionListener(e -> {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                registUser();
                new AutomatFrame();
            });

            cancel.setText("Cancel");
            cancel.setBounds(450,370,150,100);
            cancel.addActionListener(e -> System.exit(1));


            homemenu.setText("Home");
            homemenu.setBounds(50,400,100,50);
            homemenu.addActionListener(e -> {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                new AutomatFrame();
            });



        }

        public void inits(){


            nameLabel = new JLabel();
            lastnameLabel = new JLabel();
            usernameLabel = new JLabel();
            pinLabel = new JLabel();
            amountLabel = new JLabel();

            nameField = new JTextField();
            lastnameField = new JTextField();
            usernameField = new JTextField();
            pinField = new JPasswordField();
            amountField = new JTextField();


            register = new JButton();
            cancel = new JButton();
            homemenu = new JButton();

        }

        public void registUser(){

        try {
            File log = new File("database.txt");

            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.write(nameField.getText() + "," + lastnameField.getText() + "," + usernameField.getText() + "," + pinField.getText() + "," + amountField.getText());
            out.write(System.getProperty("line.separator"));
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        }

    }


