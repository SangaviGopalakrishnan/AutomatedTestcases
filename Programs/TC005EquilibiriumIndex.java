
public class TC005EquilibiriumIndex {
	
//	If leftSum == rightSum, that index is the equilibrium.
	
	public static void main (String[] args) {
		
		int arr[] = {3, 1, 5, 2, 2};
		
		for (int i = 0; i < arr.length; i++) {
			int leftsum =0;
			int rightsum=0;
			
			for (int k = i-1; k >=0; k--) {
				 leftsum += arr[k];
			}
			
			for (int j = i+1; j < arr.length; j++) {
				 rightsum += arr[j];
			}
			
			if(leftsum == rightsum) {
				System.out.println(i);
			}
			
		}
		
		
		
	}

}
