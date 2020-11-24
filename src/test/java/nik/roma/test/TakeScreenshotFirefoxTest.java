package nik.roma.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TakeScreenshotFirefoxTest {

	static {
		WebDriverManager.firefoxdriver().setup();
	}

	private static ThreadLocal<WebDriver> threadLocalDriver = ThreadLocal.withInitial(FirefoxDriver::new);

	@Test(dataProvider = "data provider")
	public void verifyTakingScreenshotInChrome(String arg) {
		System.out.println("Test in thread: " + Thread.currentThread().getName());

		WebDriver driver = threadLocalDriver.get();
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys(arg + Keys.ENTER);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	@DataProvider(name = "data provider", parallel = true)
	public static Object[][] dataProvider() {
		return new Object[][]{{"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}};
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		System.out.println("Tear down in thread: " + Thread.currentThread().getName());
		threadLocalDriver.get().quit();
	}

}
