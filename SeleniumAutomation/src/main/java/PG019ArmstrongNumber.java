
public class PG019ArmstrongNumber {

	public static void main(String[] args) {

		//Approach 1
		int sum=0;
		for (int i = 0; i < 1000; i++) {
			int temp = i;
			while (temp > 0) {
				int r = temp % 10;
				sum = sum + (r * r * r);
				temp = temp / 10;
			}
			if (sum == i) {
				System.out.println(i);
			}
			sum=0;
		}
		
		//Approach 2
		int limit = 1000;
		for (int i = 1; i <= limit; i++) {
			String a = Integer.toString(i);
			int digits = a.length();
			if(digits==1)
			{
				System.out.print("  "+i);
			}
			else if(digits==2)
			{
				int sum1 = (int) (Math.pow((i/10),2) + Math.pow((i%10), 2));
				//System.out.println("i="+i+" Sum="+sum);
				if(sum1==i)
				System.out.print(" "+i);
			}
			else if(digits==3)
				{
				int sum1 = (int) (Math.pow((i/100),3) + Math.pow(((i%100)/10), 3) + Math.pow(((i%100)%10), 3));
				if(sum1==i)
					System.out.print(" "+i);
				}
	}	
	}
}
