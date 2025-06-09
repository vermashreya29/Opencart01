package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil 
{
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static  XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	public static String path;
	
	//Constructor
	ExcelUtil(String path)//directly pass path of excel
	{
		this.path=path;
				
	}
	
	public static int getRowCount(String sheetname) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		ws=wb.getSheet(sheetname);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public static int getCellCount(String sheetname,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	
	public static String getCellData(String sheetname,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		String data ;
		try {
		DataFormatter formatter=new DataFormatter(); 
		data =formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
	
		
		wb.close();
		fi.close();
		
		return data;
	}
	
	
	public static void SetCellData(String sheetname,int rownum,int colnum,String data) throws IOException
	{
		File xlfile=new File(path);
		
	//If file does not exists then create new file
		if(!xlfile.exists())
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);//write workbook in file
			
		}
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		
		//If Worksheet does not exists then create new Worksheet
		if(wb.getSheetIndex(sheetname)==-1)
		{
			wb.createSheet(sheetname);
			ws=wb.getSheet(sheetname);
		}
		
		
		//If row does not exists then create new row
		if(ws.getRow(rownum)==null)
	        ws.createRow(rownum);
			row=ws.getRow(rownum);
		
		//if cell does not exists then create cell
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		
		fo=new FileOutputStream(path);
		wb.write(fo);//write workbook in file
	
		wb.close();
		fi.close();
		fo.close();
	}
	
	public static void ColorCellGreen(String sheetname,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
	  style= wb.createCellStyle();

    // Set background color
    style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Apply the style to the cell
    cell.setCellStyle(style);
    
    fo=new FileOutputStream(path);
	wb.write(fo);//write workbook in file
	
	wb.close();
	fi.close();
	fo.close();
	
}
	
	
	
	
	public static void ColorCellRed(String sheetname,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook (fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		
	  style= wb.createCellStyle();

    // Set background color
    style.setFillForegroundColor(IndexedColors.RED.getIndex());
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

    // Apply the style to the cell
    cell.setCellStyle(style);
    
    fo=new FileOutputStream(path);
	wb.write(fo);//write workbook in file
	
	wb.close();
	fi.close();
	fo.close();
	
}
	
	
	
	
	
	
	
	
}
