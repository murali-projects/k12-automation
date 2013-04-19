package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class TakeQuiz extends BasePageContainer {

	private static String documentTile;

	public TakeQuiz() throws Exception {
		super();
	}

	public boolean verifyTakeQuizLinkIsPresent() throws InterruptedException {
		documentTile = new TextLabel(properties
				.get("viewpoints_searchResults_xpath")
				+ "/h3/a").getLabelText();
		new Link(properties.get("viewpoints_searchResults_xpath") + "/h3/a")
				.click();
		return new Link(properties.get("take_quiz")).isPresent();
	}

	public boolean verifyNewWindowIsDisplayed() throws InterruptedException {
		new Link(properties.get("take_quiz")).ajaxClickAndWait();
		selenium.waitForPopUp("takequiz", "30000");
		selenium.selectWindow("takequiz");
		return new TextLabel(properties.get("quiz_window")).isPresent();
	}

	public boolean verifyOptionsPresentForSelectedDocument() {
		return new TextLabel(properties.get("start")).isPresent()
				&& new TextLabel(properties.get("email")).isPresent()
				&& new TextLabel(properties.get("print")).isPresent();
	}

	public boolean verifyDocumentTilteIsPresent() {
		return new TextLabel(properties.get("quiz_documentTitle")).isPresent();
	}

	public boolean verifyPrintOptions() throws InterruptedException {
		boolean isPresent = false;
		new Link(properties.get("print")).click();
		isPresent = new TextLabel(properties.get("print_button")).isPresent()
				&& compareQuestionsInPrintPage(getQuestions(),
						getQuestionsFromPrintPage())

				&& new TextLabel(properties.get("print_heading"))
						.getLabelText().contains(documentTile);
		return isPresent;
	}

	private boolean compareQuestionsInPrintPage(List<String> questionsList,
			List<String> questionsListInPrint) {
		boolean isValidate = false;
		for (int i = 0; i < questionsList.size(); i++) {
			for (int y = 0; y < questionsListInPrint.size(); y++) {
				isValidate = questionsListInPrint.get(i).equalsIgnoreCase(
						questionsList.get(y));
				if (isValidate == true)
					break;
			}
		}
		return isValidate;
	}

	private List<String> getQuestions() throws InterruptedException {
		List<String> questionsList = new ArrayList<String>();
		int totalQuestions = Integer.parseInt(new TextLabel(
				"quiz_question_result").getLabelText());
		for (int i = 1; i < totalQuestions; i++) {
			questionsList.add("quiz_question");
			new Link("quiz_next").click();
		}
		return questionsList;
	}

	private List<String> getQuestionsFromPrintPage()
			throws InterruptedException {
		List<String> questionsListInPrint = new ArrayList<String>();
		int totalQuestions = Integer.parseInt(new TextLabel(
				"quiz_question_result").getLabelText());
		for (int i = 1; i < totalQuestions; i++) {
			questionsListInPrint.add("quiz_question");
		}
		return questionsListInPrint;
	}

	public boolean verifyEmailOptions() throws InterruptedException {
		new Link(properties.get("email")).click();
		return new TextLabel(properties.get("email_quiz_lightbox")).isPresent();
	}

	public boolean verifyRequiredFieldsForEmailQuiz() {
		return new TextLabel(properties.get("sender_field")).isPresent()
				&& new TextLabel(properties.get("receiver_field")).isPresent();
	}

	public boolean verifyMessageDisplayed() throws Exception {
		new TextBox(properties.get("receiver_field")).type("abc@bbc.com");
		new Link(properties.get("quiz_email_send")).click();
		return new TextLabel(properties.get("success_message")).isPresent();
	}

	public boolean popupIsDisplayed() throws InterruptedException {
		new Link(properties.get("email_send")).clickWithoutWait();
		if (selenium.isPromptPresent()) {
			selenium.chooseOkOnNextConfirmation();
			return true;
		}
		return false;
	}

	public boolean sendingMailWithTwoOrMoreSenders(String mailAdress)
			throws Exception {
		new Link(properties.get("email")).click();
		new TextBox(properties.get("email_to_texbox")).type(mailAdress);
		new Link(properties.get("quiz_email_send")).clickWithoutWait();
		if (new TextLabel(properties.get("success_message")).isPresent()) {
			Thread.sleep(2000);
			new Link(properties.get("quiz_email_ok")).clickWithoutWait();
			return true;
		}
		return false;
	}

	public boolean verifyNewWindowOpenedForStartButton()
			throws InterruptedException {
		new Link(properties.get("start")).click();
		return new TextLabel(properties.get("quiz_enter_answer")).isPresent();
	}

	public boolean verifyStatusBar() {
		return new TextLabel(properties.get("status_bar")).isPresent();
	}

	public boolean verifySubmissionOfAnswers(String answwers)
			throws InterruptedException {
		new RadioButton(answwers).check();
		new Link(properties.get("send_mail")).click();
		return new TextLabel(properties.get("success_message")).isPresent();
	}

	public boolean verifyTheDocumentTitleInAllOptions()
			throws InterruptedException {
		return verifyDocumentTitleInEmailPage()
				&& verifyDocumentTitleInStartPage()
				&& verifyDocumentTitleInPrintPage();
	}

	private boolean verifyDocumentTitleInEmailPage()
			throws InterruptedException {
		boolean isValidate = false;
		new Link(properties.get("email")).click();
		isValidate = new TextLabel(properties.get("quiz_documentTilte"))
				.getLabelText().equals(documentTile);
		new Link(properties.get("cancel")).click();
		return isValidate;
	}

	private boolean verifyDocumentTitleInStartPage()
			throws InterruptedException {
		boolean isValidate = false;
		new Link(properties.get("start")).click();
		isValidate = new TextLabel(properties.get("quiz_documentTilte"))
				.getLabelText().equals(documentTile);
		new Link(properties.get("cancel")).click();
		return isValidate;
	}

	private boolean verifyDocumentTitleInPrintPage()
			throws InterruptedException {
		boolean isValidate = false;
		new Link(properties.get("print")).click();
		isValidate = new TextLabel(properties.get("quiz_documentTilte"))
				.getLabelText().equals(documentTile);
		new Link(properties.get("cancel")).click();
		return isValidate;
	}

	public boolean verifyCosingPopUp() throws InterruptedException {
		new Link(properties.get(properties.get("close"))).click();
		return !new TextLabel(properties.get("quiz_text")).isPresent();
	}
}
