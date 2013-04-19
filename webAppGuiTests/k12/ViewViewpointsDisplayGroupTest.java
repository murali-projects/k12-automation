package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewViewpointsDisplayGroup;

public class ViewViewpointsDisplayGroupTest extends BaseWebPageTest{

	private ViewViewpointsDisplayGroup viewViewpointsDisplayGroup;
	
	@BeforeTest
	public void setUp() throws Exception{
		viewViewpointsDisplayGroup = new ViewViewpointsDisplayGroup();
	}
	
	@Test
	public void search() throws Exception {
		doBasicSearchUsingSearchTerm("Aldehydes");
		viewViewpointsDisplayGroup.search("Aldehydes");
	}
	
	@Test(dependsOnMethods = {"search"})
	public void verifyViewpointsDisplayGroupIsDisplayed() throws Exception{
		Assert.assertTrue(viewViewpointsDisplayGroup.verifyViewpointsDisplayGroupIsDisplayed());	
	}
	
	@Test(dependsOnMethods = {"verifyViewpointsDisplayGroupIsDisplayed"})
	public void verifyTopDocumentsAreDisplayed() throws Exception{
		Assert.assertTrue(viewViewpointsDisplayGroup.verifyTopDocuments());
	}
	
	@Test(dependsOnMethods = {"verifyTopDocumentsAreDisplayed"})
	public void verifyToolTipOfDocumentTitle() throws Exception{
		Assert.assertTrue(viewViewpointsDisplayGroup.verifyToolTipOfDocumentTitle());
	}

}
