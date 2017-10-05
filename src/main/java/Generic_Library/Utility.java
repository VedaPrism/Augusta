package Generic_Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Utility {
	
	public static Iterator<Object[]> datafromexcel(String sheetname, String scriptname) throws Exception{       //Retrieving the required values from Excel

	ReadExceldata re = new ReadExceldata(System.getProperty("user.dir")+ "//src//main//resources//AElogincredentials.xlsx");
	
	int rowcount = re.rc(sheetname);
	int colcount = re.cc(sheetname);
	
//	Initializing the object array list
	
	List<Object[]> lt = new ArrayList<Object[]>();
	
	for(int i=1;i<=rowcount;i++){      //Iterating the row values
		
		String flag = re.xcelreadvalue(i,1, sheetname);
		String sn = re.xcelreadvalue(i,3, sheetname);
		
		if(flag.equals("Y") && sn.equals(scriptname)){     
			
			Map<String, String> hp = new HashMap<String,String>();                // Creation of Map for storing values
			Object[] ob = new Object[1];                                          // Creation of Object array to store the Map 
			
			for(int j=0;j<colcount;j++){
				
				String Key = re.xcelreadvalue(0,j, sheetname);                    //Getting the Excel Headings as Key
				String Value = re.xcelreadvalue(i,j, sheetname);                  //Getting the Row values as Value
				hp.put(Key, Value);                                               //Key & value stored in Map 
			}
			ob[0]=hp;                                                             //Map stored in Object array
			lt.add(ob);                                                           //Object array stored in List
		}
		}
		return lt.iterator();
	}
	
	
//	Getting the property value
	
	public static String propertyvalue(String key) throws Exception{
		
		FileInputStream f = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//Augustaprop.properties");
		Properties ps = new Properties();
		ps.load(f);
		return ps.getProperty(key);
	}
}
