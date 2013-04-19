package k12.dev.searchresults;

import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.ToolsDisplayGroup;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;

public class BookmarkSearchResultsTest extends BaseDevWebPageTest {

	private ToolsDisplayGroup toolsDisplayGroup;

	@BeforeTest
	public void setUp() throws Exception {
		toolsDisplayGroup = new ToolsDisplayGroup("reference");
	}

	@Test
	public void searchValidationMessageIsDisplayed() throws Exception {
		doBasicSearchUsingSearchTerm("war");
 
		Assert.assertTrue(toolsDisplayGroup.bookmarkLink.isPresent());
	}

	@Test(dependsOnMethods = { "searchValidationMessageIsDisplayed" })
	public void verifyBookmarks() throws Exception {
		Link bookmarkLink = toolsDisplayGroup.bookmarkLink;
		assertTrue(bookmarkLink.isPresent());
		bookmarkLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.bookmarkDialogLocator);
		PageButton button = toolsDisplayGroup.bookmarkDialogCancelButton;
		assertTrue(button.isPresent());
		button.click();
	}	
	
}
