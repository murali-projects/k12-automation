package webPageContainers4Testing.dev;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import utils.PropertyReader;
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;


public class VideosFullPortlet extends ViewFullListPortlet {

	private PropertyReader props = new PropertyReader("properties/default.dev.search.properties");

	public VideosFullPortlet() throws Exception {
		super("videos");
	}	
	
	private TextLabel getTotalResultsLabel() {
		return new TextLabel(props.get("page_sr.portlet_videosFull.totalcount.textlabel.xpath"));
	}

	private TextLabel getCurrentResultsLabel() {
		return new TextLabel(props.get("page_sr.portlet_videosFull.currentresults.textlabel.xpath"));
	}

	public int getTotalResultsCount() {
		return Integer.parseInt(getTotalResultsLabel().getLabelText().trim());
	}

	public int getCurrentResultsStartingDocNumber() {
		return Integer.parseInt(getCurrentResultsLabel().getLabelText().split("-")[0].trim());
	}

	public int getCurrentResultsEndingDocNumber() {
		return Integer.parseInt(getCurrentResultsLabel().getLabelText().split("-")[1].trim());
	}

	public Link getNextLink() {
		return new Link(props.get("page_sr.portlet_videosFull.next.link.xpath"));
	}

	public Link getLastLink() {
		return new Link(props.get("page_sr.portlet_videosFull.last.link.xpath"));
	}

	public Link getFirstLink() {
		return new Link(props.get("page_sr.portlet_videosFull.first.link.xpath"));
	}

	public Link getPrevLink() {
		return new Link(props.get("page_sr.portlet_videosFull.prev.link.xpath"));
	}

	public Link getDocThumbnailLink() {
		return new Link(props.get("page_sr.portlet_videosFull.docThumnail.link.xpath"));
	}

	public DropDownBox getSortDropDown() {
		return new DropDownBox(props.get("page_sr.portlet_videosFull.sort.dropdown.xpath"));
	}

	public int changeToNextSortOption() {
		int newIndex = (getCurrentSortIndex() == getSortDropDownSize() - 1) ? 0 : getCurrentSortIndex() + 1;
		getSortDropDown().setSelectedOptionValue("index=" + newIndex);
		return newIndex;
	}

	public int getCurrentSortIndex() {
		return Integer.parseInt(getSortDropDown().getSelectedIndex());
	}

	public int getSortDropDownSize() {
		return getSortDropDown().getSelectOptionsCount();
	}

	public void navigateToNextPage() throws InterruptedException {
		getNextLink().click();
	}

	public void navigateToLastPage() throws InterruptedException {
		getLastLink().click();
	}

	public void navigateToFirstPage() throws InterruptedException {
		getFirstLink().click();
	}

	public void navigateToPrevPage() throws InterruptedException {
		getPrevLink().click();
	}

	public boolean checkFirstPageDisplayText()throws Exception{
		String displayTextOfFirstPage = new TextLabel(props.get("viewAll_first_page_display_xpath")).getLabelText();
		String expectedTextOfFirstPage = props.get("viewAll_first_page_display_text");
		
		return displayTextOfFirstPage.contains(expectedTextOfFirstPage);
	}
	
	public boolean checkForContentGroupDisplay() throws Exception {
		String displayTextOfFirstContentGroup = new TextLabel(props.get("viewAll_content_group_display_xpath")).getLabelText();
		return displayTextOfFirstContentGroup.isEmpty() ? false : true;
	}
	
	public boolean checkForViewAllPageName(String pageName)throws Exception {
		String expectedPageName = props.get(pageName);
		String acutalPageName = new TextLabel(props.get("viewAll_page_name")).getLabelText();
		return expectedPageName.equals(acutalPageName);
	}
	
	public boolean checkForWorkingOfContentGroupSelector(String pageName) throws Exception  {

		String displayTextOfFirstContentGroup = new TextLabel(props.get("viewAll_content_group_display_xpath"))
				.getLabelText();
		String displayGroupSelected = props.get(pageName);
		String[] contentGroupWithHits = removeNonWordCharacters(displayTextOfFirstContentGroup).split("\\(");
		List<String> contentGroups = new ArrayList<String>(1);
		for (String contentGroup : contentGroupWithHits) {
			if (!contentGroup.trim().startsWith("(")) {
				contentGroups.add(contentGroup.trim());
			}
		}
		int index =  contentGroups.indexOf(displayGroupSelected);
		int indexToBeSelected = index + 1 == contentGroups.size() ? index - 2 : index + 1; 
		
		String linkToBeSelected = MessageFormat.format(props.get("display_group_to_be_selected"),
				new Object[] { indexToBeSelected + 1 });

		Link contentGroupToBeSelected = new Link(linkToBeSelected);
		// TODO - remove this once academic journals works
		if (contentGroupToBeSelected.getLinkText().contains("Academic")) {
			index = contentGroups.indexOf(displayGroupSelected);
			indexToBeSelected = index + 2 == contentGroups.size() ? index - 2 : index + 2;

			linkToBeSelected = MessageFormat.format(props.get("display_group_to_be_selected"),
					new Object[] { indexToBeSelected + 1 });

			contentGroupToBeSelected = new Link(linkToBeSelected);

		}
		contentGroupToBeSelected.click();
		return checkForViewAllPageName(pageName) ? false : true;
	}

	private String removeNonWordCharacters(String text) {

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);

			if (Character.isDigit(c) || c == ')' || c == ',') {

			} else {
				result.append(c);
			}
		}

		return result.toString();
	}
}


