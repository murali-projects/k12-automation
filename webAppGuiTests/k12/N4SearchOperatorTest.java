package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.N4SearchOperator;

public class N4SearchOperatorTest extends BaseWebPageTest{
	private N4SearchOperator n4SearchOperator;
	private String searchTerm;
	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		n4SearchOperator = new N4SearchOperator();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Parameters( { "reference"})
	@Test
	public void searchWithN4OperatorInReference(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"news"})
	@Test
	public void searchWithN4OperatorInNews(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"statistics"})
	@Test
	public void searchWithN4OperatorInStatistics(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"maagzines"})
	@Test
	public void searchWithN4OperatorInMagazines(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"primarysources"})
	@Test
	public void searchWithN4OperatorInPrimarysources(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"academicJournals"})
	@Test
	public void searchWithN4OperatorInAcademicJournals(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
	@Parameters( {"websites"})
	@Test
	public void searchWithN4OperatorInWebsites(String displayGroup) throws Exception{
		Assert.assertTrue(n4SearchOperator.verifyResultsWithN4SearchOperator(searchTerm, displayGroup));
	}
	
}

