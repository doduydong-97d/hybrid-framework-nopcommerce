package autotest.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;

public class User_01_Register extends BaseTest {
	private WebDriver driver;

	@Parameters({ "browser", "site", "server" })
	@BeforeClass
	public void beforeClass(String browserName, String siteName, String serverName) {
		driver = initDriver(browserName, siteName, serverName);
	}

	@Test
	public void testCase() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		quitDriver();
	}

}
