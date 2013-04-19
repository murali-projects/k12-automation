package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewTopicPortalPageOverviewPortlet extends BasePageContainer {

	public ViewTopicPortalPageOverviewPortlet() throws Exception {
		super();

	}

	public boolean verifyNavigationByclickingoOnTopics() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		return (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
				.isPresent());

	}

	public boolean verifyOverviewTextWithDocumentText() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		String data = new TextLabel(properties.get("topic_overview_data")).getLabelText();
		new Link(properties.get("topics_detailed_overview")).click();
		String text = new TextLabel(properties.get("topic_text_details")).getLabelText();
		return (text.contains(data));
	}

	public boolean verifyTopicMatchesTitle() throws Exception {
		String topic_text = new TextLabel("").getLabelText();
		new Link(properties.get("topics_portlet_link")).click();
		String title_text = new TextLabel("").getLabelText();
		return (topic_text.equalsIgnoreCase(title_text));
	}

	public boolean verifyOverviewDisplayed() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		return (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
				.isPresent());

	}

	public boolean verifyElipsisPresent() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		String data = new TextLabel(properties.get("topic_document_full_text")).getLabelText();
		String elipses = "...";
		return (data.contains(elipses));
	}

	public boolean verifyViewMoreOptionAvailable() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		return (new PageElementWithIdAttribute(properties.get("topics_detailed_overview"))
				.isPresent());

	}

	public boolean verifyNavigationByclickingViewMore() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		new Link(properties.get("topics_detailed_overview")).click();
		return (new Url().getCurrentUrl().contains("mode"));

	}

	public boolean verifyOverviewTextWithDetailedPageText() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		String data = new TextLabel(properties.get("topic_overview_data")).getLabelText();
		new Link(properties.get("topics_detailed_overview")).click();
		String text = new TextLabel(properties.get("topic_text_details")).getLabelText();
		return (text.contains(data));
	}

	public boolean verifyBreadCrumb() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		Thread.sleep(20000);
		return (new PageElementWithIdAttribute(properties.get("topic_breadcrumb"))
				.isPresent());

	}

	public boolean verifyToolsAvailableInDetailPage() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		new Link(properties.get("topics_detailed_overview")).click();
		return (new PageElementWithIdAttribute(properties.get("topic_tools"))
				.isPresent());

	}

	public boolean verifyComparisionOfOverviewWithTwoTopics() throws Exception {
		new Link(properties.get("topic_inside_portlet")).click();
		String data = new TextLabel(properties.get("topic_overview_data")).getLabelText();
		new TextBox(properties.get("search_box")).type("adoption");
		new PageButton(properties.get("find_button")).clickAndWait();
		Thread.sleep(10000);
		new Link(properties.get("topic_inside_portlet_2")).click();
		String text = new TextLabel(properties.get("topic_overview_data")).getLabelText();
		return (!text.equalsIgnoreCase(data));

	}

}
