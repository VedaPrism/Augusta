package Scripts;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Library.Basefunctions;

import PageFactory.pf_homepage;

public class AE_script extends Basefunctions {
	
	Logger loginlog = Logger.getLogger(AE_script.class);

	//Execution of the workflow to test the functionality using dataprovider
	@Test(dataProvider = "emandatedata",dataProviderClass=Dataprovider.dp_data.class,enabled = true,priority=1,groups={"SMK","REG"})
	public void dataretrieval(Map h) throws Exception{
		
		String uid = h.get("Uname").toString();        //Excelvalues are converted as string
		String pas = h.get("Pwd").toString();          //Excelvalues are converted as string
		tc_id = h.get("TC_ID").toString();
		order=h.get("Order").toString();
		
		startTest = extentReports.startTest(tc_id+"_" + order + "_" + browser_type);
		pf_homepage pe = new pf_homepage(em);          //Calling the Page factory homepage class
		pe.searchitem(uid, pas,em);
	}
}
