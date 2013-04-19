package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class BookmarkDocument extends BasePageContainer{

	public BookmarkDocument() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyBookmarkLink() throws InterruptedException {
		new Link((properties.get("K12-Reference_searchResults_xpath")+"[1]/h3/a")).click();
		return (new TextLabel(properties.get("bookmarkLink")).isPresent());
	}

	public boolean verifyBookmarkLinkIsWorking() throws InterruptedException {
		new Link(properties.get("bookmarkLink")).clickWithoutWait();
		String currentUrl = new Url().getUrl();
		String bookmarkUrl = new TextLabel(properties.get("bookmarkurl")).getLabelText();
		return (currentUrl.contains(bookmarkUrl));
	}

	public boolean verifyBookmarkLinkUrlIsWorking() {
		boolean flag = false;
		String bookmarkUrl = new TextLabel(properties.get("bookmarkurl")).getLabelText();
		selenium.openWindow(bookmarkUrl, "123");
		flag = new TextLabel(properties.get("bookmarkLink")).isPresent();
		selenium.selectWindow("123");
		selenium.close();
		selenium.selectWindow("");
		return flag;
		}
		
}
