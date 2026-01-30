package testBase;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

public class BaseTestClass {
	private static final Logger log = LogManager.getLogger(BaseTestClass.class);
//	public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver driver;
	
	public Properties properties;
	public static WebDriver getDriver() {
		return driver;
	}
	
	@BeforeClass
	public void openApp() throws Exception, Exception {
		log.info("========== Starting Test Setup ==========");

		try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
			properties = new Properties();
			properties.load(file);
			log.debug("Config properties loaded successfully.");
		} catch (IOException e) {
			log.error("Failed to load config.properties file.", e);
			throw new RuntimeException("Could not load configuration, aborting tests.", e);
		}
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setAutomationName("UiAutomator2");
		options.setDeviceName("emulator-5554");
		options.setApp(System.getProperty("user.dir") + "/src/test/resources/General-Store.apk");
		options.setCapability("chromedriverExecutable",
		        System.getProperty("user.dir") + "/src/test/resources/chromedriver");

		driver = new AndroidDriver(
                new URI(properties.getProperty("appUrl")).toURL(),
                options
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        log.info("============== Android Driver started successfully ==============");	
	}
	
	public String captureScreen(String tname) {
		log.info("Capturing screenshot for test: {}", tname);
		String targetFilePath = "";
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			File sourceFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
			targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			log.debug("Screenshot saved at: {}", targetFilePath);
		} catch (Exception e) {
			log.error("Failed to capture screenshot for test: {}", tname, e);
		}
		return targetFilePath;
	}
	
	
	@AfterClass
	public void closeApp() {
		log.info("============== Closing App ==============");
		if(driver != null) {
			driver.quit();
			driver = null;
		}
		log.info("============== App Closed ==============");
	}
}
