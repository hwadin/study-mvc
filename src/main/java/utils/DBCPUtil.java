package utils;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBCPUtil {
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			DataSource ds = (DataSource)new InitialContext().lookup(
				"java:comp/env/jdbc/MySQLDBCP"
			);
			conn = ds.getConnection();
		} catch (Exception e) {
			System.out.println("connection 오류 : " + e.toString());
		}
		return conn;
	}
	
	// 자원 해제
	public static void close(AutoCloseable... closer) {
		for(AutoCloseable c : closer) {
			if(c != null) {
				try {
					c.close();
				} catch (Exception e) {}
			}
		}
	}
}








