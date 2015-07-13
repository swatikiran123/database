public class TestFunction {

	public static void main(String[] args) throws Exception
	{
		User newUser = new User();
		//User newUser1 = new User();

		newUser.EmployeeID = "1";
		newUser.Name = "monk";
		newUser.email = "blasd";
		newUser.Phone="342534";
		newUser.Address="drtrd";
		//newUser1.EmployeeID = "3";
		newUser.addUser(newUser);
		//newUser.getUsers();
		//newUser.delete(newUser1);

	}

}
