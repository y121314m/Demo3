import java.sql.*;
import java.util.*;
import java.util.Date;
public class Test3 {
	public static void main(String[] args) {

	/*Scanner scan=new Scanner(System.in);
	System.out.println("ÊäÈëÐòºÅ:");
	 String id=scan.nextLine();
	System.out.println("ÊäÈëÐÕÃû:");
	String name=scan.nextLine();
	
	
	UserInfo user = getLogin(id,name);
	if(id!= null){
		System.out.println("µÇÂ½³É¹¦");
	}else{
		System.out.println("µÇÂ½Ê§°Ü");
	}*/
		UserInfo user=new UserInfo();
		//user.setName("ÍõÈó±ò");
		//user.setBirthday(new Date());
		int result=addUser(user);
		if(result>0){
			System.out.println("³É¹¦");
		}else{
			System.out.println("Ê§°Ü");
		}
		
	}
	public static int addUser(UserInfo user){
		int result=0;
		Connection conn = null;
		PreparedStatement stm = null; 
		try{
		conn=JDUtil.getConnection();
		String sql="insert into student(id,name,grade,gender,birthday)values(?,?,?,?,?)";
		stm=conn.prepareStatement(sql);
		/*stm.setInt(1, user.getId());
		stm.setString(2, user.getName());
		stm.setFloat(3, user.getGrade());
		stm.setString(4, user.getGender());
		stm.setDate(5, new java.sql.Date(user.getBirthday().getTime()));*/
		result=stm.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, null);
		}
		return result;
	}
	
	
	
	private static UserInfo getLogin(String id, String name) {
		UserInfo user = null;
		Connection conn = null;
		PreparedStatement stm = null; 
		ResultSet rs = null;
		
		try {
			conn = JDUtil.getConnection();
			String sql = "select * from student where id = ? and name=?";
			stm = conn.prepareStatement(sql); 
			stm.setString(1, id);
			stm.setString(2, name); 
			rs = stm.executeQuery();
			if(rs.next()){
				user = new UserInfo();
				//user.setId(rs.getInt("id"));
				//user.setName(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDUtil.close(conn, stm, rs);
		}
		return user;
	}		
}
