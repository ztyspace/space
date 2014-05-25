import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.apache.commons.codec.binary.Base64;


public class CilentThread extends Thread{
	
	InputStream is = null;
	DataInputStream dis = null;
	Viewer viewer = null ;
	
	public CilentThread(Socket s,UserView uv) {
		
		try {
			is = s.getInputStream();
			dis = new DataInputStream(is);
			viewer = new Viewer(s);
			Context.list.add(viewer);
			uv.Set();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		int packlength = -1 ;
		int read = 0 ;
		
		
		try{
			while((packlength=dis.readInt())!=-1){
				
				int remain = packlength;
				int k =0;
				read = 0;
				byte[] by = new byte[remain];
			//	System.out.println("receive "+ packlength +"bytes");
				while(remain>0){
					
					read = is.read(by, k, remain);
					k += read;
					remain -= read;
				}
			//	System.out.println("by is "+ by.length);
				handle(by,packlength);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void handle(byte[] by, int packlength) throws IOException {
		
		
		byte[] nobase64_image = Base64.decodeBase64(by);
		
		byte[] decompressed_image = Compressor.decompress(nobase64_image);
		
		viewer.ViewerInput(decompressed_image,decompressed_image.length);	
		
		viewer.repaint();
	}
	
}
