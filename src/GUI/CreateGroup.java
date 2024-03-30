package GUI;
import com.github.lgooddatepicker.components.TimePicker;
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
import java.time.*;
@SuppressWarnings({ "unused", "serial" })
public class CreateGroup extends BaseFrame  {

    LandingPage lastPage;

    JLabel signName, groupNameLabel,sourceLabel,destinationLabel,dateLabel,depttimeLabel,groupIdLabel,arrivaltimeLabel,arrivaldateLabel,capacityLabel,mealpriceLabel;

    JTextField groupNameTextField, sourceTextField, destinationTextField, dateTextField, groupIdTextField, capacityTextField, mealpriceTextField;

    private JComboBox<String> mealCheckbox;
    String[] mealoptions= {"Want meal","Dont want meal"};
    JButton createButton,backButton;
    JDateChooser dateChooser, arrivaldateTextField;
    TimePicker arrivaltimeTextField, depttimeTextField;
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
        signName.setForeground (new Color(255,255,255));
        signName.setBounds(100,130,300,40);
        super.basePanel.add(signName);

        groupIdLabel=new JLabel("Group ID:");
        groupIdLabel.setBounds(100,200,180,40);
        super.basePanel.add(groupIdLabel);
        groupIdLabel.setForeground (new Color(255,255,255));
        groupIdLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        groupIdTextField=new JTextField();
        groupIdTextField.setBounds(300,200,200,30);
        super.basePanel.add(groupIdTextField);
        
        
        groupNameLabel=new JLabel("Group Name:");
        groupNameLabel.setBounds(600,200,180,40);
        super.basePanel.add(groupNameLabel);
        groupNameLabel.setForeground (new Color(255,255,255));
        groupNameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        groupNameTextField=new JTextField();
        groupNameTextField.setBounds(760,200,200,30);
        super.basePanel.add(groupNameTextField);


        sourceLabel=new JLabel("Source:");
        sourceLabel.setBounds(100,270,180,40);
        super.basePanel.add(sourceLabel);
        sourceLabel.setForeground (new Color(255,255,255));
        sourceLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        sourceTextField=new JTextField();
        sourceTextField.setBounds(300,270,200,30);
        super.basePanel.add(sourceTextField);


        destinationLabel=new JLabel("Destination:");
        destinationLabel.setBounds(600,270,180,40);
        super.basePanel.add(destinationLabel);
        destinationLabel.setForeground (new Color(255,255,255));
        destinationLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        destinationTextField=new JTextField();
        destinationTextField.setBounds(760,270,200,30);
        super.basePanel.add(destinationTextField);


        dateLabel=new JLabel("Journey Date:");
        dateLabel.setBounds(100,340,180,40);
        super.basePanel.add(dateLabel);
        dateLabel.setForeground (new Color(255,255,255));
        dateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300,340,200,30);
        super.basePanel.add(dateChooser);
        
        
        arrivaldateLabel=new JLabel("Arrival Date:");
        arrivaldateLabel.setBounds(600,340,180,40);
        super.basePanel.add(arrivaldateLabel);
        arrivaldateLabel.setForeground (new Color(255,255,255));
        arrivaldateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        arrivaldateTextField=new JDateChooser();
        arrivaldateTextField.setBounds(760,340,200,30);
        super.basePanel.add(arrivaldateTextField);

        depttimeLabel=new JLabel("Departure Time:");
        depttimeLabel.setBounds(100, 410,180,40);
        super.basePanel.add(depttimeLabel);
        depttimeLabel.setForeground (new Color(255,255,255));
        depttimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));

        depttimeTextField=new TimePicker();
        depttimeTextField.setBounds(300,410,200,30);
        depttimeTextField.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        super.basePanel.add(depttimeTextField);
        
        
        arrivaltimeLabel=new JLabel("Arrival Time:");
        arrivaltimeLabel.setBounds(600,410,180,40);
        super.basePanel.add(arrivaltimeLabel);
        arrivaltimeLabel.setForeground (new Color(255,255,255));
        arrivaltimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        arrivaltimeTextField = new TimePicker();
        arrivaltimeTextField.setBounds(760,410,200,30);
        arrivaltimeTextField.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        super.basePanel.add(arrivaltimeTextField);
        
        
        capacityLabel=new JLabel("Capacity:");
        capacityLabel.setBounds(100,480,180,40);
        super.basePanel.add(capacityLabel);
        capacityLabel.setForeground (new Color(255,255,255));
        capacityLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        capacityTextField=new JTextField();
        capacityTextField.setBounds(300,480,200,30);
        super.basePanel.add(capacityTextField);
        
        mealpriceLabel=new JLabel("Meal Price:");
        mealpriceLabel.setBounds(600,480,180,30);
        super.basePanel.add( mealpriceLabel);
        mealpriceLabel.setForeground (new Color(255,255,255));
        mealpriceLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
        
        mealpriceTextField=new JTextField();
        mealpriceTextField.setBounds(760,480,200,30);
        super.basePanel.add( mealpriceTextField);
        
        mealCheckbox=new JComboBox<>(mealoptions);
        mealCheckbox.setBounds(300,550,200,40);
        mealCheckbox.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        basePanel.add(mealCheckbox);


        createButton =new JButton("Create");
        createButton.setBounds(530,590,200,40);
        createButton.addActionListener(this);
        createButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        createButton.setBackground(Color.green);
        super.basePanel.add( createButton);

        backButton =new JButton("Back");
        backButton.setBounds(760,590,200,40);
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        backButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        backButton.setBackground(new java.awt.Color(255, 153, 0));
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
          String split0 []=t.split(",");
          String split3[]= split0[0].split("\\s+");
          String dt = split0[1].trim()+"-"+hm.get(split3[0].trim())+"-"+split3[1].trim();
          System.out.println(split3[0]+"  "+split3[1]);
 
          System.out.print("YEAR:"+dt);
          
          
          String dtTime=depttimeTextField.getText().toString();
          
          
          String GroupId=groupIdTextField.getText();
          
          // Arrival Date 
          Date date2 = arrivaldateTextField.getDate();
          String t1 = DateFormat.getDateInstance().format(date2);
          String split1 [] = t1.split(",");
          String split2[]= split1[0].split("\\s+");
          String arrivalDate=split1[1].trim()+"-"+hm.get(split2[0].trim())+"-"+split2[1].trim();
          
          String arrivalTime=arrivaltimeTextField.getText().toString();
          int capacity=0;
          int mealprice=0;
          String MealOptions=mealCheckbox.getItemAt(mealCheckbox.getSelectedIndex());
          
          
          
          /*
          if(split[1].compareTo(split1[1])>0)
          {
        	  JOptionPane.showMessageDialog(basePanel, "Invalid Date");
        	  return;
          }
          if(split[1].equals(split1[1]))
          {
        	  if(hm.get(split3[1]).compareTo(hm.get(split2[1]))>0)
        	  {
        		  JOptionPane.showMessageDialog(basePanel, "Invalid Date");
            	  return;
        	  }
        	  if(hm.get(split3[1]).compareTo(hm.get(split2[1]))==0)
        	  {
        		  if(split3[0].compareTo(split2[0])>0)
        		  {
        			  JOptionPane.showMessageDialog(basePanel, "Invalid Date");
                	  return;
        		  }
        	  }
          }
          (*/
          
          
          
          
          
          
          
          
          
          
          

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
