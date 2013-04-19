package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DisplayGroups;

public class DisplayGroupsTest extends BaseWebPageTest {
	private DisplayGroups displayGroups;
	public String sortBy;
	public String dataBaseElement;
	public String searchTerm;
	public String referenceDisplayName;
	public String newsDisplayName;
	public String magazinesDisplayName;
	public String academicJournalsDisplayName;
	public String audioDisplayName;
	public String videoDisplayName;
	public String imagesDisplayName;

	@Parameters( { "searchTerm", "sortBy","dataBaseElement",
			"referenceDisplayName","newsDisplayName","magazinesDisplayName",
			"academicJournalsDisplayName","audioDisplayName",
			"videoDisplayName","imagesDisplayName" })
	@BeforeTest
	public void setUp(String searchTerm, String sortBy, String dataBaseElement,
			String referenceDisplayName, String newsDisplayName,
			String magazinesDisplayName, String academicJournalsDisplayName,
			String audioDisplayName, String videoDisplayName,
			String imagesDisplayName) throws Exception {
		displayGroups = new DisplayGroups();
		this.searchTerm = searchTerm;
		this.sortBy = sortBy;
		this.dataBaseElement = dataBaseElement;
		this.referenceDisplayName = referenceDisplayName;
		this.newsDisplayName = newsDisplayName;
		this.magazinesDisplayName = magazinesDisplayName;
		this.academicJournalsDisplayName = academicJournalsDisplayName;
		this.audioDisplayName = audioDisplayName;
		this.videoDisplayName = videoDisplayName;
		this.imagesDisplayName = imagesDisplayName;
	}

	@Test
	public void viewDisplayGroupsForSeachTerm() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(displayGroups.validateSearchContent());
	}

	@Parameters( { "referenceContentGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewReferenceArticleDisplayGroup(String referenceContentGroup)
			throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(
				referenceDisplayName, referenceContentGroup, sortBy,
				dataBaseElement, searchTerm));
	}

	@Parameters( { "magazinesContentGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewMagazinesDisplayGroup(String magazinesContentGroup)
			throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(
				magazinesDisplayName, magazinesContentGroup, sortBy,
				dataBaseElement, searchTerm));
	}

	@Parameters( { "newsContentGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewNewsDisplayGroup(String newsContentGroup) throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(newsDisplayName,
				newsContentGroup, sortBy, dataBaseElement, searchTerm));
	}

	@Parameters( { "journalsGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewAcademicJournalsDisplayGroup(String journalsGroup)
			throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(
				academicJournalsDisplayName, journalsGroup, sortBy,
				dataBaseElement, searchTerm));
	}

	@Parameters( { "imagesGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewImagesDisplayGroup(String imagesGroup) throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(imagesDisplayName,
				imagesGroup, sortBy, dataBaseElement, searchTerm));
	}

	@Parameters( { "audioGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewAudioDisplayGroup(String audioGroup) throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(audioDisplayName,
				audioGroup, sortBy, dataBaseElement, searchTerm));
	}

	@Parameters( { "videoGroup" })
	@Test
	// @Test(dependsOnMethods = { "viewDisplayGroupsForSeachTerm" })
	public void viewVideoDisplayGroup(String videoGroup) throws Exception {
		Assert.assertTrue(displayGroups.validateDisplayGroup(videoDisplayName,
				videoGroup, sortBy, dataBaseElement, searchTerm));
	}
}
