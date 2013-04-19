package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.HomePage;

public class ViewingHomePageTest extends BaseWebPageTest {

	private HomePage homePage;
	
	@BeforeTest
	public void setUp() throws Exception{
		homePage = new HomePage();
	}
	
	@Test
	public void basicSearchBoxIsDisplayed() throws Exception {
		Assert.assertTrue(homePage.basicSearchBox().isPresent());
	}

	@Test
	public void basicSearchBIsDisplayed() throws Exception {
		Assert.assertTrue(homePage.basicSearchBox().isPresent());
	}

	 
	

}
