import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class TableGUI extends Common
{
	private JPanel pleftce;
	private JButton bDateView,bDayView,blogout,bTodayList,bHome,bSwitchUser,bUserProfile;
	private String des,branch;
	private JComboBox cbranch;
	TableGUI()
	{
		bHome=new JButton("Home");
		bTodayList=new JButton("Today's List");
		bDateView=new JButton("Date View");
		bDayView=new JButton("Day View");
		bUserProfile=new JButton("User Profile");
		bSwitchUser=new JButton("Switch User");
		blogout=new JButton("Logout");
		
		bHome.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){adminPage();}});
		bTodayList.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){new List(framepanel,desktop,jf,"today",null,branch);}});
		bDateView.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){new View(framepanel,desktop,jf,"date",branch);}});
		bDayView.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){new View(framepanel,desktop,jf,"day",branch);}});
		bUserProfile.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){new View(framepanel,desktop,jf,"profile",null);}});
		bSwitchUser.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){jf.setVisible(false);
										jf.dispose();
										new log();
										}});
		blogout.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){jf.setVisible(false);
										jf.dispose();
										System.exit(1);
										}});
		
		
		try
		{
			Db.rs=Db.stmt.executeQuery("select designation,branch from login where login_id=\""+log.storelogin+"\"");
			Db.rs.next();
			des=Db.rs.getString("designation");
			branch=Db.rs.getString("branch");
		}
		catch(SQLException e)
		{}
		
		pleftce=new JPanel();
		pleftce.setLayout(new GridBagLayout());
		gbc=new GridBagConstraints();
		in=new Insets(15,10,15,10);
		gbc.insets=in;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		
		masterpage();
		
		
		if(des.equalsIgnoreCase("ADMIN"))
		{
			gbc.gridx=0;
			gbc.gridy=0;
			pleftce.add(bHome,gbc);
			gbc.gridx=0;
			gbc.gridy=1;
			pleftce.add(bTodayList,gbc);
			gbc.gridx=0;
			gbc.gridy=2;
			pleftce.add(bDateView,gbc);
			gbc.gridx=0;
			gbc.gridy=3;
			pleftce.add(bDayView,gbc);
			gbc.gridx=0;
			gbc.gridy=4;
			pleftce.add(bUserProfile,gbc);
			gbc.gridx=0;
			gbc.gridy=5;
			pleftce.add(bSwitchUser,gbc);
			gbc.gridx=0;
			gbc.gridy=6;
			pleftce.add(blogout,gbc);
			
			adminPage();
		}
		else
		{
			gbc.gridx=0;
			gbc.gridy=0;
			pleftce.add(bTodayList,gbc);
			gbc.gridx=0;
			gbc.gridy=1;
			pleftce.add(bDateView,gbc);
			gbc.gridx=0;
			gbc.gridy=2;
			pleftce.add(bDayView,gbc);
			gbc.gridx=0;
			gbc.gridy=3;
			pleftce.add(bUserProfile,gbc);
			gbc.gridx=0;
			gbc.gridy=4;
			pleftce.add(bSwitchUser,gbc);
			gbc.gridx=0;
			gbc.gridy=5;
			pleftce.add(blogout,gbc);
			
			/*try
			{
				Db.rs=Db.stmt.executeQuery("select branch from login where login_id=\""+log.storelogin+"\"");
				Db.rs.next();
				branch=Db.rs.getString("branch");
			}
			catch(SQLException e)
			{}*/
		}
		
	}	
	public void masterpage()
	{
		jf=new JFrame("JECRC Exit System");
		jf.setSize(710,690);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		framepanel=(JPanel)jf.getContentPane();
		framepanel.setLayout(new BorderLayout());
		desktop=new JDesktopPane();
		jif=new JInternalFrame();
		jif.setSize(590,600);
		
		Class c=this.getClass();
		java.net.URL url=c.getResource("/logo.jpg");
		ImageIcon ii=new ImageIcon(url);
		JLabel topic=new JLabel("      JECRC Exit System");
		topic.setIcon(ii);	
		topic.setFont(new Font("Tahoma",Font.BOLD | Font.ITALIC,40));
		JPanel ptop=new JPanel();
		ptop.setLayout(new FlowLayout(FlowLayout.LEFT));
		ptop.add(topic);
		//ptop.setBackground(Color.RED);
		framepanel.add(ptop,BorderLayout.NORTH);
		
		JPanel pleft=new JPanel();
		pleft.setLayout(new BorderLayout());
		pleft.add(pleftce,"Center");
		//pleftce.setBackground(Color.BLUE);
		framepanel.add(pleft,BorderLayout.WEST);
		
		JPanel pcenter=new JPanel();
		pcenter.setLayout(new BorderLayout());
		label();
		pcenter.add(pc1,"North");
	
		jif.add(pcenter);
		desktop.add(jif);
		((javax.swing.plaf.basic.BasicInternalFrameUI)jif.getUI()).setNorthPane(null);
		jif.setVisible(true);
		framepanel.add(desktop,"Center");
		jif.toFront();
		
		jf.setResizable(false);
		jf.setVisible(true);
	}
	private void adminPage()
	{
		startp();
		
		bTodayList.setEnabled(false);
		bDateView.setEnabled(false);
		bDayView.setEnabled(false);
		add.setText("Select Branch :");
		String da[]={"CS","ECE","IT","EE","ME","CE"};
		cbranch=new JComboBox(da);
		JButton bSelect=new JButton("Select");
		bSelect.addActionListener(new ActionListener()
									{public void actionPerformed(ActionEvent e)
										{
											branch=(String)cbranch.getSelectedItem();
											JOptionPane.showMessageDialog(jf,"Branch Selected !!","Message",JOptionPane.PLAIN_MESSAGE);
											bTodayList.setEnabled(true);
											bDateView.setEnabled(true);
											bDayView.setEnabled(true);
											startp();
											endp();
										}
									});
										
		JPanel p1=new JPanel();
		JPanel p3=new JPanel();
		
		p1.add(cbranch);
		
		gbc.gridx=0;
		gbc.gridy=0;
		pce1.add(p1,gbc);
		
		gbc.gridx=0;
		gbc.gridy=1;
		pce1.add(bSelect,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		pce1.add(p3,gbc);
		endp();
	}
}		