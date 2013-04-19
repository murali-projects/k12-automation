package webPageContainers4Testing.dev;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.RadioButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ToolsDisplayGroup extends ViewSearchResultPortlet {

	public String toolsPortletLocator = "//div[@id='tools_wrapper']";
//	public String contentLevelXpath ="view_all_content_level";
	public DropDownBox sortBy= new DropDownBox(properties.get("viewAll_sort"));
	public Link bookmarkLink = new Link(properties.get("tools_bookmark_link"));
	public Link downloadLink = new Link(properties.get("tools_download_link"));
	public Link shareLink = new Link(properties.get("tools_share_link"));
	public Link citationLink = new Link(properties.get("tools_citation_link"));
	public Link emailLink = new Link(properties.get("tools_email_link"));
	public Link printLink = new Link(properties.get("tools_print_link"));
	public Link dictionaryLink = new Link(properties.get("tools_dictionary_link"));
	public Link translateLink = new Link(properties.get("tools_translate_link"));
	public PageButton translateButton = new PageButton(properties.get("tools_translate_button"));
	
	public String basePage;

	public String bookmarkDialogLocator = "//div[@id='bookmark_dialog']";
	public PageButton bookmarkDialogCancelButton = new PageButton("//div[div[@id='bookmark_dialog']]/div/button[text() = 'Close']");

	public String downloadDialogLocator = "//div[@id='download_dialog']";
	public PageButton downloadDialogDownloadButton = new PageButton("//div[div[@id='download_dialog']]/div/button[text() = 'Download']");
	public PageButton downloadDialogCancelButton = new PageButton("//div[div[@id='download_dialog']]/div/button[text() = 'Cancel']");
	public RadioButton downloadTextRadioButton = new RadioButton("//form[@id='dl_form']/input[@id='dl_format_text']");
	public RadioButton downloadHtmlRadioButton = new RadioButton("//form[@id='dl_form']/input[@id='dl_format_html']");
	public RadioButton downloadPdfRadioButton =  new RadioButton("//form[@id='dl_form']/input[@id='dl_format_pdf']");
	
	public String citationsDialogLocator = "//div[@id='citation_dialog']";
	public PageButton citationDialogDownloadButton = new PageButton("//div[div[@id='citation_dialog']]/div/button[text() = 'Download']");
	public PageButton citationDialogCancelButton   = new PageButton("//div[div[@id='citation_dialog']]/div/button[text() = 'Cancel']");
	public RadioButton citationMlaRadioButton      = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_mla']");
	public RadioButton citationApaRadioButton      = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_apa']");
	public RadioButton citationZ3980RadioButton    = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_z3980']");
	public RadioButton citationEndnoteRadioButton  = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_endnote']");
	public RadioButton citationProciteRadioButton  = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_procite']");
	public RadioButton citationRefmanRadioButton   = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_refman']");
	public RadioButton citationRefworksRadioButton = new RadioButton("//form[@id='citation_form']/input[@id='citation_format_refworks']");
	
	public String emailDialogLocator = "//div[@id='email_dialog']";
	public 	TextBox emailDialogSenderTextBox      = new TextBox("//form[@id='send_email_form']/div/div/input[@id='from']");
	public 	TextBox emailDialogSubjectTextBox     = new TextBox("//form[@id='send_email_form']/div/div/input[@id='subject']");
	public 	TextBox emailDialogToTextBox          = new TextBox("//form[@id='send_email_form']/div/div/div[@id='toBox']/input[@id='toBox_input']");
	public 	TextBox emailDialogMessageTextBox     = new TextBox("//form[@id='send_email_form']/div/div/textarea[@id='message']");
	public 	RadioButton emailDialogFormatTexthtmlRadioButton = new RadioButton("//form[@id='send_email_form']/div/div/input[@value='text_html']");
	public 	RadioButton emailDialogFormatPdfRadioButton      = new RadioButton("//form[@id='send_email_form']/div/div/input[@value='pdf']");
	public 	PageButton emailDialogCancelButton    = new PageButton("//div[div[@id='email_dialog']]/div/button[text() = 'Cancel']");
	public 	PageButton emailDialogSendButton      = new PageButton("//div[div[@id='email_dialog']]/div/button[text() = 'Send']");

	public String translateDialogLocator = "//div[@id='translate_dialog']";
	public 	DropDownBox translateLanguageOptionDropDownBox = new DropDownBox("//form[@id='translate_form']/select[@id='translate_languages']");
	public 	PageButton translateDialogCancelButton    = new PageButton("//div[div[@id='translate_dialog']]/div/button[text() = 'Cancel']");
	public 	PageButton translateDialogTranslateButton      = new PageButton("//div[div[@id='translate_dialog']]/div/button[text() = 'Translate']");
	
	public ToolsDisplayGroup(String displayGroupName) throws Exception {
		super(displayGroupName, displayGroupName+"_searchResults_xpath",
				displayGroupName+"_title_xpath");
		basePage = new Url().getUrl();
	}
	
	public Link getFirstItemInListPortlet() {
		String locator = properties.get(firstResultTitleXPath) + "[1]/h3/a";
		return new Link(locator);
	}
}