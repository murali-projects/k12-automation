package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.QuestionToThinkAbout;

public class QuestionToThinkAboutTest extends BaseWebPageTest{
	
	public QuestionToThinkAbout questionToThinkAbout;
	
	@BeforeTest
	public void setUp() throws Exception{
	questionToThinkAbout = new QuestionToThinkAbout();
	}
	
@Test
public void verifyViewPointsDisplayGroup() throws InterruptedException{
	doBasicSearchUsingSearchTerm("Emphasis on Diversity");
	Assert.assertTrue(questionToThinkAbout.verifyViewPointsDisplayGroup());
}
	
@Test(dependsOnMethods={("verifyViewPointsDisplayGroup")})
public void verifyDetailedDocument() throws InterruptedException {
	Assert.assertTrue(questionToThinkAbout.verifyDetailedDocument());
}

@Test(dependsOnMethods={("verifyDetailedDocument")})
public void verifyQuestionTable() throws InterruptedException {
	Assert.assertTrue(questionToThinkAbout.verifyQuestionTable());
}

@Test(dependsOnMethods={("verifyQuestionTable")})
public void verifyQuestionsInNumbered()throws InterruptedException {
	Assert.assertTrue(questionToThinkAbout.verifyQuestionsInNumbered());
}
@Test(dependsOnMethods={("verifyQuestionsInNumbered")})
public void verifyNotDisplayed()throws InterruptedException {
	doBasicSearchUsingSearchTerm("Emphasis on Diversity");
	Assert.assertTrue(questionToThinkAbout.verifyNotDisplayed("K12-Reference"));
}

}
