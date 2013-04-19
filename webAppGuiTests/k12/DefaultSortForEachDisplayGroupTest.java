package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.DefaultSortForEachDisplayGroup;

public class DefaultSortForEachDisplayGroupTest extends BaseWebPageTest {

	private DefaultSortForEachDisplayGroup defaultSortForEachDisplayGroup;
	private String referenceDisplayGroup;
	private String magazinesDisplayGroup;
	private String newsDisplayGroup;
	private String primarySourcesDisplayGroup;
	private String viewPointsDisplayGroup;
	private String statisticsDisplayGroup;
	private String audioDisplayGroup;
	private String videoDisplayGroup;
	private String webLinksDisplayGroup;
	private String academicjournalsDisplayGroup;

	@Parameters( { "searchTerm", "referenceDisplayGroup",
			"magazinesDisplayGroup", "newsDisplayGroup",
			"primarySourcesDisplayGroup", "viewPointsDisplayGroup",
			"statisticsDisplayGroup", "audioDisplayGroup", "videoDisplayGroup",
			"webLinksDisplayGroup", "academicjournalsDisplayGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String referenceDisplayGroup,
			String magazinesDisplayGroup, String newsDisplayGroup,
			String primarySourcesDisplayGroup, String viewPointsDisplayGroup,
			String statisticsDisplayGroup, String audioDisplayGroup,
			String videoDisplayGroup, String webLinksDisplayGroup,
			String academicjournalsDisplayGroup) throws Exception {
		defaultSortForEachDisplayGroup = new DefaultSortForEachDisplayGroup(
				searchTerm);
		this.referenceDisplayGroup = referenceDisplayGroup;
		this.magazinesDisplayGroup = magazinesDisplayGroup;
		this.newsDisplayGroup = newsDisplayGroup;
		this.primarySourcesDisplayGroup = primarySourcesDisplayGroup;
		this.viewPointsDisplayGroup = viewPointsDisplayGroup;
		this.statisticsDisplayGroup = statisticsDisplayGroup;
		this.audioDisplayGroup = audioDisplayGroup;
		this.videoDisplayGroup = videoDisplayGroup;
		this.webLinksDisplayGroup = webLinksDisplayGroup;
		this.academicjournalsDisplayGroup = academicjournalsDisplayGroup;
	}

	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void defaultSortForReferenceDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(referenceDisplayGroup));
	}

	@Test
	public void defaultSortForMagazinesDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(magazinesDisplayGroup));
	}

	@Test
	public void defaultSortForNewsDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(newsDisplayGroup));
	}

	@Test
	public void defaultSortForPrimarySourceDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(primarySourcesDisplayGroup));
	}

	@Test
	public void defaultSortForViewPointsDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(viewPointsDisplayGroup));
	}

	@Test
	public void defaultSortForStatisticsDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(statisticsDisplayGroup));
	}

	@Test
	public void defaultSortForAudioDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByPublicationDate(audioDisplayGroup));
	}

	@Test
	public void defaultSortForVideoDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByPublicationDate(videoDisplayGroup));
	}

	@Test
	public void defaultSortForWebLinksDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByPublicationDate(webLinksDisplayGroup));
	}

	@Test
	public void defaultSortForAcademicJournalsDisplayGroup() throws Exception {
		Assert.assertTrue(defaultSortForEachDisplayGroup
				.validateDefaultSortByRelevance(academicjournalsDisplayGroup));
	}
}
