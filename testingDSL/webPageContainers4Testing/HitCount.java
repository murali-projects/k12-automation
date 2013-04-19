package webPageContainers4Testing;

import java.util.List;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class HitCount extends BasePageContainer {

	public HitCount() throws Exception {
		super();
	}

	public String getCount(TextLabel locator) throws Exception {
		if(locator.isPresent()){
			String linkText = locator.getLabelText();
			if (linkText.contains(","))
				linkText = linkText.replace(",", "");
			String noOfLinks = linkText.substring(8).trim();
			// getSubString(linkText , "(", ")");
			return noOfLinks;
		}
		return "";
	}

	/*
	 * private String getSubString(String source ,String startIndex,String
	 * endIndex){ return source.substring(source.indexOf(startIndex)+1,
	 * source.indexOf(endIndex)); }
	 */

	public boolean verifyCountisDisplayed(String displayGroup) throws Exception {
		if(new TextLabel(properties.get(displayGroup+"_viewAll")).isPresent()){
			String linkText = new TextLabel(properties.get(displayGroup+"_viewAll")).getLabelText().substring(8);
			return (linkText != null);
		}
		return true;
	}

	public boolean verifyCountisDisplayedAfterClickingViewAll (String displayGroup) throws InterruptedException, Exception {
		if(new Link(properties.get(displayGroup+"_viewAll")).isPresent()){
			new Link(properties.get(displayGroup+"_viewAll")).click();
			String linkText = new TextLabel(properties.get(displayGroup+"_viewAll_afterClick")).getLabelText();
			return (linkText != null);
		}
		return true;

	}

	public boolean validateCount(String count, String displayGroup,
			String searchTerm) throws Exception {

		if(count.equals(""))
			return true;
		// get the count from the application.
		String refArticlesCount = count.trim();

		// get the count from the DB.
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(
				properties.get("all"), properties.get(displayGroup), properties
						.get("relevance"), searchTerm);
		String refArticlesCountFromDatabase = (list != null && !list.isEmpty()) ? list
				.get(0)
				: "0";
		// check count with reference count retrieved from the DB.
		return (refArticlesCount.trim().equals(refArticlesCountFromDatabase)) ? true
				: false;
	}

	public boolean validateCountForMin(String displayGroup, TextLabel locator,
			String searchTerm) throws Exception {

		// get the count from the application.
		String count = getCount(locator);

		// get the count from the DB.
		List<String> resultCountFromDB = OceanDatabaseReadFile
				.readResultsCountFromFile(properties.get("all"), properties
						.get(displayGroup), properties.get("relevance"),
						searchTerm);
		String countFromDB = (resultCountFromDB != null && !resultCountFromDB
				.isEmpty()) ? resultCountFromDB.get(0) : "0";

		// check count with reference count retrieved from the DB.
		return (count.trim().equals(countFromDB)) ? true : false;
	}

	public boolean checkCountAfterClickingViewAll(String displayGroup,
			String searchTerm) throws Exception {
		new Link(properties.get(displayGroup + "_viewAll")).click();
		return (validateCount(new TextLabel(properties.get(displayGroup
				+ "_viewAll_afterClick")).getLabelText(), displayGroup,
				searchTerm));

	}

	public boolean checkCount(String displayGroup, String searchTerm)
			throws Exception {
		return (validateCount(getCount(new TextLabel(properties
				.get(displayGroup + "_viewAll"))), displayGroup, searchTerm));
	}

	public boolean checkCountWithZero(String displayGroup, String searchTerm)
			throws Exception {
		return (validateCountForMin(displayGroup, new TextLabel(properties
				.get(displayGroup + "_viewAll")), searchTerm));
	}

}
