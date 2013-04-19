package webPageContainers4Testing.dev;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class PortalPortlet extends BaseDevPageContainer {

	protected String baseUrl = "";

	public PortalPortlet() throws Exception {
		super();
		Url url = new Url();
		baseUrl = url.getUrl();
	}

	public boolean checkPortalIsPresent() throws Exception {
		TextLabel actualTitle = new TextLabel(properties.get("portal_page_title_xpath"));
		String actualTitleText = actualTitle.getLabelText();

		return (actualTitleText == null) ? false : actualTitleText.contains("Portal");
	}

	public boolean checkPortalPageTitlePresent() throws Exception {
		TextLabel actualTitle = new TextLabel(properties.get("portal_breadcrumb_xpath"));
		int count = actualTitle.getXpathCount();
		TextLabel lastLabel = new TextLabel(properties.get("portal_breadcrumb_xpath") + "[" + count + "]");
		String actualTitleText = lastLabel.getLabelText();

		return (actualTitleText == null) ? false : actualTitleText.contains("Portal");

	}

	public void openPage(String displayGroupProperty) throws Exception {
		Url url = new Url();
		String detailsPageUrl = baseUrl + properties.get(displayGroupProperty);
		url.goToPage(detailsPageUrl);
	}

	public boolean linkExists(String linkUrl) throws InterruptedException {
		String locator = properties.get(linkUrl);
		Link firstDocumentLink = new Link(locator);
		return firstDocumentLink.isPresent();

	}

	public boolean linkIsWorking(String linkUrl) throws InterruptedException {
		String locator = properties.get(linkUrl);
		Link firstDocumentLink = new Link(locator);

		String title = firstDocumentLink.getLinkText();
		firstDocumentLink.click();

		TextLabel documentTitle = new TextLabel(properties.get("detailPage_doctitle_xpath"));
		String detailPagetitle = documentTitle.getLabelText();

		return detailPagetitle.startsWith(title);
	}
}
