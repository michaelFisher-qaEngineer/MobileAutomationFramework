package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import pageObjects.ProductsPage;
import testBase.BaseTestClass;

public class TC03_LetsShop extends BaseTestClass {

	@Test
	public void letsShopTest() throws Exception {
		LoginScreen lp = new LoginScreen(getDriver());
		lp.selectRadioButton("male");
		lp.enterName("michael");
		lp.clickLetsShopButton();

		ProductsPage prod = new ProductsPage(getDriver());
		Assert.assertEquals(prod.getProductPageTitle(), "Products");

	}
}
