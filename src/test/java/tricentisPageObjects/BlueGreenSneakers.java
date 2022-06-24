package tricentisPageObjects;

import org.openqa.selenium.By;

import frameWorkClasses.BasePage;

public class BlueGreenSneakers extends BasePage {
	
	public void AddToCompareList() {
		clickElement(By.cssSelector("[class='button-2 add-to-compare-list-button']"));
	}

}
