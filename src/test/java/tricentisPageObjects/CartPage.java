package tricentisPageObjects;

import org.openqa.selenium.By;
import org.testng.Reporter;

import frameWorkClasses.BasePage;

public class CartPage extends BasePage {

	public String GetTextOfSmartPhone() {
		String actualText = getElementText(By.linkText("Smartphone"));
		return actualText;
	}

	public String GetElementTextOfLaptop() {
		String actualText = getElementText(By.linkText("14.1-inch Laptop"));
		return actualText;
	}

	public String GetTextOfQty() {
		String actualText = getElementText(By.cssSelector(".product-subtotal"));
		return actualText;
	}

	public void enterTextInQtytxBox(String qtyText) {
		enterText(By.xpath(
				"/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[5]/input[1]"),
				qtyText);
	}

	public void ClearQtyText() {
		clearText(By.xpath(
				"/html[1]/body[1]/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[5]/input[1]"));
	}

	public void ClickUpdateCart() {
		clickElement(By.cssSelector("input[name='updatecart']"));
	}

	public boolean CheckCartTotalAmt(String CheckAmt) throws InterruptedException {
		String itemElement = ".product-subtotal";
		System.out.println(CheckAmt);
		if (getElementText(By.cssSelector(itemElement)).contains(CheckAmt)) {
			Reporter.log("Current amount in cart is R " + getElementText(By.cssSelector(itemElement)));
			return true;
		}
		Reporter.log("Amount is incorrect. Expected R " + CheckAmt);
		Reporter.log("Returned amount R " + getElementText(By.cssSelector(itemElement)));
		return false;
	}

	public void ClickRemoveFromCart() {
		clickElement(By.cssSelector(".remove-from-cart > input[name='removefromcart']"));
	}

	public String CheckElementTextCartEmpty() {
		String actualText = getElementText(By.cssSelector(".order-summary-content"));
		return actualText;
	}

	public void SelectCountryFromDropDown() {
		clickElement(By.xpath("//option[contains(text(),'Zimbabwe')]"));
	}

	public String GetTextOfSimpleComputerInCart() {
		String actualText = getElementText(By.linkText("Simple Computer"));
		return actualText;
	}

	public void EnterTextInZipCodeTxtBx(String zipCodeText) {
		enterText(By.xpath("/html//input[@id='ZipPostalCode']"), zipCodeText);
	}

	public void ClickEstimateShipping() {
		clickElement(By.cssSelector("[class='button-2 estimate-shipping-button']"));
	}

	public String getShippingEstimatesText() {
		String actualText = getElementText(By.cssSelector("li:nth-of-type(2) > .option-name"));
		return actualText;
	}
}