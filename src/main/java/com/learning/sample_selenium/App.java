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

import com.learning.sample_selenium.util.DriverInitializer;
import com.learning.sample_selenium.util.ReadExcel;
import com.learning.sample_selenium.util.DriverInitializer.Driver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		DriverInitializer initDriver = new DriverInitializer();
		initDriver.setDriver(Driver.firefox);
		WebDriver driver = initDriver.getDriver();
		driver.manage().window().maximize();

		ReadExcel dataExcel;
		try {
			dataExcel = new ReadExcel("G:\\Workspace\\sample-selenium\\DataSheet", "DataSheet_Sample_Selenium.xlsx",
					"Sheet1");
			String usrName = dataExcel.readExcelVariable("Username");
			if (usrName != null && !usrName.isEmpty()) {
				driver.get("https://www.gmail.com");
				driver.findElement(By.id("identifierId")).sendKeys(usrName);
				driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				WebDriverWait ewait = new WebDriverWait(driver, 10);
				String pass = dataExcel.readExcelVariable("Password");
				if (pass != null && !pass.isEmpty()) {
					ewait.until(ExpectedConditions.elementToBeClickable(By.name("password"))).sendKeys(pass);
					driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
