package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AudioPodcastDocument;


public class AudioPodcastDocumentTest extends BaseWebPageTest{
	
private AudioPodcastDocument audioPodcastDocument ;
	
	@BeforeTest
	public void setUp() throws Exception{
		audioPodcastDocument = new AudioPodcastDocument();
	}
			
	@Test
	public void basicSearchTest() throws InterruptedException{
		doBasicSearchUsingSearchTerm("obama");
	}

	@Test (dependsOnMethods={"basicSearchTest"})
	public void verifyPodcastDetailedPageIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastDetailedPageIsDisplayed());
	}
	
	@Test (dependsOnMethods={"verifyPodcastDetailedPageIsDisplayed"})
	public void verifyPodcastDetailedPageTitleIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastTitleIsDisplayed());
	}
	
	@Test (dependsOnMethods={"verifyPodcastDetailedPageTitleIsDisplayed"})
	public void verifyPodcastDetailedPageSearchBoxIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastPageSearchBoxIsDisplayed());
	}
	
	@Test (dependsOnMethods={"verifyPodcastDetailedPageSearchBoxIsDisplayed"})
	public void verifyPodcastDetailedPageSearchButtonIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastPageSearchButtonIsDisplayed());
	}
	
	@Test (dependsOnMethods={"verifyPodcastDetailedPageSearchButtonIsDisplayed"})
	public void verifyPodcastDetailedPageCopyrightIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastPageCopyrightIsDisplayed());
	}
	
	@Test(dependsOnMethods={"verifyPodcastDetailedPageCopyrightIsDisplayed"})
	public void verifyPodcastDetailedPageSourceCitationIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.podcastPageSourceCitationIsDisplayed());
	}
			 
   @Test(dependsOnMethods={"verifyPodcastDetailedPageSourceCitationIsDisplayed"})
	public void verifyPodcastDetailedPageGalaeDocumentIsDisplayed() throws Exception {
		Assert.assertTrue(audioPodcastDocument.verifyGaleDocumentNo("audio", "relevance", "obama"));
	}
   
	@Test (dependsOnMethods={"verifyPodcastDetailedPageGalaeDocumentIsDisplayed"})
	public void verifyPodcastDetailedPageTranscriptIsDisplayed() throws Exception {
			Assert.assertTrue(audioPodcastDocument.verifyTranscriptIsDisplayed("obama"));
	}
	@Test (dependsOnMethods={"verifyPodcastDetailedPageTranscriptIsDisplayed"})
	public void verifyPodcastDetailedPageBreadCrumbIsWorking() throws Exception {
		Assert.assertTrue(audioPodcastDocument.verifyBreadCrumbNavigation());
	}	

}
