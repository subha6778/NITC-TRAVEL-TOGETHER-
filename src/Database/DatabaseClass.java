package Database;

import DatabaseObjectWrapper.*;

//mport java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
//import java.util.Date;
@SuppressWarnings("unused")

public class DatabaseClass {

    private static final DatabaseClass db = new DatabaseClass();
    private Connection conn=null;

    private DatabaseClass()  {
        try
        {

            // instantiate com.mysql.jdbc.Driver. This object registers itself with the DriverManager
            Class.forName("com.mysql.jdbc.Driver");

            //connect to database
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/student_db",
                    "root","8858125365");

        } catch (SQLException ex)
        {
            System.out.println("cannot query the database");
        }
        catch (ClassNotFoundException ex)
        {
            System.out.println("cannot connect to MySQL database");
        }

    }

    public static DatabaseClass getDataAccessObject()
    {
        return db;
    }

   // public Person getCustomer(String username)

    public Person getUser(String username)
    {
        Person person=null;

        try {
            String sql= "SELECT * FROM user where username=?";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next())
                person= new User(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));


            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }




    public Person getAdmin(String username)
    {
        Person person=null;

        try {
            String sql= "SELECT * FROM admin where username=?";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet= preparedStatement.executeQuery();

            while (resultSet.next())
                person= new Admin(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getString(8));

            return person;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public boolean userLoginValidate(String userName, String userPass) throws SQLException
    {

        String sql="SELECT * FROM user\n"
                + "WHERE username=?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet rs=pstm.executeQuery();
        String pass;


        while(rs.next())
        {
            pass=rs.getString("password");

            if( pass.equals(userPass)){
                return true;
            }
        }
        return false;
    }


    public boolean adminLoginValidate(String userName, String userPass) throws SQLException
    {

        String sql="SELECT * FROM admin\n"
                + "WHERE username=?";
        PreparedStatement pstm=conn.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet rs=pstm.executeQuery();
        String pass;


        while(rs.next())
        {
            pass=rs.getString("password");

            if( pass.equals(userPass)){
                return true;
            }
        }
        return false;
    }



    public boolean addUser(String userName, String password, String name, String email,String Age,String Address,String gender,String mobile_no)
    {
        try
        {
            String sql= "INSERT into user(username,password,name,email,Age,Address,gender,mobile_no) VALUES (?,?,?,?,?,?,?,?)";
            System.out.print(userName);
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            preparedStatement.setString(2,password);
            System.out.print(password);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,email);
            preparedStatement.setString(5,Age);
            preparedStatement.setString(6,Address);
            System.out.print(Address);
            preparedStatement.setString(7,gender);
            System.out.print(gender);
            preparedStatement.setString(8,mobile_no);
            System.out.print(mobile_no);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 

    public boolean updateUser(String currentUserName,String newUserName,String password,String name,String email,String Age,String Address,String gender,String mobile_no)
    {
    	   System.out.print("nuser: "+currentUserName);
        try {
        	 String sql= "UPDATE user set password=?,name=?,Age=?,Address=?,gender=?,mobile_no=?  WHERE username=?";

             PreparedStatement preparedStatement= conn.prepareStatement(sql);
             //preparedStatement.setString(1,newUserName);
             preparedStatement.setString(1,password);
             preparedStatement.setString(2,name);
            // preparedStatement.setString(4,email);
             preparedStatement.setString(3,Age);
             preparedStatement.setString(4,Address);
             preparedStatement.setString(5,gender);
             preparedStatement.setString(6,mobile_no);
           // preparedStatement.setString(9,currentUserName);
           
             preparedStatement.setString(7,currentUserName);
       
      
            preparedStatement.executeUpdate();
            System.out.println("done");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateAdmin(String currentUserName,String newUserName,String password,String name,String email,String Age,String Address,String gender,String mobile_no)
    {
    	   System.out.print("nuser: "+currentUserName);

        try {
            String sql= "UPDATE admin set username=?,password=?,name=?,email=?,Age=?,Address=?,gender=?,mobile_no=? WHERE username=?";

            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,newUserName);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,name);
            preparedStatement.setString(4,email);
            //preparedStatement.setString(5,currentUserName);
            System.out.print("nuser: "+currentUserName);
            preparedStatement.setString(5,Age);
            preparedStatement.setString(6,Address);
            preparedStatement.setString(7,gender);
            preparedStatement.setString(8,mobile_no);
            preparedStatement.setString(9,currentUserName);

            preparedStatement.executeUpdate();
            System.out.println("done");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @SuppressWarnings("static-access")
	public boolean createGroup(String GroupId,String groupName,String source,String destination,String dt,String dtTime, String arrivalDate,String arrivalTime,String currentPersonUserId,int capacity,int mealamount)
    {
    	//String leaderUsername=currentPerson.getUserName();
    	//Date date = new SimpleDateFormat("dd/mm/yyyy")
               // .parse(dt);
    	//System.out.print(date);
    	 Date date = new Date(0);
    	// prest.setDate(3, date.valueOf("1998-1-17"));
    	//System.out.print(leaderUsername);
        try {
            String sql="INSERT INTO group5(GrpId,GrpName,Src,Dest,deptTime,DeptDate,leaderId,arrivalDate,arrivalTime,Capacity,mealprice) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,GroupId);
            preparedStatement.setString(2,groupName);
            preparedStatement.setString(3,source);
            preparedStatement.setString(4,destination);
            preparedStatement.setString(5,dtTime);
            preparedStatement.setDate(6, date.valueOf(dt));
            preparedStatement.setString(7,currentPersonUserId);
            preparedStatement.setString(8,arrivalDate);
            preparedStatement.setString(9,arrivalTime);
            preparedStatement.setInt(10,capacity);
            preparedStatement.setInt(11,mealamount);
            preparedStatement.executeUpdate();
           /// String BillName=GroupId+groupName;
            InsertBillGroup(GroupId,groupName);
            
            return true;
        } catch (SQLException e)
        {
            e.printStackTrace();
            if(e instanceof SQLIntegrityConstraintViolationException)
               return false;
            return false;
        }
    }
    public List<Group> getAvailableGroup(String userId)
    {
        List<Group> grouplist=new ArrayList<>();
        List<String> groupIdlist=new ArrayList<>();
        List<String> groupNamelist=new ArrayList<>();

        try {
            String sql="SELECT * from group5";

            PreparedStatement preparedStatement=conn.prepareStatement(sql);

            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String GroupId=resultSet.getString(1);
                String GroupName=resultSet.getString(2);
                String Source= resultSet.getString(3);
                String Destination=resultSet.getString(4);
                String DeptTime=resultSet.getString(5);
                Date date=resultSet.getDate(6);
                String temp=date.toString();
                String DeptDate=temp.substring(0, 10);
                String leaderId=resultSet.getString(7);
                String arrivalDate=resultSet.getString(8);
                String arrivalTime=resultSet.getString(9);
                int capacity=resultSet.getInt(10);
                
                System.out.print(DeptDate);
                



               grouplist.add(new Group(GroupId,GroupName,Source,Destination,DeptTime,DeptDate,arrivalDate,arrivalTime,leaderId,capacity));

            }
            return  grouplist;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
        
        
        
    }
    public List<Group> getMyGroup(Person currentPerson)
    {
    	String userId=currentPerson.getUserName();
    	String grpID = null;
    	String grpNAME = null;
        List<Group> grouplist=new ArrayList<>();
        List<String> groupIdlist=new ArrayList<>();
        List<String> groupNamelist=new ArrayList<>();
        try {
            String sql="SELECT GrpId,GrpName from group_user WHERE username=?";
      
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,userId);

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next())
            {
                grpID=resultSet.getString(1);
                grpNAME=resultSet.getString(2);
                groupIdlist.add(grpID);
                groupNamelist.add(grpNAME);
                
              
                System.out.print("nameG :"+grpNAME);
            }
               //System.out.print(GrpName);
  
 
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
     for(int i=0;i<groupNamelist.size();i++)
     {
       try {
            String sql2="SELECT * from group5  WHERE GrpId=? and GrpName=?";

            PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
            
            preparedStatement2.setString(1,groupIdlist.get(i));
            preparedStatement2.setString(2,groupNamelist.get(i));
          

            ResultSet resultSet2=preparedStatement2.executeQuery();
            while (resultSet2.next())
            {	
            	String GroupId=resultSet2.getString(1);
                String GroupName=resultSet2.getString(2);
                String Source= resultSet2.getString(3);
                String Destination=resultSet2.getString(4);
                String DeptTime=resultSet2.getString(5);
                Date date=resultSet2.getDate(6);
                String temp=date.toString();
                String DeptDate=temp.substring(0, 10);
                System.out.print(DeptDate);
                String leaderId=resultSet2.getString(7);
                String arrivalDate=resultSet2.getString(8);
                String arrivalTime=resultSet2.getString(9);
                int capacity=resultSet2.getInt(10);
                
                
                
               grouplist.add(new Group(GroupId,GroupName,Source,Destination,DeptTime,DeptDate,arrivalDate,arrivalTime,leaderId,capacity));
            }
           // return grouplist;

        }
        catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
      // return grouplist;
     }
     return grouplist;
    }
    
    public boolean optOutMember(String GroupId,String GroupName,String currentPersonUserId)
    {
         //String LeaderId=null;
  
    /*  try {
            String sql="SELECT leaderId FROM group5 WHERE GrpId=? and GrpName=?";
            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,GroupId);
            preparedStatement.setString(2,GroupName);
            System.out.print("Hello: "+sql);
            ResultSet resultSet=preparedStatement.executeQuery();
           String LeaderId=resultSet.getString(1);
           System.out.print("Leader: "+LeaderId);
    	    if(LeaderId.equals(currentPersonUserId))
    	    	return false;
    	    
    	    System.out.print("Hello: "+GroupName);
       	 try {
               String sql2="DELETE FROM group_user where GrpId=? and GrpName=? and username=?";
               PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
               preparedStatement2.setString(1,GroupId);
               preparedStatement2.setString(2,GroupName);
                preparedStatement2.setString(3,currentPersonUserId);
             
               preparedStatement2.executeUpdate();

      
               return true;
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    	    
    	    
    	    
    	    
    } catch (SQLException e) {
        e.printStackTrace();
        return true;
    }
    */
        
    	 System.out.print("Hello: "+GroupName);
    	 try {
            String sql2="DELETE FROM group_user where GrpId=? and GrpName=? and username=?";
            PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
            preparedStatement2.setString(1,GroupId);
            preparedStatement2.setString(2,GroupName);
             preparedStatement2.setString(3,currentPersonUserId);
          
            preparedStatement2.executeUpdate();
            removeGlobalBill(GroupId,GroupName,currentPersonUserId);

   
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    
    
    }
    
    
    
    
    
    
    
    
    public boolean addMember(String GroupId,String GroupName,String userId,String MealOptions)
    {
       
    	System.out.print(GroupId);

        try {
     
             String sql="INSERT INTO group_user(GrpId,GrpName,username) VALUES(?,?,?)";
            PreparedStatement preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,GroupId);
            preparedStatement.setString(2,GroupName);
            preparedStatement.setString(3,userId);
            preparedStatement.executeUpdate();
            InsertGlobatBill(GroupId,GroupName,userId);
            addMealPrice(GroupId,GroupName,userId,MealOptions);
            
            
            return true;

        }  catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
      
 
    }
   
      public Group getGroupDetails(String GroupId,String GroupName)
    {
        List<Group> grouplist=new ArrayList<>();
        List<String> groupIdlist=new ArrayList<>();
        List<String> groupNamelist=new ArrayList<>();
        Group grp = null;

        try {
            String sql="SELECT * from group5 Where GrpId=? and GrpName=?";

            PreparedStatement preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,GroupId);
            preparedStatement.setString(2,GroupName);
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String groupId=resultSet.getString(1);
                String groupName=resultSet.getString(2);
                String Source= resultSet.getString(3);
                String Destination=resultSet.getString(4);
                String DeptTime=resultSet.getString(5);
                Date date=resultSet.getDate(6);
                String temp=date.toString();
                String DeptDate=temp.substring(0, 10);
                String leaderId=resultSet.getString(7);
                String arrivalDate=resultSet.getString(8);
                String arrivalTime=resultSet.getString(9);
                int capacity=resultSet.getInt(10);
                
                System.out.print(DeptDate);
                



               grp=new Group(groupId,groupName,Source,Destination,DeptTime,DeptDate,arrivalDate,arrivalTime,leaderId,capacity);

            }
            return  grp;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
       
    } 
      public int getNumberOfMember(String GroupId,String GroupName)
      {
          int count=0;

          try {
              String sql="Select count(*) from group_user where GrpId=? and GrpName=?";
              PreparedStatement preparedStatement=conn.prepareStatement(sql);
              preparedStatement.setString(1,GroupId);
              preparedStatement.setString(2,GroupName);
             
              ResultSet resultSet= preparedStatement.executeQuery();

              while (resultSet.next())
                  count=resultSet.getInt(1);

              return count;

          } catch (SQLException e) {
              e.printStackTrace();
              return count;
          }
      }
      /*
       * getLeaderName(grp.getLeaderId());
       */
        
      public String getLeaderName(String LeaderId)
      {
          String LeaderName=null;

          try {
              String sql="Select name from user where username=?";
              PreparedStatement preparedStatement=conn.prepareStatement(sql);
              preparedStatement.setString(1,LeaderId);
             
              ResultSet resultSet= preparedStatement.executeQuery();

              while (resultSet.next())
                  LeaderName=resultSet.getString(1);

              return LeaderName;

          } catch (SQLException e) {
              e.printStackTrace();
              return LeaderName;
          }
      }
      /*
       getContactNumber(grp.getLeaderId());
       */
      public String  getContactNumber(String Id)
      {
          String num=null;

          try {
              String sql="Select mobile_no from user where username=?";
              PreparedStatement preparedStatement=conn.prepareStatement(sql);
              preparedStatement.setString(1,Id);
             
              ResultSet resultSet= preparedStatement.executeQuery();

              while (resultSet.next())
                  num=resultSet.getString(1);

              return num;

          } catch (SQLException e) {
              e.printStackTrace();
              return num;
          }
      }
  
      public int getGroupCapacity(String GroupId,String GroupName)
      {
          int cap=0;

          try {
              String sql="Select Capacity from group5 where GrpId=? and GrpName=?";
              PreparedStatement preparedStatement=conn.prepareStatement(sql);
              preparedStatement.setString(1,GroupId);
              preparedStatement.setString(2,GroupName);
             
              ResultSet resultSet= preparedStatement.executeQuery();

              while (resultSet.next())
                  cap=resultSet.getInt(1);

              return cap;

          } catch (SQLException e) {
              e.printStackTrace();
              return cap;
          }
      }
      private boolean InsertBillGroup(String GroupId,String groupName)
      {
    	  String Billname=GroupId+groupName;
    	  try {
    		     
              String sql="INSERT INTO billggroup(GrpId,GrpName,billname,totalAmount) VALUES(?,?,?,?)";
             PreparedStatement preparedStatement= conn.prepareStatement(sql);
             preparedStatement.setString(1,GroupId);
             preparedStatement.setString(2,groupName);
            // String Billname=GroupId+groupName;
             preparedStatement.setString(3,Billname);
             preparedStatement.setInt(4,0);
             preparedStatement.executeUpdate();
             //CreateBill(Billname);
             return true;

         }  catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
       
      }
      private boolean InsertGlobatBill(String GroupId,String groupName,String userId)
      {
    	 String name=getLeaderName(userId);
    	 String Billname=GroupId+groupName;
    	 int paid = 0,rem = 0,mealprice=0;
    	  try {
    		     
    		   String sql="INSERT INTO GlobalBill(billname,Name,paid,remainingPay,mealprice) VALUES(?,?,?,?,?)";
             PreparedStatement preparedStatement= conn.prepareStatement(sql);
             preparedStatement.setString(1,Billname);
             preparedStatement.setString(2,name);
             preparedStatement.setInt(3,paid);
             preparedStatement.setInt(4,rem);
             preparedStatement.setInt(5,mealprice);
             preparedStatement.executeUpdate();
             
             return true;

         }  catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
       
      }
     private boolean removeGlobalBill(String GroupId,String GroupName,String currentPersonUserId)
     {
    	 String BillName=GroupId+GroupName;
    	 String username=getLeaderName(currentPersonUserId);
    	 try {
             String sql="DELETE FROM globalbill where billname=? and Name=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1,BillName);
             preparedStatement.setString(2,username);
             preparedStatement.executeUpdate();

             return true;
         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
     }
     public List<Bill> getBill(String GroupId,String GroupName)
     {
         List<Bill> billlist=new ArrayList<>();
         //List<String> groupIdlist=new ArrayList<>();
         //List<String> groupNamelist=new ArrayList<>();
         String BillName=GroupId+GroupName;

         try {
             String sql="SELECT * from globalbill where billname=?";

             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1,BillName);
             ResultSet resultSet=preparedStatement.executeQuery();

             while (resultSet.next())
             {
                 String billname=resultSet.getString(1);
                 String MemberName=resultSet.getString(2);
                 int paid=resultSet.getInt(3);
                 int remaining=resultSet.getInt(4);
                 
                billlist.add(new Bill(billname,MemberName,paid,remaining));
                //System.out.prin(billlist[0].getPaid());

             }
             return  billlist;

         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         }
        
     }
     /*
      addPaidAmount(BillName,paidAmount))
      */
     public boolean updatePaidAmount(String BillName,int paidAmount,String MemberName,String GroupId,String GroupName)
     {
         //int quantity=getPaidAmount(BillName,paidAmount);
    	 int NumberofMember=getNumberOfMember(GroupId,GroupName);

         try {
             String sql="UPDATE globalbill set paid=? where billname=? and Name=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setInt(1,paidAmount);
             preparedStatement.setString(2,BillName);
             preparedStatement.setString(3,MemberName);
             preparedStatement.executeUpdate();
             updateRemaingAmount(BillName,paidAmount,MemberName,GroupId,GroupName);
             
             return true;

         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
     }
     
     public boolean updateRemaingAmount(String BillName,int paidAmount,String MemberName,String GroupId,String GroupName)
     {
    	 
    	 int mealprice=getMemberMealPrice(BillName,MemberName);
    	 
    	 
    	 
    	 
         //int quantity=getPaidAmount(BillName,paidAmount);
    	 int NumberofMember=getNumberOfMember(GroupId,GroupName);
    	 int needToPay=0;
    	 int TotalAmount=getTotalAmount(GroupId,GroupName);
         if(TotalAmount==0)
        	 needToPay=0;
         else
         {
        	 needToPay=TotalAmount/NumberofMember;
         }
          int remainingbal=needToPay-paidAmount+mealprice;	 

         try {
             String sql="UPDATE globalbill set remainingPay=? where billname=? and Name=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setInt(1,remainingbal);
             preparedStatement.setString(2,BillName);
             preparedStatement.setString(3,MemberName);
             preparedStatement.executeUpdate();
  
             
             return true;

         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
	
     }
     public boolean updateTotalAmount(String BillName,int Amount,String GroupId,String GroupName)
     {
        
    	 List<String> memberList= getMemberList(BillName,GroupId,GroupName);
    	 int n=memberList.size();
         try {
             String sql="UPDATE billggroup set totalAmount=? where GrpId=? and GrpName=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setInt(1,Amount);
             preparedStatement.setString(2,GroupId);
             preparedStatement.setString(3,GroupName);
             preparedStatement.executeUpdate();
            // int paidAmount=getPaidAmount(BillName,String MemberName)
             //updateRemaingAmount(BillName,int paidAmount,String MemberName,String GroupId,String GroupName)
             for(int i=0;i<n;i++)
             {
            	 int paidAmount=getPaidAmount(BillName,memberList.get(i));
            	 updateRemaingAmount(BillName,paidAmount,memberList.get(i),GroupId,GroupName);
            	 
            	 
             }
             
             return true;

         } catch (SQLException e) {
             e.printStackTrace();
             return false;
         }
	
     }
     
     
     public int getTotalAmount(String GroupId,String GroupName)
     {
         int amount=0;

         try {
             String sql="SELECT totalAmount from billggroup where GrpId=? and GrpName=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1,GroupId);
             preparedStatement.setString(2,GroupName);
             ResultSet resultSet=preparedStatement.executeQuery();

             while (resultSet.next())
                 amount=resultSet.getInt(1);

             return amount;

         } catch (SQLException e) {
             e.printStackTrace();
             return amount;
         }
     }

     
     private int getPaidAmount(String BillName,String MemberName)
     {
         int amount=0;

         try {
             String sql="SELECT paid from globalbill where billname=? and Name=?";
             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1,BillName);
             preparedStatement.setString(2,MemberName);
             ResultSet resultSet=preparedStatement.executeQuery();

             while (resultSet.next())
                 amount=resultSet.getInt(1);

             return amount;

         } catch (SQLException e) {
             e.printStackTrace();
             return amount;
         }
     }
     
     public List<String> getMemberList(String BillName,String GroupId,String GroupName)
     {
         List<String> memberList =new ArrayList<>();
         //List<String> groupIdlist=new ArrayList<>();
         //List<String> groupNamelist=new ArrayList<>();
         //String BillName=GroupId+GroupName;

         try {
             String sql="SELECT Name from globalbill where billname=?";

             PreparedStatement preparedStatement=conn.prepareStatement(sql);
             preparedStatement.setString(1,BillName);
             ResultSet resultSet=preparedStatement.executeQuery();

             while (resultSet.next())
             {
                 String name=resultSet.getString(1);
             
                 
                 memberList.add(name);
               
             }
             return  memberList;

         } catch (SQLException e) {
             e.printStackTrace();
             return null;
         }
        
     }
    // getDueGroup();


  public List<Group> getDueGroup()
    {
        List<Group>avlGrpList= getAvailableGroup("ok");
        int n=avlGrpList.size();
         List<Group> Duegrouplist=new ArrayList<>();
         Date date= new Date(System.currentTimeMillis());

        //localdate object needed to minus days to date;
        LocalDate localDate=LocalDate.parse(date.toString());
        localDate=localDate.minusDays(5);
       // Date date1 = new Date(0);
   

        
        for(int i=0;i<n;i++)
        {
                  
           String temp=avlGrpList.get(i).getArrivalDate();
           LocalDate d1= LocalDate.parse(temp);
           if(d1.compareTo(localDate) <=0) 
           {
        	   Duegrouplist.add(avlGrpList.get(i));
        	   
           }
           
        }
        
         //System.out.print(Duegrouplist.get(0).getArrivalDate());
            return  Duegrouplist;

     
    }
  
  //RemoveDueGroup(GroupId,GroupName)
  /*
   public boolean RemoveGroup(Strinng GroupId,String GroupName)
    //System.out.print("Hello: "+GroupName);
    	 try {
            String sql2="DELETE FROM group_user where GrpId=? and GrpName=? and username=?";
            PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
            preparedStatement2.setString(1,GroupId);
            preparedStatement2.setString(2,GroupName);
             preparedStatement2.setString(3,currentPersonUserId);
          
            preparedStatement2.executeUpdate();
            removeGlobalBill(GroupId,GroupName,currentPersonUserId);

   
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    
   */
  public boolean DeleteDueGroup(String GroupId,String GroupName)
  {
	  try {
          String sql2="DELETE FROM group5 where GrpId=? and GrpName=?";
          PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
          preparedStatement2.setString(1,GroupId);
          preparedStatement2.setString(2,GroupName);
         
          preparedStatement2.executeUpdate();
          DeleteGroup_User(GroupId,GroupName);
          DeleteBillG(GroupId,GroupName);
          String BillName=GroupId+GroupName;
          DeleteGlobalBill(BillName);

 
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }
  public boolean DeleteGroup_User(String GroupId,String GroupName)
  {
	  try {
          String sql2="DELETE FROM group_user where GrpId=? and GrpName=?";
          PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
          preparedStatement2.setString(1,GroupId);
          preparedStatement2.setString(2,GroupName);
          preparedStatement2.executeUpdate();
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }
  public boolean  DeleteBillG(String GroupId,String GroupName)
  {
	  try {
          String sql2="DELETE FROM billggroup where GrpId=? and GrpName=?";
          PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
          preparedStatement2.setString(1,GroupId);
          preparedStatement2.setString(2,GroupName);
          preparedStatement2.executeUpdate();
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }
  public boolean  DeleteGlobalBill(String BillName)
  {
	  try {
          String sql2="DELETE FROM globalbill where billname=?";
          PreparedStatement preparedStatement2=conn.prepareStatement(sql2);
          preparedStatement2.setString(1,BillName);
          preparedStatement2.executeUpdate();
          return true;
      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
  }
  public List<String> getName_MAIL_lList(String GroupId,String GroupName)
  {
      List<String> lst =new ArrayList<>();
     
      try {
          String sql="SELECT user.name,user.email FROM user INNER JOIN group_user ON user.username=group_user.username WHERE group_user.GrpId=? and group_user.GrpName=?";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setString(1,GroupId);
          preparedStatement.setString(2,GroupName);
          ResultSet resultSet=preparedStatement.executeQuery();

          while (resultSet.next())
          {
              String name=resultSet.getString(1);
              String emailId=resultSet.getString(2);
              String str=name+"#"+emailId;
              
              lst.add(str);
            
          }
          return  lst;

      } catch (SQLException e) {
          e.printStackTrace();
          return null;
      }
     
  }
  
  public int getMealPrice(String GroupId,String GroupName)
  {
	  
	  
	  int mealprice=0;

      try {
          String sql="Select mealprice from group5 where GrpId=? and GrpName=?";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setString(1,GroupId);
          preparedStatement.setString(2,GroupName);
         
          ResultSet resultSet= preparedStatement.executeQuery();

          while (resultSet.next())
              mealprice=resultSet.getInt(1);

          return mealprice;

      } catch (SQLException e) {
          e.printStackTrace();
          return mealprice;
      }
	   
	  
  }
  
  public boolean addMealPrice(String GroupId,String GroupName,String currentPersonUserId,String MealOptions)
  {
	  String BillName=GroupId+GroupName;
	  boolean bool=MealOptions.equals("Want meal");
	  int val = (bool) ? 1 : 0;
	  if(val==0)
		  return true;
	  String name=getLeaderName(currentPersonUserId);
	  int mealprice=getMealPrice(GroupId,GroupName);
	  System.out.print("mealPrice"+mealprice);
	  
	  try {
          String sql="UPDATE globalbill set mealprice=? where billname=? and Name=?";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setInt(1,mealprice);
          preparedStatement.setString(2,BillName);
          preparedStatement.setString(3,name);
          preparedStatement.executeUpdate();
          updateRemaingAmount(BillName,0,name,GroupId,GroupName);
          
          return true;

      } catch (SQLException e) {
          e.printStackTrace();
          return false;
      }
	  
   }
  
///getMemberMealPrice(BillName,MemberName);  
 
  int getMemberMealPrice(String BillName,String MemberName)
  {
	  
	  
	  int mealprice=0;

      try {
          String sql="Select mealprice from globalbill where billname=? and Name=?";
          PreparedStatement preparedStatement=conn.prepareStatement(sql);
          preparedStatement.setString(1,BillName);
          preparedStatement.setString(2,MemberName);
         
          ResultSet resultSet= preparedStatement.executeQuery();

          while (resultSet.next())
              mealprice=resultSet.getInt(1);

          return mealprice;

      } catch (SQLException e) {
          e.printStackTrace();
          return mealprice;
      }
	   
	  
  }
///  
  public List<Group> getActiveGroup(Person currentPerson)
  {
      List<Group>myGrpList= getMyGroup(currentPerson);
      int n=myGrpList.size();
       List<Group> activegrouplist=new ArrayList<>();
       Date date= new Date(System.currentTimeMillis());

      //localdate object needed to minus days to date;
      LocalDate localDate=LocalDate.parse(date.toString());
      //localDate=localDate.minusDays(5);
     // Date date1 = new Date(0);
 

      
      for(int i=0;i<n;i++)
      {
                
         String temp=myGrpList.get(i).getArrivalDate();
         LocalDate d1= LocalDate.parse(temp);
         if(d1.compareTo(localDate) >=0) 
         {
      	   activegrouplist.add(myGrpList.get(i));
      	   
         }
         
      }
      
       //System.out.print(Duegrouplist.get(0).getArrivalDate());
          return  activegrouplist;

   
  }
  public List<Group> getPastGroup(Person currentPerson)
  {
      List<Group>myGrpList= getMyGroup(currentPerson);
      int n=myGrpList.size();
       List<Group> pastgrouplist=new ArrayList<>();
       Date date= new Date(System.currentTimeMillis());

      //localdate object needed to minus days to date;
      LocalDate localDate=LocalDate.parse(date.toString());
      //localDate=localDate.minusDays(5);
     // Date date1 = new Date(0);
 

      
      for(int i=0;i<n;i++)
      {
                
         String temp=myGrpList.get(i).getArrivalDate();
         LocalDate d1= LocalDate.parse(temp);
         if(d1.compareTo(localDate) <0) 
         {
      	   pastgrouplist.add(myGrpList.get(i));
      	   
         }
         
      }
      
       //System.out.print(Duegrouplist.get(0).getArrivalDate());
          return  pastgrouplist;

   
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

}
