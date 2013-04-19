package webPageContainers4Testing;

import webPageElements4Testing.TextLabel;

public class ViewHomePage extends BasePageContainer {

	public ViewHomePage() throws Exception {
		super();
	}

	public boolean basicSearchBoxIsPresent() {
		return new TextLabel(properties.get("search_box")).isPresent()
				&& new TextLabel(properties.get("find_button")).isPresent();
	}

	public boolean verifyTopMenuBar() {
		return new TextLabel(properties.get("top_menu")).isPresent();
	}

	public boolean verifyBottomMenuBar() {
		return new TextLabel(properties.get("bottom_menu")).isPresent();
	}

	public boolean verifyPreMentionedPortlets() {
		return new TextLabel(properties.get("browser_portlets")).isPresent();
	}

	public boolean verifyNewsPortletIsPresent() {
		return new TextLabel(properties.get("news_searchBox")).isPresent()
				&& new TextLabel(properties.get("news_searchButton")).isPresent();
	}
}
