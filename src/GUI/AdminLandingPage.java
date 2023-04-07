package GUI;

import DatabaseObjectWrapper.Person;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("unused")
public class AdminLandingPage extends LandingPage  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton ViewDueGroupsButton,DeleteGroup,AddMembers,RemoveMembers;


    public AdminLandingPage(Person currentPerson) {
        super(currentPerson);
        this.setTitle("Admin");

        ViewDueGroupsButton = new JButton("View Due Groups");
        //ViewDueGroupsButton=new JButton("<html>" + "View Due" + "<br>" + "Groups" + "</html>");
        ViewDueGroupsButton.setBounds(430,200,200,70);
        ViewDueGroupsButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
        ViewDueGroupsButton.setForeground(new Color(255, 255, 255));
        ViewDueGroupsButton.addActionListener(this);
        buttonsHolder.add(ViewDueGroupsButton);
        
        
        ViewDueGroupsButton.setBackground(new Color(43, 100, 205));
        ViewDueGroupsButton.addMouseListener(new MouseAdapter() {
            Color color = ViewDueGroupsButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = ViewDueGroupsButton.getForeground();
               ViewDueGroupsButton.setBackground(new Color(103, 180, 255)); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	ViewDueGroupsButton.setBackground(new Color(43, 100, 205));
            }
         });
/*
        DeleteGroup=new JButton("<html>" + "Delete" + "<br>" + "Groups" + "</html>");
        DeleteGroup.setBounds(25,300,100,40);;
        DeleteGroup.addActionListener(this);
        buttonsHolder.add(DeleteGroup);


        AddMembers=new JButton("<html>" + "Add" + "<br>" + "Members" + "</html>");
        AddMembers.setBounds(25,400,100,40);;
        AddMembers.addActionListener(this);
        buttonsHolder.add(AddMembers);


        RemoveMembers =new JButton("<html>" + "Remove" + "<br>" + "Members" + "</html>");
        RemoveMembers.setBounds(25,500,100,40);;
        RemoveMembers.addActionListener(this);
        buttonsHolder.add(RemoveMembers);
  */
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {

    	if(e.getSource().equals(ViewDueGroupsButton))
        {
            this.dispose();
            this.setVisible(false);
            new ViewDueGroups(this);
        }

    }
}
