import java.sql.*;
public class CONNECTION {
	

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		 Connection connection = null;
	        try {
	            // below two lines are used for connectivity.
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/student_db",
		                "root","8858125365");
	            Statement statement;
	            statement = connection.createStatement();
	            ResultSet resultSet;
	            resultSet = statement.executeQuery(
	                "select * from designation");
	            int code;
	            String title;
	            while (resultSet.next()) {
	                code = resultSet.getInt("code");
	                title = resultSet.getString("title").trim();
	                System.out.println("Code : " + code
	                                   + " Title : " + title);
	            }
	            resultSet.close();
	            statement.close();
	            connection.close();
	        }
	        catch (Exception exception)
	        {
	            System.out.println(exception);
	        }
	 

	}
}
