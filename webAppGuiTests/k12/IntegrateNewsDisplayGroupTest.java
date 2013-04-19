package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateNewsDisplayGroup;

public class IntegrateNewsDisplayGroupTest extends BaseWebPageTest{
	private IntegrateNewsDisplayGroup newsDisplayGroup;
	private String searchTerm;
	private String displayGroupName;
	private String sortBy;

	@Parameters( { "searchTerm", "displayGroupName", "sortBy" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroupName, String sortBy) throws Exception {
		newsDisplayGroup = new IntegrateNewsDisplayGroup();
		this.searchTerm = searchTerm;
		this.displayGroupName = displayGroupName;
		this.sortBy = sortBy;
	}
	
	@Test
	public void verifyNewsDisplayGroupPresent() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(newsDisplayGroup.verifyNewsDisplayGroupPresent());
	}
	
	 @Test(dependsOnMethods = { "verifyNewsDisplayGroupPresent" })
	public void verifyNewsTextIsDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyElementDisplayedOnPage("news_label"));
	}
	
	 @Test(dependsOnMethods = { "verifyNewsTextIsDisplayed" })
	public void verifyGroupNameIsInCaps() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyGroupName());
	}
	
	 @Test(dependsOnMethods = { "verifyGroupNameIsInCaps" })
	public void verifyNewsViewAllIsDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyElementDisplayedOnPage("news_viewAll"));
	}

	 @Test(dependsOnMethods = { "verifyNewsViewAllIsDisplayed" })
	public void verifyNewsCountIsInlineWithViewAll() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyCountIsInLineWithViewAll());
	}
	
	 @Test(dependsOnMethods = { "verifyNewsCountIsInlineWithViewAll" })
	public void verifyViewAllIsLink() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyViewAllIsLink());
	}
	
	 @Test(dependsOnMethods = { "verifyViewAllIsLink" })
	public void verifyThreeContentLinksAreDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsContentLinksArePresent());
	}

	 @Test(dependsOnMethods = { "verifyThreeContentLinksAreDisplayed" })
	public void verifyNewsArticlesAreSortedWithPubDate() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validateSortOrder(searchTerm, displayGroupName, sortBy));
	}
	
	 @Test(dependsOnMethods = { "verifyThreeContentLinksAreDisplayed" })
	public void verifyPublisherNames() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validatePublisherNames(searchTerm, displayGroupName, sortBy));
	}
	
	 @Test(dependsOnMethods = { "verifyThreeContentLinksAreDisplayed" })
	public void validateNewsContentLinks() throws Exception {
		Assert.assertTrue(newsDisplayGroup.validateSortOrder(searchTerm, displayGroupName, sortBy));
	}
	
	 @Test(dependsOnMethods = { "verifyThreeContentLinksAreDisplayed" })
	public void verifyNewsTitlesAreLinks() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsTitlesAreLinks());
	}
	
	 @Test(dependsOnMethods = { "verifyNewsTitlesAreLinks" })
	public void verifyPublicationDetailsAreDisplayed() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyPublicationDetailsAreDisplayed());
	}
	
	 @Test(dependsOnMethods = { "verifyPublicationDetailsAreDisplayed" })
	public void verifyViewAllIsWorking() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyViewAllLinkIsWorking());
	}
	 
	 @Test(dependsOnMethods = { "verifyViewAllIsWorking" })
		public void verifyDocTypes() throws Exception {
			Assert.assertTrue(newsDisplayGroup.validateDocTypes(searchTerm, displayGroupName, sortBy));
		}
	
	 @Test(dependsOnMethods = { "verifyViewAllIsWorking" })
	public void verifyIntroTextInlineWithDocTitle() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyIntroTextInlineWithDocTitle());
	}
	
	 @Test(dependsOnMethods = { "verifyIntroTextInlineWithDocTitle" })
	public void verifyNewsLinkOpensInNewWindow() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsLinkOpensInNewWindow());
	}
	
	 @Test(dependsOnMethods = { "verifyNewsLinkOpensInNewWindow" })
	public void verifyContentAfterClickingNewsLink() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyContentAfterClickingNewsLink());
	}
	
	 @Test(dependsOnMethods = { "verifyContentAfterClickingNewsLink" })
	public void verifyNewsIconIsInLineWithNewsTitle() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyNewsIconIsInLineWithNewsTitle());
	}
	 @Test(dependsOnMethods = { "verifyNewsIconIsInLineWithNewsTitle" })
	public void verifyLinkBackOptionAvailable() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyLinkBackOptionAvailable());
	}
	
	 @Test(dependsOnMethods = { "verifyNewsIconIsInLineWithNewsTitle" })
	public void verifyContentAfterClickingNewsIcon() throws Exception {
		Assert.assertTrue(newsDisplayGroup.verifyContentAfterClickingNewsIcon());
	}
}
