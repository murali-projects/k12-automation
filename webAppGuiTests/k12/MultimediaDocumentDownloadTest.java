package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.MultimediaDocumentDownload;

public class MultimediaDocumentDownloadTest extends BaseWebPageTest{
	private MultimediaDocumentDownload multimediaDocumentDownload;
	private String videos;
	private String audio;
	private String images;

	@Parameters( { "searchTerm", "videos", "audio", "images"})
	@BeforeTest
	public void setUp(String searchTerm, String videos, String audio, String images) throws Exception {
		multimediaDocumentDownload = new MultimediaDocumentDownload(searchTerm);
		this.videos = videos;
		this.audio = audio;
		this.images = images;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyToolsAvailableForVideos() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDocumentToolsArePresent(videos));
	}

	@Test(dependsOnMethods = "verifyToolsAvailableForVideos")
	public void verifyToolsAreAccessible() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.verifyToolsAreAccessible());
	}
	
	@Test(dependsOnMethods = "verifyToolsAreAccessible")
	public void verifyPrintDocumentForVideos() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDetailsInPrintPreviewDocument());
	}
	
	@Test(dependsOnMethods = "verifyPrintDocumentForVideos")
	public void checkDetailsForDownloadAndTranslateForVideos() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDetailsForDownloadAndTranslate(videos));
	}
	
	@Test(dependsOnMethods = "checkDetailsForDownloadAndTranslateForVideos")
	public void verifyToolsAvailableForAudio() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDocumentToolsArePresent(audio));
	}
	
	@Test(dependsOnMethods = "verifyToolsAvailableForAudio")
	public void verifyPrintDocumentForAudio() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDetailsInPrintPreviewDocument());
	}
	
	@Test(dependsOnMethods = "verifyPrintDocumentForAudio")
	public void verifyToolsAvailableForImages() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDocumentToolsArePresent(images));
	}
	
	@Test(dependsOnMethods = "verifyToolsAvailableForImages")
	public void verifyPrintDocumentForImages() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.checkDetailsInPrintPreviewDocument());
	}
	
	@Test(dependsOnMethods = "verifyPrintDocumentForImages")
	public void verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument());
	}
	
	@Test(dependsOnMethods = "verifyRelatedTopicsAndToolsNotAvailableInHtmlDocument")
	public void verifyNextPreviousLinksNotAvailableInHtmlDocument() throws Exception {
		Assert.assertTrue(multimediaDocumentDownload.verifyNextPreviousLinksNotAvailableInHtmlDocument());
	}
}
