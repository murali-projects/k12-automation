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

public class ViewAllVideosOnSearchResultsPage extends BasePageContainer{

	private String searchTerm;
	public ViewAllVideosOnSearchResultsPage(String searchTerm) throws Exception {
		super();
		this.searchTerm = searchTerm;
	}

	public boolean verifyContentResultsInVideos() throws Exception {
		return new TextLabel(properties.get("videos_searchResults_xpath"))
				.getXpathCount() > 0;
	}
	
	private String getCount(String locator) throws Exception {
		String displayGroup = new TextLabel(locator).getLabelText();
		return displayGroup.replaceAll(",", "");
	}

	public boolean verifyDefaultSortIsByPublicationDate() throws Exception {
		List<String> videosDisplayed = getDocumentTitlesInAppl();
		List<String> videosInDB = getDataFromDB(properties.get("publicationDate"), properties.get("documentTitle"));
		return compareWithDB(videosDisplayed, videosInDB);
	}
	
	private List<String> getDocumentTitlesInAppl() throws Exception {
		List<String> videosDisplayed = new ArrayList<String>();
		for (int i = 0; i < new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			videosDisplayed.add(new TextLabel("").getAttribute());
		}
		return videosDisplayed;
	}
	
	private List<String> getDataFromDB(String sortBy, String dataToRetrieve) throws IOException{
		List<String> contentFromDB = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"),properties.get("videos"),
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
	public boolean verifyVideoContent() throws InterruptedException, Exception {
		new Link(properties.get("videos_viewAll")).click();
		for (int i = 1; i <= new TextLabel(properties.get(properties
				.get("viewAll_searchResults"))).getXpathCount(); i++) {
			new Link(properties.get("videos_thumbnails") + "[" + i + "]").click();
			if (new TextLabel(properties.get("videos_title_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("videos_publisherName_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("videos_publicationDate_in_detailedView")).isPresent()
					&& new TextLabel(properties.get("videos_recordCount_in_detailedView")).isPresent())
				new Url().goBackToPreviousPage();
			else {
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyVideosHeadingIsPresent() throws InterruptedException{
		new Link(properties.get("videos_viewAll")).click();
		return new TextLabel(properties.get("videos_viewAll_label")).isPresent();
	}
 
	public boolean ensureMarkOptionIsAvailable() throws Exception {
		for (int i = 1; i <= new TextLabel(properties.get("viewAll_searchResults")).getXpathCount(); i++) {
			if (!new Link(properties.get("videos_thumbnails") + "["+ i + "]"+ properties.get("videos_markMe")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean verifyNextLinkIsworking() throws Exception {
		String nextVideo = new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_nextLink")).isPresent()){
			new PageButton(properties.get("images_nextLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title")).equals(nextVideo);
		}
		return true;
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		String previousVideo = new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_previousLink")).isPresent()){
			new PageButton(properties.get("images_previousLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title")).equals(previousVideo);
		}
		return true;
	}

	public boolean detailVideoNavigation() throws InterruptedException,	Exception {
		String videoTitle = new TextLabel(properties.get("videos_thumbnails")+ "/p").getLabelText();
		new Link(properties.get("videos_thumbnails") + "/a").click();
		String videoTitleInDetailedPage = new TextLabel(properties.get("videos_title_in_detailedView")).getLabelText();
		return (videoTitle.equals(videoTitleInDetailedPage));
	}
	
	public boolean verifyTooltipIsDisplayed() throws Exception {
		for(int i = 1; i <= new TextLabel(properties.get("videos_thumbnails")).getXpathCount(); i++){
			if(new TextLabel(properties.get("videos_thumbnails")+ "["+ i + "]/a/img").getAttribute("alt").isEmpty())
				return false;
		}
		return true;
	}
	
	public boolean verifyVideosDisplayGroupNotDisplayed() throws Exception {
		if (new TextLabel(properties.get("videos_searchResults_xpath")).isPresent()) {
			return new TextLabel(properties.get("videos_label")).isPresent();
		}
		return !(new TextLabel(properties.get("videos_label")).isPresent());
	}
	
	public boolean verifyVideosCountIsDisplayed() throws NumberFormatException, Exception {
		if (Integer.parseInt(new TextLabel(properties.get("videos_viewAll"))
				.getLabelText().split(" ")[2]) > 3){
			return new TextLabel(properties.get("video_searchResults_xpath"))
				.getXpathCount() == 3;
		}
		return true;
	}

	public boolean viewAllIsDisplayed() throws Exception {
		if (new TextLabel(properties.get("videos_searchResults_xpath"))
				.getXpathCount() > 3) {
			return new TextLabel(properties.get("videos_viewAll")).isPresent();
		}
		return true;
	}
	
	public boolean verifyViewAllIsNotDisplayed() throws NumberFormatException,
			Exception {
		if (new TextLabel(properties.get("video_searchResults_xpath"))
				.getXpathCount() < 3) {
			return !new TextLabel(properties.get("videos_viewAll")).isPresent();
		}
		return new TextLabel(properties.get("videos_viewAll")).isPresent();
	}
	
	public boolean validateVideosWithOcean() throws InterruptedException, Exception{
		int totalCount = Integer.parseInt(getCount(properties.get("totalSearchResults")));
		Random randomGenerator = new Random();
		boolean validate = false;
		new DropDownBox(properties.get("sort_by_xpath")).select("Document Title");
		selenium.waitForPageToLoad("8000");
		List<String> contentFromDB = getDataFromDB(properties.get("relevance"), 
				properties.get("documentTitle"));
		for (int i = 0; i < 5; i++) {
			int randomNumber = randomGenerator.nextInt(40);
			if (!(randomNumber == 0 && randomNumber > totalCount)) {
				String linkText = new Link("//div[@class='pagination']/ul/li/descendant::a[@class='sort']").getLinkText();
				if (totalCount > 20 && randomNumber > 20) {
					if (Integer.parseInt(linkText) != ((randomNumber / 20) + 1)) {
						new Link("//div[@id='pagination']/ul/li/descendant::a[contains(text(),"
										+ ((randomNumber / 20) + 1) + ")]").click();
					}
					validate = new TextLabel(properties.get("viewAll_searchResults")+ "[" + (randomNumber % 20) + "]/td[2]/h3/a")
							.getLabelText().equals(contentFromDB.get(randomNumber - 1));
				} else {
					if (Integer.parseInt(linkText) != ((randomNumber / 20) + 1)) {
						new Link("//div[@id='pagination']/ul/li/descendant::a[contains(text(),"
										+ ((randomNumber / 20) + 1) + ")]").click();
					}
					validate = new TextLabel(properties.get("viewAll_searchResults")+ "[" + (randomNumber) + "]/td[2]/h3/a")
							.getLabelText().equals(contentFromDB.get(randomNumber - 1));
				}
			}
			if (!validate)
				break;
		}
		return validate;
	}
}
