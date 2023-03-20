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
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
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

public class FlexibShoppingCart {
	public WebDriver driver;
	
	String ProductID="AIRDOPS12";
	String ProductName="JBL AirPods";
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
				System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports/ShoppingCartDemo.html", true);
		extent.loadConfig(new File("./extent-config.xml"));
		extent.addSystemInfo("Selenium", "3.12").addSystemInfo("Environment", "Production");
		// System.setProperty("webdriver.chrome.driver","C:\\Users\\1002162\\eclipse-workspace\\ICONProduct\\WebAutomationTesting\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*************************");
		driver.get("http://14.99.175.107:17670/SpringMVCAnnotationShoppingCart/");

	}

	@Test(priority = 1, enabled = true)
	public void NGRCCreateAndDeleteRisk() throws IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Online Shopping Cart Application");

		test.log(LogStatus.INFO, "Login into application");

		WebDriverWait wait = new WebDriverWait(driver, 120);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Login into application
		driver.findElement(By.xpath("//*[text()='Login']")).click();
		driver.findElement(By.xpath("//*[@name='userName']")).sendKeys("admin");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("123");
		driver.findElement(By.xpath("//*[@value='Login']")).click();
		Thread.sleep(2000);
		// Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Hello')]")));
		String fileName = "Login.png";
		takeScreenshotImage(fileName);

		// Validate login
		if (driver.findElement(By.xpath("//*[contains(text(),'Hello')]")).isDisplayed()) {
			System.out.println(
					"Entered username, password and clicked login button. User navigated to Product listing page.");
			test.log(LogStatus.PASS,
					"Entered username, password and clicked login button. User navigated to Product listing page."
							+ test.addScreenCapture(new File(System.getProperty("user.dir")
									+ "/src/test/java/com/shoppingcart/screenshots/" + fileName).getPath()));
		} else {
			System.out.println(
					"Entered username, password and clicked login button. User not navigated to Product listing page.");
			test.log(LogStatus.FAIL,
					"Entered username, password and clicked login button. User not navigated to Product listing page."
							+ test.addScreenCapture(new File(System.getProperty("user.dir")
									+ "/src/test/java/com/shoppingcart/screenshots/" + fileName).getPath()));

		}
		Thread.sleep(2000);

		test.log(LogStatus.INFO, "Create new Product");
		// Create Product
		driver.findElement(By.xpath("//*[contains(text(),'Create Product')]")).click();
		driver.findElement(By.xpath("//*[@id='code']")).sendKeys(ProductID);
		driver.findElement(By.xpath("//*[@id='name']")).sendKeys(ProductName);
		driver.findElement(By.xpath("//*[@id='price']")).sendKeys("1000");

		WebElement addfilebtn = driver.findElement(By.id("fileData"));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", addfilebtn);
		// driver.findElement(By.id("fileData")).click();
		Thread.sleep(2000);
		// File Upload
		StringSelection stringSelection = new StringSelection("D:\\poc\\download.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_CONTROL);
		r.keyPress(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_V);
		r.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(1000);
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@value='Submit']")).click();
		Thread.sleep(2000);
		String fileName1 = "CreateProduct.png";
		takeScreenshotImage(fileName1);

		// Validate the Create product
		if (driver.getPageSource().contains(ProductID)) {
			System.out.println(
					"Entered all product related details and clicked submit button. New product created successfully.");
			test.log(LogStatus.PASS,
					"Entered all product related details and clicked submit button. New product created successfully."
							+ test.addScreenCapture(new File(System.getProperty("user.dir")
									+ "/src/test/java/com/shoppingcart/screenshots/" + fileName1).getPath()));
		} else {
			System.out.println("Product not created successfully.");
			test.log(LogStatus.FAIL,
					"Product not created successfully." + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));

		}
		Thread.sleep(2000);

		test.log(LogStatus.INFO, "Validate the Product.");
		// Validate the Create product in listing page.
		if (driver.getPageSource().contains(ProductID)) {
			System.out.println("Created product is displayed on Product List page.");
			test.log(LogStatus.PASS,
					"Created product is displayed on Product List page." + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));
		} else {
			System.out.println("Created product is not displayed on Product List page.");
			test.log(LogStatus.FAIL,
					"Created product is not displayed on Product List page." + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));
		}
		Thread.sleep(2000);

		test.log(LogStatus.INFO, "Buy a product");
		// Buy a Product
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@href='/SpringMVCAnnotationShoppingCart/buyProduct?code="+ ProductID +"']")).click();
		String fileName2 = "Buy a product.png";
		takeScreenshotImage(fileName2);

		// Validate the Buy a product page navigation
		if (driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).isDisplayed()) {
			System.out.println("Clicked 'Buy a Product' link, user navigated to buy a product page.");
			test.log(LogStatus.PASS, "Clicked 'Buy a Product' link, user navigated to buy a product page"
					+ test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));
		} else {
			System.out.println("Clicked 'Buy a Product' link, user not navigated to buy a product page.");
			test.log(LogStatus.FAIL, "Clicked 'Buy a Product' link, user not navigated to buy a product page."
					+ test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));
		}
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@id='cartLines0.quantity']")).clear();
		driver.findElement(By.xpath("//input[@id='cartLines0.quantity']")).sendKeys("2");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(text(),'Continue')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[contains(text(),'My Cart')]")).click();
		Thread.sleep(2000);
		String fileName3 = "MyCart.png";
		takeScreenshotImage(fileName3);

		test.log(LogStatus.INFO, "Continue Buy a product.");
		// Validate the Create product
		if (driver.getPageSource().contains(ProductID)) {
			System.out.println("Added quantity and clicked 'Continue Buy' link, product placed in 'My Cart' page.");
			test.log(LogStatus.PASS, "Added quantity and clicked 'Continue Buy' link, product placed in 'My Cart' page"
					+ test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));
		} else {
			System.out.println("Added quantity and clicked 'Continue Buy' link, product not placed in 'My Cart' page.");
			test.log(LogStatus.FAIL,
					"Added quantity and clicked 'Continue Buy' link, product not placed in 'My Cart' page"
							+ test.addScreenCapture(new File(System.getProperty("user.dir")
									+ "/src/test/java/com/shoppingcart/screenshots/" + fileName3).getPath()));
		}
		Thread.sleep(2000);

		test.log(LogStatus.INFO, "Validate product in My Cart Page");
		// Validate the My Cart product
		if (driver.getPageSource().contains(ProductID)) {
			System.out.println("Purchased product is displayed in My Cart page.");
			test.log(LogStatus.PASS,
					"Purchased product is displayed in My Cart page." + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));
		} else {
			System.out.println("Purchased product is not displayed in My Cart page.");
			test.log(LogStatus.FAIL,
					"Purchased product is not displayed in My Cart page." + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
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