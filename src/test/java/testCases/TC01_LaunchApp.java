package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC01_LaunchApp extends BaseTestClass {
	private static final Logger log = LogManager.getLogger(TC01_LaunchApp.class);
	
	@Test
	void testLaunchApp() {
		log.info("========== TEST START: Verify app opens to General Store title page ==========");
		
		try {
			log.info("Step 1: Initializing Login Screen");
			LoginScreen lp = new LoginScreen(getDriver());
			Assert.assertEquals(lp.getPageTitle(), "General Store");
			log.info("✅ Validation passed — App opened successfully.");
		} catch (Exception e) {
			log.error("❌ Test failed due to exception: {}", e.getMessage(), e);
			throw e; // rethrow so TestNG still marks test as FAILED
		}
		
		log.info("========== TEST END: testLaunchApp ==========");
	}	
}
