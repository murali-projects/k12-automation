package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.LinkToThirdPartyAudio;

public class LinkToThirdPartyAudioTest extends BaseWebPageTest{
	private LinkToThirdPartyAudio linkToThirdPartyAudio;

	@Parameters( {"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		linkToThirdPartyAudio = new LinkToThirdPartyAudio();
	}
	
	@Test
	public void verifySpeakerIconIsPresent()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifySpeakerIconIsDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifySpeakerIconIsPresent" })
	public void verifyAfterClickingSpeakerIcon()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifyAfterClickingSpeakerIcon());
	}
	
	@Test(dependsOnMethods = { "verifyAfterClickingSpeakerIcon" })
	public void verifyAudioTitle()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifyAudioTitle());
	}
	
	@Test(dependsOnMethods = { "verifyAudioTitle" })
	public void verifyAfterclickingSpeakerIconInNewWindow()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifyAfterclickingSpeakerIconInNewWindow());
	}
	
	@Test(dependsOnMethods = { "verifyAfterclickingSpeakerIconInNewWindow" })
	public void verifyNoErrorMessageIsDisplayed()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifyErrorMessageNotDisplayed());
	}
	
	@Test(dependsOnMethods = { "verifyNoErrorMessageIsDisplayed" })
	public void verifyPlayPauseOptionsAvailable()throws Exception{
		Assert.assertTrue(linkToThirdPartyAudio.verifyPlayPauseOptionsAvailable());
	}
	
}
