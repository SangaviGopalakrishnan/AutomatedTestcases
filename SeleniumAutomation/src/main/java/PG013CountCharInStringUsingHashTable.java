import java.util.HashMap;
import java.util.Hashtable;

public class PG013CountCharInStringUsingHashTable {

	public static void main(String[] args) {
		
		String str = "wbhgffuuyuuhgjhw wbrjhwerweoi weuryewir ";
		//Approach 1
		Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
		int i = 0;
		while (i<str.length()) {
			Integer ifPresent = table.computeIfPresent(str.charAt(i), (k,v)->v+1);
			if (ifPresent == null) {
				table.computeIfAbsent(str.charAt(i), v->1);
			}
			i++;
			
		}
		System.out.println(table);
		
		//Approach 2 
		Hashtable<Character, Integer> table1 = new Hashtable<Character, Integer>();
		char[] charArray = str.toCharArray();
		for (int j = 0; j < charArray.length; j++) {
			if (table1.containsKey(charArray[j])) {
				table1.put(charArray[j],table1.get(charArray[j])+1);
			} else {
				table1.put(charArray[j], 1);
			}
		}
		System.out.println(table1);
		
		//Approach 3
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] charArray1 = str.toCharArray();
		for (int j = 0; j < charArray1.length; j++) {
			if (map.containsKey(charArray1[j])) {
				map.put(charArray1[j], map.get(charArray1[j])+1);
			} else {
				map.put(charArray1[j], 1);
			}
		}
		System.out.println(map);
	}
}
