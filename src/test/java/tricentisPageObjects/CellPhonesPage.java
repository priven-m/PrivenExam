package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class CellPhonesPage extends BasePage{

	public void ClickOnSmartPhone() {
		clickElement(By.cssSelector("div:nth-of-type(1) > .product-item  h2 > a"));
	}
	
	public String VerifyCellPhonePage() {
		String actualText = getElementText(By.cssSelector("h1"));
		return actualText;
	}
}
