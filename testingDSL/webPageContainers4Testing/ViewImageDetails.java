package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewImageDetails extends BasePageContainer{

	Link viewAll;
	Link nextLink;
	Link previousLink;
	int noOfResults; 
	
	public ViewImageDetails() throws Exception {
		super();
		viewAll = new Link(properties.get("images_viewAll"));
		nextLink = new Link(properties.get("images_nextLink"));
		previousLink = new Link(properties.get("images_previousLink"));
	}
	
	public boolean verifyGalleryViewIsDisplayed() throws Exception{
		return new TextLabel(properties.get("images_searchResults_xpath")).isPresent();
	}
	
	public boolean verifyDetailedViewOfImageIsDisplayed() throws Exception {
		noOfResults = Integer.parseInt(viewAll.getLinkText().split(" ")[2].replace(",", ""));
		String imageTitle = new TextLabel(properties.get("images_searchResults_xpath")+"/img").getAttribute("title");
		new Link(properties.get("images_searchResults_xpath")).click();
		return (new TextLabel(properties.get("images_title_in_detailedView")).getLabelText().equals(imageTitle));
	}
	
	public boolean checkSearchButtonIsPresent() throws Exception{
		if(new TextBox(properties.get("searchBox")).isPresent() &&
				new PageButton(properties.get("findButton")).isPresent()){
			return true;
		}
		return false;
	}
	
	public boolean verifyElementsAreDisplayedOnPage() throws Exception{
		TextLabel image = new TextLabel(properties.get("images_fullSizeImage_xpath"));
		if (//image.getAttribute("height").equals("288")
				//&& image.getAttribute("width").equals("460")
				 new TextLabel(properties.get("source_citation")).isPresent()
//				&& new TextLabel(properties.get("images_copyright")).isPresent()
				&& new TextLabel(properties.get("gale_document")).isPresent()
				&& new TextLabel(properties.get("images_title_in_detailedView")).isPresent()){
			return true;
		}
		return false;
	}
	
	public boolean verifyToolsArePresent() throws Exception{
		if(new TextLabel(properties.get("images_print")).isPresent() &&
				new TextLabel(properties.get("images_email")).isPresent() &&
				new TextLabel(properties.get("images_share")).isPresent() &&
				new TextLabel(properties.get("images_download")).isPresent()){
			return true;
		}
		return false;
	}

	public boolean ensureMarkOptionIsAvailable() throws Exception{
		new Url().goBackToPreviousPage();
		viewAll.click();
		for(int i = 1; i <= new TextLabel(properties.get("images_thumbnails")).getXpathCount(); i++){
			if(! new Link(properties.get("images_thumbnails")+ "["+ i + "]"+ properties.get("images_markMe")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean checkPictureIsDisplayedOnLeftSide() throws Exception{
		return new TextLabel(properties.get("images_fullSizeImage_xpath")).isPresent();
	}
	
	private int getCurrentResultsCount() throws Exception {
		return Integer.parseInt(new TextLabel("//span[@class='total_results']").getLabelText());
	}
	
	public boolean verifyNextAndPreviousButtonsPresent() throws Exception{
		if(getCurrentResultsCount() > 2){
			new Link(properties.get("images_thumbnails")+"[2]/a").click();
			Thread.sleep(2000);
			if(!(nextLink.isPresent() && previousLink.isPresent())){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyTooltipIsDisplayed() throws InterruptedException, Exception{
		for(int i = 1; i <= new TextLabel(properties.get("images_thumbnails")).getXpathCount(); i++){
			if(new TextLabel(properties.get("images_thumbnails")+ "["+ i + "]/a/img").getAttribute("alt").isEmpty())
				return false;
		}
		return true;
	}
	
	public boolean verifyNavigationOfPreviousNextButtons() throws InterruptedException, Exception{
		return verifyNextLinkIsworking() && verifyPreviousLinkIsworking();
	}
	
	public boolean verifyNextLinkIsworking() throws Exception {
		String nextImage = new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_nextLink")).isPresent()){
			new PageButton(properties.get("images_nextLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title")).equals(nextImage);
		}
		return true;
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		String previousImage = new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_previousLink")).isPresent()){
			new PageButton(properties.get("images_previousLink")).click();
			Thread.sleep(1000);
			return !(new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title")).equals(previousImage);
		}
		return true;
	}
	
	public boolean verifyDateIsDisplayed(){
		//TODO:Not yet implemented in the application
		return false;
	}
	
	public boolean verifyGlossaryDefnIsAvailable() throws InterruptedException, Exception{
		//TODO:Not yet implemented in the application
		new Url().mouseOver("");
		new Url().focus("");
		return new TextLabel("").getLabelText() != null;
	}
	
	public boolean verifyAfterMarkingImage() throws InterruptedException, Exception{
		Link markLink = new Link(properties.get("images_markMe"));
		markLink.clickWithoutWait();
		return new Link(properties.get("images_marked")).getLinkText().equals("Marked");
	}
}
