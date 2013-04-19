package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class IntegrateImagesDisplayGroup extends BasePageContainer {

	private String searchTerm;

	public IntegrateImagesDisplayGroup(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}

	public boolean verifyContentResultsInImages() throws Exception {
		return new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() > 0;
	}

	public boolean validateImagesWithOcean() throws Exception {
		List<String> imagesDisplayed = getImagesDisplayed();
		List<String> imagesDisplayedInDB = getImagesDisplayedFromDB();
		return compareWithDB(imagesDisplayed, imagesDisplayedInDB);
	}

	private List<String> getImagesDisplayed() throws Exception {
		List<String> imagesDisplayed = new ArrayList<String>();
		for (int i = 1; i < new TextLabel(properties
				.get("images_searchResults_xpath")).getXpathCount(); i++) {
			imagesDisplayed.add(new TextLabel(properties.get("images_searchResults_xpath")+"[" +i +"]").getLabelText());
		}
		return imagesDisplayed;
	}

	private List<String> getImagesDisplayedFromDB() throws IOException {
		List<String> imagesDisplayedInDB = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get("images"), properties.get("publicationDate"),
						properties.get("documentTile"), searchTerm);
		return imagesDisplayedInDB;
	}

	private boolean compareWithDB(List<String> displayedContent,
			List<String> displayedContentFromDB) {
		for (int i = 0; i < displayedContent.size(); i++) {
			if (!(displayedContent.get(i).equals(displayedContentFromDB.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyImagesCount() throws NumberFormatException, Exception {
		if (Integer.parseInt(new TextLabel(properties.get("images_viewAll"))
				.getLabelText().split(" ")[2]) > 9);
		return new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() == 9;
	}

	public boolean verifyTooltipIsDisplayed() throws InterruptedException,Exception {
		for (int i = 1; i < new TextLabel(properties.get("images_searchResults_xpath")).getXpathCount(); i++) {
			if(new TextLabel(properties.get("images_searchResults_xpath")+"[" +i +"]/img").getAttribute("alt").isEmpty())
				return false;
		}
		return true;
	}
	
	public boolean viewAllIsNotDisplayed() throws NumberFormatException,
			Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() < 9) {
			return !new TextLabel(properties.get("images_viewAll")).isPresent();
		}
		return new TextLabel(properties.get("images_viewAll")).isPresent();
	}

	public boolean detailImageNavigation() throws InterruptedException,
			Exception {
		new Link(properties.get("images_thumbnails") + "[" + 1 + "]/a").click();
		return new TextLabel(properties.get("images_title_in_detailedView"))
				.isPresent();
	}

	public boolean viewAllIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() > 9) {
			return new TextLabel(properties.get("images_viewAll")).isPresent();
		}
		return true;
	}

	public boolean verifyImageText() throws Exception {
		return new TextLabel(properties.get("images_label")).isPresent();
	}

	public boolean verifyNoImagesDisplayed() throws Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() == 0) {
			return !new TextLabel(properties.get("images_label")).isPresent();
		}
		return new TextLabel(properties.get("images_label")).isPresent();
	}

	public boolean ensureMarkOptionIsAvailable() throws Exception {
		new Link(properties.get("images_viewAll")).click();
		for (int i = 1; i <= new TextLabel(properties
				.get("images_searchResults_xpath")).getXpathCount(); i++) {
			if (!new Link(properties.get("images_thumbnails")
					+ properties.get("images_markMe")).isPresent())
				return false;
		}
		return true;
	}

	public boolean verifyImageContent() throws InterruptedException, Exception {
		new Link(properties.get("images_viewAll")).click();
		for (int i = 1; i <= new TextLabel(properties.get(properties
				.get("images_searchResults_xpath"))).getXpathCount(); i++) {
			new Link(properties.get("images_thumbnails") + "[" + i + "]").click();
			if (new TextLabel(properties.get("images_title_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("gale_document")).isPresent()
					&& new TextLabel(properties.get("images_publicationDate_in_detailedView")).isPresent())
				new Url().goBackToPreviousPage();
			else {
				return false;
			}
		}
		return true;
	}

}
