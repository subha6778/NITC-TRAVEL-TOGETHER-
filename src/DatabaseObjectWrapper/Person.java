package DatabaseObjectWrapper;

public abstract class Person {

    String userName;
    String fullName;
    String password;
    String mobile_no;
    String email;
    String Age;
    String Address;
    String Gender;
    

    public String getUserName() {
        return userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    public String getAddress() 
    {
        return Address;
    }
    public String getGender()
    {
        return Gender;
    }
    public String getmobile_no()
    {
        return mobile_no;
    }
    public String getAge()
    {
        return Age;
    }


    public Person(String userName, String password,String fullName, String email,String Age,String Address,String Gender,String mobile_no)
    {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.Age=Age;
        this.Address=Address;
        this.Gender=Gender;
        this.mobile_no=mobile_no;
    }

    @Override
    public String toString() {
        return this.userName+"  "+this.password+"  "+this.fullName+"  "+this.email+" "+this.Age+" "+this.Address+" "+this.Gender+" "+this.mobile_no;
    }
}
