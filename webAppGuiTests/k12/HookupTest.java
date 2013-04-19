package k12;

import org.testng.annotations.Test;
import webPageContainers4Testing.HomePage;

public class HookupTest extends BaseWebPageTest {
	public HookupTest() throws Exception {
		super();
	}

	@Test
	public void shouldLaunchFireFoxAndBringUpHomePage() throws Exception {
		HomePage homePage = new HomePage();
		homePage.verifyTitleContains(HomePage.HOME_PAGE_TITLE);
	}
}
