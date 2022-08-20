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

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class JxlReader 
{
	public WebDriver driver;
	String[][] excelData;
	@BeforeTest
	public void beforeTest()
	{
		 driver=new ChromeDriver();
	}
	@AfterTest
	public void afterTest()
	{
		driver.close();
	}
	public String[][] readExcel() throws BiffException, IOException
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\srinivasan.a.mohan\\OneDrive - Accenture\\Documents\\TestData.xls");
		Workbook workbook=Workbook.getWorkbook(fis);
		Sheet sheet=workbook.getSheet(0);
		int row=sheet.getRows();
		int column=sheet.getColumns();
		String data[][]=new String[row][column];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				data[i][j]=sheet.getCell(j, i).getContents();
			}
		}
		return data;
	}
	@DataProvider(name="Dp")
	public String[][] dp() throws BiffException, IOException
	{
		excelData=readExcel();
		return excelData;
	}
	@Test(dataProvider = "Dp")
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
