import java.util.Arrays;

public class PG014ArrayReversal {

	public static void main(String[] args) {

		int arr[]= {15,25,47,10,32,54,96,41};
		
		//Approach 1
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					int temp = arr[i];
					arr[i]=arr[j];
					arr[j] = temp;
				}
			}
		}
		
		//Approach 2
		Arrays.sort(arr);
		
		for (int i = arr.length-1; i >=0 ; i--) {
			System.out.println(arr[i]);
		}
	}
}
