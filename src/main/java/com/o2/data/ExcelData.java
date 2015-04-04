package com.o2.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @author sundhar jaganathan
 * This class is used to load an Microsoft Excel file and provide data as object 2D array or any specific cell request 
 */
public class ExcelData {
	
	Object objectarray[][];
	int totalrowcount, totalcolumncount = 0;
		
	public ExcelData(String filename, int sheetnumber) {
		try {
			  //Opening the file
			  File file = new File(filename);
		      FileInputStream inputstream = new FileInputStream(file);
		      XSSFWorkbook excelfile = new XSSFWorkbook(inputstream);
		      Sheet currentsheet = null;
		      if(file.isFile() && file.exists())
		         System.out.println("File opened successfully : " + filename);
		      else
		    	  System.out.println("File can't be opened : " + filename);
		      if (sheetnumber >= 0)
		    		currentsheet = excelfile.getSheetAt(sheetnumber);
		      totalrowcount = currentsheet.getLastRowNum()+1;
		      totalcolumncount = currentsheet.getRow(0).getLastCellNum();
		      objectarray = new Object[totalrowcount][totalcolumncount];
		      
		      for (Row row : currentsheet) {
		          for (Cell cell : row) {
		        	  switch (cell.getCellType()) {
		                case Cell.CELL_TYPE_STRING:
		                	objectarray[row.getRowNum()][cell.getColumnIndex()] 
		                			= cell.getRichStringCellValue().getString();
		                    break;
		                case Cell.CELL_TYPE_NUMERIC:
		                    if (DateUtil.isCellDateFormatted(cell)) {
		                    	objectarray[row.getRowNum()][cell.getColumnIndex()]
			                    = cell.getDateCellValue();
		                    } else {
		                    	objectarray[row.getRowNum()][cell.getColumnIndex()] 
		                    			= cell.getNumericCellValue();
		                    }
		                    break;
		        	  }
		        	  							
		          }
		        }
		      
		      excelfile.close();
		      
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Object[][] getObjectarray() {
		return objectarray;
	}
	
	//check whether any data exists in object array
	public boolean containsData() {
		if (objectarray != null) 
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	//Get total rows in the excel
	public int getTotalrowcount() {
		return totalrowcount;
	}
	
	//Get total columns in the excel
	public int getTotalcolumncount() {
		return totalcolumncount;
	}
	
	public Object getCellData(int row, int col) {
		return objectarray[row-1][col-1];
		
	}
	
}

