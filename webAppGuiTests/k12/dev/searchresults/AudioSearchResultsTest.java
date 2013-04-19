package k12.dev.searchresults;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ListViewPortlet;

public class AudioSearchResultsTest extends BaseDevWebPageTest {

	private ListViewPortlet viewAudioDisplayGroup;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		viewAudioDisplayGroup = new ListViewPortlet("audio");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("president");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(viewAudioDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = { "isListPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		Assert.assertTrue(viewAudioDisplayGroup.checkArticleLinkCount());
	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		Assert.assertTrue(viewAudioDisplayGroup.checkViewAllLinkIsPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void articleDisplayWorking() throws Exception {
		Assert.assertTrue(viewAudioDisplayGroup.checkDetailedViewOfFirstItemIsDisplayed());
	}

	@Test(dependsOnMethods = { "articleDisplayWorking" })
	public void viewAllLinkIsWorking() throws Exception {
		System.out.println("hello");
		Assert.assertTrue(viewAudioDisplayGroup.checkViewAllLinkIsWorking());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsWorking" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(viewAudioDisplayGroup.doesContentExistInClass(viewAudioDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
