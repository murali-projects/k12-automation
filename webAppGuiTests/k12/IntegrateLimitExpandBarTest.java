package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateLimitExpandBar;

public class IntegrateLimitExpandBarTest extends BaseWebPageTest{
	private IntegrateLimitExpandBar integrateLimitExpandBar;
	private String searchTerm;

	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		integrateLimitExpandBar = new IntegrateLimitExpandBar();
		this.searchTerm = searchTerm;
	}
	
	@Test
	public void verifyLimitSearchNotPresentInHomepage() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLimitSearchNotPresentInHomepage());
	}
	
	@Test(dependsOnMethods = { "verifyLimitSearchNotPresentInHomepage" })
	public void verifyLimitSearchNotPresentInDetailPage() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLimitSearchNotPresentInDetailPage());
	}
	
	@Test(dependsOnMethods = { "verifyLimitSearchNotPresentInDetailPage" })
	public void verifyDisplaygroupsPresentAlongWithLimitSearch() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyDisplaygroupsPresentAlongWithLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyDisplaygroupsPresentAlongWithLimitSearch" })
	public void verifyViewAllResultsPresentAlongWithLimitSearch() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyViewAllResultsPresentAlongWithLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyViewAllResultsPresentAlongWithLimitSearch" })
	public void verifyBasicSearchPresentAlongWithLimitSearch() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyBasicSearchPresentAlongWithLimitSearch());
	}
	
	@Test(dependsOnMethods = { "verifyBasicSearchPresentAlongWithLimitSearch" })
	public void verifyLimitSearchPortletPresent() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLimitSearchPortletPresent());
	}
	
	@Test(dependsOnMethods = { "verifyLimitSearchPortletPresent" })
	public void verifyLimitBydocumentIsPresent() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLimitBydocumentIsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyLimitBydocumentIsPresent" })
	public void verifyMoreDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyMoreDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyMoreDisplayed" })
	public void verifyLessLink() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLessLink());
	}
	
	@Test(dependsOnMethods = { "verifyLessLink" })
	public void verifyLessLinkDisplaying5Items() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyLessLinkDisplaying5Items());
	}
	
	@Test(dependsOnMethods = { "verifyLessLinkDisplaying5Items" })
	public void verifyClickingOnTermsNavigatesPage() throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrateLimitExpandBar.verifyClickingOnTermsNavigatesPage());
	}
}
