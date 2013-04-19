package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewLexileScore;

public class ViewLexileScoreTest extends BaseWebPageTest {

	public ViewLexileScore viewLexileScore;

	@BeforeTest
	public void setUp() throws Exception {
		viewLexileScore = new ViewLexileScore();
	}

	@Parameters( { "searchTerm", "displayGroupNews" })
	@Test
	public void verifyLexileScoreIsDisplayedForNews(String searchTerm,
			String displayGroupNews) throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert
				.assertTrue(viewLexileScore
						.verifyLexileScore(displayGroupNews));

	}

	@Parameters( { "searchTerm", "displayGroupMagazines" })
	@Test(dependsOnMethods = { "verifyLexileScoreIsDisplayedForNews" })
	public void verifyLexileScoreIsDisplayedForMagazines(String searchTerm,
			String displayGroupMagazines) throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScore(displayGroupMagazines));

	}

	@Parameters( { "searchTerm", "displayGroupAcademic" })
	@Test(dependsOnMethods = { "verifyLexileScoreIsDisplayedForMagazines" })
	public void verifyLexileScoreIsDisplayedForAcademicJournals(
			String searchTerm, String displayGroupAcademic)
			throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScore(displayGroupAcademic));
	}

	@Parameters( { "searchTerm", "displayGroupReference" })
	@Test(dependsOnMethods = { "verifyLexileScoreIsDisplayedForAcademicJournals" })
	public void verifyLexileScoreNotDisplayedForReference(String searchTerm,
			String displayGroupReference) throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScoreNotDisplayed(displayGroupReference));
	}

	@Parameters( { "searchTerm", "displayGroupViewpoints" })
	@Test(dependsOnMethods = { "verifyLexileScoreNotDisplayedForReference" })
	public void verifyLexileScoreNotDisplayedForViewPoints(String searchTerm,
			String displayGroupViewpoints) throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScoreNotDisplayed(displayGroupViewpoints));
	}

	@Parameters( { "searchTerm", "displayGroupPrimarysources" })
	@Test(dependsOnMethods = { "verifyLexileScoreNotDisplayedForViewPoints" })
	public void verifyLexileScoreNotDisplayedForPrimarySources(
			String searchTerm, String displayGroupPrimarysources)
			throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScoreNotDisplayed(displayGroupPrimarysources));
	}

	@Parameters( { "searchTerm", "displayGroupAudio" })
	@Test(dependsOnMethods = { "verifyLexileScoreNotDisplayedForPrimarySources" })
	public void verifyLexileScoreNotDisplayedForAudios(String searchTerm,
			String displayGroupAudio) throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewLexileScore
				.verifyLexileScoreNotDisplayed(displayGroupAudio));
	}

}
