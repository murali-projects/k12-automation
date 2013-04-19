package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateViewpointsDisplayGroup;

public class IntegrateViewpointsDisplayGroupTest extends BaseWebPageTest{

	private IntegrateViewpointsDisplayGroup viewViewpointsDisplayGroup;
	private String searchTerm;
	
	@Parameters( { "searchTerm"})
	@BeforeTest 
	public void setUp(String searchTerm) throws Exception{
		viewViewpointsDisplayGroup = new IntegrateViewpointsDisplayGroup();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyViewpointsDisplayGroupIsDisplayed() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "verifyViewpointsDisplayGroupIsDisplayed" })
	public void verifyCountIsDisplayed() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.validateCount(searchTerm));
	}
	
	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyViewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "verifyViewAllLinkIsDisplayed" })
	public void verifyLinkCountInFirstPage() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.verifyLinkCountInFirstPage());
	}
	
	@Test(dependsOnMethods = { "verifyLinkCountInFirstPage" })
	public void verifyViewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.checkViewAllLinkIsWorking());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllLinkIsWorking" })
	public void verifyDocumentTitles() throws Exception {
		Assert.assertTrue(viewViewpointsDisplayGroup.verifyDocumentTitles(searchTerm));
	}
	
	

}
