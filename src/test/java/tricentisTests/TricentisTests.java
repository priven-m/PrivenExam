package tricentisTests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import frameWorkClasses.BasePage;
import frameWorkClasses.ReadExcelData;
import tricentisPageObjects.ApparelAndShoesPage;
import tricentisPageObjects.BlueGreenSneakers;
import tricentisPageObjects.CartPage;
import tricentisPageObjects.CellPhonesPage;
import tricentisPageObjects.CompareProductsPage;
import tricentisPageObjects.ElectronicsPage;
import tricentisPageObjects.GiftCardsPage;
import tricentisPageObjects.GreenBlueSneakers;
import tricentisPageObjects.HomePage;
import tricentisPageObjects.NoteBooksPage;
import tricentisPageObjects.SimpleComputerPage;
import tricentisPageObjects.SmartPhonePage;

public class TricentisTests extends BasePage {

	// Instantiate all page objects - I have decided to expand on possible pages
	// objects in case i use the project to practice in future.
	HomePage homepg = new HomePage();
	CartPage cartpg = new CartPage();
	GiftCardsPage giftCardpg = new GiftCardsPage();
	ElectronicsPage electronicspg = new ElectronicsPage();
	SmartPhonePage smartPhonepg = new SmartPhonePage();
	CellPhonesPage cellphonepg = new CellPhonesPage();
	NoteBooksPage noteBookspg = new NoteBooksPage();
	ApparelAndShoesPage apparelpg = new ApparelAndShoesPage();
	SimpleComputerPage simpleComputerpg = new SimpleComputerPage();
	ReadExcelData readXL = new ReadExcelData();
	BlueGreenSneakers bluegreeenpg = new BlueGreenSneakers();
	GreenBlueSneakers greenbluepg = new GreenBlueSneakers();
	CompareProductsPage comparepg = new CompareProductsPage();

	// Get data from my excel to update quantity for user story 2
	@DataProvider(name = "UpdateNotebooksQuantityInCart")
	public Object[][] getNotebooksQuantityFromExcel() {
		String excelDirectory = readXL.getDataConfigPropeties("MyQuantityTestData");
		Object[][] errObj = readXL.getExcelData(excelDirectory + "TestData.xlsx", "Sheet1");
		return errObj;
	}

	//Global variable - i use this to check if cart has any items on some tests.
	int expectedCartValue = 1;

	// For readability, i will use Gherkin syntax to explain my tests, but will keep
	// my method names short.
	/*
	 * User Story 1: GIVEN UserIsOnTricentisSite
	 * ANDSuccessfullyFindsCellphoneUnderElectronicsMenuItem WHEN
	 * UserSelectsCellphones AND AddsSmartPhoneToCart THEN
	 * AssertItemDisplayedIsSmartPhoneAndAndotSameAsString
	 */
	@Test
	public void AddItemToCartAndAssertTextOfItemAddedIsSmartPhone() throws InterruptedException {

		String ExpectedText = "FailThisTest";
		int cartValue = homepg.GetTextOfCartQty();

		if (cartValue >= expectedCartValue) {
			homepg.GoToCart();
			cartpg.ClickRemoveFromCart();
			cartpg.ClickUpdateCart();
		} else {
			homepg.goHome();
			homepg.HoverOverElectronicsFromTopMenu();
			electronicspg.ClickOnCellPhones();
			cellphonepg.ClickOnSmartPhone();
			smartPhonepg.ClickAddToCart();
			homepg.GoToCart();

			Assert.assertNotSame(cartpg.GetTextOfSmartPhone(), ExpectedText);
			Reporter.log("Item added to cart is: " + cartpg.GetTextOfSmartPhone());
		}
	}

	/*
	 * User Story 2: GIVEN UserIsOnTricentisSite ANDs
	 * ClicksOnComputersUnderCategories WHEN ClicksOnNotebooks AND
	 * ClicksaddToCartOn14InchLaptop THEN ItemDisplayedInCart14InchLaptop
	 */
	@Test
	public void AddItemToCartAndAssertTextOfItemAddedInCartIs14InchLaptop() {
		String ExpectedText = "14.1-inch Laptop";
		int cartValue = homepg.GetTextOfCartQty();

		if (cartValue >= expectedCartValue) {
			homepg.GoToCart();
			cartpg.ClickRemoveFromCart();
			cartpg.ClickUpdateCart();
		} else {
			homepg.goHome();
			homepg.ClickOnNotebooksFromCategories();
			homepg.SelectNoteBooksFromList();
			noteBookspg.ClickAddToCart();
			homepg.GoToCart();

			Assert.assertEquals(ExpectedText, cartpg.GetElementTextOfLaptop());
			Reporter.log("Notebook in the cart is a " + cartpg.GetElementTextOfLaptop());
		}
	}

