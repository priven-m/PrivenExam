package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class HomePage extends BasePage {

	// Method: Navigate home
	public void goHome() {
		driver.get("http://demowebshop.tricentis.com/");
		waitForUrl(30, "tricentis");
	}
	
	// Click Electronics option from top menu
	public void HoverOverElectronicsFromTopMenu() {
		clickElement(By.cssSelector(".top-menu > li:nth-of-type(3) > a"));
	}
	
	//Click on  notebooks from the categories section
	public void ClickOnNotebooksFromCategories() {
		clickElement(By.cssSelector(".block.block-category-navigation > .listbox > .list > li:nth-of-type(2) > a"));
	}
	
	// Select notebooks option when the list exapnds
	public void SelectNoteBooksFromList() {
		clickElement(By.xpath("/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
	}

	// After adding a product to cart, get the text of the element which confirms this.
	public String GetTextAfterAddToCart() {
		String actualText = getElementText(By.xpath("//div[@id='bar-notification']/p[@class='content']"));
		return actualText;
	}
	
	// Go to cart when product is added.
	public void GoToCart() {
		clickElement(By.cssSelector("li#topcartlink  .cart-label"));
	}
	
	// Click on Apparel & Shoes from the top menu
	public void clickApparelAndShoes() {
		clickElement(By.linkText("APPAREL & SHOES"));
	}
	
	// From home screen, click on Simple Computer option
	public void clickSimpleComputer() {
		clickElement(By.linkText("Simple Computer"));
	}
	
	//GetCartQty text to check if cart is >= 1
	public int GetTextOfCartQty() {
		String actualText = getElementText(By.xpath("//li[@id='topcartlink']//span[@class='cart-qty']"));
		String strippedText = actualText.replaceAll("[^0-9]", "");
		int cartQtyInt = Integer.parseInt(strippedText);
		return cartQtyInt;
	}
}
