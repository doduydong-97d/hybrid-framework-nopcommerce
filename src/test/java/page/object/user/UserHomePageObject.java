package page.object.user;

import org.openqa.selenium.WebDriver;

import common.PatternObjects;

public class UserHomePageObject extends PatternObjects {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
