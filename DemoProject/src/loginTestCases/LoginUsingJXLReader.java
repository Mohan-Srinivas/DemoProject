package loginTestCases;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginUsingJXLReader 
{
	String[][] data=null;
	WebDriver driver;
	@DataProvider(name="LoginData")
	public String[][] loginData() throws BiffException, IOException
	{
		data=getExcelData();
		return data;
	}
	public String[][] getExcelData() throws BiffException, IOException
	{
		FileInputStream file=new FileInputStream("C:\\Users\\srinivasan.a.mohan\\Documents\\TestData.xls");
		Workbook workbook=Workbook.getWorkbook(file);
		Sheet sheet=workbook.getSheet(0);
		int columns=sheet.getColumns();
		int rows=sheet.getRows();
		String[][] excelData=new String[rows-1][columns];
		for(int i=1;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				excelData[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return excelData;
	}
	@BeforeTest
	public void beforeTest()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\srinivasan.a.mohan\\Downloads\\SeleniumDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
	}
	@Test(dataProvider = "LoginData")
	public void loginWithDataProvider(String uname,String pword)
	{
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
		WebElement userName=driver.findElement(By.id("txtUsername"));
		userName.sendKeys(uname);

		WebElement password=driver.findElement(By.id("txtPassword"));
		password.sendKeys(pword);

		WebElement login=driver.findElement(By.id("btnLogin"));
		login.click();
	}
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
}
