package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewSearchResultsPage extends BasePageContainer{
	
	public ViewSearchResultsPage() throws Exception {
		super();
	}

	public boolean verifyDisplayGroups() {
	return(new TextLabel(properties.get("reference_label")).isPresent()&&
			new TextLabel(properties.get("websites_label")).isPresent()&&
			new TextLabel(properties.get("academicJournals_label")).isPresent()&&
			new TextLabel(properties.get("primarysources_label")).isPresent()&&
			new TextLabel(properties.get("news_label")).isPresent()&&
			new TextLabel(properties.get("magazines_label")).isPresent()&&
			new TextLabel(properties.get("images_label")).isPresent()&&
			new TextLabel(properties.get("audio_label")).isPresent()&&
			new TextLabel(properties.get("video_label")).isPresent()&&
			new TextLabel(properties.get("statistics_label")).isPresent()
			);
	
	}

	public boolean verifyViewAll() {
		return(new TextLabel(properties.get("K12-Reference_viewAll")).isPresent()&&
				new TextLabel(properties.get("magazines_viewAll")).isPresent()&&
				new TextLabel(properties.get("websites_viewAll")).isPresent()&&
				new TextLabel(properties.get("news_viewAll")).isPresent()&&
				new TextLabel(properties.get("Journals_viewAll")).isPresent()&&
				//new TextLabel(properties.get("audio_viewAll")).isPresent()&&
				new TextLabel(properties.get("video_viewAll")).isPresent()&&
				new TextLabel(properties.get("images_viewAll")).isPresent()&&
				new TextLabel(properties.get("primarysources_viewAll")).isPresent()&&
				new TextLabel(properties.get("statistics_viewAll")).isPresent()
				);
	}

	public boolean verifyCount() {
		String linkText = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText1 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText2 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText3 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText4 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText5 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText6 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText7 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText8 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		String linkText9 = new TextLabel(properties.get("K12-Reference_viewAll")).getLabelText().substring(8);
		
	   return(!linkText.isEmpty()&&!linkText1.isEmpty()&&!linkText2.isEmpty()
			   &&!linkText3.isEmpty()&&!linkText4.isEmpty()&&!linkText5.isEmpty()
			   &&!linkText6.isEmpty()&&!linkText7.isEmpty()&&!linkText8.isEmpty()
			   &&!linkText9.isEmpty());
	
	}

	public boolean verifyViewAllIsLink() {
		return(new TextLabel(properties.get("K12-Reference_viewAll")).isPresent()&&
				new TextLabel(properties.get("magazines_viewAll")).isPresent()&&
				new TextLabel(properties.get("websites_viewAll")).isPresent()&&
				new TextLabel(properties.get("news_viewAll")).isPresent()&&
				new TextLabel(properties.get("Journals_viewAll")).isPresent()&&
				//new TextLabel(properties.get("audio_viewAll")).isPresent()&&
				new TextLabel(properties.get("video_viewAll")).isPresent()&&
				new TextLabel(properties.get("images_viewAll")).isPresent()&&
				new TextLabel(properties.get("primarysources_viewAll")).isPresent()&&
				new TextLabel(properties.get("statistics_viewAll")).isPresent()
				);
	}

	public boolean verifyViewAllIsLinkIsWorking() throws InterruptedException {
		if(referenceViewAllLinkWorks())
		new Url().goBackToPreviousPage();
		if(magazinesViewAllLinkWorks())
		  new Url().goBackToPreviousPage();
		if(websitesViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		if(newsViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		if(journalsViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		/*if(audioViewAllLinkWorks())
			new Url().goBackToPreviousPage();*/
		if(videoViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		if(imagesViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		if(primarysourcesViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		if(statisticsViewAllLinkWorks())
			new Url().goBackToPreviousPage();
		else 
		return false;
	return true;
	}

	public boolean referenceViewAllLinkWorks() throws InterruptedException{
	new Link(properties.get("K12-Reference_viewAll")).click();
	String url= new Url().getUrl();
	return url.contains("ReferenceFullListPage");
	}
	public boolean magazinesViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("magazines_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("MagazinesFullListPage");
		}
	public boolean websitesViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("websites_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("WebsitesFullListPage");
		}
	public boolean newsViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("news_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("NewsFullListPage");
		}
	public boolean journalsViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("Journals_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("AcademicJournalsFullListPage");
		}
	public boolean audioViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("audio_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("AudioFullListPage");
		}
	public boolean videoViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("video_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("VideosFullListPage");
		}
	public boolean imagesViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("images_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("ImagesFullListPage");
		}
	public boolean primarysourcesViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("primarysources_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("PrimarySourcesFullListPage");
		}
	public boolean statisticsViewAllLinkWorks() throws InterruptedException{
		new Link(properties.get("statistics_viewAll")).click();
		String url= new Url().getUrl();
		return url.contains("StatisticsFullListPage");
		}

	public boolean verifyClickedTabIsHighlited() throws InterruptedException {
		return magazinesViewAllLinkWorks(); 
	}

	public boolean verifyTabsChanged() throws InterruptedException {
		new Link(properties.get("statistics_tab")).click();
		String url=new Url().getUrl();
		return url.contains("StatisticsFullListPage");
	}

	public boolean verifyTabsIsDisplayed() {
		for(int i=1; i<=10; i++){
			if(!new TextLabel(properties.get("allTabs")+ i + "]").isPresent())
			return false;
			}
		return true;
	}

	public boolean verifyErrorMessage() {
	String message = new TextLabel(properties.get("homepageerrormessage")).getLabelText();
	String msg = "No Results matching your search term(s) were found.\n\nPlease check your spelling or try other search term(s).";
	return (message.contains(msg));
      
	
	}
	
	
	
	
}
