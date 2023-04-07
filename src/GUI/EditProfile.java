package GUI;

import Database.DatabaseClass;
//import DatabaseObjectWrapper.Customer;
import DatabaseObjectWrapper.User;
//import DatabaseObjectWrapper.Librarian;
import DatabaseObjectWrapper.Admin;
import DatabaseObjectWrapper.Person;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("unused")
public class EditProfile extends SignupForm   {



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	LandingPage lastPage;
	JLabel signNam;

    Person currentPerson;


   public EditProfile(LandingPage lastPage,  Person currentPerson)
    {

        super(lastPage);
        
        this.currentPerson=currentPerson;
        super.basePanel.remove(signName);
        initedit();
        this.lastPage=lastPage;

        //to show current user details in textboxes
        updateTextBoxesAndLabels();







    }

    private void updateTextBoxesAndLabels(){

       //cannot edit username as it is primary key
       enterUsername.setText(currentPerson.getUserName());
       enterName.setText(currentPerson.getFullName());
       enterPassword.setText(currentPerson.getPassword());
       enterEmail.setText(currentPerson.getEmail());
       enterAge.setText(currentPerson.getAge());
       enterAddress.setText(currentPerson.getAddress());
       enterGender.setText(currentPerson.getGender());
       entermobile_no.setText(currentPerson.getmobile_no());
      // password.setText("Password");


       //using the button in signup form to submit new details to database
       submit.setText("Update");

    }

    @Override
    protected void initTitle()
    {
        this.setTitle("Edit Profile");
    }

    private void initedit()
    {
        signNam=new JLabel("Update Profile");
        signNam.setBounds(100, 100,300, 40);
        signNam.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
        signNam.setForeground (new Color(255,255,255));
        super.basePanel.add(signNam);
    }






    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource().equals(back))
        {

            this.setVisible(false);
            lastPage.dispose();
            lastPage.setVisible(true);
        }

        if(e.getSource().equals(submit))
        {
            String newUserName= enterUsername.getText();
            String password= 	enterPassword.getText();
            String fullName= 	enterName.getText();
            String email=    	enterEmail.getText();
            String age=			enterAge.getText();
            String address=		enterAddress.getText();
            String gender=		enterGender.getText();
            String mob_no=		entermobile_no.getText();
            

            if(newUserName.equals("") || password.equals("") || fullName.equals("") || email.equals("")||address.equals("")||gender.equals("")||mob_no.equals(""))
            {
                JOptionPane.showMessageDialog(basePanel,"Error! Some fields are empty");
                return;
            }
            if(newUserName.trim().length()!=9)
            {
            	JOptionPane.showMessageDialog(basePanel,"Invalid Username");
                return;
            	
            }
            int size = email.length();
            if(size<12) {
            	JOptionPane.showMessageDialog(basePanel,"Invalid email address");
                return;
            }
            String s="";
            for(int i=size-11;i<size;i++)
            {
            	s=s + Character.toString(email.charAt(i));
            }
            if(!s.equals("@nitc.ac.in"))
            {
            	JOptionPane.showMessageDialog(basePanel,"Invalid email address");
                return;
            }
            if(password.length()<8)
            {
            	JOptionPane.showMessageDialog(basePanel, "Password should be atleat 8 characters");
            	return;
            }
            if(mob_no.length()!=10){
            	
            	JOptionPane.showMessageDialog(basePanel,"mobile Number should be 10 digits");
                return;
            }
            else
            {
             
            	System.out.print("cuurr"+currentPerson);
            	if(currentPerson instanceof User)
                {
                    if (DatabaseClass.getDataAccessObject().updateUser(currentPerson.getUserName(), newUserName, password, fullName, email,age,address,gender,mob_no)) {
                        this.dispose();
                    
                        Person person=DatabaseClass.getDataAccessObject().getUser(newUserName);
                        lastPage.setCurrentPerson(person);
                        lastPage.setVisible(true);

                    } else
                        JOptionPane.showMessageDialog(basePanel,"This Username or Email Id already exists");
                }
             
            	else if(currentPerson instanceof Admin)
                {
                    if (DatabaseClass.getDataAccessObject().updateAdmin(currentPerson.getUserName(), newUserName, password, fullName, email,age,address,gender,mob_no)) {
                        this.dispose();
                      
                        Person person=DatabaseClass.getDataAccessObject().getAdmin(newUserName);
                        lastPage.setCurrentPerson(person);
                        lastPage.setVisible(true);

                    } else
                        JOptionPane.showMessageDialog(basePanel,"This Username or Email Id already exists");
                }
            }
        }
        if(e.getSource().equals(showPassword))
        {
            if (showPassword.isSelected()) {
                enterPassword.setEchoChar((char)0); //password = JPasswordField
            } else {
                enterPassword.setEchoChar('*');
            }
        }
    }

}

