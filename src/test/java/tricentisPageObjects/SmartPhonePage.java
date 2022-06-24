package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class SmartPhonePage extends BasePage{

	public String getTextOfSmartPhone() {
		String actualText = getElementText(By.xpath("//form[@id='product-details-form']/div/div[@class='product-essential']//h1"));
		return actualText;
	}
	public void ClickAddToCart() {
		clickElement(By.cssSelector(".add-to-cart-panel [value='Add to cart']"));
	}
	
}
