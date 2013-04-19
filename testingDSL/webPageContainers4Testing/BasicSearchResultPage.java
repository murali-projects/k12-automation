package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class BasicSearchResultPage extends BasePageContainer {
	private Link nextLink;
	private Link prevLink;
	private String linkText;
	private String searchKeyword = "obama";

	public BasicSearchResultPage() throws Exception {
		nextLink = new Link(properties.get("nextLink"));
		prevLink = new Link(properties.get("previousLink"));
		linkText = "Xpath_required_link_Text";
	}

	
	public Link getNextLink() {
		return nextLink;
	}

	public Link getPrevLink() {
		return prevLink;
	}

	public void doClikOnNextLink(String LinkAddress) {
		nextLink.click(LinkAddress);

	}

	public void doClikOnPrevLink(String LinkAddress) {
		prevLink.click(LinkAddress);
	}

	public boolean doCheckNextLink() {
		int noOfLinks = Integer.parseInt(linkText.substring(linkText
				.indexOf("("), linkText.indexOf("")));
		if (noOfLinks > 10 && nextLink.isPresent()) {
			return true;
		} else if (noOfLinks <= 10 && !nextLink.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean doCheckNextLinkWorking() throws InterruptedException {
		nextLink.click();
		return (prevLink.isPresent()) ? true : false;
	}

	public boolean doCheckResultSearchWordBold() throws Exception {
		String resultText = new TextLabel(properties.get("boldText_Xpath")).getLabelText();
		return (resultText.equals(searchKeyword)) ? true : false;
	}

}