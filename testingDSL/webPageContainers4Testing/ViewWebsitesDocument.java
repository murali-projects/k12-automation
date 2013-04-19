package webPageContainers4Testing;

import webPageElements4Testing.TextLabel;

public class ViewWebsitesDocument extends BasePageContainer{

	public ViewWebsitesDocument() throws Exception {
		super();
	}

	public boolean verifyElementPresent(String element){
		return new TextLabel(properties.get(element)).isPresent();
	}
}
