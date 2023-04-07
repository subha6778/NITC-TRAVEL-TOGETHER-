package GUI;

import javax.swing.*;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

@SuppressWarnings({ "unused", "serial" })
abstract class BaseFrame extends JFrame implements ActionListener {

    JPanel basePanel;
//    JButton cross;

    protected static String backGroundImage="assets\\deb1.jpg";

    protected static Dimension windowSize=new Dimension(1070,700);

    BaseFrame()
    {

        basePanel = new ImagePanel(backGroundImage,windowSize);


        



        this.add(basePanel);
        //this.setBounds(200,100,600,600);
        //this.setUndecorated(true);
        this.setMaximumSize(new Dimension(600,800));
        this.setMinimumSize(new Dimension(600,600));
        this.setResizable(false);
        this.setLocation(230,50);
        this.setVisible(true);
        
        this.pack();
        basePanel.setLayout(null);
        /*cross = new JButton("close");
        cross.setBounds(1040,0,30,40);
        basePanel.add(cross);*/

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    	    }
    /*
    private void initSignupButton()
    {
        signup=new JButton("Sign Up");
        signup.setBounds(490,350,260,30);
        signup.setBackground(new Color(255, 255, 153));
        signup.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 12));
        //signup.setForeground(Color.yellow);
        super.basePanel.add(signup);
        signup.addActionListener(this);
        signup.addMouseListener(new MouseAdapter() {
            Color color = signup.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = signup.getForeground();
               signup.setBackground(Color.green); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
         	   signup.setBackground(new Color(255, 255, 153));
            }
         });
    }*/
}

