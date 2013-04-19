package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewMarkupInDocumentBody;

public class ViewMarkupInDocumentBodyTest extends BaseWebPageTest{
	private ViewMarkupInDocumentBody  viewMarkupInDocumentBody;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		viewMarkupInDocumentBody = new ViewMarkupInDocumentBody();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyMarkupsPresentInDocument() throws Exception {
		Assert.assertTrue(viewMarkupInDocumentBody.verifyMarkupsPresentInDocument());
	}

	@Test(dependsOnMethods = { "verifyMarkupsPresentInDocument" })
	public void verifyBoldLettersPresent() throws Exception {
		Assert.assertTrue(viewMarkupInDocumentBody.verifyBoldLettersPresent());
	}
	
	@Test(dependsOnMethods = { "verifyBoldLettersPresent" })
	public void verifyItalicsPresent() throws Exception {
		Assert.assertTrue(viewMarkupInDocumentBody.verifyItalicsPresent());
	}
	
	@Test(dependsOnMethods = { "verifyItalicsPresent" })
	public void verifyHyperlinksPresent() throws Exception {
		Assert.assertTrue(viewMarkupInDocumentBody.verifyHyperlinksPresent());
	}
}
