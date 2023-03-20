package com.tests;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Saucedemo2 {
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
		driver.get("https://www.saucedemo.com/");

	}

	@Test(priority = 1, enabled = true)
	public void OnineShoppingCart() throws IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"OnlineShopingCart");

		test.log(LogStatus.INFO, "Login into application");
		WebDriverWait wait = new WebDriverWait(driver, 120);

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		// Login into application
		driver.findElement(By.xpath("//input[@name='user-name']")).click();
		driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@name='login-button']")).click();

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Products']")));
		String fileName = "Login.png";
		takeScreenshotImage(fileName);

		// Validate login
		if (driver.findElement(By.xpath("//span[text()='Products']")).isDisplayed()) {
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

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sauce Labs Backpack']")));

		test.log(LogStatus.INFO, "Select Produce");
		driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).click();

		//// div[text()='Sauce Labs Backpack']

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Sauce Labs Backpack']")));
		String fileName1 = "SelectProd.png";
		takeScreenshotImage(fileName1);

		// Validate login
		if (driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']")).isDisplayed()) {
			System.out.println("Product selected");
			test.log(LogStatus.PASS,
					"Product Selected successfully" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));
		} else {
			System.out.println("Failed to select Product");
			test.log(LogStatus.FAIL,
					"Failed to select Prodcut" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName1)
									.getPath()));

		}

		//// button[text()='Add to cart']
		test.log(LogStatus.INFO, "Add to Cart");
		driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Remove']")));
		String fileName2 = "AddProductTocart.png";
		takeScreenshotImage(fileName2);

		// Validate login
		if (driver.findElement(By.xpath("//button[text()='Remove']")).isDisplayed()) {
			System.out.println("Product is Added to cart");
			test.log(LogStatus.PASS,
					"Product is added to cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));
		} else {
			System.out.println("Failed to Add product to cart");
			test.log(LogStatus.FAIL,
					"Failed to Add product to cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName2)
									.getPath()));

		}

////span[@class="shopping_cart_badge"]	

		test.log(LogStatus.INFO, "Open the Cart");
		driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='checkout']")));
		String fileName3 = "OpenCart.png";
		takeScreenshotImage(fileName3);

		// Validate login
		if (driver.findElement(By.xpath("//button[@id='checkout']")).isDisplayed()) {
			System.out.println("View The Cart");
			test.log(LogStatus.PASS,
					"View the Cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));
		} else {
			System.out.println("Failed to Open The cart");
			test.log(LogStatus.FAIL,
					"Failed to Open The cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName3)
									.getPath()));

		}
		test.log(LogStatus.INFO, "Checkout The Cart");
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@name='cancel']")));
		String fileName4 = "Checkout.png";
		takeScreenshotImage(fileName4);

		// Validate login
		if (driver.findElement(By.xpath("//button[@name='cancel']")).isDisplayed()) {
			System.out.println("Clicked on Checkout");
			test.log(LogStatus.PASS,
					"Clicked on Checkout" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));
		} else {
			System.out.println("Failed to Clicked on Checkout");
			test.log(LogStatus.FAIL,
					"Failed to Open The cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName4)
									.getPath()));

		}
		test.log(LogStatus.INFO, "Cancel the Product");
		driver.findElement(By.xpath("//button[@name='cancel']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Remove']")));
		String fileName5 = "Cancel.png";
		takeScreenshotImage(fileName5);

		// Validate login
		if (driver.findElement(By.xpath("//button[text()='Remove']")).isDisplayed()) {
			System.out.println("Cancelled The product");
			driver.findElement(By.xpath("//button[text()='Remove']")).click();
			test.log(LogStatus.PASS,
					"Cancelled The product" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName5)
									.getPath()));
		} else {
			System.out.println("Failed to Cancelled The product");
			test.log(LogStatus.FAIL,
					"Failed to Open The cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName5)
									.getPath()));

		}

//Logout	

		test.log(LogStatus.INFO, "Logout");
		driver.findElement(By.xpath("//button[@id=\"react-burger-menu-btn\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[@id=\"logout_sidebar_link\"]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"login-button\"]")));
		String fileName6 = "Logout.png";
		takeScreenshotImage(fileName6);

		// Validate login
		if (driver.findElement(By.xpath("//input[@name=\"login-button\"]")).isDisplayed()) {
			System.out.println("Logout");
			// driver.findElement(By.xpath("//input[@name=\"login-button\"]")).click();
			test.log(LogStatus.PASS,
					"Logout" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName6)
									.getPath()));
		} else {
			System.out.println("Failed to Logout");
			test.log(LogStatus.FAIL,
					"Failed to Open The cart" + test.addScreenCapture(new File(
							System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/screenshots/" + fileName6)
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
