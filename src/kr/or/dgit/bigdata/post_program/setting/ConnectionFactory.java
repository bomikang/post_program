package kr.or.dgit.bigdata.post_program.setting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static ConnectionFactory instance = new ConnectionFactory();
	
	public static Connection getInstance(){
		return instance.creatConnection();
	}

	private ConnectionFactory(){}
	
	private Connection creatConnection() {
		Connection connection = null;
		
		String url = "jdbc:mysql://localhost:3306/post_program";
		String user = "user_post";
		String password = "rootroot";
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("JDBC드라이버 추가 혹은 url, user, password 확인 바람");
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
}
