package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ShareBookmarkWithSocialNetworkingSite;
import webPageContainers4Testing.SortByPublicationDates;

public class ShareBookmarkWithSocialNetworkingSiteTest extends BaseWebPageTest{

	private ShareBookmarkWithSocialNetworkingSite sharebookmark;
	
	public String searchTerm;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm)
			throws Exception {
		sharebookmark = new ShareBookmarkWithSocialNetworkingSite();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Parameters({"searchTerm"})
	@Test
	public void verifyShareToolAvailableInDetailPage(String searchTerm) throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyShareToolAvailableInDetailPage());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyShareToolAvailableInDetailPage" })
	public void verifyMouseOverShareOption(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyMouseOverShareOption());
				
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyMouseOverShareOption" })
	public void verifyContentIsSharedInNetwork(String searchTerm)throws Exception{
		//selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyContentIsSharedInNetwork());
				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyContentIsSharedInNetwork" })
	public void verifyMoreAndCloseOptionsInPopup(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyMoreAndCloseOptionsInPopup());
				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyMoreAndCloseOptionsInPopup" })
	public void verifyUserAbleToSearch(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyUserAbleToSearch());
				
	}
	
	
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyUserAbleToSearch" })
	public void verifyNoSearchTermFound(String searchTerm)throws Exception{
		//selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyNoSearchTermFound());
				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyNoSearchTermFound" })
	public void verifyEmptyFieldForNextSearch(String searchTerm)throws Exception{
		//selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(sharebookmark.verifyEmptyFieldForNextSearch());
				
	}
	
}
