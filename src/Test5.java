import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.*;
public class Test5 {
public static void main(String[] args) {
	//cloDemo();
	// clobReadDemo();
	//readImgToDB();
	getImgToFile();
	System.out.println("成功");
}
static void getImgToFile(){
	Connection conn=null;
	PreparedStatement stm=null;
	ResultSet rs=null; 
	try {
		conn=JDUtil.getConnection();
		String sql="select * from t1 where id= 2"; 
		stm=conn.prepareStatement(sql);
		rs=stm.executeQuery();
		
		if(rs.next()){
			System.out.println(rs.getString("title"));
			
			InputStream in=rs.getBinaryStream("photo");
			OutputStream out=new FileOutputStream("d:/4.jpg");
			
			byte [] buff=new byte [1024];
			int len=0;
			while((len=in.read(buff))!=-1){
				out.write(buff,0,len);
			}		
			in.close();
			out.close();
		}						
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		JDUtil.close(conn, stm,null);
	}
}
static void readImgToDB(){
	Connection conn=null;
	PreparedStatement stm=null;
	try {
		conn=JDUtil.getConnection();
		String sql=" insert into t (title,photo) values (?,?) ";   //后面的 引号与前面的括号 习惯空一格，避免语句长粘在一起
		
		stm=conn.prepareStatement(sql);
		stm.setString(1, "新标题");
		
		InputStream in=new FileInputStream("d:2.PNG");
		stm.setBinaryStream(2, in);
		
		stm.executeUpdate();
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		JDUtil.close(conn, stm,null);
	}	
}

static void clobReadDemo(){
	Connection conn= null;
	PreparedStatement stm=null;
	ResultSet rs=null;
	try {
		conn=JDUtil.getConnection();
		String sql="select * from t where id=1";
		stm=conn.prepareStatement(sql);
    rs=stm.executeQuery();
    if( rs.next()){
	    System.out.println(rs.getString("title")); 
	    System.out.println(rs.getString("content"));
	    Reader r=rs.getCharacterStream("content");
	    BufferedReader br=new BufferedReader(r);
	    BufferedWriter bw=new BufferedWriter(new FileWriter("d:/wrb.txt"));
	    String str=null;
	    while((str=br.readLine())!=null){
	    	bw.write(str);
	    	bw.newLine();
	    	bw.flush();
	    }						    
	    br.close();
	    bw.close();
    }
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		JDUtil.close(conn, stm,null);
	}
}	
	static void cloDemo(){
		Connection conn=null;
		PreparedStatement stm=null;
		try{
			conn=JDUtil.getConnection();
			String sql="insert into t1(title,content) Values(?,?)";
			stm=conn.prepareStatement(sql);
			stm.setString(1, "这是标题");
			Reader r=new FileReader("d:/aaa.txt");
			stm.setCharacterStream(2, r);
			stm.executeUpdate();
			r.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDUtil.close(conn, stm,null);
		}
	}
}
