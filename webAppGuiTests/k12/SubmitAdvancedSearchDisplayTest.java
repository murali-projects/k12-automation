package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SubmitAdvancedSearchDisplay;

public class SubmitAdvancedSearchDisplayTest extends BaseWebPageTest{
	private SubmitAdvancedSearchDisplay advancedSearch;

	@BeforeTest
	public void setUp() throws Exception{
		advancedSearch = new SubmitAdvancedSearchDisplay();
	}
	
	@Test
	public void verifyAdvancedSearchOptionIsAccessible()throws Exception{
		Assert.assertTrue(advancedSearch.verifyAdvancedSearchOptionIsAccessible());
	}
	
	@Test(dependsOnMethods = "verifyAdvancedSearchOptionIsAccessible")
	public void verifyNoErrorMsgIsisplayed()throws Exception{
		Assert.assertTrue(advancedSearch.verifyNoErrorMsgIsisplayed());
	}
	
	@Test(dependsOnMethods = "verifyNoErrorMsgIsisplayed")
	public void verifyAdvanceSearchIsAccessibleAfterBasicSearch()throws Exception{
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(advancedSearch.verifyAdvancedSearchOptionIsAccessible());
	}
	
	@Test(dependsOnMethods = "verifyAdvanceSearchIsAccessibleAfterBasicSearch")
	public void verifyLimitToCheckboxesAreUnchecked()throws Exception{
		Assert.assertTrue(advancedSearch.verifyLimitToCheckboxesAreUnchecked());
	}
	
	@Test(dependsOnMethods = "verifyLimitToCheckboxesAreUnchecked")
	public void verifyDisplayGroupCheckboxesPresent()throws Exception{
		Assert.assertTrue(advancedSearch.verifyDisplayGroupCheckboxesPresent());
	}
	
	@Test(dependsOnMethods = "verifyLimitToCheckboxesAreUnchecked")
	public void verifyDocTypeOptions()throws Exception{
		Assert.assertTrue(advancedSearch.verifyDocTypeOptions());
	}
	
	@Test(dependsOnMethods = "verifyDocTypeOptions")
	public void verifyContentLevelCheckboxesPresent()throws Exception{
		Assert.assertTrue(advancedSearch.verifyContentLevelCheckboxesPresent());
	}
	
	@Test(dependsOnMethods = "verifyContentLevelCheckboxesPresent")
	public void verifyTwoSearchButtonsPresent()throws Exception{
		Assert.assertTrue(advancedSearch.verifyTwoSearchButtonsPresent());
	}
	
	@Test(dependsOnMethods = "verifyTwoSearchButtonsPresent")
	public void verifyLexileScoreInstructions()throws Exception{
		Assert.assertTrue(advancedSearch.verifyLexileScoreInstructions());
	}
	
	@Test(dependsOnMethods = "verifyLexileScoreInstructions")
	public void dateDropdownBoxesPresent()throws Exception{
		Assert.assertTrue(advancedSearch.dateDropdownBoxesPresent());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods = "dateDropdownBoxesPresent")
	public void isSearchByIndexWorking(String searchTerm)throws Exception{
		Assert.assertTrue(advancedSearch.isSearchByIndexWorking(searchTerm));
	}
}
