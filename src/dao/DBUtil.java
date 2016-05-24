package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String user = "root";
	private static final String senha = "admin";
	private static final String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	private static final String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	private static final String url = "jdbc:mysql://"+host+":"+port+"/rotasjsf";
	private Connection con;
	private static DBUtil util;
	
	private DBUtil() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		con = DriverManager.getConnection(url,user,senha);
	}
	
	public static DBUtil getDBUtil() throws ClassNotFoundException, SQLException{
		if(util == null || util.con.isClosed()){
			util = new DBUtil();
		}
		return util;
	}
	
	public Connection getConnection(){
		return this.con;
	}
}
