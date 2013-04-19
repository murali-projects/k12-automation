package webPageContainers4Testing;

import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

@Deprecated
public class ViewPrimarySourcesDisplayGroup extends BasePageContainer{

	private String currentUrl;
	private TextLabel primarySourceLabel;
	private Link viewAllLink;

	public ViewPrimarySourcesDisplayGroup() throws Exception {
		super();
		primarySourceLabel = new TextLabel(properties.get("primarySources_label"));
		viewAllLink = new Link(properties.get("primarySources_viewAll"));
	}
	
	public boolean verifySearchTermInMsgDisplayed(String searchTerm) throws Exception{
		String msg = new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText();
		return msg.equals(properties.get("searchResults_msg") + searchTerm) ? true : false;
	}
	
	public boolean checkDisplayGroupIsPresent() {
		if (primarySourceLabel.isPresent())
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsPresent() {

		if ((viewAllLink.isPresent()) && (viewAllLink.getLinkText().equals(properties.get("viewAll"))))
			return true;
		return false;
	}

	public boolean checkViewAllLinkIsWorking() throws Exception {
		viewAllLink.click();
		currentUrl = new Url().getUrl();
		return (currentUrl.contains("PrimarySource"+".jsp") && currentUrl.contains("windowstate=maximized"));
	}
 
	public String getCount(TextLabel locator) throws Exception {
		Thread.sleep(3000);
		String audioLinkText = locator.getLabelText();
		String noOfAudioLinks = getSubString(audioLinkText, "(", 	")" );
		return noOfAudioLinks;
	}

	private String getSubString(String source, String startIndex, String endIndex) {
		return source.substring(source.indexOf(startIndex) + 1, source.indexOf(endIndex));
	}

	public boolean validateCount(String searchTerm) throws Exception {

		// get the results count from the application.
		String count = getCount(primarySourceLabel);

		// get the results count from the DB.
		List<String> resultCountFromDatabase = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("all"),
				properties.get("reference"),
				properties.get("relevance"), searchTerm);
		String countFromDatabase = (resultCountFromDatabase!=null && !resultCountFromDatabase.isEmpty()) ? resultCountFromDatabase.get(0): "0";
		
		// check results count with the count retrieved from the DB.
		return (count.equals(countFromDatabase)) ? true : false;
	}

	public boolean verifyLinkCountInFirstPage()
			throws Exception {
		
		//Total number of results.
		int LinksCount = new Link(properties.get("primarySources_searchResults_xpath")).getLinkCount();
		
		if (Integer.parseInt(getCount(primarySourceLabel)) <= 3 && LinksCount == Integer.parseInt(getCount(primarySourceLabel)))
			return true;
		else {
			return (LinksCount == 3) ? true : false;
		}
	}
}
