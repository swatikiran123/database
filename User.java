import java.util.ArrayList;
import java.util.List;

public class User {

	public String EmployeeID;
	public String Name;
	public String email;
	public String Phone;
	public String Address;


	@Override
	public String toString() 
	{
		return "Jason String [id = " + EmployeeID + ", Name= " + Name +
				", email=" + email + ", phone=" + Phone + "]";
	}


	public void addUser(User newUser) throws Exception
	{
		IDatabase db = DbController.getInstance();                                  // ID Name email Phone Addres

		db.AddRecord(newUser);

	}


	public   List<User> getUsers() throws Exception
	{

		IDatabase db = DbController.getInstance();                                  // ID Name email Phone Addres
		db.listRecord();
		return null;
	}



	public void delete(User user) throws Exception 
	{
		IDatabase db = DbController.getInstance();
		db.DeleteRecord(user);

	}



}

