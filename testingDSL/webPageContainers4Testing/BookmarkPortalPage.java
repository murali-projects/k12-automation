package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class BookmarkPortalPage extends BasePageContainer{

	public BookmarkPortalPage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyBookmarkPortalIsPresent() throws InterruptedException {
		Thread.sleep(5000);
		return (new TextLabel(properties.get("bookmarkLink")).isPresent());
	}

	public boolean verifyBookmarkAfterAdvancedsearch(String searchTerm1, String searchTerm2) throws Exception {
		new Link(properties.get("advanced_search_link")).click();
		new TextBox(properties.get("search_textbox1")).type(searchTerm1);
		new Link(properties.get("addNewRow")).clickWithoutWait();
		new TextBox(properties.get("search_textbox2")).type(searchTerm2);
		new Link(properties.get("bottom_search_button")).click();
		return (new TextLabel(properties.get("bookmarkLink")).isPresent());
	}

	public boolean verifyBookmarkPortalIsWorking() throws InterruptedException {
		boolean flag = false;
		new Link(properties.get("bookmarkLink")).clickWithoutWait();
		String bookmarkUrl = new TextLabel(properties.get("bookmarkurl")).getLabelText();
		selenium.openWindow(bookmarkUrl, "123");
		flag = new TextLabel(properties.get("bookmarkLink")).isPresent();
		selenium.selectWindow("123");
		selenium.close();
		selenium.selectWindow("");
		return flag;
		
	}

	
	
	
}
