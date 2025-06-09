package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	@DataProvider(name="LoginData")
	public String[][] getdata() throws IOException
	{
		String path="./testData/opencartTestData.xlsx";//taking xl file from test Data folder
		ExcelUtil xlutil=new ExcelUtil(path);
		int rowcount=xlutil.getRowCount("Sheet1");
		int cellcount=xlutil.getCellCount("Sheet1", 1);//sheet name and row no
		String logindata[][]=new String[rowcount][cellcount];
		for(int i=1;i<=rowcount;i++)
		{
			for(int j=0;j<cellcount;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
				
	}
}
