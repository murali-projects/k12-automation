package webPageContainers4Testing;

import java.util.List;
import java.util.ArrayList;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class AccessResearchGuide extends BasePageContainer {

	public AccessResearchGuide() throws Exception {
		super();
	}

	public boolean verifyResearchToolsOption() throws InterruptedException {
		return new TextLabel(properties.get("research_tools")).isPresent();
	}

	public boolean verifyResearchToolsPage() throws InterruptedException {
		new Link(properties.get("research_tools")).click();
		return new TextLabel(properties.get("tools_tabs")).isPresent();
	}

	public boolean verifyResearchGuideTab() throws InterruptedException {
		new Link(properties.get("research_guide_tab")).clickWithoutWait();
		return new TextLabel(properties.get("research_guide")).isPresent();
	}

	public boolean verifyResearchGuideLinks(String linksInResearchGuide) {
		String[] linksUnderResearch = linksInResearchGuide.split(",");
		List<String> linksDisplayed = getDisplayedLinksUnderResearchGuide();
		for (int i = 0; i < linksUnderResearch.length; i++) {
			if (!(linksUnderResearch[i].equals(linksDisplayed.get(i)))) {
				return false;
			}
		}
		return true;
	}

	private List<String> getDisplayedLinksUnderResearchGuide() {
		List<String> linksUnderResearchGuide = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties
				.get("research_guide_content")).getXpathCount(); i++) {
			linksUnderResearchGuide.add(new TextLabel(properties
					.get("research_guide_content")
					+ "[" + i + "]/a").getLabelText());
		}
		return linksUnderResearchGuide;
	}

	//Functionality Not Yet implemented
	public boolean verifyEachLinkAccessbility() throws InterruptedException {
		List<String> linksDisplayed = getDisplayedLinksUnderResearchGuide();
		for (int i = 0; i <= linksDisplayed.size(); i++) {
			new Link(properties.get("")).click();
			if (!new TextLabel(properties.get("")).isPresent()) {
				return false;
			}
		}
		return true;
	}
	
	public void navigateToHomeTab() throws InterruptedException{
		new Link(properties.get("home_tab")).click();
	}
}
