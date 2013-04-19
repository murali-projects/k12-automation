package webPageContainers4Testing.dev;

import webPageElements4Testing.Image;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewSearchResultPortlet extends BaseDevPageContainer {

	protected String portletName;
	protected Link portletDiv;
	protected Link viewAllLink;
	protected String url;
	protected String firstResultTitleXPath;
	protected String documentPageTitleXpath;
	protected String displayGroupName;

	protected String baseUrl = "";
	public ViewSearchResultPortlet(String portletName, String url, String portletLabel, String portletViewAll,
			String firstResultTitleXPath, String documentPageTitleXpath) throws Exception {
		super();
		this.portletName = portletName;
		this.portletDiv = new Link(properties.get(portletLabel));
		this.viewAllLink = new Link(properties.get(portletViewAll));
		this.url = url;
		this.firstResultTitleXPath = firstResultTitleXPath;
		this.documentPageTitleXpath = documentPageTitleXpath;
		setupBaseUrl();
	}

	public ViewSearchResultPortlet(String portletName, String firstResultTitleXPath, String documentPageTitleXpath)
			throws Exception {
		super();
		this.portletName = portletName;
		this.portletDiv = new Link(properties.get(portletName + "_label"));
		this.viewAllLink = new Link(properties.get(portletName + "_viewAll"));
		this.url = properties.get(portletName + "_viewAll_link");
		this.firstResultTitleXPath = firstResultTitleXPath;
		this.documentPageTitleXpath = documentPageTitleXpath;
		setupBaseUrl();
	}

	protected void setupBaseUrl(){
		Url url = new Url();
		baseUrl = url.getUrl();
	}
	
	public boolean checkDisplayGroupIsPresent() {
		if (portletDiv.isPresent())
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsPresent() {
		if ((viewAllLink.isPresent()) && (viewAllLink.getLinkText().contains("View All")))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		String newUrl = new Url().getUrl();
		new Url().goBackToPreviousPage();
		return (newUrl.contains(url));
	}

	public int getArticlesCount() throws Exception {
		String referenceArticlesLinkText = viewAllLink.getLinkText();
		String noOfReferenceArticlesLinks = referenceArticlesLinkText.substring(8);
		int count = Integer.parseInt(noOfReferenceArticlesLinks.trim().replace(",", ""));
		return count;
	}

	public boolean checkArticleLinkCount() throws Exception {
		int articleLinksCount = new Link(properties.get(firstResultTitleXPath)).getLinkCount();
		return articleLinksCount == 3;
	}

	public boolean checkImagesLinkCount() throws Exception {
		int imagesLinksCount = new Link(properties.get(firstResultTitleXPath) + "/img").getLinkCount();
		return imagesLinksCount == 9;
	}

	public boolean checkDetailedViewOfFirstItemIsDisplayed() throws InterruptedException, Exception {
		String locator = properties.get(firstResultTitleXPath) + "[1]/h3/a";
		// could be a truncated string
		String firstDocumentTitle = new TextLabel(locator).getLabelText();
		firstDocumentTitle = firstDocumentTitle.substring(0, firstDocumentTitle.length() - 1);
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();
		TextLabel documentTitle = new TextLabel(properties.get(documentPageTitleXpath));
		String detailPagetitle = documentTitle.getLabelText();
		new Url().goBackToPreviousPage();

		return detailPagetitle.startsWith(firstDocumentTitle);
	}

	public boolean checkSectionTitlesForCriticalThinkingDocumentOnly(String titleXPath, String expectedTitle)
			throws InterruptedException, Exception {
		TextLabel actualTitle = new TextLabel(properties.get(titleXPath));
		String actualTitleText = actualTitle.getLabelText();

		return (actualTitleText == null) ? false : expectedTitle.equals(actualTitleText);
	}

	public boolean checkForDivision(String titleXPath) throws InterruptedException, Exception {
		Link division = new Link(properties.get(titleXPath));
		return division.isPresent();
	}

	public void goToCriticalThinkingDocument() throws InterruptedException {
		String locator = properties.get(firstResultTitleXPath) + "[1]/h3/a";
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();
	}

	public void goToReferenceDocument() throws InterruptedException {
		String locator = properties.get(firstResultTitleXPath) + "[1]/h3/a";
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();
	}

	public boolean checkDetailedViewOfFirstImageIsDisplayed() throws InterruptedException, Exception {
		String locator = properties.get(firstResultTitleXPath);
		Link firstDocumentLink = new Link(locator);
		firstDocumentLink.click();
		TextLabel documentTitle = new TextLabel(properties.get(documentPageTitleXpath));
		String detailPagetitle = documentTitle.getLabelText();
		new Url().goBackToPreviousPage();

		Image documentImg = new Image(locator + "/img");
		String firstDocumentTitle = documentImg.getAltText(documentImg.getLocator() + "/@alt");
		return detailPagetitle.equals(firstDocumentTitle);
	}

	public void goToViewAllPage() throws InterruptedException {
		viewAllLink.click();
	}

	public void gotoDocumentDetailsPage(String documentXpath) throws InterruptedException {
		Link document = new Link(properties.get(documentXpath));
		document.click();
	}
	
	
	public int getViewAllCount() {
		String viewAllLinkText = viewAllLink.getLinkText();
		String viewAllLinkCount = viewAllLinkText.replace("View All ", "").trim().replaceAll(",", ""); 
		return Integer.parseInt(viewAllLinkCount);
	}

	public void openPage(String pageUrl) throws Exception {
		Url url = new Url();
		String pageUrlString = url.getUrl() + properties.get(pageUrl);
		url.goToPage(pageUrlString);
	}
	
	public boolean doesDocumentIdShowupInOrder(String docTableXpath, String docIdXpath, String docId, int order) throws Exception{
		String locator = properties.get(docTableXpath)+"["+order+"]";
		if(!docIdXpath.isEmpty()){
			locator+= properties.get(docIdXpath);
		}
		
		Link documentLink = new Link(locator);
		String s = documentLink.getLinkAttribute(locator+"/a/@href");
		return s.contains(docId);
		
	}
}