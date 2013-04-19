package k12;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.StopAndNaughtyWordsFormat;

public class StopAndNaughyWordsFormatTest extends BaseWebPageTest{
	protected StopAndNaughtyWordsFormat stopAndNaughyWordsFormat;
	
	public StopAndNaughyWordsFormatTest() throws Exception {
		super();
		stopAndNaughyWordsFormat = new StopAndNaughtyWordsFormat();
	}
	
	@Parameters( { "searchTermForStopWords"})
	@Test
	public void validateSearchIdentifierForStopWordsinSearchString(String searchTermForStopWords)throws Exception{
		doBasicSearchUsingSearchTerm(searchTermForStopWords);
		Assert.assertTrue(stopAndNaughyWordsFormat.verifySearchResultsKeywordWithStopwords(searchTermForStopWords));
	}
	
	@Parameters( { "searchTermForNaughtyWords"})
	@Test
	public void validateSearchIndentifierForNaughtyWordsinSearchString(String searchTermForNaughtyWords)throws Exception{
		doBasicSearchUsingSearchTerm(searchTermForNaughtyWords);
		Assert.assertTrue(stopAndNaughyWordsFormat.verifySearchResultsKeywordWithNaughtyWords(searchTermForNaughtyWords));
	}
	
	@Test
	public void verifySearchIdentifierForNaughtyWordsJoinedWithSplChar() throws Exception{
		List<String> searchStrings = stopAndNaughyWordsFormat.getSearchTermsWithNaughtyWordsAndSpecialChar();
		for(String searchString : searchStrings){
			doBasicSearchUsingSearchTerm(searchString);
			stopAndNaughyWordsFormat.verifySearchIdentifierWithNaughtyWordsAndSplChar(searchString);
			
		}
	}
}
