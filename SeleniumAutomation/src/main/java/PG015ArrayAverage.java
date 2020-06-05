public class PG015ArrayAverage {

	public static void main(String[] args) {

		int arr[]= {15,25,47,-10,32,54,96,41};
		int sum =0;
		//Approach 1
		for (int i = 0; i < arr.length; i++) {
			sum=sum+arr[i];
		}
		int average = sum/arr.length;
		System.out.println("Average is "+ average);
	}
}
