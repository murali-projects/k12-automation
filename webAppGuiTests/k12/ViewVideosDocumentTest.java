package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import webPageContainers4Testing.ViewVideosDocument;

public class ViewVideosDocumentTest extends BaseWebPageTest {
	   public ViewVideosDocument viewvideosDocument;
		
	   	@Parameters({"searchTerm"})
		@BeforeTest
		public void setUp(String searchTerm) throws Exception {
	   		doBasicSearchUsingSearchTerm(searchTerm);
	   		viewvideosDocument= new ViewVideosDocument();
	    }
	   	
	   	@Parameters({"searchTerm"})
		@Test
		public void clickDocumentVideoDisplayGroup(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(viewvideosDocument.clickDocumentVideoDisplayGroup());
					
		}
	   	
	   	@Parameters({"searchTerm"})
		@Test (dependsOnMethods= { "clickDocumentVideoDisplayGroup" })
		public void verifyDocumentTitleInDetailedPage(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(viewvideosDocument.verifyDocumentTitleInDetailedPage());
					
		}
		@Parameters({"searchTerm"})
		@Test (dependsOnMethods={"verifyDocumentTitleInDetailedPage"})
		public void verifyVideoPlayedWithoutErrors(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(viewvideosDocument.verifyVideoPlayedWithoutErrors());
					
		}
		
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods={"verifyDocumentTitleInDetailedPage"})
		public void verifyVideoCaptionAvailable(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(viewvideosDocument.verifyVideoCaptionAvailable());
					
		}
	   	
		
		@Parameters({"searchTerm"})
		@Test(dependsOnMethods={"verifyVideoCaptionAvailable"})
		public void verifyVideoFromViewAll(String searchTerm)throws Exception{
			doBasicSearchUsingSearchTerm(searchTerm);
			Assert.assertTrue(viewvideosDocument.verifyVideoFromViewAll());
					
		}
}
