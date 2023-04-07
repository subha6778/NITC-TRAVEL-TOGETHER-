package GUI;

import DatabaseObjectWrapper.Person;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserLandingPage extends LandingPage  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton ViewGroupsButton,CreategroupBotton,ViewMyGroupButton;

    JLabel welcome;


    UserLandingPage(Person currentPerson) {
        super(currentPerson);
        this.setTitle("Welcome");


        //if adding other components after frame is displayed use setvisible (true),repaint() after adding them;
        // to update a jcomponent first remove it within try catch(Nullptr) then add it to panel



        ViewGroupsButton =new JButton("<html>" + "View Available" + "<br>"+ "Groups" + "</html>");
        ViewGroupsButton.setBounds(130,330,200,70);
        ViewGroupsButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
        ViewGroupsButton.addActionListener(this);
        ViewGroupsButton.setRolloverEnabled(true);
        ViewGroupsButton.setForeground(new Color(255, 255, 255));
        buttonsHolder.add(ViewGroupsButton);
        ViewGroupsButton.setBackground(new Color(43, 100, 205));
        ViewGroupsButton.addMouseListener(new MouseAdapter() {
            Color color = ViewGroupsButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = ViewGroupsButton.getForeground();
               ViewGroupsButton.setBackground(new Color(103, 180, 255)); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	ViewGroupsButton.setBackground(new Color(43, 100, 205));
            }
         });
        
        ViewMyGroupButton =new JButton("<html>" + "View My" + "<br>"+ "Group" + "</html>");
        ViewMyGroupButton.setBounds(430,200,200,70);
        ViewMyGroupButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
        ViewMyGroupButton.addActionListener(this);
        ViewMyGroupButton.setRolloverEnabled(true);
        ViewMyGroupButton.setForeground(new Color(255, 255, 255));
        buttonsHolder.add(ViewMyGroupButton);
        ViewMyGroupButton.setBackground(new Color(43, 100, 205));
        ViewMyGroupButton.addMouseListener(new MouseAdapter() {
            Color color = ViewMyGroupButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = ViewMyGroupButton.getForeground();
               ViewMyGroupButton.setBackground(new Color(103, 180, 255)); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	ViewMyGroupButton.setBackground(new Color(43, 100, 205));
            }
         });
        
        
        
        
        
        


        CreategroupBotton=new  JButton("<html>"+ "Create " + "Groups" + "</html>");
        CreategroupBotton.setBounds(430,330,200,70);
        CreategroupBotton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
        CreategroupBotton.addActionListener(this);
        CreategroupBotton.setRolloverEnabled(true);
        CreategroupBotton.setForeground(new Color(255, 255, 255));
        buttonsHolder.add(CreategroupBotton);
        CreategroupBotton.setBackground(new Color(43, 100, 205));
        CreategroupBotton.addMouseListener(new MouseAdapter() {
            Color color = CreategroupBotton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = CreategroupBotton.getForeground();
               CreategroupBotton.setBackground(new Color(103, 180, 255)); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	CreategroupBotton.setBackground(new Color(43, 100, 205));
            }
         });
      



   






    }








    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource().equals(CreategroupBotton))
        {
            this.dispose();
            this.setVisible(false);
            new CreateGroup(this,currentPerson);
        }
    	if(e.getSource().equals(ViewGroupsButton))
        {
            this.dispose();
            this.setVisible(false);
            new ViewExistingGroup(this,currentPerson);
        }
    	if(e.getSource().equals(ViewMyGroupButton))
        {
            this.dispose();
            this.setVisible(false);
            new ViewMyGroup(this,currentPerson);
        }



    }
}
