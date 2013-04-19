package webPageContainers4Testing.dev;

import utils.PropertyReader;
import webPageElements4Testing.TextLabel;

public class ImagesDetailsPortlet extends BaseDevPageContainer {

	private PropertyReader props;
	private TextLabel docTitle;

	public ImagesDetailsPortlet() throws Exception {
		super();
		props = new PropertyReader("properties/default.dev.search.properties");
		docTitle = new TextLabel(props.get("page_sr.portlet_imagesDetails.title.text.xpath"));
	}

	public TextLabel getDocTitle() {
		return docTitle;
	}

}
