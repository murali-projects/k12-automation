package webPageContainers4Testing.dev;

import utils.PropertyReader;
import webPageElements4Testing.Image;
import webPageElements4Testing.Link;

public class ImagesPortlet {

	private PropertyReader props;
	private Link imagesPortletDiv;
	private Link[] imagesThumnailImagesLinks;
	private Image[] imagesThumbnailImages;
	private Link viewAllLink;
	private final int noOfImagesInGrid;

	public ImagesPortlet() {

		props = new PropertyReader("properties/default.dev.search.properties");
		imagesPortletDiv = new Link(props.get("page_sr.portlet_images.div.xpath"));
		viewAllLink = new Link(props.get("page_sr.portlet_images.viewAll.link.xpath"));
		noOfImagesInGrid = Integer.parseInt(props.get("page_sr.portlet_images.nofofimagesingrid"));

		imagesThumnailImagesLinks = new Link[noOfImagesInGrid];
		imagesThumbnailImages = new Image[noOfImagesInGrid];

		for (int i = 0; i < noOfImagesInGrid; i++) {
			String linkXpath = props.get("page_sr.portlet_images.thumbnail.links.xpath").replace(":x",
					Integer.toString(i + 1));
			imagesThumnailImagesLinks[i] = new Link(linkXpath);

			String imageXpath = props.get("page_sr.portlet_images.thumbnail.img.xpath").replace(":x",
					Integer.toString(i + 1));
			imagesThumbnailImages[i] = new Image(imageXpath);

		}

	}


	public void navigateToViewAll() throws InterruptedException {
		viewAllLink.click();
	}

	public void navigateToFirstImage() throws InterruptedException {
		imagesThumnailImagesLinks[0].click();
	}


	public Image[] getImagesThumbnailImages() {
		return imagesThumbnailImages;
	}

}
