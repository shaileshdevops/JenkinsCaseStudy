package com.renju.training.jenkins.caseStudy.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest {
	
WebDriver driver;
	
	@BeforeClass
	public void startBrowser() {
		System.setProperty("webdriver.chrome.driver", "F:\\Downloads\\chromedriver_win32\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
	}
	
	@Test
	public void validateAppLogin() throws Exception {
		System.out.println("Opening Browser");
		driver.get("http://localhost:9090/login");
		System.out.println("Entering username");
		Thread.sleep(3000);
		driver.findElement(By.name("username")).sendKeys("renju");
		driver.findElement(By.name("password")).sendKeys("admin");
		driver.findElement(By.name("submit")).click();
		Thread.sleep(5000);
		boolean textFound = driver.getPageSource().contains("Welcome renju");
		AssertJUnit.assertTrue(textFound);
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
