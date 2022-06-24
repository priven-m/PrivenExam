package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class NoteBooksPage extends BasePage{
	
	public void ClickAddToCart() {
		clickElement(By.cssSelector("input[value='Add to cart']"));
	}

}
