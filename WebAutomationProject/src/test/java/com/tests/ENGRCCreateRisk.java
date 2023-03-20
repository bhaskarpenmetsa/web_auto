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
import org.apache.commons.lang3.RandomStringUtils;
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


public class ENGRCCreateRisk {
	public WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;
	public static String Riskcat="CreditRisk";
	public static String RiskName;
	public static String Rname;

	
	
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
				System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports/ENGRCReport.html", true);
		extent.loadConfig(new File("./extent-config.xml"));
		extent.addSystemInfo("Selenium", "3.12").addSystemInfo("Environment", "Production");
		//System.setProperty("webdriver.chrome.driver","C:\\Users\\1002162\\eclipse-workspace\\ICONProduct\\WebAutomationTesting\\drivers\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*************************");
		driver.get("http://115.112.185.58:3200/");
		
	}


	@Test(priority=1, enabled=true)
	public void ENGRCCreateAndDeleteRisk()
			throws  IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"NGRC-Create Risk and Delete Risk.");

			test.log(LogStatus.INFO, "Login into application");
			
			WebDriverWait wait = new WebDriverWait(driver,120);
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			// Login into application
			driver.findElement(By.xpath("//input[@formcontrolname='username']")).click();
			driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("Gillian");
			driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("123");
			driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
			//Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/p[text()='Completed']")));
			String fileName = "Login.png";
			takeScreenshotImage(fileName);

			// Validate login
			if (driver.findElement(By.xpath("//div/p[text()='Completed']")).isDisplayed()) {
				System.out.println(
						"Entered username, password and clicked login button.");
				test.log(LogStatus.PASS,
						"Entered username, password and clicked login button.."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName).getPath()));
		} else {
				System.out.println(
						"Entered username, password and clicked login button. User Failed to login.");
				test.log(LogStatus.FAIL,
						"Entered username, password and clicked login button. User User Failed to login"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName).getPath()));
		
			}
			
			
			Thread.sleep(6000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-sidebar//div[@class='menu-list']/ul/li[2]")));
			
			
			test.log(LogStatus.INFO, "Select Risk&Controll Management");

		/*
			
			WebElement ele = driver.findElement(By.xpath("//app-sidebar//div[@class='menu-list']/ul/li[2]"));

			//Creating object of an Actions class
			Actions action = new Actions(driver);

			//Performing the mouse hover action on the target element.
			action.moveToElement(ele).perform();
			*/
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-sidebar//div[@class='menu-list']/ul/li[2]/a")));
			driver.findElement(By.xpath("//app-sidebar//div[@class='menu-list']/ul/li[2]/a")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//span[text()='Risk & Control Management']")).click();
			
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Risk List']")));
			//Thread.sleep(8000);
			String fileName2 = "Tabs.png";
			takeScreenshotImage(fileName2);
//app-sidebar//div[@class='menu-list']/ul/li[2]
			// Validate login
			if (driver.findElement(By.xpath("//span[text()='Risk List']")).isDisplayed()) {
				System.out.println(
						"Click on Risk & Control Management .");
				test.log(LogStatus.PASS,
						"Click on Risk & Control Management ."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName2).getPath()));
		} else {
				System.out.println(
						"Failed to click on Click on Risk & Control Management.");
				test.log(LogStatus.FAIL,
						"Failed to click on Click on Risk & Control Management."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName2).getPath()));
		
			}
			
			
			
			
			
			
			
			
			//Thread.sleep(8000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Risk List']")));
			
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Risk List']")));
			
			test.log(LogStatus.INFO, "Navigate  Risk&Controll Management Tabs");
			
			driver.findElement(By.xpath("//app-dashboard-container//tabset/ul[@role=\"tablist\"]//span[text()='Risk List']")).click();
			
			//Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Control List']")));
			driver.findElement(By.xpath("//app-dashboard-container//tabset/ul[@role=\"tablist\"]//span[text()='Control List']")).click();
			
			//Thread.sleep(1000);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Risk Control Matrix']")));
			driver.findElement(By.xpath("//app-dashboard-container//tabset/ul[@role=\"tablist\"]//span[text()='Risk Control Matrix']")).click();
			
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Control Execution Workflow']")));
			//Thread.sleep(1000);
            driver.findElement(By.xpath("//app-dashboard-container//tabset/ul[@role=\"tablist\"]//span[text()='Control Execution Workflow']")).click();
			
			//Thread.sleep(1000);
            
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-dashboard-container//tabset/ul[@role='tablist']//span[text()='Risk List']")));
			
	        driver.findElement(By.xpath("//app-dashboard-container//tabset/ul[@role=\"tablist\"]//span[text()='Risk List']")).click();
			
			//Thread.sleep(1000);
			//code to create risk

			//Thread.sleep(3000);
	        
	        
	        
			String fileName3 = "Tabs.png";
			takeScreenshotImage(fileName3);

			// Validate login
			if (driver.findElement(By.xpath("//table//th[text()='Risk Area']")).isDisplayed()) {
				System.out.println(
						"Navigate Risk & Control Management Tabs.");
				test.log(LogStatus.PASS,
						"Navigated Risk & Control Management Tabs"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName3).getPath()));
		} else {
				System.out.println(
						"Failed to Navigate Risk & Control Management Tab.");
				test.log(LogStatus.FAIL,
						"Failed to Navigate Risk & Control Management Tab."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName3).getPath()));
		
			}
			
			
			  Rname=genRName();
			   System.out.println("Generated Risk Name"+Rname);
			test.log(LogStatus.INFO, "Added Risk");
			
			
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div//a//img[@alt='Add'])[1]")));
		
			driver.findElement(By.xpath("(//div//a//img[@alt='Add'])[1]")).click();
			//Thread.sleep(1000);
			 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@formcontrolname='riskName']")));
			
			driver.findElement(By.xpath("//input[@formcontrolname='riskName']")).sendKeys(Rname);
			
			Select drpCountry = new Select(driver.findElement(By.xpath("//select[@formcontrolname='riskResponse']")));
			drpCountry.selectByVisibleText("Mitigate");
			
			Thread.sleep(2000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@formcontrolname='assessmentModel']")));
			
			Select assest = new Select(driver.findElement(By.xpath("//select[@formcontrolname='assessmentModel']")));
			assest.selectByVisibleText("Quantitative");
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tab//button[text()='Save'])[1]")));
			driver.findElement(By.xpath("(//tab//button[text()='Save'])[1]")).click();
			Thread.sleep(1000);
			
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@formcontrolname='company']")));
			Select company = new Select(driver.findElement(By.xpath("//select[@formcontrolname='company']")));
			company.selectByVisibleText("ABC");
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tab//button[text()='Save'])[2]")));
			driver.findElement(By.xpath("(//tab//button[text()='Save'])[2]")).click();
			
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//tab//button[text()='Save'])[1]")));
			driver.findElement(By.xpath("(//tab//button[text()='Save'])[1]")).click();
			Thread.sleep(1000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//app-confirm-popup//button[text()='Confirm'])[2]")));
			driver.findElement(By.xpath("(//app-confirm-popup//button[text()='Confirm'])[2]")).click();
			Thread.sleep(3000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-controls='riskListTable']")));
			driver.findElement(By.xpath("//input[@aria-controls='riskListTable']")).click();
			driver.findElement(By.xpath("//input[@aria-controls='riskListTable']")).sendKeys(Rname);
		
			Thread.sleep(3000);
			String fileName4 = "Risk Created.png";
			takeScreenshotImage(fileName4);

			// Validate login
			if (driver.findElement(By.xpath("(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a")).isDisplayed()) {
				System.out.println(
						"Risk Createds.");
				test.log(LogStatus.PASS,
						"Created Risk Successfullys"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName4).getPath()));
		} else {
				System.out.println(
						"Failed to Create Risk.");
				test.log(LogStatus.FAIL,
						"Failed to Create Risk."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName4).getPath()));
		
			}
			
			//delete risk
			
			Thread.sleep(3000);
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@data-toggle='dropdown']//span[3]")));
			test.log(LogStatus.INFO, "Deleted Created Risk");
			
			driver.findElement(By.xpath("//a[@data-toggle='dropdown']//span[3]")).click();
			Thread.sleep(3000);
			
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@x-placement='bottom-start']//a//span[text()='Delete']")));
			
			driver.findElement(By.xpath("//div[@x-placement='bottom-start']//a//span[text()='Delete']")).click();
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//app-confirm-popup//button[text()='Confirm'])[2]")));
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//app-confirm-popup//button[text()='Confirm'])[2]")).click();
			Thread.sleep(2000);
			//System.out.println("before condition");
			//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@aria-controls='riskListTable']")));
			//Thread.sleep(2000);
			//System.out.println("after condition");
			driver.findElement(By.xpath("//input[@aria-controls='riskListTable']")).click();
			driver.findElement(By.xpath("//input[@aria-controls='riskListTable']")).clear();
			driver.findElement(By.xpath("//input[@aria-controls='riskListTable']")).sendKeys(Rname);
			
			int xpathcount=driver.findElements(By.xpath("(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a")).size();
			
			//System.out.println(xpathcount);
			String fileName5 = "Deleted Created Risk.png";
			takeScreenshotImage(fileName5);
			Thread.sleep(3000);
			// Validate login
			
			/*
			if (driver.findElement(By.xpath("(//table[@id='riskListTable']//tr[@class='odd']/td)[3]/a")).isDisplayed()) {
				System.out.println(
						"Failed to delete created risk");
				test.log(LogStatus.FAIL,
						"Failed to delete Created Risk"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName5).getPath()));
		} else {
				System.out.println(
						" deleted created risk");
				test.log(LogStatus.PASS,
						"deleted created risk"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName5).getPath()));
		
			}
			
			*/
			if (xpathcount!=0) {
				System.out.println(
						"Failed to delete created risk");
				test.log(LogStatus.FAIL,
						"Failed to delete Created Risk"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName5).getPath()));
		} else {
				System.out.println(
						" deleted created risk");
				test.log(LogStatus.PASS,
						"deleted created risk"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName5).getPath()));
		
			}
			
			driver.findElement(By.xpath("(//ul[@class=\"navbar-nav\"]//a[@id='navbarDropdown'])[2]")).click();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			
		extent.endTest(test);
		extent.flush();
		extent.close();

		Thread.sleep(2000);
		driver.close();

	}
	public static String genRName() {
		
		   int length = 4;
		    
		    boolean useLetters = false;
		    boolean useNumbers = true;
		    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	        RiskName=Riskcat+"_"+generatedString;
		    System.out.println(RiskName);
			return RiskName;
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

			FileUtils.copyFile(actualFile,
					new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
							+ fileNameWithExtention));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
