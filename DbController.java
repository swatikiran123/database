class DbController
{
	static String adapterType="MySQL";

	public static IDatabase getInstance() throws Exception
	{
		IDatabase db = null;
		switch (adapterType)
		{
		case "Mongo":
			db= new MongoDbAdapter();
			break;
		case "MySQL":
			db= new MysqlAdapter();
			break;

		default:
			throw new Exception("Invalid database adapter");
		}

		return db;
	}
}