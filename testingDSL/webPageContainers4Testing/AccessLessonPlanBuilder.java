package webPageContainers4Testing;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class AccessLessonPlanBuilder extends BasePageContainer{

	public AccessLessonPlanBuilder() throws Exception {
		super();
	}

	public boolean verifyResourcesPage() throws InterruptedException{
		new Link(properties.get("resources_tab")).click();
		return new TextLabel(properties.get("guide_tour")).isPresent();
	}
	
	public boolean verifyLessonPlanBuilderSelection() throws InterruptedException{
		new Link(properties.get("lesson_plan_tab")).click();
		return new TextLabel(properties.get("lesson_plan_builder")).isPresent();
	}
	
	public boolean verifyNewWindowIsOpened(){
		String[] windowNames=selenium.getAllWindowNames();
		return windowNames[2].equals("");
	}
	
	public boolean verifyNewWindowIsClosed(){
		selenium.close();
		String[] windowNames=selenium.getAllWindowNames();
		return windowNames[0].equals("") && windowNames.length==1;
	}
	
	public boolean homePageNavigation() throws InterruptedException{
		new Link(properties.get("breadcrumb_home")).click();
		return new TextLabel(properties.get("breadcrumb_home_clicked")).isPresent();
	}
	
	public boolean verifyBreadCrumbDisplay() throws InterruptedException{
		new Link(properties.get("resources_tab")).click();
		return new TextLabel(properties.get("home_resources")).isPresent();
		
	}
	
	public void navigateToHomeTab() throws InterruptedException{
		new Link(properties.get("home_tab")).click();
	}
}
