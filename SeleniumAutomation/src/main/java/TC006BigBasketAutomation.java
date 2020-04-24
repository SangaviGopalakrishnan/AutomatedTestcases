import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC006BigBasketAutomation {

	public static void main(String[] args) throws InterruptedException {
		//1) Go to https://www.bigbasket.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(coptions);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//Setting location
		driver.findElementByClassName("hvc").click();
		WebElement city = driver.findElementByXPath("//div[@qa='cityDD']");
		city.click();
		driver.findElementByXPath("//span[@qa='citySelectList'][contains(text(),'Coimbatore')]").click();
		driver.findElementById("areaselect").sendKeys("641004",Keys.TAB);
		driver.findElementByXPath("//button[text()='Continue']").click();

		Thread.sleep(5000);
		//2) mouse over on  Shop by Category 
		Actions action = new Actions(driver);
		WebElement shop = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		action.moveToElement(shop).perform();

		//3)Go to FOODGRAINS, OIL & MASALA --> RICE & RICE PRODUCTS 
		action.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Foodgrains, Oil & Masala')])[2]"))).perform();;
		driver.findElementByXPath("(//a[@qa='catL2'][contains(text(),'Rice')])[2]").click();	
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(5000);

		//4) Click on Boiled & Steam Rice
		driver.findElement(By.xpath("//span[text()='Boiled & Steam Rice']")).click();

		//5) Choose the Brand as bb Royal
		driver.findElementByXPath("//span[text()='bb Royal']/parent::label/span/i").click();
		Thread.sleep(5000);


		//6) Go to Ponni Boiled Rice - Super Premium and select 5kg bag from Dropdown
		List<WebElement> products = driver.findElements(By.xpath("//div[@qa='product_name']/a"));
		for (int i = 1; i <=products.size(); i++) {
			String text = driver.findElementByXPath("(//div[@qa='product_name']/a)["+i+"]").getText();
			if (text.equals("Ponni Boiled Rice - Super Premium")) {
				WebElement ele = driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]");
				ele.click();
				action.moveToElement(ele).perform();
				driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]/following::span[contains(text(),'5')]").click();
				
				//7) print the price of Rice
				String riceprice = driver.findElementByXPath("(//span[@class='discnt-price'])["+i+"]").getText();
				System.out.println("Price is "+riceprice);
				
				//8) Click Add button
				driver.findElementByXPath("(//button[@qa='add'])["+i+"]").click();
			}
		}	
	
		//9) Verify the success message displayed 
		String message = driver.findElementByXPath("//div[@class='toast-title']").getText();
		if (message.contains("Ponni Boiled Rice - Super Premium 5 kg")) {
			System.out.println("Selected item added to the bag correctly");
		} else {
			System.out.println("Selected item not added to the bag");
		}

		//Closing message
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		//10) Type Dal in Search field and enter
		driver.findElementByXPath("//input[@qa='searchBar']").sendKeys("Dal",Keys.ENTER);
		Thread.sleep(2000);

		//12) Go to Toor/Arhar Dal and select 2kg & set Qty 2 
		List<WebElement> products1 = driver.findElements(By.xpath("//div[@qa='product_name']/a"));
		for (int i = 1; i <=products1.size(); i++) {
			String text = driver.findElementByXPath("(//div[@qa='product_name']/a)["+i+"]").getText();
			if (text.equals("Toor/Arhar Dal/Thuvaram Paruppu")) {
				WebElement ele = driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]");
				ele.click();
				action.moveToElement(ele).perform();
				driver.findElementByXPath("((//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]/following::span[@ng-bind='allProducts.w'][contains(text(),'2 kg')])["+i+"]").click();
				WebElement qty = driver.findElementByXPath("(//input[@ng-model='vm.startQuantity'])["+i+"]");
				qty.click();
				qty.clear();
				qty.sendKeys("2");

				//13) Print the price of Dal
				String dalprice = driver.findElementByXPath("(//span[@class='discnt-price'])["+i+"]//span").getText();
				
				//14) Click Add button
				driver.findElementByXPath("(//button[@qa='add'])["+i+"]").click();
			}
		}	
		
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		
		//15) Mouse hover on My Basket 
		WebElement content = driver.findElementByXPath("//span[@class='basket-content']");
		content.click();
		action.moveToElement(content).perform();


		//16) Validate the Sub Total displayed for the selected items
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']")));
		String subtotal = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
		double subtotalno = Double.parseDouble(subtotal);	

		//18) Validate the Sub Total for the current items
		List<WebElement> no = driver.findElementsByXPath("//input[@ng-model='cartItem.quantity']");	
		double actualvalue = cal(driver,no);
		if (subtotalno == actualvalue) {
			System.out.println("Subtotal is correct");
		} else {
			System.out.println("Subtotal is not correct");
		}

		//17) Reduce the Quantity of Dal as 1 	
		Dalremoval(driver,no);
		
		//19) Close the Browser
		driver.quit();

	}

	private static void Dalremoval(ChromeDriver driver, List<WebElement> no) throws InterruptedException {
		double actualtotal = 0;
		double subtotalno = 0;
		for (int i = 1; i <=no.size(); i++) {
			String prodName = driver.findElementByXPath("(//a[@qa='prodNameMB'])["+i+"]").getText();
			if (prodName.contains("Dal")) {
				driver.findElementByXPath("(//button[@qa='decQtyMB'])["+i+"]").click();
				String subtotal= driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
				subtotalno = Double.parseDouble(subtotal);
			}
				double qtyval = Double.parseDouble(driver.findElementByXPath("(//div[@qa=\"pcsMB\"])["+i+"]").getText().substring(0,1));
				String priceval = driver.findElementByXPath("(//span[@qa='priceMB'])["+i+"]").getText();
				double pricevalno = Double.parseDouble(priceval);
				
				actualtotal = actualtotal+(qtyval*pricevalno);
		}
		if (subtotalno == actualtotal) {
			System.out.println("Subtotal is correct after dal removal");
		} else {
			System.out.println("Subtotal is not correct after dal removal");
		}
		
	}

	private static double cal(ChromeDriver driver, List<WebElement> no) {
		double actualtotal = 0;
		for (int i = 1; i <=no.size(); i++) {
			String prodName = driver.findElementByXPath("(//a[@qa='prodNameMB'])["+i+"]").getText();
			double qtyval = Double.parseDouble(driver.findElementByXPath("(//div[@qa=\"pcsMB\"])["+i+"]").getText().substring(0,1));
			String priceval = driver.findElementByXPath("(//span[@qa='priceMB'])["+i+"]").getText();
			double pricevalno = Double.parseDouble(priceval);
			actualtotal = actualtotal+(qtyval*pricevalno);
		}
		return actualtotal;
	}
}
