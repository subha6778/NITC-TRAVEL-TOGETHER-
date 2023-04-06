package GUI;

import javax.swing.*;

import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

@SuppressWarnings({ "unused", "serial" })
abstract class BaseFrame extends JFrame implements ActionListener {

    JPanel basePanel;

    protected static String backGroundImage="assets\\t1.jpg";

    protected static Dimension windowSize=new Dimension(1070,700);

    BaseFrame()
    {

        basePanel = new ImagePanel(backGroundImage,windowSize);


        basePanel.setLayout(null);




        this.add(basePanel);
        //this.setBounds(200,100,600,600);
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

