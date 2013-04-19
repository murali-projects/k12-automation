package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.LinksWithinDocumentBody;

public class LinksWithinDocumentBodyTest extends BaseWebPageTest{
	private LinksWithinDocumentBody linksWithinDocumentBody;

	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		linksWithinDocumentBody = new LinksWithinDocumentBody();
	}

	@Test
	public void verifyContentHasHyperlinks() throws Exception {
		Assert.assertTrue(linksWithinDocumentBody.verifyContentHasHyperlinks());
	}
	
	@Test(dependsOnMethods = {"verifyContentHasHyperlinks"})
	public void verifyHyperlinksAreAccessible() throws Exception {
		Assert.assertTrue(linksWithinDocumentBody.verifyHyperlinksAreAccessible());
	}
	
	@Test(dependsOnMethods = {"verifyHyperlinksAreAccessible"})
	public void verifyUserIsInSameWindow() throws Exception {
		Assert.assertTrue(linksWithinDocumentBody.verifyUserIsInSameWindow());
	}
	
	@Test(dependsOnMethods = {"verifyUserIsInSameWindow"})
	public void verifyLinkColorChanged() throws Exception {
		Assert.assertTrue(linksWithinDocumentBody.verifyLinkColorChanged());
	}
	
	@Test(dependsOnMethods = {"verifyLinkColorChanged"})
	public void verifyOtherHyperlinksAreAccessible() throws Exception {
		Assert.assertTrue(linksWithinDocumentBody.verifyOtherHyperlinksAreAccessible());
	}
}