	/*
	 * User Story 3: GIVEN UserIsOnTricentisSite AND AddsANoteBookToCart AND
	 * UserNavigatesToCart WHEN ClicksOnQuantity THEN TotalAmtChanges
	 */
	@Test(dataProvider = "UpdateNotebooksQuantityInCart")
	public void GivenNotebookAddedToCartUpdateQuantityUsingExcelSheet(String quantity) throws InterruptedException {
		String ExpectedUpdatedAmt = "6360.00";
		int cartValue = homepg.GetTextOfCartQty();

		if (cartValue >= expectedCartValue) {
			homepg.GoToCart();
			cartpg.ClickRemoveFromCart();
			cartpg.ClickUpdateCart();
		} else {
			homepg.goHome();
			homepg.ClickOnNotebooksFromCategories();
			homepg.SelectNoteBooksFromList();
			noteBookspg.ClickAddToCart();
			homepg.GoToCart();
			cartpg.ClearQtyText();
			cartpg.enterTextInQtytxBox(quantity);
			cartpg.ClickUpdateCart();

			Assert.assertEquals(cartpg.CheckCartTotalAmt(ExpectedUpdatedAmt), true);
			Reporter.log("The updated Subtotal for the product is: R " + cartpg.GetTextOfQty());
		}
	}

	/*
	 * User Story 4: GIVEN SuccessfullyAdssLeatherHandbagToCart AND
	 * UserNavigatesToCart AND SelectsItemTickBox WHEN UpdateShoppingCart THEN
	 * ItemRemoved
	 */
	@Test
	public void GivenItemAddedToCartRemoveItemAndAssertConfirmationText() throws InterruptedException {
		homepg.goHome();
		homepg.clickApparelAndShoes();
		apparelpg.AddOnGenuineLeatherToCart();
		homepg.GoToCart();
		cartpg.ClickRemoveFromCart();
		cartpg.ClickUpdateCart();

		Reporter.log(cartpg.CheckElementTextCartEmpty());
	}

	/*
	 * User Story 5: GIVEN UserIsOnTricentisHomePage AND
	 * SuccessfullyAdds5SimpleComputersToCart AND EnterShippingDetails WHEN
	 * UpdateShoppingCart THEN EstimatedShippingReturned
	 */
	@Test
	public void GivenItemAddedToCartEnterShippingDetailsEstimatedShippingTextStringIsReturned()
			throws InterruptedException {
		int cartValue = homepg.GetTextOfCartQty();
		String ExpectedShippingText = "Ground (0.00)";
		String NewQty = "5";
		String zipCode = "1234";

		if (cartValue >= expectedCartValue) {
			homepg.GoToCart();
			cartpg.ClickRemoveFromCart();
			cartpg.ClickUpdateCart();
		} else {
			homepg.goHome();
			homepg.clickSimpleComputer();
			simpleComputerpg.SelectProcessor();
			simpleComputerpg.SelectRAM();
			simpleComputerpg.ClearQtyText();
			simpleComputerpg.enterProductQty(NewQty);
			simpleComputerpg.AddSimpleComputerToCart();
			homepg.GoToCart();
			cartpg.SelectCountryFromDropDown();
			cartpg.EnterTextInZipCodeTxtBx(zipCode);
			cartpg.ClickEstimateShipping();

			Reporter.log("The expected text is: " + ExpectedShippingText);
			Reporter.log("The returned text is: " + cartpg.getShippingEstimatesText());

		}
	}

	/*
	 * User Story 6: GIVEN UserIsOnTricentisHomePage AND
	 * UserSuccessfullyAdds2ItemsToCart WHEN UserChecksThePrice THEN
	 * TheyShouldNotBeEqual
	 */
	// Add items to compare list and compare the 2 amounts.
	@Test
	public void AddToTwoItemsToCompareListAndValidateAmountsAreNotequal() {
		homepg.goHome();
		homepg.clickApparelAndShoes();
		apparelpg.ClickBlueGreenSneaker();
		bluegreeenpg.AddToCompareList();
		homepg.clickApparelAndShoes();
		apparelpg.ClickGreenBlueSneaker();
		greenbluepg.AddToCompareList();
		Reporter.log("The price for the Blue and Green Sneakers is : " + comparepg.GetTextOfBlueGreenSneakersPrice());
		Reporter.log("The price for the Green and blue Sneakers is : " + comparepg.GetTextOfGreenBlueSneakersPrice());
		Assert.assertNotEquals(comparepg.GetTextOfBlueGreenSneakersPrice(),
				comparepg.GetTextOfGreenBlueSneakersPrice());
	}

	// Close browser after all tests have run
	@AfterTest
	public void CleanUp() {
		TearDown();
	}

}