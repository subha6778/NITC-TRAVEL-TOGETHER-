package DatabaseObjectWrapper;

public class Group {

    private String GroupName;
    private String GrpId;
    private String Source;
    private String Destination;
    private String DeptTime;
    private String DeptDate;
    private String ArrivalTime;
    private String ArrivalDate;
    private String LeaderId;
    private int Capacity;
    

    public int getCapacity() {
        return Capacity;
    }
    public String getLeaderId() {
        return LeaderId;
    }

    public String getGroupName() {
        return GroupName;
    }

    public String getGroupId() {
        return GrpId;
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }

    public String getDepartureTime() {
        return DeptTime;
    }
    public String getDepartureDate() {
        return DeptDate;
    }
    public String getArrivalDate() {
        return ArrivalDate;
    }
    public String getArrivalTime() {
        return ArrivalTime;
    }

    public String toString()
    {
        return GrpId+","+GroupName;
    }

    public Group(String GrpId,String GroupName, String Source, String Destination, String DeptTime, String DeptDate,String ArrivalDate,String ArrivalTime,String LeaderId,int Capacity) {
       this.GrpId=GrpId;
       this.GroupName=GroupName;
       this.Source=Source;
       this.Destination=Destination;
       this.DeptTime=DeptTime;
       this.DeptDate=DeptDate;
       this.ArrivalDate=ArrivalDate;
       this.ArrivalTime=ArrivalTime;
       this.LeaderId=LeaderId;
       this.Capacity=Capacity;
    }

 
   


}
