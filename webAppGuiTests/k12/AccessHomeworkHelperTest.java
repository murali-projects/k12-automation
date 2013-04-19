package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessHomeworkHelper;
import webPageContainers4Testing.ViewAllWebsitesWithFeatured;

public class AccessHomeworkHelperTest extends BaseWebPageTest {

	public AccessHomeworkHelper accesshomeworkhelper;

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		accesshomeworkhelper = new AccessHomeworkHelper();
	}

	@Test
	public void verifyResourcesLinkAccesible() throws Exception {
		Assert.assertTrue(accesshomeworkhelper.verifyResourcesLinkAccesible());
	}

	@Test(dependsOnMethods = { "verifyResourcesLinkAccesible" })
	public void verifyClickingOnHomeworkHelper() throws Exception {
		Assert.assertTrue(accesshomeworkhelper.verifyResourcesLinkAccesible());
	}

	@Test(dependsOnMethods = { "verifyClickingOnHomeworkHelper" })
	public void verifyClickingOnGaleSchools() throws Exception {
		Assert.assertTrue(accesshomeworkhelper.verifyClickingOnGaleSchools());
	}

	@Test(dependsOnMethods = { "verifyClickingOnGaleSchools" })
	public void verifyNewTabClosable() throws Exception {
		Assert.assertTrue(accesshomeworkhelper.verifyNewTabClosable());
	}

	@Test(dependsOnMethods = { "verifyNewTabClosable" })
	public void verifyNavigationThroughBreadcurmb() throws Exception {
		Assert.assertTrue(accesshomeworkhelper
				.verifyNavigationThroughBreadcurmb());
	}

	@Test(dependsOnMethods = { "verifyNavigationThroughBreadcurmb" })
	public void verifyBreadcrumb() throws Exception {
		Assert.assertTrue(accesshomeworkhelper.verifyBreadcrumb());
	}

}
