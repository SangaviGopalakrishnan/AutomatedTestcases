public class PG005StringOccurance {

	public static void main(String[] args) {

		String str ="Ih jhgsfRR jfkwej 548 uwyrueTTT";

		// Approach 1
		char[] charArray = str.toCharArray();
		int upper=0,lower=0,no=0,space = 0;
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isUpperCase(charArray[i])) {
				upper++;
			}else if (Character.isLowerCase(charArray[i])) {
				lower++;
			}else if (Character.isDigit(charArray[i])) {
				no++;
			}else {
				space++;
			}
		}
		System.out.println(upper);
		System.out.println(lower);
		System.out.println(no);
		System.out.println(space);

		// Approach 2
		System.out.println(str.replaceAll("[^A-Z]", "").length());
		System.out.println(str.replaceAll("[^a-z]", "").length());
		System.out.println(str.replaceAll("[^0-9]", "").length());
		System.out.println(str.replaceAll("\\S", "").length());

		// Approach 3
		char[] charArray1 = str.toCharArray();
		int upper1=0,lower1=0,no1=0,space1 = 0;
		for (int i = 0; i < charArray1.length; i++) {
			if (charArray1[i]>=48 && charArray1[i]<=57) {
				upper1++;
			}else if (charArray1[i]>=65 && charArray1[i]<=90) {
				lower1++;
			}else if (charArray1[i]>=97 && charArray1[i]<=122) {
				no1++;
			}else if(charArray1[i] == 32){
				space1++;
			}
		}
		System.out.println(upper1);
		System.out.println(lower1);
		System.out.println(no1);
		System.out.println(space1);
	}
}
