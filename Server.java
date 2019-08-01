import java.io.*;
import java.net.*;
import java.net.Socket; 

public class Server{

	public static void main(String[] args) throws IOException {
        int portNum = 0 ; 
		ServerSocket s = null;
		Socket connection = new Socket(); 

		try {
            //the port# provide by ryerson 
			s = new ServerSocket(20470);
			

			int i = 0;

			for (;;) {
				connection = s.accept();
				System.out.println( connection.getPort() );
				portNum= connection.getPort() ; 
				ServerThread h = new ServerThread(connection,portNum); 
				System.out.println("Spawning " + i++);
				h.start(); 			
				//start();
				
			}// end of for

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			s.close();
			connection.close(); 
		}
    }


}
class ServerThread extends Thread {
	public Socket connection;
	public int client;

	public ServerThread(Socket s, int c) {
		connection = s;
		client = c;
	}

 //create a class extned runneble    
   public void run(){
        try{
             BufferedReader inHandler = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             PrintWriter outHandler = new PrintWriter(connection.getOutputStream(), true);
             ObjectOutputStream oToClient = new ObjectOutputStream(connection.getOutputStream());
             ObjectInputStream oFromClient = new ObjectInputStream(connection.getInputStream()); 
        }catch(IOException io){
		io.getMessage();
		io.printStackTrace();
	}catch(ClassNotFoundException e){
		e.getMessage();
		e.printStackTrace();
	}  
}
}

