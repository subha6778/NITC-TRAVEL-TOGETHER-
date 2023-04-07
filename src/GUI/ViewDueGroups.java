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
public class ViewDueGroups extends BaseFrame 
{

	private static final long serialVersionUID = 1L;

	protected JScrollPane scrollPane;

    JButton backButton;
    LandingPage lastPage;

    List<Group> groupList;
    private JComboBox<String> groupCheckbox;
    private JButton removeGroupButton;





    protected DefaultTableModel model;
    //used in Addgroup class
    protected JTable table;
    //Person currentPerson;

    public ViewDueGroups(LandingPage lastPage)
    {
       // this.currentPerson=currentPerson;
        this.lastPage=lastPage;
        this.setTitle("Due Groups");

       getDueGroupList();
       setupTableAndPane();
    
       setupComboBox();
       	backButton=new JButton("Back");
        backButton.setBounds(760,600,200,40);
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        backButton.setBackground(new Color(255, 153, 0));
        basePanel.add(backButton);
        /*removeGroupButton=new JButton("Remove");
        removeGroupButton.setBounds(100,500,80,40);
        removeGroupButton.addActionListener(this);
        basePanel.add(removeGroupButton);*/
        

    }

 
    protected void  getDueGroupList()
    {
      
        groupList= DatabaseClass.getDataAccessObject().getDueGroup();
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
        table.setForeground(new Color(255,255,255));
        table.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        table.setShowHorizontalLines( false );
        table.setRowSelectionAllowed( false );
        table.setColumnSelectionAllowed( false );
        table.setShowGrid(false); 


    }

    protected void setupPane()
    {
       
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(100, 50, 600, 500));
        scrollPane.setOpaque(false);
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
        groupCheckbox.setBounds(260,500,300,40);
        //basePanel.add(groupCheckbox);

    }



   
    protected String[] CreateColumns()
    {
        String[] columnNames=new String[3];
        columnNames[0]="GroupId";
        columnNames[1]="GroupName";
        columnNames[2]="Arrival Date";
        return columnNames;


    }

 
    protected String[][] CreateData() {

        String[][] dataValues=new String[groupList.size()][3];

        for(int i=0;i<groupList.size();i++)
        {
        	dataValues[i][0]=groupList.get(i).getGroupId();
            dataValues[i][1]=groupList.get(i).getGroupName();
            dataValues[i][2]=groupList.get(i).getArrivalDate();
            //System.out.print("sssss"+dataValues[i][2]);
        }

        return dataValues;

    }
    public void actionPerformed(ActionEvent e)
    {
    
        
          if(e.getSource().equals(removeGroupButton))
        {
            String[] data= groupCheckbox.getItemAt(groupCheckbox.getSelectedIndex()).split(",");

            if(data.length==1)
                return;

            String GroupId=data[0];
            String GroupName=data[1];
            //String currentPersonUserId=currentPerson.getUserName();

           if(DatabaseClass.getDataAccessObject().DeleteDueGroup(GroupId,GroupName))
             JOptionPane.showMessageDialog(basePanel,"Removed succesfully");


           getDueGroupList();


            //updating table
            basePanel.remove(scrollPane);
            setupTableAndPane();


            this.dispose();
            this.setVisible(true);
        } 
    } 
}
