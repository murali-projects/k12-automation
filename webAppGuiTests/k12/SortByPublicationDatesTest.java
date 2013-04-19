package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortByPublicationDates;

public class SortByPublicationDatesTest extends BaseWebPageTest{
	private SortByPublicationDates sortByPublDate;
	public String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm)
			throws Exception {
		sortByPublDate = new SortByPublicationDates();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Parameters( { "newsDisplayGroup" })
	@Test
	public void arePublicationDatesSortedForNews(String displayGroup) throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate(displayGroup));
	}
	
	@Parameters( { "journalsDisplayGroup" })
	@Test
	public void arePublicationDatesSortedForJournals(String displayGroup) throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate(displayGroup));
	}
	
	@Parameters( { "magazinesDisplayGroup" })
	@Test
	public void arePublicationDatesSortedForMagazines(String displayGroup) throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate(displayGroup));
	}
	
	@Parameters( { "audioDisplayGroup" })
	@Test
	public void arePublicationDatesSortedForAudio(String displayGroup) throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate(displayGroup));
	}
	
	@Parameters( { "videoDisplayGroup" })
	@Test
	public void arePublicationDatesSortedForVideo(String displayGroup) throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate(displayGroup));
	}
}
