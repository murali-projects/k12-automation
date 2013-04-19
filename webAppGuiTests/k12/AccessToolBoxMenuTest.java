package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessToolBoxMenu;

public class AccessToolBoxMenuTest extends BaseWebPageTest{
	
	AccessToolBoxMenu  accessToolBoxMenu;
	
	@BeforeTest
	public void setUp() throws Exception{
		accessToolBoxMenu = new AccessToolBoxMenu();
	}
	@Test
	public void basicSearch() throws InterruptedException{
		doBasicSearchUsingSearchTerm("war");
	}
	@Test(dependsOnMethods={"basicSearch"})
	public void verifyToolBox(){
		Assert.assertTrue(accessToolBoxMenu.verifyToolBox());
	}
	@Test(dependsOnMethods={"verifyToolBox"})
	public void verifyToolBoxElements(){
		Assert.assertTrue(accessToolBoxMenu.verifyToolBoxElements());
	}
	@Test(dependsOnMethods={"verifyToolBoxElements"})
	public void verifyToolsAreWorking() throws InterruptedException{
		Assert.assertTrue(accessToolBoxMenu.verifyToolsAreWorking());
	}
	@Test(dependsOnMethods={"verifyToolsAreWorking"})
	public void verifyToolBoxIsAvailableOnViewAllPage()throws InterruptedException{
		Assert.assertTrue(accessToolBoxMenu.verifyToolBoxIsAvailableOnViewAllPage());
	}
	@Test(dependsOnMethods={"verifyToolBoxIsAvailableOnViewAllPage"})
	public void verifyToolsAreWorkingOnViewAllPage()throws InterruptedException{
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(accessToolBoxMenu.verifyToolsAreWorkingOnViewAllPage());
	}
}
