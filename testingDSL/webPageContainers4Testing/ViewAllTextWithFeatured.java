package webPageContainers4Testing;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllTextWithFeatured extends BasePageContainer{

	List<String> contents = new ArrayList<String>(); 
	List<String> contentsInViewAll= new ArrayList<String>();  
	public ViewAllTextWithFeatured() throws Exception {
		super();
		}
	 public boolean verifyViewAllPageNavigated(String displayGroup) throws InterruptedException{
		 new Link(properties.get("topicsrelateddocument")).click();
		 new Link(properties.get("portal_"+displayGroup+"_viewall")).click();
		 String titleOfTheDisplayGroup= new TextLabel(properties.get("portal_searchresults_title")).getLabelText();
		 return (titleOfTheDisplayGroup.equals(displayGroup));
	 }

	public boolean verifyViewAllPage(String displayGroup) throws InterruptedException {
       new Link(properties.get("topicsrelateddocument")).click();
       int count= new Link(properties.get("portal_"+displayGroup+"_count")).getLinkCount();
       for(int i =1; i<=count;i++){
       contents.add(new TextLabel(properties.get("portal_"+displayGroup+"_count")+"["+i+"]/h3/a").getLabelText());
       }
       new Link(properties.get("portal_"+displayGroup+"_viewall")).click();
       int countInviewall = new Link(properties.get("portal_searchresults_count")).getLinkCount();
       for(int i=1; i<=countInviewall;i++){
       contentsInViewAll.add(new TextLabel(properties.get("portal_searchresults")+i+"]/td[2]/h3/a").getLabelText());
       }
       return compare(contents,contentsInViewAll);
	}
    
	public boolean compare(List<String> list1, List<String> list2){
		for(int i= 0; i<list1.size(); i++){
			if (!list1.get(i).equals(list2.get(i)))
             return false;		
		}
	return true;
	}
	public boolean verifyChangeSortToPubDate() throws Exception {
		new DropDownBox(properties.get("sort_by_xpath")).select("Date");
		Thread.sleep(5000);
		return verifyPublicationDatesAreInDescOrder();
		
	}
	
	private List<String> getPublicationDates() throws Exception{
		List<String> publicationDatesList = new ArrayList<String>();
		Thread.sleep(5000);		
		int noOfResults = new TextLabel(properties.get("publication_date")).getXpathCount();
		for(int i = 1; i <= noOfResults; i++){
			publicationDatesList.add(new TextLabel(properties.get("publication_date")+ "[" + i + "]" + "/td[2]/span[2]").getLabelText());
		}
		return publicationDatesList;
	}
	
	@SuppressWarnings("deprecation")
	private boolean verifyPublicationDatesAreInDescOrder( ) throws Exception{
		List<String> publicationDatesInAppl = getPublicationDates();
		for (int i = 0; i < publicationDatesInAppl.size()-1; i++) {
				String[] date1 = publicationDatesInAppl.get(i).split("-");
				String[] date2 = publicationDatesInAppl.get(i+1).split("-");
				if(!((publicationDatesInAppl.get(i).equals(publicationDatesInAppl.get(i+1))) ||
				(new Date(date1[1]+"/"+date1[2]+"/"+date1[0]).after(new Date(date2[1]+"/"+date2[2]+"/"+date2[0])))
					))
					return false;
				
		}
			return true;
	}
	
	public boolean verifyPagination() throws InterruptedException {
		int totalCount = Integer.parseInt(new TextLabel(properties.get("totalResults")).getLabelText().replace(",", ""));
		if (totalCount > 20)
			new Link(properties.get("searchResults_nextlink")).click();
		if (Integer.parseInt(new TextLabel(properties.get("currentResults"))
						.getLabelText().split("-")[0].trim()) > 20)
		         new Link(properties.get("previous")).click();
		if (Integer.parseInt(new TextLabel(properties.get("currentResults"))
		.getLabelText().split("-")[0].trim()) < 20)
			return true;
		else
			return (totalCount < 20);
	}
	public boolean verifyHandpickedItems() throws InterruptedException {
		new DropDownBox(properties.get("sort_by_xpath")).select("Relevance");
		Thread.sleep(2000);
		for(int i=0; i<contents.size();i++){
			if(contents.get(i).equals(contentsInViewAll.equals(i)))
				return false;
		}
	
		return true;
	}
	public boolean verifyWhenBackToPortal(String displayGroup) {
	for(int i=1; i<5;i++){
		new Url().goBackToPreviousPage();
	}
	List <String> contentsBackFromViewAll = new ArrayList<String>();
	int count= new Link(properties.get("portal_"+displayGroup+"_count")).getLinkCount();
    for(int i =1; i<=count;i++){
    	contentsBackFromViewAll.add(new TextLabel(properties.get("portal_"+displayGroup+"_count")+"["+i+"]/h3/a").getLabelText());
    }
		return compare(contents,contentsBackFromViewAll);
	}

}
	
	



	
	

