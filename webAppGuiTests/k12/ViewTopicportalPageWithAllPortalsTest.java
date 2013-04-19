package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import webPageContainers4Testing.TranslateTextDocument;
import webPageContainers4Testing.ViewTopicportalPageWithAllPortals;

public class ViewTopicportalPageWithAllPortalsTest extends BaseWebPageTest {
	
	private ViewTopicportalPageWithAllPortals viewtopicportalpage;
	
	
 	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
   		doBasicSearchUsingSearchTerm(searchTerm);
   		viewtopicportalpage= new ViewTopicportalPageWithAllPortals();
    }
@Parameters({"searchTerm"})
	@Test
	public void verifyUserisInportalPageAfterclickingTopic(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyUserisInportalPageAfterclickingTopic());
				
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyUserisInportalPageAfterclickingTopic")
	public void verifyPortletsInPortalPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyPortletsInPortalPage());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyPortletsInPortalPage")
	public void verifyPageforKeywordNotHavingPortalPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyPageforKeywordNotHavingPortalPage());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyPageforKeywordNotHavingPortalPage")
	public void verifyPortletsFromOceanServices(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyPortletsFromOceanServices());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyPortletsFromOceanServices")
	public void verifyDifferentMethodsForNavigationToPortalPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyDifferentMethodsForNavigationToPortalPage());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyDifferentMethodsForNavigationToPortalPage")
	public void verifyBasicSearchOptionFromPortalPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyBasicSearchOptionFromPortalPage());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyBasicSearchOptionFromPortalPage")
	public void verifyBreadcrumb(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(viewtopicportalpage.verifyBreadcrumb());
	}
	
	
	
	

}
