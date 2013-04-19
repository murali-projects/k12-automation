package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewSearchResultsPage;

public class ViewSearchResultsPageTest extends BaseWebPageTest

{
	ViewSearchResultsPage viewSearchResultsPage;
@BeforeTest
public void setUP() throws Exception{
	
	viewSearchResultsPage = new ViewSearchResultsPage();
	
}
@Test
public void verifyDisplayGroupsIsDisplayed() throws Exception {
	doBasicSearchUsingSearchTerm("war");
	Assert.assertTrue(viewSearchResultsPage.verifyDisplayGroups());
}
@Test(dependsOnMethods={"verifyDisplayGroupsIsDisplayed"})
public void verifyViewAllIsInlineWithDisplayGroup() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyViewAll());
}

@Test(dependsOnMethods={"verifyViewAllIsInlineWithDisplayGroup"})
public void verifyCountIsInlineWithViewAll() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyCount());
}

@Test(dependsOnMethods={"verifyCountIsInlineWithViewAll"})
public void verifyViewAllIsLink() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyViewAllIsLink());
}
@Test(dependsOnMethods={"verifyViewAllIsLink"})
public void verifyViewAllIsLinkIsWorking() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyViewAllIsLinkIsWorking());
}
@Test(dependsOnMethods={"verifyViewAllIsLinkIsWorking"})
public void verifyClickedTabIsHighlited() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyClickedTabIsHighlited());
}
@Test(dependsOnMethods={"verifyClickedTabIsHighlited"})
public void verifyTabsIsDisplayed() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyTabsIsDisplayed());
}
@Test(dependsOnMethods={"verifyTabsIsDisplayed"})
public void verifyTabsChanged() throws Exception {
	Assert.assertTrue(viewSearchResultsPage.verifyTabsChanged());
}

@Test(dependsOnMethods={"verifyTabsChanged"})
public void verifyErrorMessage() throws Exception {
	doBasicSearchUsingSearchTerm("abcdefgh");
	Assert.assertTrue(viewSearchResultsPage.verifyErrorMessage());
}

}
