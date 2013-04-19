package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewFirstNCharacters;

public class ViewFirstNCharactersTest extends BaseWebPageTest{
	private ViewFirstNCharacters viewFirstNCharacters;
	
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		viewFirstNCharacters = new ViewFirstNCharacters();
	}
	
	@Test
	public void verifyDescriptionIsPresent() throws Exception {
		Assert.assertTrue(viewFirstNCharacters.verifyDescriptionIsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyDescriptionIsPresent" })
	public void verifyDescriptionInDetailedPage() throws Exception {
		Assert.assertTrue(viewFirstNCharacters.verifyDescriptionInDetailedPage());
	}
	
	@Test(dependsOnMethods = { "verifyDescriptionInDetailedPage" })
	public void verifyDesriptionFromViewAllPage() throws Exception {
		Assert.assertTrue(viewFirstNCharacters.verifyDesriptionFromViewAllPage());
	}
	
	@Test(dependsOnMethods = { "verifyDesriptionFromViewAllPage" })
	public void verifyDescIsSameInBucketAndViewAllPage() throws Exception {
		Assert.assertTrue(viewFirstNCharacters.verifyDescIsSameInBucketAndViewAllPage());
	}
	
}
