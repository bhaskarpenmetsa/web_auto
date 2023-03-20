package com.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.ImmutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class seleniumWithRemote {

	public static void main(String[] args) throws MalformedURLException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions(); 
		options.setExperimentalOption("debuggerAddress", "14.99.175.107:7004"); 
		WebDriver driver = new ChromeDriver(options); 
		driver.get("https://www.devopsrealtime.com/what-is-jenkins-csrf-projection-how-to-run-jenkins-job-remotely-with-webhook/");
		driver.quit();

	}

}
