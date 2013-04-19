package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegratePublicationTitles;

public class IntegratePublicationTitlesTest extends BaseWebPageTest{
	protected IntegratePublicationTitles viewPublicationTitles;
	
	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		viewPublicationTitles = new IntegratePublicationTitles(searchTerm);
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Parameters( { "reference"})
	@Test
	public void checkPubTitlesAreDisplayedInReference(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "magazines"})
	@Test(dependsOnMethods = { "checkPubTitlesAreDisplayedInReference" })
	public void checkPubTitlesAreDisplayedInMagazines(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "news"})
	@Test(dependsOnMethods = { "checkPubTitlesAreDisplayedInMagazines" })
	public void checkPubTitlesAreDisplayedInNews(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "primarysources"})
	@Test(dependsOnMethods = { "checkPubTitlesAreDisplayedInNews" })
	public void checkPubTitlesAreDisplayedInPrimarySources(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "viewpoints"})
	@Test(dependsOnMethods = { "checkPubTitlesAreDisplayedInPrimarySources" })
	public void checkPubTitlesAreDisplayedInViewPoints(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "reference"})
	@Test
	public void verfiyPublicationTitlesInReference(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "magazines"})
	@Test
	public void verfiyPublicationTitlesInMagazines(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "news"})
	@Test
	public void verfiyPublicationTitlesInNews(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "primarysources"})
	@Test
	public void verfiyPublicationTitlesInPrimarySources(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "viewpoints"})
	@Test
	public void verfiyPublicationTitlesInViewPoints(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "academicJournals"})
	@Test
	public void checkPubTitlesAreDisplayedInAcademicJournals(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "academicJournals"})
	@Test
	public void verfiyPublicationTitlesInAcademicJournals(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "websites"})
	@Test
	public void checkPubTitlesAreDisplayedInWebsites(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "websites"})
	@Test
	public void verfiyPublicationTitlesInWebsites(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
	@Parameters( { "statistics"})
	@Test
	public void checkPubTitlesAreDisplayedInStatistics(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.checkPublicationTitlesAreDisplayed(displayGroup));
	}
	
	@Parameters( { "statistics"})
	@Test
	public void verfiyPublicationTitlesInStatistics(String displayGroup) throws Exception{
		Assert.assertTrue(viewPublicationTitles.verifypublicationTitles(displayGroup));
	}
	
}
