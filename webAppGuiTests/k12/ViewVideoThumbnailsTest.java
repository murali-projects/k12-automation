package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewVideoThumbnails;

public class ViewVideoThumbnailsTest extends BaseWebPageTest{
	private ViewVideoThumbnails viewVideoThumbnails;
	private String searchTerm;

	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		viewVideoThumbnails = new ViewVideoThumbnails();
		doBasicSearchUsingSearchTerm(searchTerm);
		this.searchTerm = searchTerm;
	}

	@Test
	public void verifyThumbnailsPresentFromViewAllPage() throws Exception {
		Assert.assertTrue(viewVideoThumbnails.verifyThumbnailsPresentFromViewAllPage());
	}

	@Test(dependsOnMethods = { "verifyThumbnailsPresentFromViewAllPage" })
	public void verifyThumbnailsPresentAfterNavigation() throws Exception {
		Assert.assertTrue(viewVideoThumbnails
				.verifyThumbnailsPresentAfterNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyThumbnailsPresentAfterNavigation" })
	public void verifyThumbnailInDetailPageFromViewAll() throws Exception {
		Assert.assertTrue(viewVideoThumbnails.verifyThumbnailInDetailPageFromViewAll());
	}
	
	@Test(dependsOnMethods = { "verifyThumbnailInDetailPageFromViewAll" })
	public void verifyThumbnailInDetailPageFromSearchResults() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewVideoThumbnails.verifyThumbnailInDetailPageFromSearchResults());
	}
}
