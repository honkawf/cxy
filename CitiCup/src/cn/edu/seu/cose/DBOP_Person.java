package cn.edu.seu.cose;

import java.sql.*;
import java.util.ArrayList;


public class DBOP_Person {
	DBOP_Person()
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID="CITICUP";
		USERNAME="SYSTEM";
		PASSWORD="citicup";
	}

	DBOP_Person(String My_Sid,String My_Username,String My_Password)
	{
		url="jdbc:oracle:thin:@localhost:1521/";
		SID=My_Sid;
		USERNAME=My_Username;
		PASSWORD=My_Password;
	}

	private Connection Connect_person_db() throws ClassNotFoundException,SQLException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");      
		Connection conn =DriverManager.getConnection(url+SID,USERNAME,PASSWORD);     
		conn.setAutoCommit(false);
		return conn;    
	}

	public String Query_all() throws ClassNotFoundException,SQLException
	{
		Connection tempcon=Connect_person_db();
		Statement stmt = tempcon.createStatement();    
		ResultSet rset = stmt.executeQuery("select * from person_info");    
		
		String result = "userName: " + rset.getString(1);
		
		stmt.close(); 
		return result;
	}

	

	private String url;
	private String SID;
	private String USERNAME;
	private String PASSWORD;
}
