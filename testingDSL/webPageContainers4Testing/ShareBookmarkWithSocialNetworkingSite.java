package webPageContainers4Testing;

import java.awt.Button;

import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class ShareBookmarkWithSocialNetworkingSite extends BasePageContainer {

	public ShareBookmarkWithSocialNetworkingSite() throws Exception {
		super();

	}

	public boolean verifyShareToolAvailableInDetailPage() throws Exception {
		new Link(properties.get("news_link")).click();

		if (new PageElementWithIdAttribute(properties.get("share_option"))
				.isPresent())
			return true;
		return false;
	}

	public boolean verifyMouseOverShareOption() throws Exception {
		new Link(properties.get("news_link")).click();
		new Url().mouseOver(properties.get("share_option"));
		String share = new TextLabel(properties.get("share_option"))
				.getLabelText();
		if (share.equals(properties.get("bookmark&Share")))
			return true;
		return false;
	}

	public boolean verifyContentIsSharedInNetwork() throws Exception {
		new Link(properties.get("news_link")).click();
		new Link(properties.get("share_option")).clickWithoutWait();
		Thread.sleep(15000);
		new Link(properties.get("facebook_Link")).clickWithoutWait();
		new Url().waitForPopup(properties.get("facebook_windowid"));
		new Url().selectWindow(properties.get("facebook_windowid"));
		Thread.sleep(15000);
		new TextBox(properties.get("email_id")).type("k12cengage@gmail.com");
		new TextBox(properties.get("password")).type("k12@cengage");
		new PageButton(properties.get("loginbutton")).click();
		Thread.sleep(20000);
		new Link(properties.get("share_button")).clickWithoutWait();
		return true;
	}

	public boolean verifyMoreAndCloseOptionsInPopup() throws Exception {
		new Link(properties.get("news_link")).click();
		new Url().mouseOver(properties.get("share_option"));

		new Link(properties.get("share_option")).clickWithoutWait();
		Thread.sleep(10000);
		if (new PageElementWithIdAttribute(properties.get("close_link"))
				.isPresent())
			new Link(properties.get("close_link")).clickWithoutWait();
		return true;
	}

	public boolean verifyUserAbleToSearch() throws Exception {
		new Link(properties.get("news_link")).click();
		new Link(properties.get("share_option")).clickWithoutWait();
		Thread.sleep(500);
		new TextBox(properties.get("share_textbox")).type(properties
				.get("share_searchterm"));

		// String searchresult =
		// selenium.getText("//div[@id='ati_facebook']/span");
		String searchresult = new TextLabel(properties.get("search_result"))
				.getLabelText();

		return (searchresult.equalsIgnoreCase("Facebook"));

	}

	public boolean verifyEmptyFieldForNextSearch() throws Exception {
		new Link(properties.get("news_link")).click();
		new Link(properties.get("share_option")).clickWithoutWait();
		Thread.sleep(5000);
		return (new TextLabel(properties.get("share_textbox")).getLabelText()
				.equals(""));

	}

	public boolean verifyNoSearchTermFound() throws Exception {
		new Link(properties.get("news_link")).click();
		new Link(properties.get("share_option")).clickWithoutWait();
		Thread.sleep(10000);
		new TextBox(properties.get("share_textbox")).type(properties
				.get("share_invalidsearchterm"));
		selenium.keyUp(properties.get("share_textbox"), "\40");
		Thread.sleep(4000);
		String searchresult = new TextLabel(properties
				.get("invalid_search_termresult")).getLabelText();
		return (searchresult.equalsIgnoreCase("No matching services."));

	}

}
