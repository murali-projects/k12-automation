package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewAllImagesWithFeatured extends BasePageContainer {

	List<String> contentDisplayedBeforeViewAll;
	List<String> contentDisplayedAfterViewAll;
	List<String> contentDisplayedInPortlet;

	public ViewAllImagesWithFeatured() throws Exception {
		super();
		contentDisplayedBeforeViewAll = new ArrayList<String>();
		contentDisplayedAfterViewAll = new ArrayList<String>();
	}

	public boolean verifyDefaultSortForImages() throws InterruptedException {
		contentDisplayedInPortlet = getImagesContentFromPortletInPortal();
		if (new TextLabel(properties.get("images_portal_viewall")).isPresent()) {
			new Link(properties.get("images_portal_viewall")).click();
			contentDisplayedAfterViewAll = getDisplayedContent();
			for (int i = 0; i < contentDisplayedInPortlet.size(); i++) {
				if (!(contentDisplayedInPortlet.get(i)
						.equals(contentDisplayedAfterViewAll.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyChangeInSortForImages(String option) throws Exception {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			selectSortElement(option);
			List<String> contentDisplayedAfterSortChange = getDisplayedContent();
			for (int i = 0; i < contentDisplayedAfterViewAll.size(); i++) {
				if (!(contentDisplayedAfterViewAll.get(i)
						.equals(contentDisplayedAfterSortChange.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	private void selectSortElement(String option) throws Exception {
		new DropDownBox(properties.get("sort_by_xpath")).select(option);
	}

	public boolean verifyRelevanceSortForImages(String option) throws Exception {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			selectSortElement(option);
			contentDisplayedAfterViewAll = getDisplayedContent();
			for (int i = 0; i < contentDisplayedBeforeViewAll.size(); i++) {
				if (!(contentDisplayedBeforeViewAll.get(i)
						.equals(contentDisplayedAfterViewAll.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	private List<String> getDisplayedContent() {
		List<String> contentDisplayed = new ArrayList<String>();
		for (int i = 1; i <= 3; i++) {
			contentDisplayed.add(new TextLabel(properties
					.get("viewAll_searchResults" + "[" + i + "]/td[2]/h3/a"))
					.getLabelText());
		}
		return contentDisplayed;
	}

	public boolean verifyWhenTabsAreChanged() throws InterruptedException {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			new DropDownBox(properties.get("sort_by_xpath"))
					.select("Relevance");
			new Link(properties.get("news_tab")).click();
			new Link(properties.get("images_tab")).click();
			List<String> contentDisplayed = getDisplayedContent();
			for (int i = 0; i < contentDisplayed.size(); i++) {
				if (!(contentDisplayed.get(i)
						.equals(contentDisplayedAfterViewAll.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyPaginationOfHandPickedItems()
			throws InterruptedException {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			new DropDownBox(properties.get("sort_by_xpath"))
					.select("Document Title");
			List<String> contentDisplayedBeforePagination = getDisplayedContent();
			new Link(properties.get("pages") + "[2]").click();
			new Link(properties.get("pages") + "[4]").click();
			new Link(properties.get("pages") + "[1]").click();
			List<String> contentDisplayedAfterPagination = getDisplayedContent();
			for (int i = 0; i < contentDisplayedAfterPagination.size(); i++) {
				if (!(contentDisplayedBeforePagination.get(i)
						.equals(contentDisplayedAfterPagination.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyDetailedPageOfHandPickedItem()
			throws InterruptedException {
		new Url().goBackToPreviousPage();
		String titleInViewAllPage = new TextLabel(properties
				.get("images_portal")
				+ "[1]/img").getAttribute("alt");
		new Link(properties.get("images_portal") + "[1]/img").click();
		return (titleInViewAllPage.equals(new TextLabel(properties
				.get("images_title_in_detailedView")).getLabelText()));
	}

	public boolean verifyHandPickedItemsOnSearchPage() {
		new Url().goBackToPreviousPage();
		return !new TextLabel(properties.get("editorspick")).isPresent();

	}

	public boolean verifyPaginationWithRelavence() throws InterruptedException {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			new Link(properties.get("pages") + "[2]").click();
			List<String> contentDisplayedBeforeRelevance = getDisplayedContent();
			new DropDownBox(properties.get("sort_by_xpath"))
					.select("Relevance");
			List<String> contentDisplayedAfterRelevance = getDisplayedContent();
			for (int i = 0; i < contentDisplayedAfterRelevance.size(); i++) {
				if ((contentDisplayedBeforeRelevance.get(i)
						.equals(contentDisplayedBeforeRelevance.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean verifyPaginationWithRelavenceFromOtherSort()
			throws InterruptedException {
		if (new Url().getUrl().contains(
				"ImagesFullListPage/ImagesFullListWindow")) {
			List<String> contentDisplayedBeforeRelevance = getDisplayedContent();
			new Link(properties.get("pages") + "[2]").click();
			new DropDownBox(properties.get("sort_by_xpath"))
					.select("Document Title");
			new DropDownBox(properties.get("sort_by_xpath"))
					.select("Relevance");
			List<String> contentDisplayedAfterRelevance = getDisplayedContent();
			for (int i = 0; i < contentDisplayedAfterRelevance.size(); i++) {
				if (!(contentDisplayedBeforeRelevance.get(i)
						.equals(contentDisplayedBeforeRelevance.get(i)))) {
					return false;
				}
			}
		}
		return true;
	}

	private List<String> getImagesContentFromPortletInPortal() {
		List<String> contentDisplayed = new ArrayList<String>();
		for (int i = 1; i <= new TextLabel(properties.get("images_portal"))
				.getXpathCount(); i++) {
			contentDisplayed.add(new TextLabel(properties.get("images_portal")
					+ "[" + i + "]/img").getAttribute("alt"));
		}
		return contentDisplayed;
	}

	public void portalNavigation() throws InterruptedException {
		new Link(properties.get("topic_portal")).click();
	}
}
