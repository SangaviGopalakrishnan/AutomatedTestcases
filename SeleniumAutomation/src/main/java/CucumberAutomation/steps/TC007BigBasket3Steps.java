package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TC007BigBasket3Steps{

	public static ChromeDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<Integer> sortedlist;
	List<Integer> list;
	Actions action;

	@Given("Go to link https:\\/\\/www.bigbasket.com")
	public void go_to_link_https_www_bigbasket_com() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions coptions = new ChromeOptions();
		coptions.addArguments("--disable-notifications");
		driver = new ChromeDriver(coptions);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
	}


	@Given("mouse over on to Shop by Category")
	public void mouse_over_on_to_Shop_by_Category() {
		action = new Actions(driver);
		WebElement shop = driver.findElementByXPath("//a[text()=' Shop by Category ']");
		action.moveToElement(shop).perform();
	}

	@Given("Go to Beverages and Fruit juices & Drinks")
	public void go_to_Beverages_and_Fruit_juices_Drinks() {
		action.moveToElement(driver.findElementByXPath("(//a[text()='Beverages'])[2]")).build().perform();
		action.click(driver.findElementByXPath("(//a[text()='Fruit Juices & Drinks'])[2]")).build().perform();
	}

	@Given("Click on JUICES")
	public void click_on_JUICES() {
		driver.findElementByXPath("//span[text()='Juices']").click();
	}

	@Given("click Tropicana and Real under Brand and Check count of the products from each Brands and total count")
	public void click_Tropicana_and_Real_under_Brand() throws InterruptedException {
		Thread.sleep(5000);
		WebElement search = driver.findElementByXPath("//input[@ng-model=\"vm.brandSearch\"]");
		search.sendKeys("real");
		action.click(driver.findElementByXPath("(//span[text()='Real'])[1]/parent::label/span/i")).build().perform();
		Thread.sleep(5000);
		int realsize = driver.findElementsByXPath("//h6[text()='Real']").size();
		int actualtotalcount=0;
		int realtotalsize = driver.findElementsByXPath("//div[@qa=\"product_name\"]").size();
		actualtotalcount =actualtotalcount+realtotalsize;
		action.click(driver.findElementByXPath("(//span[text()='Real'])[1]/parent::label/span/i")).build().perform();

		Thread.sleep(10000);
		WebElement search1 = driver.findElementByXPath("//input[@ng-model=\"vm.brandSearch\"]");
		action.click(search1).build().perform();
		search1.clear();
		search1.sendKeys("tropicana");
		action.click(driver.findElementByXPath("(//span[text()='Tropicana'])[1]/parent::label/span/i")).build().perform();
		Thread.sleep(5000);
		int tropicanasize = driver.findElementsByXPath("//h6[text()='Tropicana']").size();
		int tropicanatotalsize = driver.findElementsByXPath("//div[@qa=\"product_name\"]").size();
		actualtotalcount =actualtotalcount+tropicanatotalsize;
		int countobtained = realsize+tropicanasize;
		if (actualtotalcount == countobtained) {
			System.out.println("Count matches");
		} else {
			System.out.println("Count not matched");
		}
		action.click(driver.findElementByXPath("(//span[text()='Tropicana'])[1]/parent::label/span/i")).build().perform();

		//7) Check whether the products is availabe with Add button
		int productssize = driver.findElementsByXPath("//div[@qa=\"product_name\"]").size();
		int addbuttonsize = driver.findElementsByXPath("//button[@qa=\"add\"]").size();
		if (productssize == addbuttonsize) {
			System.out.println("Products available with add button --> "+addbuttonsize);
		} else {
			System.out.println("Products not available with add button --> "+(productssize-addbuttonsize));
		}	   
	}

	@Given("Check whether the products is availabe with Add button.")
	public void check_whether_the_products_is_availabe_with_Add_button() {
		int productssize = driver.findElementsByXPath("//div[@qa=\"product_name\"]").size();
		int addbuttonsize = driver.findElementsByXPath("//button[@qa=\"add\"]").size();
		if (productssize == addbuttonsize) {
			System.out.println("Products available with add button --> "+addbuttonsize);
		} else {
			System.out.println("Products not available with add button --> "+(productssize-addbuttonsize));
		}  
	}

	@Given("Add the First listed available product")
	public void add_the_First_listed_available_product() throws InterruptedException {
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("(//button[@qa='add'])[1]")).build().perform();
		action.click(driver.findElementByXPath("(//button[@qa='add'])[1]")).build().perform();;
	}

	@Given("click on Address")
	public void click_on_Address() {
		driver.findElementByXPath("//a[text()='Change Location']").click();
	}

	@Given("Select Chennai as City, Alandur as Area and click Continue")
	public void select_Chennai_as_City_Alandur_as_Area_and_click_Continue() {
		driver.findElementByXPath("//div[@placeholder=\"Select your city\"]").click();
		driver.findElementByXPath("//span[@qa='citySelectList'][contains(text(),'Chennai')]").click();
		driver.findElementById("areaselect").sendKeys("Alandur,St.Thomas Mount,Kalaigar Nagar-St Thomas M-600016,Chennai",Keys.TAB);
		driver.findElementByXPath("//button[text()='Continue']").click();
	}

	@Given("Mouse over on My Basket print the product name. count and price.")
	public void mouse_over_on_My_Basket_print_the_product_name_count_and_price() {
		WebElement content = driver.findElementByXPath("//span[@class='basket-content']");
		content.click();
		action.moveToElement(content).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']")));
		String subtotal = driver.findElementByXPath("//span[@ng-bind='vm.cart.cart_total']").getText();
		System.out.println("Total --> "+subtotal);
		int count = driver.findElements(By.id("p_10000246")).size();
		System.out.println("Count --> "+count);
		String productname = driver.findElementByXPath("//a[@qa=\"prodNameMB\"]").getText();
		System.out.println("ProductName --> "+productname);
	}

	@When("Click View Basket & Checkout")
	public void click_View_Basket_Checkout() {
		driver.findElementByXPath("//button[text()='View Basket & Checkout']").click();
	}

	@Then("Click the close button")
	public void click_the_close_button() {
		driver.findElementByXPath("//button[@class=\"close\"]").click();
		driver.quit();
	}
}
