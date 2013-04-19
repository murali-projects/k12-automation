package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.EmailDocumentAsPdf;

public class EmailDocumentAsPdfTest extends BaseWebPageTest {

	public EmailDocumentAsPdf pdfDocument;
	private String displayGroup;
	private String searchTerm;

	@Parameters( { "searchTerm", "displayGroup" })
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		pdfDocument = new EmailDocumentAsPdf();
		this.displayGroup = displayGroup;
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyDetailsOfDocument() throws InterruptedException {
		Assert.assertTrue(pdfDocument
				.verifyDetailsOfDocumentLink(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyDetailsOfDocument" })
	public void verifyEmailOption() {
		Assert.assertTrue(pdfDocument.verifyEmailIsPresent());
	}

	@Test(dependsOnMethods = { "verifyEmailOption" })
	public void verifyEmailOptionFromDisplayGroups()
			throws InterruptedException {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(pdfDocument
				.verifyEmailPresentFromViewAll(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyEmailOptionFromDisplayGroups" })
	public void verifyModalWindowIsDisplayed() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifySendEmailPopUp());
	}

	@Test(dependsOnMethods = { "verifyModalWindowIsDisplayed" })
	public void verifyNoSelectionInModalWindowMode()
			throws InterruptedException {
		Assert.assertTrue(pdfDocument.noSelectionInModalWindowMode());
	}

	@Test(dependsOnMethods = { "verifyModalWindowIsDisplayed" })
	public void verifyModalWindowName() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifyWindowName());
	}

	@Test(dependsOnMethods = { "verifyModalWindowName" })
	public void verifyButtons() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifyButtonsPresent());
	}

	@Test(dependsOnMethods = { "verifyButtons" })
	public void verifyRadioButtonsIsPresent() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifyPresenceOfRadioButtons());
	}

	@Test(dependsOnMethods = { "verifyRadioButtonsIsPresent" })
	public void verifyRadioButtonsAvailability() throws InterruptedException {
		Assert.assertTrue(pdfDocument.selectionAvailablity());
	}

	@Test(dependsOnMethods = { "verifyRadioButtonsAvailability" })
	public void verifyFieldsProminance() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifyFieldProminance());
	}

	@Test(dependsOnMethods = { "verifyFieldsProminance" })
	public void verifyAllFieldsArePresent() throws InterruptedException {
		Assert.assertTrue(pdfDocument.verifyAllFiledsArePresent());
	}

	@Test(dependsOnMethods = { "verifyAllFieldsArePresent" })
	public void verifyEmailNotDelivered() throws Exception {
		Assert.assertTrue(pdfDocument
				.noMailDelivered("sreehari.ghali@applabs.com"));
	}

	@Test(dependsOnMethods = { "verifyEmailNotDelivered" })
	public void sendingMailWithInvalidMailAddress() throws Exception {
		Assert.assertTrue(pdfDocument
				.sendingMailWithInvalidMailAddress("123"));
	}

	@Test(dependsOnMethods = { "sendingMailWithInvalidMailAddress" })
	public void verifyMaxCharacterLength() throws Exception {
		Assert.assertTrue(pdfDocument.verifyMaxCharacterLength("abc"));
	}

	@Test(dependsOnMethods = { "verifyMaxCharacterLength" })
	public void verifyEmailSentMessage() throws Exception {
		Assert.assertTrue(pdfDocument
				.sendingMailWithOnlyTO("harika.k@applabs.com"));
	}

	@Test(dependsOnMethods = { "verifyEmailSentMessage" })
	public void verifyEmailSentMessageForMoreSenders() throws Exception {
		Assert
				.assertTrue(pdfDocument
						.sendingMailWithTwoOrMoreSenders("sreehari.ghali@applabs.com,harika.k@applabs.com"));
	}

	@Test(dependsOnMethods = { "verifyEmailSentMessageForMoreSenders" })
	public void verifyEmailWindowClosed() throws InterruptedException {
		Assert.assertTrue(pdfDocument.closeSendEmailWindow());
	}

}