package ExcelReadingOperations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Range;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.read.biff.PasswordException;

public class JxlReaderExample 
{
	WebDriver driver;
	String[][] data=null;
	@BeforeTest
	public void beforeTest()
	{
		driver=new ChromeDriver();
	}
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
	public String[][] readExcelData() throws IOException, BiffException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\srinivasan.a.mohan\\OneDrive - Accenture\\Documents\\TestData.xls");
		Workbook workbook=Workbook.getWorkbook(fis);
		Sheet sheet=workbook.getSheet(0);
		int column=sheet.getColumns();
		int row=sheet.getRows();
		String[][] excelData=new String[row-1][column];
		for(int i=1;i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				excelData[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return excelData;
	}
	@DataProvider(name="LoginData")
	public String[][] readData() throws BiffException, IOException
	{
		data=readExcelData();
		return data;
	}
	@Test(dataProvider = "LoginData")
	public void login(String name,String pwd)
	{
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/validateCredentials");
		WebElement userName=driver.findElement(By.id("txtUsername"));
		userName.sendKeys(name);

		WebElement password=driver.findElement(By.id("txtPassword"));
		password.sendKeys(pwd);

		WebElement login=driver.findElement(By.id("btnLogin"));
		login.click();
	}

}
