import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC004StoreHPAutomation {

	public static void main(String[] args) throws InterruptedException {		

		//1) Go to https://store.hp.com/in-en/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://store.hp.com/in-en/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Thread.sleep(3000);
		driver.findElementByXPath("//button[@class='optanon-alert-box-close banner-close-button']").click();
		try {
			boolean frame = driver.findElementByXPath("//iframe[@name='ifr_edmpopup']").isDisplayed();
			if (frame == true) {
				driver.findElement(By.xpath("//span[@class=\"optly-modal-close close-icon\"]")).click();
			}
		} catch (Exception e) {
			System.out.println("No frame");
		}

		//2) Mouse over on Laptops menu and click on Pavilion
		WebElement laptop = driver.findElementByXPath("//span[text()='Laptops']");
		Actions option = new Actions(driver);
		option.moveToElement(laptop).perform();
		driver.findElementByXPath("//span[text()='Pavilion']").click();
		Thread.sleep(2000);

		//3) Under SHOPPING OPTIONS -->Processor -->Select Intel Core i7
		driver.findElementByXPath("(//span[text()='Processor'])[2]").click();
		driver.findElementByXPath("//span[text()='Intel Core i7']/parent::a/input").click();
		Thread.sleep(5000);
		
		//4) Hard Drive Capacity -->More than 1TB
		driver.findElementByXPath("//span[text()='More than 1 TB']/parent::a/input").click();
		Thread.sleep(5000);
		
		//5) Select Sort By: Price: Low to High
		WebElement sort = driver.findElementById("sorter");
		Select opt = new Select(sort);
		opt.selectByValue("price_asc");
		Thread.sleep(5000);
		
		//6) Print the First resulting Product Name and Price
		String text = driver.findElementByXPath("(//strong[@class='product name product-item-name']/a)[1]").getText();
		System.out.println("Product Name is "+text);
		String price = driver.findElementByXPath("(//span[@data-price-type='finalPrice'])[1]").getText().replaceAll("\\D", "");
		System.out.println("Price is "+price);
		
		//7) Click on Add to Cart
		driver.findElementByXPath("(//span[text()='Add To Cart'])[1]").click();
		Thread.sleep(5000);
		
		//8) Click on Shopping Cart icon --> Click on View and Edit Cart
		driver.findElementByXPath("//div[@class=\"inside_closeButton fonticon icon-hclose\"]").click();
		driver.findElementByXPath("//a[@title='Shopping Cart']").click();
		driver.findElementByXPath("//span[text()='View and edit cart']").click();
		Thread.sleep(10000);
		
		//9) Check the Shipping Option --> Check availability at Pincode
		driver.findElementByXPath("//input[@name='pincode']").sendKeys("638002");
		driver.findElementByXPath("//button[text()='check']").click();
		Thread.sleep(5000);
		
		//10) Verify the order Total against the product price
		String Total = driver.findElementByXPath("//td[@data-th='Order Total']/strong/span").getText().replaceAll("\\D", "");
		if (Total.equals(price)) {
			System.out.println("Price are equal");
			
			//11) Proceed to Checkout if Order Total and Product Price matches
			driver.findElementByXPath("//span[text()='Proceed to Checkout']").click();
			Thread.sleep(3000);
			
			//12) Click on Place Order
			driver.findElementByXPath("(//span[text()='Place Order'])[3]").click();
			
			//13) Capture the Error message and Print
			System.out.println("Error message is "+driver.findElementByXPath("//div[@class='message notice']/span").getText());
		} else {
			System.out.println("Price are not equal");
		}
		
		//14) Close Browser
		driver.quit();
	}

}
