package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewPrinterFriendlyDocument;

public class ViewPrinterFriendlyDocumentTest extends BaseWebPageTest{
	
	private ViewPrinterFriendlyDocument printerFriendlyDocument;
	private String reference;
	private String magazines;
	private String news;
	private String primarysources;
	private String viewpoints;
	private String statistics;
	private String images;
	private String video;
	private String websites;
	private String audio;

	@Parameters( {"searchTerm","reference","magazines","news","primarysources","viewpoints","statistics","images","video","websites","audio"})
	@BeforeTest
	public void setUp(String searchTerm,String reference, String magazines, String news, String primarysources, String viewpoints,
			String statistics, String images,String video, String websites, String audio) throws Exception{
		printerFriendlyDocument = new ViewPrinterFriendlyDocument();
		this.reference = reference;
		this.magazines = magazines;
		this.news = news;
		this.primarysources = primarysources;
		this.viewpoints = viewpoints;
		this.statistics = statistics;
		this.images = images;
		this.video = video;
		this.websites = websites;
		this.audio = audio;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyPrintOptionAvailableForReference()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(reference));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForReference" })
	public void verifyPrintOptionAvailableForMagazines()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(magazines));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForMagazines" })
	public void verifyPrintOptionAvailableForNews()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(news));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForNews" })
	public void verifyPrintOptionAvailableForPrimarysources()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(primarysources));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForPrimarysources" })
	public void verifyPrintOptionAvailableForViewpoints()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(viewpoints));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForViewpoints" })
	public void verifyPrintOptionAvailableForStatistics()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(statistics));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForStatistics" })
	public void verifyPrintOptionAvailableForImages()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(images));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForImages" })
	public void verifyPrintOptionAvailableForVideo()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(video));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForVideo" })
	public void verifyPrintOptionAvailableForWebsites()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(websites));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForWebsites" })
	public void verifyPrintOptionAvailableForAudio()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailable(audio));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableForAudio" })
	public void verifyPrintOptionAvailableFromViewAll()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionAvailableFromViewAll(reference));
	}
	
	@Test(dependsOnMethods = { "verifyPrintOptionAvailableFromViewAll" })
	public void verifyDocumentOpensInNewWindow()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintPreviewWindowExists());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentOpensInNewWindow" })
	public void verifyPrintOptionNotAvailableInNewWindow()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionNotAvailableInNewWindow());
	}
	
	/*@Test(dependsOnMethods = { "verifyPrintOptionAvailableFromViewAll" })
	public void verifyTooltipExists()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyTooltipIsPresent());
	}*/
	
	@Test(dependsOnMethods = { "verifyPrintOptionNotAvailableInNewWindow" })
	public void verifyAfterClickingCancelOnPopup()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintWindowExistsAfterClickingCancel());
	}
	
	@Test(dependsOnMethods = { "verifyAfterClickingCancelOnPopup" })
	public void verifyPrintOptionWorking()throws Exception{
		Assert.assertTrue(printerFriendlyDocument.verifyPrintOptionIsWorking());
	}
	
}
