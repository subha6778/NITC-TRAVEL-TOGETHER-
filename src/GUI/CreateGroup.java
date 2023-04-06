package GUI;
import com.toedter.calendar.JDateChooser;

import CommonActionListeners.BackButtonListener;
import Database.DatabaseClass;
import DatabaseObjectWrapper.Person;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

@SuppressWarnings({ "unused", "serial" })
public class CreateGroup extends BaseFrame  {

    LandingPage lastPage;

    JLabel signName, groupNameLabel,sourceLabel,destinationLabel,dateLabel,depttimeLabel,groupIdLabel,arrivaltimeLabel,arrivaldateLabel,capacityLabel,mealpriceLabel;

    JTextField groupNameTextField,sourceTextField,destinationTextField,dateTextField,depttimeTextField,groupIdTextField,arrivaltimeTextField,capacityTextField,mealpriceTextField;

    private JComboBox<String> mealCheckbox;
    String[] mealoptions= {"Want meal","Dont want meal"};
    JButton createButton,backButton;
    JDateChooser dateChooser, arrivaldateTextField;
    //++TimePicker time;
    HashMap<String,String> hm = new HashMap<>();
    
   Person currentPerson;

  public CreateGroup(LandingPage lastPage,Person currentPerson)
    {
	  hm.put("Jan","01");
	  hm.put("Feb","02");
	  hm.put("Mar","03");
	  hm.put("Apr","04");
	  hm.put("May","05");
	  hm.put("Jun","06");
	  hm.put("Jul","07");
	  hm.put("Aug","08");
	  hm.put("Sep","09");
	  hm.put("Oct","10");
	  hm.put("Nov","11");
	  hm.put("Dec","12");
	  
       this.currentPerson=currentPerson;
        this.setTitle("CREATE Group");
        this.lastPage=lastPage;
        
        signName=new JLabel("Create New Group");
        signName.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
        signName.setForeground (Color.orange);
        signName.setBounds(250,50,300,40);
        super.basePanel.add(signName);

        groupIdLabel=new JLabel("Group ID");
        groupIdLabel.setBounds(250,110,150,30);
        super.basePanel.add(groupIdLabel);
        groupIdLabel.setForeground (Color.orange);
        
        groupIdTextField=new JTextField();
        groupIdTextField.setBounds(350,110,250,30);
        super.basePanel.add(groupIdTextField);
        
        
        groupNameLabel=new JLabel("Group Name");
        groupNameLabel.setBounds(250,150,150,30);
        super.basePanel.add(groupNameLabel);
        groupNameLabel.setForeground (Color.orange);
        
        groupNameTextField=new JTextField();
        groupNameTextField.setBounds(350,150,250,30);
        super.basePanel.add(groupNameTextField);


        sourceLabel=new JLabel("SOURCE");
        sourceLabel.setBounds(250,190,150,30);
        super.basePanel.add(sourceLabel);
        sourceLabel.setForeground (Color.orange);

        sourceTextField=new JTextField();
        sourceTextField.setBounds(350,190,250,30);
        super.basePanel.add(sourceTextField);


        destinationLabel=new JLabel("DESTINATION");
        destinationLabel.setBounds(250,230,150,30);
        super.basePanel.add(destinationLabel);
        destinationLabel.setForeground (Color.orange);

        destinationTextField=new JTextField();
        destinationTextField.setBounds(350,230,250,30);
        super.basePanel.add(destinationTextField);


        dateLabel=new JLabel("Journey Date");
        dateLabel.setBounds(250,270,150,30);
        super.basePanel.add(dateLabel);
        dateLabel.setForeground (Color.orange);
        
        //dateTextField=new JTextField();
        //dateTextField.setBounds(350,270,250,30);
        //super.basePanel.add(dateTextField);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(350,270,250,30);
        super.basePanel.add(dateChooser);

        depttimeLabel=new JLabel("Departure Time");
        depttimeLabel.setBounds(250,310,250,30);
        super.basePanel.add(depttimeLabel);
        depttimeLabel.setForeground (Color.orange);

        depttimeTextField=new JTextField();
        depttimeTextField.setBounds(350,310,250,30);
        super.basePanel.add(depttimeTextField);
        
        arrivaldateLabel=new JLabel("Arrival Date");
        arrivaldateLabel.setBounds(250,350,150,30);
        super.basePanel.add(arrivaldateLabel);
        arrivaldateLabel.setForeground (Color.orange);
        
        
        arrivaldateTextField=new JDateChooser();
        arrivaldateTextField.setBounds(350,350,250,30);
        super.basePanel.add(arrivaldateTextField);
        
        arrivaltimeLabel=new JLabel("Arrival Time");
        arrivaltimeLabel.setBounds(250,390,150,30);
        super.basePanel.add(arrivaltimeLabel);
        arrivaltimeLabel.setForeground (Color.orange);
        
        arrivaltimeTextField=new JTextField();
        arrivaltimeTextField.setBounds(350,390,250,30);
        super.basePanel.add(arrivaltimeTextField);
        
        capacityLabel=new JLabel("Capacity");
        capacityLabel.setBounds(250,430,150,30);
        super.basePanel.add(capacityLabel);
        capacityLabel.setForeground (Color.orange);
        
        capacityTextField=new JTextField();
        capacityTextField.setBounds(350,430,250,30);
        super.basePanel.add(capacityTextField);
        
        mealpriceLabel=new JLabel("Meal Price");
        mealpriceLabel.setBounds(250,470,150,30);
        super.basePanel.add( mealpriceLabel);
        mealpriceLabel.setForeground (Color.orange);
        
        mealpriceTextField=new JTextField();
        mealpriceTextField.setBounds(350,470,250,30);
        super.basePanel.add( mealpriceTextField);
        
        mealCheckbox=new JComboBox<>(mealoptions);
        mealCheckbox.setBounds(350,510,250,30);
        basePanel.add(mealCheckbox);


        createButton =new JButton("Create");
        createButton.setBounds(350,550,250,40);
        createButton.addActionListener(this);
        super.basePanel.add( createButton);

        backButton =new JButton("Back");
        backButton.setBounds(350,600,250,40);
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        super.basePanel.add(backButton);





    }




