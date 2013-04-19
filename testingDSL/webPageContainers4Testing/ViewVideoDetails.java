package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewVideoDetails extends BasePageContainer{

	Link viewAll;
	Link nextLink;
	Link previousLink;
	
	public ViewVideoDetails() throws Exception {
		super();
		viewAll = new Link(properties.get("videos_viewAll"));
		nextLink = new Link(properties.get("images_nextLink"));
		previousLink = new Link(properties.get("images_previousLink"));
	}
	
	public boolean verifyDetailedViewOfVideoIsDisplayed() throws Exception {
		String videoTitle = new TextLabel(properties.get("videos_searchResults_xpath")).getLabelText();
		new Link(properties.get("videos_searchResults_xpath")).click();
		return (new TextLabel(properties.get("videos_title_in_detailedView")).getLabelText().contains(videoTitle.substring(0, videoTitle.length()-1)));
	}
	
	public boolean checkSearchButtonIsPresent() throws Exception{
		if(new TextBox(properties.get("searchBox")).isPresent() &&
				new PageButton(properties.get("findButton")).isPresent()){
			return true;
		}
		return false;
	}
	
	public boolean verifyElementsAreDisplayedOnPage() throws Exception{
		TextLabel video = new TextLabel(properties.get("videos_fullSizeVideo_xpath"));
		if (video.getAttribute("height").equals("288")
				&& video.getAttribute("width").equals("460")
				&& new TextLabel(properties.get("videos_source")).isPresent()
				&& new TextLabel(properties.get("videos_copyright")).isPresent()
				&& new TextLabel(properties.get("videos_gale_documentNo")).isPresent()
				&& new TextLabel(properties.get("videos_title_in_detailedView")).isPresent()){
			return true;
		}
		return false;
	}
	
	public boolean verifyToolsArePresent() throws Exception{
		if(new TextLabel(properties.get("videos_print")).isPresent() &&
				new TextLabel(properties.get("videos_email")).isPresent() &&
				new TextLabel(properties.get("videos_share")).isPresent() &&
				new TextLabel(properties.get("videos_download")).isPresent()){
			return true;
		}
		return false;
	}

	public boolean ensureMarkOptionIsAvailable() throws Exception{
		viewAll.click();
		for(int i = 1; i <= new TextLabel(properties.get("images_thumbnails")).getXpathCount(); i++){
			if(! new Link(properties.get("images_thumbnails")+ "["+ i + "]"+ properties.get("images_markMe")).isPresent())
				return false;
		}
		return true;
	}
	
	public boolean checkVideoIsDisplayedOnLeftSide() throws Exception{
		return new TextLabel(properties.get("video_xpath")).isPresent();
	}
	
	public boolean verifyNextAndPreviousButtonsPresent() throws Exception{
			Thread.sleep(2000);
			if(!(nextLink.isPresent() && previousLink.isPresent())){
				return false;
		}
		return true;
	}
	
	public boolean verifyTooltipIsDisplayed() throws InterruptedException, Exception{
		//TODO:Not yet implemented in the application
		new Link(properties.get("")).click();
		new Url().mouseOver("");
		new Url().focus("");
		return new TextLabel("").getLabelText() != null;
	}
	
	public boolean verifyNavigationOfPreviousNextButtons() throws InterruptedException, Exception{
		return verifyNextLinkIsworking() && verifyPreviousLinkIsworking();
	}
	
	public boolean verifyNextLinkIsworking() throws Exception {
		String nextImage = new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_nextLink")).isPresent()){
			new PageButton(properties.get("images_nextLink")).click();
			Thread.sleep(5000);
			return !(new TextLabel(properties.get("images_nextImage_title")+ "/img").getAttribute("title")).equals(nextImage);
		}
		return true;
	}
	
	public boolean verifyPreviousLinkIsworking() throws Exception {
		String previousImage = new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title");
		if(new PageButton(properties.get("images_previousLink")).isPresent()){
			new PageButton(properties.get("images_previousLink")).click();
			Thread.sleep(5000);
			return !(new TextLabel(properties.get("images_previousImage_title")+ "/img").getAttribute("title")).equals(previousImage);
		}
		return true;
	}
	
	public boolean verifyGalleryViewIsDisplayed() throws Exception{
		viewAll.click();
		return new TextLabel(properties.get("videos_thumbnails")).isPresent();
	}
	
	public boolean verifyDateIsDisplayed(){
		//TODO:Not yet implemented in the application
		return true;
	}
	
	public boolean verifyGlossaryDefnIsAvailable() throws InterruptedException, Exception{
		//TODO:Not yet implemented in the application
		new Url().mouseOver("");
		new Url().focus("");
		return new TextLabel("").getLabelText() != null;
	}
	
	public boolean verifyAfterMarkingVideo() throws InterruptedException, Exception{
		Link markLink = new Link(properties.get("videos_markMe"));
		markLink.clickWithoutWait();
		return new Link(properties.get("videos_marked")).getLinkText().equals("Marked");
	}
	
	public boolean verifyVideoIsDispayed(){
		return new TextLabel(properties.get("video_xpath")).isPresent();
	}
}

