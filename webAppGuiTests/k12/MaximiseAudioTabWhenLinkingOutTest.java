package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ShareBookmarkWithSocialNetworkingSite;
import webPageContainers4Testing.MaximiseAudioTabWhenLinkingOut;

public class MaximiseAudioTabWhenLinkingOutTest extends BaseWebPageTest {

	private MaximiseAudioTabWhenLinkingOut maximiseaudio;

	public String searchTerm = "war";

	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		maximiseaudio = new MaximiseAudioTabWhenLinkingOut();
		this.searchTerm = searchTerm;
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Parameters( { "searchTerm" })
	@Test
	public void verifyWindowMaximisedForAudio(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(maximiseaudio.verifyWindowMaximisedForAudio());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyWindowMaximisedForAudio" })
	public void verifyWindoMaximisedAfterminimised(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(maximiseaudio.verifyWindoMaximisedAfterminimised());
	}

	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods = { "verifyWindoMaximisedAfterminimised" })
	public void verifyWindowMaximisedForSecondTime(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(maximiseaudio.verifyWindowMaximisedForSecondTime());
	}

}
