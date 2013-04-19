package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.StopAndNaughtyWords;

public class NaughtyWordsTest extends BaseWebPageTest{

	private StopAndNaughtyWords stopAndNaughtyWordsTest;
	
	@BeforeTest
	public void setUp() throws Exception{
		stopAndNaughtyWordsTest = new StopAndNaughtyWords();
	}
	
	@Test
	public void doCheckWithSingleNaughtyWord()throws Exception{
		doBasicSearchUsingSearchTerm("anal");
		StopAndNaughtyWords.search("anal");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWord());
	}
	
	@Test(dependsOnMethods = "doCheckWithSingleNaughtyWord")
	public void doCheckWithTwoNaughtyWordsInSentenceWithoutQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("anal erotic is naughtyWord");
		StopAndNaughtyWords.search("anal erotic is naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWordsInSentence());
	}
	
	@Test(dependsOnMethods = "doCheckWithTwoNaughtyWordsInSentenceWithoutQuotes")
	public void doCheckWithMultipleNaughtyWordsInSentenceWithoutQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("anal erotic anal is naughtyWord");
		StopAndNaughtyWords.search("anal erotic anal is naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWordsInSentence());
	}
	
	@Test(dependsOnMethods = "doCheckWithMultipleNaughtyWordsInSentenceWithoutQuotes")
	public void doCheckWithMultipleStopAndNaughtyWordsInSentenceWithoutQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("corp is anal erotic naughtyWord");
		StopAndNaughtyWords.search("corp is anal erotic naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithStopAndNaughtyWordsInSentenceWithoutQuotes());
	}
	
	@Test(dependsOnMethods = "doCheckWithMultipleStopAndNaughtyWordsInSentenceWithoutQuotes")
	public void doCheckWithSingleNaughtyWordInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("\"anal\" is naughtyWord");
		StopAndNaughtyWords.search("\"anal\" is naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWordsInSentence());
	}
	
	@Test(dependsOnMethods = "doCheckWithSingleNaughtyWordInSentenceWithQuotes")
	public void doCheckWithTwoNaughtyWordsInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("\"erotic\" is \"anal\" naughtyWord");
		StopAndNaughtyWords.search("\"erotic\" is \"anal\" naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWordsInSentence());
	}
	
	@Test(dependsOnMethods = "doCheckWithTwoNaughtyWordsInSentenceWithQuotes")
	public void doCheckWithMultipleNaughtyWordsInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("\"erotic\" is \"anal\" \"anal\" naughtyWord");
		StopAndNaughtyWords.search("\"erotic\" is \"anal\" \"anal\" naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithNaughtyWordsInSentence());
	}
	
	@Test(dependsOnMethods = "doCheckWithMultipleNaughtyWordsInSentenceWithQuotes")
	public void doCheckWithMultipleStopAndNaughtyWordsInSentenceWithQuotes()throws Exception{
		doBasicSearchUsingSearchTerm("\"corp\" is  \"anal\" \"erotic\" naughtyWord");
		StopAndNaughtyWords.search("\"corp\" is  \"anal\" \"erotic\" naughtyWord");
		Assert.assertTrue(stopAndNaughtyWordsTest.searchWithStopAndNaughtyWordsInSentenceWithQuotes());
	}
}
