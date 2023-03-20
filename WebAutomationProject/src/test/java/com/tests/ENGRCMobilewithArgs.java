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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ENGRCMobilewithArgs {
	public WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;

	@Parameters({"deviceName","osversion","platformName","browserName","userName","accesskey","testName","build" })
	@BeforeMethod
	public void EnGRC(String deviceName,String osversion,String platformName,String browserName,String userName,String accesskey,String testName,String build) throws MalformedURLException, InterruptedException {
		File directory = new File(System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports");
		File[] files = directory.listFiles();
		for (File file : files) {
			if (!file.delete()) {
				System.out.println("");
			}
		}

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports/EnGRCMobileDemo.html", true);
		extent.loadConfig(new File("./extent-config.xml"));
		extent.addSystemInfo("Selenium", "3.12").addSystemInfo("Environment", "QA");
		DesiredCapabilities caps = DesiredCapabilities.iphone();
		caps.setCapability("deviceName",deviceName);
		caps.setCapability("platformVersion",osversion);
		caps.setCapability("platformName",platformName);
		caps.setCapability("browserName",browserName);
		caps.setCapability("username",userName);
		caps.setCapability("accesskey",accesskey);
		caps.setCapability("name",testName);
		caps.setCapability("build",build);
		driver = new RemoteWebDriver(new URL("https://ondemand.eu-central-1.saucelabs.com:443/wd/hub"), caps);
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*************************");
		driver.get("http://115.112.185.58:3200/");
		Thread.sleep(3000);

	}

	@Test(priority = 1, enabled = true)
	public void ENGRCUserNavigation() throws IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"EnGRC-Navigate Menu Links");

			test.log(LogStatus.INFO, "Login into application");
			
			WebDriverWait wait = new WebDriverWait(driver,120);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Login into application
		driver.findElement(By.xpath("//input[@formcontrolname='username']")).click();
		driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("Gillian");
		driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("123");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		Thread.sleep(5000);
		wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Toggle navigation']")));
		String fileName = "Login.png";
		takeScreenshotImage(fileName);

		// Validate login
		if (driver.findElement(By.xpath("//app-header/nav//a[@class='navbar-brand all-in']/span[text()='En']"))
				.isDisplayed()) {
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

		test.log(LogStatus.INFO, "HomeScreen");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//app-header/nav//a[@class='navbar-brand all-in']/span[text()='En']")));
		driver.findElement(By.xpath("//app-header/nav//a[@class='navbar-brand all-in']/span[text()='En']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li/a[text()='Dashboard']")));
		String fileName1 = "HomeScreen.png";
		takeScreenshotImage(fileName1);

		// Validate login
		if (driver.findElement(By.xpath("//li/a[text()='Dashboard']")).isDisplayed()) {
			System.out.println("Navigate to the home Screen uccessfully");
			test.log(LogStatus.PASS,
					"Navigate to the home screen sucessfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));
		} else {
			System.out.println("Failed to navigate to the home screen");
			test.log(LogStatus.FAIL,
					"Failed to navigate to te home screen" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));

		}

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//app-sidebar//div[@class='expand-container']/img")));

		test.log(LogStatus.INFO, "Expand the container");

		driver.findElement(By.xpath("//app-sidebar//div[@class='expand-container']/img")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//app-sidebar//span[text()=' Control Testing '])[2]")));

		String fileName2 = "Expand.png";
		takeScreenshotImage(fileName2);

		// Validate login
		if (driver.findElement(By.xpath("(//app-sidebar//span[text()=' Control Testing '])[2]")).isDisplayed()) {
			System.out.println("Expanded side menu sucessfully");
			test.log(LogStatus.PASS,
					"Expanded side menu sucessfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));
		} else {
			System.out.println("Failed to Expanded side menu");
			test.log(LogStatus.FAIL,
					"Failed to Expanded side menun" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));

		}

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//app-sidebar//span[text()=' Control Testing '])[2]")));

		test.log(LogStatus.INFO, "Open ControlsTesting screen ");

		driver.findElement(By.xpath("(//app-sidebar//span[text()=' Control Testing '])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Controls']")));

		String fileName3 = "ControlsTesting.png";
		takeScreenshotImage(fileName3);

		// Validate login
		if (driver.findElement(By.xpath("//div/p[text()='Controls']")).isDisplayed()) {
			System.out.println("Controlstestingscreen is displayed");
			test.log(LogStatus.PASS,
					"Controlstestingscreen is displayed sucessfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));
		} else {
			System.out.println("Failed to display Controlstestingscreen");
			test.log(LogStatus.FAIL,
					"Failed to display Controlstestingscreen" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));

		}

		test.log(LogStatus.INFO, "Open Case Management Screen");

		driver.findElement(By.xpath("//app-sidebar//div[@class='expand-container']/img")).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("(//app-sidebar//span[text()=' Case Management '])[2]")));
		driver.findElement(By.xpath("(//app-sidebar//span[text()=' Case Management '])[2]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/p[text()='High']")));

		// div/p[text()='High']

		String fileName4 = "CaseManagement.png";
		takeScreenshotImage(fileName4);

		// Validate login
		if (driver.findElement(By.xpath("//div/p[text()='High']")).isDisplayed()) {
			System.out.println("CaseManagement is displayed");
			test.log(LogStatus.PASS,
					"CaseManagement screen is displayed sucessfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));
		} else {
			System.out.println("Failed to display CaseManagement screen");
			test.log(LogStatus.FAIL,
					"Failed to display CaseManagement screen" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));

		}

		test.log(LogStatus.INFO, "Open Report screen");

		driver.findElement(By.xpath("//app-sidebar//div[@class='expand-container']/img")).click();

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("(//app-sidebar//span[text()=' Report '])[2]")));
		driver.findElement(By.xpath("(//app-sidebar//span[text()=' Report '])[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div/span[text()='Reports'])[1]")));

		String fileName5 = "Reports.png";
		takeScreenshotImage(fileName5);

		// Validate login
		if (driver.findElement(By.xpath("//div/p[text()='Total']")).isDisplayed()) {
			System.out.println("Reports is displayed");
			test.log(LogStatus.PASS,
					"Reports screen is displayed sucessfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName5)
									.getPath()));
		} else {
			System.out.println("Failed to display Reports screen");
			test.log(LogStatus.FAIL,
					"Failed to display Reports screen" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName5)
									.getPath()));

		}

		driver.findElement(By.xpath("//button[@aria-label='Toggle navigation']")).click();
		driver.findElement(By.xpath("//span[text()='Gillian']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

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
