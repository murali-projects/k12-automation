package webPageContainers4Testing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewViewpointsDisplayGroup extends BasePageContainer {

	private String searchString;

	public ViewViewpointsDisplayGroup() throws Exception {
		super();
	}
	public void search(String searchTerm)
			throws InterruptedException {
		this.searchString = searchTerm;
	}

	public boolean verifyViewpointsDisplayGroupIsDisplayed() {
		try {
			return new TextLabel(properties.get("viewPoints_label")).getLabelText()
					.equals(properties.get("viewPoints"));
		} catch (Exception e) {
			return false;
		}
	}
	public boolean verifyTopDocuments() throws Exception {
		int documentsCount = new Link(properties.get("viewPoints_documents"))
				.getLinkCount();
		return (documentsCount >= 1 && documentsCount <= 3) ? true : false;
	}

	public boolean verifyToolTipOfDocumentTitle() throws Exception {

		List<String> docTitleToolTips = getDocTitleToolTip();
		List<String> docTitleToolTipsFromDataBase = getDocTitlesFromDataBase();
		return (docTitleToolTips.size() == docTitleToolTipsFromDataBase.size()) ? 
				validateDocTitleToolTipWithDataBase(docTitleToolTips, docTitleToolTipsFromDataBase) : false;
	}
	
	private boolean validateDocTitleToolTipWithDataBase(List<String> docTitleToolTips, 
														List<String> docTitleToolTipsFromDataBase){
		for (int index = 0; index < docTitleToolTips.size(); index++) {
			if (!(docTitleToolTips.get(index).equals(docTitleToolTipsFromDataBase.get(index)))) {
				return false;
			}
		}
		return true;		
	}

	private List<String> getDocTitleToolTip() throws Exception {
		List<String> docTitlesToolTip = new ArrayList<String>();
		int xPathCount = new Link(properties.get("viewPoints_documents")).getLinkCount() - 1;

		for (int count = 1; count <= xPathCount; count++) {
			docTitlesToolTip.add(new TextLabel(properties.get("viewPoints_documents")
					+ "[" + count + "]/div[2]/a@title").getAttribute());
		}
		return docTitlesToolTip;
	}

	private List<String> getDocTitlesFromDataBase() throws IOException {
		return OceanDatabaseReadFile.readResultsFromFile(
				properties.get("all"),
				properties.get("reference"),
				properties.get("relevance"),
				properties.get("docTitleAnnotation"), this.searchString);
	}
	
}
