package webPageContainers4Testing;

import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

@Deprecated
public class IntegratePrimarySourcesDisplayGroup extends BasePageContainer{

	private TextLabel primarySourceLabel;
	private Link viewAllLink;

	public IntegratePrimarySourcesDisplayGroup() throws Exception {
		super();
		primarySourceLabel = new TextLabel(properties.get("primarysources_label"));
		viewAllLink = new Link(properties.get("primarysources_viewAll"));
	}
	
	public boolean verifySearchTermInMsgDisplayed(String searchTerm) throws Exception{
		String msg = new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText();
		return msg.equals(properties.get("searchResults_msg") + searchTerm) ? true : false;
	}
	
	public boolean checkDisplayGroupIsPresent() {
		return (primarySourceLabel.isPresent());
	}

	public boolean checkViewAllLinkIsPresent() {
		if ((viewAllLink.isPresent()))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		return new TextLabel(properties.get("primarysources_viewAll_page")).isPresent();
	}
 
	public String getTotalResultsCount() throws Exception {
		if (new Link(properties.get("primarysources_viewAll")).isPresent()) {
			String viewAllText = new Link(properties.get("primarysources_viewAll"))
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
				properties.get("K12-PrimarySources"),
				properties.get("relevance"), searchTerm);
		String countFromDatabase = (resultCountFromDatabase!=null && !resultCountFromDatabase.isEmpty()) ? resultCountFromDatabase.get(0): "0";
		return (count.equals(countFromDatabase)) ? true : false;
	}

	public boolean verifyLinkCountInFirstPage()
			throws Exception {
		int LinksCount = new Link(properties.get("primarysources_searchResults_xpath")).getLinkCount();
		if (Integer.parseInt(getTotalResultsCount()) > 3)
			return (LinksCount == 3) ? true : false;
		return true;
	}
}
