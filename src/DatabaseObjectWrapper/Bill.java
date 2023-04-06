package DatabaseObjectWrapper;

public class Bill {

    private String billname;
    private String MemberName;
 
    private int Paid;
    private int RemainigPay;
   
    

    public int getPaid() {
        return Paid;
    }
   
    public String getMemberName() {
        return MemberName;
    }
    public String getBillName() {
        return billname;
    }
    public int getRemainingPay() {
        return RemainigPay;
    }

    
   /* public String toString()
   {
     //   return GrpId+","+GroupName;
   
    }
    */

    public Bill(String billname,String MemberName,int Paid,int RemainingPay)
    {
       this.billname=billname;
       this.MemberName=MemberName;
       this.Paid=Paid;
       this.RemainigPay=RemainingPay;
   
       
    }

 
   


}
