package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateWebsitesFavIconsOnViewAllPage;

public class IntegrateWebsitesFavIconsOnViewAllPageTest extends BaseWebPageTest{

	public IntegrateWebsitesFavIconsOnViewAllPage integratewebsitesfavicons;
	

	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
   		doBasicSearchUsingSearchTerm(searchTerm);
   		integratewebsitesfavicons= new IntegrateWebsitesFavIconsOnViewAllPage();
	
	}
	
	@Parameters({"searchTerm"})
	@Test
	public void verifyfaviconsOnViewAllPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integratewebsitesfavicons.verifyfaviconsOnViewAllPage());
				
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyfaviconsOnViewAllPage" })
	public void verifyfaviconsNotPresentInSearchResultsPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integratewebsitesfavicons.verifyfaviconsNotPresentInSearchResultsPage());
				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyfaviconsNotPresentInSearchResultsPage" })
	public void verifyFavIconsForAllDisplayGroupsinViewAllpage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integratewebsitesfavicons.verifyFavIconsForAllDisplayGroupsinViewAllpage());				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyFavIconsForAllDisplayGroupsinViewAllpage" })
	public void verifyFaviconsOnLeftsideOfRecord(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integratewebsitesfavicons.verifyFaviconsOnLeftsideOfRecord());				
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = { "verifyFaviconsOnLeftsideOfRecord" })
	public void verifyDisplayOfGenericIcon(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integratewebsitesfavicons.verifyDisplayOfGenericIcon());				
	}
	
	
	
	
	
}
