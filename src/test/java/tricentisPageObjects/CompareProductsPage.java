package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class CompareProductsPage extends BasePage{
		
	public String GetTextOfSBlueGreenSneaker() {
		String actualText = getElementText(By.cssSelector(".a-center > a"));
		return actualText;
	}
	public String GetTextOfSGreenBlueSneaker() {
		String actualText = getElementText(By.cssSelector("td:nth-of-type(2) > a"));
		return actualText;
	}
	public String GetTextOfBlueGreenSneakersPrice() {
		String actualText = getElementText(By.cssSelector(".product-price .a-center:nth-of-type(2)"));
		return actualText;
	}
	public String GetTextOfGreenBlueSneakersPrice() {
		String actualText = getElementText(By.cssSelector(".product-price > td:nth-of-type(3)"));
		return actualText;
	}
}
