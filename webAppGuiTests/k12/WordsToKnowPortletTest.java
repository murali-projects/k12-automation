package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.WordsToKnowPortlet;

public class WordsToKnowPortletTest extends BaseWebPageTest{
	
	public WordsToKnowPortlet wordsToKnowPortlet;
	
	@BeforeTest
	public void setUP() throws Exception{
		wordsToKnowPortlet= new WordsToKnowPortlet();
	}
@Parameters("searchTerm")
@Test
public void verifyDisplayGroup(String searchTerm) throws Exception{
	doBasicSearchUsingSearchTerm(searchTerm);
	Assert.assertTrue(wordsToKnowPortlet.verifyDisplayGroup());
}

@Test(dependsOnMethods={"verifyDisplayGroup"})
public void verifyDetailedDocument() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyDetailedDocument());
}
	

@Test(dependsOnMethods={"verifyDetailedDocument"})
public void verifyWordsPortlet() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyWordsPortlet());
}

@Test(dependsOnMethods={"verifyWordsPortlet"})
public void verifyWordsPortletHighlighted() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyWordsPortletHighlighted());
}

@Test(dependsOnMethods={"verifyWordsPortletHighlighted"})
public void verifyDefinitionOfWord() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyDefinitionOfWord());
}

@Test(dependsOnMethods={"verifyDefinitionOfWord"})
public void verifyWordsList() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyWordsList());
}
@Test(dependsOnMethods={"verifyWordsList"})
public void verifyWordsToKnowHeading() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyWordsToKnowHeading());
}

@Test(dependsOnMethods={"verifyWordsToKnowHeading"})
public void verifyMeaningandPartsOfSpeechAvailable() throws Exception{
	Assert.assertTrue(wordsToKnowPortlet.verifyMeaningandPartsOfSpeechAvailable());
}
}
