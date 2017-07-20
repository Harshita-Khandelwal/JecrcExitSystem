import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.util.*;
@SuppressWarnings({"unchecked","deprecation"})
class List extends Common implements ListSelectionListener
{	
	private String sno="1";  
	private DefaultTableModel dm;
	private JTable tb;
	private JScrollPane sp;
	private int i,count;
	private String search,temp,branch,section,des;
	List(JPanel p,JDesktopPane d,JFrame f,String s,String t,String b)
	{
		framepanel=p;
		jf=f;
		desktop=d;
		search=s;
		temp=t;
		branch=b;
		viewpanel();
	}
	public void viewpanel()
	{
		startp();
		if(search.equals("date"))
			add.setText(temp+"'s List :");
		else if(search.equals("day"))
			add.setText(temp+"'s List :");
		else
			add.setText("Today's List :");
				
		String columns[]={"S.No.","BarCodeNo","Name","Year","Section","Count"};
		dm=new DefaultTableModel(null,columns);
		tb=new JTable(dm){
						public boolean isCellEditable(int row,int column)
						{
							return false;
						}
						};
		tb.setGridColor(new Color(200,200,220));
		tb.setForeground(new Color(120,150,220));
		Font fn1=new Font("Comic Sans Ms",Font.PLAIN,12);
		tb.setFont(fn1);
		//tb.setRowMargin(5);
		tb.getTableHeader().setFont(new Font("Comic Sans Ms",Font.BOLD,15));
		tb.getTableHeader().setForeground(new Color(120,120,250));
		tb.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tb.getTableHeader().setReorderingAllowed(false);
		tb.getSelectionModel().addListSelectionListener(this);
		//tb.setRowSelectionAllowed(false);
		sp=new JScrollPane(tb);
		
		Calendar c=Calendar.getInstance();
		int mon=c.get(Calendar.MONTH)+1;
        String u=c.get(Calendar.DATE)+"-"+mon+"-"+c.get(Calendar.YEAR);
						
		/*java.sql.Date dt=new java.sql.Date(System.currentTimeMillis());
		String u=dt.toString();*/
		
		try
		{
			if(search.equals("day"))
				Db.rs=Db.stmt.executeQuery("select barCodeNo from timeUpdate where day=\""+temp+"\"");
			else if(search.equals("date"))
				Db.rs=Db.stmt.executeQuery("select barCodeNo from timeUpdate where date=\""+temp+"\"");
			else
				Db.rs=Db.stmt.executeQuery("select barCodeNo from timeUpdate where date=\""+u+"\"");
				
			java.util.Vector v=new java.util.Vector();
			while(Db.rs.next()==true)
			{
				v.add(Db.rs.getString("barCodeNo"));
			}
			
			int rowcount=dm.getRowCount();
			for(int u1=0;u1<rowcount;u1++)
			{
				dm.removeRow(0);
			}
			
			
			java.util.Vector no=new java.util.Vector();
			
			int tr=1;
			java.util.Iterator it=v.iterator();
			while(it.hasNext()==true)
			{	
				String h=(String)it.next();
				if(tr==1)
				{	
					no.add(h);
					tr++;
				}
				else
				{	
					java.util.Iterator it1=no.iterator();
					int y=0;
					while(it1.hasNext()==true)
					{	
						String p=(String)it1.next();
						if(h.equals(p))
						{
							y=1;
							break;
						}
					}
					if(y==1)
					{}
					else
					{
						no.add(h);
					}
				}
			}
			try
			{
				Db.rs=Db.stmt.executeQuery("select designation from login where login_id=\""+log.storelogin+"\"");
				Db.rs.next();
				des=Db.rs.getString("designation");
			}
			catch(SQLException e)
			{}
			
			int i=0;
			String barCodeNo;
			String name,year,section1;
			java.util.Iterator it1=no.iterator();
			while(it1.hasNext()==true)
			{
				count=0;
				barCodeNo=(String)it1.next();
				if(des.equalsIgnoreCase("CC"))
				{
						try
						{
							Db.rs=Db.stmt.executeQuery("select branch from login where login_id=\""+log.storelogin+"\"");
							Db.rs.next();
							branch=Db.rs.getString("branch");
							section=branch.substring(branch.length()-1,branch.length());
							branch=branch.substring(0,branch.length()-1);
						}
						catch(SQLException e)
						{}
					Db.rs=Db.stmt.executeQuery("select name,year,section from student where barCodeNo=\""+barCodeNo+"\" and branch=\""+branch+"\" and section=\""+section+"\"");
				}
				else
				{
					Db.rs=Db.stmt.executeQuery("select name,year,section from student where barCodeNo=\""+barCodeNo+"\" and branch=\""+branch+"\"");
				}
				
				if(Db.rs.next()==true)
				{
					name=Db.rs.getString("name");
					year=Db.rs.getString("year");
					section1=Db.rs.getString("section");
					
					if(search.equals("day"))
						Db.rs=Db.stmt.executeQuery("select sNo from timeUpdate where barCodeNo=\""+barCodeNo+"\" and day=\""+temp+"\"");
					else if(search.equals("date"))
						Db.rs=Db.stmt.executeQuery("select sNo from timeUpdate where barCodeNo=\""+barCodeNo+"\" and date=\""+temp+"\"");
					else
						Db.rs=Db.stmt.executeQuery("select sNo from timeUpdate where barCodeNo=\""+barCodeNo+"\" and date=\""+u+"\"");
					
					while(Db.rs.next()==true)
					{
						count++;
					}
					
					Object ob[]={sno,barCodeNo,name,year,section1,count};
					dm.insertRow(i,ob);
					i++;
					int j=Integer.parseInt(sno);
					j++;
					sno=Integer.toString(j);
				}
			}
			
		}
		catch(SQLException e)
		{
			System.out.println("Exception A");
			e.printStackTrace();
		}
		
		pce1.setLayout(new BorderLayout());
		pce1.add(sp,"Center");
			
		jf.validate();
			
		endp();
	}
	public void valueChanged(ListSelectionEvent e) {
		if(e.getValueIsAdjusting() == false) {
			jf.setVisible(false);
			int row = tb.getSelectedRow();
			if (row > -1) {
				// source of event
				// ListSelectionModel lsm = (ListSelectionModel)e.getSource();
			
				// clears the selection
				// lsm.clearSelection();
			
				// check if the specified index is selected
				// System.out.println(lsm.isSelectedIndex(0));
			
				// returns true if no indices are selected
				// System.out.println(lsm.isSelectionEmpty());
				
				// returns the value for the cell at specified row and column
				Object val = dm.getValueAt(row, 1);
				String str=(String)val;
				//Integer str1=(Integer)val;
			   	//int str=(int)str1;
			   	tb.clearSelection();
				call(str,jf);
				
			
			}
		}
    }
    public void call(String str,JFrame frame)
	{
		try
		{
		  new hg(str,jf);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}