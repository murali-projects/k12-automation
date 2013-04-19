package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HandleOceanExceptions;

public class HandleOceanExceptionsTest extends BaseWebPageTest{
	private HandleOceanExceptions handleExceptions;

	@BeforeTest
	public void setUp() throws Exception {
		handleExceptions = new HandleOceanExceptions();
	}

	@Parameters( { "invalidSearchTerm1"})
	@Test
	public void verifyErrorPageNotDisplayed(String invalidSearchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(invalidSearchTerm);
		Assert.assertTrue(handleExceptions.verifyErrorPageIsNotDisplayed());
				
	}
	
	@Parameters( { "invalidSearchTerm1"})
	@Test(dependsOnMethods = { "verifyErrorPageNotDisplayed" })
	public void verifyMessagesDisplayedWithInvalidSearchTerm(String invalidSearchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(invalidSearchTerm);
		Assert.assertTrue(handleExceptions.verifyCautionMessagesAreDisplayed());
				
	}

	@Parameters( { "incorrectSpeltSearchTerm"})
	@Test(dependsOnMethods = { "verifyMessagesDisplayedWithInvalidSearchTerm" })
	public void verifyMessagesDisplayedWithIncorrectSpeltSearchTerm(String incorrectSpeltSearchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(incorrectSpeltSearchTerm);
		Assert.assertTrue(handleExceptions.verifyCautionMessagesAreDisplayed());
	}
	
	@Parameters( { "searchTerm"})
	@Test(dependsOnMethods = { "verifyMessagesDisplayedWithIncorrectSpeltSearchTerm" })
	public void verifyResultsAreDisplayed(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(handleExceptions.verifyResultsAreDisplayed());
	}
	
	@Parameters( { "invalidSearchTerm1"})
	@Test(dependsOnMethods = { "verifyResultsAreDisplayed" })
	public void verifyOnlyErrorMessagesAreDisplayed(String invalidSearchTerm1) throws Exception {
		doBasicSearchUsingSearchTerm(invalidSearchTerm1);
		Assert.assertTrue(handleExceptions.verifyOnlyCautionMessagesDisplayed());
	}
	
	@Parameters( { "searchTerm"})
	@Test(dependsOnMethods = { "verifyOnlyErrorMessagesAreDisplayed" })
	public void checkWithValidSearchTerm(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(handleExceptions.verifyResultsAreDisplayed());
	}
	
	@Parameters( { "invalidSearchTerm2"})
	@Test(dependsOnMethods = { "checkWithValidSearchTerm" })
	public void checkWithInvalidSearchTerm(String invalidSearchTerm2) throws Exception {
		doBasicSearchUsingSearchTerm(invalidSearchTerm2);
		Assert.assertTrue(handleExceptions.verifyOnlyCautionMessagesDisplayed());
	}
}
