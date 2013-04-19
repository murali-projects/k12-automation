package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.BookmarkDocument;

public class BookmarkDocumentTest extends BaseWebPageTest {
	
	BookmarkDocument bookmarkDocument;
	
	@BeforeTest
	public void setUp() throws Exception{
		bookmarkDocument = new BookmarkDocument();
	}

	@Test
	public void verifyBookmarkLink() throws InterruptedException{
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(bookmarkDocument.verifyBookmarkLink());
	}
	
	@Test(dependsOnMethods={"verifyBookmarkLink"})
	public void verifyBookmarkLinkIsWorking() throws InterruptedException{
		Assert.assertTrue(bookmarkDocument.verifyBookmarkLinkIsWorking());
	}
   
	@Test(dependsOnMethods={"verifyBookmarkLinkIsWorking"})
	public void verifyBookmarkLinkUrlIsWorking() throws InterruptedException{
		Assert.assertTrue(bookmarkDocument.verifyBookmarkLinkUrlIsWorking());
	}

}
