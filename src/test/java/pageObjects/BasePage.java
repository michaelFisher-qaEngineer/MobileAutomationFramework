package pageObjects;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class BasePage {
	protected WebDriver driver;
	protected final Logger log = LogManager.getLogger(getClass());
	
	BasePage(WebDriver driver) {
		this.driver = driver;
		log.debug("Initializing page: {}", getClass().getSimpleName());
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
	}

}
