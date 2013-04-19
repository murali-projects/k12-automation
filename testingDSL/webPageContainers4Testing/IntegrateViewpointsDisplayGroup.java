package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

@Deprecated
public class IntegrateViewpointsDisplayGroup extends BasePageContainer{

	private TextLabel viewpointsLabel;
	private Link viewAllLink;

	public IntegrateViewpointsDisplayGroup() throws Exception {
		super();
		viewpointsLabel = new TextLabel(properties.get("viewpoints_label"));
		viewAllLink = new Link(properties.get("viewpoints_viewAll"));
	}
	
	public boolean checkDisplayGroupIsPresent() {
		return (viewpointsLabel.isPresent());
	}

	public boolean checkViewAllLinkIsPresent() {
		if ((viewAllLink.isPresent()))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		return new TextLabel(properties.get("viewpoints_viewAll_page")).isPresent();
	}
 
	public String getTotalResultsCount() throws Exception {
		if (new Link(properties.get("viewpoints_viewAll")).isPresent()) {
			String viewAllText = viewAllLink.getLinkText();
			return viewAllText.split(" ")[2].trim().replace(
					",", "");
		}
		return "";
	}

	public boolean validateCount(String searchTerm) throws Exception {
		String count = getTotalResultsCount();
		List<String> resultCountFromDatabase = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("all"),
				properties.get("viewPoints"),
				properties.get("relevance"), searchTerm);
		String countFromDatabase = (resultCountFromDatabase!=null && !resultCountFromDatabase.isEmpty()) ? resultCountFromDatabase.get(0): "0";
		return (count.equals(countFromDatabase)) ? true : false;
	}

	public boolean verifyLinkCountInFirstPage()
			throws Exception {
		int LinksCount = new Link(properties.get("viewpoints_searchResults_xpath")).getLinkCount();
		if (Integer.parseInt(getTotalResultsCount()) > 3)
			return (LinksCount == 3) ? true : false;
		return true;
	}
	
	public boolean verifyDocumentTitles(String searchTerm) throws Exception{
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties.get("viewpoints_searchResults_xpath")).getXpathCount(); i++) {
			list.add(new Link(properties.get("viewpoints_searchResults_xpath")+"["+i+"]/h3/a").getLinkText());
		}
		return compareWithDB(list, searchTerm);
	}
	
	private boolean compareWithDB(List<String> dataInAppl,String searchTerm) throws IOException{
		List<String> dataInDB = OceanDatabaseReadFile.readResultsFromFile(properties.get("all"), properties.get("viewPoints"), properties.get("publicationDate"),
				properties.get("documentTitle"), searchTerm);
		for (int i = 0; i < dataInAppl.size(); i++) {
			if (!(dataInAppl.get(i).equals(dataInDB.get(i)))) {
				return false;
			}
		}
		return true;
	}
}


