import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TC007HondaAutomation {

	public static void main(String[] args) throws InterruptedException {
		
		//1) Go to https://www.honda2wheelersindia.com/
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.honda2wheelersindia.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		try {
			driver.findElementByXPath("//button[@data-dismiss='modal']").click();
		} catch (Exception e) {
		    System.out.println("Alert not thrown");
		}
		
		//2) Click on scooters and click dio
		driver.findElementByXPath("//a[@rel='menu_Scooter']").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/dioBS6-icon.png']").click();
		Thread.sleep(5000);
		
		//3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(5000);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).click().build().perform();
		
		//4) Get Displacement value
		double diodisplacementval = Double.parseDouble(driver.findElementByXPath("//span[text()='Displacement']//following-sibling::span")
				.getText().replaceAll("[^0-9.]", ""));
		System.out.println("Displacement value is "+diodisplacementval);
		
		//5) Go to Scooters and click Activa 125
		driver.findElementByXPath("//a[@rel='menu_Scooter']").click();
		driver.findElementByXPath("//img[@src='/assets/images/thumb/activa-125new-icon.png']").click();
		Thread.sleep(5000);
		
		//6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		Thread.sleep(5000);
		action.moveToElement(driver.findElementByXPath("//a[text()='ENGINE']")).click().build().perform();
		
		//7) Get Displacement value
		double activadisplacementval = Double.parseDouble(driver.findElementByXPath("//span[text()='Displacement']//following-sibling::span")
				.getText().replaceAll("[^0-9.]", ""));
		System.out.println("Displacement value is "+activadisplacementval);
		
		//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if (diodisplacementval > activadisplacementval) {
			System.out.println("Dio displacement value is high");
		}else
		{
			System.out.println("Activa displacement value is high");
		}
		//9) Click FAQ from Menu
		driver.findElementByXPath("//a[@href='/FAQ']").click(); 
		Thread.sleep(5000);
		
		// 10) Click Activa 125 BS-VI under Browse By Product
		driver.findElementByXPath("//h6[text()='Browse by Product']/following::a[contains(text(),'Activa 125')]").click();
		Thread.sleep(5000);
		
		//11) Click  Vehicle Price 
		driver.findElementByXPath("//a[text()=' Vehicle Price']").click();
		Thread.sleep(5000);
		
		//12) Make sure Activa 125 BS-VI selected and click submit
		String text = driver.findElementByXPath("//select[@id='ModelID6']/option[@selected]").getText();
		if (text.contains("Activa 125")) {
			driver.findElementByXPath("//button[@id='submit6']").click();
			
			//13) click the price link
			driver.findElementByXPath("//td[text()='Activa 125 BS-VI']/following::td/a").click();
		}
		
		//14)  Go to the new Window and select the state as Tamil Nadu and  city as Chennai
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> list = new ArrayList<String>(windowHandles);
		driver.switchTo().window(list.get(1));
		
		WebElement state = driver.findElementById("StateID");
		Select st = new Select(state);
		st.selectByVisibleText("Tamil Nadu");
		
		WebElement city = driver.findElementById("CityID");
		Select ct = new Select(city);
		ct.selectByVisibleText("Chennai");
		
		//15) Click Search
		driver.findElementByXPath("//button[text()='Search']").click();
		
		//16) Print all the 3 models and their prices
		Map<String, String> map = new LinkedHashMap<String, String>();
		int count = 1;
		WebElement table = driver.findElement(By.xpath("//table[@class=\"datashow\"]"));
		List<WebElement> row = table.findElements(By.tagName("tr"));
		for (int i = 2; i <row.size(); i++) {
			List<WebElement> column = row.get(i).findElements(By.tagName("td"));
				if (count==1) {
					String key = column.get(1).getText();
					String Value = column.get(2).getText();
					map.put(key, Value);
					count++;
				}else if(count<4){
					String key = column.get(0).getText();
					String Value = column.get(1).getText();
					map.put(key, Value);
					count++;
				}
		}
		
		Set<Entry<String, String>> entrySet = map.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println("Model --> "+entry.getKey()+" Price --> "+entry.getValue());
		}
		
		//17) Close the Browser
//		driver.quit();
	}

}
