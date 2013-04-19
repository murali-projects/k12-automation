package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateMagazinesDisplayGroup;

public class IntegrateMagazinesDisplayGroupTest extends BaseWebPageTest {
	private IntegrateMagazinesDisplayGroup magazinesDisplayGroup;


	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		magazinesDisplayGroup = new IntegrateMagazinesDisplayGroup();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void checkMagazinesDisplayedInDisplayGroup() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup
				.verifyTopThreeDocumentsArePresent());
	}

	//@Test
	@Test(dependsOnMethods = { "checkMagazinesDisplayedInDisplayGroup" })
	public void verifyViewAllIsNotDisplayed() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup
				.verifyViewAllNotDisplayed());
	}

	//@Test
	@Test(dependsOnMethods = { "verifyViewAllIsNotDisplayed" })
	public void verifyViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyViewAllIsDisplayed());
	}
	
	//@Test
	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyCountIsDisplayed() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyCountIsDisplayed());
	}
	
	@Parameters( { "searchTerm"})
	//@Test
	@Test(dependsOnMethods = { "verifyViewAllIsDisplayed" })
	public void verifyContentLinksAreSortedByPublicationDate(String searchTerm) throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyContentLinksAreSortedByPublicationDate(searchTerm,"Magazines", "Relevance"));
	}

	//@Test
	@Test(dependsOnMethods = { "verifyCountIsDisplayed" })
	public void verifyPublicationDetailsPresent() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyPublicationDetailsPresent());
	}
	
	//@Test
	@Test(dependsOnMethods = { "verifyPublicationDetailsPresent" })
	public void verifyViewAllIsAccessible() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyViewAllIsAccessible());
	}
	
	//@Test
	@Test(dependsOnMethods = { "verifyViewAllIsAccessible" })
	public void verifyGroupNameIsDisplayedInCaps() throws Exception {
		Assert.assertTrue(magazinesDisplayGroup.verifyGroupNameIsDisplayedInCaps());
	}
	
	@Parameters( { "searchTerm"})
	//@Test
	@Test(dependsOnMethods = { "verifyGroupNameIsDisplayedInCaps" })
	public void verifyContentLinkIsWorking(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(magazinesDisplayGroup.verifyContentLinkIsWorking());
	}
	
	@Parameters( { "searchTerm"})
	@Test(dependsOnMethods = { "verifyContentLinkIsWorking" })
	public void verifyMagazinesCanBeMarked(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(magazinesDisplayGroup.verifyMagazinesCanBeMarked());
	}
}
