package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllWebsitesWithFeatured extends BasePageContainer{

	public List<String> documentTitlesDisplayedList;
	public List<String> documentTitlesViewallList;
	
	public ViewAllWebsitesWithFeatured() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
   	
	public boolean verifyDefaultsortRelevanceInViewall()throws Exception{
		new Link(properties.get("topic_inside_portlet")).click();
		new Link(properties.get("portal_websites_view_all")).click();
		String option=new DropDownBox(properties.get("portal_viewall_sort_order")).getSelectedOptionLabel();
		return(option.equalsIgnoreCase("Relevance"));
		
	}
	
	
	public boolean verifyFeaturedwebsitesAppearOntheTopOfList()throws Exception{
		//new Link(properties.get("topic_inside_portlet")).click();
		//int documentTitles = new TextLabel(properties.get("portal_websites_featured_list")).getXpathCount();
		/*for (int i = 1; i <= documentTitles; i++) {
			documentTitlesDisplayedList.add(new TextLabel(properties
					.get("portal_websites_featured_list")
					+ "/li"+"[" + i + "]" + "/h3").getLabelText());
		}
		new Link(properties.get("portal_websites_view_all")).click();
		for (int i = 1; i < 3; i++) {
			documentTitlesViewallList.add(new TextLabel(properties
					.get("portal_websites_view_all_list")
					+ "[" + i + "]" + "/td[2]/h3/a").getLabelText());

		}
		for (int i = 0; i < documentTitlesDisplayedList.size(); i++) {
			for (int j = 0; j < documentTitlesViewallList.size(); j++) {
				if (documentTitlesDisplayedList.get(i).equalsIgnoreCase(
						documentTitlesViewallList.get(j))) {
					j++;
					return false;
				}
				continue;
			}
		}*/
		new Link(properties.get("topic_inside_portlet")).click();
		String element= new TextLabel(properties.get("portal_searchresults_websites")).getLabelText();
		new Link(properties.get("portal_websites_view_all")).click();
		new DropDownBox(properties.get("portal_viewall_sort_order")).select("label=Document Title");
		Thread.sleep(1000);
		String newelemnet= new TextLabel(properties.get("portal_viewall_link")).getLabelText();
		return true;
	}
	
public boolean verifyFeaturedItemsAfterChangingSort() throws Exception{
	new Link(properties.get("topic_inside_portlet")).click();
	new Link(properties.get("portal_websites_view_all")).click();
	String element= new TextLabel(properties.get("portal_websites_veiw_all_title")).getLabelText();
	new DropDownBox(properties.get("portal_viewall_sort_order")).select("label=Document Title");
	Thread.sleep(4000);
	String newelemnet= new TextLabel(properties.get("portal_websites_veiw_all_title")).getLabelText();
	return (!element.equalsIgnoreCase(newelemnet));
	
}


public boolean verifyClickingfeatureItemnavigatesToDocumentPage() throws Exception{
	Thread.sleep(1000);
	new Link(properties.get("topic_inside_portlet")).click();
	new Link(properties.get("portal_websites_featured_title")).click();
return(!new PageElementWithIdAttribute(properties.get("portal_websites_featured_title"))
	.isPresent());
	
}
	
	
public boolean verifyFeaturedWebsitesNotpresentInSearchResultsPageBucket() throws Exception{
	new Link(properties.get("topic_inside_portlet")).click();
	String element= new TextLabel(properties.get("portal_websites_featured_title")).getLabelText();
	new Link(properties.get("portal_breadcrumb_searchresults")).click();
	String newelemnet= new TextLabel(properties.get("portal_searchresults_websites")).getLabelText();
	return (!element.equalsIgnoreCase(newelemnet));
}	
	

public boolean verifyEditorpickBucketItemwithFeaturedBucketItem() throws Exception{
	new Link(properties.get("topic_inside_portlet")).click();
	String editorElement= new TextLabel(properties.get("portal_handpicked_list_item")).getLabelText();
	String FeaturedElement=new TextLabel(properties.get("portal_websites_featured_title")).getLabelText();
	
	return(!editorElement.equalsIgnoreCase(FeaturedElement));
}
	
	
	
}
