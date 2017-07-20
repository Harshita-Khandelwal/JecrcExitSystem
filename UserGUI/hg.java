import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
public class hg {

    private Connection con,con1;
    private Statement stmt,stmt1;
    private ResultSet rs,rs1;
    private JFrame frm;
    private String allinfo="";
    private Object arr[];
    private JFrame fm;
    private File image;
    private JFrame ff;
    private JLabel ll;
    private String globalbarcode,hodEmailID;
    public hg()
    {
    	
    }
    public hg(String str,JFrame f)throws ClassNotFoundException,SQLException,IOException
    {
			try
			{System.out.println("hello");
				Db.rs=Db.stmt.executeQuery("select emailId from login where login_id=\""+log.storelogin+"\"");
				Db.rs.next();
				hodEmailID=Db.rs.getString("emailId");
				System.out.print(hodEmailID+"fdgdfgdfg");
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
    	fm=f;
    	globalbarcode=str;
    	System.out.print(globalbarcode);
    	Class.forName("com.mysql.jdbc.Driver");
	    con=DriverManager.getConnection("jdbc:mysql://localhost/entrysystem","root","admin");
	    stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    rs=stmt.executeQuery("Select * from student where barCodeNo ='"+globalbarcode+"'");
	    rs.next();
	    arr=new Object[9];
        conversionimg();
        connectn();
        gui();
		
    }
    void connectn()throws ClassNotFoundException,SQLException,IOException
    {
        con1=DriverManager.getConnection("jdbc:mysql://localhost/entrysystem","root","admin");
	    stmt1=con1.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	    rs1=stmt1.executeQuery("Select * from timeupdate where barcodeNo ='"+globalbarcode+"'");
	    rs1.last();
		System.out.print(hodEmailID+"2nd");
	    calculate();	
    }
    void calculate()
    {
    	   try
    	   {
    	   
					for(;!rs1.isBeforeFirst();rs1.previous())
					{
						String s1=(String)rs1.getString("date");
						String s2=(String)rs1.getString("outTime");
						allinfo+="  "+s1+"        |        "+s2+"\n";
							 
					}
    	   }
    	   catch(SQLException e)
    	   {
    	   		e.printStackTrace();
    	   }
    	   arr[6]=allinfo;
    }
    void conversionimg()throws IOException,SQLException
    {
      try
      {
      image = new File("E:/temp.png");
      FileOutputStream fos = new FileOutputStream(image);

      byte[] buffer = new byte[1];
      InputStream is = rs.getBinaryStream("photo");
      while (is.read(buffer) > 0) {
        fos.write(buffer);
      }
      fos.close();
      }
      catch(Exception e)
      {
      	e.printStackTrace();
      }
      try
      {
      
      arr[7]=image;
      arr[1]=rs.getString("name");
      arr[2]=rs.getString("fatherName");
      arr[3]=rs.getString("branch");
      arr[4]=rs.getString("year");
      arr[5]=rs.getLong("mobileNo");
      arr[8]=rs.getString("emailid");
      }
      catch(SQLException e)
      {
      	e.printStackTrace();
      }
    
    }
    
    void gui()
    {
		try
		{
			con.close();
			con1.close();
			stmt.close();
			stmt1.close();
			rs.close();
			rs1.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
    	new info(arr,fm,hodEmailID).setVisible(true);
		System.out.print(hodEmailID+"hg.java");
//    	ff=new JFrame("Test");
//    	ff.setSize(400,400);
//    	ll=new JLabel();
//    	ImageIcon icon = new ImageIcon(image.getPath());
//    	ll.setIcon(icon);
//    	ff.add(ll);
//    	ff.setVisible(true);
        
    }
   
   
    
}