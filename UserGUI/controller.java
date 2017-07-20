 import java.sql.*;
class Controller
{
	private boolean count;
	Controller()
	{
		try
		{
			Db.rs=Db.stmt.executeQuery("select firstCount from login where login_id=\""+log.storelogin+"\"");
			Db.rs.next();
			count=Db.rs.getBoolean("firstCount");
			if(count==false)
			{
				new mailreg(false).setVisible(true);
			}
			else
			{
				new TableGUI();
			}
		}
		catch(SQLException e)
		{}
	}
}