public class PG004ReverseEvenWords {

	public static void main(String[] args) {

		String str ="when wjf adkjwlkf 787qw qwwk wewkjfk";

		// Approach 1
		String[] arr = str.split(" ");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <arr.length; i++) {
			if (i%2!=0) {
				String ne ="";
				for (int j = arr[i].length()-1; j >=0; j--) {
					 ne = ne+ arr[i].charAt(j);
				}
				sb.append(ne);
			} else {
				sb.append(arr[i]);
			}
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}
