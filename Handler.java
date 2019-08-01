import java.io.*;
import java.net.*;
class Handler extends Thread {
	public Socket connection;
	public int client;

	public Handler(Socket s, int c) {
		connection = s;
		client = c;
	}

	public void run() {	
		try{
			BufferedReader inHandler = 		new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter outHandler = 		new PrintWriter(connection.getOutputStream(), true);
			ObjectOutputStream oToClient = 	new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream oFromClient = new ObjectInputStream(connection.getInputStream());
	
		    Student student = null;
			Timetable timeTable = null;
			String file = null;
			String option = null;
			do{
				System.out.println("Handler receives option#: " + option);
				switch(option){
				case "1":
						oToClient.flush();
						student = (Student) oFromClient.readObject(); 
						student.registerStudent(" " + student.hashCode());
						oToClient.writeObject(student);
						oToClient.flush();
						break;
						
				case "2":
						tTable = (Timetable) oFromClient.readObject();
						System.out.println( timeTable.getRegisteredId() );
						file = timeTable.getRegisteredId() + "_timetable";
						System.out.println( file );
						ObjectOutputStream outToFile = new ObjectOutputStream( new FileOutputStream( file ) );
						outToFile.writeObject(timeTable);
						outToFile.close();
						break;
						
				}
		    }while( !( ( option = inHandler.readLine() ).trim() ).equals("exit")  ) ; 
		    
			inHandler.close();
			outHandler.close();
			connection.close();
			
		}catch(IOException io){
			io.getMessage();
			io.printStackTrace();
		}
		catch(ClassNotFoundException e){
			e.getMessage();
			e.printStackTrace();
		}
		
	}// run()
}