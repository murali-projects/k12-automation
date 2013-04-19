package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.TakeQuiz;

public class TakeQuizTest extends BaseWebPageTest {

	private TakeQuiz takeQuiz;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		takeQuiz = new TakeQuiz();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyTakeQuizLinkIsPresent() throws Exception {
		Assert.assertTrue(takeQuiz.verifyTakeQuizLinkIsPresent());
	}

	@Test(dependsOnMethods = { "verifyTakeQuizLinkIsPresent" })
	public void verifyNewWindowIsDisplayed() throws Exception {
		Assert.assertTrue(takeQuiz.verifyNewWindowIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyNewWindowIsDisplayed" })
	public void verifyOptionsPresentForSelectedDocument() throws Exception {
		Assert.assertTrue(takeQuiz.verifyOptionsPresentForSelectedDocument());
	}
	
	@Test(dependsOnMethods = { "verifyOptionsPresentForSelectedDocument" })
	public void verifyDocumentTilteIsPresent() throws Exception {
		Assert.assertTrue(takeQuiz.verifyDocumentTilteIsPresent());
	}

	@Test(dependsOnMethods = { "verifyDocumentTilteIsPresent" })
	public void verifyPrintOptions() throws Exception {
		Assert.assertTrue(takeQuiz.verifyPrintOptions());
	}

	@Test(dependsOnMethods = { "verifyPrintOptions" })
	public void verifyEmailOptions() throws Exception {
		Assert.assertTrue(takeQuiz.verifyEmailOptions());
	}
	
	@Test(dependsOnMethods = { "verifyEmailOptions" })
	public void verifyRequiredFieldsForEmailQuiz() throws Exception {
		Assert.assertTrue(takeQuiz.verifyTakeQuizLinkIsPresent());
	}

	@Test(dependsOnMethods = { "verifyRequiredFieldsForEmailQuiz" })
	public void verifyPopupIsDisplayed() throws Exception {
		Assert.assertTrue(takeQuiz.popupIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyPopupIsDisplayed" })
	public void verifyMessageDisplayed() throws Exception {
		Assert.assertTrue(takeQuiz.verifyNewWindowIsDisplayed());
	}

	@Test(dependsOnMethods = { "verifyPopupIsDisplayed" })
	public void sendingMailWithTwoOrMoreSenders(String mailId) throws Exception {
		Assert.assertTrue(takeQuiz.sendingMailWithTwoOrMoreSenders(mailId));
	}

	@Test(dependsOnMethods = { "sendingMailWithTwoOrMoreSenders" })
	public void verifyNewWindowOpenedForStartButton() throws Exception {
		Assert.assertTrue(takeQuiz.verifyNewWindowOpenedForStartButton());
	}

	@Test(dependsOnMethods = { "verifyNewWindowOpenedForStartButton" })
	public void verifyStatusBar() throws Exception {
		Assert.assertTrue(takeQuiz.verifyStatusBar());
	}
	
	@Test(dependsOnMethods = { "verifyStatusBar" })
	public void verifySubmissionOfAnswers(String answer) throws Exception {
		Assert.assertTrue(takeQuiz.verifySubmissionOfAnswers(answer));
	}

	@Test(dependsOnMethods = { "verifySubmissionOfAnswers" })
	public void verifyTheDocumentTitleInAllOptions() throws Exception {
		Assert.assertTrue(takeQuiz.verifyTheDocumentTitleInAllOptions());
	}
	
	@Test(dependsOnMethods = { "verifyTheDocumentTitleInAllOptions" })
	public void verifyCosingPopUp() throws Exception {
		Assert.assertTrue(takeQuiz.verifyCosingPopUp());
	}

}
