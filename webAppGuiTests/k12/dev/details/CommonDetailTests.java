package k12.dev.details;

import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.ListDetailPortlet;

public class CommonDetailTests extends BaseDevWebPageTest {

	private ListDetailPortlet listDetailPortlet;

	@BeforeTest
	public void setUp() throws Exception {
		listDetailPortlet = new ListDetailPortlet("viewpoints");
	}

	@Test
	public void checkDetailsForForViewpoints() throws Exception {
		listDetailPortlet.checkRelatedSubjects("viewpoints.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());

	}

	@Test(dependsOnMethods = "checkDetailsForForViewpoints")
	public void checkDetailsForForReference() throws Exception {
		listDetailPortlet.checkRelatedSubjects("reference.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsMoreLinkIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsMoreLinkIsWorking());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLessLinkIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLessLinkIsWorking());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForReference")
	public void checkDetailsForForNews() throws Exception {
		listDetailPortlet.checkRelatedSubjects("reference.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForNews")
	public void checkDetailsForForPrimarySources() throws Exception {
		listDetailPortlet.checkRelatedSubjects("primary.sources.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForPrimarySources")
	public void checkDetailsForForAudio() throws Exception {
		listDetailPortlet.checkRelatedSubjects("audio.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForAudio")
	public void checkDetailsForForStatistics() throws Exception {
		listDetailPortlet.checkRelatedSubjects("statistics.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForStatistics")
	public void checkDetailsForForImage() throws Exception {
		listDetailPortlet.checkRelatedSubjects("images.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForImage")
	public void checkDetailsForForVideo() throws Exception {
		listDetailPortlet.checkRelatedSubjects("video.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForVideo")
	public void checkDetailsForForWebsite() throws Exception {
		listDetailPortlet.checkRelatedSubjects("website.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkShortDescriptionIsPresent());
		Assert.assertTrue(listDetailPortlet.checkExternalUrlIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForWebsite")
	public void checkDetailsForForAcademicJournal() throws Exception {
		listDetailPortlet.checkRelatedSubjects("academic.journal.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = "checkDetailsForForAcademicJournal")
	public void checkDetailsForForMagazines() throws Exception {
		listDetailPortlet.checkRelatedSubjects("magazine.detail.page.url");
		waitABit();
		Assert.assertTrue(listDetailPortlet.checkSourceCitationIsPresent());
		Assert.assertTrue(listDetailPortlet.checkGaleDocumentNumberIsPresent());
		// TODO - check this later.
		// Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsIsPresent());
		// Assert.assertTrue(listDetailPortlet.checkRelatedSubjectsLinkIsWorking());
	}

	@Test(dependsOnMethods = { "checkDetailsForForMagazines" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(listDetailPortlet.doesContentExistInClass(listDetailPortlet.getProperty("footerDiv"),
				"footerGaleLogo"));
	}
}
