import java.util.Arrays;
import java.util.Iterator;

public class TC001Merge2Arrays {
	
	public static void main(String args[]) {
		
		int[] arr1 = {1,2,3,4,5};
		int[] arr2 = {6,7,8,9};
		int[] arr3 = new int[arr1.length + arr2.length];
//		System.arraycopy(arr1, 0, arr3, 0, arr1.length);
//		System.arraycopy(arr2, 0, arr3, arr1.length, arr2.length);
	
		
		//Another approach
		for (int i = 0; i < arr1.length; i++) {
			arr3[i]=arr1[i];
		}
		for (int i = 0; i < arr2.length; i++) {
			arr3[arr1.length+i]=arr2[i];
		}
		
		System.out.println(Arrays.toString(arr3));
		
//		 int[] array1 = {1, 2, 3};
//	        int[] array2 = {4, 5, 6};
//
//	        // Convert IntStream to Stream<Integer>
//	        Stream<Integer> stream1 = Arrays.stream(array1).boxed();
//	        Stream<Integer> stream2 = Arrays.stream(array2).boxed();
//
//	        // Merge the two streams
//	        Integer[] mergedArray = Stream.concat(stream1, stream2).toArray(Integer[]::new);
//
//	        // Print the merged array
//	        System.out.println("Merged Array: " + Arrays.toString(mergedArray));
	    
	}

}
