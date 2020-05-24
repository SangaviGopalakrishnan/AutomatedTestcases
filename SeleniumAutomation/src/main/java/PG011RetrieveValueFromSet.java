import java.util.LinkedHashSet;
import java.util.Set;

public class PG011RetrieveValueFromSet {

	public static void main(String[] args) {
		
		Set<Character> set = new LinkedHashSet<Character>();
		set.add('f');
		set.add('n');
		set.add('s');
		set.add('j');
		set.add('f');
		set.add('k');
		set.add('j');
		
		//Approach 1
		if (set.contains('k')) {
			System.out.println('k');
		}
		
		//Approach 2
		for (Character character : set) {
			if (character == 'k') {
				System.out.println(character);
			}
		}
		
	}
}
