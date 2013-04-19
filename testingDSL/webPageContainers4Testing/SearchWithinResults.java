package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class SearchWithinResults extends BasePageContainer {

	public SearchWithinResults() throws Exception {
		super();
	}
	
	public boolean verifySearchBoxInSidebarInViewAllPage() throws Exception{
		new Link(properties.get("view_all_news")).click();
		return (new TextLabel(properties.get("search_within_results")).isPresent());		
	}
	
	public boolean canUserEnterDataInSearchBox() throws Exception{
		new Link(properties.get("view_all_news")).click();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));
		return true;
	}
	
	
	
	public boolean verifyFullTextOnSearchTermsDisplayed() throws Exception{
		new Link(properties.get("view_all_news")).click();
		String basic_count=new TextLabel(properties.get("search_within_total_count")).getLabelText();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));
		
        new PageButton(properties.get("search_within_submit")).clickAndWait();
        
        String results_count=new TextLabel(properties.get("search_within_total_count")).getLabelText();
        return(!basic_count.equals(results_count));
	}
	
	public boolean verifyUserInViewAllAfterResultsSearch() throws Exception{
		new Link(properties.get("view_all_news")).click();
		String label=new TextLabel(properties.get("search_within_limit")).getLabelText();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));
		
        new PageButton(properties.get("search_within_submit")).clickAndWait();
    
        return(new TextLabel(properties.get("search_within_limit")).getLabelText().equalsIgnoreCase(label));
	}
	
	public boolean verifySearchOptionForOtherTabs() throws Exception{
		new Link(properties.get("view_all_news")).click();
        if(new TextLabel(properties.get("search_within_display_tabs")).isPresent())
        	new Link(properties.get("search_within_display_tabs")).click();
            if(new TextLabel(properties.get("search_within_displaytab")).isPresent())
            	new Link(properties.get("search_within_displaytab")).click();
                  return(new TextLabel(properties.get("search_within_results")).isPresent());
	}
	
     
	public boolean verifyHitcountAfterLimitingResults() throws Exception{
		new Link(properties.get("view_all_news")).click();
		String basic_count=new TextLabel(properties.get("search_within_total_count")).getLabelText();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));
		
        new PageButton(properties.get("search_within_submit")).clickAndWait();
        
        String results_count=new TextLabel(properties.get("search_within_total_count")).getLabelText();
        return(!basic_count.equals(results_count));
	}
	
	public boolean verifySearchLimitedAcrossOtherTabs() throws Exception{
		new Link(properties.get("view_all_news")).click();
		String firstdisplaygroup=new TextLabel(properties.get("search_within_display_tabs")).getLabelText();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));
	
        new PageButton(properties.get("search_within_submit")).clickAndWait();
       
        String firstdisplaygroupAfter=new TextLabel(properties.get("search_within_display_tabs")).getLabelText();
		return (!firstdisplaygroup.equalsIgnoreCase(firstdisplaygroupAfter));
	}
	
	public boolean verifyLimiterModifiedByWithinSearch()throws Exception{
		new Link(properties.get("view_all_news")).click();
		String limiterbefore=new TextLabel(properties.get("search_within_limitsearch_limiter")).getLabelText();
		new TextBox(properties.get("search_within_results")).type(properties.get("search_within_searchterm"));

        new PageButton(properties.get("search_within_submit")).clickAndWait();
   
        String limiterafter=new TextLabel(properties.get("search_within_limitsearch_limiter")).getLabelText();
        return (!limiterbefore.equalsIgnoreCase(limiterafter));
	}
	
	
}
