import java.util.*;

public class TC003GetTheIndexOfElementInArray {
	
	public static void main(String args[]) {
		
		int arr[]= {1,3,3,4,5,6,6,7,8,9,9};
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value");
		int input = sc.nextInt();
		boolean count = false;
		for (int i = 0; i < arr.length; i++) {
			if(arr[i]==input) {
				System.out.println("Index is "+i);
				count = true;
			}
		}
		if(count == false)
			System.out.println("element not found");
		
	}

}
