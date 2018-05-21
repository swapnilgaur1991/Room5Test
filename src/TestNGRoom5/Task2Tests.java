package TestNGRoom5;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task2Tests {
	
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeMethod()
	public void startDriver() {
		
		// Set system path 
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Naina\\Downloads\\geckodriver-v0.20.1-win64\\geckodriver.exe");
		
		// open default url
		driver = new FirefoxDriver();
	    driver.get("http://room5.trivago.com");
	
	    wait = new WebDriverWait(driver, 10);
    }
	
	@Test(priority=0, description="This test case will Search for any location on Room5 by using the search bar")
	public void searchLocation() {
		try {
			driver.findElement(By.xpath("//span[@class='room5-icons-search']")).click();
			
			WebElement search = driver.findElement(By.xpath("//input[@id='ajax-search-input']"));
			search.clear();
			search.sendKeys("USA");
			
			By res = By.xpath("//div[@class='h3 montserrat-regular'][contains(text(),'USA')]");
			WebElement resultContainer = wait.until(ExpectedConditions.presenceOfElementLocated(res));

			if(resultContainer.isDisplayed() == true) {
				resultContainer.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=1, description="This test case will Fill in the contact form and send it (accessible through the footer)")
	public void fillContactForm() {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
			WebElement footer = driver.findElement(By.id("footer"));

			List<WebElement> footerLinks = footer.findElements(By.tagName("li"));
			
			// passing index 1 for Contact link
			WebElement anchor = footerLinks.get(1).findElement(By.tagName("a"));
			
			JavascriptExecutor jse2 = (JavascriptExecutor)driver;
			jse2.executeScript("arguments[0].scrollIntoView()", anchor);
			anchor.click();
			
			// Enter data in Contact Form
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));
			driver.findElement(By.id("message")).sendKeys("This is sample test message");
			driver.findElement(By.id("full_name")).sendKeys("Test Full Name");
			driver.findElement(By.id("email")).sendKeys("Test@email.com");
			driver.findElement(By.id("contactform-submit")).click();
  
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='success-msg mb-16']")));
			Boolean isVisible = driver.findElement(By.xpath("//div[@class='success-msg mb-16']")).isDisplayed();
			if(isVisible == true) {
				//driver.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=2, description="This test case will Subscribe to the Newsletter")
	public void subscribeToNewsletter() {
		try {
			By subscriptionPath = By.xpath("//html//div[11]");
		    
		    WebElement subscriptionSection = driver.findElement(subscriptionPath);
		    
		    // scroll to the subscription section view
		    JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		    jse2.executeScript("arguments[0].scrollIntoView()", subscriptionSection);
		    
		    // select the check box for subscription
		    subscriptionSection.findElement(By.id("etn_conf_checkbox_de")).click();
		    
		    // enter test email id for subscription
		    subscriptionSection.findElement(By.id("etn_email")).sendKeys("test@email.com");
		    
		    // submit button for subscription
		    subscriptionSection.findElement(By.xpath("//button[@type='submit']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=3, description="This test case will Navigate to a destination through the menu on the top left")
	public void navigateToDestination() {
		try {
			By menuItem = By.xpath("//div[@class='nav-icon']");
		    wait.until(ExpectedConditions.presenceOfElementLocated(menuItem));
		    
		    driver.findElement(menuItem).click();
		    
		    WebElement destinationsList = driver.findElement(By.xpath("//html//ul[@class='single-sub-menu-container']/ul[1]"));
		    List<WebElement> destinations = destinationsList.findElements(By.tagName("li"));
		    destinations.get(1).findElement(By.tagName("a")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=4, description="This test case will Select a country from the drop down menu options")
	public void selectCountry() {
		try {
			By countryDropdown = By.id("select-country");
		    WebElement countrySelector = driver.findElement(countryDropdown);
		    
		    // open the drop down options
		    JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		    jse2.executeScript("arguments[0].scrollIntoView()", countrySelector);
		    countrySelector.click();
		    
		    // select Deutschland country from the drop down
		    countrySelector.findElements(By.tagName("option")).get(2).click();
		    
		    //validate the country which is selected
		    Select country = new Select(driver.findElement(By.id("select-country")));
		   
		    if(country.getFirstSelectedOption().getText().equalsIgnoreCase("Deutschland")) {
		    	//driver.close();
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority=5, description="This test case will navigate the user to All Inspirations page")
	public void allInspirations() {
		try {
			By inspirationId = By.id("inspiration");
		    WebElement inspirationSection = driver.findElement(inspirationId);
		    
		    // open the drop down options
		    JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		    jse2.executeScript("arguments[0].scrollIntoView()", inspirationSection);
		    
		    // find "See All Inspirations" button inside inspirationSection
		    inspirationSection.findElements(By.id("all_themes_btn")).get(0).click();
		    
		    // validate if the page is redirected to desired URL
		    String currentUrl = "http://room5.trivago.com/theme/all-themes/";
		    if(driver.getCurrentUrl().equalsIgnoreCase(currentUrl)) {
		    	//driver.close();
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void stopDriver() {
		driver.quit();
    }
}
