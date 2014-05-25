import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JFrame;

public class Viewer extends JFrame{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	String ip;
	Socket socket;
	public Viewer(Socket s){
		this.setBounds(0, 0, 400, 300);
		this.setVisible(false);
		image = null;
		socket = s;
		ip = s.getInetAddress().getHostAddress();
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				
				try {
					Context.list.remove(Viewer.this);
					Context.uv.Set();
					Viewer.this.dispose();
					socket.getOutputStream().write((byte)1);
					socket.getOutputStream().flush();
					socket.close();
				} catch (IOException e1) {
					try {
						socket.close();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
			}

		}); 
	}

    private int[] toIntArray(byte[] barr,int length) {
            int[] result = new int[length];
            for(int i=0;i<length;i++)	result[i]=barr[i];
            return result;
    }
    
    public void ViewerInput(byte[] image_bytes,int length) throws IOException{
   //	WritableRaster raster = image.getRaster();
   //     raster.setPixels(0, 0, 320, 240, toIntArray(image_bytes,length));
        ByteArrayInputStream in = new ByteArrayInputStream(image_bytes);    //��b��Ϊ��������
        image = ImageIO.read(in);     //��in��Ϊ����������ȡͼƬ����image�У�������in����ΪByteArrayInputStream();
    }

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
        g.drawImage(image, 0, 0,this.getWidth(),this.getHeight(),null); // see javadoc for more info on the parameters 
    }

	public String getip() {
		
		return ip;
	}
                  
}

