package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.HitcountAndSortingDocumentRelatedSubjects;
import webPageContainers4Testing.IntegrateRelatedTopicsPortlet;
import webPageContainers4Testing.TranslateTextDocument;

public class IntegrateRelatedTopicsPortletTest extends BaseWebPageTest {

	public IntegrateRelatedTopicsPortlet integrageRelatedtopicsPortlet;
	
	
	@Parameters({"searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
   		doBasicSearchUsingSearchTerm(searchTerm);
   		integrageRelatedtopicsPortlet= new IntegrateRelatedTopicsPortlet();
    }
	
	
    @Parameters({"searchTerm"})
	@Test
	public void verifyTopicsAssociatedIsDisplayed(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyTopicsAssociatedIsDisplayed());
				
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyTopicsAssociatedIsDisplayed")
	public void verifyOtherPortletPresent(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyOtherPortletPresent());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyOtherPortletPresent")
	public void verifyViewAllPresentForOtherBuckets(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyViewAllPresentForOtherBuckets());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyViewAllPresentForOtherBuckets")
	public void verifyClickingTopicsInPortlet(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyClickingTopicsInPortlet());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyClickingTopicsInPortlet")
	public void verifyTopicsPortletNotPresentInOtherPages(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyTopicsPortletNotPresentInOtherPages());
	}
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyClickingTopicsInPortlet")
	public void verifyDetailedOverviewIsPresent(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyDetailedOverviewIsPresent());
	}
	
	@Parameters({"searchTerm"})
	@Test(dependsOnMethods="verifyClickingTopicsInPortlet")
	public void verifyOtherBucketsalongWithDetailedOverview(String searchTerm)throws Exception{
		doBasicSearchUsingSearchTerm(searchTerm);
		Assert.assertTrue(integrageRelatedtopicsPortlet.verifyOtherBucketsalongWithDetailedOverview());
	}
	
	
	
	
	
}
