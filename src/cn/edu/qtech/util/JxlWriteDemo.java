package cn.edu.qtech.util;

import java.awt.Label;
import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class JxlWriteDemo {
	public JxlWriteDemo(String fileName,Object[][] data)throws IOException, WriteException{
		File xlsFile = new File("xls//"+fileName+".xls");
	      // 创建一个工作簿
	      WritableWorkbook workbook = Workbook.createWorkbook(xlsFile);
	      // 创建一个工作表
	      WritableSheet sheet = workbook.createSheet("sheet1", 0);
	      for (int row = 0; row < data.length; row++)
	      {
	         for (int col = 0; col < data[0].length; col++)
	         {
	            // 向工作表中添加数据
	            sheet.addCell(new jxl.write.Label(col, row,(String) data[row][col]));
	         }
	      }
	      workbook.write();
	      workbook.close();
	}
}
