package com.ecom.testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.ecom.testBase.Initializer;

public class UtilsClass extends Initializer {

	public static FileInputStream xlfis = null;
	public static boolean runverify = false;
	public static boolean acceptNextAlert = false;

	// Passing Test data from Excel to Data Provider
	public static String[][] getExcelData(String Sheetname) throws IOException {

		// Xls File Initialization for Input
		xlfis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\Data\\TestData.xls");

		String[][] arrayExcelData = null;
		System.out.println("Getting input data from getexceldata");
		HSSFWorkbook wb = new HSSFWorkbook(xlfis);
		Sheet sh = wb.getSheet(Sheetname);

		int totalNoOfRows = sh.getLastRowNum() + 1;
		int totalNoOfCols = sh.getRow(1).getLastCellNum();

		System.out.println(totalNoOfRows);
		System.out.println(totalNoOfCols);

		// arrayExcelData = new String[totalNoOfRows - 1][totalNoOfCols];
		// to set row number for arrayexcel data when only have runmode yes
		int x = 0;
		for (int i = 1; i < totalNoOfRows; i++) {
			System.out.println(sh.getRow(i).getCell(8).getStringCellValue());

			if (sh.getRow(i).getCell(8).getStringCellValue().equalsIgnoreCase("Yes")) {
				x++;
			}

		}
		System.out.println("total x value is" + x);
		arrayExcelData = new String[x][totalNoOfCols];

		for (int i = 1, k = 0; i < totalNoOfRows; i++) {

			if (sh.getRow(i).getCell(8).getStringCellValue().equalsIgnoreCase("Yes")) {
				for (int j = 0; j < (totalNoOfCols); j++) {
					arrayExcelData[k][j] = sh.getRow(i).getCell(j).getStringCellValue();

				}
				k++;
			}

		}

		return arrayExcelData;
	}

	public String[] spltTestData(String inp) {

		String arr[] = inp.split("#");
		for (String s : arr) {
			System.out.println(" Input Test Data: " + s);
		}
		return arr;

	}

	public static void getSnapShot(String tcid) throws IOException {

		TakesScreenshot ss = ((TakesScreenshot) wd);
		File srcFile = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFileToDirectory(srcFile,
				new File(System.getProperty("user.dir") + "\\ScreenShots" + tcid + ".jpeg"));

	}
}
