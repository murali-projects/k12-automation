package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.NewsDisplayGroup;

public class NewsDisplayGroupTest extends BaseWebPageTest{
	private NewsDisplayGroup newsDisplayGroup;
	private String searchTerm;
	private String displayGroupName;
	private String sortBy;

	@Parameters( { "searchTerm", "displayGroupName", "sortBy" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroupName, String sortBy) throws Exception {
		newsDisplayGroup = new NewsDisplayGroup();
		this.searchTerm = searchTerm;
		this.displayGroupName = displayGroupName;
		this.sortBy = sortBy;
	}
	
	@Test
	public void verifyNewsDisplayGroupPresent() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(newsDisplayGroup.verifyNewsDisplayGroupPresent());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsTextIsDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyElementDisplayedOnPage("news_label"));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyElementDisplayedOnPage("news_viewAll"));
	}

	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsCountIsInlineWithViewAll() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyCountIsInLineWithViewAll());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyViewAllIsLink() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyViewAllIsLink());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyViewAllIsWorking() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyViewAllLinkIsWorking());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyThreeContentLinksAreDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsContentLinksArePresent());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsArticlesAreSortedWithPubDate() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validateSortOrder(searchTerm, displayGroupName, sortBy));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyPublisherNames() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validatePublisherNames(searchTerm, displayGroupName, sortBy));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyDocTypes() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validateDocTypes(searchTerm, displayGroupName, sortBy));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void validateNewsContentLinks() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validateSortOrder(searchTerm, displayGroupName, sortBy));
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsTitlesAreLinks() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsTitlesAreLinks());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsIconIsInLineWithNewsTitle() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsIconIsInLineWithNewsTitle());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyContentAfterClickingNewsLink() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyContentAfterClickingNewsLink());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyLinkBackOptionAvailable() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyLinkBackOptionAvailable());
	}
	
	@Test
	// @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyContentAfterClickingNewsIcon() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyContentAfterClickingNewsIcon());
	}
}
