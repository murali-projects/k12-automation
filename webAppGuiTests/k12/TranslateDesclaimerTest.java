package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.BasePageContainer;
import webPageContainers4Testing.JumpToPortalPageFromBasicSearch;
import webPageContainers4Testing.TranslationsDisclaimer;

public class TranslateDesclaimerTest extends BaseWebPageTest{

	public TranslationsDisclaimer translationsdisclaimer;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		translationsdisclaimer = new TranslationsDisclaimer();
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyTranslateOptionAvailable(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer
				.verifyTranslateOptionAvailable());

	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyTranslateOptionAvailable")
	public void verifyNewWindowOpening(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyNewWindowOpening());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyNewWindowOpening")
	public void verifyDisclaimerIsLink(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyDisclaimerIsLink());
	}

	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyDisclaimerIsLink")
	public void verifyBottomDisclaimer(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyBottomDisclaimer());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyDisclaimerIsLink")
	public void verifyDisclaimerOnTopOfWindow(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyDisclaimerOnTopOfWindow());
	}
	
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyDisclaimerOnTopOfWindow")
	public void verifyDisclaiamerLanguageIsEnglish(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyDisclaiamerLanguageIsEnglish());
	}

	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyDisclaiamerLanguageIsEnglish")
	public void verifyBottomDesclaimerDoesntHaveLink(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translationsdisclaimer.verifyBottomDesclaimerDoesntHaveLink());
	}
	
	
}
