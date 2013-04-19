package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitTermHighlighting;

public class HitTermHighlightingTest extends BaseWebPageTest{

	private HitTermHighlighting highlightTerm;
	private String displayGroup;
	private String searchTerm;
	
	@Parameters( { "searchTerm","displayGroup"})
	@BeforeTest
	public void setUp(String searchTerm,String displayGroup) throws Exception {
		highlightTerm = new HitTermHighlighting();
		this.displayGroup=displayGroup;
		this.searchTerm=searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyHighlightedTermsInDocument()throws Exception {
		Assert.assertTrue(highlightTerm.verifyHighlightedTermsInDocument(displayGroup,searchTerm));
	}
	
	@Test
	public void verifyHighlightedTermsInDocumentFromViewAll()throws Exception {
		Assert.assertTrue(highlightTerm.verifyHighlightedTermsInDocumentFromViewAll(searchTerm));
	}
	
	@Test
	public void verifyKeyWordIsNotHighLightedInSearchResults()throws Exception {
		Assert.assertTrue(highlightTerm.verifyKeyWordIsNotHighlightedInViewAll(displayGroup));
	}
	
	@Test
	public void verifyKeyWordIsNotHighlightedInViewAll()throws Exception {
		Assert.assertTrue(highlightTerm.verifyKeyWordIsNotHighlightedInViewAll(displayGroup));
	}
}
