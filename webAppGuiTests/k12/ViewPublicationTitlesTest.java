package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewPublicationTitles;

public class ViewPublicationTitlesTest extends BaseWebPageTest{
protected ViewPublicationTitles viewPublicationTitles;
	
	@BeforeTest
	public void setUp() throws Exception {
		viewPublicationTitles = new ViewPublicationTitles();
		doBasicSearchUsingSearchTerm("war");
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInReference() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("reference"));
	}
	
	@Test
	public void verfiyPublicationTitlesInReference() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "reference"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInMagazines() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("magazines"));
	}
	
	@Test
	public void verfiyPublicationTitlesInMagazines() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "magazines"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInNews() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("news"));
	}
	
	@Test
	public void verfiyPublicationTitlesInNews() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "news"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInAcademicJournals() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("academicJournals"));
	}
	
	@Test
	public void verfiyPublicationTitlesInAcademicJournals() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "academicJournals"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInPrimarySources() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("primarySources"));
	}
	
	@Test
	public void verfiyPublicationTitlesInPrimarySources() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "primarySources"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInStatistics() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("statistics"));
	}
	
	@Test
	public void verfiyPublicationTitlesInStatistics() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "statistics"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInViewPoints() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("viewPoints"));
	}
	
	@Test
	public void verfiyPublicationTitlesInViewPoints() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "viewPoints"));
	}
	
	@Test
	public void checkPubTitlesAreDisplayedInWebLinks() throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed("webLinks"));
	}
	
	@Test
	public void verfiyPublicationTitlesInWebLinks() throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles("war", "webLinks"));
	}
	
}
