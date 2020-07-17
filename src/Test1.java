import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;

public class Test1 {
public static void main(String[] args) throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
	Statement stm=conn.createStatement();
	ResultSet rs=stm.executeQuery("select * from student");
	   
	while(rs.next()){
		String sno=rs.getString("SNo");
		String sname=rs.getString("SName");
		String SSex=rs.getString("SSex");
		Integer Sage=rs.getInt("Sage");
			
		System.out.println(sno+"\t"+sname+"\t"+SSex+"\t"+Sage);	
	}
	//清理资源
	rs.close();
	stm.close();
	conn.close();  


}
}
