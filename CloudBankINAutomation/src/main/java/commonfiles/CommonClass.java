package commonfiles;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class CommonClass
{
	public static ChromeDriver driver;
	
	@BeforeMethod
	public void commonclass() throws InterruptedException 
		{
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, 30);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//		1.	Login in-to - https://salesdemo.habiletechnologies.com/  (qatest@habile.in/Qatest123$)
			driver.get("https://salesdemo.habiletechnologies.com/");
			driver.manage().window().maximize();
			wait.until(ExpectedConditions.visibilityOf(driver.findElementById("uid")));
			driver.findElementById("uid").sendKeys("qatest@habile.in");
			driver.findElementById("pwd").sendKeys("Qatest123$");		
			driver.findElementByXPath("(//button[text()='Sign In'])[1]").click();
		}
		
		@AfterMethod
		public void closingdriver()
			{
				driver.close();
			}
		
}