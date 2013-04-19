package webPageContainers4Testing;

import java.util.List;

import webPageContainers4Testing.BasePageContainer;
import webPageContainers4Testing.OceanDatabaseReadFile;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class IntegrateWeblinksDisplayGroup extends BasePageContainer{

	private TextLabel webSitesLabel;
	private Link viewAllLink;

	public IntegrateWeblinksDisplayGroup() throws Exception {
		super();
		webSitesLabel = new TextLabel(properties.get("websites_label"));
		viewAllLink = new Link(properties.get("websites_viewAll"));
	}
	
	public boolean checkDisplayGroupIsPresent() {
		return (webSitesLabel.isPresent());
	}

	public boolean checkViewAllLinkIsPresent() {
		if ((viewAllLink.isPresent()))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		return new TextLabel(properties.get("websites_viewAll_page")).isPresent();
	}
 
	public String getTotalResultsCount() throws Exception {
		if (new Link(properties.get("websites_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("websites_viewAll"))
					.getLinkText();
			return viewAllText.split(" ")[2].trim().replace(
					",", "");
		}
		return "";
	}

	public boolean validateCount(String searchTerm) throws Exception {
		String count = getTotalResultsCount();
		List<String> resultCountFromDatabase = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("all"),
				properties.get("websites"),
				properties.get("relevance"), searchTerm);
		String countFromDatabase = (resultCountFromDatabase!=null && !resultCountFromDatabase.isEmpty()) ? resultCountFromDatabase.get(0): "0";
		return (count.equals(countFromDatabase)) ? true : false;
	}

	public boolean verifyLinkCountInFirstPage()
			throws Exception {
		int LinksCount = new Link(properties.get("websites_searchResults_xpath")).getLinkCount();
		if (Integer.parseInt(getTotalResultsCount()) > 3)
			return (LinksCount == 3) ? true : false;
		return true;
	}
}
