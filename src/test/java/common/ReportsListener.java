package common;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportsListener implements ITestListener {
	private static ExtentReports extentReports;
	private static final Map<Integer, ExtentTest> extentTestMap = new ConcurrentHashMap<>();
	private static String reportName;
	private static String browser;
	private static String role;
	private static String server;

	private static ExtentReports createExtentReports() {
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(GlobalConstants.EXTENT_REPORTS_PATH + reportName);

		sparkReporter.config().setDocumentTitle("nopCommerce Automation Report");
		sparkReporter.config().setReportName("nopCommerce Functional Testing");
		sparkReporter.config().setTimelineEnabled(true);
		sparkReporter.config().setEncoding("utf-8");
		sparkReporter.config().setTheme(Theme.DARK);

		extentReports.attachReporter(sparkReporter);
		extentReports.setSystemInfo("Project", "nopCommerce");
		extentReports.setSystemInfo("Browser", browser);
		extentReports.setSystemInfo("Site", role);
		extentReports.setSystemInfo("Server", server);
		extentReports.setSystemInfo("SDET", GlobalConstants.USER_NAME);
		extentReports.setSystemInfo("OS", GlobalConstants.OS_NAME);
		extentReports.setSystemInfo("JDK Version", GlobalConstants.JDK_VERSION);

		return extentReports;
	}

	public static synchronized ExtentTest startTest(String testName, String stepDesc) {
		ExtentTest test = extentReports.createTest(testName, stepDesc);
		extentTestMap.put((int) Thread.currentThread().getId(), test);
		return test;
	}

	public static synchronized ExtentTest getTest() {
		return extentTestMap.get((int) Thread.currentThread().getId());
	}

	@Override
	public void onTestStart(ITestResult result) {
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		getTest().log(Status.PASS, result.getName() + " [PASSED]");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String screenshotBase64 = "data:image/png;base64," + ((TakesScreenshot) ((BaseTest) result.getInstance()).getDriver()).getScreenshotAs(OutputType.BASE64);
		getTest().log(Status.FAIL, result.getThrowable().getMessage(), getTest().addScreenCaptureFromBase64String(screenshotBase64).getModel().getMedia().get(0));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		getTest().log(Status.SKIP, result.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		XmlTest xmlTest = context.getCurrentXmlTest();
		browser = xmlTest.getParameter("browser");
		role = xmlTest.getParameter("site");
		server = xmlTest.getParameter("server");
		reportName = "report-" + new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss").format(new Date()) + "-" + browser + "-" + role + "-" + server + ".html";
		extentReports = createExtentReports();
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReports.flush();
		File reportsFile = new File(GlobalConstants.EXTENT_REPORTS_PATH + reportName);
		try {
			Desktop.getDesktop().browse(reportsFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
