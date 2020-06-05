import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PG017NumberSwapping {

	public static void main(String[] args) {

		int a=10, b=20;
		
		//Approach 1
		
		int temp =a;
		a=b;
		b=temp;
		
		//Approach 2
		a=a+b;
		b=a-b;
		a=a-b;
		
		System.out.println(a +"," +b);
	}
}
