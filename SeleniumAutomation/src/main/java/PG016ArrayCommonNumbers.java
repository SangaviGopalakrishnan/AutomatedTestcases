import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PG016ArrayCommonNumbers {

	public static void main(String[] args) {

		int arr[]= {15,25,47,15,-10,32,54,96,15,25,32,41};
		
		//Approach 1
		Set<Integer> duplicate = new LinkedHashSet<>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					duplicate.add(arr[i]);
				}
			}
		}
		System.out.println(duplicate);
		
		//Approach 2
		Map<Integer,Integer> map = new LinkedHashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int ch = arr[i];
			if (map.containsKey(arr[i])) {
				map.put(ch,map.get(ch)+1);
			} else {
				map.put(ch, 1);
			}
		}
		Set<Entry<Integer, Integer>> entrySet = map.entrySet();
		for (Entry<Integer, Integer> entry : entrySet) {
			if (entry.getValue()>1) {
				System.out.println(entry.getKey() +"---> "+entry.getValue());
			}
		}
	}
}
