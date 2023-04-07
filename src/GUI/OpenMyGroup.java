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

    JLabel gn, groupNameLabel,sourceLabel,destinationLabel,dateLabel,depttimeLabel,groupIdLabel,arrivaltimeLabel,arrivaldateLabel,capacityLabel,personLabel,leaderNameLabel,leaderIdLabel,leadercontactLabel;

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
      this.grpId=grp.getGroupId();
      this.grpName=grp.getGroupName();
  
      gn = new JLabel("Group : "+ grpName);
      gn.setBounds(100,130,300,30);
      gn.setForeground (Color.white);
      gn.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
      super.basePanel.add(gn);
      
      leaderIdLabel=new JLabel("Leader Id :");
      leaderIdLabel.setBounds(100,190,180,30);
      leaderIdLabel.setForeground (Color.white);
      leaderIdLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(leaderIdLabel);
      
      leaderIdTextField=new JTextField(grp.getLeaderId());
      leaderIdTextField.setBounds(280,190,200,30);
      super.basePanel.add(leaderIdTextField);

      
      
      
      
      
      groupNameLabel=new JLabel("Group Name :");
      groupNameLabel.setBounds(580,190,180,30);
      groupNameLabel.setForeground (Color.white);
      groupNameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(groupNameLabel);
      groupNameTextField=new JTextField(grp.getGroupName());
      groupNameTextField.setBounds(760,190,200,30);
      super.basePanel.add(groupNameTextField);



      sourceLabel=new JLabel("SOURCE :");
      sourceLabel.setBounds(100,250,180,30);
      sourceLabel.setForeground (Color.white);
      sourceLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(sourceLabel);

      sourceTextField=new JTextField(grp.getSource());
      sourceTextField.setBounds(280,250,200,30);
      super.basePanel.add(sourceTextField);


      destinationLabel=new JLabel("DESTINATION :");
      destinationLabel.setBounds(580,250,180,30);
      destinationLabel.setForeground (Color.white);
      destinationLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(destinationLabel);

      destinationTextField=new JTextField(grp.getDestination());
      destinationTextField.setBounds(760,250,200,30);
      super.basePanel.add(destinationTextField);


      dateLabel=new JLabel("Journey Date :");
      dateLabel.setBounds(100,310,180,30);
      dateLabel.setForeground (Color.white);
      dateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(dateLabel);
      dateTextField=new JTextField(grp.getDepartureDate());
      dateTextField.setBounds(280,310,200,30);
      super.basePanel.add(dateTextField);


      depttimeLabel=new JLabel("Departure Time :");
      depttimeLabel.setBounds(580,310,180,30);
      depttimeLabel.setForeground (Color.white);
      depttimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(depttimeLabel);

      depttimeTextField=new JTextField(grp.getDepartureTime());
      depttimeTextField.setBounds(760,310,200,30);
      super.basePanel.add(depttimeTextField);
      
      arrivaldateLabel=new JLabel("Arrival Date :");
      arrivaldateLabel.setBounds(100,370,180,30);
      arrivaldateLabel.setForeground (Color.white);
      arrivaldateLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(arrivaldateLabel);
      
      arrivaldateTextField=new JTextField(grp.getArrivalDate());
      arrivaldateTextField.setBounds(280,370,200,30);
      super.basePanel.add(arrivaldateTextField);
      
      arrivaltimeLabel=new JLabel("Arrival Time :");
      arrivaltimeLabel.setBounds(580,370,180,30);
      arrivaltimeLabel.setForeground (Color.white);
      arrivaltimeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(arrivaltimeLabel);
      
      arrivaltimeTextField=new JTextField(grp.getArrivalTime());
      arrivaltimeTextField.setBounds(760,370,200,30);
      super.basePanel.add(arrivaltimeTextField);
      
      capacityLabel=new JLabel("Capacity :");
      capacityLabel.setBounds(100,430,180,30);
      capacityLabel.setForeground (Color.white);
      capacityLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(capacityLabel);
      //Integer cap=grp.getCapacity();
      //String capa=cap.toString();
      int cap=grp.getCapacity();
      String s=String.valueOf(cap);
      capacityTextField=new JTextField(s);
      capacityTextField.setBounds(280,430,200,30);
      super.basePanel.add(capacityTextField);
      
      personLabel=new JLabel("Remaning Space:");
      personLabel.setBounds(580,430,180,30);
      personLabel.setForeground (Color.white);
      personLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(personLabel);
     int numberOfmember= DatabaseClass.getDataAccessObject().getNumberOfMember(grp.getGroupId(),grp.getGroupName());
     int remaingmember=cap-numberOfmember;
     String rem=String.valueOf(remaingmember);
      personTextField=new JTextField(rem);
      personTextField.setBounds(760,430,200,30);
      super.basePanel.add( personTextField);
      
      leaderNameLabel=new JLabel("Leader Name :");
      leaderNameLabel.setBounds(100,490,180,30);
      leaderNameLabel.setForeground (Color.white);
      leaderNameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(leaderNameLabel);
      String leadername= DatabaseClass.getDataAccessObject().getLeaderName(grp.getLeaderId());
      leaderNameTextField=new JTextField(leadername);
      leaderNameTextField.setBounds(280,490,200,30);
      super.basePanel.add(leaderNameTextField);
     
      leadercontactLabel=new JLabel("Leader Contact:");
      leadercontactLabel.setBounds(580,490,180,30);
      leadercontactLabel.setForeground (Color.white);
      leadercontactLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
      super.basePanel.add(leadercontactLabel);
      
      String mobno= DatabaseClass.getDataAccessObject().getContactNumber(grp.getLeaderId());
      
      
      
      leadercontactTextField=new JTextField(mobno);
      leadercontactTextField.setBounds(760,490,200,30);
      super.basePanel.add(leadercontactTextField);
      
      
      
      backButton =new JButton("Back");
      backButton.setBounds(760,580,200,40);
      backButton.setBackground(new Color(255, 153, 0));
      backButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
      backButton.addActionListener(new BackButtonListener(this,lastPage));
      super.basePanel.add(backButton);
      splitbillButton =new JButton("Split Bill");
      splitbillButton.setBounds(150,580,200,40);
      splitbillButton.setBackground(Color.green);
      splitbillButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
      splitbillButton.addActionListener(this);
      super.basePanel.add(splitbillButton);
      viewMemberButton=new JButton("Members");
      viewMemberButton.setBounds(430,580,200,40);
      viewMemberButton.setBackground(Color.green);
      viewMemberButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
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
