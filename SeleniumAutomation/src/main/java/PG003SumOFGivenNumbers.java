public class PG003SumOFGivenNumbers {

	public static void main(String[] args) {

		String str ="ewr1wr9werw5wre7wer5";

		// Approach 1
		int sum=0;
		String newstr = str.replaceAll("\\D", "");
		for (int i = 0; i < newstr.length(); i++) {
			sum = sum+Integer.parseInt(String.valueOf(newstr.charAt(i)));
		}
		System.out.println(sum);

		// Approach 2
		char[] charArray = str.toCharArray();
		int sum1=0;
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isDigit(charArray[i])) {
				sum1 = sum1+Integer.parseInt(String.valueOf(charArray[i]));
			}
		}
		System.out.println(sum1);

		// Approach 3
		int sol=0,rem;
		String newstr1 = str.replaceAll("[^0-9]", "");
		int no = Integer.parseInt(newstr1);
		while (no>0) {
			rem = no%10;
			sol = sol+rem;
			no=no/10;
		}
		System.out.println(sol);
	}
}
