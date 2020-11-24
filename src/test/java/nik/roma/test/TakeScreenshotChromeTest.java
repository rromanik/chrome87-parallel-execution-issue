package nik.roma.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import nik.roma.util.ChromeFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TakeScreenshotChromeTest {

	private static ThreadLocal<WebDriver> threadLocalDriver = ThreadLocal.withInitial(new ChromeFactory()::getChromeDriver);

	@Test(dataProvider = "dataProvider")
	public void verifyTakingScreenshotInChrome(String arg) {
		System.out.println("Test in thread: " + Thread.currentThread().getName());

		WebDriver driver = threadLocalDriver.get();
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys(arg + Keys.ENTER);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}

	@DataProvider(parallel = true)
	public static Object[][] dataProvider() {
		return new Object[][]{{"a"}, {"b"}, {"c"}, {"d"}, {"e"}, {"f"}, {"g"}, {"h"}};
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		System.out.println("Tear down in thread: " + Thread.currentThread().getName());
		threadLocalDriver.get().quit();
	}

}
