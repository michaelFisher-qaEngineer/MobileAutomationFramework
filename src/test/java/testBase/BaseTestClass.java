package testBase;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

@Listeners(listeners.ExtentReportManager.class)
public class BaseTestClass {

	private static final Logger log = LogManager.getLogger(BaseTestClass.class);

	public static WebDriver driver;
	public Properties properties;

	public static WebDriver getDriver() {
		return driver;
	}

	@BeforeClass
	public void openApp() {
		log.info("========== Starting Test Setup ==========");
		// ===== Load Config File =====
		try (FileReader file = new FileReader("./src/test/resources/config.properties")) {
			properties = new Properties();
			properties.load(file);
			log.debug("Config properties loaded successfully from config.properties");
		} catch (IOException e) {
			log.fatal("❌ Failed to load config.properties file. Aborting test run.", e);
			throw new RuntimeException("Could not load configuration, aborting tests.", e);
		}

		try {
			// ===== Desired Capabilities Setup =====
			log.debug("Setting up UiAutomator2Options...");
			UiAutomator2Options options = new UiAutomator2Options();

			options.setPlatformName("Android");
			options.setAutomationName("UiAutomator2");
			options.setDeviceName("emulator-5554");

			String appPath = System.getProperty("user.dir") + "/src/test/resources/General-Store.apk";
			String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/chromedriver";

			options.setApp(appPath);
			options.setCapability("chromedriverExecutable", chromeDriverPath);

			log.debug("App Path: {}", appPath);
			log.debug("ChromeDriver Path: {}", chromeDriverPath);
			log.debug("Appium Server URL: {}", properties.getProperty("appUrl"));

			// ===== Driver Initialization =====
			log.info("Initializing AndroidDriver...");
			driver = new AndroidDriver(new URI(properties.getProperty("appUrl")).toURL(), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			log.info("✅ Android Driver started successfully");

		} catch (Exception e) {
			log.fatal("❌ Driver initialization failed. Tests cannot continue.", e);
			throw new RuntimeException("Driver setup failed", e);
		}
	}

	public static String captureScreen(String tname) {
		log.info("Capturing screenshot for test: {}", tname);
		String targetFilePath = "";
		try {
			if (driver == null) {
				log.error("WebDriver is NULL. Cannot take screenshot.");
				return "";
			}
			String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			targetFilePath = System.getProperty("user.dir") + "/screenshots/" + tname + "_" + timeStamp + ".png";
			File targetFile = new File(targetFilePath);

			// Ensure directory exists
			targetFile.getParentFile().mkdirs();
			Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			log.debug("Screenshot saved at: {}", targetFilePath);
		} catch (Exception e) {
			log.error("❌ Failed to capture screenshot for test: {}", tname, e);
		}
		return targetFilePath;
	}

	@AfterClass
	public void closeApp() {
		log.info("============== Closing App ==============");

		try {
			if (driver != null) {
				log.debug("Quitting driver session...");
				driver.quit();
				log.info("✅ Driver session closed successfully");
			} else {
				log.warn("Driver was already null — nothing to close.");
			}
		} catch (Exception e) {
			log.error("❌ Error occurred while closing the driver.", e);
		} finally {
			driver = null;
		}
		log.info("============== App Closed ==============");
	}
}