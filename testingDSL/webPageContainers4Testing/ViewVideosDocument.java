package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewVideosDocument extends BasePageContainer{

	public ViewVideosDocument() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public boolean clickDocumentVideoDisplayGroup() throws Exception {
		
		new Link(properties.get("video_link")).click();
        return true;  
	}
	
	public boolean verifyDocumentTitleInDetailedPage() throws Exception{
		new Link(properties.get("video_link")).click();
		//String title= new TextLabel(properties.get("video_link")).getLabelText();
		if(new PageElementWithIdAttribute(properties.get("video_title")).isPresent())
		return true;
		return false;
	}
	
	public boolean verifyVideoPlayedWithoutErrors() throws Exception{
		new Link(properties.get("video_link")).click();
		if(new PageElementWithIdAttribute(properties.get("video_player")).isPresent())
			return true;
			return false;
	}
	
	public boolean verifyVideoCaptionAvailable() throws Exception{
		new Link(properties.get("video_link")).click();
		if(new PageElementWithIdAttribute(properties.get("video_caption")).isPresent())
			return true;
			return false;
	}
	
	public boolean verifyVideoFromViewAll() throws Exception{
		new Link(properties.get("video_viewall")).click();
		new Link(properties.get("video_viewall_link")).click();
		if(new PageElementWithIdAttribute(properties.get("video_player")).isPresent())
			return true;
			return false;
	}
}
