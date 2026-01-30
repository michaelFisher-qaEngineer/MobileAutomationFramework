package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC01_LaunchApp extends BaseTestClass {
	
	@Test
	void testLaunchApp() {
		LoginScreen lp = new LoginScreen(getDriver());
		Assert.assertEquals(lp.getPageTitle(), "General Store");
	}	
}
