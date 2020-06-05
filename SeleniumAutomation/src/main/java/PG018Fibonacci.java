
public class PG018Fibonacci {

	public static void main(String[] args) {

		int a =-1,b=1,c ,no = 0;
		int arr[] = new int[10];
		while (no<10) {
			c=a+b;
			arr[no] = c;
			no++;
			a=b;
			b=c;
		}
		for (int i = arr.length-1; i >=0 ; i--) {
			System.out.print(arr[i]+"\t");
		}
	}
}
