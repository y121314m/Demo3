
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import java.sql.*;
public class Task3 {
	public static void main(String[] args) {
		/*UserInfo user=new UserInfo();
		user.setSName("于晓曼");
		user.setSage(20);
		user.setSNo("18111111");*/
		//int result=addUser(user);
		//int result=updateUser(user);					
		/*int result=delUser("201215126");
		if(result>0){
			System.out.println("删除成功");
		}else{
			System.out.println("删除失败");
		}*/
     	/*List<UserInfo> userlist=getList("a","男");
		for(UserInfo user:userlist){
			System.out.println(user);
		}*/
	}
	
	public static int addUser(UserInfo user){
		int result = 0;
		Connection conn = null;
		Statement stm = null;
		
		try {
			conn = JDUtil.getConnection();
			stm = conn.createStatement();
			String sql = "insert into student(SNo,SName,Sage) values('"+user.getSNo()+"','"+user.getSName()+"',"+user.getSage()+")";
			result = stm.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, null);
		}
		
		return result ;
	}
	
	//修改数据
	public static int updateUser(UserInfo user){
		int result = 0;
		Connection conn = null;
		Statement stm = null;
		
		try{
			conn = JDUtil.getConnection();
			stm = conn.createStatement();
			String sql="update student set SName='"+user.getSName()+"',Sage = "+user.getSage()+" where SNo="+user.getSNo();
			result = stm.executeUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, null);
		}
		return result;
	}
	
	//删除数据
	public static int delUser(String sno){
		int result = 0;
		Connection conn = null;
		Statement stm = null;
		
		try{
			conn = JDUtil.getConnection();
			stm = conn.createStatement();
			String sql = "delete from student where sno="+sno;
			result = stm.executeUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, null);
		}
		
		return result;
	}
	private static List<UserInfo> getList(String SName, String SSex) {
		List<UserInfo> userlist=new ArrayList<UserInfo>();
		UserInfo user=null;
		Connection conn=null;
		PreparedStatement stm = null;
		ResultSet rs=null;
		try {
			conn = JDUtil.getConnection();
			
			String sql= "select * from student where 1=1";
			
			if(!(SName == null || "".equals(SName))){
				sql += " and SName like '%"+SName+"%' ";
			}
			if(!(SSex == null || "".equals(SSex))){
				sql += " and SSex like '%"+SSex+"%' ";
			}		
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			while(rs.next()){
				user = new UserInfo();
				user.setSNo(rs.getString("SNo"));
				user.setSName(rs.getString("SName"));
				user.setSSex(rs.getString("SSex"));
				user.setSage(rs.getInt("Sage"));
				userlist.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDUtil.close(conn, stm, rs);
		}
		return userlist;
	}
	//根据id查询用户
	public static UserInfo getUserById(String SNo) {
		UserInfo user = null;
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try {
			conn = JDUtil.getConnection();
			stm = conn.createStatement();
			String sql = "select * from userinfo where SNo="+SNo;
			rs = stm.executeQuery(sql);
			if(rs.next()) {
				user = new UserInfo();
				user.setSNo(rs.getString("SNo"));
				user.setSName(rs.getString("SName"));
				user.setSage(rs.getInt("Sage"));
				//user.setScredits(rs.getInt("Scredits"));
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			JDUtil.close(conn, stm, rs);
		}
			
		return user;
	}
}
