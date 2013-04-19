package k12;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.EmailFullTextDocument;

public class EmailFullTextDocumentTest extends BaseWebPageTest {

	public EmailFullTextDocument emailDocument;
	private String displayGroup;
	private String searchTerm;

	@Parameters( { "searchTerm", "displayGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		emailDocument = new EmailFullTextDocument();
		this.displayGroup = displayGroup;
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyDetailsOfDocument() throws InterruptedException {
		Assert.assertTrue(emailDocument
				.verifyDetailsOfDocumentLink(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDetailsOfDocument" })
	public void verifyEmailOption() {
		Assert.assertTrue(emailDocument.verifyEmailIsPresent());
	}

	@Test(dependsOnMethods = { "verifyEmailOption" })
	public void verifyEmailOptionFromDisplayGroups()
			throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(emailDocument
				.verifyEmailPresentFromViewAll(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyEmailOptionFromDisplayGroups" })
	public void verifyModalWindowIsDisplayed() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifySendEmailPopUp());
	}

	@Test(dependsOnMethods = { "verifyModalWindowIsDisplayed" })
	public void verifyNoSelectionInModalWindowMode()
			throws InterruptedException {
		Assert.assertTrue(emailDocument.noSelectionInModalWindowMode());
	}

	@Test(dependsOnMethods = { "verifyModalWindowIsDisplayed" })
	public void verifyModalWindowName() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifyWindowName());
	}

	@Test(dependsOnMethods = { "verifyModalWindowName" })
	public void verifyButtons() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifyButtonsPresent());
	}

	@Test(dependsOnMethods = { "verifyButtons" })
	public void verifyRadioButtonsIsPresent() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifyPresenceOfRadioButtons());
	}

	@Test(dependsOnMethods = { "verifyRadioButtonsIsPresent" })
	public void verifyRadioButtonsAvailability() throws InterruptedException {
		Assert.assertTrue(emailDocument.selectionAvailablity());
	}

	@Test(dependsOnMethods = { "verifyRadioButtonsAvailability" })
	public void verifyFieldsProminance() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifyFieldProminance());
	}

	@Test(dependsOnMethods = { "verifyFieldsProminance" })
	public void verifyAllFieldsArePresent() throws InterruptedException {
		Assert.assertTrue(emailDocument.verifyAllFiledsArePresent());
	}

	@Parameters( { "inValidMailAddress" })
	@Test(dependsOnMethods = { "verifyAllFieldsArePresent" })
	public void sendingMailWithInvalidMailAddress(String inValidMailAddress)
			throws Exception {
		Assert.assertTrue(emailDocument
				.sendingMailWithInvalidMailAddress(inValidMailAddress));
	}

	@Parameters( { "content" })
	@Test(dependsOnMethods = { "sendingMailWithInvalidMailAddress" })
	public void verifyMaxCharacterLength(String content) throws Exception {
		Assert.assertTrue(emailDocument.verifyMaxCharacterLength(content));
	}

	@Parameters( { "mailId" })
	@Test(dependsOnMethods = { "verifyMaxCharacterLength" })
	public void verifyEmailSentMessage(String mailId) throws Exception {
		Assert.assertTrue(emailDocument.sendingMailWithOnlyTO(mailId));
	}

	@Parameters( { "mailIds" })
	@Test(dependsOnMethods = { "verifyEmailSentMessage" })
	public void verifyEmailSentMessageForMoreSenders(String mailIds)
			throws Exception {
		Assert.assertTrue(emailDocument
				.sendingMailWithTwoOrMoreSenders(mailIds));
	}

	@Test(dependsOnMethods = { "verifyEmailSentMessageForMoreSenders" })
	public void verifyEmailWindowClosed() throws InterruptedException {
		Assert.assertTrue(emailDocument.closeSendEmailWindow());
	}
	
	@Test(dependsOnMethods = { "verifyEmailWindowClosed" })
	public void closeSendEmailWindowUsingEsc()throws InterruptedException, AWTException {
		Assert.assertTrue(emailDocument.closeSendEmailWindowUsingEsc());
	}

}