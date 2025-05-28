package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtill {
	
	static {
//		System.out.println("static 블럭");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/playnfrun?serverTimezone=UTC";
		String user = "playuser";
		String password = "root";


		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return con;
		
	}
	
	public static void close(ResultSet rs, Statement stmt, Connection con) {
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
