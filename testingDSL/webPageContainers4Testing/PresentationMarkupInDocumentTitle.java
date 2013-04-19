package webPageContainers4Testing;

import webPageElements4Testing.TextLabel;

public class PresentationMarkupInDocumentTitle extends BasePageContainer {

	public PresentationMarkupInDocumentTitle() throws Exception {
		super();
	}

	public boolean verifyDocumentTitleIsBold(String displayName) throws Exception {

		int noOfDocumentTitles = new TextLabel(properties
				.get(displayName+"_searchResults_xpath")).getXpathCount();
		for (int i = 1; i <= noOfDocumentTitles; i++) {
			if (!(new TextLabel(properties.get(displayName+"_searchResults_xpath")+"["+i+"]"+"/h3").isPresent())) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyDocumentTitleIsHyperLinked(String displayName) throws Exception {
		int noOfDocumentTitles = new TextLabel(properties
				.get(displayName+"_searchResults_xpath")).getXpathCount();
		for (int i = 1; i <= noOfDocumentTitles; i++) {
			if (!(new TextLabel(properties.get(displayName+"_searchResults_xpath")+"["+i+"]"+"/h3/a").isPresent())) {
				return false;
			}
		}
		return true;
	}
}
