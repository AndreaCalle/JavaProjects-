import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;
class Client {
  public static void main(String[] args) {
    try {
			String port = 20470 ; 


			int Port =Integer.parseInt(JOptionPane.showInputDialog("Input Your Port : "));
			String IP = JOptionPane.showInputDialog("Input Your IP Server : ");
			Socket sock=new Socket("localhost", 20470);
			DataInputStream in= new DataInputStream(sock.getInputStream());
			System.out.println(in.readUTF());
			DataOutputStream out =new DataOutputStream(sock.getOutputStream());
			out.writeUTF("waiting for connection");
			sock.close();

    } catch (Exception e) { 
      System.out.println("Error: " + e); 
    }
  }
}
