package com.learning.sample_selenium;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.learning.sample_selenium.util.DriverInitializer;
import com.learning.sample_selenium.util.ReadExcel;
import com.learning.sample_selenium.util.RetryAnalyzer;
import com.learning.sample_selenium.util.DriverInitializer.Driver;

/**
 * Hello world!
 *
 */

public class App {
	
	public static void main(String[] args) {

		WebDriver driver;

//	@BeforeTest
//	public void driverInitilizer() {
		DriverInitializer initDriver = new DriverInitializer();
		initDriver.setDriver(Driver.firefox);
		driver = initDriver.getDriver();
		driver.manage().window().maximize();
		// }

		// @Test(priority = 0)
		// public void testGmailLogin() {
		ReadExcel dataExcel;
		try {
			dataExcel = new ReadExcel("G:\\Workspace\\sample-selenium\\DataSheet", "DataSheet_Sample_Selenium.xlsx",
					"Sheet1");
			String usrName = dataExcel.readExcelVariable("Username");
			if (usrName != null && !usrName.isEmpty()) {
				driver.get(
						"https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
				Assert.assertEquals(true, driver.findElement(By.id("identifierId")).isDisplayed());
				driver.findElement(By.id("identifierId")).sendKeys(usrName);
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				WebDriverWait ewait = new WebDriverWait(driver, 20);
				String pass = dataExcel.readExcelVariable("Password");
				if (pass != null && !pass.isEmpty()) {
					ewait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys(pass);
					driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				}
				// Assert.assertEquals(true,
				// driver.findElement(By.xpath("//a[@title=\"Inbox\"]")).isDisplayed());
				Assert.assertEquals(true, driver.findElement(By.xpath("//span[@class=\"gb_xa gbii\"]")).isDisplayed());
				WebElement accsetting = ewait
						.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"gb_xa gbii\"]")));
				accsetting.click();
				ewait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign out')]")))
						.click();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// }

//	@Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
//	public void failedtestGmailLogin() {
//		ReadExcel dataExcel;
//		try {
//			dataExcel = new ReadExcel("G:\\Workspace\\sample-selenium\\DataSheet", "DataSheet_Sample_Selenium.xlsx",
//					"Sheet1");
//			String usrName = dataExcel.readExcelVariable("Username");
//			if (usrName != null && !usrName.isEmpty()) {
//				driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
//				Assert.assertEquals(true, driver.findElement(By.id("identifierId")).isDisplayed());
//				driver.findElement(By.id("identifierId")).sendKeys("***");
//				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
//				WebDriverWait ewait = new WebDriverWait(driver, 15);
//				String pass = dataExcel.readExcelVariable("Password");
//				if (pass != null && !pass.isEmpty()) {
//					ewait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys(pass);
//					driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
//				}
//				Assert.assertEquals(true, driver.findElement(By.xpath("//a[@title=\"Inbox\"]")).isDisplayed());
//				Assert.assertEquals(true, driver.findElement(By.xpath("//span[@class=\"gb_xa gbii\"]")).isDisplayed());
//				WebElement accsetting = ewait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class=\"gb_xa gbii\"]")));
//				accsetting.click();
//				ewait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Sign out')]"))).click();
//			}
//			
//			
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@AfterTest
//	public void cleanup() {
//		driver.quit();
	}
}
