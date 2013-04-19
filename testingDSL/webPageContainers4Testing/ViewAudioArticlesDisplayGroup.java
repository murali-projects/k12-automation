package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewAudioArticlesDisplayGroup extends BasePageContainer {

	private Link viewAllLink;
	private TextLabel audioDisplayGroupLabel;
	Link audioContentItem;
	TextLabel transcriptTitle;
	private String searchTerm;

	public ViewAudioArticlesDisplayGroup(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
		viewAllLink = new Link(properties.get("audio_viewAll"));
		audioContentItem = new Link(properties.get("audio_content"));
		transcriptTitle = new TextLabel(properties.get("transcript_title"));

	}

	public boolean checkAudioArticlesDisplayGroupIsPresent() throws Exception {
		audioDisplayGroupLabel = new TextLabel(properties
				.get("audio_display_group_title"));
		if (audioDisplayGroupLabel.isPresent()
				&& audioDisplayGroupLabel.getLabelText().equals(
						properties.get("expected_audio_display_group_title")))
			return true;
		return false;
	}
	
	public boolean checkAudioArticlesDisplayGroupIsNotPresent() throws Exception {
		audioDisplayGroupLabel = new TextLabel(properties
				.get("audio_display_group_title"));
		if (audioDisplayGroupLabel.isPresent())
			return false;
		return true;
	}

	public boolean checkAudioContentLinksArePresent() throws Exception {

		if (checkAudioArticlesDisplayGroupIsPresent()) {
			if (new TextLabel(properties.get("audio_article_links"))
					.getXpathCount() > 0)
				return true;
		}
		return false;
	}

	public boolean verifyAudioArticleNameIsLink() throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		for (int i = 1; i <= noOfAudioArticles; i++) {
			if (!new TextLabel(properties.get("audio_content")+"[" + i + "]/h3/a").isPresent()) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyAudioIconIsDisplayed() throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		int noOfAudioIcons = new TextLabel(properties
				.get("expected_audio_display_group_title")
				+ "/a/img").getXpathCount();

		return (noOfAudioArticles == noOfAudioIcons) ? true : false;
	}

	public boolean verifyAudioArticlesCount(String searchTerm)
			throws NumberFormatException, Exception {
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("p2"), properties.get("audio"), properties
						.get("publicationTitle"), searchTerm);
		String audioArticleCountFromDB = (list != null && !list.isEmpty()) ? list
				.get(0)
				: "0";
		if (!viewAllLink.isPresent()) {
			return (new TextLabel(properties.get("audio_article_links"))
					.getXpathCount() == Integer
					.parseInt(audioArticleCountFromDB)) ? true : false;
		}
		String[] viewAllText = viewAllLink.getLinkText().split(" ");
		return (viewAllText[viewAllText.length - 1]
				.equals(audioArticleCountFromDB)) ? true : false;
	}

	public boolean verifyPublicatinNamesArePresent() throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		for (int i = 1; i <= noOfAudioArticles; i++) {
			if (!new TextLabel(properties.get("audio_article_links") + "[" + i
					+ "]" + properties.get("audio_pubTitle"))
					.isPresent()) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyPublicatinDatesArePresent() throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		for (int i = 1; i <= noOfAudioArticles; i++) {
			if (!new TextLabel(properties.get("audio_article_links") + "[" + i
					+ "]" + properties.get("audio_pubDate")).isPresent()) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyAudioArticlesSortedByPublicationDates()
			throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		List<String> publicationDates = new ArrayList<String>();
		for (int i = 1; i <= noOfAudioArticles; i++) {
			publicationDates.add(new TextLabel(properties
					.get("audio_article_links")
					+ "[" + i + "]" + properties.get("audio_pubDate"))
					.getLabelText());
		}
		return verifyElementsInApplWithDB(publicationDates, properties
				.get("publicationDate"));
	}

	private boolean verifyElementsInApplWithDB(
			List<String> elementsListFromAppl, String dataBaseElement)
			throws Exception {
		List<String> elementsFromDB = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("p2"), properties
						.get("audio"), properties.get("publicationTitle"),
						dataBaseElement, searchTerm);
		if (elementsFromDB.size() > 0 && elementsListFromAppl.size() > 0) {
			for (int i = 0; i <= elementsListFromAppl.size(); i++) {
				if (!(elementsListFromAppl.get(i).equals(elementsFromDB.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyContentItemIsViewed() throws InterruptedException,
			Exception {

		// Click on the content items of audio
		audioContentItem.click();
		// Now click back button of the browser
		selenium.goBack();
		if (verifyContentItem(audioContentItem)) {
			return true;
		}
		return false;
	}

	private boolean verifyContentItem(Link contentItem) throws Exception {

		// Move mouse over the audio content item to verify the text
		selenium.mouseOver(contentItem.getLinkText());
		// Put focus on the tooltip to retrieve the text
		selenium.focus("");
		String msg = new TextLabel("").getLabelText();
		return msg.equals(properties.get("tooltip_message")) ? true : false;
	}

	public boolean verifyDetailedViewIsDisplayed() throws Exception {

		int noOfAudioArticles = new TextLabel(properties
				.get("audio_article_links")).getXpathCount();
		for (int i = 1; i <= noOfAudioArticles; i++) {
			Thread.sleep(2000);
			new Link(properties.get("audio_article_links") + "["
					+ i + "]" + "/h3/a").click();
			if (!(new TextLabel(properties.get("audio_details"))
					.isPresent() && new TextLabel(
							properties.get("audio_source")).isPresent())) {
				return false;
			}
			selenium.goBack();
		}
		return true;
	}
}
