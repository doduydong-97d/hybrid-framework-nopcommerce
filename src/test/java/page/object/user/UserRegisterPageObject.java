package page.object.user;

import org.openqa.selenium.WebDriver;

import common.PageGeneratorManager;
import common.PatternObjects;
import page.ui.user.UserRegisterPageUI;

public class UserRegisterPageObject extends PatternObjects {
	private WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public void sendKeysToFirstNameTextbox(String keysToSend) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX, keysToSend);
	}

	public void sendKeysToLastNameTextbox(String keysToSend) {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.LASTNAME_TEXTBOX, keysToSend);
	}

	public void sendKeysToEmailTextbox(String keysToSend) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, keysToSend);
	}

	public void sendKeysToPasswordTextbox(String keysToSend) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, keysToSend);
	}

	public void sendKeysToConfirmPasswordTextbox(String keysToSend) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, keysToSend);
	}

	public void clickRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
	}

	public String getFirstNameTextboxErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.FIRSTNAME_TEXTBOX_ERROR_MESSAGE);
	}

	public String getLastNameTextboxErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.LASTNAME_TEXTBOX_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.LASTNAME_TEXTBOX_ERROR_MESSAGE);
	}

	public String getEmailTextboxErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_TEXTBOX_ERROR_MESSAGE);
	}

	public String getPasswordTextboxErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.PASSWORD_TEXTBOX_ERROR_MESSAGE);
	}

	public String getConfirmPasswordTextboxErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX_ERROR_MESSAGE);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	public UserHomePageObject clickContinueButton() {
		waitForElementClickable(driver, UserRegisterPageUI.CONTINUE_BUTTON);
		clickElement(driver, UserRegisterPageUI.CONTINUE_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public String getEmailExistsErrorMessage() {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_EXISTS_ERROR_MESSAGE);
		return getElementText(driver, UserRegisterPageUI.EMAIL_EXISTS_ERROR_MESSAGE);
	}

}