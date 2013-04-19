package webPageContainers4Testing;

import webPageElements4Testing.Image;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class LinkToRelatedSubjects extends BasePageContainer{
private String SearchString="radio";
	public LinkToRelatedSubjects() throws Exception {
		super();
		
	}
	public boolean verifyIntegrateRelatedTopicsPresent() throws Exception{
		new Link(properties.get("news_link")).click();
		return (new PageElementWithIdAttribute(properties.get("RelateedSubjectsText"))
		.isPresent());
	}
	
	public boolean verifyTermsDisplayedInRelatedTopics() throws Exception{
		new Link(properties.get("news_link")).click();
		return (new PageElementWithIdAttribute(properties.get("relatedsubjectstab"))
		.isPresent());
		
	}
	
	public boolean verifyRelatedTopicsFromViewAll() throws Exception{
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		return (new PageElementWithIdAttribute(properties.get("RelateedSubjectsText"))
		.isPresent());
	}
	
	public boolean verifyPageNavigationByClickingSubjects() throws Exception{
		new Link(properties.get("news_link")).click();
		String subject=new TextLabel(properties.get("related_subject_term")).getLabelText();
		new Link(properties.get("related_subject_term")).click();
		String text=new TextLabel(properties.get("related_subject_msg")).getLabelText();
		if(text.contains(subject)) {
			return true;
		}
		return false;
	}
}
