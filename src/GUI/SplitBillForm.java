package GUI;

import Database.DatabaseClass;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SplitBillForm extends ViewBill {

    private JComboBox<String> billCheckbox;
    private JButton addPaidButton,addTotalAmountButton;

    private JLabel name, paidLabel,totalAmountLabel;

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
    	name=new JLabel("Split Bill Page");
    	name.setBounds(250,20,300,40);
    	name.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 30));
    	name.setForeground(new Color(255,255,255));
        basePanel.add(name);
    	
    	paidLabel=new JLabel("Paid amount ");
    	paidLabel.setBounds(750,215,120,40);
    	paidLabel.setForeground(new Color(255,255,255));
    	paidLabel.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 16));
        basePanel.add(paidLabel);

        paidTextField=new JTextField();
        paidTextField.setBounds(850,220,200,30);
        basePanel.add(paidTextField);

        addPaidButton=new JButton("Add");
        addPaidButton.setBounds(920,260,130,30);
        addPaidButton.setBackground(Color.cyan);
        addPaidButton.addActionListener(this);
        basePanel.add(addPaidButton);
        
        totalAmountLabel=new JLabel("Total amount ");
        totalAmountLabel.setBounds(750,320,120,30);
        totalAmountLabel.setForeground(new Color(255,255,255));
        totalAmountLabel.setFont(new Font(Font.SANS_SERIF,  Font.PLAIN, 16));
        basePanel.add(totalAmountLabel);

        totalAmountTextField=new JTextField(Integer.toString(totalamount));
        totalAmountTextField.setBounds(850,320,200,30);
        basePanel.add(totalAmountTextField);
        
        addTotalAmountButton=new JButton("Submit");
        addTotalAmountButton.setBounds(920,360,130,30);
        addTotalAmountButton.setBackground(Color.cyan);
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
        scrollPane.setBounds(20,120,700, 500);
        scrollPane.getViewport().setOpaque(false);
        basePanel.add( scrollPane, null );
    }


    private void setupComboBox()
    {
        String[] bills=new String[billList.size()];

        for(int i=0;i<billList.size();i++)
        {
            bills[i]=billList.get(i).getMemberName();
        }

        if(bills.length==0)
            bills= new String[]{"No records"};

        billCheckbox=new JComboBox<>(bills);
        billCheckbox.setBounds(750,120,300,40);
        billCheckbox.setBackground(Color.cyan);
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
