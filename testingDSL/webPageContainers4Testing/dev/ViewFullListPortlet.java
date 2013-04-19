package webPageContainers4Testing.dev;


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Image;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewFullListPortlet extends ViewSearchResultPortlet {

	private Image contentLevelImg;
	protected String contentLevelXpath = "view_all_content_level";
	protected DropDownBox sortBy = new DropDownBox(properties.get("viewAll_sort"));
	
	private TextBox searchWithinBox = new TextBox(properties.get("page_viewAll.searchwithin.textbox"));
    private PageButton basicSearchButton = new PageButton(properties.get("page_viewAll.searchwithinSubmit.button"));
    private Link contentDivision = new Link(properties.get("page_viewAll.content.division"));
    private Link firstPublicationTitleLimiterLink = new Link(properties.get("browse_index_title_links"));
    private Link firstDocTypeLimiterLink = new Link(properties.get("browse_index_doctype_links"));

	
	public ViewFullListPortlet(String displayGroupName) throws Exception {
		super(displayGroupName, "view_all_document_title", displayGroupName + "_title_xpath");
		this.contentLevelImg = new Image(properties.get("view_all_content_level"));
	}
	
	@Override
	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		String newUrl = new Url().getUrl();
		return (newUrl.contains(url));
	}

	public boolean verifyContentLevelIndicatorIsDisplayed() throws Exception {
		return contentLevelImg.isPresent();
	}

	public boolean verifyContentLevelIndicatorHasAltText() throws Exception {
		String[] contentLevelTypes = { "Intermediate", "Basic", "Advanced" };
		String altText = contentLevelImg.getAltText(contentLevelImg.getLocator() + "/@alt");
		for (String contentLevel : contentLevelTypes) {
			if (altText.contains(contentLevel)) {
				return true;
			}
		}

		return false;
	}

	public boolean checkViewAllIsNavigatedCorrectly() throws Exception {
		return new TextLabel(properties.get(portletName + "Full_viewAll_page_xpath")).isPresent() ? true : false;
	}

	public boolean verifyDocumentFullListPage() throws Exception {
		int totalResultsTextDisplayedInPage = getTotalSearchResultsCount();
		int countOfRecordsInPage = new TextLabel(properties.get("viewAll_searchResults")).getXpathCount();
		return (totalResultsTextDisplayedInPage > 20) ? (countOfRecordsInPage == 20)
				: (countOfRecordsInPage == totalResultsTextDisplayedInPage);
	}
	
	public int getTotalSearchResultsCount() throws Exception {
		return getCount("totalSearchResults");
	}

	private int getCount(String locator) throws Exception {

		String displayGroup = new TextLabel(properties.get(locator)).getLabelText().trim();
		return Integer.parseInt(displayGroup.replaceAll(",", ""));
	}

	public boolean verifyNextLink() throws Exception {
		if (getCount("totalSearchResults") > 20) {
			return new TextLabel(properties.get("next")).isPresent();
		}
		return true;
	}

	public boolean verifyNextLinkIsworking() throws Exception {
		new Link(properties.get("next")).click();
		return Integer.parseInt(getCurrentResults()[0].trim()) > 20 ? true : false;
	}

	private String[] getCurrentResults() throws Exception {
		return new TextLabel(properties.get("currentResults")).getLabelText().split("-");
	}

	public boolean verifyPreviousLink() throws Exception {
		return new TextLabel(properties.get("previous")).isPresent();
	}

	public boolean verifyPreviousLinkIsworking() throws Exception {
		int count = Integer.parseInt(getCurrentResults()[0].trim());
		new Link(properties.get("previous")).click();
		return Integer.parseInt(getCurrentResults()[0].trim()) == count - 20 ? true : false;
	}

	public boolean verifyLastLink() throws Exception {
		return new TextLabel(properties.get("last")).isPresent();
	}

	public boolean verifyLastLinkIsworking() throws Exception {
		int count = getCount("totalSearchResults");
		new Link(properties.get("last")).click();
		return Integer.parseInt(getCurrentResults()[1].trim().replace(",", "")) == count ? true : false;
	}

	public boolean verifyFirstLink() throws Exception {
		return new TextLabel(properties.get("first")).isPresent();
	}

	public boolean verifyFirstLinkIsworking() throws Exception {
		new Link(properties.get("first")).click();
		int totalResultsTextDisplayedInPage = getCount("totalSearchResults");
		String[] currentResults = getCurrentResults();
		int firstResultOnPage = Integer.parseInt(currentResults[0].replace(",", "").trim());
		int lastResultOnPage = Integer.parseInt(currentResults[1].replace(",", "").trim());

		if (firstResultOnPage != 1) {
			return false;
		}

		if (totalResultsTextDisplayedInPage > 20) {
			return lastResultOnPage == 20;
		} else {
			return lastResultOnPage == totalResultsTextDisplayedInPage;
		}

	}

	public boolean checkDocInfoTypeOfFirstItemIsDisplayed() throws Exception {

		return !(new TextLabel(properties.get("viewAll_docInfoType_xpath")).getLabelText().isEmpty());
	}

	public boolean checkDefaultSort() throws Exception {
		String defaultSortOrder = properties.get(portletName + "_default_sort");
		String sortOrderFromSelect = sortBy.getSelectedOptionValue();

		return defaultSortOrder.equalsIgnoreCase(sortOrderFromSelect);
	}

	public boolean checkSortChange() throws Exception {
		boolean sortChanged = true;

		int dropDownSize = sortBy.getSelectOptionsCount();

		try {
			int selectedIndex = Integer.parseInt(sortBy.getSelectedIndex());
			int newIndex = (selectedIndex == dropDownSize - 1) ? 0 : selectedIndex + 1;

			sortBy.setSelectedOptionValue("index=" + newIndex);
		} catch (Exception e) {
			sortChanged = false;
		}
		return sortChanged;
	}
	
	public boolean checkSortChange(int sortIndex) throws Exception {
		boolean sortChanged = true;

		int dropDownSize = sortBy.getSelectOptionsCount();

		try {
			sortBy.setSelectedOptionValue("index=" + sortIndex);
		} catch (Exception e) {
			sortChanged = false;
		}
		return sortChanged;
	}
	

	public boolean checkFirstPageDisplayText() throws Exception {
		String displayTextOfFirstPage = new TextLabel(properties.get("viewAll_first_page_display_xpath"))
				.getLabelText();
		String expectedTextOfFirstPage = properties.get("viewAll_first_page_display_text");

		return displayTextOfFirstPage.contains(expectedTextOfFirstPage);
	}

	public boolean checkForContentGroupDisplay() throws Exception {
		String displayTextOfFirstContentGroup = new TextLabel(properties.get("viewAll_content_group_display_xpath"))
				.getLabelText();
		return displayTextOfFirstContentGroup.isEmpty() ? false : true;
	}

	public boolean checkForViewAllPageName(String pageName) throws Exception {
		String expectedPageName = properties.get(pageName);
		String acutalPageName = new TextLabel(properties.get("viewAll_page_name")).getLabelText();
		return expectedPageName.equals(acutalPageName);
	}

	public boolean checkForWorkingOfContentGroupSelector(String pageName) throws Exception {

		String displayTextOfFirstContentGroup = new TextLabel(properties.get("viewAll_content_group_display_xpath"))
				.getLabelText();
		String displayGroupSelected = properties.get(pageName);
		String[] contentGroupWithHits = removeNonWordCharacters(displayTextOfFirstContentGroup).split("\\(");
		List<String> contentGroups = new ArrayList<String>(1);
		for (String contentGroup : contentGroupWithHits) {
			if (!contentGroup.trim().startsWith("(")) {
				contentGroups.add(contentGroup.trim());
			}
		}
		int index = contentGroups.indexOf(displayGroupSelected);
		int indexToBeSelected = index + 1 == contentGroups.size() ? index - 2 : index + 1;

		String linkToBeSelected = MessageFormat.format(properties.get("display_group_to_be_selected"),
				new Object[] { indexToBeSelected + 1 });

		Link contentGroupToBeSelected = new Link(linkToBeSelected);
		// TODO - remove this once academic journals works
		if (contentGroupToBeSelected.getLinkText().contains("Academic")) {
			index = contentGroups.indexOf(displayGroupSelected);
			indexToBeSelected = index + 2 == contentGroups.size() ? index - 2 : index + 2;

			linkToBeSelected = MessageFormat.format(properties.get("display_group_to_be_selected"),
					new Object[] { indexToBeSelected + 1 });

			contentGroupToBeSelected = new Link(linkToBeSelected);

		}
		contentGroupToBeSelected.click();
		return checkForViewAllPageName(pageName) ? false : true;
	}

	public int checkContentGroupLinkAndCount() throws Exception {

		String displayTextOfFirstContentGroup = new TextLabel(properties.get("viewAll_content_group_display_xpath"))
				.getLabelText();

		String[] contentGroupWithHits = displayTextOfFirstContentGroup.split("\\) ");

		int count = splitAndCount(contentGroupWithHits[0]);

		return count;
	}

	public void clickFirstNavBarLink() throws InterruptedException {
		String linkToBeSelected = MessageFormat.format(properties.get("display_group_to_be_selected"),
				new Object[] { 1 });

		Link contentGroupToBeSelected = new Link(linkToBeSelected);

		contentGroupToBeSelected.click();
	}

	private String removeNonWordCharacters(String text) {

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (Character.isDigit(c) || c == ')' || c == ',') {

			} else {
				result.append(c);
			}
		}

		return result.toString();
	}

	public boolean checkForBrowseIndexLabel(String expectedLabel, String browseIndexLabel) {
		String documentTypes = new TextLabel(properties.get(browseIndexLabel)).getLabelText();
		return expectedLabel.equalsIgnoreCase(documentTypes);
	}

	public boolean checkForBrowseIndexLinksExist(String browseIndexLinks) {
		int linkCount = new Link(properties.get(browseIndexLinks)).getLinkCount();
		return (linkCount >= 1);
	}

	public boolean verifyDocumentFavIconsAreDisplayed() {
		return (new Image(properties.get("viewall_results.favicon.xpath")).isPresent());
	}

	public boolean verifyCorrectDocumentFavIconAltTextIsDisplayed() {
		String altTextExpected = properties.get("viewall_results.favicon.expectedAltText");
		Image favIconImage = new Image(properties.get("viewall_results.favicon.xpath"));
		return altTextExpected.equalsIgnoreCase(favIconImage.getAltText());
	}
	
	public void submitAnSearchWithin(String searchText) {
		searchWithinBox.type(searchText);
		basicSearchButton.clickAndWait();
	}
	
	public String getContentDivisionText(){
		return contentDivision.getLinkText();
	}
	
	public boolean verifySearchWithinResultsCount(String searchText) throws Exception{
		int resultCountBefore = getTotalSearchResultsCount();
		submitAnSearchWithin(searchText);
		int resultCountAfter = getTotalSearchResultsCount();
		return resultCountAfter < resultCountBefore;
	}
	
	public boolean verifySearchWithinResultsForNoResultsText(String searchText){
		submitAnSearchWithin(searchText);
		String textNoResults = getContentDivisionText();		
		return textNoResults.contains("No Results matching your search term(s) were found");				
	}
	
	public boolean verifyNavigationTabCount() throws Exception{
		int beforeNavigationTabCount = checkContentGroupLinkAndCount();
		clickFirstNavBarLink();
		int afterNavigationTabCount = checkContentGroupLinkAndCount();
		return beforeNavigationTabCount == afterNavigationTabCount;		
	}
	
	
	private int getLinkCount(Link linkWithCount){
		String linktText = linkWithCount.getLinkText();
		return splitAndCount(linktText);
	}
	
	private int splitAndCount(String text){
		String[] textAndCount = text.split(" \\(");
		int count = Integer.valueOf(textAndCount[1].split("\\)")[0].replace(",", ""));		
		return count;
	}
	
	
	public boolean verifyLimiterAppliedForFirstPublicationTitleLink() throws Exception {
		int totalCountBeforeApplyingLimiter = getTotalSearchResultsCount();
		firstPublicationTitleLimiterLink.click();
		int publicationTitleCount = getLinkCount(firstPublicationTitleLimiterLink);
		int actualCount = getTotalSearchResultsCount();
		return (actualCount < totalCountBeforeApplyingLimiter && 
				actualCount == publicationTitleCount);
	}
	
	public boolean verifyLimiterAppliedForFirstDocumentTypeLink() throws Exception {
		int totalCountBeforeApplyingLimiter = getTotalSearchResultsCount();
		firstDocTypeLimiterLink.click();
		int publicationTitleCount = getLinkCount(firstDocTypeLimiterLink);
		int actualCount = getTotalSearchResultsCount();
		return (actualCount < totalCountBeforeApplyingLimiter && 
				actualCount == publicationTitleCount);		
	}
	
	
	
}
