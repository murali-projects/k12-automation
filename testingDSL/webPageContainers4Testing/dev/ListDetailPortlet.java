package webPageContainers4Testing.dev;

import com.thoughtworks.selenium.Wait;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ListDetailPortlet extends BaseDevPageContainer {

	protected String displayGroupName;
	protected String baseUrl = "";

	public ListDetailPortlet(String displayGroupName) throws Exception {
		super();
		Url url = new Url();
		baseUrl = url.getUrl();
		this.displayGroupName = displayGroupName;
	}

	public boolean checkRelatedSubjectsIsPresent() {
		String relatedSubjectsTitleXpath = "related_subjects_title_xpath";
		TextLabel relatedSubjectTitle = new TextLabel(properties
				.get(relatedSubjectsTitleXpath));
		return relatedSubjectTitle.isPresent();
	}

	public boolean checkRelatedSubjectsLinkIsWorking()
			throws InterruptedException {

		String firstResultTitleXPath = "related_subjects_first_subject_xpath";
		String locator = properties.get(firstResultTitleXPath);
		String firstDocumentTitle = new TextLabel(locator).getLabelText();
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();

		String searchStatusXpath = properties
				.get("searchStatus_xpath");
		TextLabel searchStatus = new TextLabel(searchStatusXpath);
		String searchStatusText = searchStatus.getLabelText();

		return searchStatusText.contains(firstDocumentTitle);

	}

	public void checkRelatedSubjects(String displayGroupProperty)
			throws Exception {
		Url url = new Url();
		String detailsPageUrl = baseUrl + properties.get(displayGroupProperty);
		url.goToPage(detailsPageUrl);
	}

	public boolean checkRelatedSubjectsMoreLinkIsPresent() {
		String relatedSubjectMoreLinkXpath = "related_subject_more_link_xpath";
		Link relatedSubjectMoreLink = new Link(properties
				.get(relatedSubjectMoreLinkXpath));	
		return relatedSubjectMoreLink.isPresent();
	}
	
	public boolean checkRelatedSubjectsLessLinkIsPresent() {
		String relatedSubjectLessLinkXpath = "related_subject_less_link_xpath";
		Link relatedSubjectLessLink = new Link(properties
				.get(relatedSubjectLessLinkXpath));	
		return relatedSubjectLessLink.isPresent();
	}

	public boolean checkRelatedSubjectsMoreLinkIsWorking() throws InterruptedException {
		String relatedSubjectMoreLinkXpath = "related_subject_more_link_xpath";
		Link relatedSubjectMoreLink = new Link(properties
				.get(relatedSubjectMoreLinkXpath));			
		relatedSubjectMoreLink.clickWithoutWait();
		return getRelatedSubjectLinksMoreThanFive();
		
		
	}

	public boolean checkRelatedSubjectsLessLinkIsWorking() throws InterruptedException {
		String relatedSubjectLessLinkXpath = "related_subject_less_link_xpath";
		Link relatedSubjectLessLink = new Link(properties
				.get(relatedSubjectLessLinkXpath));
		relatedSubjectLessLink.clickWithoutWait();
		
		return !getRelatedSubjectLinksMoreThanFive();
		
		
	}
	
	protected boolean getRelatedSubjectLinksMoreThanFive() throws InterruptedException {
		String relatedSubjectLinksXpath = "related_subjects_links_xpath";		
		TextLabel relatedSubjectLinksXpathList = new TextLabel(properties
				.get(relatedSubjectLinksXpath));	
		Thread.sleep(375);
		return relatedSubjectLinksXpathList.isVisible();
		
	}

	public boolean checkSourceCitationIsPresent() {
		String sourcecitationXpath = "source_citation_title_xpath";
		TextLabel sourceCitationTitleLabel = new TextLabel(properties.get(sourcecitationXpath));
		
		if(!sourceCitationTitleLabel.isVisible()) {
			return false;
		}

		TextLabel sourceCitationContent = new TextLabel(properties.get("source_citation_content_xpath"));
		// int labelCount = relatedSubjectLinksXpathList.getXpathCount();
		if (sourceCitationContent.getLabelText().length() < 20) {
			return false;
		}

		return true;

	}

	public boolean checkGaleDocumentNumberIsPresent() {
		String sourcecitationXpath = "gale_document_number_xpath";
		TextLabel sourceCitationTitleLabel = new TextLabel(properties.get(sourcecitationXpath));
		return sourceCitationTitleLabel.getLabelText().contains("GALE|");

	}

	public boolean checkShortDescriptionIsPresent() {
		String shortDescriptionXpath = "details.short_description.xpath";
		TextLabel shortDescriptionText = new TextLabel(properties.get(shortDescriptionXpath));
		return !shortDescriptionText.getLabelText().isEmpty();
	}
	
	public boolean checkExternalUrlIsPresent() {
		String externalUrlXpath = "details.url.xpath";
		Link externalUrlLink = new Link(properties.get(externalUrlXpath));
		return !externalUrlLink.getLinkText().isEmpty();
	}
}
