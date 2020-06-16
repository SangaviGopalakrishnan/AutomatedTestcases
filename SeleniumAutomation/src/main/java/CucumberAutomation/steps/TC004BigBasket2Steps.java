package steps;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC004BigBasket2Steps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;

	@Given("User launches the browser for Big Basket")
	public void user_launches_the_browser_for_Big_Basket() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");
		driver = new ChromeDriver(coptions);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Given("User mouse hovers on  Shop by Category")
	public void user_mouse_hovers_on_Shop_by_Category() {
		action = new Actions(driver);
		WebElement shop = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		action.moveToElement(shop).perform();
	}

	@Given("User goes to FOODGRAINS, OIL & MASALA and RICE & RICE PRODUCTS")
	public void user_goes_to_FOODGRAINS_OIL_MASALA_and_RICE_RICE_PRODUCTS() throws InterruptedException {
		action.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Foodgrains, Oil & Masala')])[2]"))).perform();
		action.click(driver.findElementByXPath("(//a[@qa='catL2'][contains(text(),'Rice')])[2]")).build().perform();	
		wait = new WebDriverWait(driver, 10);
		Thread.sleep(5000);
	}

	@Given("User clicks on BOILED & STEAM RICE")
	public void user_clicks_on_BOILED_STEAM_RICE() {
		driver.findElement(By.xpath("//span[text()='Boiled & Steam Rice']")).click();
	}

	@Given("User gets the URL of the page and check it with site navigation link HOME > FOODGRAINS, OIL & MASALA> RICE & RICE PRODUCTS> BOILED & STEAM RICE")
	public void user_gets_the_URL_of_the_page_and_check_it_with_site_navigation_link_HOME_FOODGRAINS_OIL_MASALA_RICE_RICE_PRODUCTS_BOILED_STEAM_RICE() {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("https://www.bigbasket.com/pc/foodgrains-oil-masala/rice-rice-products/boiled-steam-rice/")) {
			System.out.println("URL matches");
		} else {
			System.out.println("URL not matched");
		}	   
	}

	@Given("User chooses the Brand as bb Royal")
	public void user_chooses_the_Brand_as_bb_Royal() throws InterruptedException {
		driver.findElementByXPath("//span[text()='bb Royal']/parent::label/span/i").click();
		Thread.sleep(5000);
	}

	@Given("User goes to Ponni Boiled Rice and select 10kg bag from Dropdown and User clicks Add button")
	public void user_goes_to_Ponni_Boiled_Rice_and_select_kg_bag_from_Dropdown() {
		List<WebElement> products = driver.findElements(By.xpath("//div[@qa='product_name']/a"));
		for (int i = 1; i <=products.size(); i++) {
			String text = driver.findElementByXPath("(//div[@qa='product_name']/a)["+i+"]").getText();
			if (text.equals("Ponni Boiled Rice - Super Premium")) {
				WebElement ele = driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]");
				ele.click();
				action.moveToElement(ele).perform();
				driver.findElementByXPath("(//button[@class='btn btn-default dropdown-toggle form-control'])["+i+"]/following::span[contains(text(),'10')]").click();

				//8) Click Add button
				driver.findElementByXPath("(//button[@qa='add'])["+i+"]").click();
			}
		}	  
	}

	@Given("User goes to search box and type Dal")
	public void user_goes_to_search_box_and_type_Dal() throws InterruptedException {
		driver.findElementByXPath("//input[@qa='searchBar']").sendKeys("Dal",Keys.ENTER);
		Thread.sleep(2000);
	}

	@Given("User adds Toor\\/Arhar Dal 2kg and set Qty2 from the list")
	public void user_adds_Toor_Arhar_Dal_kg_and_set_Qty_from_the_list() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
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
				String dalprice = driver.findElementByXPath("(//span[@class='discnt-price'])["+i+"]//span").getText();
				driver.findElementByXPath("(//button[@qa='add'])["+i+"]").click();
			}
		}		   
	}

	@Given("User clicks Address")
	public void user_clicks_Address() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)");
		driver.findElementByXPath("//button[@class='toast-close-button']").click();
		driver.findElementByXPath("//a[@qa=\"areaDD\"]").click();
	}

	@Given("User selects Chennai as City, Alandur-600016,Chennai as Area  and click Continue")
	public void user_selects_Chennai_as_City_Alandur_Chennai_as_Area_and_click_Continue() {
		driver.findElementByXPath("//div[@placeholder=\"Select your city\"]").click();
		driver.findElementByXPath("//span[@qa='citySelectList'][contains(text(),'Chennai')]").click();
		driver.findElementById("areaselect").sendKeys("Alandur,St.Thomas Mount,Kalaigar Nagar-St Thomas M-600016,Chennai",Keys.TAB);
		driver.findElementByXPath("//button[text()='Continue']").click();
	}

	@Given("User mouse hovers on My Basket take a screen shot")
	public void user_mouse_hovers_on_My_Basket_take_a_screen_shot() throws IOException {
		WebElement content = driver.findElementByXPath("//span[@class='basket-content']");
		content.click();
		action.moveToElement(content).perform();
		File src = driver.findElementByXPath("//ul[@ng-show=\"vm.basketDrop\"]").getScreenshotAs(OutputType.FILE);
		File dest = new File("./snaps/basket.png");
		FileUtils.copyFile(src, dest);
	}

	@When("clicks View Basket and Checkout")
	public void clicks_View_Basket_and_Checkout() {
		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
	}

	@Then("clicks the close button and close the browser")
	public void clicks_the_close_button_and_close_the_browser() {
		driver.findElementByXPath("//button[@class=\"close\"]").click();
		driver.quit();
	}
}
