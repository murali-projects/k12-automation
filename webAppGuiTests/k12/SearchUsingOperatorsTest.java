package k12;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.SearchUsingOperators;

public class SearchUsingOperatorsTest extends BaseWebPageTest{
	private SearchUsingOperators searchOperators;

	@BeforeTest
	public void setUp() throws Exception{
		searchOperators = new SearchUsingOperators();
	}
	
    @Parameters( {"singleSearchTerm"})
	@Test
	public void validateSingleWildCharacterSearchOperator(String singleSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(singleSearchTerm);
		Assert.assertTrue(searchOperators.validateSingleWildCharacterSearch(singleSearchTerm));
	}
	
	@Parameters( {"multipleSearchTerm"})	
	@Test(dependsOnMethods = "validateSingleWildCharacterSearchOperator")
	public void validateMultipleWildCharacterSearchOperator(String multipleSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(multipleSearchTerm);
		Assert.assertTrue(searchOperators.validateMultipleWildCharacterSearch(multipleSearchTerm));
	}
	
	@Parameters( {"searchTermWithExclamationMark"})	
	@Test(dependsOnMethods = "validateMultipleWildCharacterSearchOperator")
	public void validateExclamationWildCharacterSearchOperator(String searchTermWithExclamationMark)throws Exception{
		doBasicSearchUsingSearchTerm(searchTermWithExclamationMark);
		Assert.assertTrue(searchOperators.validateExclamationWildCharacterSearch(searchTermWithExclamationMark));
	}
	
	@Parameters( {"booleanOrOperator", "booleanOrSearchTerm"})	
	@Test(dependsOnMethods = "validateExclamationWildCharacterSearchOperator")
	public void validateBooleanOrSearchOperator(String booleanOrOperator, String booleanOrSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm( booleanOrSearchTerm);
		Assert.assertTrue(searchOperators.validateBooleanPattern(booleanOrSearchTerm,booleanOrOperator));
	}
	
	@Parameters( {"booleanNotOperator", "booleanNotSearchTerm"})	
	@Test(dependsOnMethods = "validateBooleanOrSearchOperator")
	public void validateBooleanNotSearchOperator(String booleanNotOperator, String booleanNotSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(booleanNotSearchTerm);
		Assert.assertTrue(searchOperators.validateBooleanPattern(booleanNotSearchTerm, booleanNotOperator));
	}
	
	@Parameters( {"booleanAndOperator", "booleanAndSearchTerm"})	
	@Test(dependsOnMethods = "validateBooleanNotSearchOperator")
	public void validateBooleanAndSearchOperator(String booleanAndOperator, String booleanAndSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm( booleanAndSearchTerm);
		Assert.assertTrue(searchOperators.validateBooleanPattern(booleanAndSearchTerm, booleanAndOperator));
	}
	
	@Parameters( {"nestedSearchTerm"})
	@Test(dependsOnMethods = "validateBooleanAndSearchOperator")
	public void validateNestedSearchOperator(String nestedSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(nestedSearchTerm);
		Assert.assertTrue(searchOperators.validateNestedOperator(nestedSearchTerm));
	}
	
	@Parameters( {"withinProximityOperator", "withinProximitySearchTerm"})	
	@Test(dependsOnMethods = "validateNestedSearchOperator")
	public void validateWithinProximitySearchOperator(String withinProximityOperator, String withinProximitySearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(withinProximitySearchTerm);
		Assert.assertTrue(searchOperators.validateProximitySearchOperator(withinProximitySearchTerm,withinProximityOperator));
	}
	
	@Parameters( {"nearProximityOperator", "nearProximitySearchTerm"})	
	@Test(dependsOnMethods = "validateWithinProximitySearchOperator")
	public void validateNearProximitySearchOperator(String nearProximityOperator, String nearProximitySearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(nearProximitySearchTerm);
		Assert.assertTrue(searchOperators.validateProximitySearchOperator(nearProximitySearchTerm, nearProximityOperator));
	}
	
	@Parameters( {"dateSearchTerm"})
	@Test(dependsOnMethods = "validateNearProximitySearchOperator")
	public void validateDateRangeOperator(String dateSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(dateSearchTerm);
		Assert.assertTrue(searchOperators.validateDateRangeOperator(dateSearchTerm));
	}
	
	@Parameters( {"greaterThanSearchTerm"})
	@Test(dependsOnMethods = "validateDateRangeOperator")
	public void validateGreaterThanOperator(String greaterThanSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(greaterThanSearchTerm);
		Assert.assertTrue(searchOperators.verifyLexileScoreInResults(greaterThanSearchTerm, ">"));
	}
	
	@Parameters( {"greaterThanOrEqualToSearchTerm"})
	@Test(dependsOnMethods = "validateNearProximitySearchOperator")
	public void validateGreaterThanEqualToOperator(String greaterThanOrEqualToSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(greaterThanOrEqualToSearchTerm);
		Assert.assertTrue(searchOperators.verifyLexileScoreInResults(greaterThanOrEqualToSearchTerm, ">="));
	}
	
	@Parameters( {"lessThanSearchTerm"})
	@Test(dependsOnMethods = "validateGreaterThanEqualToOperator")
	public void validateLessThanOperator(String lessThanSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(lessThanSearchTerm);
		Assert.assertTrue(searchOperators.verifyLexileScoreInResults(lessThanSearchTerm, "<"));
	}
	
	@Parameters( {"lessThanOrEqualToSearchTerm"})
	@Test(dependsOnMethods = "validateLessThanOperator")
	public void validateLessThanEqualToOperator(String lessThanOrEqualToSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(lessThanOrEqualToSearchTerm);
		Assert.assertTrue(searchOperators.verifyLexileScoreInResults(lessThanOrEqualToSearchTerm, "<="));
	}
	
	@Parameters( {"phraseContainsSearchTerm"})
	@Test(dependsOnMethods = "validateLessThanEqualToOperator")
	public void validatePhraseContains(String phraseContainsSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(phraseContainsSearchTerm);
		Assert.assertTrue(searchOperators.verifyPhraseContainsResults(phraseContainsSearchTerm));
	}
	
	@Parameters( {"phraseExactSearchTerm"})
	@Test(dependsOnMethods = "validatePhraseContains")
	public void validatePhraseExact(String phraseExactSearchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(phraseExactSearchTerm);
		Assert.assertTrue(searchOperators.verifyPhraseExactResults(phraseExactSearchTerm));
	}
	
}
