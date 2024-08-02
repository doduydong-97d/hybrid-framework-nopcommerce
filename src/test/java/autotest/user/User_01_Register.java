package autotest.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import common.BaseTest;
import common.PageGeneratorManager;
import common.ReportsListener;
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
	public void Register_01_Empty_Data(Method method) {
		ReportsListener.startTest(method.getName(), "Register_01_Empty_Data");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 01 - Click 'Register' header link");
		userRegisterPage = (UserRegisterPageObject) userHomePage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 02 - Clear 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox("");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 03 - Clear 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox("");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 04 - Clear 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox("");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 05 - Clear 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox("");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 06 - Clear 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox("");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 08 - Verify 'FirstName' error message");
		Assert.assertEquals(userRegisterPage.getFirstNameTextboxErrorMessage(), "First name is required.");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 09 - Verify 'LastName' error message");
		Assert.assertEquals(userRegisterPage.getLastNameTextboxErrorMessage(), "Last name is required.");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 10 - Verify 'Email' error message");
		Assert.assertEquals(userRegisterPage.getEmailTextboxErrorMessage(), "Email is required.");

		ReportsListener.getTest().log(Status.INFO, "Register_01 - Step 11 - Verify 'Confirm Password' error message");
		Assert.assertEquals(userRegisterPage.getConfirmPasswordTextboxErrorMessage(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email(Method method) {
		ReportsListener.startTest(method.getName(), "Register_02_Invalid_Email");

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 01 - Click 'Register' header link");
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 02 - Enter '" + firstName + "' to 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 03 - Enter '" + lastName + "' to 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 04 - Enter 'dong.sdet@gmail@com' to 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox("dong.sdet@gmail@com");

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 05 - Enter '" + password + "' to 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 06 - Enter '" + password + "' to 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_02 - Step 08 - Verify 'Email' error message");
		Assert.assertEquals(userRegisterPage.getEmailTextboxErrorMessage(), "Please enter a valid email address.");

	}

	@Test
	public void Register_03_Invalid_Password(Method method) {
		ReportsListener.startTest(method.getName(), "Register_03_Invalid_Email");

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 01 - Click 'Register' header link");
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 02 - Enter '" + firstName + "' to 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 03 - Enter '" + lastName + "' to 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 04 - Enter '" + emailAddress + "' to 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 05 - Enter 'SeJ3@' to 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox("SeJ3");

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 06 - Enter 'SeJ3@' to 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox("SeJ3");

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_03 - Step 08 - Verify 'Password' error message");
		Assert.assertEquals(userRegisterPage.getPasswordTextboxErrorMessage(), "Password must meet the following rules:\nmust have at least 6 characters and not greater than 64 characters");
	}

	@Test
	public void Register_04_Incorrect_Confirm_Password(Method method) {
		ReportsListener.startTest(method.getName(), "Register_04_Invalid_Email");

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 01 - Click 'Register' header link");
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 02 - Enter '" + firstName + "' to 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 03 - Enter '" + lastName + "' to 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 04 - Enter '" + emailAddress + "' to 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 05 - Enter '" + password + "' to 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 06 - Enter 'JavaSe3@' to 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox("JavaSe3@");

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_04 - Step 08 - Verify 'Password' error message");
		Assert.assertEquals(userRegisterPage.getConfirmPasswordTextboxErrorMessage(), "The password and confirmation password do not match.");
	}

	@Test
	public void Register_05_Valid_Mandatory_Data(Method method) {
		ReportsListener.startTest(method.getName(), "Register_05_Invalid_Email");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 01 - Click 'Register' header link");
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 02 - Enter '" + firstName + "' to 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 03 - Enter '" + lastName + "' to 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 04 - Enter '" + emailAddress + "' to 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 05 - Enter '" + password + "' to 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 06 - Enter '" + password + "' to 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 08 - Verify registration completed message");
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 09 - Click 'Continue' button");
		userHomePage = userRegisterPage.clickContinueButton();

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 10 - Click 'My account' header link");
		userCustomerInfoPage = (UserCustomerInfoPageObject) userHomePage.clickHeaderLinkByLinkText("My account");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 11 - Verify 'My account - Customer info' page title");
		Assert.assertTrue(userCustomerInfoPage.isMyAccountPageTitleDisplayedByPageName("Customer info"));

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 12 - Verify 'Male' radio is not selected");
		Assert.assertFalse(userCustomerInfoPage.isGenderRadioSelectedByValue("Male"));

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 13 - Verify 'Female' radio is not selected");
		Assert.assertFalse(userCustomerInfoPage.isGenderRadioSelectedByValue("Female"));

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 14 - Verify 'FirstName' textbox value is '" + firstName + "'");
		Assert.assertEquals(userCustomerInfoPage.getFirstNameTextboxValue(), firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 15 - Verify 'LastName' textbox value is '" + lastName + "'");
		Assert.assertEquals(userCustomerInfoPage.getLastNameTextboxValue(), lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 16 - Verify 'Day' dropdown value is 'Day");
		Assert.assertEquals(userCustomerInfoPage.getDayDropdownValue(), "Day");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 17 - Verify 'Month' dropdown value is 'Month");
		Assert.assertEquals(userCustomerInfoPage.getMonthDropdownValue(), "Month");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 18 - Verify 'Year' dropdown value is 'Year");
		Assert.assertEquals(userCustomerInfoPage.getYearDropdownValue(), "Year");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 19 - Verify 'Email' textbox value is '" + emailAddress + "'");
		Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(), emailAddress);

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 20 - Verify 'Company' textbox value is empty");
		Assert.assertEquals(userCustomerInfoPage.getCompanyTextboxValue(), "");

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 21 - Verify 'Newsletter' checkbox is selected");
		Assert.assertTrue(userCustomerInfoPage.isNewsletterCheckboxChecked());

		ReportsListener.getTest().log(Status.INFO, "Register_05 - Step 22 - Click 'Log out' header link");
		userHomePage = (UserHomePageObject) userCustomerInfoPage.clickHeaderLinkByLinkText("Log out");
	}

	@Test
	public void Register_06_Existing_Email(Method method) {
		ReportsListener.startTest(method.getName(), "Register_06_Invalid_Email");

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 01 - Click 'Register' header link");
		userRegisterPage.clickHeaderLinkByLinkText("Register");

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 02 - Enter '" + firstName + "' to 'FirstName' textbox");
		userRegisterPage.sendKeysToFirstNameTextbox(firstName);

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 03 - Enter '" + lastName + "' to 'LastName' textbox");
		userRegisterPage.sendKeysToLastNameTextbox(lastName);

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 04 - Enter '" + emailAddress + "' to 'Email' textbox");
		userRegisterPage.sendKeysToEmailTextbox(emailAddress);

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 05 - Enter '" + password + "' to 'Password' textbox");
		userRegisterPage.sendKeysToPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 06 - Enter '" + password + "' to 'Confirm Password' textbox");
		userRegisterPage.sendKeysToConfirmPasswordTextbox(password);

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 07 - Click 'Register' button");
		userRegisterPage.clickRegisterButton();

		ReportsListener.getTest().log(Status.INFO, "Register_06 - Step 08 - Verify email already exists error message");
		Assert.assertEquals(userRegisterPage.getEmailExistsErrorMessage(), "The specified email already exists");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		quitDriver();
	}

}
