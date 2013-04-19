package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class Email extends BasePageContainer{

	private String documentTitle;
	public Email() throws Exception {
		super();
	}
	
	public boolean verifyEmailOption() throws InterruptedException{
		documentTitle = new Link(properties.get("reference_searchResults_xpath")+ "/h3/a").getLinkText();
		new Link(properties.get("reference_searchResults_xpath")+ "/h3/a").click();
		Thread.sleep(1000);
		new Link(properties.get("email_link")).clickWithoutWait();
		if (new TextLabel(properties.get("email_dialog")).isPresent())
			return true;
		return false;
	}
	
	public boolean verifyDocumentTitleInSubjectField() throws Exception{
		return new TextBox(properties.get("email_subject_textbox")).getValue().equals(documentTitle);
	}

	public boolean verifyEmailOptionForOtherDisplayGroup() throws InterruptedException{
		new Link(properties.get("email_cancel")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		documentTitle = new Link(properties.get("news_searchResults_xpath")+ "/h3/a").getLinkText(); 
		new Link(properties.get("news_searchResults_xpath")+ "/h3/a").click();
		Thread.sleep(1000);
		new Link(properties.get("email_link")).clickWithoutWait();
		if (new TextLabel(properties.get("email_dialog")).isPresent())
			return true;
		return false;
	}

	public boolean verifyDocumentTitleForOtherDisplayGroup() throws Exception{
		return new TextBox(properties.get("email_subject_textbox")).getValue().contains(documentTitle.substring(0, documentTitle.length()-1));
	}
	
	public boolean verifySubjectFieldIsEditable() throws Exception{
		return new TextBox(properties.get("email_subject_textbox")).isEditable();
	}
	
	public boolean verifySenderFieldIsEditable() throws Exception{
		return new TextBox(properties.get("email_sender_textbox")).isEditable();
	}
	
	public boolean verifyEmailIdInSenderField(String mailId) throws Exception{
		new TextBox(properties.get("email_subject_textbox")).type(mailId);
		return new TextBox(properties.get("email_subject_textbox")).getValue().equals(mailId);
	}
	
	public boolean verifyEmailIdInSenderFieldForOtherDisplayGroup(String mailId) throws Exception{
		return ! new TextBox(properties.get("email_subject_textbox")).getValue().equals(mailId);
	}
	
	public boolean verifyDefaultEmailIdInSenderField(String defaultMailId) throws Exception{
		return new TextBox(properties.get("email_subject_textbox")).getValue().equals(defaultMailId);
	}
	
	public boolean verifyPresenceOfRadioButtons() {
		return new RadioButton(properties.get("radiobutton_pdf"))
						.isPresent();
	}
	
	public boolean verifyDefaultOptionForRadioButton() throws InterruptedException{
		new Link(properties.get("email_cancel")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		new Link(properties.get("news_searchResults_xpath")+ "/h3/a").click();
		Thread.sleep(1000);
		new Link(properties.get("email_link")).clickWithoutWait();
		if (new RadioButton(properties.get("radiobutton_text")).isChecked())
			return true;
		return false;
	}
	
}
