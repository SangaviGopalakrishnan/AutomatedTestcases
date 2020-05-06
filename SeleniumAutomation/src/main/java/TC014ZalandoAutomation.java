import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC014ZalandoAutomation {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		//1) Go to https://www.zalando.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.zalando.com/");

		//2) Get the Alert text and print it
		Thread.sleep(5000);
		System.out.println(driver.switchTo().alert().getText());

		//3) Close the Alert box and click on Zalando.uk
		driver.switchTo().alert().accept();
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();

		//4) Click Women--> Clothing and click Coat
		Thread.sleep(5000);
		try {
			driver.findElementByXPath("//button[@id=\"uc-btn-accept-banner\"]").click();
		} catch (Exception e) {
			System.out.println("thrown");
		}
		driver.findElementByXPath("(//span[text()=\"Women\"])[1]").click();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//span[text()=\"Clothing\"]")).perform();
		driver.findElementByXPath("//span[text()='Coats']").click();

		// 5) Choose Material as cotton (100%) and Length as thigh-length
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//span[text()=\"Material\"]/ancestor::button")).click().perform();
		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		action.moveToElement(driver.findElementByXPath("//button[text()='Save']")).click().perform();
		Thread.sleep(5000);

		action.moveToElement(driver.findElementByXPath("//span[text()=\"Length\"]/ancestor::button")).click().perform();
		driver.findElementByXPath("//span[text()='thigh-length']").click();
		action.moveToElement(driver.findElementByXPath("//button[text()='Save']")).click().perform();
		Thread.sleep(5000);

		//6) Click on Q/S designed by MANTEL - Parka coat
		driver.findElementByXPath("//div[text()='MANTEL - Parka - navy']").click();

		//7) Check the availability for Color as Olive and Size as 'M'
		driver.findElementByXPath("(//img[@alt=\"olive\"])[2]").click();

		//8) If the previous preference is not available, check  availability for Color Navy and Size 'M'
		boolean displayed = driver.findElementByXPath("//h2[text()='Out of stock']").isDisplayed();
		if (displayed == true) {
			driver.findElementByXPath("(//img[@alt=\"navy\"])[2]").click();
			Thread.sleep(3000);
			driver.findElementByXPath("//button[@id=\"picker-trigger\"]").click();
			driver.findElementByXPath("//span[text()='M']/parent::div").click();
		}else {
			driver.findElementByXPath("//button[@id=\"picker-trigger\"]").click();
			driver.findElementByXPath("//span[text()='M']/parent::div").click();	
		}
		
		//9) Add to bag only if Standard Delivery is free
		Thread.sleep(3000);
		String text = driver.findElementByXPath("(//span[text()=\"Standard delivery\"]/parent::div/div/div/button/span)[1]").getText();
		System.out.println(text);
		if (text.equals("Free")) {
			driver.findElementByXPath("//span[text()='Add to bag']").click();
		}
		
		//10) Mouse over on Your Bag and Click on "Go to Bag"
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//span[text()='Your bag']")).click().perform();
//		driver.findElementByXPath("//div[text()='Go to bag']").click();
		
		//11) Capture the Estimated Deliver Date and print
		Thread.sleep(5000);
		System.out.println(driver.findElementByXPath("//div[@data-id=\"delivery-estimation\"]/span").getText());
		
		//12) Mouse over on FREE DELIVERY & RETURNS*, get the tool tip text and print
		action.moveToElement(driver.findElementByXPath("//a[text()='Free delivery & returns*']")).perform();
		System.out.println(driver.findElementByXPath("//a[text()='Free delivery & returns*']/parent::span").getAttribute("title"));
		
		//13) Click on Start chat in the Start chat and go to the new window
		driver.findElementByXPath("//a[text()='Free delivery & returns*']").click();
		driver.findElementByXPath("//span[text()='Start chat']/parent::button").click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		//14) Enter you first name and a dummy email and click Start Chat
		driver.findElementByXPath("//input[@id='prechat_customer_name_id']").sendKeys("world");
		driver.findElementByXPath("//input[@id='prechat_customer_email_id']").sendKeys("abc@gmail.com");
		driver.findElementById("prechat_submit").click();
		
		//15) Type Hi, click Send and print thr reply message and close the chat window.
		
		driver.findElementById("liveAgentChatTextArea").sendKeys("Hi");
		driver.findElementByXPath("//button[text()='Send']").click();
		System.out.println(driver.findElementByXPath("(//span[@class='operator'])[3]//span[2]").getText());
		driver.close();
	}

}
