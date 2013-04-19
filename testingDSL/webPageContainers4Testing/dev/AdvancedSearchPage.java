package webPageContainers4Testing.dev;

import webPageElements4Testing.CheckBox;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class AdvancedSearchPage extends BaseDevPageContainer{

	private TextLabel searchFor;
	private TextBox textSearch1;
	private PageButton advSearchSubmit;
	private CheckBox fullTextDocCheckBox;

	public AdvancedSearchPage() throws Exception {
		searchFor = new TextLabel(properties.get("page_advsearch.searchFor.textlabel"));
		textSearch1 = new TextBox(properties.get("page_advsearch.textSearch1.textbox"));
		advSearchSubmit = new PageButton(properties.get("page_advsearch.submit.button"));
		fullTextDocCheckBox = new CheckBox(properties.get("page_advsearch.fullTextDoc.checkbox"));
	}
	
	public boolean checkifNavigatedToAdvancedSearchPage() {
		return searchFor.isPresent();
	}
	
	public void performAnAdvancedSearch(String searchText) {
		textSearch1.type(searchText);
		fullTextDocCheckBox.click();
		advSearchSubmit.clickAndWait();
	}
	

}
