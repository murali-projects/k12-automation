package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.PresentationMarkupInDocumentTitle;

public class PresentationMarkupInDocumentTitleTest extends BaseWebPageTest {

	private PresentationMarkupInDocumentTitle markupDocumentTitle;
	private String displayName;
	
	@Parameters( { "displayName" })
	@BeforeTest
	public void setUp(String displayName) throws Exception{
		markupDocumentTitle = new PresentationMarkupInDocumentTitle();
		this.displayName=displayName;
	}
	
	@Parameters( { "searchTerm" })
	@Test
	public void basicSearch(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test(dependsOnMethods = { "basicSearch" })
	public void verifyDocumentTitleIsBold()throws Exception {
		Assert.assertTrue(markupDocumentTitle.verifyDocumentTitleIsBold(displayName));
	}
	
	@Test(dependsOnMethods = { "basicSearch" })
	public void verifyDocumentTitleIsHyperLinked()throws Exception {
		Assert.assertTrue(markupDocumentTitle.verifyDocumentTitleIsHyperLinked(displayName));
	}
}
