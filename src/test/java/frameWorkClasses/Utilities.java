package frameWorkClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;

public class Utilities extends BasePage {

	// Method to create a screenshot
	public void TakeSnapShot(String fileWithPath) throws IOException {

		// Convert webdriver object to TakeScreenShot
		TakesScreenshot scrShot = ((TakesScreenshot)driver);

		// Call getScreenShot as a method to create image file
		File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

		// move image to the new destination
		File destFile = new File("target//" + "surefire-reports-" + getAppConfigPropeties("build.timestamp") + "//ScreenShots//" + fileWithPath);

		// copy file
		FileUtils.copyFile(srcFile, destFile);

		// update the pdf report with the screenShot
		Reporter.log("<a href='"+ destFile.getAbsolutePath() + "'> <img src='"+ destFile.getAbsolutePath()
				+ "' height='200' width='200'/> </a>");

	}

	// method to return the current time
	public String returnTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");
		return dtf.format(now);
	}

	// Get the property from the app.properties
	public String getAppConfigPropeties(String propertyName) {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = new FileInputStream(".\\target\\app.properties");
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

}
