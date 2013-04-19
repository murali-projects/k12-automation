package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.UpdateTranslationTooltoUseSeparateDialogBox;

public class UpdateTranslationTooltoUseSeparateDialogBoxTest extends BaseWebPageTest{
	
	private UpdateTranslationTooltoUseSeparateDialogBox translationTool;
	private String displayGroup;
	
	@Parameters( { "searchTerm","displayGroup"})
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		translationTool = new UpdateTranslationTooltoUseSeparateDialogBox();
		this.displayGroup=displayGroup;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyTranslateOption()throws Exception {
		Assert.assertTrue(translationTool.verifyTranslateOption(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyTranslateOption" })
	public void verifyTranslateDialogBox() throws Exception {
		Assert.assertTrue(translationTool.verifyTranslateDialogBox(displayGroup));
	}

	@Test(dependsOnMethods = { "verifyTranslateDialogBox" })
	public void verifyOptionsInTranslateDialogBox() throws Exception {
		Assert.assertTrue(translationTool.verifyOptionsInTranslateDialogBox(displayGroup));
	}

	@Parameters( { "language"})
	@Test(dependsOnMethods = { "verifyOptionsInTranslateDialogBox" })
	public void verifySelectionOfTranslate(String language) throws Exception {
		Assert.assertTrue(translationTool.verifySelectionOfTranslate(language,displayGroup));
	}
	
	@Parameters( { "language"})
	@Test(dependsOnMethods = { "verifySelectionOfTranslate" })
	public void verifyCancelOfTranslate(String language) throws Exception {
		Assert.assertTrue(translationTool. verifyCancelOfTranslate(language,displayGroup));
	}
	
	@Parameters( { "language"})
	@Test(dependsOnMethods = { "verifyCancelOfTranslate" })
	public void verifySelectionOfTranslateAfterCancelling(String language) throws Exception {
		Assert.assertTrue(translationTool. verifySelectionOfTranslateAfterCancelling(language,displayGroup));
	}
	
	@Test(dependsOnMethods = { "verifySelectionOfTranslateAfterCancelling" })
	public void verifyDialogBoxIsClosed() throws Exception {
		Assert.assertTrue(translationTool.verifyDialogBoxIsClosed(displayGroup));
	}
	
	@Test(dependsOnMethods = { "verifyDialogBoxIsClosed" })
	public void verifyTranslateFromViewAll()  throws Exception {
		Assert.assertTrue(translationTool.verifyTranslateFromViewAll(displayGroup));
	}


}
