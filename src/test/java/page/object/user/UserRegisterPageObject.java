package page.object.user;

import org.openqa.selenium.WebDriver;

import common.PatternObjects;

public class UserRegisterPageObject extends PatternObjects {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}