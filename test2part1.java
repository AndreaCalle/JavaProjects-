import java.util.*;

public class ListIterateExample {
	public static void main(String a[]){
		
        List<String> strList = new LinkedList<>();
        
        strList.add("First");
        strList.add("Second");
        strList.add("Third");
        
        Iterator<String> it = strList.iterator();
        while(it.hasNext()){
            it.next().toLowerCase();
        }
       
       // strList.removeIf(strList.length() <= 5);
        System.out.println(strList);
    }
}