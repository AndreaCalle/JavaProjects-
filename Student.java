import java.io.Serializable;
public class Student implements Serializable, Cloneable {

	private String fName;
	private String lName;
	private int age;
	private String registeredId;
	

	Student(String fname, String lname, int age){
		fName = fname;
		lName = lname;
		this.age = age;
		registeredId = "unregistered";
	}
	
	public String getRegisteredId(){
		return registeredId;
	}
	public void registerStudent(String hashCode){
		registeredId = hashCode;
	}
	
	
	public void setRegisteredId(int regID){
		registeredId = Integer.toString(regID);
	}
	@Override
	public String toString(){

		return "First Name:" + fName +
			   " Last Name:" + lName + 
			   " Age:"		  + age	  +
			   " Registration ID:" + registeredId +
			   " " ;
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj) return true;
		
		if (obj == null) return false;
		
		if (this.getClass() != obj.getClass()) return false;
		
		Student token = (Student)obj;
		
		if ( fName.compareTo(token.fName) != 0 || lName.compareTo(token.lName) != 0 || age != token.age || registeredId.compareTo(token.registeredId) != 0 ) return false;
		 
		return true;
	}
/*
	public int hashCode(){
		int hash = 1;
		hash = 31 * hash + (null == lName || 0 : lName.hashCode());
		hash = hash + age;
        return hash ;
	}

*/
	public Object clone() throws CloneNotSupportedException{
		Student temp = (Student)super.clone();
		temp.fName = fName;
		temp.lName = lName;
		temp.age = age;
		temp.registeredId = registeredId;
		return temp;
	}
	
	public static void main(String args[]) throws CloneNotSupportedException{
		
	    Student student1 = new Student("Andrea", "Calle", 21);
		Student student2 = new Student("Emil", "Smith", 21);
		System.out.println( student1 );
		System.out.println( student2 );

	}
}
