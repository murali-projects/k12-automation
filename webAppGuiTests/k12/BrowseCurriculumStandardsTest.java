package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import webPageContainers4Testing.BrowseCurriculumStandards;

public class BrowseCurriculumStandardsTest extends BaseWebPageTest {
	
	private BrowseCurriculumStandards browsecurriculumstandards;
	
	@BeforeTest
	public void setUp() throws Exception {
		browsecurriculumstandards = new BrowseCurriculumStandards();
	}

	@Test
	public void verifyClickingonResourcesLink() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyClickingonResourcesLink());
	}

	@Test(dependsOnMethods = { "verifyClickingonResourcesLink" })
	public void verifySelectingCurriculumStandards() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifySelectingCurriculumStandards());
	}
	
	@Test(dependsOnMethods = { "verifySelectingCurriculumStandards" })
	public void verifyCountryLinkPresent() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyCountryLinkPresent());
	}
	
	@Test(dependsOnMethods = { "verifyCountryLinkPresent" })
	public void verifyNationalStandardsLinkispresent() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyNationalStandardsLinkispresent());
	}
	
	@Test(dependsOnMethods = { "verifyNationalStandardsLinkispresent" })
	public void verifyNationalStandards() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyNationalStandards());
	}
	
	@Test(dependsOnMethods = { "verifyNationalStandards" })
	public void verifyClickingNationalStandards() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyClickingNationalStandards());
	}
	
	@Test(dependsOnMethods = { "verifyClickingNationalStandards" })
	public void verifyNationalStandardsPopupClosable() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyNationalStandardsPopupClosable());
	}
	
	@Test(dependsOnMethods = { "verifyNationalStandardsPopupClosable" })
	public void verifyStateStandardsLink() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyStateStandardsLink());
	}
	
	@Test(dependsOnMethods = { "verifyStateStandardsLink" })
	public void verifyClickingStateStandards() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyClickingStateStandards());
	}
	@Test(dependsOnMethods = { "verifyClickingStateStandards" })
	public void verifyClickingOnListOfStates() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyClickingOnListOfStates());
	}
	@Test(dependsOnMethods = { "verifyClickingOnListOfStates" })
	public void verifyStateStandardsPoppupClosable() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyStateStandardsPoppupClosable());
	}
	@Test(dependsOnMethods = { "verifyStateStandardsPoppupClosable" })
	public void verifyNavigationThroughBreadcurmb() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyNavigationThroughBreadcurmb());
	}
	@Test(dependsOnMethods = { "verifyNavigationThroughBreadcurmb" })
	public void verifyBreadcrumb() throws Exception {
		Assert.assertTrue(browsecurriculumstandards.verifyBreadcrumb());
	}
	

}
