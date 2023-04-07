package GUI;

import CommonActionListeners.BackButtonListener;
import Database.DatabaseClass;
import DatabaseObjectWrapper.Bill;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

@SuppressWarnings("unused")
public class ViewMembers extends BaseFrame{



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JScrollPane scrollPane;

    JButton backButton;
    OpenMyGroup lastPage;
    JLabel name;

    List<String> recordlist;
    String GroupId=null,GroupName=null;



    protected DefaultTableModel model;
    protected JTable table;

    public ViewMembers(OpenMyGroup lastPage,String GroupId,String GroupName)
    {

        this.lastPage=lastPage;
        this.GroupId=GroupId;
        this.GroupName=GroupName;
        this.setTitle("View Members");

       getBillList();
       setupTableAndPane();
       // getBillList();
       
       name = new JLabel("Group Members");
       name.setBounds(250,30,300,40);
       super.basePanel.add(name);
       name.setForeground (new Color(255,255,255));
       name.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));

       backButton=new JButton("Back");
       backButton.setBounds(800,620,150,40);
       backButton.setBackground(new Color(255, 153, 0));
       backButton.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 16));
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        basePanel.add(backButton);


    }

 
    protected void getBillList()
    {
     
        recordlist= DatabaseClass.getDataAccessObject().getName_MAIL_lList(GroupId,GroupName);
        //System.out.print(list(0).getPaid());
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

        table.setShowHorizontalLines( false );
        table.setRowSelectionAllowed( true );
        table.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 16));
        table.setForeground(new Color(255,255,255));
        table.setColumnSelectionAllowed( true );
        table.setShowGrid(false); 


    }

    protected void setupPane()
    {
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(0, 0, 600, 500));
        scrollPane.setOpaque(false);
        scrollPane.setBounds(20,120,700,500);
        scrollPane.getViewport().setOpaque(false);
        basePanel.add( scrollPane, null );
    }

    protected String[] CreateColumns()
    {
        String[] columnNames=new String[2];
        columnNames[0]="Name";
        columnNames[1]="Email Id";
        
  
        return columnNames;


    }

 
    protected String[][] CreateData() {

        String[][] dataValues=new String[recordlist.size()][2];

        for(int i=0;i<recordlist.size();i++)
        {
        	String temp=recordlist.get(i);
        	String[] arrOfStr = temp.split("#");
            dataValues[i][0]=arrOfStr[0];
            dataValues[i][1]=arrOfStr[1];
      
        }

        return dataValues;

    }



}
