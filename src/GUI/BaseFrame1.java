package GUI;

import javax.swing.*;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

@SuppressWarnings({ "unused", "serial" })
abstract class BaseFrame1 extends JFrame implements ActionListener {

    JPanel basePanel;

    protected static String backGroundImage="assets\\map1.jpg";

    protected static Dimension windowSize=new Dimension(1070,700);

    BaseFrame1()
    {

        basePanel = new ImagePanel(backGroundImage,windowSize);

        
        basePanel.setLayout(null);
        



        this.add(basePanel);
        basePanel.setLocation(230,50);
        //this.setBounds(200,100,600,600);
        this.setLocation(230,50);
        this.setMaximumSize(new Dimension(600,800));
        this.setMinimumSize(new Dimension(600,600));
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

