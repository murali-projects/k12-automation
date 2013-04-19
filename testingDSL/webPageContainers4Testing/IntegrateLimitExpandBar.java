package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;

public class IntegrateLimitExpandBar extends BasePageContainer {

	public IntegrateLimitExpandBar() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyLimitSearchNotPresentInHomepage() throws Exception{
		return (!new PageElementWithIdAttribute(properties.get("Limit_DocumentType"))
		.isPresent());	
		
	}
	public boolean verifyLimitSearchNotPresentInDetailPage()throws Exception{
		new Link(properties.get("news_link")).click();		
		return (!new PageElementWithIdAttribute(properties.get("Limit_DocumentType"))
		.isPresent());
		
	}
	
	public boolean verifyDisplaygroupsPresentAlongWithLimitSearch() throws Exception{
		
		new Link(properties.get("view_all")).click();		
		return (new PageElementWithIdAttribute(properties.get("Limit_displayGroup"))
		.isPresent());
	}
	
	public boolean verifyViewAllResultsPresentAlongWithLimitSearch() throws Exception{
		
		new Link(properties.get("view_all")).click();		
		return (new PageElementWithIdAttribute(properties.get("limit_view_results"))
		.isPresent());
	}
	
	public boolean verifyBasicSearchPresentAlongWithLimitSearch() throws Exception{
		
		new Link(properties.get("view_all")).click();		
		return (new PageElementWithIdAttribute(properties.get("search_box"))
		.isPresent());
	}
	
	
	public boolean verifyLimitSearchPortletPresent() throws Exception{
		new Link(properties.get("view_all")).click();		
		return (new PageElementWithIdAttribute(properties.get("limit_index_browse"))
		.isPresent());
	}
	
	public boolean verifyLimitBydocumentIsPresent() throws Exception{
		new Link(properties.get("view_all")).click();		
		return (new PageElementWithIdAttribute(properties.get("Limit_DocumentType"))
		.isPresent());
		
	}
	
	
	public boolean verifyMoreDisplayed() throws Exception{
		new Link(properties.get("view_all")).click();
		int count=new TextLabel(properties.get("limit_terms_count")).getXpathCount();
		if(count>5){
			return(new PageElementWithIdAttribute(properties.get("limit_viewmore"))
			.isPresent());
		}return (!new PageElementWithIdAttribute(properties.get("limit_viewmore"))
		.isPresent());
	}
	
	public boolean verifyLessLink() throws Exception{
		new Link(properties.get("view_all")).click();
		int count=new TextLabel(properties.get("limit_terms_count")).getXpathCount();
		if(count>5){
			new Link(properties.get("limit_viewmore")).clickWithoutWait();
			return(new PageElementWithIdAttribute(properties.get("limit_viewLess"))
			.isPresent());
		}return (!new PageElementWithIdAttribute(properties.get("limit_viewmore"))
		.isPresent());
		
	}
	public boolean verifyLessLinkDisplaying5Items() throws Exception{
		new Link(properties.get("view_all")).click();
		int count=new TextLabel(properties.get("limit_terms_count")).getXpathCount();
		if(count>5){
			new Link(properties.get("limit_viewmore")).clickWithoutWait();
			new Link(properties.get("limit_viewLess")).clickWithoutWait();
			int newcount=new TextLabel(properties.get("limit_terms_count")).getXpathCount();
			if(newcount>5)
				return newcount==count;
		}return (!new PageElementWithIdAttribute(properties.get("limit_viewmore"))
		.isPresent());
	}
	
	public boolean verifyClickingOnTermsNavigatesPage() throws Exception{
		new Link(properties.get("view_all")).click();
		String label=new TextLabel(properties.get("limit_searchby_text")).getLabelText();
		new Link(properties.get("limit_search_firstelement")).click();
		return(new TextLabel(properties.get("limit_searchby_text")).getLabelText().contains(label));
	}
	
	
	
	
}
