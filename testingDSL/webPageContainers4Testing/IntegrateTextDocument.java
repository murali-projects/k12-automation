package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;


public class IntegrateTextDocument extends BasePageContainer{

	public IntegrateTextDocument() throws Exception {
		super();
		
	}
	
	public boolean verifyDocumentDisplay(String dispalyGroup) throws Exception{
		String expectedTitle = new TextLabel("//div[@id='"+dispalyGroup+"']/div/ul/li[1]/h3/a").getLabelText();
		new Link("//div[@id='"+dispalyGroup+"']/div/ul/li[1]/h3/a").click();
		String titleInDetailedPage = new TextLabel(properties.get("titleXpath")).getLabelText();
		return titleInDetailedPage.contains(expectedTitle.substring(0,expectedTitle.length()-1));
		}

	public boolean verifyDocumentNoDisplay() throws Exception{
		return (new TextLabel(properties.get("gale_document")).isPresent());
	}
	
	
}
