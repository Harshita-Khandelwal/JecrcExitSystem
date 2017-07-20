import java.awt.*;
import java.awt.event.*;
class DialogCloser implements WindowListener
{
	public void windowOpened(WindowEvent e)
	{
	}
	public void windowClosed(WindowEvent e)
	{
	}
	public void windowActivated(WindowEvent e)
	{
	}
	public void windowDeactivated(WindowEvent e)
	{
	}
	public void windowIconified(WindowEvent e)
	{
	}
	public void windowDeiconified(WindowEvent e)
	{
	}
	public void windowClosing(WindowEvent e)
	{
		Window w;
		w=e.getWindow();
		w.setVisible(false);
		w.dispose();
	}
}