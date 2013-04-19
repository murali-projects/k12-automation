package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.StopAndNaughtyWords;

public class StopWordsTest extends BaseWebPageTest{
	private StopAndNaughtyWords stopAndNaughtyWords;
	
	@BeforeTest
	public void setUp() throws Exception{
		stopAndNaughtyWords = new StopAndNaughtyWords();
	}
	
	@Test
	public void doCheckWithSingleStopWord()throws Exception{
		doBasicSearchUsingSearchTerm("aspects");
		StopAndNaughtyWords.search("aspects");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWord());
	}
	@Test(dependsOnMethods = "doCheckWithSingleStopWord")
	public void doCheckWithOneStopWordInSentenceWithoutQuotes() throws Exception {
		doBasicSearchUsingSearchTerm("corp dog cat");
		StopAndNaughtyWords.search("corp dog cat");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithoutQuotes());
	}
	@Test(dependsOnMethods = "doCheckWithOneStopWordInSentenceWithoutQuotes")
	public void doCheckWithTwoStopWordsInSentenceWithoutQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("aspects corp dog cat");
		StopAndNaughtyWords.search("aspects corp dog cat");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithoutQuotes());
	}
	@Test(dependsOnMethods = "doCheckWithTwoStopWordsInSentenceWithoutQuotes")
	public void doCheckWithMultipleStopWordsInSentenceWithoutQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("aspects corp dog cat inc");
		StopAndNaughtyWords.search("aspects corp dog cat inc");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithoutQuotes());
	}
	@Test(dependsOnMethods = "doCheckWithMultipleStopWordsInSentenceWithoutQuotes")
	public void doCheckWithStopWordInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("he \"aspects\" dog");
		StopAndNaughtyWords.search("he \"aspects\" dog");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithQuotes());
	}
	
	@Test(dependsOnMethods = "doCheckWithStopWordInSentenceWithQuotes")
	public void doCheckWithTwoStopWordsInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("he \"aspects\" hero \"inc\"");
		StopAndNaughtyWords.search("he \"aspects\" hero \"inc\"");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithQuotes());
	}
	@Test(dependsOnMethods = "doCheckWithTwoStopWordsInSentenceWithQuotes")
	public void doCheckWithMultipleStopWordsInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("he \"aspects\" hero \"inc\" \"corp\"");
		StopAndNaughtyWords.search("he \"aspects\" hero \"inc\" \"corp\"");
		Assert.assertTrue(stopAndNaughtyWords.searchWithStopWordsInSentenceWithQuotes());
	}
	
}