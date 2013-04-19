package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class LinksWithinDocumentBody extends BasePageContainer{

	String[] windowIds;
	public LinksWithinDocumentBody() throws Exception {
		super();
	}
	
	public boolean verifyContentHasHyperlinks(){
		return new TextLabel(properties.get("")).isPresent();
	}
	
	public boolean verifyHyperlinksAreAccessible() throws InterruptedException{
		windowIds = selenium.getAllWindowIds();
		String url = new Url().getUrl();
		new Link(properties.get("document_description_detailedPage")+"/a").click();
		return !new Url().getUrl().equals(url);
	}
	
	public boolean verifyUserIsInSameWindow(){
		return selenium.getAllWindowIds().length == windowIds.length;
	}
	
	public boolean verifyLinkColorChanged(){
		new Url().goBackToPreviousPage();
		return new TextLabel(properties.get("document_description_detailedPage")+"/a").getAttribute("color").equals("red");
	}
	
	public boolean verifyOtherHyperlinksAreAccessible() throws InterruptedException{
		windowIds = selenium.getAllWindowIds();
		String url = new Url().getUrl();
		new Link(properties.get("document_description_detailedPage")+"/a[2]").click();
		return !new Url().getUrl().equals(url);
	}

}
