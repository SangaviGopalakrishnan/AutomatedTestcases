public class TC007JumpGameProblem {

	public static void main(String[] args) {
		int[] arr = {2, 3, 1, 1, 4}; // Example input
		int result = canReachEnd(arr);

		if (result > -1) {
			System.out.println("Reached the last index" + result);
		} else {
			System.out.println("Cannot reach the last index");
		}
	}

	public static int canReachEnd(int[] nums) {

		if (nums.length < 0)
			return -1;
		if (nums[0] == 0)
			return -1;

		int reachable = 0;
		int jumps = 0;
		int end = 0;
		for (int i = 0; i < nums.length - 1; i++) {

				reachable = Math.max(reachable, i + nums[i]);
				
			if(i==end) {
				jumps++;
				end = reachable;
			}
		}
		return jumps;

	}
}