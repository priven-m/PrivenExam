package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class ElectronicsPage extends BasePage{
	
	public void ClickOnCellPhones() {
		clickElement(By.cssSelector(".sub-category-grid a [title='Show products in category Cell phones']"));
	}

}
