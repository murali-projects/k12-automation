package webPageContainers4Testing;

import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class HomePage extends BasePageContainer {
	public static final String HOME_PAGE_URL = "http://think.qa.gghybrid.com/portal";
	public static final String HOME_PAGE_TITLE = "K-12";
	
	private TextBox basicSearchBox; 
	private PageButton basicSearchButton; 
	
	public HomePage() throws Exception {
		goToPage(HOME_PAGE_URL);
		//LoginPage loginPage = new LoginPage();
		//loginPage.logInAs(GALE_USERGROUP_NAME);
		//verifyTitleContains(HOME_PAGE_TITLE);
		
		basicSearchBox = new TextBox(properties.get("searchBox")); //TODO: get an ID for this from Nate
		basicSearchButton = new PageButton(properties.get("findButton"));
		
	}

	public TextBox basicSearchBox() {
		return basicSearchBox;
	}

	public PageButton basicSearchButton() {
		return basicSearchButton;
	}
	
	public void doBasicSearchUsingSearchTerm (String SearchTerm){
		basicSearchBox.type(SearchTerm);
		basicSearchButton.clickAndWait();
	}
	
	public String basicSearchBoxWithText() {
		 return getValue(properties.get("searchBox"));
	}
	
	public String getResutlsPageTitle(){
		return getTitle();
	}
	
	public String getLableText(String string)throws Exception {
		TextLabel resultLabel = new TextLabel(string);
		return resultLabel.getLabelText(string);
	}
}
