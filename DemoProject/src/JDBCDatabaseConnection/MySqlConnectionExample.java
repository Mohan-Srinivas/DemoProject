package JDBCDatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlConnectionExample 
{
	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		// TODO Auto-generated method stub
		
		
		//Mention what type of database you have used..I am using MySql
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection("jdbc:mysql://root@localhost:8080/selenium_users");
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM superheroes_table");
		System.out.println("Name/t/tNick Name");
		System.err.println("-----------------------------------------------");
		while(resultSet.next())
		{
			System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2));
		}
	}
}
