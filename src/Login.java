
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
 class LoginFrame extends JFrame implements ActionListener {
 
	 /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	Container container=getContentPane();
	    JLabel userLabel=new JLabel("USERNAME");
	    JLabel passwordLabel=new JLabel("PASSWORD");
	    JTextField userTextField=new JTextField();
	    JPasswordField passwordField=new JPasswordField();
	    JButton loginButton=new JButton("LOGIN");
	    JButton resetButton=new JButton("RESET");
	    JCheckBox showPassword=new JCheckBox("Show Password");
 
    LoginFrame()
    {
        //Calling setLayoutManger() method inside the constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }
   public void setLayoutManager()
   {
       //Setting layout manager of Container to null
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
       userLabel.setBounds(50,150,100,30);
       passwordLabel.setBounds(50,220,100,30);
       userTextField.setBounds(150,150,150,30);
       passwordField.setBounds(150,220,150,30);
       showPassword.setBounds(150,250,150,30);
       loginButton.setBounds(50,300,100,30);
       resetButton.setBounds(200,300,100,30);
       
 
   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
       container.add(userLabel);
       container.add(passwordLabel);
       container.add(userTextField);
       container.add(passwordField);
       container.add(showPassword);
       container.add(loginButton);
       container.add(resetButton);
   }
 
    public void addActionEvent()
    {
       //adding Action listener to components
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
  
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) 
    {
    	 if (e.getSource() == loginButton)
    	 {
             String userText;
             String pwdText;
             userText = userTextField.getText();
             pwdText = passwordField.getText();
             if (userText.equalsIgnoreCase("mehtab") && pwdText.equalsIgnoreCase("12345"))
             {
                 JOptionPane.showMessageDialog(this, "Login Successful");
             } 
             else 
             {
                 JOptionPane.showMessageDialog(this, "Invalid Username or Password");
             }
  
         }
         //Coding Part of RESET button
         if (e.getSource() == resetButton) 
         {
             userTextField.setText("");
             passwordField.setText("");
         }
        //Coding Part of showPassword JCheckBox
         if (e.getSource() == showPassword)
         {
             if (showPassword.isSelected())
             {
                 passwordField.setEchoChar((char) 0);
             }
             else
             {
                 passwordField.setEchoChar('*');
             }
  
  
         }
     }
 
    }
 
public class Login {
    public static void main(String[] a){
        LoginFrame frame=new LoginFrame();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
   
 
}