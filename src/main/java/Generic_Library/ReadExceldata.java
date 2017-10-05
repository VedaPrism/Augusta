package Generic_Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExceldata {
	
	FileInputStream fi;
	XSSFWorkbook wb;
	
//	Initializing the Excel
	
	public ReadExceldata(String fpath) throws Exception{
	
		fi = new FileInputStream(fpath);
		wb = new XSSFWorkbook(fi);
	}
	
//	Getting the rowcount
	
	public int rc(String sheetname){
		
		XSSFSheet xh = wb.getSheet(sheetname);
		return xh.getLastRowNum();
	}

//	Getting the columncount
	
	public int cc(String sheetname){
		
		XSSFSheet xh = wb.getSheet(sheetname);
		return xh.getRow(0).getLastCellNum();
	}
	
//	Getting the values of cell
	
	public String xcelreadvalue(int ri, int ci, String sheetname){
		
		XSSFSheet xh = wb.getSheet(sheetname);
		XSSFCell cl = xh.getRow(ri).getCell(ci);
		String cv = null;
		
		if(cl.getCellType() == cl.CELL_TYPE_STRING){
			
			cv = cl.getStringCellValue();
		}else if (cl.getCellType() == cl.CELL_TYPE_NUMERIC){
			
			cv = String.valueOf(cl.getNumericCellValue());
		}else if (cl.getCellType() == cl.CELL_TYPE_BLANK && cl.getCellType()== cl.CELL_TYPE_ERROR){
			cv ="";
		}
		return cv;
	}
	
//	Writing the values to excel
	
	public void xcelwritevalue(int ri,int ci,String sheetname,String input){
		
		XSSFSheet xh = wb.getSheet(sheetname);
		xh.getRow(ri).getCell(ci).setCellValue(input);
	}
	
//	Save & close the excel
	
	public void saveclose(String fpath) throws Exception{
		
		FileOutputStream fs = new FileOutputStream(fpath);
		wb.write(fs);
		fs.close();
		fi.close();
	}
}
