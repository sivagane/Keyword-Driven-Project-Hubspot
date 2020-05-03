package org.KDD.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.KDD.Base.Base;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeywordEngine {

	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	public Base base;
	public static Workbook book;
	public static Sheet sheet;

   String locatorName ;
   String locatorValue ;
   FileInputStream file ;
	public final String SHEET_PATH= "C:\\Users\\sivag\\eclipse-workspace\\KeywordDrivenFramework\\src\\test\\java\\org\\TestData\\Book1.xlsx";

	public void start_Execution(String sheetname) throws IOException, InterruptedException {

		try {
			file = new FileInputStream(SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sheet sheet = book.getSheet(sheetname);

		int k = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {
			String locatorName = sheet.getRow(i + 1).getCell(k + 1).toString().trim();//Trim is used to ignore space in Excel Sheet
			String locatorValue= sheet.getRow(i + 1).getCell(k + 2).toString().trim();
			
		   
			String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
			String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

			switch (action) {
			case "open browser":
				base = new Base();
				prop = base.init_Properties();
				if (value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver = base.init_Driver(prop.getProperty("browser"));
				} else {
					driver = base.init_Driver(value);
				}
				break;

			case "enter url":
				if (value.isEmpty() || value.equals("NA")) {
					driver.get(prop.getProperty("url"));
				} else {
					driver.get(value);
					Thread.sleep(10000);
				}
            break;
			case "quit":
				driver.quit();
            break;
			default:
				break;
			}

			switch (locatorName) {
			case "id":
				element = driver.findElement(By.id(locatorValue));
				if (action.equals("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				} else if (action.equalsIgnoreCase("click")) {
					element.click();
				}
				locatorValue = null;
				break;
				
			case "className":
				element = driver.findElement(By.className(locatorValue));
				if (action.equals("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				} else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else if
					(action.equalsIgnoreCase("isDisplayed")) {
					 element.isDisplayed();
				}else if(action.equalsIgnoreCase("getText")) {
					String text = element.getText();
					System.out.println(text);
				}
				locatorValue = null;
				break;
				
			case "xpath":
				element = driver.findElement(By.xpath(locatorValue));
				if (action.equals("sendkeys")) {
					element.clear();
					element.sendKeys(value);
				} else if(action.equalsIgnoreCase("click")) {
					element.click();
				}else if
					(action.equalsIgnoreCase("isDisplayed")) {
					 boolean b = element.isDisplayed();
					 System.out.println(b);
				}
				locatorValue = null;
				break;

			case "linkText":
				element = driver.findElement(By.linkText(locatorValue));
				element.click();
				locatorValue = null;
				break;
			default:
				break;
			}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
