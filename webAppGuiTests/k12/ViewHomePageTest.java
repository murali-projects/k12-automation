package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewHomePage;

public class ViewHomePageTest extends BaseWebPageTest {

	private ViewHomePage viewHomePage;

	@BeforeTest
	public void setUp() throws Exception {
		viewHomePage = new ViewHomePage();
	}

	@Test
	public void verifyBasicSearchBoxIsPresent() throws Exception {
		Assert.assertTrue(viewHomePage.basicSearchBoxIsPresent());
	}

	@Test
	public void verifyTopMenuBarIsPresent() throws Exception {
		Assert.assertTrue(viewHomePage.verifyTopMenuBar());
	}

	@Test
	public void verifyBottomMenuBarIsPresent() throws Exception {
		Assert.assertTrue(viewHomePage.verifyBottomMenuBar());
	}

	@Test
	public void verifyPreMentionedPortletsArePresent() throws Exception {
		Assert.assertTrue(viewHomePage.verifyPreMentionedPortlets());
	}

	@Test
	public void verifyNewsPortletIsPresent() throws Exception {
		Assert.assertTrue(viewHomePage.verifyNewsPortletIsPresent());
	}
}
