package nik.roma.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeFactory {

	static {
		WebDriverManager.chromedriver().setup();
	}

	public WebDriver getChromeDriver() {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1080");

		return new ChromeDriver(options);
	}
}
