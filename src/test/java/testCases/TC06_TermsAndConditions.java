package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseTestClass;

public class TC06_TermsAndConditions extends BaseTestClass {

	String productName = "Jordan 6 Rings";

	@Test
	public void termsAndConditionsTest() throws Exception {
		// login to app:
		LoginScreen lp = new LoginScreen(getDriver());
		lp.selectRadioButton("male");
		lp.enterName("michael");
		lp.clickLetsShopButton();

		ProductsPage prod = new ProductsPage(getDriver());
		prod.addProductToCart(getDriver(), productName);
		prod.openCart();
		
		ShoppingCartPage cart = new ShoppingCartPage(getDriver());
		cart.longPressTermsButton(getDriver());
		Assert.assertEquals(cart.getAlertTitle(getDriver()), "Terms Of Conditions");

	}
}
