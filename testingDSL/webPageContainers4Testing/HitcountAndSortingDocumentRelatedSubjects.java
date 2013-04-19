package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class HitcountAndSortingDocumentRelatedSubjects extends
		BasePageContainer {

	public HitcountAndSortingDocumentRelatedSubjects() throws Exception {
		super();

	}

	public boolean verifyQuickSearchSidebarisPresent() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
	
		if (new PageElementWithIdAttribute(properties.get("sidebar"))
				.isPresent())
			return true;
		return false;

	}
	

	public boolean verifySortingOfRelatedSubjectTerms() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
	
			String fullText=new TextLabel(properties.get("RelateedSubjectsText")).getLabelText();
			String[] hitcount=getNumbers(fullText);
			
		if(Integer.parseInt(hitcount[1])>Integer.parseInt(hitcount[2]))
		return true;
				return false;
	}
	
	
	

	public boolean verifyHitcountForAllDisplayGroups() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		if (new PageElementWithIdAttribute(properties.get("sidebar"))
				.isPresent())
			return true;
		return false;

	}

	public boolean verifyOnlyFiveLinksDisplayedInRelatedResults()
			throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 7)
			return false;
		return true;
	}

	public boolean verifyMoreLinkForMoreThanFiveResults() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 6) {
			if (new PageElementWithIdAttribute(properties.get("More"))
					.isPresent())
				return true;

		}
		return false;
	}

	public boolean verifyAccesibilityOfMoreLink() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 5) {
			new Link(properties.get("More")).clickWithoutWait();
		}
		return true;
	}

	public boolean verifySortingOrderAfterClickingMore() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 5) {
			new Link(properties.get("More")).clickWithoutWait();
			Thread.sleep(1000);
			String fullText=new TextLabel(properties.get("RelateedSubjectsText")).getLabelText();
			String[] hitcount=getNumbers(fullText);
			
		if(Integer.parseInt(hitcount[1])>Integer.parseInt(hitcount[2]))
		return true;
		
		
			}
		return true;
	}

	public boolean verifyFunctionalityOfLessLink() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 5) {
			new Link(properties.get("More")).clickWithoutWait();
			Thread.sleep(1000);
			new Link(properties.get("Less")).clickWithoutWait();
		
			return true;
		}
		return true;

	}

	public boolean verifyDescendingAfterLessLink() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();

		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count > 5) {
			new Link(properties.get("More")).clickWithoutWait();
			Thread.sleep(10000);
			new Link(properties.get("Less")).clickWithoutWait();
			Thread.sleep(10000);
			String fullText=new TextLabel(properties.get("RelateedSubjectsText")).getLabelText();
			String[] hitcount=getNumbers(fullText);
					if(Integer.parseInt(hitcount[1])>Integer.parseInt(hitcount[2]))
		return true;
			}
				
		return true;
	}

	public boolean verifyNavigationthroughRelatedSubjectTerm() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		new Link(properties.get("related_subject_term")).click();
		
		if (new PageElementWithIdAttribute(properties.get("news_display"))
		.isPresent())
	return true;
return false;
		
		
	
	}

  
	public boolean verifyHitcountByaddingAllDispalyGroups() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		Thread.sleep(1000);
		String fullText=new TextLabel(properties.get("RelateedSubjectsText")).getLabelText();
		String[] hitcount=getNumbers(fullText);
		new Link(properties.get("related_subject_term")).click();
		int total1=Integer.parseInt(hitcount[0].replace(",", ""));
		int countFromDB = Integer.parseInt(OceanDatabaseReadFile.readRelatedSubjectsCountFromFile(properties.get("subject"), properties.get("all"),properties.get("none"), properties.get("relevance"),"\"Law enforcement\"").get(0));
		                                                      
		if(total1==countFromDB)
				return true;
		return false;
	}

	public boolean verifyViewingOtherSubjectsByNavigation() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		new Link(properties.get("related_subject_term")).click();
		
		if (new PageElementWithIdAttribute(properties.get("news_display"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("reference_Link"))
		.isPresent())
	return true;
	return false;	
		}

	public boolean verifyNoMoreLinkForLessThanFiveResults() throws Exception {
		new Link(properties.get("view_all")).click();
		new Link(properties.get("detailed_link")).click();
		int count = new TextLabel(properties.get("relatedsubjectstab"))
				.getXpathCount();
		if (count < 5) {			
			if (new PageElementWithIdAttribute(properties.get("Less"))
			.isPresent())
		return false;
	return true;
			
		}
		return true;
	}

		
	
	public static String[] getNumbers(String text) {
		String[] num = new String[30];
		int index = 0;
		while (text.indexOf("(")!=-1) {
			int start = text.indexOf("(");
			int end = text.indexOf(")");
			String abc = text.substring(start + 1, end);
			num[index] = text.substring(start + 1, end);
			index++;
			text = text.substring(end + 1);
		}
		return num;

	}

	
	

}
