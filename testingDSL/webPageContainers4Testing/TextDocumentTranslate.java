package webPageContainers4Testing;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class TextDocumentTranslate extends BasePageContainer {

	public TextDocumentTranslate() throws Exception {
		super();
	}

	public boolean verifyTitleOfTranslatedDocument() throws Exception {
		new Link(properties.get("translate_viewpoints_options")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new DropDownBox(properties.get("translate_dropdown"))
				.select("label=Spanish");
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(10000);
		return (new TextLabel(properties.get("popup_title")).isPresent());
	}

	public boolean verifyWordsToKnowAvailable() throws Exception {
		return (new TextLabel(properties.get("translate_words_to_know"))
				.isPresent());
	}

	public boolean verifyCitationIsPresent() throws Exception {
		return (new TextLabel(properties.get("source_citation")).isPresent());
	}

	public boolean verifyGaleDocumentNoIsPresent() throws Exception {
		return (new TextLabel(properties.get("gale_document")).isPresent());
	}

	public boolean verifyDisclaimerIsPresent() throws Exception {
		return (new TextLabel(properties.get("desclaimer")).isPresent());
	}

	public boolean verifyNoreadSpeakerLinksPresent() throws Exception {
		if (!new TextLabel(properties.get("translate_read_speaker_icons"))
				.isPresent()){
			selenium.close();
			return true;
		}
		return false;

	}

}
