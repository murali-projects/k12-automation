package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.DocInfoType;

public class DocInfoTypeTest extends BaseWebPageTest {

	public DocInfoType docInfoType;

	@BeforeTest
	public void setUp() throws Exception {
	docInfoType = new DocInfoType();
     }

    @Test
	public void verifyDocInfoTypeIsDisplayedInReference() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("K12-Reference","Relevance"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInReference" })
	public void validateDocInfoTypeWIthOCeanInReference() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-Reference","Relevance"));
	}

	@Test (dependsOnMethods = { "validateDocInfoTypeWIthOCeanInReference" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInReference()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-Reference","Relevance"));
	}
	
	@Test(dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaInReference" })
	public void verifyArticeIsPresentForDontHaveDocInfoType() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-Reference","Relevance"));
	}
	@Test(dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoType" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInReference() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}
    
	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayInAllLinksInReference" })
	public void verifyDocInfoTypeIsDisplayedInWebsites() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("websites","Relevance"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInWebsites" })
	public void validateDocInfoTypeWIthOCeanInWebsites() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("websites","Relevance"));
	}

	@Test (dependsOnMethods = { "validateDocInfoTypeWIthOCeanInWebsites" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaWebsites()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("websites","Relevance"));
	}
	
	@Test(dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaWebsites" })
	public void verifyArticeIsPresentForDontHaveWebsites() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("websites","Relevance"));
	}
	@Test(dependsOnMethods = { "verifyArticeIsPresentForDontHaveWebsites" })
	public void verifyDocInfoTypeIsDisplayInAllLinksWebsites() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayInAllLinksWebsites" })
	public void verifyDocInfoTypeIsDisplayedInPrimarySources() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("primarysources","Relevance"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInPrimarySources" })
	public void validateDocInfoTypeWIthOCeanInPrimarySources() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-PrimarySources","Relevance"));
	}

	@Test(dependsOnMethods = { "validateDocInfoTypeWIthOCeanInPrimarySources" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInPrimarySources()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-PrimarySources","Relevance"));
	}

	@Test(dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaInPrimarySources" })
	public void verifyArticeIsPresentForDontHaveDocInfoTypeInPrimarySources()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("K12-PrimarySources","Relevance"));
	}
	

	@Test (dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoTypeInPrimarySources" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInPrimarySources() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}

	@Test (dependsOnMethods = { "verifyDocInfoTypeIsDisplayInAllLinksInPrimarySources" })
	public void verifyDocInfoTypeIsDisplayedInStatistics() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("statistics","Relevance"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInStatistics" })
	public void validateDocInfoTypeWIthOCeanInStatistics() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("statistics","Relevance"));
	}

	@Test(dependsOnMethods ={"validateDocInfoTypeWIthOCeanInStatistics"})
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInStatistics()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("statistics","Relevance"));
	}

	@Test(dependsOnMethods ={"verifyMultipleDocInfoTypeIsSeparatedByCommaInStatistics"})
	public void verifyArticeIsPresentForDontHaveDocInfoTypeInStatistics()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("statistics","Relevance"));

	}
	
	@Test (dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoTypeInStatistics" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInStatistics() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}
	/*
	@Test 
	public void verifyDocInfoTypeIsDisplayedInMagazines() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("magazines","Date"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInMagazines" })
	public void validateDocInfoTypeWIthOCeanInMagazines() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("magazines","Date"));
	}

	@Test(dependsOnMethods = { "validateDocInfoTypeWIthOCeanInMagazines" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInMagazines()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("magazines","Date"));
	}

	@Test (dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaInMagazines" })
	public void verifyArticeIsPresentForDontHaveDocInfoTypeInMagazines()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("magazines","Date"));
	}
	
	@Test (dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoTypeInMagazines" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInMagazines() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}

	@Test
	public void verifyDocInfoTypeIsDisplayedInNews() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("news","Date"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInNews" })
	public void validateDocInfoTypeWIthOCeanInNews() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("news","Date"));
	}

	@Test(dependsOnMethods = { "validateDocInfoTypeWIthOCeanInNews" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInNews()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("news","Date"));
	}

	@Test(dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaInNews" })
	public void verifyArticeIsPresentForDontHaveDocInfoTypeInNews()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("news","Date"));
	}
	
	@Test (dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoTypeInNews" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInNews() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}

	@Test
	public void verifyDocInfoTypeIsDisplayedInViewPoints() throws Exception {
		doBasicSearchUsingSearchTerm("test");
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayed("viewPoints","Date"));
	}

	@Test(dependsOnMethods = { "verifyDocInfoTypeIsDisplayedInViewPoints" })
	public void validateDocInfoTypeWIthOCeanInViewPoints() throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("viewPoints","Date"));
	}

	@Test(dependsOnMethods = { "validateDocInfoTypeWIthOCeanInViewPoints" })
	public void verifyMultipleDocInfoTypeIsSeparatedByCommaInViewPoints()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("viewPoints","Date"));
	}

	@Test(dependsOnMethods = { "verifyMultipleDocInfoTypeIsSeparatedByCommaInViewPoints" })
	public void verifyArticeIsPresentForDontHaveDocInfoTypeInViewPoints()throws Exception {
		Assert.assertTrue(docInfoType.validateDocInfoTypeWithOcean("viewPoints","Date"));

	}
	@Test (dependsOnMethods = { "verifyArticeIsPresentForDontHaveDocInfoTypeInViewPoints" })
	public void verifyDocInfoTypeIsDisplayInAllLinksInViewPoints() throws Exception {
		Assert.assertTrue(docInfoType.doCheckDocInfoTypeIsDisplayedInAllLinks());
	}*/

}
