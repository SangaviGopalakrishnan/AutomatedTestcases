import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class TC001MyntraAutomation {

	public static void main(String[] args) throws InterruptedException {

//		1) Open https://www.myntra.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable notifications");
		ChromeDriver driver = new ChromeDriver(coptions);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
//		2) Mouse over on WOMEN (Actions -> moveToElement
		WebElement women = driver.findElementByXPath("//a[text()='Women']");
		Actions action = new Actions(driver);
		action.moveToElement(women).perform();
		
//		3) Click Jackets & Coats
		driver.findElementByXPath("//a[text()='Jackets & Coats']").click();
		
//		4) Find the total count of item (top) -> getText -> String
//
//			 String str = driver.findElementByClassName("title-count").getText();
//			 split, 
//			 String text = str.replaceAll("\\D","") -> String
//			 Integer.parseInt(text) -> int
		String text = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		int ActualCount = Integer.parseInt(text.replaceAll("\\D", ""));
		
//		5) Validate the sum of categories count matches
		int ExpectedCount= 0;
		List<WebElement> list = driver.findElementsByXPath("//span[@class='categories-num']");
		for (int i = 0; i <list.size(); i++) {
			String text2=list.get(i).getText();
			int int1 = Integer.parseInt(text2.replaceAll("\\D", ""));
			ExpectedCount = ExpectedCount+int1;
		}
		if (ActualCount == ExpectedCount) {
			System.out.println("Count matches");
		} else {
			System.out.println("Count not matches");
		}
		
//		6) Check Coats
		driver.findElementByXPath("//input[@value='Coats']//following::div[@class=\"common-checkboxIndicator\"]").click();
		
//		7) Click + More option under BRAND
		driver.findElementByXPath("//div[text()='+ ']").click();
		
//		8) Type MANGO and click checkbox
		driver.findElementByXPath("//input[@class='FilterDirectory-searchInput']").sendKeys("MANGO");
		driver.findElementByXPath("(//input[@value='MANGO']//following-sibling::div)[2]").click();
		
//		9) Close the pop-up x
		driver.findElementByXPath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		Thread.sleep(5000);
		
//		10) Confirm all the Coats are of brand MANGO
//	    findElements (brand) -> List<WebElement> 
//	    foreach -> getText of each brand 
//	    compare > if(condition)
		List<WebElement> brand = driver.findElementsByXPath("//h3[@class='product-brand']");
		for (int i = 0; i <brand.size(); i++) {
			String text2 = brand.get(i).getText();
			if (text2.contains("MANGO")) {
				System.out.println(i+"is matching");
			} else {
				System.out.println(i+"is not matching");
			}
		}
		
//		11) Sort by Better Discount
		WebElement sort = driver.findElementByXPath("//div[@class='sort-sortBy']");
		Thread.sleep(10000);
		action.moveToElement(sort).perform();
		driver.findElementByXPath("//input[@value='discount']/parent::label").click();
		
//		12) Find the price of first displayed item
//	     findElements (price) -> List<WebElement> 
//	     get(0) -> WebElement -> getText -> String -> int
		List<WebElement> price = driver.findElementsByXPath("//span[@class='product-discountedPrice']");
		int priceval = Integer.parseInt(price.get(0).getText().replaceAll("\\D", ""));
		System.out.println("Price val is "+priceval);
		
//		13) Mouse over on size of the first item
		action.moveToElement(driver.findElementByXPath("(//h3[@class='product-brand'])[1]")).perform();
		Thread.sleep(5000);
		
//		14) Click on WishList Now
		driver.findElementByXPath("//span[@class='product-actionsButton product-wishlist product-prelaunchBtn']").click();
		System.out.println("Title of login page is "+ driver.getTitle());
		
//		15) Close Browser
		driver.close();
	}

}
