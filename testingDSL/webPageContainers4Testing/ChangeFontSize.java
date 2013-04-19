package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextLabel;

public class ChangeFontSize extends BasePageContainer{

	public ChangeFontSize() throws Exception {
		super();
		}
    
	public boolean verifyChangeFontSizeIsPresent() throws InterruptedException{
		new Link(properties.get("K12-Reference_searchResults_xpath")+"[1]/h3/a").click();
		return (new TextLabel(properties.get("Larger_FontSize")).isPresent()
				&&
				new TextLabel(properties.get("Smaller_FontSize")).isPresent()	
				);
		 
	}

	public boolean verifyLargerSizeIsWorking() {
		new PageButton(properties.get("Larger_FontSize")).click();
		String fontSize = new TextLabel(properties.get("fontSizeXpath")).getAttribute("style");
		return(fontSize.contains("16.32"));
		}

	public boolean verifySmallerSizeIsWorking() {
		new PageButton(properties.get("Smaller_FontSize")).click();
		String fontSize = new TextLabel(properties.get("fontSizeXpath")).getAttribute("style");
		return(fontSize.contains("13.6"));
	}

	public boolean verifyFontSizeOptionsInDetailedNavigation() throws InterruptedException {
		new Link(properties.get("reference_viewAll")).click();
		new Link(properties.get("viewAll_searchResults")+"[1]/td[2]/h3/a").click();
		return (verifyLargerSizeIsWorking()&& verifySmallerSizeIsWorking());
	}
	
	
	
}
