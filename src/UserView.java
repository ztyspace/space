import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel jp = new JPanel();
	public UserView(){
		
		this.setBounds(0, 0, 100, 500);
		jp.setBounds(0, 0, 100, 500);
		jp.setVisible(true);
		this.add(jp);
		this.setVisible(true);
	}
	
	public void Set(){
		
		int size = 20 ;
		int index = 0;
		jp.removeAll();
		for(;index<Context.list.size();index++){
			JButton JB = new JButton(Context.list.get(index).getip());
			JB.setName(index+"");
			JB.setBounds(0, index*size, 100, 20);
			JB.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
					System.out.println(((JComponent)e.getSource()).getName());
					Context.list.get(Integer.parseInt(((JComponent)e.getSource()).getName())).setVisible(true);
					try {
						Context.list.get(Integer.parseInt(((JComponent)e.getSource()).getName())).socket.getOutputStream().write((byte)1);
						Context.list.get(Integer.parseInt(((JComponent)e.getSource()).getName())).socket.getOutputStream().flush();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			jp.add(JB) ;	
		}
		this.repaint();
	}
}
