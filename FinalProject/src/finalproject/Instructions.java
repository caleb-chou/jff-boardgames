package finalproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Instructions extends JFrame implements ActionListener{
    
    public Instructions(String[] instructions, String[] titles, ImageIcon[] titleIcons, boolean[] hasIcon, int[] titleNum){
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("Instructions");
        setIconImage(Resources.getImage("Info_Icon.png"));
        setUndecorated(true);
        setAlwaysOnTop(true);
        
        JLabel header = new JLabel("              How To Play              ");
        header.setFont(new Font("", Font.BOLD, 30));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.setHorizontalAlignment(JTextField.CENTER);
        
        customButton ok = new customButton("Abort_Button", 50);
        ok.setText(" I understand");
        ok.setFont(new Font("", Font.PLAIN, 30));
        ok.setBorder(null);
        ok.setAlignmentX(CENTER_ALIGNMENT);
        ok.addActionListener(this);
        
        JPanel[] squares = new JPanel[titles.length];
        
            JLabel[] titlePanels = new JLabel[titles.length];
            for(int i = 0; i < titlePanels.length; i++){
                titlePanels[i] = new JLabel();
                titlePanels[i].setFont(new Font("", Font.BOLD, 30));
                if(hasIcon[i]) {
                    titlePanels[i].setIcon(titleIcons[i]);
                    titlePanels[i].setText(" " + titles[i]);
                } else {
                    titlePanels[i].setText(titles[i]);
                }
            }
            
            JPanel[] iPanels = new JPanel[titles.length];
            int total = 0;
            for(int i = 0; i < iPanels.length; i++){
                iPanels[i] = new JPanel();
                iPanels[i].setLayout(new BoxLayout(iPanels[i], BoxLayout.PAGE_AXIS));
                
                JLabel[] iLabels = new JLabel[titleNum[i]];
                for(int j = 0; j < iLabels.length; j++){
                    iLabels[j] = new JLabel(instructions[total]);
                    iLabels[j].setFont(new Font("", Font.PLAIN, 20));
                    iPanels[i].add(iLabels[j]);
                    total++;
                }
            }
        
        
        add(new JLabel(" "));
        add(header);
        add(new JLabel(" "));
        for(int i = 0; i < squares.length; i++){
            squares[i] = new JPanel();
            squares[i].setLayout(new BoxLayout(squares[i], BoxLayout.LINE_AXIS));
            squares[i].add(titlePanels[i]);
            squares[i].add(new JLabel("             "));
            squares[i].add(iPanels[i]);
            squares[i].setAlignmentX(Component.LEFT_ALIGNMENT);
            add(squares[i]);
            add(new JLabel(" "));
        }
        add(new JLabel(" "));
        add(ok);
        pack();
        setBounds(0, 0, getSize().width + 50, getSize().height + 50);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        dispose();
    }
    
}
