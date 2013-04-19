package webPageContainers4Testing;

import java.awt.AWTException;
import java.awt.event.KeyEvent;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class EmailFullTextDocument extends BasePageContainer {

	public EmailFullTextDocument() throws Exception {
		super();
	}

	public boolean verifyDetailsOfDocumentLink(String displayGroup)
			throws InterruptedException {
		String linkValue = new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")
				+ "[1]/h3/a").getLabelText();
		new Link(properties.get(displayGroup + "_searchResults_xpath")
				+ "[1]/h3/a").click();
		if (linkValue.equals(new TextLabel(properties.get(displayGroup
				+ "_title_in_details")).getLabelText()))
			return true;
		return false;
	}

	public boolean verifyEmailIsPresent() {
		if (new Link(properties.get("email_link")).isPresent())
			return true;
		return false;
	}

	public boolean verifyEmailPresentFromViewAll(String displayGroup)
			throws InterruptedException {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		new Link(properties.get("viewAll_searchResults") + "/td[2]/h3/a")
				.click();
		return verifyEmailIsPresent();

	}

	public boolean verifySendEmailPopUp() throws InterruptedException {
		Thread.sleep(2000);
		new Link(properties.get("email_link")).clickWithoutWait();
		if (new TextLabel(properties.get("email_dialog")).isPresent())
			return true;
		return false;
	}

	public boolean verifyWindowName() {
		if (new TextLabel(properties.get("email_dialog")).getLabelText()
				.equals("Send Email"))
			return true;
		return false;
	}

	public boolean verifyButtonsPresent() {
		if (new TextLabel(properties.get("email_cancel")).isPresent()
				&& new TextLabel(properties.get("email_send")).isPresent())
			return true;
		return false;
	}

	public boolean verifyPresenceOfRadioButtons() {
		if (new RadioButton(properties.get("radiobutton_text")).isPresent()
				&& new RadioButton(properties.get("radiobutton_pdf"))
						.isPresent())
			return true;
		return false;
	}

	public boolean selectionAvailablity() {
		new RadioButton(properties.get("radiobutton_pdf")).click();
		new RadioButton(properties.get("radiobutton_text")).click();
		if (new RadioButton(properties.get("radiobutton_pdf")).isChecked())
			return false;
		return true;
	}

	public boolean verifyFieldProminance() {
		if (new TextLabel(properties.get("email_to")).getLabelText().contains(
				"*"))
			return true;
		return false;
	}

	public boolean closeSendEmailWindow() throws InterruptedException {
		new Link(properties.get("email_link")).clickWithoutWait();
		new Link(properties.get("email_cancel")).clickWithoutWait();
		if (new Link(properties.get("email_link")).isPresent())
			return true;
		return false;
	}

	public boolean closeSendEmailWindowUsingEsc() throws AWTException,
			InterruptedException {
		new Link(properties.get("email_link")).clickWithoutWait();
		RobotWebBrowser.delay(1000);
		RobotWebBrowser.typeKey(KeyEvent.VK_ESCAPE, 1000);
		if (new Link(properties.get("email_link")).isPresent())
			return true;
		return false;

	}

	public boolean verifyAllFiledsArePresent() {
		if (new TextLabel(properties.get("email_sender")).isPresent()
				&& new TextLabel(properties.get("email_subject")).isPresent()
				&& new TextLabel(properties.get("email_to")).isPresent()
				&& new TextLabel(properties.get("email_message")).isPresent())
			return true;
		return false;
	}

	public boolean popupIsDisplayedWithOutTo() throws InterruptedException {
		new Link(properties.get("email_send")).clickWithoutWait();
		if (selenium.isPromptPresent())
			return true;
		return false;
	}

	public boolean sendingMailWithOnlyTO(String mailAdress) throws Exception {
		new TextBox(properties.get("email_to_texbox")).type(mailAdress);
		new Link(properties.get("email_send")).clickWithoutWait();
		if (new TextLabel(properties.get("email_sent_message")).isPresent()) {
			Thread.sleep(2000);
			new Link(properties.get("email_ok")).clickWithoutWait();
			return true;
		}
		return false;
	}

	public boolean sendingMailWithTwoOrMoreSenders(String mailAdress)
			throws Exception {
		new Link(properties.get("email_link")).clickWithoutWait();
		new TextBox(properties.get("email_to_texbox")).type(mailAdress);
		new Link(properties.get("email_send")).clickWithoutWait();
		if (new TextLabel(properties.get("email_sent_message")).isPresent()) {
			Thread.sleep(2000);
			new Link(properties.get("email_ok")).clickWithoutWait();
			return true;
		}
		return false;
	}

	public boolean noMailDelivered(String mailAdress) throws Exception {
		new TextBox(properties.get("email_to_texbox")).type(mailAdress);
		new Link(properties.get("email_cancel")).clickWithoutWait();
		if (new TextLabel(properties.get("email_link")).isPresent())
			return true;
		return false;
	}

	public boolean noSelectionInModalWindowMode() {
		if (!new Link(properties.get("email_link")).isPresent())
			return true;
		else
			return false;
	}

	public boolean sendingMailWithInvalidMailAddress(String invalidMailAddress)
			throws Exception {
		new TextBox(properties.get("email_to_texbox")).type(invalidMailAddress);
		new Link(properties.get("email_send")).clickWithoutWait();
		if (new TextLabel(properties.get("email_error_message")).isPresent())
			return true;
		return false;
	}

	public boolean verifyMaxCharacterLength(String content) throws Exception {
		new TextBox(properties.get("email_message_texarea")).type(content);
		if (new TextLabel(properties.get("email_message_texarea"))
				.getLabelText().length() >= 30)
			return false;
		return true;
	}
}