    @Override
    public void actionPerformed(ActionEvent e) {
    	  String currentPersonUserId=currentPerson.getUserName();
      if(e.getSource().equals(createButton))
      {
          String groupName=groupNameTextField.getText();
          String source=sourceTextField.getText();
          String destination=destinationTextField.getText();
          
          // Starting Date
          Date date1 = dateChooser.getDate();
          String t = DateFormat.getDateInstance().format(date1);
          String split []=t.split("-");
          String dt = split[2]+"-"+hm.get(split[1])+"-"+split[0];
          
          
          String dtTime=depttimeTextField.getText();
          
          
          String GroupId=groupIdTextField.getText();
          
          // Arrival Date 
          Date date2 = arrivaldateTextField.getDate();
          String t1 = DateFormat.getDateInstance().format(date2);
          String split1 [] = t1.split("-");
          String arrivalDate=split1[2]+"-"+hm.get(split1[1])+"-"+split1[0];
          
          String arrivalTime=arrivaltimeTextField.getText();
          int capacity=0;
          int mealprice=0;
          String MealOptions=mealCheckbox.getItemAt(mealCheckbox.getSelectedIndex());

          try {
 
        	  capacity=Integer.parseInt(capacityTextField.getText());
          }
          catch (NumberFormatException n)
          {
              JOptionPane.showMessageDialog(basePanel,"Capacity must be positive integer");
              return;
          }

          if(capacity<=0) {
              JOptionPane.showMessageDialog(basePanel,"Capacity must a positive integer");
              return;
          }
          //////////
          try {
        	  
        	  mealprice=Integer.parseInt(mealpriceTextField.getText());
          }
          catch (NumberFormatException n)
          {
              JOptionPane.showMessageDialog(basePanel,"Price must be positive integer");
              return;
          }

          if(mealprice<=0) {
              JOptionPane.showMessageDialog(basePanel,"Price must a positive integer");
              return;
          }

     

          if(DatabaseClass.getDataAccessObject().createGroup(GroupId,groupName,source,destination,dt,dtTime,arrivalDate,arrivalTime,currentPersonUserId,capacity,mealprice))
          {
              JOptionPane.showMessageDialog(basePanel, "My group " +groupName+" "+"is created");
              //String currentPersonUserId=currentPerson.getUserName();
              //String currentPersonUserId=GroupId;
              DatabaseClass.getDataAccessObject().addMember(GroupId,groupName,currentPersonUserId,MealOptions);
              this.dispose();
              lastPage.setVisible(true);
          }
          else
              JOptionPane.showMessageDialog(basePanel,"Error! This Group already exists");



      }
     

    }
}
