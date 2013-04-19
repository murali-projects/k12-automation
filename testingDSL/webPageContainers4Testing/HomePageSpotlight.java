package webPageContainers4Testing;

import java.util.Random;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class HomePageSpotlight extends BasePageContainer {

	public HomePageSpotlight() throws Exception {
		super();
	}

	public boolean verifySpotlightPortletPage() {
		return new TextLabel(properties.get("spotilight_portlet")).isPresent();
	}

	public boolean verifyImageInPortlet() throws InterruptedException {
		boolean isImage = new TextLabel(properties.get("spotlight_image")).isPresent();
		new Link(properties.get("spotlight_image_link")).click();
		return isImage && new TextLabel(properties.get("spotlight_detailedview")).isPresent();
	}

	public boolean verifyDocumentTitleOrPortalPageTile() {
		return new TextLabel(properties.get("spotlight_documenttile")).isPresent()
				|| new TextLabel(properties.get("spotlight_portaltitle")).isPresent();
	}

	public boolean verifyPageLeadToDetailedViewPage()
			throws InterruptedException {
		boolean isNavigated = false;
		if (new TextLabel(properties.get("spotlight_documenttile")).isPresent()) {
			new Link(properties.get("spotlight_documenttile")).click();
			isNavigated = new TextLabel(properties.get("spotlight_documenttile_detailview")).isPresent();
		}
		if (new TextLabel(properties.get("spotlight_portaltitle")).isPresent()) {
			new Link(properties.get("spotlight_portaltitle")).click();
			isNavigated = new TextLabel(properties.get("spotlight_portaltitle_detailview")).isPresent();
		}
		return isNavigated;
	}

	public boolean verifySnippetForDocumentLink() {
		if (new Link(properties.get("spotlight_snippet")).isPresent()) {
			return new TextLabel(properties.get("spotlight_snippet_details")).isPresent();
		}
		return true;
	}

	public boolean verifySnippetForPortalPageTilte() {
		if (new Link(properties.get("spotlight_snippet")).isPresent()) {
			return new TextLabel(properties.get("spotlight_snippet_details")).isPresent();
		}
		return true;
	}

	public boolean verifyReadMoreLink() {
		return new Link(properties.get("snippet_readmore_document")).isPresent()
				&& new Link(properties.get("snippet_readmore_portal")).isPresent();
	}

	public boolean readMoreLinkDetailPage() throws InterruptedException {
		new Link(properties.get("snippet_readmore_document")).click();
		return new TextLabel(properties.get("snippet_readmore_detailview")).isPresent();
	}

	public boolean verifyPortalOrDocumentCountInSpotlight() {
		return new TextLabel(properties.get("spotlight_count")).getXpathCount() <= 5;
	}

	public boolean verifyEachItemIsAppearedAsNumber() {
		boolean isNumbered = false;
		for (int i = 1; i < new TextLabel(properties.get("spotlight_count")).getXpathCount(); i++) {
			isNumbered = new TextLabel(properties.get("spotlight_number")).isPresent();
		}
		return isNumbered;
	}

	public boolean verifySpotlightUpdates() throws InterruptedException {
		new Link(properties.get("spotlight_link")).click();
		return new TextLabel(properties.get("spotlight_image")).isPresent()
				&& new TextLabel(properties.get("spotlight_detailedview")).isPresent();
	}

	public boolean verifySpotlightUpdatesForAllFive()
			throws InterruptedException {
		boolean isUpdated = false;
		for (int i = 1; i < new TextLabel(properties.get("spotlight_count")).getXpathCount(); i++) {
			new Link(properties.get("spotlight_link")).click();
			isUpdated = new TextLabel(properties.get("spotlight_image")).isPresent()
					&& new TextLabel(properties.get("spotlight_detailedview")).isPresent();
		}

		return isUpdated;
	}

	public boolean verifyGreyedOutForCurrentPage() throws InterruptedException {
		new Link(properties.get("spotlight_link")).click();
		return new TextLabel(properties.get("spotlight_link_grey")).isPresent();
	}

	public boolean verifyPortletForViewAllPage(String displayeGroup)
			throws InterruptedException {
		new Link(properties.get(displayeGroup + "_viewAll")).click();
		return !new TextLabel(properties.get("spotilight_portlet")).isPresent();
	}

	public boolean verifySpotlightNavigation() throws InterruptedException {
		boolean isNavigated = false;
		new Link(properties.get("spotlight_link")).click();
		isNavigated = new TextLabel(properties.get("spotlight_detailedview")).isPresent();
		new Url().goBackToPreviousPage();
		new Link(properties.get("spotlight_link")).click();
		isNavigated = new TextLabel(properties.get("spotlight_detailedview")).isPresent();
		return isNavigated;
	}

	public boolean linkNavigationInSpotlight() throws InterruptedException {
		boolean isNavigated = false;
		for (int i = 1; i < new TextLabel(properties.get("spotlight_count")).getXpathCount(); i++) {
			Random randomGenerator = new Random();
			int randomNumber = randomGenerator.nextInt(4) + 1;
			new Link(properties.get(""+randomNumber)).click();
			isNavigated = new TextLabel(properties.get("spotlight_detailedview")).isPresent();
		}

		return isNavigated;
	}
}
