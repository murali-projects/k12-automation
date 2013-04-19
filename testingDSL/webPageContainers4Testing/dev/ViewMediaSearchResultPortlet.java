package webPageContainers4Testing.dev;

import webPageElements4Testing.Image;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewMediaSearchResultPortlet extends ViewSearchResultPortlet {

	
	public ViewMediaSearchResultPortlet(String portletName, String firstResultTitleXPath, String documentPageTitleXpath)
			throws Exception {
		super(portletName, firstResultTitleXPath, documentPageTitleXpath);
	}

	public boolean checkImagesLinkCount() throws Exception {
		int imagesLinksCount = new Link(properties.get(firstResultTitleXPath) + "/img").getLinkCount();
		return imagesLinksCount == 9;
	}
	
	public boolean checkVideosThumbnailImagesCount() throws Exception {
		int imagesLinksCount = new Link(properties.get(firstResultTitleXPath) + "/ul/li/a/img").getLinkCount();
		return imagesLinksCount == 3;
	}
	
	public boolean checkDocVideosTitleCount() throws Exception {
		int videoDocTitleCount = new Link(properties.get(firstResultTitleXPath) + "/ul/li/h3/a").getLinkCount();
		return videoDocTitleCount == 3;
	}

	public boolean checkDetailedViewOfFirstImageIsDisplayed() throws InterruptedException, Exception{
		String locator = properties.get(firstResultTitleXPath);
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();
		TextLabel documentTitle = new TextLabel(properties.get(documentPageTitleXpath));		
		String detailPagetitle = documentTitle.getLabelText();
		new Url().goBackToPreviousPage();
		
		Image documentImg = new Image(locator + "/img");
		String firstDocumentTitle = documentImg.getAltText(documentImg.getLocator()+ "/@alt");
		return detailPagetitle.equals(firstDocumentTitle);
	}
	
}