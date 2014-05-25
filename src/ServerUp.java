import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerUp {
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(11111);
			Socket s = null ;
			UserView uv = new UserView();
			Context.uv = uv;
			while(true){
				
				s = ss.accept();
				System.out.println(s.getLocalAddress().getHostAddress()+" cilent connected");
				// start server				
				new CilentThread(s,uv).start();				
			}
						
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}


