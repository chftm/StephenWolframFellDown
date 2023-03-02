package com.foxfil.stephenwolfram;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StephenFrame extends JFrame implements ActionListener {

    JLabel stephen;
    JLabel stephenFallText;
    JButton button;
    StephenFrame(){
        JFrame frame = new JFrame("Make Stephen Wolfram fall down!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 200, 700, 500);

        this.stephen = new JLabel();
        stephen.setIcon(new ImageIcon("src/com/foxfil/stephenwolfram/Stephen.jpg"));
        Dimension size = stephen.getPreferredSize();
        stephen.setBounds(250, 100, size.width, size.height);
        frame.add(stephen);

        this.stephenFallText = new JLabel();
        stephenFallText.setText("");
        stephenFallText.setBounds(260, 350, 500, 100);
        frame.add(stephenFallText);

        this.button = new JButton("Make Stephen fall!");
        button.setBounds(50,100,100,50);
        button.setFont(new Font("Comic Sans", Font.BOLD, 25));
        button.setBackground(Color.LIGHT_GRAY);
        button.addActionListener(this);
        JPanel panel = new JPanel();
        panel.add(button);
        frame.add(panel);

        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            if (button.getText().equals("Make Stephen fall!")) {
                button.setText("Make Stephen stand!");
                stephenFallText.setText("Stephen Wolfram fell down!");
                stephen.setIcon(new ImageIcon("src/com/foxfil/stephenwolfram/rotatedStephen.jpg"));
                Dimension size = stephen.getPreferredSize();
                stephen.setBounds(220, 130, size.width, size.height);
            } else {
                button.setText("Make Stephen fall!");
                stephenFallText.setText("");
                stephen.setIcon(new ImageIcon("src/com/foxfil/stephenwolfram/Stephen.jpg"));
                Dimension size = stephen.getPreferredSize();
                stephen.setBounds(250, 100, size.width, size.height);
            }
        }
    }
}
