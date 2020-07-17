import java.sql.*;

public class Test2 {
public static void main(String[] args) {
	Connection conn=null;
	Statement stm=null;
	ResultSet rs=null;
	
	try {
		conn=JDUtil.getConnection();
		stm=conn.createStatement();
		String sql="select * from student";
		rs=stm.executeQuery(sql);
		while(rs.next()){
			int id=rs.getInt("id");
			String name=rs.getString("name");
			Float grade=rs.getFloat("grade");
			String gender=rs.getString("gender");
		
			System.out.println("id是"+id+",姓名是:"+name+",分数是:"+grade+",性别是:"+gender);
			//System.out.println("学号是"+sno+",姓名是:"+sname);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}finally{
		JDUtil.close(conn,stm,rs);
	}
	
}
}
