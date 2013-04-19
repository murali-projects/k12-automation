package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAudioDocuments;

public class ViewAudioDocumentsTest extends BaseWebPageTest {
	private ViewAudioDocuments viewAudioDoucuments;


	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		viewAudioDoucuments = new ViewAudioDocuments();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void checkAudioArticlesDisplayGroupIsPresent() throws Exception {
		Assert.assertTrue(viewAudioDoucuments
				.checkAudioArticlesDisplayGroupIsPresent());
	}

	@Test
	public void checkAudioContentLinksArePresent() throws Exception {
		Assert.assertTrue(viewAudioDoucuments
				.checkAudioContentLinksArePresent());
	}

	@Test
	public void verifyContentItemIsViewed() throws Exception {
		Assert.assertTrue(viewAudioDoucuments.verifyContentItemIsViewed());
	}

	@Test
	public void verifyDetailedViewIsDisplayed() throws Exception {
		Assert.assertTrue(viewAudioDoucuments.verifyDetailedViewIsDisplayed());
	}
}
