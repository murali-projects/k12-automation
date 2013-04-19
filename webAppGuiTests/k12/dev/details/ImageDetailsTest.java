package k12.dev.details;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.PropertyReader;
import webPageContainers4Testing.dev.ImagesDetailsPortlet;
import webPageContainers4Testing.dev.ImagesPortlet;
import webPageElements4Testing.Image;
import webPageElements4Testing.Link;

public class ImageDetailsTest extends BaseDevWebPageTest {

	private PropertyReader props;

	private ImagesPortlet imagePortlet;
	private ImagesDetailsPortlet imageDetailsPortlet;
	private Link previewsPrevLink;
	private Link previewsNextLink;
	private Image previewPrevImage;
	private Image previewNextImage;

	public ImageDetailsTest() throws Exception {
		props = new PropertyReader("properties/default.dev.search.properties");
		imagePortlet = new ImagesPortlet();
		imageDetailsPortlet = new ImagesDetailsPortlet();
		previewsPrevLink = new Link(props.get("page_sr.portlet_imagesDetails.previewsprev.link.xpath"));
		previewsNextLink = new Link(props.get("page_sr.portlet_imagesDetails.previewsnext.link.xpath"));
		previewPrevImage = new Image(props.get("page_sr.portlet_imagesDetails.previewsprevimage.image.xpath"));
		previewNextImage = new Image(props.get("page_sr.portlet_imagesDetails.previewsnextimage.image.xpath"));
	}

	@Test
	public void verifyDocTitle() throws Exception {
		// this should return search results containing more than one page
		doBasicSearchUsingSearchTerm("war");
		String altTextinSRPage = imagePortlet.getImagesThumbnailImages()[0].getAltText();
		imagePortlet.navigateToFirstImage();
		String title = imageDetailsPortlet.getDocTitle().getLabelText();
		assertEquals(title, altTextinSRPage);
	}

	@Test(dependsOnMethods = { "verifyDocTitle" })
	public void verifyPreviewsPrevLinks() throws InterruptedException {
		//this should be replaced with a waitForCondition someday
		waitABit();
		String prevImageTitleBefore = previewPrevImage.getAltText();
		assertFalse(prevImageTitleBefore.trim().isEmpty());
		previewsPrevLink.ajaxClickAndWait();
		String prevImageTitleAfter = previewPrevImage.getAltText();
		assertFalse(prevImageTitleAfter.trim().isEmpty());
		assertFalse(prevImageTitleAfter.equals(prevImageTitleBefore));
	}

	@Test(dependsOnMethods = { "verifyPreviewsPrevLinks" })
	public void verifyPreviewsNextLinks() throws InterruptedException {
		String nextImageTitleBefore = previewNextImage.getAltText();
		assertFalse(nextImageTitleBefore.trim().isEmpty());
		previewsNextLink.ajaxClickAndWait();
		String nextImageTitleAfter = previewNextImage.getAltText();
		assertFalse(nextImageTitleAfter.trim().isEmpty());
		assertFalse(nextImageTitleBefore.equals(nextImageTitleAfter));

	}

	@Test(dependsOnMethods = { "verifyPreviewsNextLinks" })
	public void verifyPreviewsNextImages() throws InterruptedException {

		String nextImageTitleBefore = previewNextImage.getAltText();
		previewNextImage.ajaxClickAndWait();
		String title = imageDetailsPortlet.getDocTitle().getLabelText();
		assertEquals(nextImageTitleBefore, title);

	}

	@Test(dependsOnMethods = { "verifyPreviewsNextImages" })
	public void verifyPreviewsPrevImages() throws InterruptedException {

		String prevImageTitleBefore = previewPrevImage.getAltText();
		previewPrevImage.ajaxClickAndWait();
		String title = imageDetailsPortlet.getDocTitle().getLabelText();
		assertEquals(prevImageTitleBefore, title);

	}

	@Test(dependsOnMethods = { "verifyPreviewsPrevImages" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(imageDetailsPortlet.doesContentExistInClass(imageDetailsPortlet.getProperty("footerDiv"),
				"footerGaleLogo"));
	}
}
