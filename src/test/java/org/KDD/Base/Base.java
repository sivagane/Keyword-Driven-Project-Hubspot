package org.KDD.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public FileInputStream file;
	
	public WebDriver init_Driver(String browsername) {
		if(browsername.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sivag\\eclipse-workspace\\KeywordDrivenFramework\\div\\chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")) {
				ChromeOptions options= new ChromeOptions();
				options.addArguments("--headless");
				driver=new ChromeDriver(options);
			}
			else {
				driver= new ChromeDriver();
			}
		}
		return driver;
	}
	
	public Properties init_Properties() throws IOException {
		prop= new Properties();
		
		try {
			file = new FileInputStream("C:\\Users\\sivag\\eclipse-workspace\\KeywordDrivenFramework\\src\\test\\java\\org\\config\\cofig.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 prop.load(file);
		 return prop;
	}
}
