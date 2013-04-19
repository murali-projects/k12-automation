package k12.dev.doctools;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import k12.dev.BaseDevWebPageTest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import util.SeleniumSingletonFactory;
import webPageContainers4Testing.dev.ToolsDisplayGroup;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextBox;

public class ImagesToolsTest extends BaseDevWebPageTest {

	public static final String GROUP = "image";
	private ToolsDisplayGroup toolsDisplayGroup;
	private static final String SEARCH_TERM = "war";

	@BeforeClass
	public void init() throws Exception {
		selenium = SeleniumSingletonFactory.getInstance();
		toolsDisplayGroup = new ToolsDisplayGroup(GROUP);		
	}

	@Test
	public void gotoDetailsPage() throws Exception {
		doBasicSearchUsingSearchTerm(SEARCH_TERM);
		Link firstItemLink = new Link("//div[@id='images']/div/a[1]");
		firstItemLink.click();
		waitForElement(toolsDisplayGroup.toolsPortletLocator);
	}

	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyBookmarks() throws Exception {
		Link bookmarkLink = toolsDisplayGroup.bookmarkLink;
		assertTrue(bookmarkLink.isPresent());
		bookmarkLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.bookmarkDialogLocator);
		PageButton button = toolsDisplayGroup.bookmarkDialogCancelButton;
		assertTrue(button.isPresent());
		button.click();
	}
	
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyDownload() throws Exception {
		Link bookmarkLink = toolsDisplayGroup.downloadLink;
		assertTrue(bookmarkLink.isPresent());
		bookmarkLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.downloadDialogLocator);
		
		RadioButton downloadHtmlRadioButton = toolsDisplayGroup.downloadHtmlRadioButton;
		assertTrue(downloadHtmlRadioButton.isPresent());
		assertTrue(downloadHtmlRadioButton.isChecked(), "should be checked as default");
		
		PageButton downloadButton = toolsDisplayGroup.downloadDialogDownloadButton;
		assertTrue(downloadButton.isPresent());
		PageButton downloadCancel = toolsDisplayGroup.downloadDialogCancelButton;
		assertTrue(downloadCancel.isPresent());
		downloadCancel.click();	
	}
	
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyShare() throws Exception {
		Link bookmarkLink = toolsDisplayGroup.downloadLink;
		assertTrue(bookmarkLink.isPresent());		
	}
	
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyCitations() throws Exception {
		Link citationsLink = toolsDisplayGroup.citationLink;
		assertTrue(citationsLink.isPresent());
		citationsLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.citationsDialogLocator);
		
		RadioButton citationMla7RadioButton = toolsDisplayGroup.citationMlaRadioButton;
		assertTrue(citationMla7RadioButton.isPresent());
		assertTrue(citationMla7RadioButton.isChecked(), "should be checked as default");
		
		RadioButton citationApaRadioButton = toolsDisplayGroup.citationApaRadioButton;
		assertTrue(citationApaRadioButton.isPresent());
		assertFalse(citationApaRadioButton.isChecked(), "should not be checked");
		citationApaRadioButton.click();
		assertTrue(citationApaRadioButton.isChecked(), "should be checked now");
		
		RadioButton citationZ3980RadioButton = toolsDisplayGroup.citationZ3980RadioButton;
		assertTrue(citationZ3980RadioButton.isPresent());
		assertFalse(citationZ3980RadioButton.isChecked(), "should not be checked");
		citationZ3980RadioButton.click();
		assertTrue(citationZ3980RadioButton.isChecked(), "should be checked now");
				
		RadioButton citationEndnoteRadioButton = toolsDisplayGroup.citationEndnoteRadioButton;
		assertTrue(citationEndnoteRadioButton.isPresent());
		assertFalse(citationEndnoteRadioButton.isChecked(), "should not be checked");
		citationEndnoteRadioButton.click();
		assertTrue(citationEndnoteRadioButton.isChecked(), "should be checked now");
				
		RadioButton citationProciteRadioButton = toolsDisplayGroup.citationProciteRadioButton;
		assertTrue(citationProciteRadioButton.isPresent());
		assertFalse(citationProciteRadioButton.isChecked(), "should not be checked");
		citationProciteRadioButton.click();
		assertTrue(citationProciteRadioButton.isChecked(), "should be checked now");
				
		RadioButton citationRefmanRadioButton = toolsDisplayGroup.citationRefmanRadioButton;
		assertTrue(citationRefmanRadioButton.isPresent());
		assertFalse(citationRefmanRadioButton.isChecked(), "should not be checked");
		citationRefmanRadioButton.click();
		assertTrue(citationRefmanRadioButton.isChecked(), "should be checked now");
				
		RadioButton citationRefworksRadioButton = toolsDisplayGroup.citationRefworksRadioButton;
		assertTrue(citationRefworksRadioButton.isPresent());
		assertFalse(citationRefworksRadioButton.isChecked(), "should not be checked");
		citationRefworksRadioButton.click();
		assertTrue(citationRefworksRadioButton.isChecked(), "should be checked now");
				
		PageButton downloadButton = toolsDisplayGroup.citationDialogDownloadButton;
		assertTrue(downloadButton.isPresent());
		PageButton downloadCancel = toolsDisplayGroup.citationDialogCancelButton;
		assertTrue(downloadCancel.isPresent());
		downloadCancel.click();
	}
	
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyEmail() throws Exception {
		Link emailLink = toolsDisplayGroup.emailLink;
		assertTrue(emailLink.isPresent());
		emailLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.emailDialogLocator);
		
		TextBox emailDialogSenderTextBox = toolsDisplayGroup.emailDialogSenderTextBox;
		assertTrue(emailDialogSenderTextBox.isPresent());
		assertEquals(emailDialogSenderTextBox.getValue(), "", "should be empty");
		
		TextBox emailDialogSubjectTextBox = toolsDisplayGroup.emailDialogSubjectTextBox;
		assertTrue(emailDialogSubjectTextBox.isPresent());
		assertTrue(emailDialogSubjectTextBox.getValue().length() > 0, "should not be empty");
		
		TextBox emailDialogToTextBox = toolsDisplayGroup.emailDialogToTextBox;
		assertTrue(emailDialogToTextBox.isPresent());
		assertEquals(emailDialogToTextBox.getValue(), "", "should be empty");
		
		TextBox emailDialogMessageTextBox = toolsDisplayGroup.emailDialogMessageTextBox;
		assertTrue(emailDialogMessageTextBox.isPresent());
		assertEquals(emailDialogMessageTextBox.getValue(), "", "should be empty");
		
		PageButton emailDialogSendButton = toolsDisplayGroup.emailDialogSendButton;
		assertTrue(emailDialogSendButton.isPresent());
		PageButton emailDialogCancelButton = toolsDisplayGroup.emailDialogCancelButton;
		assertTrue(emailDialogCancelButton.isPresent());
		emailDialogCancelButton.click();		
	}

	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyPrint() throws Exception {
		Link printLink = toolsDisplayGroup.printLink;
		assertTrue(printLink.isPresent());
	}
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyDictionary() throws Exception {
		Link dictionaryLink = toolsDisplayGroup.dictionaryLink;
		assertTrue(dictionaryLink.isPresent());		
	}
	@Test(dependsOnMethods = { "gotoDetailsPage" })
	public void verifyTranslate() throws Exception {
		String[] languages = {"Spanish", "French", "Japanese", "German", "Italian", "Portuguese", "Chinese (Simplified)", "Korean"};

		Link translateLink = toolsDisplayGroup.translateLink;
		assertTrue(translateLink.isPresent());
		translateLink.clickWithoutWait();
		waitForElement(toolsDisplayGroup.translateDialogLocator);
		
		DropDownBox lanDropBox = toolsDisplayGroup.translateLanguageOptionDropDownBox;
		assertTrue(lanDropBox.isPresent());
		for (String aLanguage : languages) {
			lanDropBox.select(aLanguage);
			assertEquals(lanDropBox.getSelectedOptionLabel(), aLanguage);			
		}				
				
		PageButton translateDialogTranslateButton = toolsDisplayGroup.translateDialogTranslateButton;
		assertTrue(translateDialogTranslateButton.isPresent());
		PageButton translateDialogCancelButton = toolsDisplayGroup.translateDialogCancelButton;
		assertTrue(translateDialogCancelButton.isPresent());
		translateDialogCancelButton.click();		
	}
}
