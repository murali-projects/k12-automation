package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class AccessToolsForWrappingItUp extends BasePageContainer{

	public AccessToolsForWrappingItUp() throws Exception {
		super();
	}
	
	public boolean verifyResourcesLinkIsAccessible() throws InterruptedException{
		new Link(properties.get("resources_link")).click();
		return new Link(properties.get("resources_tabs")).isPresent();
	}
	
	public boolean checkDetailsDisplayedInWrappingItUp() throws InterruptedException{
		new Link(properties.get("wrappingUp_link")).click();
		return new TextLabel(properties.get("citeASource")).isPresent()
			&& new TextLabel(properties.get("organiseReports")).isPresent()
			&& new TextLabel(properties.get("buildArgument")).isPresent()
			&& new TextLabel(properties.get("writeConclusion")).isPresent()
			&& new TextLabel(properties.get("writeThesis")).isPresent()
			&& new TextLabel(properties.get("createVisual")).isPresent()
			&& new TextLabel(properties.get("footnote")).isPresent();
	}

	public boolean verifyBreadcrumbInResourcesPage(){
		return new TextLabel(properties.get("breadcrumb_list") + "/a").getLabelText().equals("Home")
				&& new TextLabel(properties.get("breadcrumb_list") + "[2]/a").getLabelText().equals("Resources");
	}
	
	public boolean verifyWrappingUpLinksOpensInNewWindow() throws InterruptedException{
		String[] windowTitles = selenium.getAllWindowTitles();
		new Link(properties.get("createVisual")).click();
		return selenium.getAllWindowTitles().length == windowTitles.length + 1;
	}
	
	public boolean verifyUrlOfNewWindow(){
		String[] windowTitles = selenium.getAllWindowTitles();
		selenium.selectWindow(windowTitles[windowTitles.length-1]);
		selenium.windowFocus();
		String windowUrl = new Url().getUrl();
		selenium.close();
		selenium.selectWindow("");
		return windowUrl.contains("galeschools.com");
	}
	
	public boolean verifyOtherToolsArePresent() {
		return (new TextLabel(properties.get("guided_tour_label")).isPresent()
				&& new TextLabel(properties.get("homework_helper_label")).isPresent()
				&& new TextLabel(properties.get("searchTips_link")).isPresent()
				&& new TextLabel(properties.get("submitFeedback_link")).isPresent()
				&& new TextLabel(properties.get("researchGuide_link")).isPresent()
				&& new TextLabel(properties.get("shareYourWork_link")).isPresent());
	}
	
	public boolean verifyFooterLinksAnBasicSearchAccessible() throws InterruptedException, Exception{
		return verifyFooterLinks() && verifyBasicSearchAccessible();
	}
	
	private boolean verifyFooterLinks() throws InterruptedException{
		new Link(properties.get("termsOfUse_link")).clickWithoutWait();
		Thread.sleep(2000);
		String[] windowsAfterClickingLink = selenium.getAllWindowNames();
		selenium.selectWindow(windowsAfterClickingLink[windowsAfterClickingLink.length-1]);
		selenium.windowFocus();
		boolean verifyWindowUrl = new Url().getUrl().contains(properties.get("termsOfUse_url"));
		selenium.close();
		selenium.selectWindow("");
		return  verifyWindowUrl;
	}
	
	private boolean verifyBasicSearchAccessible() throws Exception{
		new TextBox(properties.get("searchBox")).type("war");
		new PageButton(properties.get("findButton")).clickAndWait();
		return new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText().equals(properties.get("searchResults_msg") + DisplayQueryFormatting.KEYWORD + "war");
	}
	
}
