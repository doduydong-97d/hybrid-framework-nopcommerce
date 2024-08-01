package autotest.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.BaseTest;
import common.PageGeneratorManager;
import page.object.user.UserCustomerInfoPageObject;
import page.object.user.UserHomePageObject;
import page.object.user.UserRegisterPageObject;

public class User_01_Register extends BaseTest {
	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	String firstName, lastName, emailAddress, password;

	@Parameters({ "browser", "site", "server" })
	@BeforeClass
	public void beforeClass(String browserName, String siteName, String serverName) {
		driver = initDriver(browserName, siteName, serverName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Dong";
		lastName = "Do";
		emailAddress = "dong.sdet" + getRandomNumbers() + "@gmail.com";
		password = "SeJava3@";
	}

	@Test
	public void Register_01_Empty_Data() {
		userRegisterPage = (UserRegisterPageObject) userHomePage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox("");

		userRegisterPage.sendKeysToLastNameTextbox("");

		userRegisterPage.sendKeysToEmailTextbox("");

		userRegisterPage.sendKeysToPasswordTextbox("");

		userRegisterPage.sendKeysToConfirmPasswordTextbox("");

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getFirstNameTextboxErrorMessage(), "First name is required.");

		Assert.assertEquals(userRegisterPage.getLastNameTextboxErrorMessage(), "Last name is required.");

		Assert.assertEquals(userRegisterPage.getEmailTextboxErrorMessage(), "Email is required.");

		Assert.assertEquals(userRegisterPage.getConfirmPasswordTextboxErrorMessage(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		userRegisterPage.sendKeysToEmailTextbox("dong.sdet@gmail@com");

		userRegisterPage.sendKeysToPasswordTextbox(password);

		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getEmailTextboxErrorMessage(), "Please enter a valid email address.");

	}

	@Test
	public void Register_03_Invalid_Password() {
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		userRegisterPage.sendKeysToPasswordTextbox("SeJ3@");

		userRegisterPage.sendKeysToConfirmPasswordTextbox("SeJ3@");

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getPasswordTextboxErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters and not greater than 64 characters");
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password() {
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		userRegisterPage.sendKeysToPasswordTextbox(password);

		userRegisterPage.sendKeysToConfirmPasswordTextbox("JavaSe3@");

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getConfirmPasswordTextboxErrorMessage(), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Valid_Mandatory_Data() {
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		userRegisterPage.sendKeysToPasswordTextbox(password);

		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userHomePage = userRegisterPage.clickContinueButton();

		userCustomerInfoPage = (UserCustomerInfoPageObject) userHomePage.clickHeaderLinkByLinkText("My account");

		Assert.assertTrue(userCustomerInfoPage.isMyAccountPageTitleDisplayedByPageName("Customer info"));

		Assert.assertFalse(userCustomerInfoPage.isGenderRadioSelectedByValue("Male"));

		Assert.assertFalse(userCustomerInfoPage.isGenderRadioSelectedByValue("Female"));

		Assert.assertEquals(userCustomerInfoPage.getFirstNameTextboxValue(), firstName);

		Assert.assertEquals(userCustomerInfoPage.getLastNameTextboxValue(), lastName);

		Assert.assertEquals(userCustomerInfoPage.getDayDropdownValue(), "Day");

		Assert.assertEquals(userCustomerInfoPage.getMonthDropdownValue(), "Month");

		Assert.assertEquals(userCustomerInfoPage.getYearDropdownValue(), "Year");

		Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(), emailAddress);

		Assert.assertEquals(userCustomerInfoPage.getCompanyTextboxValue(), "");

		Assert.assertTrue(userCustomerInfoPage.isNewsletterCheckboxChecked());

		userHomePage = (UserHomePageObject) userCustomerInfoPage.clickHeaderLinkByLinkText("Log out");
	}

	@Test
	public void Register_06_Existing_Email() {
		userRegisterPage = (UserRegisterPageObject) userHomePage.clickHeaderLinkByLinkText("Register");

		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		userRegisterPage.sendKeysToPasswordTextbox(password);

		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		userRegisterPage.clickRegisterButton();

		Assert.assertEquals(userRegisterPage.getEmailExistsErrorMessage(), "The specified email already exists");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		quitDriver();
	}

}
