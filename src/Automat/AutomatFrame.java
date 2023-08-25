package Automat;

import javax.swing.*;

public class AutomatFrame extends JFrame {

       AutomatPanel panel;

     public AutomatFrame(){
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
         panel = new AutomatPanel();
    }

}
