import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;
import com.mongodb.util.JSONParseException;

interface IDatabase
{
	public void AddRecord(User user);
	public void DeleteRecord(User user);
	public List<User> listRecord();
}


class MongoDbAdapter implements IDatabase
{

	public List<User> listRecord()
	{
		MongoClient mongoClient = null;
		try 
		{
			mongoClient = new MongoClient( "localhost" , 27017 );
		}
		catch (UnknownHostException e)
		{			
			e.printStackTrace();                              
		}

		DB db = mongoClient.getDB("Database");
		DBCollection coll = db.createCollection("Documents", null);

		List<User> list = new ArrayList<User>();
		DBCursor cursor = coll.find();

		try{
			while(cursor.hasNext())
			{
				String serialize = JSON.serialize(cursor);
				System.out.println(serialize);

			}
		}
		finally {
			cursor.close();
		}
		return null;
	}

	@Override
	public void AddRecord(User user) {

		MongoClient mongoClient = null;

		try
		{
			mongoClient = new MongoClient( "localhost" , 27017 );
		} 
		catch (UnknownHostException e)
		{
			e.printStackTrace();
		}

		DB db = mongoClient.getDB("Database");
		DBCollection coll = db.createCollection("Documents", null);

		Gson gson = new Gson();
		String json = gson.toJson(user);

		System.out.println(json);

		DBObject dbObject = (DBObject)JSON.parse(json);
		coll.insert(dbObject);

	}


	public void DeleteRecord(User user) {


		MongoClient mongoClient = null;
		try 
		{
			mongoClient = new MongoClient( "localhost" , 27017 );
		}
		catch (UnknownHostException e)
		{			
			e.printStackTrace();                              
		}
		DB db = mongoClient.getDB( "Database" );
		DBCollection coll = db.createCollection("Documents", null);

		Gson gson = new Gson();
		String json = gson.toJson(user);

		System.out.println(json);

		DBObject dbObject = (DBObject)JSON.parse(json);
		coll.remove(dbObject);


		System.out.println("deleted");

	}
}

class MysqlAdapter implements IDatabase
{

	public List<User> listRecord()
	{

		Statement stmt = null;
		Connection conn = null;
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
		try {

			try {
				Class.forName("com.mysql.jdbc.Driver");
			} 
			catch (ClassNotFoundException e) 
			{

				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL,"root","admin");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Documents";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				//Retrieve by column name
				String EmployeeID  = rs.getString("EmployeeID");
				String Name = rs.getString("Name");
				String email = rs.getString("email");
				String Phone = rs.getString("Phone");
				String Address = rs.getString("Address");
				//Display values
				System.out.print("EmployeeID: " + EmployeeID);
				System.out.print(", Name: " + Name);
				System.out.print(", email: " + email);
				System.out.print(", Phone: " + Phone);
				System.out.println(", Address: " + Address);
			}
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return null;

	} 
	public void AddRecord(User user) 
	{
		Statement stmt = null;
		Connection conn = null;
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
		try {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException e) 
			{

				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL,"root","admin");
			stmt = conn.createStatement();
			String a=user.EmployeeID;
			String b=user.Name;
			String c=user.email;
			String d=user.Phone;
			String e=user.Address;
			System.out.println(a);

			stmt.executeUpdate("INSERT INTO Documents(EmployeeID,Name,email,Phone,Address) VALUES('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"')"); 

			System.out.println("inserted");

		}catch (SQLException e) {

			e.printStackTrace();
		}


	}


	public void DeleteRecord(User user) {

		Statement stmt = null;
		Connection conn = null;
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
		try {
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException e) 
			{

				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL,"root","admin");
			stmt = conn.createStatement();
			String k=user.EmployeeID;
			String sql = "DELETE FROM Documents " +
					"WHERE EmployeeID="+k;
			stmt.executeUpdate(sql);
			System.out.println("deleted");
		}catch (SQLException e) {

			e.printStackTrace();
		}
	}



}




