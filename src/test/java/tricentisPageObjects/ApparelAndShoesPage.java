package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class ApparelAndShoesPage extends BasePage{
	
	public void AddOnGenuineLeatherToCart() {
		clickElement(By.cssSelector("div:nth-of-type(7) > .product-item input[value='Add to cart']"));
	}
	public void ClickBlueGreenSneaker() {
		clickElement(By.cssSelector("div:nth-of-type(2) > .product-item  h2 > a"));
	}
	public void ClickGreenBlueSneaker() {
		clickElement(By.cssSelector("div:nth-of-type(8) > .product-item  h2 > a"));
	}	
}
