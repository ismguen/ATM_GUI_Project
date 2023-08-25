package User;

import Automat.AutomatFrame;
import Automat.AutomatPanel;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPanel extends JPanel {

    static final int WIDTH = 700;
    static final int HEIGHT = 500;
    static final Dimension dim = new Dimension(WIDTH,HEIGHT);
    User user;

    JLabel userLabel;
    JLabel amountLabel;
    JLabel cashLabel;

    JTextField amountField;
    JTextField cashField;

    JButton cashIn;
    JButton cashOut;
    JButton homemenu;

    public UserPanel(User user){
        this.user = user;

        inits();
        setLabels();
        setButtons();
        setTextfield();

        this.setPreferredSize(dim);
        this.setLayout(null);
        this.setBackground(Color.lightGray);

        this.add(userLabel);
        this.add(amountLabel);
        this.add(cashLabel);

        this.add(amountField);
        this.add(cashField);
        this.add(cashLabel);

        this.add(cashIn);
        this.add(cashOut);
        this.add(homemenu);

    }


    public void setLabels(){

        userLabel.setText(user.getName() + " " + user.getLastname());
        userLabel.setFont(new Font("Arial",Font.PLAIN,36));
        FontMetrics metrics = getFontMetrics(userLabel.getFont());
        userLabel.setBounds((WIDTH - metrics.stringWidth(user.getName() + " " + user.getLastname()))/2,20,400,50);

        amountLabel.setText("Amount (€)");
        amountLabel.setFont(new Font("Arial",Font.PLAIN,42));
        FontMetrics metrics2 = getFontMetrics(amountLabel.getFont());
        amountLabel.setBounds(((WIDTH - metrics2.stringWidth("Amount (€)"))/2)-200,175,400,50);

        cashLabel.setText("Deposit/Withdraw");
        cashLabel.setFont(new Font("Arial",Font.PLAIN,26));
        FontMetrics metrics3 = getFontMetrics(cashLabel.getFont());
        cashLabel.setBounds(((WIDTH - metrics3.stringWidth("Deposit/Withdraw")) / 2)-200, 235, 400, 50);



    }


    public void setTextfield(){

        if(user.getAmount()<0){
            amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
            amountField.setFont(new Font("Arial",Font.PLAIN,40));
            amountField.setForeground(Color.red);
            FontMetrics metrics1 = getFontMetrics(amountLabel.getFont());
            amountField.setBounds(((WIDTH - metrics1.stringWidth("Amount"))/2),175,300,50);
            amountField.setEditable(false);

        }else {
            amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
            amountField.setFont(new Font("Arial", Font.PLAIN, 40));
            amountField.setForeground(Color.green);
            FontMetrics metrics2 = getFontMetrics(amountLabel.getFont());
            amountField.setBounds(((WIDTH - metrics2.stringWidth("Amount")) / 2), 175, 300, 50);
            amountField.setEditable(false);

        }

        cashField.setText("");
        cashField.setFont(new Font("Arial", Font.PLAIN, 40));
        FontMetrics metrics2 = getFontMetrics(cashField.getFont());
        cashField.setBounds(((WIDTH - metrics2.stringWidth("Amount")) / 2), 235, 300, 50);

    }

    public void setButtons(){

        cashIn.setText("Cash-In");
        cashIn.setBounds(275,350,150,100);
        cashIn.addActionListener(e -> {
                        try {
                            if(Double.parseDouble(cashField.getText()) < 0){
                                JOptionPane.showMessageDialog(null,"Du kannst keine negative Zahl Einzahlen!","Error 401",JOptionPane.ERROR_MESSAGE);
                            }else{
                                if (user.getAmount() + Double.parseDouble(cashField.getText()) >= 0) {
                                    user.setAmount(user.getAmount() + Double.parseDouble(cashField.getText()));
                                    amountField.setForeground(Color.green);
                                    amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
                                } else {
                                    user.setAmount(user.getAmount() + Double.parseDouble(cashField.getText()));
                                    amountField.setForeground(Color.red);
                                    amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
                                }
                            }
                        }catch (NumberFormatException fail){
                            JOptionPane.showMessageDialog(null,"Gib deine Einzahlung/Auszahlung als Zahl ein!","Error 404",JOptionPane.ERROR_MESSAGE);
                        }

                        try {
                            Path p = Paths.get(".", "database.txt");
                            Path tempFile = Files.createTempFile(p.getParent(), "usersTemp", ".txt");
                            try (BufferedReader reader = Files.newBufferedReader(p);
                                 BufferedWriter writer = Files.newBufferedWriter(tempFile)) {

                                String line;
                                // copy everything until the id is found
                                while ((line = reader.readLine()) != null) {
                                    System.out.println(line);
                                    String[] usertext = line.split(",");
                                    if (usertext[2].equals(user.getUsername())) {
                                        System.out.println(line);
                                        usertext[4] = String.valueOf(user.getAmount());
                                    }
                                    writer.write(String.join(",", usertext));
                                    writer.newLine();
                                }


                            } catch (IOException fail) {
                                fail.printStackTrace();
                            }
                            // copy new file & delete temporary file
                            Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
                            Files.delete(tempFile);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                });





        cashOut.setText("Cash-Out");
        cashOut.setBounds(450,350,150,100);
        cashOut.addActionListener(e -> {

                try {
                    if(Double.parseDouble(cashField.getText()) < 0){
                        JOptionPane.showMessageDialog(null,"Du kannst keine negative Zahl Auszahlen!","Error 402",JOptionPane.ERROR_MESSAGE);
                    }else {
                        if (user.getAmount() - Double.parseDouble(cashField.getText()) >= 0) {
                            user.setAmount(user.getAmount() - Double.parseDouble(cashField.getText()));
                            amountField.setForeground(Color.green);
                            amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
                        } else {
                            user.setAmount(user.getAmount() - Double.parseDouble(cashField.getText()));
                            amountField.setForeground(Color.red);
                            amountField.setText(String.format("%1$,.2f" + " €", user.getAmount()));
                        }
                    }
                }catch (NumberFormatException fail){
                    JOptionPane.showMessageDialog(null,"Gib deine Einzahlung/Auszahlung als Zahl ein!","Error 404",JOptionPane.ERROR_MESSAGE);
                }

                try {
                    Path p = Paths.get(".", "database.txt");
                    Path tempFile = Files.createTempFile(p.getParent(), "usersTemp", ".txt");
                    try (BufferedReader reader = Files.newBufferedReader(p);
                         BufferedWriter writer = Files.newBufferedWriter(tempFile)) {

                        String line;
                        // copy everything until the id is found
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                            String[] usertext = line.split(",");
                            if (usertext[2].equals(user.getUsername())) {
                                System.out.println(line);
                                usertext[4] = String.valueOf(user.getAmount());
                            }
                            writer.write(String.join(",", usertext));
                            writer.newLine();
                        }


                    } catch (IOException fail) {
                        fail.printStackTrace();
                    }
                    // copy new file & delete temporary file
                    Files.copy(tempFile, p, StandardCopyOption.REPLACE_EXISTING);
                    Files.delete(tempFile);
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }


        });

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

        userLabel = new JLabel();
        amountLabel = new JLabel();
        cashLabel = new JLabel();

        amountField = new JTextField();
        cashField = new JTextField();

        cashIn = new JButton();
        cashOut = new JButton();
        homemenu = new JButton();

    }

}
