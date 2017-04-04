/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PageRankDemo;

import java.awt.*;
import static javafx.scene.text.Font.font;
import javax.swing.*;

/**
 *
 * @author DreamingTraveler
 */
public class MyFrame{
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("Please input the text you want to search:");
    private JButton button = new JButton("Click to search");
    private JTextArea textArea = new JTextArea(50,100);

    public MyFrame() {
        Container container = frame.getContentPane();
        container.setLayout(null);
        
        label.setBounds(70, 50, 390, 50);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        textArea.setBounds(150, 150, 200, 30);
        textArea.setBackground(Color.yellow);
        textArea.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        button.setBounds(150, 200, 200, 50);
        button.setFont(new Font("serif", Font.BOLD, 16));
        button.setBackground(Color.ORANGE);
        
        container.add(label);
        container.add(textArea);
        container.add(button);
        container.setBackground(Color.green);
        
        frame.setSize(600,350);
        frame.setTitle("Search Test");
        frame.setVisible(true);
    }
    
    public void getText(){
        String s = textArea.getText();
        System.out.println(s);
    }
}
