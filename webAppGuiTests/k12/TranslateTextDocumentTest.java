package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import org.testng.annotations.Test;

import webPageContainers4Testing.TranslateTextDocument;

public class TranslateTextDocumentTest extends BaseWebPageTest {
   public TranslateTextDocument translateTextDocument;
	
   	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
   		doBasicSearchUsingSearchTerm(searchTerm);
		translateTextDocument= new TranslateTextDocument();
    }
@Parameters({"searchTerm"})
	@Test
	public void verifyIsTranslateOptionAvailableFromViewAll(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyTranslateOptionAvailableFromViewAll());
				
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsTranslateOptionAvailableFromViewAll")
	public void verifyIsTranslateOptionAvailableFromSearchResultsPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyTranslateOptionAvailableFromSearchResultsPage());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsTranslateOptionAvailableFromSearchResultsPage")
	public void verifyIsTranslateOptionAvailableFromDetailedPage(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyTranslateOptionAvailableFromDetailedPage());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsTranslateOptionAvailableFromDetailedPage")
	public void verifyIsTranslateNotAppliedWhenButtonNotPressed(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyTranslateNotAppliedWhenButtonNotPressed());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsTranslateNotAppliedWhenButtonNotPressed")
	public void verifyAvailableOptionsInDropDownBox(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyOptionsInDropDownBox());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsTranslateNotAppliedWhenButtonNotPressed")
	public void verifyIsMultipleSelectNotAllowed(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyMultipleSelectNotAllowed());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyAvailableOptionsInDropDownBox")
	public void verifySelectionOfSpanishOption(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifySelectingSpanishOption());
	}
@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifySelectionOfSpanishOption")
	public void verifyIsLanguageChanged(String searchTerm)throws Exception{
		selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyLanguageChanged());
	}
	

@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsLanguageChanged")
	public void verifyIsLanguageChangingForAllLanguages(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyLanguageChangedForAllLanguages());
		
	}
	@Test
	public void verifyIsPrintOptionWorkingForTranslatedText()throws Exception{
		Assert.assertTrue(translateTextDocument.verifyPrintOptionForTranslatedText());
	}
	@Test
	public void verifyIsPrintPopupWorkingForTranslatedText()throws Exception{
		Assert.assertTrue(translateTextDocument.verifyPrintPopupForTranslatedText());
	}
	
	@Test
	public void verifyCancellationOfPrintpopupForTranslatedText()throws Exception{
		Assert.assertTrue(translateTextDocument.verifyCancelPrintpopupForTranslatedText());
	}
	@Test
	public void verifyIsDownloadOptionAvailableForTranslatedText()throws Exception{
		Assert.assertTrue(translateTextDocument.verifyDownloadOptionAvailableForTranslatedText());
	}
	@Test
	public void verifyIsCancelDownloadPopupWorkingForTranslatedText()throws Exception{
		Assert.assertTrue(translateTextDocument.verifyCancelDownloadPopupForTranslatedText());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsLanguageChangingForAllLanguages")
	public void verifyIsDesclaimerAvailableForTranslatedDocument(String searchTerm)throws Exception{
		selenium.selectWindow("");
		   doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyDesclaimerForTranslatedDocument());
	}
	
	
   @Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsLanguageChanged")
	public void verifyIsCitationAvaialableForTranslatedDocument(String searchTerm)throws Exception{
	   selenium.selectWindow("");
	   doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.verifyCitationForTranslatedDocument());
	}
   	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyIsCitationAvaialableForTranslatedDocument")
	public void verifyCanTranlatedDocumentBeClosed(String searchTerm)throws Exception{
   	    selenium.selectWindow("");
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.canTranlatedDocumentBeClosed());
	}
   	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyCanTranlatedDocumentBeClosed")
	public void veirifyWhetherSelectedOptionAvailableinDropDownAfterActionPerformed(String searchTerm)throws Exception{
   		selenium.selectWindow("");
   		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(translateTextDocument.isSelectedOptionAvailableinDropDownAfterActionPerformed());
	}
}
