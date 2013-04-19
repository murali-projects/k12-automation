package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.N4ToAndBasicSearchFailover;

public class N4ToAndBasicSearchFailoverTest extends BaseWebPageTest{
	private N4ToAndBasicSearchFailover n4ToAndBasicSearchFailover;
	
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		n4ToAndBasicSearchFailover = new N4ToAndBasicSearchFailover();
	}
	
	@Parameters( { "searchTermHavingOr", "referenceDisplayGroup"})
	@Test
	public void searchUsingOrForReference(String searchTerm, String displayGroup) throws Exception{
		Assert.assertTrue(n4ToAndBasicSearchFailover.verifySearchResults(searchTerm, displayGroup));
	}
	
	@Parameters( { "searchTermHavingWith", "referenceDisplayGroup"})
	@Test
	public void searchUsingWithForReference(String searchTerm, String displayGroup) throws Exception{
		Assert.assertTrue(n4ToAndBasicSearchFailover.verifySearchResults(searchTerm, displayGroup));
	}
	
	@Parameters( { "searchTermHavingNor", "referenceDisplayGroup"})
	@Test
	public void searchUsingNorForReference(String searchTerm, String displayGroup) throws Exception{
		Assert.assertTrue(n4ToAndBasicSearchFailover.verifySearchResults(searchTerm, displayGroup));
	}
	
	@Parameters( { "searchTermHavingBut", "referenceDisplayGroup"})
	@Test
	public void searchUsingButForReference(String searchTerm, String displayGroup) throws Exception{
		Assert.assertTrue(n4ToAndBasicSearchFailover.verifySearchResults(searchTerm, displayGroup));
	}

}
