package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC06_ValidateToastMessage extends BaseTestClass {

	@Test
	public void letsShopTest() throws Exception {
		LoginScreen lp = new LoginScreen(getDriver());
		lp.selectRadioButton("male");
		lp.clickLetsShopButton();
		Assert.assertEquals(lp.getToastMessage(), "Please enter your name");
	}
}
