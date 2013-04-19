package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class IntegrateWebsitesFavIconsOnViewAllPage extends BasePageContainer{

	public IntegrateWebsitesFavIconsOnViewAllPage() throws Exception {
		super();
	}
public boolean verifyfaviconsOnViewAllPage()throws Exception{
		new Link(properties.get("view_all_news")).click();
		return(new TextLabel(properties.get("favicons")).isPresent());
	}
	
	public boolean verifyfaviconsNotPresentInSearchResultsPage(){
		return(!new TextLabel(properties.get("favicons")).isPresent());
	}
	
	public boolean verifyFavIconsForAllDisplayGroupsinViewAllpage() throws Exception{
		new Link(properties.get("view_all_news")).click();
		if(new TextLabel(properties.get("favicons")).isPresent()){
			new Link(properties.get("search_within_display_tabs")).click();
			if(new TextLabel(properties.get("favicons")).isPresent()){
				new Link(properties.get("search_within_displaytab")).click();
				if(new TextLabel(properties.get("favicons")).isPresent()){
					return true;
					
				}
				
			}
		}
	return false;
	}
	
	public boolean verifyFaviconsOnLeftsideOfRecord() throws Exception{
		new Link(properties.get("view_all_news")).click();
		return(new TextLabel(properties.get("favicon_record_left")).isPresent());
	}
	
	public boolean verifyDisplayOfGenericIcon()throws Exception{
		new Link(properties.get("view_all_news")).click();
		return(new TextLabel(properties.get("generic_icon")).isPresent());
	}
	
	
	
}
