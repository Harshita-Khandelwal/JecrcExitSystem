 import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
@SuppressWarnings({"unchecked","deprecation"})
class View extends Common 
{
	private JButton bsearch,bEditPassword,bEditEmailId,bupdatePassword;
	private JComboBox year,month,date,day;
	private String search,branch,password;
	private JLabel ldate,lmonth,lyear,uName,uNameValue,uMobileNo,uMobileNoValue,uDesignation,uDesignationValue,uDept,uDeptValue,uUserName,uUserNameValue,uPassword,uEmailId;
	private JLabel lOldPassword,lNewPassword,lConfirmPassword;
	private JTextField uEmailIdValue;
	private JPasswordField uPasswordValue,pOldPassword,pNewPassword,pConfirmPassword;
	private Dialog dEditPassword,dEditEmailId;
	private JPanel jp1,jp2;
	View(JPanel p,JDesktopPane d,JFrame f,String s,String b)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		search=s;
		branch=b;
		panel();
	}
	public void panel()
	{
		startp();
		
		if(search.equals("date"))
			add.setText("Select Date :");
		else if(search.equals("day"))
			add.setText("Select Day :");
		else if(search.equals("profile"))
			add.setText("User Profile :");
				
		java.sql.Date dt=new java.sql.Date(System.currentTimeMillis());
		String u=dt.toString();
		int a,b,r;
		a=Integer.parseInt(u.substring(0,u.indexOf("-")));
		b=Integer.parseInt(u.substring(u.indexOf("-")+1,u.lastIndexOf("-")));
		r=Integer.parseInt(u.substring(u.lastIndexOf("-")+1));
			
		year=new JComboBox();
		for(int k=2000;k<=a;k++)
		{
			year.addItem(k);
		}
		
		Integer m[]=new Integer[12];
		for(int k=0;k<12;k++)
		{
			m[k]=k+1;
		}
		month=new JComboBox(m);
		month.setSelectedItem(b);
		
		Integer d[]=new Integer[31];
		for(int k=0;k<31;k++)
		{
			d[k]=k+1;
		}
		date=new JComboBox(d);
		date.setSelectedItem(r);

		String da[]={"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		day=new JComboBox(da);
		
		bsearch=new JButton("Search");
		bsearch.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
											if(search.equals("day"))
											{
												String tempday=(String)day.getSelectedItem();
												new List(framepanel,desktop,jf,"day",tempday,branch);
											}
											else if(search.equals("date"))
											{
												Integer tempdate=(Integer)date.getSelectedItem();
												Integer tempmonth=(Integer)month.getSelectedItem();
												Integer tempyear=(Integer)year.getSelectedItem();
												String temp=tempdate+"-"+tempmonth+"-"+tempyear;
												new List(framepanel,desktop,jf,"date",temp,branch);
											}
										}});
		
		ldate=new JLabel("Date");
		lmonth=new JLabel("Month");
		lyear=new JLabel("Year");
		
		uName=new JLabel("Name :");
		uNameValue=new JLabel();
		uMobileNo=new JLabel("Mobile No :");
		uMobileNoValue=new JLabel();
		uDesignation=new JLabel("Designation :");
		uDesignationValue=new JLabel();
		uDept=new JLabel("Department :");
		uDeptValue=new JLabel();
		uUserName=new JLabel("UserName :");
		uUserNameValue=new JLabel();
		uPassword=new JLabel("Password :");
		uPasswordValue=new JPasswordField(20);
		uPasswordValue.setEditable(false);
		uEmailId=new JLabel("Sender's Email :");
		uEmailIdValue=new JTextField(20);
		uEmailIdValue.setEditable(false);
		
		bupdatePassword=new JButton("Update");
		
		bupdatePassword.addActionListener(new ActionListener()
										{
											public void actionPerformed(ActionEvent e)
											{
												try
												{
													Db.rs=Db.stmt.executeQuery("select password from login where login_id=\""+log.storelogin+"\"");
													Db.rs.next();
													password=Db.rs.getString("password");
												}
												catch(SQLException exc)
												{}
												String oldp,newp,confirmp;
												oldp=pOldPassword.getText();
												newp=pNewPassword.getText();
												confirmp=pConfirmPassword.getText();
												if((oldp==null | oldp.equals(""))|(newp==null | newp.equals("")) |(confirmp==null | confirmp.equals("")))
												{
														JOptionPane.showMessageDialog(jf,"Fields cannot be empty !!");
														return;
												}
												if(!(oldp.equals(password)))
												{
													JOptionPane.showMessageDialog(jf,"Old Password doesn't match!!");
													return;
												}
												if(!(newp.equals(confirmp)))
												{
													JOptionPane.showMessageDialog(jf,"New Password and confirm password doesn't match!!");
													return;
												}
												if(newp.length()>45)  //change it when change database
												{
													JOptionPane.showMessageDialog(jf,"Password cannot be more than 45 characters");
													return;
												}
												int x=JOptionPane.showConfirmDialog(jf,"Are you sure you want to update password","Confirmation",JOptionPane.YES_NO_OPTION);
												if(x==JOptionPane.YES_OPTION)
												{
													try
													{	
														PreparedStatement pstmt=Db.con.prepareStatement("update login set password=? where login_id=\""+log.storelogin+"\"");
														pstmt.setString(1,newp);
														pstmt.execute();
														pstmt.close();								
														JOptionPane.showMessageDialog(jf,"Password Updated Successfully");
														dEditPassword.setVisible(false);
														dEditPassword.dispose();
														uPasswordValue.setText(newp);
													}
													catch(SQLException ex)
													{
														JOptionPane.showMessageDialog(jf,"Some error");
														
													}
												}
											}
										});
		
		bEditPassword=new JButton("Edit");
		bEditPassword.addActionListener(new ActionListener()
										{public void actionPerformed(ActionEvent e)
											{
												dEditPassword=new Dialog(jf,"Edit Password",true);
												dEditPassword.setSize(500,250);
												dEditPassword.setLocationRelativeTo(jf);
												dEditPassword.setResizable(false);
												
												lOldPassword=new JLabel("Old Password");
												lNewPassword=new JLabel("New Password");
												lConfirmPassword=new JLabel("Confirm Password");
												pOldPassword=new JPasswordField(20);
												pNewPassword=new JPasswordField(20);
												pConfirmPassword=new JPasswordField(20);
												jp1=new JPanel();
												jp2=new JPanel();
												
												jp1.setLayout(new GridBagLayout());
												GridBagConstraints gb=new GridBagConstraints();
												Insets in=new Insets(15,10,15,10);
												gb.insets=in;
												gb.gridx=0;
												gb.gridy=0;
												jp1.add(lOldPassword,gb);
												gb.gridx=1;
												gb.gridy=0;
												jp1.add(pOldPassword,gb);
												gb.gridx=0;
												gb.gridy=1;
												jp1.add(lNewPassword,gb);
												gb.gridx=1;
												gb.gridy=1;
												jp1.add(pNewPassword,gb);
												gb.gridx=0;
												gb.gridy=2;
												jp1.add(lConfirmPassword,gb);
												gb.gridx=1;
												gb.gridy=2;
												jp1.add(pConfirmPassword,gb);
												
												jp2.setLayout(new FlowLayout(FlowLayout.CENTER));
												jp2.add(bupdatePassword);
												
												dEditPassword.setLayout(new GridBagLayout());
												GridBagConstraints gb1=new GridBagConstraints();
												gb1.gridx=0;
												gb1.gridy=0;
												dEditPassword.add(jp1,gb1);
												gb1.gridx=0;
												gb1.gridy=1;
												dEditPassword.add(jp2,gb1);
												DialogCloser dc=new DialogCloser();
												dEditPassword.addWindowListener(dc);
												dEditPassword.setVisible(true);
											}
										});
		bEditEmailId=new JButton("Edit");
		bEditEmailId.addActionListener(new ActionListener()
										{public void actionPerformed(ActionEvent e)
											{
												new mailreg(true).setVisible(true);
												/*try
												{
													uEmailIdValue.setEditable(true);
													Db.rs=Db.stmt.executeQuery("select emailId from login where login_id=\""+log.storelogin+"\"");
													Db.rs.next();
													uEmailIdValue.setText(Db.rs.getString("emailId"));
													framepanel.validate();
													uEmailIdValue.setEditable(false);
												}
												catch(SQLException ex)
												{}*/
											}
										});
		
		JPanel p1=new JPanel();
		JPanel p3=new JPanel();
		
		if(search.equals("day"))
		{
			p1.add(day);
			
			gbc.gridx=0;
			gbc.gridy=0;
			pce1.add(p1,gbc);
			
			gbc.gridx=0;
			gbc.gridy=1;
			pce1.add(bsearch,gbc);
			
			gbc.gridx=0;
			gbc.gridy=2;
			pce1.add(p3,gbc);
		}
		else if(search.equals("date"))
		{
			p1.setLayout(new GridBagLayout());
			GridBagConstraints gbc1=new GridBagConstraints();
			Insets in1=new Insets(15,20,15,20);
			gbc1.insets=in1;
			
			gbc1.gridx=0;
			gbc1.gridy=0;
			gbc1.fill=GridBagConstraints.HORIZONTAL;
			p1.add(ldate,gbc1);
			
			gbc1.gridx=1;
			gbc1.gridy=0;
			p1.add(lmonth,gbc1);
			
			gbc1.gridx=2;
			gbc1.gridy=0;
			p1.add(lyear,gbc1);
			
			gbc1.gridx=0;
			gbc1.gridy=1;
			p1.add(date,gbc1);
			
			gbc1.gridx=1;
			gbc1.gridy=1;
			p1.add(month,gbc1);
			
			gbc1.gridx=2;
			gbc1.gridy=1;
			p1.add(year,gbc1);
			
			gbc.gridx=0;
			gbc.gridy=0;
			pce1.add(p1,gbc);
			
			gbc.gridx=0;
			gbc.gridy=1;
			pce1.add(bsearch,gbc);
			
			gbc.gridx=0;
			gbc.gridy=2;
			pce1.add(p3,gbc);
		}
		else if(search.equals("profile"))
		{
		
			try
			{
				Db.rs=Db.stmt.executeQuery("select * from login where login_id=\""+log.storelogin+"\"");
				Db.rs.next();
				uNameValue.setText(Db.rs.getString("name"));
				uMobileNoValue.setText(Db.rs.getString("mobileNo"));
				uDeptValue.setText(Db.rs.getString("branch"));
				uDesignationValue.setText(Db.rs.getString("designation"));
				uUserNameValue.setText(Db.rs.getString("userName"));
				uPasswordValue.setText(Db.rs.getString("password"));
				
				uEmailIdValue.setText(Db.rs.getString("emailId"));
			}
			catch(SQLException e)
			{}
		
			gbc.gridx=0;
			gbc.gridy=0;
			pce1.add(uName,gbc);
			
			gbc.gridx=1;
			gbc.gridy=0;
			pce1.add(uNameValue,gbc);
			
			gbc.gridx=0;
			gbc.gridy=1;
			pce1.add(uMobileNo,gbc);
			
			gbc.gridx=1;
			gbc.gridy=1;
			pce1.add(uMobileNoValue,gbc);
			
			gbc.gridx=0;
			gbc.gridy=2;
			pce1.add(uDept,gbc);
			
			gbc.gridx=1;
			gbc.gridy=2;
			pce1.add(uDeptValue,gbc);
			
			gbc.gridx=0;
			gbc.gridy=3;
			pce1.add(uDesignation,gbc);
			
			gbc.gridx=1;
			gbc.gridy=3;
			pce1.add(uDesignationValue,gbc);
			
			gbc.gridx=0;
			gbc.gridy=4;
			pce1.add(uUserName,gbc);
			
			gbc.gridx=1;
			gbc.gridy=4;
			pce1.add(uUserNameValue,gbc);
			
			gbc.gridx=0;
			gbc.gridy=5;
			pce1.add(uPassword,gbc);
			
			gbc.gridx=1;
			gbc.gridy=5;
			pce1.add(uPasswordValue,gbc);
			
			gbc.gridx=2;
			gbc.gridy=5;
			pce1.add(bEditPassword,gbc);
			
			gbc.gridx=0;
			gbc.gridy=6;
			pce1.add(uEmailId,gbc);
			
			gbc.gridx=1;
			gbc.gridy=6;
			pce1.add(uEmailIdValue,gbc);
			
			gbc.gridx=2;
			gbc.gridy=6;
			pce1.add(bEditEmailId,gbc);
		}
		
		endp();
	}
}