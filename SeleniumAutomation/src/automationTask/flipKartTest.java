package automationTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class flipKartTest {
	WebDriver driver;

	/**
	 * @author Zeel parmar
	 * @description This function will execute before each Test tag in testng.xml
	 * @param browser
	 * @throws Exception
	 */
	@BeforeTest
	@Parameters("browser")
	public void setup(String browser) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
		if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
			System.setProperty("webdriver.gecko.driver", "D:\\Automation\\geckodriver-v0.29.1-win64.\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
			//set path to chromedriver.exe
		     System.setProperty("webdriver.chrome.driver", "D:\\Automation\\chromedriver_win32\\chromedriver.exe");
			//create chrome instance
			driver = new ChromeDriver();
		}
		else{
			//If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
  @Test
  public void function() {
	 
	    driver.manage().window().maximize();
	    
	    // 1.Open Google.com (Add the case for Cross Browsers- firefox, and chrome)
		driver.get("https://www.google.com/");
		
		// 2.Search for “Flipkart” 

		driver.findElement(By.xpath("//*[@title=\"Search\"]")).sendKeys("Flipkart");
		WebDriverWait wait = new WebDriverWait(driver, 15);

 		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"wM6W7d\"]//span")));
 		
 		// 3.Print all the search options displayed in the console from google’s search field, while we write “Flipkart” in that. 

		java.util.List<WebElement> data = driver.findElements(By.xpath("//*[@class=\"wM6W7d\"]//span"));
		for (WebElement str : data) {
		System.out.println(str.getText());
		} 

        // 4.Press enter to give out the search results and open the link for the “flipkart.com” website available. 

		driver.findElement(By.xpath("//*[@title=\"Search\"]")).sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Flipkart")));		
		driver.findElement(By.partialLinkText("Flipkart")).click();
		
		// 5. Close the login popup on the website (if available) 

		if (driver.findElement(By.xpath("//*[@class=\"_2QfC02\"]")).isDisplayed()) {
		driver.findElement(By.xpath("//*[@class=\"_2QfC02\"]//button")).click();
		}
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,0)"); // Scroll down as per pixel
		
		// 6. Click on “TV & Appliances” dropdown button and Navigate to “AirConditioners > Window AC’s” page 

		Actions action = new Actions(driver);
		WebElement element = driver.findElement(By.xpath("//*[@alt=\"Appliances\"]"));
		action.moveToElement(element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(.,'Televisions')]")));
		driver.findElement(By.xpath("//a[contains(.,'Televisions')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'TVs & Appliances')]")));
		WebElement element2 = driver.findElement(By.xpath("//span[contains(.,'TVs & Appliances')]"));
		element2.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title=\"Window ACs\"]")));
		driver.findElement(By.xpath("//*[@title=\"Window ACs\"]")).click();
		
		// 7. Click on the “Add To Compare” checkbox of the 2nd, 3rd and 6th products displayed.  

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"_1fQZEK\"]//label/div")));
		java.util.List<WebElement> tutorialsList1 = driver.findElements(By.xpath("//*[@class=\"_1fQZEK\"]//label/div"));
		tutorialsList1.get(1).click();
		js.executeScript("window.scrollBy(0,500)"); // Scroll down as per pixel
		tutorialsList1.get(2).click();
		tutorialsList1.get(5).click(); 
		
		// 8. Then Click on the COMPARE button. 

		driver.findElement(By.xpath("//*[@class=\"_3hShhO\"]//span")).click();
		
		// 9. Print Name and price of all three products in the console. 

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]")));
		java.util.List<WebElement> productName = driver.findElements(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]"));
		for (WebElement str1 : productName) {
		System.out.println("product name is : " + str1.getText());
		}
		java.util.List<WebElement> price = driver
		.findElements(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_30jeq3\"]"));
		for (WebElement str2 : price) {
		System.out.println("product price is : " + str2.getText());
		}
		
		// 10. Add all the 3 products into the cart, one by one.

		java.util.List<WebElement> productName1 = driver.findElements(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]"));
		productName1.get(0).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"row\"]")));
		driver.findElement(By.xpath("//*[@class=\"row\"]//button[contains(.,'ADD TO CART')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"VUS-tD eBV0fb\"]//button")));
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"_3hShhO\"]//span")));
		driver.findElement(By.xpath("//*[@class=\"_3hShhO\"]//span")).click();
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]")));
		java.util.List<WebElement> productName2 = driver.findElements(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]"));
		productName2.get(1).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"row\"]//button[contains(.,'ADD TO CART')]")));
		driver.findElement(By.xpath("//*[@class=\"row\"]//button[contains(.,'ADD TO CART')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"VUS-tD eBV0fb\"]//button"))); driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"_3hShhO\"]//span"))); driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]")));			
		java.util.List<WebElement> productName3 = driver.findElements(By.xpath("//*[@class=\"col col-3-12 _1Z-FPJ\"]//*[@class=\"_3L_M2k\"]"));
		productName3.get(2).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"row\"]//button[contains(.,'ADD TO CART')]")));
		
		// 11. Go to the cart and add your area Pincode and check the availability of the product delivery there.

		driver.findElement(By.xpath("//*[@class=\"row\"]//button[contains(.,'ADD TO CART')]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"VUS-tD eBV0fb\"]//button")));
		driver.findElement(By.xpath("//*[@placeholder=\"Enter delivery pincode\"]")).sendKeys("380081");
		driver.findElement(By.xpath("//*[@class=\"UgLoKg\"]")).click();	
		
		// 12. Print the message getting displayed for the availability/delivery of the product in the console.

		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");	// scroll till the end of page	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if(driver.findElement(By.xpath("//*[@class=\"_3fSRat\"]//div[@class=\"_1tBBEs\"]")).isDisplayed()) {
			String availabilityMessage2 = driver.findElement(By.xpath("//*[@class=\"_3fSRat\"]//div[@class=\"_1tBBEs\"]")).getText();
			System.out.println("Availability of stock message is: " + availabilityMessage2);
			}
			else {
				  System.out.println("product is available");
			}
		
		// 13. Click the ‘Deliver to’ input box, available to the top of the page, and add another pin code and check the availability of the product delivery there.

		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");	// scroll till the top of the page	
		driver.findElement(By.xpath("//*[@class=\"_3gc7Vf\"]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@placeholder=\"Enter delivery pincode\"]")).sendKeys("400008");
		driver.findElement(By.xpath("//*[@class=\"UgLoKg\"]")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// 14. Print the message getting displayed for the availability/delivery of the product in the console for the changed Pincode.

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");	// scroll till the end of page	
		if(driver.findElement(By.xpath("//*[@class=\"_3fSRat\"]//div[@class=\"_1tBBEs\"]")).isDisplayed()) {
		String availabilityMessage2 = driver.findElement(By.xpath("//*[@class=\"_3fSRat\"]//div[@class=\"_1tBBEs\"]")).getText();
		System.out.println("Availability of stock message is: " + availabilityMessage2);
		}
		else {
			  System.out.println("product is available");
		}
		
		// 15. Close the browser.

	    driver.close();

	}

  }
