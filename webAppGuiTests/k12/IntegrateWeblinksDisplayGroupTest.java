package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateWeblinksDisplayGroup;


public class IntegrateWeblinksDisplayGroupTest extends BaseWebPageTest{

	private IntegrateWeblinksDisplayGroup weblinksDisplayGroup;
	
	private String searchTerm;
	
	@Parameters( { "searchTerm"})
	@BeforeTest 
	public void setUp(String searchTerm) throws Exception{
		weblinksDisplayGroup = new IntegrateWeblinksDisplayGroup();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyWeblinksDisplayGroupIsDisplayed() throws Exception {
		Assert.assertTrue(weblinksDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "verifyWeblinksDisplayGroupIsDisplayed" })
	public void verifyCountIsDisplayed() throws Exception {
		Assert.assertTrue(weblinksDisplayGroup.validateCount(searchTerm));
	}
	
	@Test(dependsOnMethods = { "verifyWeblinksDisplayGroupIsDisplayed" })
	public void verifyViewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(weblinksDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyLinkCountInFirstPage() throws Exception {
		Assert.assertTrue(weblinksDisplayGroup.verifyLinkCountInFirstPage());
	}
	
	@Test(dependsOnMethods = { "verifyLinkCountInFirstPage" })
	public void verifyViewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(weblinksDisplayGroup.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllLinkIsWorking" })
	public void verifyViewAllLinkIsNotDisplayed() throws Exception {
		
		//Search term should fetch less than 3 results
		doBasicSearchUsingSearchTerm("obama");
		Assert.assertFalse(weblinksDisplayGroup.checkViewAllLinkIsPresent());
	}
}
