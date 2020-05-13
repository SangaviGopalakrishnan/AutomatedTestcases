import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class PG002Palindrome {

	public static void main(String[] args) {

		String str ="liril";

		// Approach 1
		StringBuilder sb = new StringBuilder(str);
		String string = sb.reverse().toString();
		if (str.equals(string)) {
			System.out.println("The given string is a palindrome");
		}else {
			System.out.println("Given string is not a palindrome");
		}

		// Approach 2
		String rev = "";
		for (int i = str.length()-1	; i>0; i--) {
			rev=rev + str.charAt(i);
		}
		if (str.equals(rev)) {
			System.out.println("The given string is a palindrome");
		}else {
			System.out.println("Given string is not a palindrome");
		}

		// Approach 3
		int count = 0 , j =str.length()-1;
		for (int i = 0; i < str.length()/2; i++) {
			if (str.charAt(i) == str.charAt(j)) {
				j--;
				break;
			}
			else {
				count++;
			}

		}
		if (count==0) {
			System.out.println("The given string is a palindrome");
		}else {
			System.out.println("Given string is not a palindrome");
		}
	}

}
