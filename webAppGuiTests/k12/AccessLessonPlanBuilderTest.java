package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessLessonPlanBuilder;

public class AccessLessonPlanBuilderTest extends BaseWebPageTest{
	
	private AccessLessonPlanBuilder accessLessonPlanBuilder;

	@BeforeTest
	public void setUp() throws Exception {
		accessLessonPlanBuilder = new AccessLessonPlanBuilder();
		accessLessonPlanBuilder.navigateToHomeTab();
	}
	
	@Test
	public void verifyResourcesPage() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.verifyResourcesPage());
	}
	
	@Test(dependsOnMethods = { "verifyResourcesPage" })
	public void verifyLessonPlanBuilderSelection() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.verifyLessonPlanBuilderSelection());
	}
	
	@Test(dependsOnMethods = { "verifyLessonPlanBuilderSelection" })
	public void verifyNewWindowIsOpened() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.verifyNewWindowIsOpened());
	}
	
	@Test(dependsOnMethods = { "verifyNewWindowIsOpened" })
	public void verifyNewWindowIsClosed() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.verifyNewWindowIsClosed());
	}
	
	@Test(dependsOnMethods = { "verifyNewWindowIsClosed" })
	public void verifyHomePageNavigation() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.homePageNavigation());
	}
	
	@Test(dependsOnMethods = { "verifyHomePageNavigation" })
	public void verifyBreadCrumbDisplay() throws Exception {
		Assert.assertTrue(accessLessonPlanBuilder.verifyBreadCrumbDisplay());
	}
	

}
