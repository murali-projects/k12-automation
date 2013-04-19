package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewVideoThumbnails extends BasePageContainer{

	public ViewVideoThumbnails() throws Exception {
		super();
	}
	
	public boolean verifyThumbnailsPresentFromViewAllPage() throws InterruptedException{
		new Link(properties.get("videos_viewAll")).click();
		for(int i=1; i<=new Link(properties.get("videos_thumbnails")).getLinkCount(); i++){
			if(! new TextLabel(properties.get("videos_thumbnails")+ "/a/img").getAttribute("alt").equals("thumbnail")){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyThumbnailsPresentAfterNavigation() throws InterruptedException{
		new Link(properties.get("reference_tab")).click();
		new Link(properties.get("video_tab")).click();
		for(int i=1; i<=new Link(properties.get("videos_thumbnails")).getLinkCount(); i++){
			if(! new TextLabel(properties.get("videos_thumbnails")+ "/a/img").getAttribute("alt").equals("thumbnail")){
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyThumbnailInDetailPageFromSearchResults() throws InterruptedException{
		new Link(properties.get("videos_searchResults_xpath")).click();
		return new TextLabel(properties.get("images_fullSizeImage_xpath")).isPresent();
	}
	
	public boolean verifyThumbnailInDetailPageFromViewAll() throws InterruptedException{
		new Link(properties.get("videos_thumbnails")+"/a").click();
		return new TextLabel(properties.get("images_fullSizeImage_xpath")).isPresent();
	}

}
