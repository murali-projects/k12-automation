package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.UserSortInDropDown;

public class UserSortInDropDownTest extends BaseWebPageTest {

	private UserSortInDropDown userSortInDropDown;
	
	@BeforeTest
	public void setUp() throws Exception{
		userSortInDropDown = new UserSortInDropDown();
	}

	@Test
	public void search() throws Exception {
		doBasicSearchUsingSearchTerm("Aldehydes");
		userSortInDropDown.search("Aldehydes");
	}
	
	@Test(dependsOnMethods = {"search"})
	public void searchValidation()throws Exception{
		Assert.assertTrue(userSortInDropDown.searchValidation());
	}	

	@Test(dependsOnMethods = {"search"})
	public void checkSortByDropdownIsDisplayingValidOptions()throws Exception{
		Assert.assertTrue(userSortInDropDown.verifyOptionsInSortByDropDownIsDisplayed());
	}	
	
	@Test(dependsOnMethods = {"search"})
	public void confirmAllDisplayGroupsOnThePageOptionIsSelected() throws Exception{
		Assert.assertTrue(userSortInDropDown.confirmAllDisplayGroupsOnThePageOptionIsSelected());
	}
	
	@Test(dependsOnMethods = {"confirmAllDisplayGroupsOnThePageOptionIsSelected"})
	public void validateSortResultsForAllDisplayGroupsOnThePage() throws Exception{
		Assert.assertTrue(userSortInDropDown.validateSortResultsForAllDisplayGroupsOnThePage());
	}	

	@Test(dependsOnMethods = {"search"})
	public void confirmDescendingPublicationDateIsSelected() throws Exception{
		Assert.assertTrue(userSortInDropDown.confirmDescendingPublicationDateIsSelected());
	}
	
	@Test(dependsOnMethods = {"confirmDescendingPublicationDateIsSelected"})
	public void validateSortResultsForDescendingPublicationDate() throws Exception{
		Assert.assertTrue(userSortInDropDown.validateSortResultsForDescendingPublicationDate());
	}		
	
	@Test(dependsOnMethods = {"search"})
	public void confirmDescendingRelevanceIsSelected() throws Exception{
		Assert.assertTrue(userSortInDropDown.confirmDescendingRelevanceIsSelected());
	}

	@Test(dependsOnMethods = {"confirmDescendingRelevanceIsSelected"})
	public void validateSortResultsForDescendingRelevance() throws Exception{
		Assert.assertTrue(userSortInDropDown.validateSortResultsForDescendingRelevance());
	}	

	@Test(dependsOnMethods = {"search"})
	public void confirmAscendingContentLevelIsSelected() throws Exception{
		Assert.assertTrue(userSortInDropDown.confirmAscendingContentLevelIsSelected());
	}

	@Test(dependsOnMethods = {"confirmAscendingContentLevelIsSelected"})
	public void validateSortResultsForAscendingContentLevel() throws Exception{
		Assert.assertTrue(userSortInDropDown.validateSortResultsForAscendingContentLevel());
	}		
	
	@Test(dependsOnMethods = {"search"})
	public void confirmResetToDefaultIsSelected() throws Exception{
		Assert.assertTrue(userSortInDropDown.confirmResetToDefaultIsSelected());
	}	
	
	@Test(dependsOnMethods = {"confirmResetToDefaultIsSelected"})
	public void validateSortResultsForDefaultSorts() throws Exception{
		Assert.assertTrue(userSortInDropDown.validateSortResultsForDefaultSorts());
	}	
	
}
