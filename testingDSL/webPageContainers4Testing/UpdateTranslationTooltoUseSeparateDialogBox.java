package webPageContainers4Testing;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class UpdateTranslationTooltoUseSeparateDialogBox extends
		BasePageContainer {

	public UpdateTranslationTooltoUseSeparateDialogBox() throws Exception {
		super();
	}

	public boolean verifyTranslateOption(String displayGroup)
			throws InterruptedException {
		boolean isPresent = false;
		for (int i = 1; i < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); i++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + i + "]/h3/a").click();
			isPresent = new Link(properties.get("translate")).isPresent();
			new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifyTranslateDialogBox(String displayGroup)
			throws InterruptedException {
		boolean isPresent = false;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
			new Link(properties.get("translate")).clickWithoutWait();
			Thread.sleep(3000);
			isPresent = new TextLabel(properties.get("translate_dialog_box"))
					.isPresent()
					&& verifyLanguages();
			new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	private boolean verifyLanguages() {
		boolean isValidate = false;
		String[] defaultLanguages = { "Spanish", "French", "Japanese",
				"German", "Italian", "Portuguese", "Chinese (Simplified)",
				"Korean" };
		String[] displayedLanguages = new DropDownBox(properties
				.get("translate_options")).getSelectOptions();
		for (int i = 0; i < defaultLanguages.length; i++) {
			for (int y = 0; y < displayedLanguages.length; y++) {
				isValidate = defaultLanguages[i]
						.equalsIgnoreCase(displayedLanguages[y]);
				if (isValidate == true)
					break;
			}
		}
		new Link(properties.get("translate_cancel")).clickWithoutWait();
		return isValidate;
	}

	public boolean verifyOptionsInTranslateDialogBox(String displayGroup) throws InterruptedException {
		boolean isPresent = false;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
			new Link(properties.get("translate")).clickWithoutWait();
			isPresent= new TextLabel(properties.get("translate_button")).isPresent()
				&& new TextLabel(properties.get("translate_cancel")).isPresent();
			new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifySelectionOfTranslate(String language,String displayGroup)
			throws InterruptedException {
		boolean isPresent = true;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
			new Link(properties.get("translate")).clickWithoutWait();	
			Thread.sleep(3000);
		new DropDownBox(properties.get("translate_options")).select(language);
		new Link(properties.get("translate_button")).clickWithoutWait();
		new Url().waitForPopup("translateDocument");
		new Url().selectWindow("name=translateDocument");
		isPresent = new TextLabel(properties.get("translate_document"))
		.isPresent();
		new Url().closeWindow();
		new Url().selectWindow("");
		new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifyCancelOfTranslate(String language,String displayGroup)
			throws InterruptedException {
		boolean isPresent = true;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
		new Link(properties.get("translate")).clickWithoutWait();
		Thread.sleep(3000);
		new DropDownBox(properties.get("translate_options")).select(language);
		new Link(properties.get("translate_cancel")).clickWithoutWait();
		isPresent=!new TextLabel(properties.get("translate_cancel")).isPresent();
		new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifySelectionOfTranslateAfterCancelling(String language,String displayGroup)
			throws InterruptedException {
		boolean isPresent = true;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
		new Link(properties.get("translate")).clickWithoutWait();
		Thread.sleep(3000);
		new DropDownBox(properties.get("translate_options")).select(language);
		new Link(properties.get("translate_button")).clickWithoutWait();
		new Url().waitForPopup("translateDocument");
		new Url().selectWindow("name=translateDocument");
		isPresent = new TextLabel(properties.get("translate_document"))
		.isPresent();
		new Url().closeWindow();
		new Url().selectWindow("");
		new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifyDialogBoxIsClosed(String displayGroup) throws InterruptedException {
		boolean isPresent = true;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
			new Link(properties.get(displayGroup + "_searchResults_xpath")
					+ "[" + count + "]/h3/a").click();
		new Link(properties.get("translate")).clickWithoutWait();
		Thread.sleep(3000);
		new Link(properties.get("translate_close")).clickWithoutWait();
		isPresent=!new TextLabel(properties.get("translate_close")).isPresent();
		new Url().goBackToPreviousPage();
		}
		return isPresent;
	}

	public boolean verifyModalWindow() throws InterruptedException {
		boolean isPresent = true;
		new Link(properties.get("translate")).click();
		new Url().mouseOver(properties.get("share_option"));
		isPresent = new TextLabel(properties.get("")).isPresent();
		new Link(properties.get("translate_close")).clickWithoutWait();
		return isPresent;
	}

	public boolean verifyTranslateFromViewAll(String displayGroup)
			throws InterruptedException {
		boolean isPresent = true;
		for (int count = 1; count < new TextLabel(properties.get(displayGroup
				+ "_searchResults_xpath")).getXpathCount(); count++) {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		new Link(properties.get("viewAll_document_link")).click();
		new Link(properties.get("translate")).clickWithoutWait();
		Thread.sleep(3000);
		new Link(properties.get("translate_button")).clickWithoutWait();
		new Url().waitForPopup("translateDocument");
		new Url().selectWindow("name=translateDocument");
		isPresent = new TextLabel(properties.get("translate_document"))
				.isPresent();
		new Url().closeWindow();
		new Url().selectWindow("");
		new Url().goBackToPreviousPage();
		}
		return isPresent;
	}
}
