package GUI;

import CommonActionListeners.LogoutListener;
import CommonActionListeners.ProfileEditListener;
import DatabaseObjectWrapper.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class LandingPage extends BaseFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//backGroundImage="assets\\t2.jpg";

	JPanel buttonsHolder, contentHolder;

    JButton editProfile,logout;    // add action listeners for these buttons in the subclasses

    Person currentPerson;
    
    JLabel  home;


    protected Person getCurrentPerson() {
        return currentPerson;
    }
    protected void setCurrentPerson(Person currentPerson)
    {
        this.currentPerson=currentPerson;
        //update listener with changed person
        ProfileEditListener listener= (ProfileEditListener) editProfile.getActionListeners()[0];
        listener.setCurrentPerson(currentPerson);
    }



    private static String buttonsHolderBackgroundImage="assets\\panel.jpg";









   public LandingPage(Person currentPerson)
    {
        this.currentPerson=currentPerson;

        //initialise buttons
        
        JLabel home = new JLabel("Welcome "+currentPerson.getFullName().split(" ")[0]);
        home.setBounds(210,50,500,40);
        home.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 35));
        home.setForeground (new Color(255, 255, 255));
        
       //buttonsHolder.add(home);

        editProfile=new JButton("Edit Profile");
        editProfile.setBounds(130,200,200,70);
        editProfile.setBackground(new Color(43, 100, 205));
        editProfile.setForeground(new Color(255, 255, 255));
        editProfile.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
        editProfile.addMouseListener(new MouseAdapter() {
            Color color = editProfile.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = editProfile.getForeground();
               editProfile.setBackground(new Color(103, 180, 255)); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	editProfile.setBackground(new Color(43, 100, 205));
            }
         });


        logout=new JButton("Logout");
        logout.setBounds(130,450,100,35);
        logout.setBackground(new java.awt.Color(144, 78, 78));
        logout.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 14));
        logout.setForeground(new Color(255,255,255));



        //common logout for librarian and user
        logout.addActionListener(new LogoutListener(this));
        editProfile.addActionListener(new ProfileEditListener(this,currentPerson));


        //initialize text panel

        contentHolder =new ImagePanel("assets\\welcome.jpg",BaseFrame.windowSize);
        contentHolder.setLayout(null);
        contentHolder.setBounds(150,0,450,600);


        //initialize left panel

        buttonsHolder=new JPanel();
        //buttonsHolder=new ImagePanel(/*buttonsHolderBackgroundImage*/,BaseFrame.windowSize);
        buttonsHolder.add(editProfile);
        buttonsHolder.add(logout);
        buttonsHolder.add(home);
        buttonsHolder.setLayout(null);
        //buttonsHolder.setBackground(new Color(148, 148, 167));
        buttonsHolder.setBounds(150,100,720,520);
        buttonsHolder.setOpaque(true);
        buttonsHolder.setFocusable(true);
        buttonsHolder.setBackground(new java.awt.Color(204, 255, 255,0));




        super.basePanel.add(buttonsHolder);
        //super.basePanel.add(contentHolder);




    }



}
