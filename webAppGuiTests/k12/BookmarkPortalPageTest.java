package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.BookmarkPortalPage;

public class BookmarkPortalPageTest extends BaseWebPageTest{

	BookmarkPortalPage bookmarkPortalPage;
	
	@BeforeTest
	public void setUp() throws Exception{
		bookmarkPortalPage = new BookmarkPortalPage();
	}
	
@Test
public void basicsearch() throws InterruptedException{
	doBasicSearchUsingSearchTerm("war");
}
@Test(dependsOnMethods={"basicsearch"})
public void verifyBookmarkPortalIsPresent() throws InterruptedException{
	Assert.assertTrue(bookmarkPortalPage.verifyBookmarkPortalIsPresent());
}

@Test(dependsOnMethods={"verifyBookmarkPortalIsPresent"})
public void verifyBookmarkAfterAdvancedsearch() throws Exception{
	Assert.assertTrue(bookmarkPortalPage.verifyBookmarkAfterAdvancedsearch("war", "rain"));
}
@Test(dependsOnMethods={"verifyBookmarkAfterAdvancedsearch"})
public void verifyBookmarkPortalIsWorking() throws Exception{
	Assert.assertTrue(bookmarkPortalPage.verifyBookmarkPortalIsWorking());
}

}
