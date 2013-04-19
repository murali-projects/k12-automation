package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewStatisticsDocument extends BasePageContainer {

	String documentTitle;

	public ViewStatisticsDocument() throws Exception {
		super();
		documentTitle = new Link(properties
				.get("statistics_searchResults_xpath")
				+ "/h3/a").getLinkText();
	}

	public boolean verifyDisplayGroupName() {
		return new TextLabel(properties.get("statistics_label_search"))
				.isPresent();
	}

	public boolean verifyViewAllIsDisplayed() {
		return new Link(properties.get("statistics_viewAll")).isPresent();
	}

	public boolean verifyStatisticsCount() throws Exception {
		if (Integer
				.parseInt(new TextLabel(properties.get("statistics_viewAll"))
						.getLabelText().split(" ")[2].replace(",", "")) > 3) {
			return new TextLabel(properties
					.get("statistics_searchResults_xpath")).getXpathCount() == 3;
		}
		return new TextLabel(properties.get("statistics_searchResults_xpath"))
				.getXpathCount() > 0;

	}

	public boolean verifyDetailedViewIsDisplayed() throws InterruptedException {
		new Link(properties.get("statistics_searchResults_xpath") + "/h3/a")
				.click();
		return (new TextLabel(properties.get("statistics_title_in_details"))
				.isPresent());
	}

	public boolean verifyStatisticalImageIsDisplayedInDetailedView()
			throws InterruptedException {
		return (new TextLabel(properties.get("statistics_image_xpath"))
				.isPresent());
	}

	public boolean verifyDocumentTitleInDetailedView()
			throws InterruptedException {
		return (new TextLabel(properties.get("statistics_title_in_details"))
				.getLabelText().equals(documentTitle));
	}

	public boolean verifyPreviousNextThumbnailsPresent() {
		return (new TextLabel(properties.get("statistics_next_thumbnail"))
				.isPresent() && new TextLabel(properties
				.get("statistics_previous_thumbnail")).isPresent());
	}

	public boolean verifyGaleDocumentAndCopyrightDisplayed() {
		return (new TextLabel(properties.get("statistics_gale_document"))
				.isPresent() && new TextLabel(properties.get("copyright_label"))
				.isPresent());
	}

	public boolean verifySearchFieldsPresent() throws Exception {
		return (new PageButton(properties.get("findButton")).isPresent() && new TextBox(
				properties.get("searchBox")).isPresent());
	}

	public boolean verifyImageByNavigatingFromViewAllPage()
			throws InterruptedException {
		new Url().goBackToPreviousPage();
		new Link(properties.get("statistics_viewAll")).click();
		new Link(properties.get("viewAll_searchResults") + "/td[2]/h3/a")
				.click();
		return verifyStatisticalImageIsDisplayedInDetailedView();
	}

	public boolean verifyHyperLinksOfDocuments(){
		for(int i = 1; i <= new Link(properties.get("statistics_searchResults_xpath")).getLinkCount(); i++){
			String tilteDisplayed = new Link(properties.get("statistics_searchResults_xpath")+"["+i+"]/h3/a").getLinkText();
			if(!new TextLabel(properties.get("statistics_searchResults_xpath")+"["+i+"]/h3/a").getAttribute("title").contains(tilteDisplayed.substring(0,tilteDisplayed.length()-1))){
				return false;
			}
		}
		return true;
	}

	public boolean verifyContentForStatisticImage() {
		return new TextLabel(properties.get("statistics_image_content"))
				.isPresent();
	}

	public boolean verifyNoSpeakerIconIsPresent() {
		return (!new TextLabel(properties.get("speaker_icon")).isPresent());
	}
}
