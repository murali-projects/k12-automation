package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllImagesOnSearchResultsPage extends BasePageContainer{

	private String searchTerm;
	public ViewAllImagesOnSearchResultsPage(String seachTerm) throws Exception {
		super();
		this.searchTerm = seachTerm;
	}
	
	public boolean verifyContentResultsInImages() throws Exception {
		return new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() > 0;
	}
	
	private String getCount(String locator) throws Exception {
		String displayGroup = new TextLabel(locator).getLabelText();
		return displayGroup.replaceAll(",", "");
	}

	public boolean verifyDefaultSortIsByPublicationDate() throws Exception {
		List<String> imagesDisplayed = getDocumentTitlesInAppl();
		List<String> imagesInDB = getDataFromDB(properties.get("publicationDate"), properties.get("documentTitle"));
		return compareWithDB(imagesDisplayed, imagesInDB);
	}
	
	private List<String> getDocumentTitlesInAppl() throws Exception {
		List<String> imagesDisplayed = new ArrayList<String>();
		for (int i = 0; i < new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			imagesDisplayed.add(new TextLabel("").getAttribute());
		}
		return imagesDisplayed;
	}
	
	private List<String> getDataFromDB(String sortBy, String dataToRetrieve) throws IOException{
		List<String> contentFromDB = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"),properties.get("images"),
				sortBy, dataToRetrieve, searchTerm);
		return contentFromDB;
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
	public boolean verifyImageContent() throws InterruptedException, Exception {
		new Link(properties.get("images_viewAll")).click();
			new Link(properties.get("images_thumbnails") + "/a").click();
			if (new TextLabel(properties.get("images_title_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("images_publisherName_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("images_publicationDate_in_detailedView")).isPresent())
				new Url().goBackToPreviousPage();
			else {
				return false;
			}
		return true;
	}
	
	public boolean verifyImagesHeadingIsPresent() throws InterruptedException{
		new Link(properties.get("images_viewAll")).click();
		return new TextLabel(properties.get("images_viewAll_label")).isPresent();
	}
 
	public boolean ensureMarkOptionIsAvailable() throws Exception {
		for (int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			if (!new Link(properties.get("images_thumbnails") + "["+ i + "]"+ properties.get("images_markMe")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean verifyNextLinkIsworking() throws Exception {
		Thread.sleep(1000);
		String nextImage = new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_nextLink")).isPresent()){
			new PageButton(properties.get("images_nextLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title")).equals(nextImage);
		}
		return true;
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		Thread.sleep(1000);
		String previousImage = new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_previousLink")).isPresent()){
			new PageButton(properties.get("images_previousLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title")).equals(previousImage);
		}
		return true;
	}

	public boolean detailImageNavigation() throws InterruptedException,	Exception {
		String imageTitle = new TextLabel(properties.get("images_thumbnails")+ "/p").getLabelText();
		new Link(properties.get("images_thumbnails") + "/a").click();
		String imageTitleInDetailedPage = new TextLabel(properties.get("images_title_in_detailedView")).getLabelText();
		return (imageTitle.equals(imageTitleInDetailedPage));
	}
	
	public boolean verifyTooltipIsDisplayed() throws Exception {
		for(int i = 1; i <= new TextLabel(properties.get("images_thumbnails")).getXpathCount(); i++){
			if(new TextLabel(properties.get("images_thumbnails")+ "["+ i + "]/a/img").getAttribute("alt").isEmpty())
				return false;
		}
		return true;
	}
	
	public boolean verifyImagesDisplayGroupNotDisplayed() throws Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath")).isPresent()) {
			return new TextLabel(properties.get("images_label")).isPresent();
		}
		return !(new TextLabel(properties.get("images_label")).isPresent());
	}
	
	public boolean verifyImagesCountIsDisplayed() throws NumberFormatException, Exception {
		if (Integer.parseInt(new TextLabel(properties.get("images_viewAll"))
				.getLabelText().split(" ")[2]) > 9);
		return new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() == 9;
	}

	public boolean viewAllIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() > 9) {
			return new TextLabel(properties.get("images_viewAll")).isPresent();
		}
		return true;
	}
	
	public boolean verifyViewAllIsNotDisplayed() throws Exception {
		if (new TextLabel(properties.get("images_searchResults_xpath"))
				.getXpathCount() < 9) {
			return !new TextLabel(properties.get("images_viewAll")).isPresent();
		}
		return new TextLabel(properties.get("images_viewAll")).isPresent();
	}
	
	public boolean validateImagesWithOcean() throws InterruptedException, Exception{
		List<String> imageTitlesInDB = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), 
				properties.get("images"), properties.get("relevance"), properties.get("documentTitle"), searchTerm);
		for(int i = 1; i <= new TextLabel(properties.get("images_thumbnails")).getXpathCount(); i++){
			String imageTitle = new TextLabel(properties.get("images_thumbnails")+ "["+ i + "]/p").getLabelText();
			if(!imageTitle.equals(imageTitlesInDB.get(i-1)))
				return false;
		}
		return true;
	}
}
