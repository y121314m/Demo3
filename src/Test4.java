import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;


public class Test4{
	public static void main(String[] args) {
		List<UserInfo> userlist=getList("s","ÄÐ");
		for(UserInfo user:userlist){
			System.out.println(user);
		}
}

	private static List<UserInfo> getList(String name, String gender) {
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		Connection conn=null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		try {
			conn = JDUtil.getConnection();
			
			String sql= "select * from student where 1=1";
			
			if(!(name == null || "".equals(name))){
				sql += " and name like '%"+name+"%' ";
			}
			if(!(gender == null || "".equals(gender))){
				sql += " and gender like '%"+gender+"%' ";
			}		
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				user = new UserInfo();
				//user.setId(rs.getInt("id"));
				//user.setName(rs.getString("name"));
				//user.setSsex(rs.getString("ssex"));
				userlist.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, rs);
		}
		return userlist;
	}
	}	