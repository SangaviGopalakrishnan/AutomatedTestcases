import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PG008PrintDuplicates {

	public static void main(String[] args) {

		String str ="gf ugere weteit etoieuio eiui";
		
		// Approach 1
		char[] charArray = str.toCharArray();
		Map<Character,Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < charArray.length; i++) {
			char ch = charArray[i];
			if (map.containsKey(charArray[i])) {
				map.put(ch,map.get(ch)+1);
			} else {
				map.put(ch, 1);
			}
		}
		Set<Entry<Character, Integer>> entrySet = map.entrySet();
		for (Entry<Character, Integer> entry : entrySet) {
			if (entry.getValue()>1) {
				System.out.println(entry.getKey() +"---> "+entry.getValue());
			}
		}
		
		//Approach 2
		char[] charArray1 = str.toCharArray();
		for (int i = 0; i < charArray1.length; i++) {
			for (int j = i+1; j < charArray1.length; j++) {
				if (charArray1[i] == charArray1[j]) {
					System.out.print(charArray1[i]);
					break;
				}
			}
		}
		
		//Approach 3
		Set<Character> set = new LinkedHashSet<>();
		Set<Character> dupset = new LinkedHashSet<>();
		for (int i = 0; i < str.length(); i++) {
			if (set.contains(str.charAt(i))) {
				dupset.add(str.charAt(i));
			} else {
				set.add(str.charAt(i));
			}
		}
		System.out.println(dupset.toString());
	}
}
