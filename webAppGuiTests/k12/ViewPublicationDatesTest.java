package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewPublicationDates;

public class ViewPublicationDatesTest extends BaseWebPageTest{

	private ViewPublicationDates viewPublicationDates;
	private String searchTerm;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception{
		this.searchTerm = searchTerm;
		viewPublicationDates = new ViewPublicationDates();
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Parameters( { "reference" })
	@Test
	public void verifyPublicationDatesAreDisplayedForReference(String reference) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(reference));
	}
	
	@Parameters( { "reference" })
	@Test
	public void verifyPublicationDatesForReference(String reference) throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates(reference, searchTerm));
	}

	@Parameters( { "magazines" })
	@Test
	public void verifyPublicationDatesAreDisplayedForMagazines(String magazines) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(magazines));
	}
	
	@Parameters( { "magazines" })
	@Test
	public void verifyPublicationDatesForMagazines() throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates("magazines", searchTerm));
	}
	
	@Parameters( { "news" })
	@Test
	public void verifyPublicationDatesAreDisplayedForNews(String news) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(news));
	}
	
	@Parameters( { "news" })
	@Test
	public void verifyPublicationDatesForNews(String news) throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates(news, searchTerm));
	}
	
	@Parameters( { "primarysources" })
	@Test
	public void verifyPublicationDatesAreDisplayedForPrimarySources(String primarysources) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(primarysources));
	}
	
	@Parameters( { "primarysources" })
	@Test
	public void verifyPublicationDatesForPrimarySources(String primarysources) throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates(primarysources, searchTerm));
	}
	
	@Parameters( { "audio" })
	@Test
	public void verifyPublicationDatesAreDisplayedForAudio(String audio) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(audio));
	}
	
	@Parameters( { "audio" })
	@Test
	public void verifyPublicationDatesForAudio(String audio) throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates(audio, searchTerm));
	}
	
	@Parameters( { "viewpoints" })
	@Test
	public void verifyPublicationDatesAreDisplayedForViewPoints(String viewpoints) throws Exception{
		Assert.assertTrue(viewPublicationDates.verifyPublicationDatesArePresent(viewpoints));
	}
	
	@Parameters( { "viewpoints" })
	@Test
	public void verifyPublicationDatesForviewPoints(String viewpoints) throws Exception{
		Assert.assertTrue(viewPublicationDates.validatePublicationDates(viewpoints, searchTerm));
	}
	
	
	
}
