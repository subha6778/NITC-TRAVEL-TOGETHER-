package GUI;

import Database.DatabaseClass;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public  class AdminLogin extends UserLogin{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton userLoginButton;
	JLabel adminLabel;
	
	
	protected void initAdminLabel()
    {
    	adminLabel = new JLabel("Admin Login");
    	adminLabel.setBounds(475,60,200,40);
    	adminLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 25));
    	adminLabel.setForeground (new Color(0,0,153));
        super.basePanel.add(adminLabel);
    }
	
	
    @Override
   protected void setTitle()
    {
        setTitle("Admin Login");
    }


    
    
  protected void initLoginLabel()
    {
        loginLabel = new JLabel("Admin Id:");
        loginLabel.setBounds(350,120,100,30);
        loginLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
    	loginLabel.setForeground (new Color(0,0,153));
        super.basePanel.add(loginLabel);
    }


   private void initUserLoginButton()
    {
        userLoginButton = new JButton("Login as User");
        userLoginButton.setBounds(550,500,200,35);
        userLoginButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 12));
        super.basePanel.add(userLoginButton);
        userLoginButton.addActionListener(this);
        userLoginButton.setBackground(Color.green);
        userLoginButton.addMouseListener(new MouseAdapter() {
            Color color = userLoginButton.getForeground();
            public void mouseEntered(MouseEvent me) {
               color = userLoginButton.getForeground();
               userLoginButton.setBackground(Color.orange); // change the color to green when mouse over a button
            }
            public void mouseExited(MouseEvent me) {
            	userLoginButton.setBackground(Color.green);
            }
         });
    }
    AdminLogin()
    {
        super();

        initUserLoginButton();
        initAdminLabel();


        super.basePanel.remove(signup);
        super.basePanel.remove(adminLogin);
        super.basePanel.remove(newLabel);
        super.basePanel.remove(userLabel);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(userLoginButton))
        {
            new UserLogin();
            this.setVisible(false);
        }

        if(e.getSource().equals(submit))
        {
            String userName = loginIdTextBox.getText();        //get user entered username from the textField1
            @SuppressWarnings("deprecation")
			String passValue = passwordTextBox.getText();        //get user entered pasword from the textField2

            //check whether the credentials are authentic or not
            try {
                if (  !userName.equals("") && DatabaseClass.getDataAccessObject().adminLoginValidate(userName,passValue))
                {
                    //if authentic, navigate user to a new page
                    //create instance of the GUI.NewPage

                    this.setVisible(false);
                    new AdminLandingPage(DatabaseClass.getDataAccessObject().getAdmin(userName));

                } else
                    JOptionPane.showMessageDialog(basePanel,"Error ! Username or Password is invalid");
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        if(e.getSource().equals(showPassword))
        {
            if (showPassword.isSelected()) {
                passwordTextBox.setEchoChar((char)0); //password = JPasswordField
            } else {
                passwordTextBox.setEchoChar('*');
            }
        }
    }
}
