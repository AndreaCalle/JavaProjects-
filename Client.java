import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	
	static public Socket sock; 
	static DataInputStream dataIS ;
	static DataOutputStream dataOS;
	
	
	 public static void transferData(String file) throws IOException {
		

		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dataOS.write(buffer);
		}
		
		fis.close();
		//dos.close();	
	}

	public static void main(String[] args) throws IOException {
		
		try {
			Scanner sc = new Scanner(System.in); //see if you can use BufferedReader
			
			InetAddress IPAddress = InetAddress.getByName("localhost");
			
			//establish connection
			Socket sock = new Socket(IPAddress, 20470);
			String file = "C:\\Users\\acalle\\Downloads\\flower.jpg"; 
			DataInputStream dataIS = new DataInputStream(sock.getInputStream());
			DataOutputStream dataOS = new DataOutputStream(sock.getOutputStream());

			
			//exchange information between client and clientHandler
			while (true) {
				System.out.println(dataIS.readUTF());
				String sendBack = sc.nextLine();
				dataOS.writeUTF(sendBack);
				if (sendBack.equals("Upload")) {
					System.out.println(dataIS.readUTF());
					sendBack = sc.nextLine();
					dataOS.writeUTF(sendBack);
				}
				//sendFile(file);
				//if client sends exit, close this connection and break
				if (sendBack.equals("Exit")) {
					System.out.println("Closing this connection...");
					sock.close();
					System.out.println("Connection closed");
					break;
				}
				
				String receivedMsg = dataIS.readUTF();
				System.out.println(receivedMsg);

				
			}
			
				sc.close();
				dataIS.close();
				dataOS.close();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
}