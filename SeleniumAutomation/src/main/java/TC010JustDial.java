import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class TC010JustDial {

	public static void main(String[] args) throws InterruptedException, IOException {

		//1) https://www.justdial.com/
		System.setProperty("webdriver.chrome.silentOutput", "true");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		options.addArguments("--disable-notifications");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		options.merge(cap);
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.justdial.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		//2) Set the Location as Chennai
		driver.findElementById("city").click();
		driver.findElementById("city").sendKeys("Chennai",Keys.TAB);
		Thread.sleep(2000);

		//3) Click Auto Care in the left menu
		driver.findElementByXPath("//span[text()='Auto care']").click();
		Thread.sleep(2000);

		//4) Click Car Repair
		driver.findElementByXPath("//span[@title='Car Repair']").click();
		Thread.sleep(2000);

		//5) Click Car Brand as Hyundai
		driver.findElementByXPath("//span[text()='Hyundai']").click();
		Thread.sleep(2000);

		//6) Click Make as Hyundai Xcent
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.findElementByXPath("//span[text()='Hyundai Xcent']").click();
		Thread.sleep(2000);


		//7) Click on Location and Enter Porur
		Thread.sleep(5000);

		try {
			driver.findElementByXPath("(//span[text()='X'])[25]").click();
		} catch (Exception e) {
			System.out.println("Handled");
		}
		driver.findElementByXPath("//a[text()='Location']").click();
		driver.findElementById("sortbydist").click();

		//8) Select Porur from the dropdown list
		driver.findElementById("sortbydist").sendKeys("Porur",Keys.ENTER);
		driver.findElementByXPath("//button[text()='Go']").click();

		//9) Select Distance starting from 1 km
		WebElement dist = driver.findElementByXPath("//span[text()='Distance']");
		Select sel = new Select(dist);
		sel.selectByVisibleText("1 km");

		//10) Identify all the Service Center having Ratings >=4.5 and Votes >=50
		List<String> centers = new ArrayList<String>();
		List<String> phno = new ArrayList<String>();
		List<WebElement> rating = driver.findElementsByXPath("//span[@class='green-box']");
		for (int i = 1; i <=rating.size(); i++) {
			double actualrating = Double.parseDouble(driver.findElementByXPath("(//span[@class=\"green-box\"])["+i+"]").getText());
			if (actualrating >= 4.5) {
				int votes = Integer.parseInt(driver.findElementByXPath("(//p[@class=\"newrtings \"]/a/span[3])["+i+"]").getText().replaceAll("\\D", ""));
				if(votes>=50) {

					//11) Save all the Service Center name and Phone number matching the above condition in excel 

					String centername = driver.findElementByXPath("(//span[@class=\"lng_cont_name\"])["+i+"]").getText();
					centers.add(centername);
					List<WebElement> no = driver.findElementsByXPath("(//p[@class=\"contact-info \"]/span/a/b)["+i+"]/span");
					String phoneno = "";
					Map<String,String> map = new LinkedHashMap<String,String>();
					map.put("dc", "+");
					map.put("fe", "(");
					map.put("hg", ")");
					map.put("ba", "-");
					map.put("acb", "0");
					map.put("yz", "1");
					map.put("wx", "2");
					map.put("vu", "3");
					map.put("ts", "4");
					map.put("rq", "5");
					map.put("po", "6");
					map.put("nm", "7");
					map.put("lk", "8");
					map.put("ji", "9");
					for (int j = 1; j <=16; j++) {
						String n1 = driver.findElementByXPath("((//p[@class=\"contact-info \"]/span/a/b)["+i+"]/span)["+j+"]").getAttribute("class");
						String[] splitval = n1.split("-");
						String val = splitval[1];
						Set<Entry<String, String>> entrySet = map.entrySet();
						for (Entry<String, String> entry : entrySet) {
							if (entry.getKey().equalsIgnoreCase(val)) {
								phoneno = phoneno+(entry.getValue());
							}
						}
					}
					phno.add(phoneno.substring(6, 16));
				}
				//						Base64.Decoder decoder = Base64.getDecoder();
				//						String str = new String(decoder.decode(val));
				//						System.out.println(str);
			}
		}
		File file = new File("M://MyProject/SeleniumAutomation/excel/Output.xlsx");
		XSSFWorkbook wbook = new XSSFWorkbook();
		XSSFSheet sheet =wbook.createSheet("Output");
		for (int k = 0; k < centers.size(); k++) {
			sheet.createRow(k).createCell(0).setCellValue(centers.get(k));
			sheet.getRow(k).createCell(1).setCellValue(phno.get(k));
		}
		FileOutputStream fout = new FileOutputStream(file);
		wbook.write(fout);
		wbook.close();

		//12) Close the browser
		driver.quit();
	}
}