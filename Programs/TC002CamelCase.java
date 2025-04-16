import java.util.Arrays;
import java.util.Iterator;

public class TC002CamelCase {
	
	public static void main(String args[]) {
		
		String str = "Cats AND Dogs/Are%*There";
		TC002CamelCase camel = new TC002CamelCase();
		camel.modifiedString(str);
	}

	private String modifiedString(String str) {
		
		String lower = str.toLowerCase();
		String arr[] = lower.split("[^A-Za-z]");
		StringBuilder sb=new StringBuilder();
		sb.append(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			char[] ch = arr[i].toCharArray();
			for (int j = 0; j < ch.length; j++) {
				if(j==0) {
					sb.append(Character.toUpperCase(ch[0]));
				}
				else {
					sb.append(ch[j]);
				}
				
			}
		}
		
		System.out.println(sb.toString());
		return sb.toString();
		// TODO Auto-generated method stub
		
	}

}
