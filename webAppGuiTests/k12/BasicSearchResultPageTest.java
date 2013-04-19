package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.BasicSearchResultPage;
import webPageContainers4Testing.HomePage;

public class BasicSearchResultPageTest extends BaseWebPageTest {
	protected BasicSearchResultPage basicSearchResultPage;
	protected HomePage homePage;
	
	@BeforeTest
	public void setUp() throws Exception{
		basicSearchResultPage = new BasicSearchResultPage();
		homePage =  new HomePage();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		homePage.doBasicSearchUsingSearchTerm("Obama");
	}

	@Test
	public void basicSearchBoxIsDisplayed() throws Exception {
		Assert.assertTrue(homePage.basicSearchBox().isPresent());
	}

	@Test
	public void basicSearchButtonIsDisplayed() throws Exception {
		Assert.assertTrue(homePage.basicSearchButton().isPresent());
	}

	@Test
	public void doCheckResultTextContainsBold() throws Exception {
		Assert.assertTrue(basicSearchResultPage.doCheckResultSearchWordBold());
	}

	@Test
	public void doCheckNextLinkIsDisplayed() throws Exception {
		Assert.assertTrue(basicSearchResultPage.getNextLink().isPresent());
	}

	@Test
	public void doCheckNextLinkIsWorking() throws Exception {
		Assert.assertTrue(basicSearchResultPage.doCheckNextLinkWorking());
	}

}
