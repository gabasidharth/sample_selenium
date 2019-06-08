package com.learning.sample_selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	Workbook gmailReadWorkbook = null;
	Sheet gmailWorksheet = null;
	String filePath;
	String fileName;
	String sheetName;

	public ReadExcel(String filePath, String fileName, String sheetName) throws IOException {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
		this.sheetName = sheetName;
		File file = new File(filePath + "\\" + fileName);
		FileInputStream fis = new FileInputStream(file);

		String fileExtension = fileName.substring(fileName.indexOf("."));
		if (fileExtension.equalsIgnoreCase(".xlsx")) {
			gmailReadWorkbook = new XSSFWorkbook(fis);
		} else if (fileExtension.equalsIgnoreCase(".xls")) {
			gmailReadWorkbook = new HSSFWorkbook(fis);
		}
		gmailWorksheet = gmailReadWorkbook.getSheet(sheetName);

	}

	

	public String readExcelVariable(String excelVariable) {

		//int rowCount = gmailWorksheet.getLastRowNum() - gmailWorksheet.getFirstRowNum();
		int colCount = gmailWorksheet.getRow(0).getLastCellNum();

		for (int i = 0; i < colCount; i++) {
			if (gmailWorksheet.getRow(0).getCell(i).getStringCellValue().equalsIgnoreCase(excelVariable)) {
				return gmailWorksheet.getRow(1).getCell(i).getStringCellValue();
			} 
		}
		return null;
	}
}
