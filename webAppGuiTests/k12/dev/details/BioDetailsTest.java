package k12.dev.details;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.ListDetailPortlet;
import webPageContainers4Testing.dev.ListViewPortlet;

public class BioDetailsTest extends BaseDevWebPageTest {

	private static final String SEARCH_TERM = "Albert Schweitzer";

	private ListViewPortlet referenceDisplayGroup;
	private ListDetailPortlet listDetailPortlet;

	@BeforeTest
	public void setUp() throws Exception {
		referenceDisplayGroup = new ListViewPortlet("reference");
		listDetailPortlet = new ListDetailPortlet("viewpoints");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		waitABit();
		doBasicSearchUsingSearchTerm(SEARCH_TERM);
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isListPortletPresent() throws Exception {
		Assert.assertTrue(referenceDisplayGroup.checkDisplayGroupIsPresent());
	}

	@Test(dependsOnMethods = "isListPortletPresent")
	public void bioInfoIsDisplayedForReference() throws Exception {
		referenceDisplayGroup.goToReferenceDocument();
		Assert.assertTrue(referenceDisplayGroup.checkForDivision("reference_bio_info_xpath"));
	}

	@Test(dependsOnMethods = { "bioInfoIsDisplayedForReference" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(referenceDisplayGroup.doesContentExistInClass(referenceDisplayGroup
				.getProperty("footerDiv"), "footerGaleLogo"));
	}
}
