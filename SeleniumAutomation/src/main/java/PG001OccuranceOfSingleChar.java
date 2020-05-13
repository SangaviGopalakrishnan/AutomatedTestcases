import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PG001OccuranceOfSingleChar {

	public static void main(String[] args) {

		String str ="You can have no choice other than following meal";

		// Approach 1
		char[] chararr = str.toCharArray();
		int count = 0;
		for (int i = 0; i < chararr.length; i++) {
			if (chararr[i] == 'o') {
				count++;
			}
		}
		System.out.println("Occurance is "+ count);

		// Approach 2
		int count1 = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'o') {
				count1++;
			}
		}
		System.out.println("Occurance is "+ count1);

		// Approach 3
		String newstr = str.replaceAll("[^o]", "");
		System.out.println("Occurance is "+ newstr.length());

		// Approach 4
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		char[] ch = str.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			if (map.containsKey(ch[i])) {
				map.put(ch[i], map.get(ch[i])+1);
			} else {
				map.put(ch[i], 1);
			}
		}
		Set<Entry<Character, Integer>> entrySet = map.entrySet();
		for (Entry<Character, Integer> entry : entrySet) {
			if (entry.getKey() == 'o') {
				System.out.println(entry.getKey() +"--->"+ entry.getValue());
			}
		}
	}

}
