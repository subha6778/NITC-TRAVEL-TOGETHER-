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


        JLabel home = new JLabel("Home");
        home.setBounds(280,50,150,40);
        home.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 40));
        home.setForeground (new Color(0,0,153));
        
       //buttonsHolder.add(home);

        editProfile=new JButton("Edit Profile");
        editProfile.setBounds(100,140,200,70);
        editProfile.setBackground(Color.green);
        editProfile.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        editProfile.addMouseListener(new MouseAdapter() {
            Color color = editProfile.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = editProfile.getForeground();
               editProfile.setBackground(Color.orange); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	editProfile.setBackground(Color.green);
            }
         });


        logout=new JButton("Logout");
        logout.setBounds(100,450,100,35);
        logout.setBackground(new java.awt.Color(255, 153, 0));
        logout.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 12));



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
        buttonsHolder.setBackground(new java.awt.Color(204, 255, 255,100));




        super.basePanel.add(buttonsHolder);
        //super.basePanel.add(contentHolder);




    }



}
