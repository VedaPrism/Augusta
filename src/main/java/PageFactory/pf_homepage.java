package PageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import Generic_Library.Basefunctions;
import Generic_Library.Utility;

public class pf_homepage extends pf_genericmethods{
	
	Basefunctions b = new Basefunctions();
//	Initializing the webelements
	
	@FindBy(how = How.XPATH, using = "//div[@id = 'nav-user-account']/div[1]/div/span[1]/a[1]") WebElement signinicon;
	@FindBy(how = How.CLASS_NAME, using = "sign-btn") WebElement loginbuttonicon;
	@FindBy(how = How.ID, using = "fm-login-id") WebElement userid;
	@FindBy(how = How.XPATH, using = "//div[@id='login-content']/dl[2]/dd/div/input") WebElement password;
	@FindBy(how = How.NAME, using = "submit-btn") WebElement loginsubmit;
	@FindBy(how = How.ID, using = "search-key") WebElement searchkey;
	@FindBy(how = How.ID, using = "nc_1_n1z") WebElement validatekey;
	@FindBy(how = How.ID, using = "submitBtn") WebElement submitbutton;
	@FindAll({@FindBy(how = How.CLASS_NAME, using = "ui-window-content")}) public List<WebElement> popup;
	@FindBy(how = How.XPATH, using = "//*[@class = 'ui-window-bd']/div/a") WebElement popupclose;
	@FindAll({@FindBy(how = How.XPATH, using = "//div[@id = 'hs-list-items']/ul/li")}) public List<WebElement> searchitems;
	@FindBy(how = How.CLASS_NAME, using = "search-button") WebElement searchicon;
	
	String product = "//div[@id = 'hs-list-items']/ul/li[#DELIM#]";
	
	@FindBy(how = How.XPATH, using = "//span[@class='product-action-main']/a[2]") WebElement addtocart;
	@FindBy(how = How.ID, using = "alibaba-login-box") WebElement frameelement;
	
//	Initializing the Page factory elements with WebDriver
	
	public pf_homepage(WebDriver driver){
		
		PageFactory.initElements(driver, this);
	}
	
//	Workflow creation using the webelements
	
	public void searchitem(String uid, String pas, WebDriver driver) throws Exception{
		
		List<WebElement> pp = popup;
		if(pp.size() > 0){            //Checking for the advertisement popup if any
		cl_click(popupclose);
		}
		Thread.sleep(3000);
		cl_click(signinicon);
		driver.switchTo().frame(frameelement);   // Switching to iframe
		
		cl_entertext(userid, uid);
		cl_entertext(password, pas);
		cl_click(loginsubmit);
		driver.switchTo().defaultContent();      // Switching back to parent page
		Thread.sleep(3000);
		
		if(pp.size() > 0){             //Checking for the advertisement popup if any
			
			cl_click(popupclose);
			}
		
		cl_entertext(searchkey, Utility.propertyvalue("searchkey"));      // Searching a product 
		Thread.sleep(2000);
		cl_click(searchicon);
		Thread.sleep(3000);
		int sis = searchitems.size();
		System.out.println(sis);
		for(int i =1; i<=sis; i++){             //Iterating the total no of results
			
		String text = driver.findElement(By.xpath(product.replace("#DELIM#", String.valueOf(i)))).getText();
		if(text.contains(Utility.propertyvalue("itemtext"))){       // Checking for the Required product by Iterating the results
			
			Thread.sleep(2000);
			cl_click(driver.findElement(By.xpath(product.replace("#DELIM#", String.valueOf(i)))));
			System.out.println("Search Product Found");
			Thread.sleep(2000);
			String wh = driver.getWindowHandle();
			ArrayList<String> als = new ArrayList<String>(driver.getWindowHandles());
			if(!als.isEmpty()){
			driver.switchTo().window(als.get(1));
			cl_click(addtocart);
			}
			break;
		}
		
		}
		
		
	}

}

