import java.util.*;

public class TC008TripletPattern {

    public static void findTriplets(int[] arr, int target) {
        int n = arr.length;
        Set<String> seen = new HashSet<>(); // To avoid duplicate triplets

        for (int i = 0; i < n - 1; i++) {
        	Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n ; j++) {

                    int sum = target - arr[j] - arr[i];
                    if (set.contains(sum)) {
                        int[] triplet = {sum, arr[j], arr[i]};
                        Arrays.sort(triplet); // Sort to avoid duplicates
                        String key = triplet[0] + "," + triplet[1] + "," + triplet[2];
                        if (!seen.contains(key)) {
                            System.out.println(Arrays.toString(triplet));
                            seen.add(key);
                        
                    }
                }
                    set.add(arr[j]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, -1, 0, -2, 1};
        int target = 0;
        findTriplets(arr, target);
    }
}
