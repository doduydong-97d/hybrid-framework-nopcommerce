package common;

import org.openqa.selenium.WebDriver;

public class PatternObjects extends BasePage {
	private WebDriver driver;

	public PatternObjects(WebDriver driver) {
		this.driver = driver;
	}

	public PatternObjects clickHeaderLinkByLinkText(String linkText) {
		waitForElementClickable(driver, PatternUIs.HEADER_LINK_BY_LINKTEXT, linkText);
		clickElement(driver, PatternUIs.HEADER_LINK_BY_LINKTEXT, linkText);
		switch (linkText) {
		case "Register":
			return PageGeneratorManager.getUserRegisterPage(driver);
		case "Login":
			return PageGeneratorManager.getUserLoginPage(driver);
		case "My account":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Log out":
			return PageGeneratorManager.getUserHomePage(driver);

		default:
			throw new RuntimeException(linkText + " link is invalid.");
		}
	}

	public boolean isMyAccountPageTitleDisplayedByPageName(String pageName) {
		waitForElementVisible(driver, PatternUIs.MY_ACCOUNT_PAGE_TITLE_BY_PAGE_NAME, pageName);
		return isElementDisplayed(driver, PatternUIs.MY_ACCOUNT_PAGE_TITLE_BY_PAGE_NAME, pageName);
	}

}
