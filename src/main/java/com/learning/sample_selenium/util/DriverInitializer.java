package com.learning.sample_selenium.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverInitializer {
	private WebDriver driver = null;

	public void setDriver(Driver driverName) {
		switch (driverName) {
		case chrome:
			System.setProperty("webdriver.chrome.driver",
					"G:\\Workspace\\sample-selenium\\Drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case firefox:
			System.setProperty("webdriver.gecko.driver",
					"G:\\Workspace\\sample-selenium\\Drivers\\geckodriver-v0.24.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		}

	}

	public WebDriver getDriver() {
		return driver;
	}

	public enum Driver {
		chrome, firefox
	}

}
