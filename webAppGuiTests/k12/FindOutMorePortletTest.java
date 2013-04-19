package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.FindOutMorePortlet;

public class FindOutMorePortletTest extends BaseWebPageTest {

	private FindOutMorePortlet findOutMorePortlet;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		findOutMorePortlet = new FindOutMorePortlet();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyViewPointRecords() throws Exception {
		Assert.assertTrue(findOutMorePortlet.verifyViewPointRecordsCount());
	}

	@Test(dependsOnMethods = { "verifyViewPointRecords" })
	public void verifyDetailedViewPage() throws Exception {
		Assert.assertTrue(findOutMorePortlet
				.verifyDetailedViewPageIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyDetailedViewPage" })
	public void verifyFindOutMorePortletIsDisplayed() throws Exception {
		Assert.assertTrue(findOutMorePortlet
				.verifyFindOutMorePortletIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyFindOutMorePortletIsDisplayed" })
	public void verifyAssignmentTextIsDisplayed() throws Exception {
		Assert.assertTrue(findOutMorePortlet.verifyAssignmentTextIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyAssignmentTextIsDisplayed" })
	public void verifySubjectSearchIsDisplayed() throws Exception {
		Assert.assertTrue(findOutMorePortlet.verifySubjectSearch());
	}

	@Test(dependsOnMethods = { "verifySubjectSearchIsDisplayed" })
	public void verifySamePortletIsNotDisplayed() throws Exception {
		Assert.assertTrue(findOutMorePortlet.verifySamePortletIsNotDisplayed());
	}
}
