package k12.dev;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.dev.PortalPortlet;
import webPageElements4Testing.TextLabel;

public class PageFooterTest extends BaseDevWebPageTest {

	private PortalPortlet homePage;

	@BeforeTest
	public void setUp() throws Exception {
		homePage = new PortalPortlet();
	}

	@Test
	public void isFooterPresent() throws Exception {
		Assert.assertTrue(homePage.doesContentExistInClass(homePage.getProperty("footerDiv"), "footerGaleLogo"));
	}

	@Test(dependsOnMethods = { "isFooterPresent" })
	public void validateLogo() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerGaleLogo");

		Assert.assertTrue(content.getLabelText() != null && content.getLabelText().equals("Gale | Cengage"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com");
	}

	@Test(dependsOnMethods = { "validateLogo" })
	public void validateAboutOVRC() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerAboutOVRC");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("About Think Opposing Viewpoints"));
		//Assert.assertEquals(content.getAttribute("href"), "#");
	}

	@Test(dependsOnMethods = { "validateAboutOVRC" })
	public void validateAboutGale() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerAboutGale");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("About Gale"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com");
	}

	@Test(dependsOnMethods = { "validateAboutGale" })
	public void validateContactUs() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerContactUs");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("Contact Us"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com/contact.htm");
	}

	@Test(dependsOnMethods = { "validateContactUs" })
	public void validateTermsOfUse() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerTermsOfUse");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("Terms of Use"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com/epcopyright/index.htm#terms");
	}

	@Test(dependsOnMethods = { "validateTermsOfUse" })
	public void validatePrivacyPolicy() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerPrivacyPolicy");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("Privacy Policy"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com/epcopyright/index.htm#privacy");
	}

	@Test(dependsOnMethods = { "validateTermsOfUse" })
	public void validateCopyright() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerCopyright");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("Copyright"));
		Assert.assertEquals(content.getAttribute("href"), "http://gale.cengage.com/epcopyright/index.htm#copyright");
	}

	@Test(dependsOnMethods = { "validateCopyright" })
	public void validateEndSession() throws Exception {
		TextLabel content = homePage.getTextLabel("class", homePage.getProperty("footerDiv"), "footerEndSession");

		Assert.assertTrue(content.getLabelText() != null
				&& content.getLabelText().equals("End Session"));
		Assert.assertTrue(content.getAttribute("href").endsWith("/SessionPage"));
	}
}
