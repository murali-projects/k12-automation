package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.TextDocumentTranslate;


public class TextDocumentTranslateText extends BaseWebPageTest {

	public TextDocumentTranslate textdocumenttranslate;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		textdocumenttranslate = new TextDocumentTranslate();
	}

	@Test
	public void verifyTitleOfTranslatedDocument() throws Exception {
		Assert.assertTrue(textdocumenttranslate
				.verifyTitleOfTranslatedDocument());

	}

	@Test(dependsOnMethods = "verifyTitleOfTranslatedDocument")
	public void verifyWordsToKnowAvailable() throws Exception {

		Assert.assertTrue(textdocumenttranslate.verifyWordsToKnowAvailable());
	}

	@Test(dependsOnMethods = "verifyWordsToKnowAvailable")
	public void verifyCitationIsPresent() throws Exception {

		Assert.assertTrue(textdocumenttranslate.verifyCitationIsPresent());
	}

	@Test(dependsOnMethods = "verifyCitationIsPresent")
	public void verifyGaleDocumentNoIsPresent() throws Exception {

		Assert.assertTrue(textdocumenttranslate.verifyGaleDocumentNoIsPresent());
	}

	@Test(dependsOnMethods = "verifyGaleDocumentNoIsPresent")
	public void verifyDisclaimerIsPresent() throws Exception {

		Assert.assertTrue(textdocumenttranslate.verifyDisclaimerIsPresent());
	}

	@Test(dependsOnMethods = "verifyDisclaimerIsPresent")
	public void verifyNoreadSpeakerLinksPresent() throws Exception {

		Assert.assertTrue(textdocumenttranslate
				.verifyNoreadSpeakerLinksPresent());
	}

}
