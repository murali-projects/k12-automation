package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class LibraryLinks extends BasePageContainer {

	public LibraryLinks() throws Exception {
		super();
	}

	public boolean verifyLibraryLinksAvailability(String displayedGroup)
			throws InterruptedException {
		boolean isPresent = false;
		new Link(properties.get(displayedGroup + "_searchResults_xpath")
				+ "[1]/h3/a").click();
		isPresent = new TextLabel(properties.get("library_links")).isPresent();
		new Url().goBackToPreviousPage();
		return isPresent;
	}

	public boolean verifyLinksUnderLibrarySection() {
		boolean isPresent = false;
		isPresent = new Link(properties.get("library_holdings")).isPresent()
				&& new Link(properties.get("external_url")).isPresent()
				&& new Link(properties.get("internal_library")).isPresent()
				&& new Link(properties.get("jstor")).isPresent();
		new Url().goBackToPreviousPage();
		return isPresent;

	}

	public boolean verifyLibraryLinksAvailabilityForAllGroups(
			String displayGroup) throws InterruptedException {
		boolean isPresent = true;
		String[] displayGroups = displayGroup.split(",");
		for (int i = 0; i < displayGroups.length; i++) {
			if (new TextLabel(properties.get(displayGroups[0]+"_label")).isPresent()) {
				for (int count = 1; count <= new TextLabel(properties.get(displayGroups[0]+"_searchResults_xpath"))
						.getXpathCount(); count++) {
					new Link(properties.get(displayGroup
							+ "_searchResults_xpath")
							+ "[" + count + "]/h3/a").click();
					isPresent = new TextLabel(properties.get("library_links"))
							.isPresent();
					new Url().goBackToPreviousPage();
				}
			}

		}
		return isPresent;
	}

	public boolean verifyLinksInPortalPage() throws InterruptedException {
		new Link(properties.get("topic_portal")).click();
		return new Link(properties.get("library_holdings_portal")).isPresent()
				&& new Link(properties.get("external_url_portal")).isPresent()
				&& new Link(properties.get("internal_library_potal")).isPresent()
				&& new Link(properties.get("jstor_portal")).isPresent();
	}
}
