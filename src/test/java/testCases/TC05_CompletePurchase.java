package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import pageObjects.WebviewPage;
import testBase.BaseTestClass;

public class TC05_CompletePurchase extends BaseTestClass {
	String productName = "Jordan 6 Rings";
	@Test
	public void completePurchaseTest() throws Exception {
		LoginScreen lp = new LoginScreen(getDriver());
		lp.selectRadioButton("male");
		lp.enterName("michael");
		lp.clickLetsShopButton();

		ProductsPage prod = new ProductsPage(getDriver());
		prod.addProductToCart(getDriver(), productName);
		prod.openCart();
		
		ShoppingCartPage cart = new ShoppingCartPage(getDriver());
		cart.checkSendEmailsCheckbox();
		cart.clickCompletePurchaseButton();
		
		WebviewPage webview = new WebviewPage(getDriver());
		webview.searchForTerm(getDriver(), "Bootcamp");
		webview.goBack(driver);
		webview.switchToNativeContext(driver);
		Assert.assertEquals(lp.getPageTitle(), "General Store");

	}

}
