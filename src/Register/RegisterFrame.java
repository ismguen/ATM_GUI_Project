package Register;

import Automat.AutomatPanel;

import javax.swing.*;

public class RegisterFrame extends JFrame {

    RegisterPanel panel;

    public RegisterFrame(){
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
        panel = new RegisterPanel();
    }

}
