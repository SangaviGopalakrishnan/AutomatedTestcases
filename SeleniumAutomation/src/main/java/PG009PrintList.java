import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PG009PrintList {

	public static void main(String[] args) {
		
		List<Character> list = new ArrayList<Character>();
		list.add('f');
		list.add('n');
		list.add('s');
		list.add('j');
		
		//Approach 1
		for (Character character : list) {
			System.out.println(character);
		}

		//Approach 2
		System.out.println(list.toString());
		
		//Approach 3
		System.out.println(Arrays.asList(list.toArray()));
	}
}
