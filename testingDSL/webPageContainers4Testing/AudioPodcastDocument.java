package webPageContainers4Testing;

import java.io.IOException;
import java.util.List;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class AudioPodcastDocument extends BasePageContainer {



	public AudioPodcastDocument() throws Exception {
		super();
	}

	public String titleOfThePodcast() throws Exception {
		return (new Link(properties.get("podcast_content_document_Titlelink"))
				.getLinkText());
	}

	public boolean podcastDetailedPageIsDisplayed() throws Exception {
		String expectedString = new Link(properties.get("podcast_content_link"))
		.getLinkText();
		new Link(properties.get("podcast_content_link")).click();
		return titleOfThePodcast().equals(expectedString);
	}

	public boolean podcastTitleIsDisplayed() throws Exception {
		return (new TextLabel(properties.get("podcast_content_document_Titlelink"))
				.isPresent());
	}

	public boolean podcastPageSearchBoxIsDisplayed() throws Exception {
		return (new TextLabel(properties.get("searchBox")).isPresent());
	}

	public boolean podcastPageSearchButtonIsDisplayed() throws Exception {
		return (new TextLabel(properties.get("findButton")).isPresent());
	}

	public boolean podcastPageCopyrightIsDisplayed() throws Exception {
		return (new TextLabel(properties.get("copyright_link")).isPresent());
	}

	public boolean podcastPageSourceCitationIsDisplayed() throws Exception {
		return ((new TextLabel(properties.get("podcast_Page_SourceCitation")).isPresent())
				&& new TextLabel(properties.get("podcast_Page_SourceCitation")).getLabelText()
				.contains("Source Citation"));
	}

	public boolean verifyTranscriptIsDisplayed(String searchTerm)
			throws Exception {
		new Url().goBackToPreviousPage();
		boolean verified = false;
		List<String> docTypesFromDB = getDataFromDB(searchTerm, properties
				.get("docTypes"));
		for (int i = 1; i <= new TextLabel(properties.get("audio_searchResults_xpath"))
				.getXpathCount(); i++) {
			new Link(properties.get("audio_searchResults_xpath") + "[" + i + "]/h3/a")
					.click();
			if (docTypesFromDB.get(i-1).contains("transcript")) {
				verified = verifyElementPresent(properties.get("transcript_xpath"));
			} else
				verified = verifyElementPresent(properties.get("abstract_xpath"));
			if (!verified)
				break;
			else
				new Url().goBackToPreviousPage();
		}
		return verified;
	}

	private List<String> getDataFromDB(String searchTerm, String elementType)
			throws IOException {
		List<String> list = OceanDatabaseReadFile.readResultsFromFile("all",
				"Audio", "Relevance", elementType, searchTerm);
		return list;
	}

	public boolean verifyGaleDocumentNo(String displayGroup, String sortBy,
			String searchTerm) throws Exception {
		if (new TextLabel(properties.get("audio_gale_documentNo")).isPresent()) {
			List<String> documentIdsFromDB = OceanDatabaseReadFile
					.readResultsFromFile(properties.get("all"), properties.get(displayGroup), properties
							.get(sortBy), properties.get("documentId"),
							searchTerm);
			return (documentIdsFromDB != null && !documentIdsFromDB.isEmpty()) ? ((new TextLabel(properties.get("audio_gale_documentNo")).getLabelText())).contains(documentIdsFromDB.get(0))
					: false;
		}
		return false;
	}

	public boolean verifyElementPresent(String element_xpath) throws Exception {
		return new TextLabel(element_xpath).isPresent();
	}

	public boolean verifyBreadCrumbNavigation() throws Exception {
		new Link(properties.get("breadCrumb_Results_link")).click();
		if (new TextLabel(properties.get("audio_searchResults_xpath")).isPresent()) {
			return true;
		}
		return false;
	}

}
