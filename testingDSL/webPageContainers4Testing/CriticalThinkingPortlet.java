package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class CriticalThinkingPortlet extends BasePageContainer{

	public CriticalThinkingPortlet() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyCriticalPortlet() {
		return (new TextLabel(properties.get("critical_Thinking_portlet")).isPresent());
	}

	public boolean verifyCriticalThinkingPortletDocuments() {
		int count = new Link(properties.get("critical_Thinking_portlet_documents")).getLinkCount();
		return count <=3;
	}

	public boolean verifyViewAllLink() {
		return !(new TextLabel(properties.get("critical_viewall_link")).isPresent());
	}

	@SuppressWarnings("deprecation")
	public boolean verifyPublicationDateOrder() throws Exception{
		boolean isValidate = true;
		List<String> publicationDatesInAppl = getPublicationDates();
		int count=0;
		
		for (int i = 0; i <= publicationDatesInAppl.size(); i++) {
			count++;
			if (count < publicationDatesInAppl.size()) {
				String[] date1 = publicationDatesInAppl.get(i).split("-");
				String[] date2 = publicationDatesInAppl.get(i+1).split("-");
				if (publicationDatesInAppl.get(i).equals(publicationDatesInAppl.get(i+1))) {
					//check if document titles are displayed in ascending order
					isValidate = verifyDocumentTitlesAreInAscOrder(i+1);
				}else if(new Date(date1[1]+"/"+date1[2]+"/"+date1[0]).before(new Date(date2[1]+"/"+date2[2]+"/"+date2[0]))){
					isValidate = false;
				}
			}
			if(!isValidate)
				break;
		}
		return isValidate;
		
	}
	private List<String> getDocumentTitles(int documentTitleIndex) throws Exception{
		List<String> documentTitlesList = new ArrayList<String>();
		// TODO:xpaths need to be changed after implementing tabs in the Appl		
		documentTitlesList.add(new TextLabel(properties.get("critical_results")
				+ "["
				+ documentTitleIndex
				+ "]"
				+ properties.get("critical_results_documentTitle")).getLabelText());
		documentTitlesList.add(new TextLabel(properties.get("critical_results")
				+ "["
				+ (documentTitleIndex + 1)
				+ "]"
				+ properties.get("critical_results_documentTitle")).getLabelText());

		return documentTitlesList;
	}
	private List<String> getPublicationDates() throws Exception{
		List<String> publicationDatesList = new ArrayList<String>();
		//TODO:xpaths need to be changed after implementing tabs in the Appl
		int noOfResults = new TextLabel(properties.get("criti_results")+"/td[2]").getXpathCount();
		for(int i = 1; i <= noOfResults; i++){
			publicationDatesList.add( new TextLabel(properties.get("criti_results")
					+ "[" + i + "]"
					+ properties.get("critical_results_publicationDate")).getLabelText());
		}
		return publicationDatesList;
	}
	private boolean verifyDocumentTitlesAreInAscOrder( int documentTitleIndex) throws Exception {
		boolean isValidate = false;
		List<String> documentTitles = getDocumentTitles(documentTitleIndex);
		int count=0;
		for (int i = 0; i <= documentTitles.size(); i++) {
			count++;
			if (count < documentTitles.size()) {
				if ((int) documentTitles.get(i).charAt(0) <= 
					(int) documentTitles.get(i + 1).charAt(0)) {
					isValidate = true;
				} else {
					isValidate = false;
					break;
				}
			}

		}
		return isValidate;
	}

	public boolean verifyDetailedDocument() throws InterruptedException {
		for(int i=1;i<=3; i++)
		{
		String documentText = new TextLabel(properties.get("critical_documetnt")+i+"]").getLabelText();
		new Link(properties.get("critical_documetnt")+i+"]").click();
		String actualText =new TextLabel(properties.get("ctitical_document_title")).getLabelText();
		if(!(documentText.equals(actualText)))
				return false;
		else
		new Url().goBackToPreviousPage();
		}
		return true;
	}
}
