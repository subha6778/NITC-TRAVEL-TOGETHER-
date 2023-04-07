package GUI;

import CommonActionListeners.BackButtonListener;
import Database.DatabaseClass;
import DatabaseObjectWrapper.Group;
import DatabaseObjectWrapper.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@SuppressWarnings({ "unused" })
public class ViewMyGroup extends BaseFrame 
{

	private static final long serialVersionUID = 1L;

	protected JScrollPane scrollPane;

    JButton backButton;
    LandingPage lastPage;
    JLabel name;

    List<Group> groupList;
    private JComboBox<String> groupCheckbox;
    private JButton optOutButton,openButton,pastgroupButton;





    protected DefaultTableModel model;
    //used in Addgroup class
    protected JTable table;
    Person currentPerson;

    public ViewMyGroup(LandingPage lastPage,Person currentPerson)
    {
        this.currentPerson=currentPerson;
        this.lastPage=lastPage;
        this.setTitle("Active Groups");

       getGroupList();
       setupTableAndPane();
      // setupPane();
       setupComboBox();
       
       name = new JLabel("Active Groups");
       name.setBounds(250,30,300,40);
       super.basePanel.add(name);
       name.setForeground (new Color(255,255,255));
       name.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
       
        backButton=new JButton("Back");
        backButton.setBounds(800,590,150,40);
        backButton.setBackground(new Color(255, 153, 0));
        backButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        basePanel.add(backButton);
        
        optOutButton=new JButton("OptOut");
        optOutButton.setBounds(750,170,130,40);
        optOutButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        optOutButton.setBackground(Color.cyan);
        optOutButton.addActionListener(this);
        basePanel.add(optOutButton);
        
        openButton=new JButton("Open");
        openButton.setBounds(920,170,130,40);
        openButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        openButton.setBackground(Color.cyan);
        openButton.addActionListener(this);
        basePanel.add(openButton);
        
        pastgroupButton=new JButton("Past Group");
        pastgroupButton.setBounds(750,240,300,40);
        pastgroupButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        pastgroupButton.setBackground(Color.cyan);
        pastgroupButton.addActionListener(this);
        basePanel.add(pastgroupButton);


    }

 
    protected void getGroupList()
    {
      
       // groupList= DatabaseClass.getDataAccessObject().getMyGroup(currentPerson);
    	groupList= DatabaseClass.getDataAccessObject().getActiveGroup(currentPerson);
    }

    public void setupTableAndPane()
    {
        setUpTable();
        setupPane();
    }

    private void setUpTable()
    {

        String[] columnNames= CreateColumns();
        String[][] dataValues= CreateData();


        model = new DefaultTableModel(dataValues, columnNames);
        table = new JTable(model);


        table.setOpaque(false);
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);

        table.setRowHeight(40);
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 16));
        table.setForeground(new Color(255,255,255));
        table.setShowHorizontalLines( false );
        table.setRowSelectionAllowed( false );
        table.setColumnSelectionAllowed( false );
        table.setShowGrid(false); 


    }

    protected void setupPane()
    {
       
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(0, 0, 600, 500));
        scrollPane.setOpaque(false);
        scrollPane.setBounds(20,100,700, 500);
        scrollPane.getViewport().setOpaque(false);
        basePanel.add( scrollPane, null );
    }
    private void setupComboBox()
    {
        String[] groups=new String[groupList.size()];

        for(int i=0;i<groupList.size();i++)
            groups[i]=groupList.get(i).toString();

        if(groups.length==0)
            groups= new String[]{"No groups"};

        groupCheckbox=new JComboBox<>(groups);
        groupCheckbox.setBounds(750,100,300,40);
        basePanel.add(groupCheckbox);

    }



   
    protected String[] CreateColumns()
    {
        String[] columnNames=new String[4];
        columnNames[0]="GroupId";
        columnNames[1]="GroupName";
        columnNames[2]="Sorce";
        columnNames[3]="Destination";
        return columnNames;


    }

 
    protected String[][] CreateData() {

        String[][] dataValues=new String[groupList.size()][4];

        for(int i=0;i<groupList.size();i++)
        {
        	dataValues[i][0]=groupList.get(i).getGroupId();
            dataValues[i][1]=groupList.get(i).getGroupName();
            dataValues[i][2]=groupList.get(i).getSource();
            dataValues[i][3]=groupList.get(i).getDestination();
            
        }

        return dataValues;

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(optOutButton))
        {
            String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");

            if(data.length==1)
                return;

            String GroupId=data[0];
            String GroupName=data[1];
            String currentPersonUserId=currentPerson.getUserName();

           if(DatabaseClass.getDataAccessObject().optOutMember(GroupId,GroupName,currentPersonUserId))
            //  JOptionPane.showMessageDialog(basePane,added succesfully");


        	   getGroupList();


            //updating table
            basePanel.remove(scrollPane);
            setupTableAndPane();


            this.dispose();
            this.setVisible(true);
        }
        
        if(e.getSource().equals(openButton))
        {
            String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");

            if(data.length==1)
                return;

            String GroupId=data[0];
            String GroupName=data[1];
            String currentPersonUserId=currentPerson.getUserName();

          // DatabaseClass.getDataAccessObject().openGroup(GroupId,GroupName,currentPersonUserId);
            
          
              this.dispose();
            this.setVisible(false);
            Group grp= DatabaseClass.getDataAccessObject().getGroupDetails(GroupId,GroupName);
            new OpenMyGroup(this,GroupId,GroupName,currentPersonUserId,grp);
           

            //getgroupList();

   
            //updating table
           // basePanel.remove(scrollPane);
            //setupTableAndPane();


            //this.dispose();
            //this.setVisible(true);
        }
        if(e.getSource().equals(pastgroupButton))
        {
           
            String currentPersonUserId=currentPerson.getUserName();

          // DatabaseClass.getDataAccessObject().openGroup(GroupId,GroupName,currentPersonUserId);
            
          
              this.dispose();
            this.setVisible(false);
           // Group grp= DatabaseClass.getDataAccessObject().getGroupDetails(GroupId,GroupName);
            new ViewPastGroup(this,currentPerson);
           

            //getgroupList();

   
            //updating table
           // basePanel.remove(scrollPane);
            //setupTableAndPane();


            //this.dispose();
            //this.setVisible(true);
        }
        
        
        
        
        
    }
 




    
}
