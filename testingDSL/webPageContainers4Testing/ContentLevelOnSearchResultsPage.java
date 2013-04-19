package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageContainers4Testing.BasePageContainer;
import webPageContainers4Testing.OceanDatabaseReadFile;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ContentLevelOnSearchResultsPage extends BasePageContainer {

	public ContentLevelOnSearchResultsPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * private List<String> contentLevelDBValues; private List<String>
	 * basicList; private List<String> intermediateList; private List<String>
	 * advancedList; private String searchTerm;
	 * 
	 * public boolean validateContentLevalValues() throws Exception {
	 * contentLevelValuesInDB(properties.get("content_group_contentLevel"),
	 * properties.get("sort_contentLevel"), properties .get("targetAudience"));
	 * return basicLevelSearch() && intermediateLevelSearch() &&
	 * advancedLevelSearch(); }
	 * 
	 * public void contentLevelValuesInDB(String contentGroup, String sortBy,
	 * String dataBaseElement) throws IOException { contentLevelDBValues =
	 * OceanDatabaseReadFile.readResultsFromFile( properties.get("all"),
	 * contentGroup, sortBy, dataBaseElement, searchTerm); for (int i = 0; i <
	 * contentLevelDBValues.size(); i++) { if
	 * (contentLevelDBValues.get(i).equalsIgnoreCase("basic")) {
	 * this.basicList.add(contentLevelDBValues.get(i)); } else if
	 * (contentLevelDBValues.get(i).equalsIgnoreCase( "intermediate")) {
	 * this.intermediateList.add(contentLevelDBValues.get(i)); } else if
	 * (contentLevelDBValues.get(i).equalsIgnoreCase("advanced")) {
	 * this.advancedList.add(contentLevelDBValues.get(i)); } } }
	 * 
	 * private boolean basicLevelSearch() throws Exception { int count; int a =
	 * 0; if (basicList.size() > 0) { for (count = 1; count <= basicList.size();
	 * count++) { if (!new TextLabel(properties.get("editorPicks") + "[" + count
	 * + "]" + properties.get("sortType")).getAttribute(
	 * "width").equalsIgnoreCase("31")) { return false; } a++; if (a ==
	 * basicList.size()) break; if (count > 20) { new Link("").click(); count =
	 * 1; } else if (count == 20) { new Link("").click(); } } } return true; }
	 * 
	 * private boolean intermediateLevelSearch() throws Exception { int count =
	 * 0; if (basicList.size() > 20) { count = basicList.size() % 20; } for (int
	 * i = 1; i <= intermediateList.size(); i++) { if (!new
	 * TextLabel(properties.get("editorPicks") + "[" + count++ + "]" +
	 * properties.get("sortType")).getAttribute("width")
	 * .equalsIgnoreCase("60")) { return false; } if (count == 20) { new
	 * Link("").click(); count = 0; } } return true; }
	 * 
	 * private boolean advancedLevelSearch() throws Exception { int count = 0;
	 * if (basicList.size() > 20 || intermediateList.size() > 20) { count =
	 * basicList.size() % 20 + intermediateList.size() % 20; } for (int i = 1; i
	 * <= advancedList.size(); i++) { if (!new
	 * TextLabel(properties.get("editorPicks") + "[" + count++ + "]" +
	 * properties.get("sortType")).getAttribute("width")
	 * .equalsIgnoreCase("90")) { return false; } if (count == 20) { new
	 * Link("").click(); count = 0; } } return true; }
	 */

	public boolean verifyIndicatorIsDisplayed(String displayGroup)
			throws InterruptedException {
		int count;
		if (new TextLabel(properties.get(displayGroup + "_viewAll"))
				.isPresent()) {
			count = Integer.parseInt(new TextLabel(properties.get(displayGroup
					+ "_viewAll")).getLabelText().substring(8).replace(",", "")
					.trim());
			if (count >= 20)
				count = 20;
			new Link(properties.get(displayGroup + "_viewAll")).click();
			for (int i = 1; i <= count; i++) {
				if (!new TextLabel(properties.get("contentLevel_locator") + i
						+ "]/td[4]/a/img").isPresent())
					return false;
			}
		}
		return true;
	}

	public boolean verifyToolTipIsDisplayed() {
		if (new TextLabel(properties.get("total_resultscount")).isPresent()) {
			int count = Integer.parseInt(new TextLabel(properties
					.get("total_resultscount")).getLabelText().replace(",", "")
					.trim());
			if (count >= 20)
				count = 20;
			List<String> toolTipMessages = new ArrayList<String>();
			String toolTip;
			for (int i = 1; i <= count; i++) {
				selenium.mouseOver(properties.get("contentLevel_locator") + i
						+ "]/td[4]/a/img");
				toolTip = new TextLabel(properties.get("contentLevel_locator")
						+ i + "]/td[4]/a/img").getAttribute("alt");
				String s[] = toolTip.split("=");
				if (s.length == 2) {
					toolTipMessages.add(s[1]);
				}
				if (toolTip == null)
					return false;
			}
		}
		return true;
	}

	private List<String> getDataFromDB(String searchTerm, String displayGroup,
			String sortBy, String dataToRetrieve) throws IOException {
		List<String> list = OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"), displayGroup, sortBy, dataToRetrieve,
				searchTerm);
		return list;
	}

	public boolean validateToolTipMessages(String searchTerm,
			String displayGroup, String sortBy) throws IOException {
		if(displayGroup.equals("viewpoints")||displayGroup.equals("news")||displayGroup.equals("magazines")||
				displayGroup.equals("Journals")||displayGroup.equals("images")||
				displayGroup.equals("audio")||displayGroup.equals("video")){
			new DropDownBox(properties.get("sort_by_xpath")).select(properties
					.get("relevance"));
			selenium.waitForPageToLoad("10000");
		}
		if (new TextLabel(properties.get("total_resultscount")).isPresent()) {
			int count = Integer.parseInt(new TextLabel(properties
					.get("total_resultscount")).getLabelText().replace(",", "")
					.trim());
			if (count >= 20)
				count = 20;
			List<String> list = new ArrayList<String>();
			String toolTip;
			for (int i = 1; i <= count; i++) {
			 if(new TextLabel(properties.get("contentLevel_locator")
						+ i + "]/td[4]/a/img").isPresent())
				{
				toolTip = new TextLabel(properties.get("contentLevel_locator")
						+ i + "]/td[4]/a/img").getAttribute("alt");
				String s[] = toolTip.split(",");
				list.add(s[0].substring(s[0].indexOf('=')+1).trim());
				}
				else{
					list.add("");
				}
			}
			return compareWithDB(list, getDataFromDB(searchTerm, displayGroup,
					sortBy, properties.get("targetAudience")));
		}
		return true;
	}

	private boolean compareWithDB(List<String> dataInAppl, List<String> dataInDB) {
		for (int i = 0; i < dataInAppl.size(); i++) {
			if (!(dataInAppl.get(i).equals(dataInDB.get(i)))) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyContentLevelDisplayedInAll(String displayGroup)
			throws InterruptedException {
		if (new TextLabel(properties.get("total_resultscount")).isPresent()) {
			int count = Integer.parseInt(new TextLabel(properties
					.get("total_resultscount")).getLabelText().replace(",", "")
					.trim());
			if (count > 20)
				count = 20;
			for (int j = 1; j <= count; j++) {
				if (!new TextLabel(properties.get("contentLevel_locator") + j
						+ "]/td[4]/a/img").isPresent())
					return true;
			}
		}
		return true;
	}

}
