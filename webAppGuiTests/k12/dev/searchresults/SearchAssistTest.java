package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.HomePage;

public class SearchAssistTest extends BaseDevWebPageTest {

	private HomePage homePage;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		homePage = new HomePage();
	}


	@Test
	public void searchForAirWillSuggestAutocompleteKeywords() throws Exception {
		Assert.assertFalse(homePage.verifyTextExists("air management"));
		homePage.typeSearchText("air");
		Assert.assertTrue(homePage.verifyTextExists("air management"));
		
	}
	
	@Test
	public void searchForWaterWillSuggestAutocompleteKeywords() throws Exception {
		Assert.assertFalse(homePage.verifyTextExists("water management"));
		homePage.typeSearchText("water");
		Assert.assertTrue(homePage.verifyTextExists("water management"));
	}
		
}
