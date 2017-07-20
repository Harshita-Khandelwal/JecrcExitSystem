import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
class Common 
{
	public JFrame jf;
	public JDesktopPane desktop;
	public JInternalFrame jif;
	private JPanel pce,pp,padd;
	public JPanel pce1,framepanel,pc1;
	public JLabel add;
	private JLabel designation,login_by;
	public GridBagConstraints gbc;
	public Insets in;
	public void startp()
	{
		jif=new JInternalFrame();
		jif.setSize(590,600);
		pce=new JPanel();
		pce.setLayout(new BorderLayout());
		pp=new JPanel();
		pp.setLayout(new BorderLayout());
		pce1=new JPanel();
		pce1.setLayout(new GridBagLayout());
		
		padd=new JPanel();
		padd.setLayout(new FlowLayout(FlowLayout.LEFT));
		add=new JLabel();
		add.setFont(new Font("Comic Sans MS",Font.BOLD,25));
		padd.add(add);
	
		gbc=new GridBagConstraints();
		in=new Insets(15,20,15,20);
		gbc.insets=in;
	}
	public void endp()
	{
		label();
		pce.add(pc1,"North");
		pp.add(padd,"North");
		pp.add(pce1,"Center");
		pce.add(pp,"Center");
		
		jif.add(pce,BorderLayout.CENTER);
		((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI()).setNorthPane(null);
		jif.setVisible(true);
		desktop.add(jif);
		jif.toFront(); 
		jf.validate();
	}
	public void label()
	{
		pc1=new JPanel();
		JPanel pc11=new JPanel();
		JPanel pc12=new JPanel();
		pc1.setLayout(new GridLayout(2,1));
		pc11.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pc12.setLayout(new FlowLayout(FlowLayout.RIGHT));
		try
		{	
			Db.rs=Db.stmt.executeQuery("select name,designation,branch from login where login_id=\""+log.storelogin+"\"");
			Db.rs.next();
			String des=Db.rs.getString("designation");
			login_by=new JLabel("Login By : "+Db.rs.getString("name"));
			if(des.equalsIgnoreCase("ADMIN"))
			{
				designation=new JLabel("Designation : "+des);
			}
			else
			{
				designation=new JLabel("Designation : "+des+"-"+Db.rs.getString("branch"));
			}
		}
		catch(SQLException e)
		{
			System.out.println("Exception");
		}
		pc11.add(login_by);
		pc12.add(designation);
		pc1.add(pc11);
		pc1.add(pc12);
	}
}