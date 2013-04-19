package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewVideoDocument;

public class ViewVideoDocumentTest extends BaseWebPageTest{
	
private ViewVideoDocument viewVideoDocument;
	
	@BeforeTest
	public void setUp() throws Exception{
		viewVideoDocument = new ViewVideoDocument();
	} 
	
	@Test
	public void searchIntegrateVideoDisplayGroup() throws Exception{
		doBasicSearchUsingSearchTerm("obama");
	}
	
	@Test
	public void verifyVideoTopicPortalTabIsDisplayed()throws Exception{
	Assert.assertTrue(viewVideoDocument.verifyTopicPortalTabIsPresent());
		}
	@Test
	public void verifyVideoTopicPortalTabIsWorking()throws Exception{
	Assert.assertTrue(viewVideoDocument.verifyTopicPortalTabIsWorking());
		}
	@Test
	public void verifyContentLinksIsDisplayed()throws Exception{
	Assert.assertTrue(viewVideoDocument.verifyContentLinksIsDisplayed());
		}
	@Test
	public void verifyContentLinksIsWorking()throws Exception{
	Assert.assertTrue(viewVideoDocument.verifyTopicPortalTabIsWorking());
		}
	
	
	
}
