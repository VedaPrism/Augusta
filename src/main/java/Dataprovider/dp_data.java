package Dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import Generic_Library.Utility;

public class dp_data {

	//Data Provider - Positive scenario 
	@DataProvider(name = "emandatedata")
	public static Iterator<Object[]> dp_emdata() throws Exception{
		
		return Utility.datafromexcel("Sheet1","ValidLogin");
	}
}
