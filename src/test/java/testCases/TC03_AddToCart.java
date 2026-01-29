package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import pageObjects.ShoppingCartPage;
import testBase.BaseTestClass;
import utilities.AppiumUtilities;

public class TC03_AddToCart extends BaseTestClass{
	
	String productName = "Jordan 6 Rings";
	@Test
	public void addToCartTest() throws Exception {
		//login to app:
		LoginScreen lp = new LoginScreen(getDriver());
		lp.selectRadioButton("male");
		lp.enterName("michael");
		lp.clickLetsShopButton();
		
		ProductsPage prod = new ProductsPage(getDriver());
		AppiumUtilities.scrollAndroidElementIntoView(getDriver(), productName);
		prod.addProductToCart(getDriver(), productName);
		prod.openCart();
		
		ShoppingCartPage cart = new ShoppingCartPage(getDriver());
		Assert.assertEquals(cart.getProductNameInCart(), productName);
	}
}
