package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class GiftCardsPage extends BasePage {
	
	public String getGiftCardsSections() {
		String actualText = getElementText(By.tagName("h1"));
		return actualText;
	}
	
	public void click5$GiftCard() {
		clickElement(By.cssSelector("div:nth-of-type(1) > .product-item  h2 > a"));
		
	}
	
	public void Add5$GiftCardToCart() {
		clickElement(By.cssSelector(".add-to-cart-panel [value='Add to cart']"));
	}

}
