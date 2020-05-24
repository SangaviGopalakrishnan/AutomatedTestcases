import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PG006ValidateEmail {

	public static void main(String[] args) {

		String str ="hello@gmail.com";

		// Approach 1
		String pattern = "[a-zA-Z0-9._]{8,}+@[a-z0-9]+.[a-z]{2,}"; //{8,} tells the min length of the word 
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		System.out.println(matcher.matches());
	}
}
