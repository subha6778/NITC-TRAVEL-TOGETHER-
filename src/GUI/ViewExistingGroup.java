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
public class ViewExistingGroup extends BaseFrame 
{

	private static final long serialVersionUID = 1L;

	protected JScrollPane scrollPane;

    JButton backButton;
    LandingPage lastPage;
    JLabel name;

    List<Group> groupList;
    private JComboBox<String> groupCheckbox;
    private JComboBox<String> mealCheckbox;
    private JButton joinButton;
    private JButton openButton;





    protected DefaultTableModel model;
    //used in Addgroup class
    protected JTable table;
    Person currentPerson;

    public ViewExistingGroup(LandingPage lastPage,Person currentPerson)
    {
        this.currentPerson=currentPerson;
        this.lastPage=lastPage;
        this.setTitle("Available Group");

       getGroupList();
       setupTableAndPane();
      // setupPane();
       setupComboBox();
       
       name = new JLabel("Available Groups");
       name.setBounds(250,30,300,40);
       super.basePanel.add(name);
       name.setForeground (new Color(255,255,255));
       name.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
       
       
        backButton=new JButton("Back");
        backButton.setBounds(800,590,200,40);
        backButton.setBackground(new Color(255, 153, 0));
        backButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        basePanel.add(backButton);
        
        joinButton=new JButton("Join");
        joinButton.setBounds(750,240,130,40);
        joinButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        joinButton.setBackground(Color.cyan);
        joinButton.addActionListener(this);
        basePanel.add( joinButton);
        
        openButton=new JButton("Open");
        openButton.setBounds(920,240,130,40);
        openButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        openButton.setBackground(Color.cyan);
        openButton.addActionListener(this);
        basePanel.add(openButton);


    }

    //overriden in Addcopies to get all books
    protected void getGroupList()
    {
        //get available books ie quantity >0
    	String currentPersonUserId=currentPerson.getUserName();
        groupList= DatabaseClass.getDataAccessObject().getAvailableGroup(currentPersonUserId);
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
        //table.setBounds(50,50,700, 500);
        table.setShowHorizontalLines( false );
        table.setRowSelectionAllowed( true );
        table.setColumnSelectionAllowed( true );
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 16));
        table.setForeground(new Color(255,255,255));
        table.setShowGrid(false); // sirs suggestion


    }

    protected void setupPane()
    {
        //method is protected to allow AddCopies to change scrollpane height
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(20, 50, 600, 500));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBounds(20,100,700, 500);
        basePanel.add( scrollPane, null );
    }
    private void setupComboBox()
    {
        String[] groups=new String[groupList.size()];
        String[] mealoptions= {"Want meal","Dont want meal"};

        for(int i=0;i<groupList.size();i++)
            groups[i]=groupList.get(i).toString();

        if(groups.length==0)
        {	
            groups= new String[]{"No groups"};
            mealoptions=new String[]{"No groups"};
        }

        groupCheckbox=new JComboBox<>(groups);
        groupCheckbox.setBounds(750,170,300,40);
        basePanel.add(groupCheckbox);
        
        mealCheckbox=new JComboBox<>(mealoptions);
        mealCheckbox.setBounds(750,100,300,40);
        basePanel.add(mealCheckbox);

    }



    //overriden in AddCopies to change column count and names
    protected String[] CreateColumns()
    {
        String[] columnNames=new String[4];
        columnNames[0]="GroupId";
        columnNames[1]="GroupName";
        columnNames[2]="Sorce";
        columnNames[3]="Destination";
      //  columnNames[4]="Departure Time";
       // columnNames[5]="Departure Date";
      //  columnNames[6]="Arrival Date";
       // columnNames[7]="Arrival Time";
       // columnNames[8]="LeaderId";

        return columnNames;


    }

    //overriden in AddCopies to allow different data
    protected String[][] CreateData() {

        String[][] dataValues=new String[groupList.size()][4];

        for(int i=0;i<groupList.size();i++)
        {
        	dataValues[i][0]=groupList.get(i).getGroupId();
            dataValues[i][1]=groupList.get(i).getGroupName();
            dataValues[i][2]=groupList.get(i).getSource();
            dataValues[i][3]=groupList.get(i).getDestination();
           // dataValues[i][4]=groupList.get(i).getDepartureTime();
            //dataValues[i][5]=groupList.get(i).getDepartureDate();
            //dataValues[i][6]=groupList.get(i).getArrivalDate();
            //dataValues[i][7]=groupList.get(i).getArrivalDate();
            //dataValues[i][8]=groupList.get(i).getLeaderId();
          
        }

        return dataValues;

    }
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(joinButton))
        {
            String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");
            String MealOptions=mealCheckbox.getItemAt(mealCheckbox.getSelectedIndex());

            if(data.length==1)
                return;

            String GroupId=data[0];
            String GroupName=data[1];
            String currentPersonUserId=currentPerson.getUserName();
            int numberOfmember= DatabaseClass.getDataAccessObject().getNumberOfMember(GroupId,GroupName);
            int capacity= DatabaseClass.getDataAccessObject().getGroupCapacity(GroupId,GroupName);
            int sub=capacity-numberOfmember;
            System.out.print("sub:"+sub);
            if((capacity-numberOfmember)>0)
            {	

                 DatabaseClass.getDataAccessObject().addMember(GroupId,GroupName,currentPersonUserId,MealOptions);
                // DatabaseClass.getDataAccessObject().addMealPrice(GroupId,GroupName,currentPersonUserId,MealOptions);
                 
                 basePanel.remove(scrollPane);
                 setupTableAndPane();
                 this.dispose();
                this.setVisible(true);
            }
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
            new OpenGroup(this,GroupId,GroupName,currentPersonUserId,grp);
           

            //getgroupList();


            //updating table
           // basePanel.remove(scrollPane);
            //setupTableAndPane();


            //this.dispose();
            //this.setVisible(true);
        }
        
        
        
        
        
        
        
        
        
        
    }




    
}
