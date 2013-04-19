package k12.dev.searchresults;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.VideosPortlet;

public class VideosSearchResultsTest extends BaseDevWebPageTest {
	private VideosPortlet videoPortlet;

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
	}

	@BeforeTest
	public void setUp() throws Exception {
		videoPortlet = new VideosPortlet();
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void isVideoPortletPresent() throws Exception {
		assertTrue(videoPortlet.getVideoPortletDiv().isPresent());
	}

	@Test(dependsOnMethods = { "isVideoPortletPresent" })
	public void doesPortletHaveResults() throws Exception {
		int noOfDocsinVideoPorlet = 3;
		assertEquals(noOfDocsinVideoPorlet, videoPortlet.getVideosThumbnailImagesLink().getLinkCount());
		assertEquals(noOfDocsinVideoPorlet, videoPortlet.getVideosDocTileLink().getLinkCount());

	}

	@Test(dependsOnMethods = { "doesPortletHaveResults" })
	public void viewAllLinkIsDisplayed() throws Exception {
		assertTrue(videoPortlet.getViewAllLink().isPresent());
	}

	@Test(dependsOnMethods = { "viewAllLinkIsDisplayed" })
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(videoPortlet.doesContentExistInClass(videoPortlet.getProperty("footerDiv"),
				"footerGaleLogo"));
	}
}
