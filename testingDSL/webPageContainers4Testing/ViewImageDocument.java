package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ViewImageDocument extends BasePageContainer {

	private Link viewAll;
	private Link nextLink;
	private Link previousLink;
	private int noOfResults;

	public ViewImageDocument() throws Exception {
		super();
		viewAll = new Link(properties.get("images_viewAll"));
		nextLink = new Link(properties.get("next"));
		previousLink = new Link(properties.get("previous"));
	}

	public boolean verifyDetailedViewOfImageIsDisplayed() throws Exception {
		noOfResults = Integer.parseInt(viewAll.getLinkText().split(" ")[2]
				.replace(",", ""));
		String imageTitle = new TextLabel(properties
				.get("images_searchResults_xpath")
				+ "/img").getAttribute("title");
		new Link(properties.get("images_searchResults_xpath")).click();
		return (new TextLabel(properties.get("images_title_in_detailedView"))
				.getLabelText().equals(imageTitle));
	}

	public boolean verifyDetailedViewOfImageIsDisplayedFromViewAll()
			throws Exception {
		new Url().goBackToPreviousPage();
		new Link(properties.get("images_viewAll")).click();
		String imageTitle = new TextLabel(properties.get("images_thumbnails")
				+ "/p").getLabelText();
		new Link(properties.get("images_thumbnails") + "/a/img").click();
		return (new TextLabel(properties.get("images_title_in_detailedView"))
				.getLabelText().equals(imageTitle));
	}

	public boolean checkSearchButtonIsPresent() throws Exception {
		if (new TextBox(properties.get("searchBox")).isPresent()
				&& new PageButton(properties.get("findButton")).isPresent()) {
			return true;
		}
		return false;
	}

	public boolean verifyFullSizeImageIsDisplayed() {
		TextLabel image = new TextLabel(properties
				.get("images_fullSizeImage_xpath"));
		if (image.getAttribute("height").equals("288")
				&& image.getAttribute("width").equals("460"))
			return true;
		return false;
	}

	public boolean verifyElementsAreDisplayedOnPage() throws Exception {
		if (new TextLabel(properties.get("source_citation")).isPresent()
				&& new TextLabel(properties.get("copyright_label"))
						.isPresent()
				&& new TextLabel(properties.get("gale_document"))
						.isPresent())
			return true;
		return false;
	}

	public boolean verifyToolsArePresent() throws Exception {
		if (new TextLabel(properties.get("images_print")).isPresent()
				&& new TextLabel(properties.get("images_email")).isPresent()
				&& new TextLabel(properties.get("images_share")).isPresent()
				&& new TextLabel(properties.get("images_download")).isPresent()
		// Not yet implemented in the application
		/*
		 * && new TextLabel(properties.get("images_wordDefn_lookup"))
		 * .isPresent() && new TextLabel(properties.get("images_read_article"))
		 * .isPresent() && new
		 * TextLabel(properties.get("images_subjectTerms_search")) .isPresent()
		 */) {

			return true;
		}
		return false;
	}

	public boolean verifyNextAndPreviousButtonsPresent() throws Exception {
		if (noOfResults > 2) {
			Thread.sleep(1000);
			if (!(new Link(properties.get("images_nextLink")).isPresent() && new Link(
					properties.get("images_previousLink")).isPresent())) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyImagesForNext() throws Exception {
		new Url().goBackToPreviousPage();
		if (noOfResults > 15) {
			nextLink.click();
			String imageTitle = new TextLabel(properties
					.get("images_thumbnails")
					+ "/p").getLabelText();
			new Link(properties.get("images_thumbnails") + "/a/img").click();
			return (new TextLabel(properties
					.get("images_title_in_detailedView")).getLabelText()
					.equals(imageTitle));
		}
		return true;
	}

	public boolean verifyImagesForPrevious() throws Exception {
		new Url().goBackToPreviousPage();
		if (Integer.parseInt(new TextLabel(properties.get("currentResults"))
				.getLabelText().split("-")[0].trim()) > 15) {
			previousLink.click();
			String imageTitle = new TextLabel(properties
					.get("images_thumbnails")
					+ "/p").getLabelText();
			new Link(properties.get("images_thumbnails") + "/a/img").click();
			return (new TextLabel(properties
					.get("images_title_in_detailedView")).getLabelText()
					.equals(imageTitle));
		}
		return true;
	}

	private int getResultsCount() {
		return noOfResults;
	}

	public boolean verifyNavigationOfPreviousNextButtons()
			throws InterruptedException, Exception {
		String imageTitle;
		if (getResultsCount() > 2) {
			new Link(properties.get("images_searchResults_xpath") + "[2]")
					.click();
			imageTitle = new Link(properties.get("images_searchResults_xpath")
					+ "[2]").getLinkText();
			previousLink.click();
			return (!new TextLabel(properties
					.get("images_title_in_detailedView")).getLabelText()
					.equals(imageTitle));
		}
		return true;
	}

}
