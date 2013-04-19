package webPageContainers4Testing.dev;

import webPageElements4Testing.Link;

public class VideosPortlet extends BaseDevPageContainer {
	
	private Link viewAllLink = new Link(properties.get("page_sr.portlet_videos.viewAll.link.xpath"));

	public VideosPortlet() throws Exception {
		super();
	}

	public Link getVideoPortletDiv() {
		return new Link(properties.get("page_sr.portlet_videos.div.xpath"));
	}

	public Link getVideosThumbnailImagesLink() {
		return new Link(properties.get("page_sr.portlet_videos.thumbnail.img.xpath"));
	}

	public Link getVideosDocTileLink() {
		return new Link(properties.get("page_sr.portlet_videos.doctitle.link.xpath"));
	}

	public Link getViewAllLink() {
		return new Link(properties.get("page_sr.portlet_videos.viewAll.link.xpath"));
	}

	public void navigateToViewAll() throws InterruptedException {
		getViewAllLink().click();
	}
	
	public int getViewAllCount() {
		String viewAllLinkText = viewAllLink.getLinkText();
		String viewAllLinkCount = viewAllLinkText.replace("View All ", "").trim().replaceAll(",", ""); 
		return Integer.parseInt(viewAllLinkCount);
	}

}
