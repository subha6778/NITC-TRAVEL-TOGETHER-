package GUI;

import Database.DatabaseClass;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SplitBillForm extends ViewBill {

    private JComboBox<String> billCheckbox;
    private JButton addPaidButton,addTotalAmountButton;

    private JLabel paidLabel,totalAmountLabel;

    private JTextField paidTextField,totalAmountTextField;
    String BillName=null;
    String GroupId=null,GroupName=null;
    int totalamount=0;
 
	
    public SplitBillForm(OpenMyGroup lastPage,String GroupId,String GroupName) {
        super(lastPage,GroupId,GroupName);
   
        this.setTitle("Bill Form");
         BillName=GroupId+GroupName;
         this.GroupId=GroupId;
         this.GroupName=GroupName;
        // getTotalAmount(String GroupId,String GroupName)
          totalamount=DatabaseClass.getDataAccessObject().getTotalAmount(GroupId,GroupName);
          
         getBillList();
         setupTableAndPane();
         setupComboBox();
        setupLabelTextFieldAndButton();
      

    }

    @Override
    protected void getBillList()
    {
  
        billList= DatabaseClass.getDataAccessObject().getBill(GroupId,GroupName);
    
    }
    

    private void setupLabelTextFieldAndButton()
    {
    	paidLabel=new JLabel("Paid amount ");
    	paidLabel.setBounds(195,450,120,40);
        basePanel.add(paidLabel);

        paidTextField=new JTextField();
        paidTextField.setBounds(300,450,80,40);
        basePanel.add(paidTextField);

        addPaidButton=new JButton("Add");
        //addPaidButton.setBounds(260,500,80,40);
        addPaidButton.setBounds(415,450,80,40);
        addPaidButton.addActionListener(this);
        basePanel.add(addPaidButton);
        
        totalAmountLabel=new JLabel("Total amount ");
        totalAmountLabel.setBounds(195,490,120,40);
        basePanel.add(totalAmountLabel);

        totalAmountTextField=new JTextField(Integer.toString(totalamount));
        totalAmountTextField.setBounds(300,490,80,40);
        basePanel.add(totalAmountTextField);
        
        addTotalAmountButton=new JButton("Submit");
        //addPaidButton.setBounds(260,500,80,40);
        addTotalAmountButton.setBounds(415,490,80,40);
        addTotalAmountButton.addActionListener(this);
        basePanel.add(addTotalAmountButton);
        

    }
    @SuppressWarnings("deprecation")
	@Override
    protected void setupPane()
    {

        scrollPane = JTable.createScrollPaneForTable( table);
        scrollPane.setBounds(new Rectangle(0, 0, 600, 400));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        basePanel.add( scrollPane, null );
    }


    private void setupComboBox()
    {
        String[] bills=new String[billList.size()];

        for(int i=0;i<billList.size();i++)
        {
            bills[i]=billList.get(i).getMemberName();
            System.out.print("Namee :"+bills[i]);
        }

        if(bills.length==0)
            bills= new String[]{"No records"};

        billCheckbox=new JComboBox<>(bills);
        billCheckbox.setBounds(100,400,400,40);
        basePanel.add(billCheckbox);

    }

    @Override
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
            dataValues[i][1]=String.valueOf(billList.get(i).getPaid());
            dataValues[i][2]=String.valueOf(billList.get(i).getRemainingPay());
      
        }

        return dataValues;

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource().equals(addPaidButton))
        {
            //String[] data= billCheckbox.getItemAt(billCheckbox.getSelectedIndex()).split(",");
        	//String data=billCheckbox;

           // if(data.length==1)
             //   return;
            String MemberName=billCheckbox.getItemAt(billCheckbox.getSelectedIndex());
            System.out.print("Bill name: "+MemberName);
             int paidAmount=0;

            try {
            	paidAmount=Integer.parseInt(paidTextField.getText());
            }
            catch (NumberFormatException n)
            {
                JOptionPane.showMessageDialog(basePanel,"Amount must be positive integer");
                return;
            }

            if(paidAmount<=0) {
                JOptionPane.showMessageDialog(basePanel,"Amount must a positive integer");
                return;
            }

                         
           if(DatabaseClass.getDataAccessObject().updatePaidAmount(BillName,paidAmount,MemberName,GroupId,GroupName))
               JOptionPane.showMessageDialog(basePanel,paidAmount+" added succesfully to "+MemberName+" of Bill "+BillName);


            getBillList();


            //updating table
            basePanel.remove(scrollPane);
            setupTableAndPane();


            this.dispose();
            this.setVisible(true);
        }
        
        if(e.getSource().equals(addTotalAmountButton))
        {
        	 int totalAmount=0;

             try {
             	totalAmount=Integer.parseInt(totalAmountTextField.getText());
             }
             catch (NumberFormatException n)
             {
                 JOptionPane.showMessageDialog(basePanel,"Amount must be positive integer");
                 return;
             }

             if(totalAmount<=0) {
                 JOptionPane.showMessageDialog(basePanel,"Amount must a positive integer");
                 return;
             }

                          
            if(DatabaseClass.getDataAccessObject().updateTotalAmount(BillName,totalAmount,GroupId,GroupName))
                JOptionPane.showMessageDialog(basePanel,totalAmount+" added succesfully to "+BillName);


             getBillList();


             //updating table
             basePanel.remove(scrollPane);
             setupTableAndPane();


             this.dispose();
             this.setVisible(true);
        }
        
        
        
        
        
        
        
    
        
        
    }
   



}
