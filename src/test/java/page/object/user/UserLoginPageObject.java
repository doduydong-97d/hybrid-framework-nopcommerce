package page.object.user;

import org.openqa.selenium.WebDriver;

import common.PatternObjects;

public class UserLoginPageObject extends PatternObjects {
	private WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}