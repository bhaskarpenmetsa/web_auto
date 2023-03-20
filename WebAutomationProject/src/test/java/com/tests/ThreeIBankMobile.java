package com.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ThreeIBankMobile {
	public WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;

	@BeforeMethod
	public void ShoppingCart() throws MalformedURLException, InterruptedException {
		File directory = new File(System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports");
		File[] files = directory.listFiles();
		for (File file : files) {
			if (!file.delete()) {
				System.out.println("");
			}
		}

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports/3ibankAutomationReport.html",
				true);
		extent.loadConfig(new File("./extent-config.xml"));
		extent.addSystemInfo("Selenium", "3.12").addSystemInfo("Environment", "Production");
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("deviceName", "iPhone 12 Pro Max Simulator");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("platformVersion", "15.0");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("browserName", "Safari");
		caps.setCapability("username", "oauth-infotechdemoacc32-0ea01");
		caps.setCapability("accesskey", "812d7664-e2d2-4f7a-82c9-128de77f2e4a");
		caps.setCapability("name", "SampleTest");
		caps.setCapability("build", "Build11");
		// Emusim devices have Simulator/Emulator in the name
		driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*************************");
		driver.get("http://14.99.175.107:17656/3iBank/index.html");

	}

	@Test(priority = 1, enabled = true)
	public void ThreeIBankLoanSubmission() throws IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "3i-Bank Loan submission");

		test.log(LogStatus.INFO, "Login into application");

		WebDriverWait wait = new WebDriverWait(driver, 120);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Login into application
		driver.findElement(By.xpath("//input[@id='username']")).click();
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("vamsi");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("vamsi");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='card']")).sendKeys("4004");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@id='login_btn']")).click();
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@id='profilename']")));
		String fileName = "Login.png";
		takeScreenshotImage(fileName);

		// Validate login
		if (driver.findElement(By.xpath("//h4[@id='profilename']")).isDisplayed()) {
			System.out.println("Entered username, password and clicked login button.");
			test.log(LogStatus.PASS, "Entered username, password and clicked login button.." + test.addScreenCapture(
					new File(System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName)
							.getPath()));
		} else {
			System.out.println("Entered username, password and clicked login button. User Failed to login.");
			test.log(LogStatus.FAIL, "Entered username, password and clicked login button. User User Failed to login"
					+ test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName)
									.getPath()));

		}

		test.log(LogStatus.INFO, "Click On Apply Loan");

		WebElement cname = driver.findElement(By.xpath("//div[@class='carousel-inner']/div"));
		// get class attribute with getAttribute()
		String val = cname.getAttribute("class");

		System.out.println(val);

		if (val.equalsIgnoreCase("carousel-item active")) {

			driver.findElement(By.xpath("(//button[text()='Apply Now'])[1]")).click();
			//System.out.println("Caractive selected");

		} else {

			driver.findElement(By.xpath("(//button[text()='Apply Now'])[2]")).click();
			//System.out.println("Caractive not selected");

		}

		// Thread.sleep(3000);
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='fixed']/h4[text()=' We need your Approval ']")));

		String fileName1 = "ClikApplyLoan.png";
		takeScreenshotImage(fileName1);

		if (driver.findElement(By.xpath("//h4[text()=' We need your Approval ']")).isDisplayed()) {
			System.out.println("Clicked on Apply Loan Button");
			test.log(LogStatus.PASS,
					"Clicked on Apply Loan Button" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));
		} else {
			System.out.println("Failed to Click on Apply Loan Button");
			test.log(LogStatus.FAIL,
					"Failed to Click on Apply Loan Button" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));

		}

		test.log(LogStatus.INFO, "Accept Aggrement");
		WebElement ele = driver.findElement(By.xpath("//input[@id='myCheck']"));

		// Creating object of an Actions class
		Actions action = new Actions(driver);

		// Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();
		driver.findElement(By.xpath("//input[@id='myCheck']")).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Agree & Continue']")));
		driver.findElement(By.xpath("//button[text()='Agree & Continue']")).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Customer Form']")));
		String fileName2 = "Aggrement.png";
		takeScreenshotImage(fileName2);

		if (driver.findElement(By.xpath("//h6[text()='Customer Form']")).isDisplayed()) {
			System.out.println("Accepted Aggrement");
			test.log(LogStatus.PASS,
					"Accepted Aggrement" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));
		} else {
			System.out.println("Failed to Accept Aggrement");
			test.log(LogStatus.FAIL,
					"Failed to Accept Aggrement" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));

		}

		test.log(LogStatus.INFO, "Fill Personal Details");
		Thread.sleep(1000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[@id='personaldetails'][@aria-selected='true']")));

		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Bhaskar");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Penmetsa");
		Thread.sleep(500);
		driver.findElement(By.xpath("(//input[@name='gender'])[1]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//p[text()='Date Of Birth']/following-sibling::input")).sendKeys("14-02-1987");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='pancard']")).sendKeys("BXJHG1876G");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='mobileno']")).sendKeys("9999999999");
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("bhaskar@gmail.com");
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Employer*']")));
		String fileName3 = "Personaldetails.png";
		takeScreenshotImage(fileName3);

		if (driver.findElement(By.xpath("//p[text()='Employer*']")).isDisplayed()) {
			System.out.println("Personal Details entered");
			test.log(LogStatus.PASS,
					"Personal Details entered" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));
		} else {
			System.out.println("Failed to Personal Details enter");
			test.log(LogStatus.FAIL,
					"Failed to Personal Details enter" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));

		}
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "Fill Employment Details");
		driver.findElement(By.xpath("//div/p[text()='Employer*']/following-sibling::input")).sendKeys("3i-infotech");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div/p[text()='Employers Phone']/following-sibling::input"))
				.sendKeys("9988776655");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div/p[text()='Employement Status*']/following-sibling::input"))
				.sendKeys("Permenent");
		Thread.sleep(500);
		// driver.findElement(By.xpath("/div/p[text()='Employement
		// Status*']/following-sibling::input")).sendKeys("Permenent");

		Select paid = new Select(driver.findElement(By.id("quality-source")));
		paid.selectByVisibleText("First Day Of The Month");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div/p[text()='Last Pay Date*']/following-sibling::input"))
				.sendKeys("01-10-2021");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div/p[text()='Next Pay Date*']/following-sibling::input"))
				.sendKeys("11-11-2021");
		Thread.sleep(500);

		driver.findElement(By.xpath("//div/p[text()='Last Take Home Pay Cheque Amount*']/following-sibling::input"))
				.sendKeys("5000");
		Thread.sleep(500);
		driver.findElement(By.xpath("//div/p[text()='Take Home For The Month Of May*']/following-sibling::input"))
				.sendKeys("5000");
		Thread.sleep(500);
		driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='submit_form']/div/h6[text()=' Successful ']")));

		String fileName4 = "EmploymentDetails.png";
		takeScreenshotImage(fileName4);

		if (driver.findElement(By.xpath("//div[@class='submit_form']/div/h6[text()=' Successful ']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[@class='btn btn-primary submit_ok']")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='Employer*']")));
			System.out.println("EmploymentDetail submitted");
			test.log(LogStatus.PASS,
					"EmploymentDetail submitted" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));
		} else {
			System.out.println("Failed to submit EmploymentDetail");
			test.log(LogStatus.FAIL,
					"Failed to submit EmploymentDetail" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));

		}
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "Logout");
		driver.findElement(By.xpath("//img[@id='profileimg']")).click();
		Thread.sleep(500);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Logout']")));
		driver.findElement(By.xpath("//span[text()='Logout']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='login_btn']")));
		String fileName5 = "Logout.png";
		takeScreenshotImage(fileName5);

		if (driver.findElement(By.xpath("//button[@id='login_btn']")).isDisplayed()) {
			System.out.println("Logout Successfully");
			test.log(LogStatus.PASS,
					"Logout Successfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));
		} else {
			System.out.println("Failed to Logout");
			test.log(LogStatus.FAIL,
					"Failed to Logout" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));

		}

		extent.endTest(test);
		extent.flush();
		extent.close();

		Thread.sleep(2000);
		driver.close();

	}

	public void takeScreenshotImage(String fileNameWithExtention) throws IOException {
		WebDriver driver1 = new Augmenter().augment(driver);
		File actualFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		try {
			BufferedImage fullImg = ImageIO.read(actualFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			FileUtils.copyFile(actualFile, new File(System.getProperty("user.dir")
					+ "/src/test/java/com/shoppingcart/screenshots/" + fileNameWithExtention));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
