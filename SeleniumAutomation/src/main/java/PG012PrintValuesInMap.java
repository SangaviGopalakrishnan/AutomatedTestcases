import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PG012PrintValuesInMap {

	public static void main(String[] args) {
		
		Map<Character, Integer> map = new LinkedHashMap<Character, Integer>();
		map.put('A', 3);
		map.put('B', 2);
		map.put('C', 1);
		
		//Approach 1
		System.out.println(map);
		
		//Approach 2
		Set<Entry<Character, Integer>> entrySet = map.entrySet();
		for (Entry<Character, Integer> entry : entrySet) {
			System.out.println(entry.getKey() +" --> "+ entry.getValue());
		}
		
		//Approach 3
		map.forEach((key,val) -> System.out.println(key+"->"+val));
		
		//Approach 4
		Iterator<Entry<Character, Integer>> iterator = map.entrySet().iterator();
		iterator.forEachRemaining((entry) -> System.out.println(entry.getKey() +"--> "+entry.getValue()));
		
		for (Iterator iterator2 = entrySet.iterator(); iterator2.hasNext();) {
			Entry<Character, Integer> entry = (Entry<Character, Integer>) iterator2.next();
			
		}
	}
}
