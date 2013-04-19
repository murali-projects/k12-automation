package webPageContainers4Testing.dev;

import webPageElements4Testing.TextLabel;


public class ListViewPortlet extends ViewSearchResultPortlet{

	public ListViewPortlet(String displayGroupName) throws Exception {
		super(displayGroupName, displayGroupName+"_searchResults_xpath",
				displayGroupName+"_doctitle_xpath");
		
	}
	
	public boolean isContentPresent(String content) throws Exception{
		String wordsToKnow = properties.get(content);
		
		TextLabel documentTitle = new TextLabel(wordsToKnow);		
		String detailPagetitle = documentTitle.getLabelText();
		
		return detailPagetitle != null? true : false;
	}
}


