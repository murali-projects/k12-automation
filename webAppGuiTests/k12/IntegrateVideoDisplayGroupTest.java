package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateVideoDisplayGroup;

public class IntegrateVideoDisplayGroupTest extends BaseWebPageTest{
		
	private IntegrateVideoDisplayGroup integrateVideoDispalyGroup;
	
	@BeforeTest
	public void setUp() throws Exception{
		integrateVideoDispalyGroup = new IntegrateVideoDisplayGroup();
	} 
	
	@Test
	public void searchIntegrateVideoDisplayGroup() throws InterruptedException{
		doBasicSearchUsingSearchTerm("war");
	}
	
    @Test (dependsOnMethods={"searchIntegrateVideoDisplayGroup"})
	public void verifyVideoBucketisDisplayed() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyVideoBucket());
	}
	
	@Test (dependsOnMethods={"verifyVideoBucketisDisplayed"})
	public void verifyViewAllLinkisDisplayed() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyVideoViewAllLink());
	}
	
	@Test (dependsOnMethods={"verifyViewAllLinkisDisplayed"})
	public void verifyCountOfVideoIsPresent() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyCountOfVideo());
	}
	
	@Test (dependsOnMethods={"verifyCountOfVideoIsPresent"})
	public void validateVideoCountLinksWithOcean() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyVideoBucketContentLinksCount());
	}
	
	@Test(dependsOnMethods={"validateVideoCountLinksWithOcean"})
	public void validateVideoBucketCountWithOcean() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.validateCountWithOcean("war"));
	}
	
	@Test (dependsOnMethods={"validateVideoBucketCountWithOcean"})
	public void validateVideoContentLinksWithOcean() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyDocumentTitles("war", "videos"));
	}

	@Test (dependsOnMethods={"validateVideoBucketCountWithOcean"})
	public void verifyVideoIconIsDisplayed() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyVideoIcon());
	}
	
	@Test(dependsOnMethods={"verifyVideoIconIsDisplayed"})
	public void verifyViewAllIsWorking() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyViewAllLinkIsWorking());
	}
	
	@Test (dependsOnMethods={"verifyViewAllIsWorking"})
	public void verifyVideoIconIsDisplayedInDocument() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyVideoIconInDocument());
	}
	
	@Test
	public void verifyBackLinkIsDisplayed() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyBackLinkinVideoDocument());
	}
	
	@Test
	public void verifyBackLinkIsWorking() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyBackLinkinWorking());
	}

	@Test
	public void verifyPublicationDateIsDisplayed() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifyPublicationDatesIsDisplayed());
	}

	@Test
	public void validatePublicationdatesWithOcean() throws Exception {
		Assert.assertTrue(integrateVideoDispalyGroup.verifypublicationDate("war", "video"));
	}
}