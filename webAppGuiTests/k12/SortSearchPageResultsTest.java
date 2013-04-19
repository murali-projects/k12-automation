package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortSearchPageResults;

public class SortSearchPageResultsTest extends BaseWebPageTest {

	private SortSearchPageResults sortSearchPageResults;
	
	@BeforeTest
	public void setUp() throws Exception{
		sortSearchPageResults = new SortSearchPageResults();
	}
	
	@Test
	public void searchValidationMessageAndDisplayGroupsIsDisplayed()throws Exception{
		Assert.assertTrue(sortSearchPageResults.searchValidation());
	}	

	@Test
	public void clickDocTitleCheckBoxInSortTab()throws Exception{
		Assert.assertTrue(sortSearchPageResults.clickDocTitleCheckBox());
	}	
	
	@Test
	public void verifyDocTitleCheckBoxIsChecked()throws Exception{
		Assert.assertTrue(sortSearchPageResults.isCheckBoxChecked());
	}	
	
	@Test
	public void clickApplyButtonIsClicked()throws Exception{
		Assert.assertTrue(sortSearchPageResults.isApplyButtonClicked());
	}	

	@Test
	public void checkSortResultsMatch()throws Exception{
		Assert.assertTrue(sortSearchPageResults.validateSortedResults());
	}	
	
}
