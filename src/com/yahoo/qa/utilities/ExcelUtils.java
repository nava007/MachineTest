package com.yahoo.qa.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yahoo.qa.base.BaseClass;

public class ExcelUtils extends BaseClass {

	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	
	
	public ExcelUtils(String path, int sheetindex){

		try {
			workbook =new XSSFWorkbook(path);
			sheet= workbook.getSheetAt(sheetindex);
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	public int getrowcount(){

		int row=sheet.getPhysicalNumberOfRows(); 
		return row;

	}

	public int getcolumncount() {	

		int col=sheet.getRow(0).getPhysicalNumberOfCells();
		return col;
	}

	public String getcellstringdata(int rowNum, int colNum) {

		String str=sheet.getRow(rowNum).getCell(colNum).getStringCellValue();

		return str;
	}

	public static Object[][] excelread(String pathname,int sheetno) {

		ExcelUtils excel=new ExcelUtils(pathname,sheetno);
		int rowNum=excel.getrowcount();
		
		int colNum=excel.getcolumncount();
		System.out.println(rowNum);
		System.out.println(colNum);
		Object ob[][]=new Object[rowNum-1][colNum];

		for(int i=1;i<rowNum;i++){
			for(int j=0;j<colNum;j++){

				String cellData=excel.getcellstringdata(i, j);
				ob[i-1][j]=cellData;
				System.out.println(ob[i-1][j]);
			}
		}
		return ob;

	}


	public static String parentwindow(){

		String parent=driver.getWindowHandle();
		return parent;
	}

	public static String childwindow(){

		String child=null;
		Set<String> s1=driver.getWindowHandles();
		Iterator<String> i1=s1.iterator();

		while(i1.hasNext()){

			child=i1.next();
		}
		return child;

	}
	


}












