/*Input:
arr = {5, 8, 9, 6, 3, 2, 4, 7}

Expected Output:
8 9 -1 7 4 4 7 -1

Explanation:

5 → next greater is 8

8 → next greater is 9

9 → no greater on right → -1

6 → next greater is 7

3 → next greater is 4

2 → next greater is 4

4 → next greater is 7

7 → no greater → -1*/

public class TC004NextGreaterElement {
	
    public static void main(String[] args) {
        int[] arr = {5, 8, 9, 6, 3, 2, 4, 7};
        int[] result = nextGreaterElement(arr);

        System.out.println("Next Greater Elements:");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }

    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            res[i] = -1;  // default value
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    res[i] = arr[j];
                    break;
                }
            }
        }
        return res;
    }
}
