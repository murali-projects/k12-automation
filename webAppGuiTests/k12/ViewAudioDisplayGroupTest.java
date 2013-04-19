package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAudioArticlesDisplayGroup;

public class ViewAudioDisplayGroupTest extends BaseWebPageTest{
	private ViewAudioArticlesDisplayGroup viewAudioArticlesDisplayGroup;
	private String searchTerm;
	
	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception{
		viewAudioArticlesDisplayGroup = new ViewAudioArticlesDisplayGroup(searchTerm);
		this.searchTerm=searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void checkAudioArticlesDisplayGroupIsPresent() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.checkAudioArticlesDisplayGroupIsPresent());
	}
	
	@Test(dependsOnMethods = { "checkAudioArticlesDisplayGroupIsPresent" })
	public void checkAudioContentLinksArePresent() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.checkAudioContentLinksArePresent());
	}
	
	@Test(dependsOnMethods = { "checkAudioContentLinksArePresent" })
	public void verifyAudioArticleNameIsLink() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyAudioArticleNameIsLink());
	}
	
	@Test(dependsOnMethods = { "verifyAudioArticleNameIsLink" })
	public void verifyAudioIconIsDisplayed() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyAudioIconIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyAudioArticleNameIsLink" })
	public void verifyAudioArticlesCount() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyAudioArticlesCount(searchTerm));
	}
	
	@Test(dependsOnMethods = { "checkAudioContentLinksArePresent" })
	public void verifyPublicatinNamesArePresent() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyPublicatinNamesArePresent());
	}
	
	@Test(dependsOnMethods = { "checkAudioContentLinksArePresent" })
	public void verifyPublicatinDatesArePresent() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyPublicatinDatesArePresent());
	}
	
	@Test(dependsOnMethods = { "verifyPublicatinDatesArePresent" })
	public void verifyAudioArticlesSortedByPublicationDates() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyAudioArticlesSortedByPublicationDates());
	}
	
	@Test(dependsOnMethods = { "verifyAudioArticlesSortedByPublicationDates" })
	public void verifyAudioContentItemIsViewed() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyContentItemIsViewed());
	}
	
	@Test(dependsOnMethods = { "checkAudioContentLinksArePresent" })
	public void verifyDetailedViewIsDisplayed() throws Exception{
		Assert.assertTrue(viewAudioArticlesDisplayGroup.verifyDetailedViewIsDisplayed());
	}
	
	@Parameters( { "displyGroupSearchTerm"})
	@Test(dependsOnMethods = { "verifyDetailedViewIsDisplayed" })
	public void checkAudioArticlesDisplayGroupIsNotPresent(String displyGroupSearchTerm) throws Exception{
		doBasicSearchUsingSearchTerm(displyGroupSearchTerm);
		Assert.assertTrue(viewAudioArticlesDisplayGroup.checkAudioArticlesDisplayGroupIsNotPresent());
	}
	
	
	@Test
	@Parameters( { "displyGroupSearchTerm"})
	public void checkAudioArticlesDisplayGroupIsNotPresentForDev(String displyGroupSearchTerm) throws Exception{
		doBasicSearchUsingSearchTerm(displyGroupSearchTerm);
		Assert.assertTrue(viewAudioArticlesDisplayGroup.checkAudioArticlesDisplayGroupIsNotPresent());
	}
}
