package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortSearchResultsFromSelectionList;

public class SortSearchResultsFromSelectionListTest extends BaseWebPageTest {

	/*public SortSearchResultsFromSelectionListTest() throws Exception {  
		super();
	}*/
private SortSearchResultsFromSelectionList sortSearchResultsFromSelectionList;
	
	@BeforeTest
	public void setUp() throws Exception{
		sortSearchResultsFromSelectionList = new SortSearchResultsFromSelectionList();
	}
	@Test
	public void searchValidation()throws Exception{
		Assert.assertTrue(sortSearchResultsFromSelectionList.searchValidation());
	}	

	@Test
	public void checkSortByDropdownIsDisplayingValidOptions()throws Exception{
		Assert.assertTrue(sortSearchResultsFromSelectionList.onClickSortByDropDownDisplayOptions());
	}	
	
/*	@Test
	public void verifySelectedValueInTheSortByDropDown()throws Exception{
		Assert.assertTrue(sortSearchResultsFromSelectionList.validateSelectedOptionInSortByDropDown());
	}
	
	@Test
	public void checkResultsAreDisplayedWhenRightArrowButtonIsClicked()throws Exception{
		Assert.assertTrue(sortSearchResultsFromSelectionList.clickRightArrowButton());
	}

	@Test
	public void verifySortResultCountMatches()throws Exception{
		Assert.assertTrue(sortSearchResultsFromSelectionList.validateSortResultsCount());
	}	
	*/
}
