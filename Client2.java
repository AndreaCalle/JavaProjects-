import java.io.*;
import java.net.*;

class Client02 {
  public static void main(String[] args) {
    try {
    	Student student = new Student("Andrea", "Calle", 33);
    	Timetable tTable = null;
    	BufferedReader inCon = new BufferedReader(new InputStreamReader(System.in)); 
    	Socket s = new Socket("localhost", 20470);
    	BufferedReader inClient = 	new BufferedReader(new InputStreamReader(s.getInputStream())); 											
    	PrintWriter outClient = 	new PrintWriter(new OutputStreamWriter(s.getOutputStream())); 
 
    	ObjectOutputStream oToServer = new ObjectOutputStream(s.getOutputStream()); // this first
    	ObjectInputStream oFromServer = new ObjectInputStream(s.getInputStream());
    	String option;
      while( !( ( option = inCon.readLine() ).trim() ).equals("exit")  ){	
    	  System.out.println ("You chose: " + option);
	      outClient.println(option);
	      outClient.flush();
	      
	      switch(option){
			  
	      		case	"1":
	      			oToServer.flush();
					oToServer.writeObject(student); 
					oToServer.flush();
					student = (Student)oFromServer.readObject();

	      			break;
	      			
	      		case	"2":
	      			tTable = new Timetable(student, "timetable.txt");
					oToServer.writeObject(tTable);
					oToServer.flush();
					System.out.println ("The Student's timetable has been sent to server.");
	      			break;
	      			
	      		case	"3":
	      			outClient.println("8");// row
	      			outClient.println("2");// column
	      			outClient.flush();
	      			break;
	      }
      }//while
      oFromServer.close();
      oToServer.close();
      s.close();

    } catch (Exception e) { 
      System.out.println("Error: " + e); 
    }
  }
}