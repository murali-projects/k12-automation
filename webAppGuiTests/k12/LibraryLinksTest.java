package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.LibraryLinks;

public class LibraryLinksTest extends BaseWebPageTest {
	
	private LibraryLinks libraryLinks;
	private String displayGroup;

	@Parameters( { "searchTerm","displayGroup" })
	@BeforeTest
	public void setUp(String searchTerm,String displayGroup) throws Exception {
		libraryLinks = new LibraryLinks();
		this.displayGroup=displayGroup;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Parameters( { "displayedGroup"})
	@Test
	public void verifyLibraryLinksAvailability(String displayedGroup) throws Exception {
		Assert.assertTrue(libraryLinks.verifyLibraryLinksAvailability(displayedGroup));
	}
	
	@Test(dependsOnMethods = { "verifyLibraryLinksAvailability" })
	public void verifyLinksUnderLibrarySection() throws Exception {
		Assert.assertTrue(libraryLinks.verifyLinksUnderLibrarySection());
	}
	
	@Test(dependsOnMethods = { "verifyLinksUnderLibrarySection" })
	public void verifyLibraryLinksAvailabilityForAllGroups() throws Exception {
		Assert.assertTrue(libraryLinks.verifyLibraryLinksAvailabilityForAllGroups(displayGroup));
	}
	
	@Test(dependsOnMethods = { "verifyLibraryLinksAvailabilityForAllGroups" })
	public void verifyLinksInPortalPage() throws Exception {
		Assert.assertTrue(libraryLinks.verifyLinksInPortalPage());
	}
}
