import java.io.IOException;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Timetable implements Serializable, Cloneable {

	private String registeredID;	
	private String[][] timetable; 
	public Timetable( Student s, String file ){
		registeredID = s.getRegisteredId();
		timetable = new String[12][5];
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(file)));
			int line = 0;
			for (String x = in.readLine(); x !=  null; x = in.readLine() ){
				if (line <= 11){
					String[] tokens = x.split(",");
					for (int i = 0; i < 5; i++ ){
						timetable[line][i] = tokens[i];
					}
					line++;
				}else{
					break;
				}
			}
			in.close();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getRegisteredId(){
		return registeredID;
	}
	
	public String getCourse(int period, int day){
		if( timetable[period][day].equals("\"") == true ){
			return "free";
		}else{
			return timetable[period][day];
		}
	}
	
	public String toString(){
		String s = null;
		s = "identified by file named: " + registeredID ; 
		for (int i = 0; i < 12; i ++)
		{
			for (int j = 0; j < 5; j ++)
			{
					s += timetable[i][j];
			}
			s+="\n";
		}
		return s;
	}

	public boolean equals(Object obj){
		
		if (this == obj) return true;
		if (obj == null) return false;
		if (this.getClass() != obj.getClass()) return false;
		Timetable token = (Timetable)obj;
		if ( registeredID.equals(token.registeredID) == false ) return false; // Assume that a student cannot change their timetable and resubmit.
		
		for (int i = 0; i < 12; i ++)
			if ( timetable[i][j].equals( token.timetable[i][j] ) == false )
						return false;
		return true;
	}
	
	public int hashCode(){
		int hash = 1;
		hash = 7 * hash + (null == registeredID ? 0 : registeredID.hashCode());
		
		for (int i = 0; i < 12; i ++)
			for (int j = 0; j < 5; j ++)
				hash = 7 * hash + ( null == timetable[i][j] ? 0 : timetable[i][j].hashCode() );
	
		return hash;
	}

	
	public static void main(String args[]) throws CloneNotSupportedException{
		Student student02 = new Student("Andrea", "Calle", 21);
		Student student03 = new Student("Emil", "Smith", 21);
		Timetable time = new Timetable(student01, "timetable.txt");
		System.out.println( time );
		
		
		System.out.println( time );			
	}
}