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



        ViewGroupsButton =new JButton("<html>" + "View Existing" + "<br>"+ "Groups" + "</html>");
        ViewGroupsButton.setBounds(100,270,200,70);
        ViewGroupsButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        ViewGroupsButton.addActionListener(this);
        ViewGroupsButton.setRolloverEnabled(true);
        buttonsHolder.add(ViewGroupsButton);
        ViewGroupsButton.setBackground(Color.green);
        ViewGroupsButton.addMouseListener(new MouseAdapter() {
            Color color = ViewGroupsButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = ViewGroupsButton.getForeground();
               ViewGroupsButton.setBackground(Color.orange); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	ViewGroupsButton.setBackground(Color.green);
            }
         });
        
        ViewMyGroupButton =new JButton("<html>" + "View My" + "<br>"+ "Group" + "</html>");
        ViewMyGroupButton.setBounds(400,140,200,70);
        ViewMyGroupButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        ViewMyGroupButton.addActionListener(this);
        ViewMyGroupButton.setRolloverEnabled(true);
        buttonsHolder.add(ViewMyGroupButton);
        ViewMyGroupButton.setBackground(Color.green);
        ViewMyGroupButton.addMouseListener(new MouseAdapter() {
            Color color = ViewMyGroupButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = ViewMyGroupButton.getForeground();
               ViewMyGroupButton.setBackground(Color.orange); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	ViewMyGroupButton.setBackground(Color.green);
            }
         });
        
        
        
        
        
        


        CreategroupBotton=new  JButton("<html>" + "Create" + "<br>" + "Groups" + "</html>");
        CreategroupBotton.setBounds(400,270,200,70);
        CreategroupBotton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        CreategroupBotton.addActionListener(this);
        CreategroupBotton.setRolloverEnabled(true);
        buttonsHolder.add(CreategroupBotton);
        CreategroupBotton.setBackground(Color.green);
        CreategroupBotton.addMouseListener(new MouseAdapter() {
            Color color = CreategroupBotton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = CreategroupBotton.getForeground();
               CreategroupBotton.setBackground(Color.orange); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	CreategroupBotton.setBackground(Color.green);
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
