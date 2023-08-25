package User;

import Automat.AutomatPanel;

import javax.swing.*;

public class UserFrame extends JFrame {

    UserPanel panel;
    User user;

    public UserFrame(User user){
        this.user = user;

        inits();
        this.add(panel);
        this.setTitle("ATM by Ismail");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void inits(){
        panel = new UserPanel(user);
    }
}
