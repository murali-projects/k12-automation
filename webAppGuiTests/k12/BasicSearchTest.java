package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.HomePage;

public class BasicSearchTest extends BaseWebPageTest {
	private HomePage homePage;
	public BasicSearchTest() throws Exception {		
		super();		
	}
	
	@BeforeTest
	public void setUp() throws Exception{
		homePage = new HomePage();
	}	

	@Test
	public void basicSearchBoxIsDisplayedInHomePage() throws Exception {
		Assert.assertTrue(homePage.basicSearchBox().isPresent());
	}

	@Test
	public void basicSearchButtonIsDisplayedInHomePage() throws Exception {
		Assert.assertTrue(homePage.basicSearchButton().isPresent());
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("Obama");
		Thread.sleep(3000);
		Assert.assertEquals("search results: KEYWORD= Obama", homePage.getLableText("//div[@id='search_identifier']/p") );
	}
	
	@Test
	public void basicSearchBoxIsDisplayedInBasicSearchResultsPage() throws Exception {
		Assert.assertTrue(homePage.basicSearchBox().isPresent());
	}

	@Test
	public void basicSearchButtonIsDisplayedInBasicSearchResultsPage() throws Exception {
		Assert.assertTrue(homePage.basicSearchButton().isPresent());
	}

	@Test
	public void basicSearchBoxIsDisplayedWithSearchTerm() throws Exception {
		Assert.assertEquals(homePage.basicSearchBoxWithText(),"Search K12");
			}
	
	@Test
	public void resultsPageTitleDisplayed()throws Exception{
	Assert.assertTrue(homePage.verifyTitleContains("Think"));
	}
	
}
