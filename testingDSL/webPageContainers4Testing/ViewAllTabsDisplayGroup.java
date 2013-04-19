package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllTabsDisplayGroup extends BasePageContainer {

	private Link tab;
	private Link next;
	private Link previous;
	private TextLabel displayGroupLabels;
	private TextLabel results;
	public HashMap<String, String> tabMap;
	private HashMap<String, String> allGroupNamesMap;
	private List<String> displayedGroups;
	private List<String> displayedGroupsTextValue;
	private List<String> displayedTabs;
	private List<String> displayedTabsTextValue;

	public ViewAllTabsDisplayGroup() throws Exception {
		super();
		displayGroupLabels = new TextLabel(properties
				.get("viewAll_searchResults"));
		results = new TextLabel(properties.get("currentResults"));
		next = new Link(properties.get("next"));
		previous = new Link(properties.get("previous"));
		loadTabMap();
		loadAllGroupMap();
	}

	public void clickViewAll(String displayGroup) {
		new Link(properties.get(displayGroup + "_viewAll"));
	}

	public void loadTabMap() {
		tabMap = new HashMap<String, String>();
		tabMap.put("referenceArticlesTab", properties.get("reference_tab"));
		tabMap.put("newsTab", properties.get("news_tab"));
		tabMap.put("magazinesTab", properties.get("magazines_tab"));
		tabMap.put("academicJournalsTab", properties
				.get("academicJournals_tab"));
		tabMap.put("imagesTab", properties.get("images_tab"));
		tabMap.put("audioTab", properties.get("audio_tab"));
		tabMap.put("videoTab", properties.get("video_tab"));
		tabMap.put("primarySourceTab", properties.get("primarysources_tab"));
		tabMap.put("websiteTab", properties.get("websites_tab"));
		tabMap.put("statisticsTab", properties.get("statistics_tab"));
		tabMap.put("viewPointsTab", properties.get("viewpoints_tab"));
	}

	public void loadAllGroupMap() {
		allGroupNamesMap = new HashMap<String, String>();
		allGroupNamesMap.put(properties.get("reference_label"), properties
				.get("reference_viewAll"));
		allGroupNamesMap.put(properties.get("news_label"), properties
				.get("news_viewAll"));
		allGroupNamesMap.put(properties.get("magazines_label"), properties
				.get("magazines_viewAll"));
		allGroupNamesMap.put(properties.get("academicJournals_label"),
				properties.get("academicJournals_viewAll"));
		allGroupNamesMap.put(properties.get("images_label"), properties
				.get("images_viewAll"));
		allGroupNamesMap.put(properties.get("audio_label"), properties
				.get("audio_viewAll"));
		allGroupNamesMap.put(properties.get("video_label"), properties
				.get("video_viewAll"));
		allGroupNamesMap.put(properties.get("primarysources_label"), properties
				.get("primarysources_viewAll"));
		allGroupNamesMap.put(properties.get("viewpoints_label"), properties
				.get("viewpoints_viewAll"));
	}

	public boolean validateContentForDisplayGroupWithOcean(String groupContent,
			String groupType, String contentGroup, String sortBy,
			String dataBaseElement, String searchTerm) throws Exception {
		if (new Link(properties.get(groupType + "_tab")).isPresent())
			navigateTab(properties.get(groupType + "_tab"), groupContent);
		return verifyContentWithDatabase(getAllGroupDocumentTitles(properties
				.get(sortBy)), contentGroup, sortBy, dataBaseElement,
				searchTerm);
	}

	public boolean verifyDisplayGroupCount(String groupType,
			String contentGroup, String sortBy, String searchTerm)
			throws Exception {
		String displayCount = getCount(properties.get(groupType + "_tab"));
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("all"), properties.get(contentGroup), properties
						.get(sortBy), searchTerm);
		String refArticlesCountFromDatabase = (list != null && !list.isEmpty()) ? list
				.get(0)
				: "0";
		return (displayCount.equals(refArticlesCountFromDatabase));
	}

	public List<String> getAllGroupDocumentTitles(String sortBy)
			throws Exception {
		List<String> displayGroupIdList = new ArrayList<String>();
		String displayGroup;
		selectSortElement(sortBy);
		Thread.sleep(10000);
		int groupDisplayCount = displayGroupLabels.getXpathCount();
		for (int i = 1; i <= groupDisplayCount; i++) {
			displayGroup = displayGroupLabels.getLabelText(properties
					.get("viewAll_searchResults")
					+ "[" + i + "]/td[2]/h3/a");
			displayGroupIdList.add(displayGroup);
		}
		return displayGroupIdList;
	}

	private boolean verifyContentWithDatabase(List<String> contentList,
			String contentGroup, String sortBy, String dataBaseElement,
			String searchTerm) throws IOException {
		List<String> dataBaseContentList = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties
						.get(contentGroup), properties.get(sortBy), properties
						.get(dataBaseElement), searchTerm);
		for (int i = 0; i < contentList.size(); i++) {
			
			if (!dataBaseContentList.get(i).contains(contentList.get(i).substring(0, contentList.get(i).length()-1)))
				return false;
		}
		return true;
	}

	public boolean verifyNextLinkForDisplayGroup(String groupType)
			throws Exception {
		String count = getCount(properties.get(groupType + "_tab"));
		if (Integer.parseInt(count) > 20 && next.isPresent()) {
			next.click();
			if (groupType.equals("images") || groupType.equals("videos")) {
				return (Integer.parseInt(results.getLabelText().split("-")[0]
						.trim())) > 15;
			} else {
				return (Integer.parseInt(results.getLabelText().split("-")[0]
						.trim())) > 20;
			}
		}
		return true;
	}

	public boolean verifyPreviousLinkForDisplayGroup()
			throws InterruptedException {
		if (Integer.parseInt(results.getLabelText().split("-")[0].trim()) > 15
				&& previous.isPresent()) {
			previous.click();
			return (Integer.parseInt(results.getLabelText().split("-")[0]
					.trim())) < 20;
		}
		return true;
	}

	public void selectSortElement(String option) throws Exception {
		new DropDownBox(properties.get("sort_by_xpath")).select(option);
	}

	private String getCount(String locator) throws Exception {
		String displayGroup = new TextLabel(locator).getLabelText();
		String displayGroupCountValue = getSubString(displayGroup, "(", ")")
				.replace(",", "");
		return displayGroupCountValue;
	}

	private String getSubString(String source, String startIndex,
			String endIndex) {
		return source.substring(source.indexOf(startIndex) + 1, source
				.indexOf(endIndex));
	}

	public void navigateTab(String tabName, String groupContent)
			throws Exception {
		if (!new TextLabel(
				"//div[@id='displayGroup_tabs']/ul/descendant::a[@class='current' and contains(text(),'"
						+ groupContent + "')]").isPresent()) {
			tab = new Link(tabName);
			tab.click();
		}
	}

	public List<String> displayedGroupsForSearchTerm() throws Exception {
		displayedGroups = new ArrayList<String>();
		displayedGroupsTextValue = new ArrayList<String>();
		for (String groupName : allGroupNamesMap.keySet()) {
			if (new TextLabel(groupName).isPresent()) {
				displayedGroups.add(groupName);
				displayedGroupsTextValue.add(new TextLabel(groupName)
						.getLabelText());
			}
		}
		clickViewAll();
		return displayedGroupsTextValue;
	}

	public boolean clickViewAll() throws Exception {
		if (displayedGroups != null && !(displayedGroups.size() == 0)) {
			for (int i = 0; i < displayedGroups.size(); i++) {
				if (new Link(allGroupNamesMap.get(displayedGroups.get(i)))
						.isPresent()) {
					new Link(allGroupNamesMap.get(displayedGroups.get(i)))
							.click();
					break;
				}
			}
			return true;
		}
		return false;
	}

	public boolean validateSearchContent() throws Exception {
		boolean displayedContentIsEqual = false;
		List<String> displayedGroupsForSearchTermList = displayedGroupsForSearchTerm();
		List<String> displayedGroupTabsList = displayedGroupTabs();
		for (int i = 0; i < displayedGroupsForSearchTermList.size(); i++) {
			for (int y = 0; y < displayedGroupTabsList.size(); y++) {
				displayedContentIsEqual = displayedGroupTabsList.get(y)
						.toString().contains(
								displayedGroupsForSearchTermList.get(i)
										.toString());
				{
					if (displayedContentIsEqual == true)
						break;
				}
			}
		}
		return displayedContentIsEqual;
	}

	private List<String> displayedGroupTabs() throws Exception {
		displayedTabs = new ArrayList<String>();
		displayedTabsTextValue = new ArrayList<String>();
		for (String tabName : tabMap.values()) {
			if (new TextLabel(tabName).isPresent()) {
				displayedTabs.add(tabName);
				displayedTabsTextValue.add(new TextLabel(tabName)
						.getLabelText());
			}
		}
		return displayedTabsTextValue;
	}

	public boolean verifyTabsAfterNext(String groupType) throws Exception {
		String count = getCount(properties.get(groupType + "_tab"));
		if (Integer.parseInt(count) > 20 && next.isPresent()) {
			next.click();
			for (int i = 1; i < new TextLabel(properties.get("tabs_count"))
					.getXpathCount(); i++) {
				if (!new TextLabel(properties.get("tabs_count") + "[" + i
						+ "]/h3/a").isPresent())
					return false;
			}
		}
		return true;
	}

	public boolean verifyTabsAfterPrevious(String groupType) throws Exception {
		if (Integer.parseInt(results.getLabelText().split("-")[0].trim()) > 20
				&& previous.isPresent()) {
			previous.click();
			for (int i = 1; i < new TextLabel(properties.get("tabs_count"))
					.getXpathCount(); i++) {
				if (!new TextLabel(properties.get("tabs_count") + "[" + i
						+ "]/h3/a").isPresent())
					return false;
			}
		}
		return true;
	}

	public boolean verifySearchInViewAll() {
		if (new TextLabel(properties.get("searchBox")).isPresent())
			return true;
		return false;
	}

	public boolean verifyTabsFromDetailedPage(String displayGroup)
			throws InterruptedException {
		if (displayGroup.equals("images") || displayGroup.equals("video"))
			new Link(properties.get("thumbnail")).click();
		else
			new Link(properties.get("viewAll_searchResults") + "[1]/td[2]/h3/a")
					.click();
		new Url().goBackToPreviousPage();
		for (int i = 1; i < new TextLabel(properties.get("tabs_count"))
				.getXpathCount(); i++) {
			if (!new TextLabel(properties.get("tabs_count") + "[" + i
					+ "]/h3/a").isPresent())
				return false;
		}
		return true;
	}

	public boolean verifyTabsForDetailedPage(String displayGroup)
			throws InterruptedException {
		if (displayGroup.equals("images") || displayGroup.equals("video"))
			new Link(properties.get("thumbnail")).click();
		else
			new Link(properties.get("viewAll_searchResults") + "[1]/td[2]/h3/a")
					.click();
		for (int i = 1; i < new TextLabel(properties.get("tabs_count"))
				.getXpathCount(); i++) {
			if (new TextLabel(properties.get("tabs_count") + "[" + i + "]/h3/a")
					.isPresent())
				return false;
		}
		return true;
	}

	public boolean ensureSingleTabIsSelectedOnce(String groupType,
			String groupNeedToSelect, String groupContent)
			throws InterruptedException {
		new Url().goBackToPreviousPage();
		new Link(properties.get(groupNeedToSelect + "_tab")).click();
		if (new TextLabel(properties.get("present_tab")
				+ "[@class='current' and contains(text(),'" + groupContent
				+ "')]").isPresent())
			return false;
		new Link(properties.get(groupType + "_tab")).click();
		return true;
	}

	public boolean headingMatch(String displayGroup, String groupContent) {
		String headingName = new TextLabel("//div/h2[contains(text(),'"
				+ groupContent + "')]").getLabelText();
		String groupName = new TextLabel(properties.get(displayGroup + "_tab"))
				.getLabelText();
		if (groupName.contains(headingName))
			return true;
		return false;
	}

	public boolean verifyRecordsCountWithViewAll(String displayGroup)
			throws NumberFormatException, Exception {
		if (new TextLabel(properties.get(displayGroup + "_viewAll"))
				.isPresent()) {
			int count = Integer
					.parseInt(new TextLabel(properties.get(displayGroup
							+ "_viewAll")).getLabelText().split(" ")[2]
							.replace(",", ""));
			new Link(properties.get(displayGroup + "_viewAll")).click();
			if (count == Integer.parseInt(getCount(properties.get(displayGroup
					+ "_tab")))) {
				return true;
			}
		}
		return true;
	}

	public boolean verifySearchResult(String groupNeedToSelect)
			throws InterruptedException {
		if(new TextLabel(properties.get(groupNeedToSelect+"_viewAll")).isPresent())
			new Link(properties.get(groupNeedToSelect+"_viewAll")).click();
		new Link(properties.get(groupNeedToSelect + "_tab")).clickWithoutWait();
		if (new TextLabel(properties.get("success_message_xpath")).isPresent())
			return true;
		return false;
	}
}
