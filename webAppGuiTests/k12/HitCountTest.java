package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitCount;

public class HitCountTest extends BaseWebPageTest {
	private HitCount hitCount;

	@BeforeTest
	public void setUp() throws Exception {
		hitCount = new HitCount();
	}

	@Test
	public void verifyReferenceArticleCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("reference"));
	}

	@Test(dependsOnMethods = { "verifyReferenceArticleCountisDisplayed" })
	public void verifyReferenceArticleCountisDisplayedAfterClicking()
			throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("reference"));
	}

	@Test(dependsOnMethods = { "verifyReferenceArticleCountisDisplayedAfterClicking" })
	public void validateReferenceArticleCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("K12-Reference", "war"));
	}

	@Test(dependsOnMethods = { "validateReferenceArticleCount" })
	public void verifyMagazineseCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("magazines"));
	}

	@Test(dependsOnMethods = { "verifyMagazineseCountisDisplayed" })
	public void verifyMagazinesCountisDisplayedAfterClicking() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("magazines"));
	}

	@Test(dependsOnMethods = { "verifyMagazinesCountisDisplayedAfterClicking" })
	public void validateMagazinesCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("magazines", "war"));
	}

	@Test(dependsOnMethods = { "validateMagazinesCount" })
	public void verifyAudioCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("audio"));
	}

	@Test(dependsOnMethods = { "verifyAudioCountisDisplayed" })
	public void verifyAudioCountisDisplayedAfterClicking() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("audio"));
	}

	@Test(dependsOnMethods = { "verifyAudioCountisDisplayedAfterClicking" })
	public void validateAudioCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("audio", "war"));
	}

	@Test(dependsOnMethods = { "validateAudioCount" })
	public void verifyVideoCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("video"));
	}

	@Test(dependsOnMethods = { "verifyVideoCountisDisplayed" })
	public void verifyVideoCountisDisplayedAfterClicking() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("video"));
	}

	@Test(dependsOnMethods = { "verifyVideoCountisDisplayedAfterClicking" })
	public void validateVideoCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("video", "war"));
	}

	@Test(dependsOnMethods = { "validateVideoCount" })
	public void verifyNewsCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("news"));
	}

	@Test(dependsOnMethods = { "verifyNewsCountisDisplayed" })
	public void verifyNewsCountisDisplayedAfterClicking() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("news"));
	}

	@Test(dependsOnMethods = { "verifyNewsCountisDisplayedAfterClicking" })
	public void validateNewsCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("news", "war"));
	}

	@Test(dependsOnMethods = { "validateNewsCount" })
	public void verifyAcademicJournalsCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("academicjournals"));
	}

	@Test(dependsOnMethods = { "verifyAcademicJournalsCountisDisplayed" })
	public void verifyAcademicJournalsCountisDisplayedAfterClicking()
			throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert
				.assertTrue(hitCount
						.verifyCountisDisplayedAfterClickingViewAll("academicjournals"));
	}

	@Test(dependsOnMethods = { "verifyAcademicJournalsCountisDisplayedAfterClicking" })
	public void validateAcademicJournalsCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("Journals", "war"));
	}

	@Test(dependsOnMethods = { "validateAcademicJournalsCount" })
	public void verifyPrimarySourcesCountisDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.verifyCountisDisplayed("primarysources"));
	}

	@Test(dependsOnMethods = { "verifyPrimarySourcesCountisDisplayed" })
	public void verifyPrimarySourcesCountisDisplayedAfterClicking()
			throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount
				.verifyCountisDisplayedAfterClickingViewAll("primarysources"));
	}

	@Test(dependsOnMethods = { "verifyPrimarySourcesCountisDisplayedAfterClicking" })
	public void validatePrimarySourcesCount() throws Exception {
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(hitCount.checkCount("K12-PrimarySources", "war"));

	}

}
