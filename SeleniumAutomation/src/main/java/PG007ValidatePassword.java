import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PG007ValidatePassword {

	public static void main(String[] args) {

		String str ="Sangavi_1";

		// Approach 1
		String pattern = "[a-zA-Z0-9._]{8,}"; //{8,} tells the min length of the word 
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(str);
		System.out.println(matcher.matches());
	}
}
