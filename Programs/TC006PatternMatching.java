import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TC006PatternMatching {

    public static void main(String[] args) {

        String pattern = "^[A-Za-z0-9._%+-]+@gmail\\.[a-z]{2,6}$";
        String str = "pgasdas@gmail.cn";

        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(str);

        if (matcher.matches()) {
            System.out.println("True: Valid Gmail address");
        } else {
            System.out.println("False: Invalid Gmail address");
        }
    }
}
