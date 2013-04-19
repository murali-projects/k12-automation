package webPageContainers4Testing.dev;

import utils.PropertyReader;
import webPageContainers4Testing.BasePageContainer;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class BaseDevPageContainer extends BasePageContainer {

	public BaseDevPageContainer() throws Exception {
		super();
	}

	protected void loadProperties() {
		properties = new PropertyReader("properties/default.dev.search.properties");
	}

	public String getProperty(String propertyName) {
		return properties.get(propertyName);
	}

	public boolean doesContentExistInId(String id, String specificContentXpath) throws InterruptedException, Exception {
		return doesContentExist("id", id, specificContentXpath);
	}

	public boolean doesContentExistInClass(String className, String specificContentXpath) throws InterruptedException,
			Exception {
		return doesContentExist("class", className, specificContentXpath);
	}

	protected boolean doesContentExist(String type, String div, String specificContentXpath)
			throws InterruptedException, Exception {
		TextLabel content = getTextLabel(type, div, specificContentXpath);
		return content.getLabelText() != null && !content.getLabelText().isEmpty();
	}

	public TextLabel getTextLabel(String type, String div, String specificContentXpath) {
		String locator = String.format("//div[@%s='%s']%s", type, div, properties.get(specificContentXpath));
		TextLabel content = new TextLabel(locator);

		return content;
	}
	
	public void clickLink(String linkXPath) throws InterruptedException {
		Link document = new Link(properties.get(linkXPath));
		document.click();
	}
}
