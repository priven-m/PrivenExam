package frameWorkClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	// Declare Webdriver to be used
	public static WebDriver driver;

	// BasePage constructor
	public BasePage() {
		if (driver == null) {

			// Get Parameter values
			String browser = getDataConfigPropeties("browser");
			String URL = getDataConfigPropeties("URL");

			// use browser based on the config setup
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				driver.get(URL);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
	}

	// Reading from our config file
	public String getDataConfigPropeties(String propertyName) {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream("config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

	// Wait for elements
	public void waitForElement(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(pLocator));
	}

	// Get current url
	public String getUrl() {
		String getCurrentURL = driver.getCurrentUrl();
		return getCurrentURL;

	}

	// Wait for click
	public void waitforClick(int elementWait, By pLocator) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.elementToBeClickable(pLocator));
	}

	// Wait for Url
	public void waitForUrl(int elementWait, String urlContainer) {
		WebDriverWait wait = new WebDriverWait(driver, elementWait);
		wait.until(ExpectedConditions.urlContains(urlContainer));
	}

	// Get element text
	public String getElementText(By pLocator) {
		String elementText = getElement(pLocator).getText();
		return elementText;
	}

	// Click element
	public void clickElement(By pLocator) {
		waitforClick(30, pLocator);
		getElement(pLocator).click();

	}

	// Check if element exists
	public boolean elementExists(By pLocator) {
		boolean display = getElement(pLocator).isDisplayed();
		return display;
	}

	// Get element
	public WebElement getElement(By pLocator) {
		waitforClick(30, pLocator);
		return driver.findElement(pLocator);
	}

	// Enter text
	public void enterText(By pLocator, String QtyText) {
		waitforClick(30, pLocator);
		driver.findElement(pLocator).sendKeys(QtyText);
	}

	// Switch to window
	public void SwitchToNewTab() {
		// Selenium will check how many windows are currently open,
		// It will store the ID for both parent and child window
		Set<String> handles = driver.getWindowHandles();

		Iterator<String> it = handles.iterator(); // using the it object you can access the ID
		String parentWindow = it.next();
		String childWindow = it.next();
		driver.switchTo().window(childWindow); // Switch to new window by passing the ID of the child window
	}

	public void SwitchToParent() {
		// Selenium will check how many windows are currently open,
		// It will store the ID for both parent and child window
		Set<String> handles = driver.getWindowHandles();

		Iterator<String> it = handles.iterator(); // using the it object you can access the ID
		String parentWindow = it.next();
		String childWindow = it.next();
		driver.switchTo().window(parentWindow); // Switch to new window by passing the ID of the child window
	}

	public void closeChildBrowserTab() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowID = it.next();
		String childWindowID = it.next();
		driver.switchTo().window(childWindowID);
		driver.close();
		driver.switchTo().window(parentWindowID);
	}

	// Clear text
	public void clearText(By pLocator) {
		waitforClick(30, pLocator);
		driver.findElement(pLocator).clear();
	}

	// Select from drop-down
	public void selectDropDown(By pLocator, String pValue) {
		waitForElement(20, pLocator);
		Select sDropDown = new Select(getElement(pLocator));
		sDropDown.selectByVisibleText(pValue);
	}

	public void TearDown() {
		driver.quit();
	}

	public void CloseBrowser() {
		driver.close();
	}

}