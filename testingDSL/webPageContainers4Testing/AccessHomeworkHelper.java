package webPageContainers4Testing;

import java.util.Properties;

import com.thoughtworks.selenium.Selenium;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class AccessHomeworkHelper extends BasePageContainer{

	public AccessHomeworkHelper() throws Exception {
		super();
	}

	public boolean verifyResourcesLinkAccesible() throws Exception {
		new Link(properties.get("resources_link")).click();
		return (new TextLabel(properties.get("resources_tabs")).isPresent());
	}

	public boolean verifyClickingOnHomeworkHelper() throws Exception {
		new Link(properties.get("resources_link")).click();
		new Link(properties.get("access_homework_helper")).click();
		return (new TextLabel(properties.get("homework_helper_label")).isPresent());
	}

	public boolean verifyClickingOnGaleSchools() throws Exception {
		//new Link(properties.get("resources_link")).click();
		new Link(properties.get("access_homework_helper")).click();
		new Link(properties.get("homework_gale_schools")).clickWithoutWait();
		Thread.sleep(1000);
		String galeschoolsUrl=new Url().getUrl();
		return (galeschoolsUrl.contains("galeschools"));
	}

	public boolean verifyNewTabClosable() throws Exception {
		
		new Url().selectWindow("name=research_tools_win");
		new Url().closeWindow();
		new Url().selectWindow("");
		return true;

	}

	public boolean verifyNavigationThroughBreadcurmb() throws Exception {
		return new TextLabel(properties.get("breadcrumb_list") + "/a").getLabelText().equals("Home")
		&& new TextLabel(properties.get("breadcrumb_list") + "[2]/a").getLabelText().equals("Resources");
	}

	public boolean verifyBreadcrumb() throws Exception {
		new Link(properties.get("breadcrumb_home")).click();
		return new TextLabel("Home").isPresent();

	}
	

}
