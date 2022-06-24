package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class SimpleComputerPage extends BasePage {

	public void ClearQtyText() {
		clearText(By.cssSelector("input[name='addtocart_75.EnteredQuantity']"));
	}

	public void SelectProcessor() {
		clickElement(By.cssSelector("dl dd:nth-child(2) [type]"));
	}

	public void SelectRAM() {
		clickElement(By.cssSelector("li:nth-of-type(1) > input[name='product_attribute_75_6_32']"));
	}

	public void enterProductQty(String QtyText) {
		enterText(By.cssSelector("input[name='addtocart_75.EnteredQuantity']"), QtyText);
	}

	public void AddSimpleComputerToCart() {
		clickElement(By.cssSelector(".add-to-cart-panel [value='Add to cart']"));

	}
}
