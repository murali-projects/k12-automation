package webPageContainers4Testing;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class ViewAllAudioWithFeatured extends BasePageContainer	{
	public ViewAllAudioWithFeatured() throws Exception {
		super();
		}
	public boolean verifyHandPickedItemsOnTop() throws InterruptedException {
		new Link(properties.get("audio_viewAll")).click();
		new DropDownBox(properties.get("sort_by_xpath")).select("Relevance");
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (text.equals(handPickedText));
			}
	public boolean ChangeTheSortOption() {
		new DropDownBox(properties.get("sort_by_xpath")).select("Date");
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
			}
	public boolean verifyWithDifferentSortOption() {
		new DropDownBox(properties.get("sort_by_xpath")).select("Date");
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
	}
	public boolean verifyWithOtherSortOption() {
		new DropDownBox(properties.get("sort_by_xpath")).select("Document Title");
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
		}
	public boolean verifyWhenTabsAreChanged() throws InterruptedException {
		new DropDownBox(properties.get("sort_by_xpath")).select("Relevance");
		new Link(properties.get("news_tab")).click();
		new Link(properties.get("audio_tab")).click();
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
	}
	public boolean verifyPaginationOfHandPickedItems() throws InterruptedException {
		new DropDownBox(properties.get("sort_by_xpath")).select("Document Title");
		new Link(properties.get("pages")+"[2]").click();
		new Link(properties.get("pages")+"[4]").click();
		new Link(properties.get("pages")+"[1]").click();
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
	}
	public boolean verifyPaginationWithRelavence() throws InterruptedException {
		new Link(properties.get("pages")+"[2]").click();
		new Link(properties.get("pages")+"[4]").click();
		new DropDownBox(properties.get("sort_by_xpath")).select("Relevance");
		String handPickedText = null;
		String text = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		return (!text.equals(handPickedText));
		}
	public boolean verifyDetailedPageOfHandPickedItem() throws InterruptedException {
		String titleInViewAllPage = new TextLabel(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").getLabelText();
		new Link(properties.get("contentLevel_locator")+ "1]/td[2]/h3/a").click();
		String titleInDetailedPage = new TextLabel(properties.get("titleXpath")).getLabelText();
		return (titleInViewAllPage.equals(titleInDetailedPage));
	}
	public boolean verifyHandPickedItemsOnSearchPage() {
		return !new TextLabel(properties.get("editorspick")).isPresent();
	}

}
