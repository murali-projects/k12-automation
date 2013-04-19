package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.AccessBottomMenuBar;

public class AccessBottomMenuBarTest extends BaseWebPageTest{
protected AccessBottomMenuBar bottomMenuBar;
	
	private String aboutThinkLink;
	private String aboutGaleLink;
	private String contactUsLink;
	private String copyrightLink;
	private String termsOfUseLink;
	private String privacyPolicyLink;
	private String endSessionLink;

	@Parameters({"aboutThinkLink","aboutGaleLink","contactUsLink","copyrightLink","termsOfUseLink","privacyPolicyLink","endSessionLink"})
	@BeforeTest
	public void setUp(String aboutThinkLink, String aboutGaleLink,
			String contactUsLink, String copyrightLink, String termsOfUseLink,
			String privacyPolicyLink, String endSessionLink) throws Exception {
		
		bottomMenuBar = new AccessBottomMenuBar();
		this.aboutThinkLink = aboutThinkLink;
		this.aboutGaleLink = aboutGaleLink;
		this.contactUsLink = contactUsLink;
		this.copyrightLink = copyrightLink;
		this.termsOfUseLink = termsOfUseLink;
		this.privacyPolicyLink = privacyPolicyLink;
		this.endSessionLink = endSessionLink;
	}
	
	@Test
	public void verifyAboutThinkLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(aboutThinkLink));
	}
	
	@Test(dependsOnMethods = "verifyAboutThinkLinkIsDisplayed")
	public void verifyAboutGaleLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(aboutGaleLink));
	}
	
	@Test(dependsOnMethods = "verifyAboutGaleLinkIsDisplayed")
	public void verifyContactUsLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(contactUsLink));
	}
	
	@Test(dependsOnMethods = "verifyContactUsLinkIsDisplayed")
	public void verifyCopyrightLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(copyrightLink));
	}
	
	@Test(dependsOnMethods = "verifyCopyrightLinkIsDisplayed")
	public void verifyTermsofUseLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(termsOfUseLink));
	}
	
	@Test(dependsOnMethods = "verifyTermsofUseLinkIsDisplayed")
	public void verifyPrivacyPolicyLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(privacyPolicyLink));
	}
	
	@Test(dependsOnMethods = "verifyPrivacyPolicyLinkIsDisplayed")
	public void verifyEndSessionLinkIsDisplayed()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyBottomMenuBarContainsLink(endSessionLink));
	}
	
	@Test(dependsOnMethods = "verifyPrivacyPolicyLinkIsDisplayed")
	public void isNewWindowOpenedOnClickingAboutThink()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(aboutThinkLink));
	}
	
	@Test(dependsOnMethods = "verifyEndSessionLinkIsDisplayed")
	public void isNewWindowOpenedOnClickingAboutGale()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(aboutGaleLink));
	}
	
	@Test(dependsOnMethods = "isNewWindowOpenedOnClickingAboutGale")
	public void isNewWindowOpenedOnClickingContactUs()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(contactUsLink));
	}
	
	@Test(dependsOnMethods = "isNewWindowOpenedOnClickingContactUs")
	public void isNewWindowOpenedOnClickingCopyright()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(copyrightLink));
	}
	
	@Test(dependsOnMethods = "isNewWindowOpenedOnClickingCopyright")
	public void isNewWindowOpenedOnClickingTermsOfUse()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(termsOfUseLink));
	}
	
	@Test(dependsOnMethods = "isNewWindowOpenedOnClickingTermsOfUse")
	public void isNewWindowOpenedOnClickingPrivacyPolicy()throws Exception{
		Assert.assertTrue(bottomMenuBar.isNewWindowOpenedOnClickingLink(privacyPolicyLink));
	}
	
	@Test(dependsOnMethods = "isNewWindowOpenedOnClickingPrivacyPolicy")
	public void verifyEndSessionLinkIsWorking()throws Exception{
		Assert.assertTrue(bottomMenuBar.verifyEndSessionLinkIsWorking());
	}
}
