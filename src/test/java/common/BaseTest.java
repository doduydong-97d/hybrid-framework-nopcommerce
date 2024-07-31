package common;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	private WebDriver driver;

	protected WebDriver initDriver(String browserName, String siteName, String serverName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver();
			break;
		default:
			throw new RuntimeException(browserName.toUpperCase() + " Browser is invalid");
		}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get(getUrlAt(siteName, serverName));
		return driver;
	}

	private String getUrlAt(String siteName, String serverName) {
		if (siteName.equalsIgnoreCase("user")) {
			switch (serverName.toLowerCase()) {
			case "dev":
				return GlobalConstants.USER_DEV_URL;
			case "test":
				return GlobalConstants.USER_TEST_URL;
			case "stag":
				return GlobalConstants.USER_STAG_URL;
			case "demo":
				return GlobalConstants.USER_DEMO_URL;
			case "prod":
				return GlobalConstants.USER_PROD_URL;
			default:
				throw new RuntimeException(serverName.toUpperCase() + " server is invalid");
			}
		} else if (siteName.equalsIgnoreCase("admin")) {
			switch (serverName.toLowerCase()) {
			case "dev":
				return GlobalConstants.ADMIN_DEV_URL;
			case "test":
				return GlobalConstants.ADMIN_TEST_URL;
			case "stag":
				return GlobalConstants.ADMIN_STAG_URL;
			case "demo":
				return GlobalConstants.ADMIN_DEMO_URL;
			case "prod":
				return GlobalConstants.ADMIN_PROD_URL;
			default:
				throw new RuntimeException(serverName.toUpperCase() + " server is invalid");
			}
		} else {
			throw new RuntimeException(siteName.toUpperCase() + " site is invalid");
		}
	}

	protected void quitDriver() {
		driver.manage().deleteAllCookies();
		driver.quit();
	}

}
