package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateRelatedTopicsPortlet;
import webPageContainers4Testing.LinkToRelatedSubjects;


public class LinkToRelatedSubjectTest extends BaseWebPageTest{
 
	public LinkToRelatedSubjects linkToRelatedSubjects;
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
	
		linkToRelatedSubjects= new LinkToRelatedSubjects();
    }
	
	

	@Parameters( { "searchTerm" })
	@Test
	public void verifyIntegrateRelatedTopicsPresent(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(linkToRelatedSubjects.verifyIntegrateRelatedTopicsPresent());

	}
	
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods={"verifyIntegrateRelatedTopicsPresent"})
	public void verifyTermsDisplayedInRelatedTopics(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(linkToRelatedSubjects.verifyTermsDisplayedInRelatedTopics());

	}
	
	
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods={"verifyTermsDisplayedInRelatedTopics"})
	public void verifyRelatedTopicsFromViewAll(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(linkToRelatedSubjects.verifyRelatedTopicsFromViewAll());

	}
	
	
	
	@Parameters( { "searchTerm" })
	@Test(dependsOnMethods={"verifyRelatedTopicsFromViewAll"})
	public void verifyPageNavigationByClickingSubjects(String searchTerm)
			throws Exception {
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(linkToRelatedSubjects.verifyPageNavigationByClickingSubjects());

	}
	
	
	
}
