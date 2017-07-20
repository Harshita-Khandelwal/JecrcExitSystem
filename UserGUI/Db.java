import java.sql.*;
class Db
{
	public static Connection con;
	public static Statement stmt;
	public static ResultSet rs; 
	
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/entrysystem","root","admin");
		}
		catch(Exception ex)
		{System.out.println("A");
			try
			{
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306","root","admin");
				stmt=con.createStatement();
				stmt.execute("create database entrysystem");
				stmt.close();
				con.close();
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/entrysystem","root","admin");
			}
			catch(SQLException e)
			{System.out.println("B");
			}
		}
		try
		{//System.out.println("C");
			stmt=con.createStatement();
			/*try
			{
				stmt.execute("create table login(login_id smallint,username varchar(10),password varchar(10),name varchar(20),mobileNo varchar(10),designation varchar(20),branch varchar(10),primary key(login_id),unique(username))");
				stmt.execute("insert into login values('1','HODCS','cs12','Mr. Mukesh Agarwal','9856321254','HOD','CS')");
				stmt.execute("insert into login values('2','HODECE','ece12','Mr. Jain','7845123695','HOD','ECE')");
				stmt.execute("insert into login values('3','HODIT','it12','Ms. Sharma','8745123695','HOD','IT')");
				
				stmt.execute("create table student(sNo smallint,barCodeNo varchar(30),name varchar(30),mobileNo varchar(10),fatherName varchar(30),year smallint,section varchar(10),branch varchar(20),photo blob,primary key(sNo))");
				stmt.execute("insert into student values('1','123652','Harshita Khandelwal','9782590536','Ajay Khandelwal','4','A','CS',null)");
				stmt.execute("insert into student values('2','548956','Himanshu Gaur','7854236912','Ashok Gaur','4','A','CS',null)");
				stmt.execute("insert into student values('3','123653','Radhika Joshi','8956321456','Rahul Joshi','4','B','CS',null)");
				stmt.execute("insert into student values('4','659823','Riddha Mathur','7845123695','Hemant Mathur','4','K','ECE',null)");
				
				stmt.execute("create table timeUpdate(sNo smallint auto_increment,barCodeNo varchar(30),day varchar(15),date varchar(10),outTime varchar(8),primary key(sNo))");
				stmt.execute("insert into timeUpdate values('1','123652','Monday','2015-02-12','10:20:00')");
				stmt.execute("insert into timeUpdate values('2','548956','Monday','2015-05-11','2:20:00')");
				stmt.execute("insert into timeUpdate values('3','123652','Tuesday','2015-02-12','11:30:00')");
				stmt.execute("insert into timeUpdate values('4','548956','Monday','2015-07-15','1:20:00')");
				
				
				stmt.execute("insert into timeUpdate values('5','659823','Monday','2015-07-15','1:20:00')");
				stmt.execute("insert into timeUpdate values('6','123653','Tuesday','2015-08-15','9:20:00')");
				stmt.execute("insert into timeUpdate values('7','548956','Tuesday','2015-08-15','9:20:00')");
				
			}
			catch(SQLException r)
			{
				r.printStackTrace();
			}*/
		}
		catch(SQLException exc)
		{System.out.println("D");
		}
	}
	public void finalize()
	{
		try
		{
			rs.close();
			stmt.close();
			con.close();
		}
		catch(SQLException e)
		{
		}
	}
}