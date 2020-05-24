import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PG010RemoveDuplicatesFromList {

	public static void main(String[] args) {
		
		List<Character> list = new ArrayList<Character>();
		list.add('f');
		list.add('n');
		list.add('s');
		list.add('j');
		list.add('f');
		list.add('k');
		list.add('j');
		ArrayList<Character> ar = new ArrayList<>(list);
		
		//Approach 1
		Set<Character> set = new LinkedHashSet<>(list);
		System.out.println(set);
		
		//Approach 2
		for (int i = 0; i < list.size(); i++) {
			for (int j = i+1; j < list.size(); j++) {
				if (list.get(i) == list.get(j)) {
					list.remove(j);
					break;
				} 
			}
		}
		System.out.println(list);
		
		//Approach 3
		System.out.println(ar.stream().distinct().collect(Collectors.toList()));
	}
}
