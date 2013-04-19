package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class IntegrateVideoDisplayGroup extends BasePageContainer {

	private String currentUrl;

	public IntegrateVideoDisplayGroup() throws Exception {
		super();
		currentUrl = new Url().getUrl();
	}

	public boolean verifyVideoBucket() throws Exception {
		return new TextLabel(properties.get("video_Title_Xpath")).isPresent();
	}

	public boolean verifyVideoViewAllLink() throws Exception {
		return new TextLabel(properties.get("Video_ViewAll_Xpath")).isPresent();
	}

	public boolean verifyCountOfVideo() throws Exception {
		int count= getTotalCount();
		return (count > 0);
	}
	
	public boolean verifyVideoIcon() throws Exception {
		boolean flag = true;
		int noOfLinks = new TextLabel(properties.get("video_contentLinks_xpath")).getXpathCount();
		for(int i=1;i<=noOfLinks;i++){
				flag = new TextLabel(properties.get("video_Contentlinks_documentTitle_xpath") + "[" + i
				+ "]/h3/a").isPresent();
			if (flag == false)
				return false;
		}
		return flag;	
			
	}
	public boolean verifyVideoDocumentPage() throws Exception{
		new Link(properties.get("video_contentLinks_xpath")).click();
		return(currentUrl.contains("videocontent.jsp") && currentUrl
				.contains("windowstate=maximized"));
	}
	public boolean verifyBackLinkinVideoDocument() throws Exception{
		return (new TextLabel(properties.get("backLink_xpath")).isPresent());
	}
	public boolean verifyBackLinkinWorking() throws Exception{
		new Link(properties.get("backLink_xpath")).click();
		return(currentUrl.contains("videoTopicPortal") && currentUrl
				.contains("windowstate=maximized"));
		}
	public boolean verifyVideoIconInDocument() throws Exception{
		return (new TextLabel(properties.get("video_icon_xpath_InViewAllPage")).isPresent());
	}
	public boolean validateCountWithOcean(String searchTerm) throws Exception{
		List<String> list = OceanDatabaseReadFile.readResultsCountFromFile(properties.get("all"), properties.get("video"), properties.get("relevance"), searchTerm);
		String count = (!list.isEmpty() && list != null)?list.get(0):"0";
		if(getTotalCount() == Integer.parseInt(count))
			return true;
			else
				return false;
		
	}
	
	public int getTotalCount() throws Exception {
		return Integer.parseInt((new TextLabel(properties.get("Video_ViewAll_Xpath")).getLabelText()).substring(8).trim());
		}

	public boolean verifyViewAllLinkIsWorking() throws Exception {
		new Link(properties.get("Video_ViewAll_Xpath")).click();
		currentUrl = new Url().getUrl();
		return (currentUrl.contains("VideosFullListPage/VideosFullListWindow"));
	}

	public boolean verifyVideoBucketContentLinksCount() throws Exception {
		int videoDisplayGroupLinksCount = new Link(
				properties.get("video_Contentlinks_publicationdate_xpath")).getLinkCount();
		if (getTotalCount() <= 3
				&& videoDisplayGroupLinksCount == getTotalCount())
			return true;
		else {
			return (videoDisplayGroupLinksCount == 3) ? true : false;
		}
	}

	public boolean verifyPublicationDatesIsDisplayed() throws Exception {
		int noOfLinks = new TextLabel(properties.get("video_contentLinks_xpath")).getXpathCount();
		for (int i = 1; i < noOfLinks; i++) {
			if (!(new TextLabel(properties.get("video_contentLinks_xpath") + "[" + i
					+ "]/span[@class='publication_Date italics']").isPresent())) {
				return false;
			}
		}
		return true;
	}

	private List<String> getPublicationDates(String displayGroup)
			throws Exception {
		int noOfLinks = new TextLabel(properties.get("video_contentLinks_xpath")).getXpathCount();
		List<String> publicationTitlesList = new ArrayList<String>();
		for (int i = 1; i <= noOfLinks; i++) {
			publicationTitlesList.add(new TextLabel(properties.get("video_Contentlinks_documentTitle_xpath") + "[" + i
					+ "]/h3/a")
					.getLabelText());
		}

		return publicationTitlesList;
	}

	public boolean verifypublicationDate(String searchTerm, String displayGroup)
			throws Exception {
		List<String> publicationDatesInAppl = getPublicationDates(displayGroup);
		return verifyPublicationDatesWithDB(searchTerm, displayGroup,
				publicationDatesInAppl);
	}

	private boolean verifyPublicationDatesWithDB(String searchTerm,
			String displayGroup, List<String> publicationDatesInAppl)
			throws IOException {

		List<String> publicationDatesInDB = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"),displayGroup, properties.get("relevance"), properties.get("publicationDate"), searchTerm);
		
		if ((publicationDatesInDB != null && publicationDatesInAppl != null)) {
			for (int i = 0; i < publicationDatesInAppl.size(); i++) {
				if (!publicationDatesInAppl.get(i).equals(
						publicationDatesInDB.get(i)))
					return false;

			}
		}
		return true;
	}

	private List<String> getDocumentTitles(String displayGroup)
			throws Exception {
		new Link(properties.get(displayGroup+"_viewAll")).click();
		int noOfLinks = new TextLabel(properties.get("video_contentLinks_xpath")).getXpathCount();
		new DropDownBox(properties.get("sort_by_xpath")).select(properties.get("relevance"));
		List<String> documentTitlesList = new ArrayList<String>();
		for (int i = 1; i <= noOfLinks; i++) {
			documentTitlesList.add(new TextLabel(
					properties.get("video_Contentlinks_documentTitle_xpath") + "[" + i
							+ "]/h3/a")
					.getLabelText());
		}

		return documentTitlesList;
	}

	public boolean verifyDocumentTitles(String searchTerm, String displayGroup)
			throws Exception {
		List<String> documentTitlesInAppl = getDocumentTitles(displayGroup);
		return verifyDocumentTitlesWithDB(searchTerm, displayGroup,
				documentTitlesInAppl);
	}

	private boolean verifyDocumentTitlesWithDB(String searchTerm,
			String displayGroup, List<String> documentTitlesInAppl)
			throws IOException {
		List<String> publicationDatesInDB = OceanDatabaseReadFile
		.readResultsFromFile(properties.get("all"),displayGroup, properties.get("relevance"), properties.get("documentTitle"), searchTerm);
		if ((publicationDatesInDB != null && documentTitlesInAppl != null)) {
			for (int i = 0; i < documentTitlesInAppl.size(); i++) {
				if (!documentTitlesInAppl.get(i).equals(
						publicationDatesInDB.get(i)))
					return false;

			}
		}
		return true;
	}

}
