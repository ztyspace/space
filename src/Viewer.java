import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import javax.swing.JFrame;

public class Viewer extends JFrame{

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public Viewer(){
		this.setBounds(0, 0, 320, 240);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		image = new BufferedImage(32,24,BufferedImage.TYPE_3BYTE_BGR);
	}

    private int[] toIntArray(byte[] barr,int length) {
            int[] result = new int[length];
            for(int i=0;i<length;i++)	result[i]=barr[i];
            return result;
    }
    
    public void ViewerInput(byte[] image_bytes,int length){
    	WritableRaster raster = image.getRaster();
        raster.setPixels(0, 0, 32, 24, toIntArray(image_bytes,length));
    }

    @Override
    public void paint(Graphics g) {
    	super.paint(g);
        g.drawImage(image, 0, 0,320,240,null); // see javadoc for more info on the parameters 
    }
                  
}

