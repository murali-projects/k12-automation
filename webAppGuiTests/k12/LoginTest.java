package k12;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.LoginPage;

public class LoginTest extends BaseWebPageTest {
	 private LoginPage loginPage;
	 
	 @BeforeTest
	 public void setUp() throws Exception{
	  loginPage = new LoginPage();
	 } 
	 
	 @Parameters( {"userGroupName", "password"})
	 @Test
	 public void basicSearchBoxIsDisplayedInHomePage(String userGroupName, String password) throws Exception {
	  try{
	   loginPage.logInAs(userGroupName, password); 
	  }catch(Exception e){
	   e.printStackTrace();
	  }
	  
	 }
	}
