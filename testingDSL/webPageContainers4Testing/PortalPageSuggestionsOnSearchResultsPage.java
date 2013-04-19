package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class PortalPageSuggestionsOnSearchResultsPage extends BasePageContainer {
	String displayGroup = "news,magazines,viewpoints";

	public PortalPageSuggestionsOnSearchResultsPage() throws Exception {
		super();

	}

	public boolean verifyTopicsPortletAvailable() throws Exception {
		 return(new TextLabel(properties.get("topics_associated")).isPresent());
	}

	public boolean verifyTopicsPortletForInvalidKeyword() throws Exception {
		 return(!new TextLabel(properties.get("topics_associated")).isPresent());
	}

	public boolean verifyTopicPortalalongWithOtherBuckets() throws Exception {
		return (new TextLabel(properties.get("topics_associated")).isPresent()
				&&new PageElementWithIdAttribute(properties.get("news_link"))
		.isPresent());
	}

	public boolean verifyTopicsAreHyperlinked() throws Exception {
		return (new PageElementWithIdAttribute(properties.get("topic_inside_portlet")).isPresent());
	}

	public boolean verifySearchResultsStatementIsDisplayed() throws Exception {
		return (new PageElementWithIdAttribute(properties.get("searchResults_msg_xpath")).isPresent());
	}

}
