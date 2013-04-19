package webPageContainers4Testing.dev;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Image;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewFullMediaListPortlet extends ViewSearchResultPortlet {

	private Image contentLevelImg;
	protected String contentLevelXpath ="view_all_content_level";
	protected DropDownBox sortBy= new DropDownBox(properties.get("viewAll_sort"));
	

	
	public ViewFullMediaListPortlet(String displayGroupName) throws Exception {
		super(displayGroupName, "view_all_document_title",
				displayGroupName+"_title_xpath");
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
		String altText = contentLevelImg.getAltText(contentLevelImg.getLocator()+ "/@alt");
		for (String contentLevel : contentLevelTypes) {
			if (altText.contains(contentLevel)) {
				return true;
			}
		}

		return false;
	}

	public boolean checkViewAllIsNavigatedCorrectly() throws Exception {
			return new TextLabel(properties.get( portletName +"Full_viewAll_page_xpath")).isPresent() ? true : false;
		}
	
	public boolean verifyDocumentFullListPage() throws Exception {
		int totalResultsTextDisplayedInPage = getCount("totalSearchResults");
		int countOfRecordsInPage = new TextLabel(properties.get("viewAll_searchResults")).getXpathCount();
		return (totalResultsTextDisplayedInPage > 20) ? (countOfRecordsInPage == 20) : (countOfRecordsInPage  == totalResultsTextDisplayedInPage);
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
		return Integer.parseInt(getCurrentResults()[0].trim()) == count - 20 ? true
				: false;
	}
	
	public boolean verifyLastLink() throws Exception {
		return new TextLabel(properties.get("last")).isPresent();
	}
	
	public boolean verifyLastLinkIsworking() throws Exception {
		int count = getCount("totalSearchResults");
		new Link(properties.get("last")).click();
		return Integer.parseInt(getCurrentResults()[1].trim().replace(",","")) == count ? true : false;
	}
	
	public boolean verifyFirstLink() throws Exception {
		return new TextLabel(properties.get("first")).isPresent();
	}
	
	public boolean verifyFirstLinkIsworking() throws Exception {
		new Link(properties.get("first")).click();
		int totalResultsTextDisplayedInPage = getCount("totalSearchResults");
		String[] currentResults = getCurrentResults();
		int firstResultOnPage= Integer.parseInt(currentResults[0].replace(",","").trim());
		int lastResultOnPage= Integer.parseInt(currentResults[1].replace(",","").trim());
		
		if(firstResultOnPage != 1) {
			return false;
		}
		
		if(totalResultsTextDisplayedInPage > 20 ) {
			return lastResultOnPage == 20; 
		} else 	{
			return lastResultOnPage == totalResultsTextDisplayedInPage;
		}
		
	}

	public boolean checkDocInfoTypeOfFirstItemIsDisplayed() throws Exception {
		
		return !(new TextLabel(properties.get("viewAll_docInfoType_xpath")).getLabelText().isEmpty());
	}
	
	public boolean checkDefaultSort() throws Exception{
		String defaultSortOrder = properties.get(portletName + "_default_sort");
		String sortOrderFromSelect  = sortBy.getSelectedOptionValue();

		return  defaultSortOrder.equalsIgnoreCase(sortOrderFromSelect);
	}
	
	public boolean checkSortChange()throws Exception{
		boolean sortChanged = true;
		
		int dropDownSize = sortBy.getSelectOptionsCount();
		
		try {
			int selectedIndex = Integer.parseInt(sortBy.getSelectedIndex());
			int newIndex = (selectedIndex == dropDownSize - 1) ? 0 : selectedIndex + 1;
			
			sortBy.setSelectedOptionValue("index="+newIndex);
		} catch (Exception e) {
			 sortChanged = false;
		}
		return sortChanged;
	}
	
	public boolean checkFirstPageDisplayText()throws Exception{
		String displayTextOfFirstPage = new TextLabel(properties.get("viewAll_first_page_display_xpath")).getLabelText();
		String expectedTextOfFirstPage = properties.get("viewAll_first_page_display_text");
		
		return displayTextOfFirstPage.contains(expectedTextOfFirstPage);
	}
}

