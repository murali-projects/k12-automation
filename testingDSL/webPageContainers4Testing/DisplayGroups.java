package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class DisplayGroups extends BasePageContainer {

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

	public DisplayGroups() throws Exception {
		super();
		displayGroupLabels = new TextLabel(properties.get("displayGroup"));
		results = new TextLabel(properties.get("results"));
		next = new Link(properties.get("next"));
		previous = new Link(properties.get("previous"));
		loadTabMap();
		loadAllGroupMap();
	}

	public void loadTabMap() {
		tabMap = new HashMap<String, String>();
		tabMap.put("referenceArticlesTab", properties.get("reference_tab"));
		tabMap.put("newsTab", properties.get("news_tab"));
		tabMap.put("magazinesTab", properties.get("magazines_tab"));
		tabMap.put("academicJournalsTab", properties.get("academicJournals_tab"));
		tabMap.put("imagesTab", properties.get("images_tab"));
		tabMap.put("audioTab", properties.get("audio_tab"));
		tabMap.put("videoTab", properties.get("video_tab"));
	}

	public void loadAllGroupMap() {
		allGroupNamesMap = new HashMap<String, String>();
		allGroupNamesMap.put(properties.get("reference_label"),
				properties.get("reference_viewAll"));
		allGroupNamesMap.put(properties.get("news_label"),
				properties.get("news_viewAll"));
		allGroupNamesMap.put(properties.get("magazines_label"),
				properties.get("magazines_viewAll"));
		allGroupNamesMap
				.put(properties.get("academicJournals_label"),
						properties.get("academicJournals_viewAll"));
		allGroupNamesMap.put(properties.get("images_label"),
				properties.get("images_viewAll"));
		allGroupNamesMap.put(properties.get("audio_label"),
				properties.get("audio_viewAll"));
		allGroupNamesMap.put(properties.get("video_label"),
				properties.get("video_viewAll"));
	}

	public boolean validateDisplayGroup(String groupType, String contentGroup,
			String sortBy, String dataBaseElement, String searchTerm)
			throws Exception {
		return validateNavigationPageForDisplayGroup(groupType, contentGroup,
				sortBy, dataBaseElement, searchTerm)
				&& verifyDisplayGroupCount(groupType, contentGroup, sortBy,
						searchTerm)
				&& verifyNextLinkForDisplayGroup(getCount(properties.get(groupType+"_tab")), next,
						results)
				&& verifyPreviousLinkForDisplayGroup(previous, results);
	}

	private boolean validateNavigationPageForDisplayGroup(String groupType,
			String contentGroup, String sortBy, String dataBaseElement,
			String searchTerm) throws Exception {
		if (new Link(properties.get(groupType+"_tab")).isPresent())
			navigateTab(properties.get(groupType+"_tab"));
		return verifyContentWithDatabase(
				getAllGroupDocumentIds(properties.get("displayGroup")), contentGroup, sortBy,
				dataBaseElement, searchTerm);
	}

	private boolean verifyDisplayGroupCount(String groupType,
			String contentGroup, String sortBy, String searchTerm)
			throws Exception {
		String displayCount = getCount(properties.get(groupType+"_tab"));
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(
				"all", contentGroup, sortBy, searchTerm);
		String refArticlesCountFromDatabase = (list != null && !list.isEmpty()) ? list
				.get(0)
				: "0";
		return (displayCount.equals(refArticlesCountFromDatabase));
	}

	private List<String> getAllGroupDocumentIds(String locator) {
		List<String> displayGroupIdList = new ArrayList<String>();
		String displayGroup;
		int groupDisplayCount = displayGroupLabels.getXpathCount();
		for (int i = 1; i <= groupDisplayCount; i++) {
			displayGroup = displayGroupLabels.getLabelText(locator + "+li[" + i
					+ "]+");
			displayGroupIdList.add(displayGroup);
		}
		return displayGroupIdList;
	}

	private boolean verifyContentWithDatabase(List<String> contentList,
			String contentGroup, String sortBy, String dataBaseElement,
			String searchTerm) throws IOException {
		for (int i = 1; i <= contentList.size(); i++) {
			if (!contentList.get(i).equals(
					OceanDatabaseReadFile.readResultsFromFile("all",
							contentGroup, sortBy, dataBaseElement, searchTerm)
							.get(i)))
				return false;

		}
		return true;
	}

	private boolean verifyNextLinkForDisplayGroup(String count, Link link,
			TextLabel label) throws InterruptedException {
		if (Integer.parseInt(count) > 10 && link.isPresent()) {
			link.click();
			return new Integer(label.getLabelText()) > 10 ? true : false;
		}
		return false;
	}

	private boolean verifyPreviousLinkForDisplayGroup(Link link, TextLabel label)
			throws InterruptedException {
		if (Integer.parseInt(label.getLabelText()) > 10 && link.isPresent()) {
			link.click();
			return new Integer(label.getLabelText()) < 10 ? true : false;
		}
		return false;
	}

	private String getCount(String locator) throws Exception {
		Thread.sleep(3000);
		String displayGroup = new TextLabel(locator).getLabelText();
		String displayGroupCountValue = getSubString(displayGroup, "(", ")");
		return displayGroupCountValue;
	}

	private String getSubString(String source, String startIndex,
			String endIndex) {
		return source.substring(source.indexOf(startIndex) + 1, source
				.indexOf(endIndex));
	}

	public void navigateTab(String tabName) throws Exception {
		tab = new Link(tabName);
		tab.click();
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
			new Link(allGroupNamesMap.get(displayedGroups.get(0))).click();
			return true;
		}
		return false;
	}

	public boolean validateSearchContent() throws Exception {
		boolean displayedContentIsEqual = false;
		for (int i = 0; i < displayedGroupsForSearchTerm().size(); i++) {
			for (int y = 0; i < displayedGroupTabs().size(); y++) {
				if (displayedGroupsForSearchTerm().get(i).equals(
						displayedGroupTabs().get(y))) {
					displayedContentIsEqual = true;
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
}
