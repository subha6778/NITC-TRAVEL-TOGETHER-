package GUI;

import CommonActionListeners.BackButtonListener;
import Database.DatabaseClass;
import DatabaseObjectWrapper.Person;
import DatabaseObjectWrapper.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "unused", "serial" })
public class OpenMyGroup extends BaseFrame  {

   // LandingPage lastPage;
	ViewMyGroup lastPage;

    JLabel groupNameLabel,sourceLabel,destinationLabel,dateLabel,depttimeLabel,groupIdLabel,arrivaltimeLabel,arrivaldateLabel,capacityLabel,personLabel,leaderNameLabel,leaderIdLabel,leadercontactLabel;

    JTextField groupNameTextField,sourceTextField,destinationTextField,dateTextField,depttimeTextField,groupIdTextField,arrivaltimeTextField,arrivaldateTextField,capacityTextField,personTextField,leaderNameTextField,
    leaderIdTextField,leadercontactTextField;


    JButton backButton,splitbillButton,viewMemberButton;

   Person currentPerson;
   Group grp;
  String currentPersonUserId;
  String grpId=null,grpName=null;

  public OpenMyGroup(ViewMyGroup lastpage,String GroupId,String GroupName, String currentPersonUserId,Group grp)
    {
      // this.currentPerson=currentPerson;
	 // this.currentPerson=currentPerson;
	  this.currentPersonUserId=currentPersonUserId;
      this.setTitle("Open MY Group");
      this.lastPage=lastpage;
      this.grp=grp;
  
      leaderIdLabel=new JLabel("Leader Id :");
      leaderIdLabel.setBounds(250,10,150,30);
      leaderIdLabel.setForeground (Color.orange);
      leaderIdLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(leaderIdLabel);
      leaderIdTextField=new JTextField(grp.getLeaderId());
      leaderIdTextField.setBounds(550,10,250,30);
      super.basePanel.add(leaderIdTextField);

      this.grpId=grp.getGroupId();
      this.grpName=grp.getGroupName();
      
      
      
      
      groupNameLabel=new JLabel("Group Name :");
      groupNameLabel.setBounds(250,50,150,30);
      groupNameLabel.setForeground (Color.orange);
      groupNameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(groupNameLabel);
      groupNameTextField=new JTextField(grp.getGroupName());
      groupNameTextField.setBounds(550,50,250,30);
      super.basePanel.add(groupNameTextField);



      sourceLabel=new JLabel("SOURCE :");
      sourceLabel.setBounds(250,90,150,30);
      sourceLabel.setForeground (Color.orange);
      sourceLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(sourceLabel);

      sourceTextField=new JTextField(grp.getSource());
      sourceTextField.setBounds(550,90,250,30);
      super.basePanel.add(sourceTextField);


      destinationLabel=new JLabel("DESTINATION :");
      destinationLabel.setBounds(250,130,150,30);
      destinationLabel.setForeground (Color.orange);
      destinationLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(destinationLabel);

      destinationTextField=new JTextField(grp.getDestination());
      destinationTextField.setBounds(550,130,250,30);
      super.basePanel.add(destinationTextField);


      dateLabel=new JLabel("Journey Date :");
      dateLabel.setBounds(250,170,150,30);
      dateLabel.setForeground (Color.orange);
      dateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(dateLabel);
      dateTextField=new JTextField(grp.getDepartureDate());
      dateTextField.setBounds(550,170,250,30);
      super.basePanel.add(dateTextField);


      depttimeLabel=new JLabel("Departure Time :");
      depttimeLabel.setBounds(250,210,250,30);
      depttimeLabel.setForeground (Color.orange);
      depttimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(depttimeLabel);

      depttimeTextField=new JTextField(grp.getDepartureTime());
      depttimeTextField.setBounds(550,210,250,30);
      super.basePanel.add(depttimeTextField);
      
      arrivaldateLabel=new JLabel("Arrival Date :");
      arrivaldateLabel.setBounds(250,250,150,30);
      arrivaldateLabel.setForeground (Color.orange);
      arrivaldateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(arrivaldateLabel);
      
      arrivaldateTextField=new JTextField(grp.getArrivalDate());
      arrivaldateTextField.setBounds(550,250,250,30);
      super.basePanel.add(arrivaldateTextField);
      
      arrivaltimeLabel=new JLabel("Arrival Time :");
      arrivaltimeLabel.setBounds(250,290,150,30);
      arrivaltimeLabel.setForeground (Color.orange);
      arrivaltimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(arrivaltimeLabel);
      
      arrivaltimeTextField=new JTextField(grp.getDepartureTime());
      arrivaltimeTextField.setBounds(550,290,250,30);
      super.basePanel.add(arrivaltimeTextField);
      
      capacityLabel=new JLabel("Capacity :");
      capacityLabel.setBounds(250,330,150,30);
      capacityLabel.setForeground (Color.orange);
      capacityLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(capacityLabel);
      //Integer cap=grp.getCapacity();
      //String capa=cap.toString();
      int cap=grp.getCapacity();
      String s=String.valueOf(cap);
      capacityTextField=new JTextField(s);
      capacityTextField.setBounds(550,330,250,30);
      super.basePanel.add(capacityTextField);
      
      personLabel=new JLabel("Remaning Capacity :");
      personLabel.setBounds(250,370,200,30);
      personLabel.setForeground (Color.orange);
      personLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(personLabel);
     int numberOfmember= DatabaseClass.getDataAccessObject().getNumberOfMember(grp.getGroupId(),grp.getGroupName());
     int remaingmember=cap-numberOfmember;
     String rem=String.valueOf(remaingmember);
      personTextField=new JTextField(rem);
      personTextField.setBounds(550,370,250,30);
      super.basePanel.add( personTextField);
      
      leaderNameLabel=new JLabel("Leader Name :");
      leaderNameLabel.setBounds(250,410,150,30);
      leaderNameLabel.setForeground (Color.orange);
      leaderNameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(leaderNameLabel);
      String leadername= DatabaseClass.getDataAccessObject().getLeaderName(grp.getLeaderId());
      leaderNameTextField=new JTextField(leadername);
      leaderNameTextField.setBounds(550,410,250,30);
      super.basePanel.add(leaderNameTextField);
     
      leadercontactLabel=new JLabel("Leader Contact no :");
      leadercontactLabel.setBounds(250,450,200,30);
      leadercontactLabel.setForeground (Color.orange);
      leadercontactLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 18));
      super.basePanel.add(leadercontactLabel);
      
      String mobno= DatabaseClass.getDataAccessObject().getContactNumber(grp.getLeaderId());
      
      
      
      leadercontactTextField=new JTextField(mobno);
      leadercontactTextField.setBounds(550,450,250,30);
      super.basePanel.add(leadercontactTextField);
      
      
      
      backButton =new JButton("Back");
      backButton.setBounds(250,490,80,40);
      backButton.addActionListener(new BackButtonListener(this,lastPage));
      super.basePanel.add(backButton);
      splitbillButton =new JButton("Split Bill");
      splitbillButton.setBounds(250,530,80,40);
      splitbillButton.addActionListener(this);
      super.basePanel.add(splitbillButton);
      viewMemberButton=new JButton("Members");
      viewMemberButton.setBounds(400,490,80,40);
      viewMemberButton.addActionListener(this);
      super.basePanel.add(viewMemberButton);
     /*
      * 
     openButton=new JButton("Open");
        openButton.setBounds(100,550,80,40);
        openButton.addActionListener(this);
        basePanel.add(openButton);
      */




    }




    @Override
    public void actionPerformed(ActionEvent e) {
    	/*
    	  String currentPersonUserId=currentPerson.getUserName();
      if(e.getSource().equals(createButton))
      {
          String groupName=groupNameTextField.getText();
          String source=sourceTextField.getText();
          String destination=destinationTextField.getText();
          String dt=dateTextField.getText();
          String dtTime=depttimeTextField.getText();
          String GroupId=groupIdTextField.getText();
          String arrivalDate=arrivaldateTextField.getText();
          String arrivalTime=arrivaltimeTextField.getText();


          if(DatabaseClass.getDataAccessObject().createGroup(GroupId,groupName,source,destination,dt,dtTime,arrivalDate,arrivalTime,currentPersonUserId))
          {
              JOptionPane.showMessageDialog(basePanel, "My group " +groupName+" "+"is created");
              //String currentPersonUserId=currentPerson.getUserName();
              //String currentPersonUserId=GroupId;
              DatabaseClass.getDataAccessObject().addMember(GroupId,groupName,currentPersonUserId);
              this.dispose();
              lastPage.setVisible(true);
          }
          else
              JOptionPane.showMessageDialog(basePanel,"Error! This Group already exists");



      }
     

    }
    */
    	
    	 if(e.getSource().equals(splitbillButton))
         {
             //String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");

             //if(data.length==1)
                // return;

            // String GroupId=data[0];
             //String GroupName=data[1];
             //String currentPersonUserId=currentPerson.getUserName();

           // DatabaseClass.getDataAccessObject().openGroup(GroupId,GroupName,currentPersonUserId);
             
           
               this.dispose();
             this.setVisible(false);
             //Group grp= DatabaseClass.getDataAccessObject().getGroupDetails(GroupId,GroupName);
              new SplitBillForm(this,grpId,grpName);
            

             //getgroupList();


             //updating table
            // basePanel.remove(scrollPane);
             //setupTableAndPane();



         }	
    	 if(e.getSource().equals(viewMemberButton))
         {
             //String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");

             //if(data.length==1)
                // return;

            // String GroupId=data[0];
             //String GroupName=data[1];
             //String currentPersonUserId=currentPerson.getUserName();

           // DatabaseClass.getDataAccessObject().openGroup(GroupId,GroupName,currentPersonUserId);
             
           
               this.dispose();
             this.setVisible(false);
             //Group grp= DatabaseClass.getDataAccessObject().getGroupDetails(GroupId,GroupName);
              new ViewMembers(this,grpId,grpName);
            

             //getgroupList();


             //updating table
            // basePanel.remove(scrollPane);
             //setupTableAndPane();



         }	
    
    	
    	
    	
    }
}
