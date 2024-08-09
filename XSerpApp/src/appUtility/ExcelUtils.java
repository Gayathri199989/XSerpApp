package appUtility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.PageFactory;

import au.com.bytecode.opencsv.CSVReader;

import org.apache.poi.ss.usermodel.Cell;
public class ExcelUtils {
	
//	public static String[] ReadCSV() throws Exception{
//		// This will load csv file 
//		 CSVReader reader = new CSVReader(new FileReader("E:\\PartyImport.csv"));
//		 
//		 // this will load content into list
//		  List<String[]> li=reader.readAll();
//		  String[] str = null; 
//		 // create Iterator reference
//		  Iterator<String[]>i1= li.iterator();
//		    
//		 // Iterate all values 
//		 while(i1.hasNext()){
//		     
//		 str=i1.next();
//		  for(int i=0;i<str.length;i++)
//		{
//		
//		}    
//		    
//		}
//		 return str;
//	 
//	}
//	
	public static HashMap<String,String> storeValues = new HashMap();
	
	public static List<HashMap<String,String>> data(String filename,String sheetName) throws Exception
	{


		List<HashMap<String,String>> mydata = new ArrayList<>();
		try
		{
	
			FileInputStream fs = new FileInputStream(filename);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
	
			for(int i=1;i<sheet.getPhysicalNumberOfRows();i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String,String> currentHash = new HashMap<String,String>();
				for(int j=0;j<currentRow.getPhysicalNumberOfCells();j++)
				{
					
					Cell currentCell = currentRow.getCell(j);
					currentCell = currentRow.getCell(j, Row.CREATE_NULL_AS_BLANK);
					switch (currentCell.getCellType())
					{
					case Cell.CELL_TYPE_STRING:
	//					System.out.print(currentCell.getStringCellValue() + "\t");
	
						currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
						break;
					}
	
				}
				mydata.add(currentHash);
			}
	
			fs.close();
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		
		return mydata;
	
	}
/*
//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

public static void setExcelFile(String Path,String SheetName) throws Exception {

		try {

			// Open the Excel file

		FileInputStream ExcelFile = new FileInputStream(Path);

		// Access the required test data sheet

		ExcelWBook = new XSSFWorkbook(ExcelFile);

		ExcelWSheet = ExcelWBook.getSheet(SheetName);

		} catch (Exception e){

			throw (e);

		}

}

//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

public static String getCellData(int RowNum, int ColNum) throws Exception{

		try{

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

			}catch (Exception e){

			return"";

			}

}

//This method is to write in the Excel cell, Row num and Col num are the parameters

public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{
	ReadPropertyFile property = new ReadPropertyFile();

		try{

			Row  = ExcelWSheet.getRow(RowNum);

		Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);

		if (Cell == null) {

			Cell = Row.createCell(ColNum);

			Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

// Constant variables Test Data path and Test Data file name

				FileOutputStream fileOut = new FileOutputStream(property.getTestDataPath() + "TestData.xlxs");

				ExcelWBook.write(fileOut);

				fileOut.flush();

				fileOut.close();

			}catch(Exception e){

				throw (e);

		}

	}*/
}
