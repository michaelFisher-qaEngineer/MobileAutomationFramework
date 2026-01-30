package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginScreen;
import testBase.BaseTestClass;

public class TC06_ValidateToastMessage extends BaseTestClass {
	private static final Logger log = LogManager.getLogger(TC06_ValidateToastMessage.class);

	@Test
	public void validateToastMessageTest() throws Exception {
		log.info("========== TEST START: toast message when not entering name ==========");
		try {
			// ===== Login Step =====
			log.info("Step 1: Entering user details on Login screen");

			LoginScreen lp = new LoginScreen(getDriver());
			lp.selectRadioButton("male");
			log.debug("Gender selected: male");
			
			lp.clickLetsShopButton();
			log.info("Login details submitted with name filed empty");
			String actualToastMessage = lp.getToastMessage();
			String expectedToastMessage = "Please enter your name";
			log.info("Verifying toast message text");
			log.debug("Expected toast message: {}", expectedToastMessage);
			log.debug("Actual toast messsage: {}", actualToastMessage);
			Assert.assertEquals(actualToastMessage, expectedToastMessage);
		} catch (Exception e) {
            log.error("❌ Test failed: Validate toast message failed; reason: {}", e.getMessage(), e);
            throw e;
        }

        log.info("========== TEST END: validateToastMessageTest ==========");
    }
}
