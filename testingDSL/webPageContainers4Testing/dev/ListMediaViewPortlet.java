package webPageContainers4Testing.dev;

import utils.PropertyReader;
import webPageElements4Testing.Link;


public class ListMediaViewPortlet extends ViewMediaSearchResultPortlet {

	public ListMediaViewPortlet(String displayGroupName) throws Exception {
		super(displayGroupName, displayGroupName+"_searchResults_xpath",
				displayGroupName+"_doctitle_xpath");
		
	}

	private static PropertyReader props = new PropertyReader("properties/default.search.properties");

	public static Link getImagesLink() {
		return new Link(props.get("pages_sr.portlet_images.thumbnaillink.xpath"));
	}
}


