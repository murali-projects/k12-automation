package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewPrimarySourcesDisplayGroup;

public class ViewPrimarySourcesDisplayGroupTest extends BaseWebPageTest{

	private ViewPrimarySourcesDisplayGroup viewPrimarySourcesDisplayGroup;
	
	@BeforeTest 
	public void setUp() throws Exception{
		viewPrimarySourcesDisplayGroup = new ViewPrimarySourcesDisplayGroup();
	}
	
	@Test
	public void search() throws Exception {
		doBasicSearchUsingSearchTerm("Aldehydes");
	}

	@Test(dependsOnMethods = "search")
	public void verifySearchTermInMsg() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.verifySearchTermInMsgDisplayed("Aldehydes"));
	}
	
	@Test(dependsOnMethods = "search")
	public void verifyPrimarySourcesDisplayGoupIsDisplayed() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = "search")
	public void verifyCountIsDisplayed() throws Exception {
		Thread.sleep(500);
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.validateCount("Aldehydes"));
	}
	
	@Test(dependsOnMethods = "search")
	public void verifyViewAllLinkIsDispalyed() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test
	public void verifyViewAllLinkIsNotDispalyed() throws Exception {
		
		//Search term should fetch less than 3 results
		doBasicSearchUsingSearchTerm("");
		Assert.assertFalse(viewPrimarySourcesDisplayGroup.checkViewAllLinkIsPresent());
	}
	
	@Test(dependsOnMethods = "search")
	public void verifyViewAllLinkIsWorking() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = "search")
	public void verifyLinkCountInFirstPage() throws Exception {
		Assert.assertTrue(viewPrimarySourcesDisplayGroup.verifyLinkCountInFirstPage());
	}
}
