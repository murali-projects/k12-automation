package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegratePrimarySourcesDisplayGroup;

public class IntegratePrimarySourcesDisplayGroupTest extends BaseWebPageTest{

	private IntegratePrimarySourcesDisplayGroup primarySourcesDisplayGroup;
	
	private String searchTerm;
	
	@Parameters( { "searchTerm"})
	@BeforeTest 
	public void setUp(String searchTerm) throws Exception{
		primarySourcesDisplayGroup = new IntegratePrimarySourcesDisplayGroup();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifySearchTermInMsg() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.verifySearchTermInMsgDisplayed(searchTerm));
	}
	
	@Test(dependsOnMethods = { "verifySearchTermInMsg" })
	public void verifyPrimarySourcesDisplayGroupIsDisplayed() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "verifyPrimarySourcesDisplayGroupIsDisplayed" })
	public void verifyCountIsDisplayed() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.validateCount(searchTerm));
	}
	
	@Test(dependsOnMethods = { "verifyPrimarySourcesDisplayGroupIsDisplayed" })
	public void verifyViewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyLinkCountInFirstPage() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.verifyLinkCountInFirstPage());
	}
	
	@Test(dependsOnMethods = { "verifyLinkCountInFirstPage" })
	public void verifyViewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(primarySourcesDisplayGroup.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllLinkIsWorking" })
	public void verifyViewAllLinkIsNotDisplayed() throws Exception {
		
		//Search term should fetch less than 3 results
		doBasicSearchUsingSearchTerm("obama");
		Assert.assertFalse(primarySourcesDisplayGroup.checkViewAllLinkIsPresent());
	}
}
