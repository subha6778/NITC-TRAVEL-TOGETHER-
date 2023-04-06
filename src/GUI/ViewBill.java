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
public class ViewBill extends BaseFrame{



    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JScrollPane scrollPane;

    JButton backButton;
    OpenMyGroup lastPage;

    List<Bill> billList;
    String GroupId=null,GroupName=null;



    protected DefaultTableModel model;
    protected JTable table;

    public ViewBill(OpenMyGroup lastPage,String GroupId,String GroupName)
    {

        this.lastPage=lastPage;
        this.GroupId=GroupId;
        this.GroupName=GroupName;
        this.setTitle("View Bill");

       getBillList();
       setupTableAndPane();
       // getBillList();

        backButton=new JButton("Back");
        backButton.setBounds(260,550,80,40);
        backButton.addActionListener(new BackButtonListener(this,lastPage));
        basePanel.add(backButton);


    }

 
    protected void getBillList()
    {
     
        billList= DatabaseClass.getDataAccessObject().getBill(GroupId,GroupName);
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
        //table.setBounds(50,50,700, 500);
        table.setShowHorizontalLines( false );
        table.setRowSelectionAllowed( true );
        table.setColumnSelectionAllowed( true );
        table.setShowGrid(true); 


    }

    protected void setupPane()
    {
        
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(new Rectangle(0, 0, 600, 500));
        scrollPane.setOpaque(true);
        scrollPane.getViewport().setOpaque(false);
        basePanel.add( scrollPane, null );
    }

    protected String[] CreateColumns()
    {
        String[] columnNames=new String[3];
        columnNames[0]="Name";
        columnNames[1]="Paid";
        columnNames[2]="Remainig  pay";
        
  
        return columnNames;


    }

 
    protected String[][] CreateData() {

        String[][] dataValues=new String[billList.size()][3];

        for(int i=0;i<billList.size();i++)
        {
            dataValues[i][0]=billList.get(i).getMemberName();
            System.out.print("Namee :"+dataValues[i][0]);
            dataValues[i][1]=String.valueOf(billList.get(i).getPaid());
            dataValues[i][2]=String.valueOf(billList.get(i).getRemainingPay());
      
        }

        return dataValues;

    }








}
