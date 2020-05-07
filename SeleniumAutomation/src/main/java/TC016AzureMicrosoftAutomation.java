import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC016AzureMicrosoftAutomation {

	public static void main(String[] args) throws InterruptedException {

		//1) Go to https://azure.microsoft.com/en-in/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://azure.microsoft.com/en-in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 30);

		//2) Click on Pricing
		driver.findElementById("navigation-pricing").click();
		
		//3) Click on Pricing Calculator
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()[normalize-space()='Pricing calculator']]")));
		driver.findElementByXPath("//a[text()[normalize-space()='Pricing calculator']]").click();
		
		//4) Click on Containers
		driver.findElementByXPath("//button[text()='Containers']").click();
		
		//5) Select Container Instances
		driver.findElementByXPath("(//span[text()='Container Instances'])[3]").click();
		
		//6) Click on Container Instance Added View
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[text()='View']")));
		driver.findElementByXPath("//a[text()='View']").click();
		Thread.sleep(5000);
		
		//span[text()='Delete']/parent::button/span
		
		//7) Select Region as "South India"
		WebElement ele = driver.findElementByXPath("//select[@name=\"region\"]");
		Select select = new Select(ele);
		select.selectByVisibleText("South India");
		
		//8) Set the Duration as 180000 seconds.
		String currentUrl = driver.getCurrentUrl();
		WebElement duration = driver.findElementByXPath("(//input[@class=\"text-input numeric\"])[2]");
		duration.sendKeys(Keys.CONTROL + "a");
		duration.sendKeys(Keys.DELETE);
		duration.sendKeys("180000");
		
		//9) Select the Memory as 4GB
		WebElement mem = driver.findElementByXPath("//select[@name=\"memory\"]");
		Select select1 = new Select(mem);
		select1.selectByVisibleText("4 GB");
		
		//10) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id=\"devtest-toggler\"]").click();
		
		//11) Select Indian Rupee  as currency
		WebElement currency = driver.findElementByXPath("//select[@aria-label=\"Currency\"]");
		Select select2 = new Select(currency);
		select2.selectByValue("INR");
		
		//12) Print the Estimated monthly price
		System.out.println("Price --> "+driver.findElementByXPath("(//div[@class=\"column large-3 text-right total\"]/div/span)[2]/span").getText().replaceAll("₹", ""));
		
		//13) Click on Export to download the estimate as excelfolder
		String path = "C://Users/Admin/Downloads";
//		HashedMap<String, Object> map = new HashedMap<>();
//		map.put("download.default_directory", path);
//		options.setExperimentalOption("prefs", map);
		driver.findElementByXPath("//button[@class=\"calculator-button button-transparent export-button\"]").click();
		
		//14) Verify downloaded file in local folder
		Thread.sleep(15000);
		File dir = new File(path);
		List<String> filelist = Arrays.asList(dir.list());
		for (int i = 0; i < filelist.size(); i++) {
			if (filelist.get(i).equals("ExportedEstimate.xlsx")) {
				System.out.println("Success");
				break;
			} 
		}
		
		//15) Navigate to Example Scenarios and Select CI/CD for Containers
		Thread.sleep(10000);
		WebElement examplesce = driver.findElementByXPath("//a[text()='Example Scenarios']");
		Actions action = new Actions(driver);
		action.moveToElement(examplesce).click().perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='CI/CD for Containers']")));
		driver.findElementByXPath("//span[text()='CI/CD for Containers']").click();
		
		//16) Click Add to Estimate
		action.moveToElement(driver.findElementByXPath("//button[text()='Add to estimate']")).click().perform();
		
		//17) Change the Currency as Indian Rupee
		Thread.sleep(15000);
		WebElement currency1 = driver.findElementByXPath("//select[@aria-label=\"Currency\"]");
		action.moveToElement(currency1).perform();
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//select[@aria-label=\"Currency\"]")));
		Select select3 = new Select(currency1);
		select3.selectByValue("INR");
		
		//18) Enable SHOW DEV/TEST PRICING
		driver.findElementByXPath("//button[@id=\"devtest-toggler\"]").click();
		
		//19) Export the Estimate
		driver.findElementByXPath("//button[@class=\"calculator-button button-transparent export-button\"]").click();
		Thread.sleep(15000);
		File dir1 = new File(path);
		List<String> filelist1 = Arrays.asList(dir1.list());
		for (int i = 0; i < filelist1.size(); i++) {
			if (filelist1.get(i).equals("ExportedEstimate (1).xlsx")) {
				System.out.println("Success");
				break;
			} 
		}
	}

}
