package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllAudioOnSearchResultsPage extends BasePageContainer {

	private String searchTerm;
	private List<String> documentTitlesDisplayedList;
	private List<String> documentTitles;
	private int audioDisplayCount;

	public ViewAllAudioOnSearchResultsPage(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
		documentTitlesDisplayedList = new ArrayList<String>();
		documentTitles = new ArrayList<String>();
	}

	public boolean verifyAudioTextIsDisplayed() throws Exception {
		return new TextLabel(properties.get("audio_label")).getLabelText()
				.equalsIgnoreCase("audio") ? true : false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		if(new Link(properties.get("audio_viewAll")).isPresent()){
		new Link(properties.get("audio_viewAll")).click();
		String newUrl = new Url().getUrl();
		return (newUrl
				.contains("AudioFullListPage/AudioFullListWindow"));
		}
		return true;
	}

	private String getAudioDisplayGroupCount() throws Exception {
		if(new TextLabel(properties.get("audio_viewAll")).isPresent()){
		return new TextLabel(properties.get("audio_viewAll"))
				.getLabelText().split(" ")[2];
		}
		return new TextLabel(properties.get("audio_content"))
		.getXpathCount()+"";
	}

	public boolean verifyAudioContentLinksAreDisplayed() throws Exception {
		audioDisplayCount = Integer.parseInt(getAudioDisplayGroupCount());
		if (audioDisplayCount > 3) {
			return new TextLabel(properties.get("audio_content"))
					.getXpathCount() == 3;
		}
		return new TextLabel(properties.get("audio_content"))
				.getXpathCount() == audioDisplayCount;
	}

	public boolean validateViewAllLink() throws Exception {
		if(Integer.parseInt(getAudioDisplayGroupCount())>3){
		return new Link(properties.get("audio_viewAll"))
				.isPresent();
		}
		return true;
	}

	public boolean inLineValidationForViewAll() throws Exception {
		boolean isInline = true;
		if (Integer.parseInt(getAudioDisplayGroupCount()) > 3) {
			String audioContent = new TextLabel(properties.get("audio_viewAll")).getLabelText();
			if (!audioContent.contains("View All"))
				return isInline = false;
		}
		return isInline;
	}

	public boolean inLineValidationForDisplayCount() throws Exception {
		boolean isInlineDisplayCount = true;
		if (Integer.parseInt(getAudioDisplayGroupCount()) > 3) {
			return isInlineDisplayCount = !(getAudioDisplayGroupCount()
					.isEmpty());
		}
		return isInlineDisplayCount;
	}

	public boolean verifyAudioContentNavigation() throws InterruptedException,
			Exception {
		new Link(properties.get("audio_content_locator")).click();
		return new Url().getUrl().contains("podcastId=GALE|");
	}

	public boolean inLineValidationForAudioIcon() throws Exception {
		return new TextLabel(properties.get("audio_icon")).getLabelText()
				.endsWith("Listen");
	}

	public boolean verifyAudioIconNavigation() throws InterruptedException,
			Exception {
		new Link(properties.get("audio_icon_locator")).click();
		return new Url().getUrl().contains("podcastId=GALE|");
	}

	public boolean validateAudioDocumentTilesWithDB() throws Exception {
		boolean isValidate = false;
		documentTitles = getDocumentTitlesFromDB();
		documentTitlesDisplayedList = getDocumentTitlesDisplayed();
		for (int i = 0; i < documentTitlesDisplayedList.size(); i++) {
			for (int y = 0; y < documentTitles.size(); y++) {
				isValidate = documentTitlesDisplayedList.get(i)
						.equalsIgnoreCase(documentTitles.get(y));
				if (isValidate == true)
					break;
			}
		}
		return isValidate;
	}

	private List<String> getDocumentTitlesDisplayed() throws Exception {
		for (int i = 1; i <= documentTitles.size(); i++) {
			documentTitlesDisplayedList.add(new TextLabel(properties
					.get("audio_content")
					+ "[" + i + "]" +"/h3/a").getLabelText());
			if (i >= 3) {
				break;
			}
		}
		return documentTitlesDisplayedList;
	}

	private List<String> getDocumentTitlesFromDB() throws IOException {
		return documentTitles = new ArrayList<String>(new HashSet<String>(
				OceanDatabaseReadFile.readResultsFromFile(properties
						.get("all"), properties.get("content_group_audio"),
						properties.get("relevance"), properties
								.get("documentTitle"), searchTerm)));
	}

	public boolean pageHidination() throws InterruptedException, Exception {
		if (audioDisplayCount <= 20) {
			return (!(new TextLabel("next").isPresent()) && !(new TextLabel(
					"previous").isPresent()));
		}
		return true;
	}

	public boolean checkViewAllForAudio() throws NumberFormatException,
			Exception {
		boolean isValidate = false;
		if (Integer.parseInt(getAudioDisplayGroupCount()) < 3) {
			isValidate = !new TextLabel(properties.get("audio_viewAll"))
					.isPresent();
		} else {
			isValidate = new TextLabel(properties.get("audio_viewAll"))
					.isPresent();
		}
		return isValidate;

	}

	public boolean pageNumberNavigation(int pageNumberNeedToClick)
			throws InterruptedException, Exception {
		boolean isValidate = true;
		if(audioDisplayCount>20){
		new Link(properties.get("pageNumber"+"[" +pageNumberNeedToClick+"]")+"/a").click();
		if (Integer.parseInt(new TextLabel(properties.get("currentResults"))
				.getLabelText().split("-")[0].trim()) > pageNumberNeedToClick * 10) {
			return isValidate ;
		}
			else{
				return isValidate=false;
			}
		}
		return true;
	}

	public boolean shortDescriptionOfEachLink() throws InterruptedException,
			Exception {
		boolean isValidate = true;
		for (int i = 1; i <= new TextLabel(properties.get("displayGroup")).getXpathCount(); i++) {
			if (new TextLabel(properties
					.get("audio_viewAll_Link")
					+ "[" + i + "]" + properties.get("audio_viewAll_LinkValue"))
					.getLabelText().isEmpty()) {
				isValidate = false;
				break;
			}
		}
		return isValidate;
	}

	public boolean lastPageVerification() throws InterruptedException,
			Exception {
		if (new Link(properties.get("lastPage")).isPresent()) {
			new Link(properties.get("lastPage")).click();
			return new Link(properties.get("next")).isPresent() ? false : true;
		}
		return true;
	}

	public boolean firstPageVerification() throws InterruptedException,
			Exception {
		if (new Link(properties.get("firstPage")).isPresent()) {
			new Link(properties.get("firstPage")).click();
			return new Link(properties.get("previous")).isPresent() ? false
					: true;
		}
		return true;
	}

}
