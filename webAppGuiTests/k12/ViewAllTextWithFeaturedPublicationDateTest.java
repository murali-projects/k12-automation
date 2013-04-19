package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ViewAllTextWithFeatured;

public class ViewAllTextWithFeaturedPublicationDateTest extends BaseWebPageTest{
	
public ViewAllTextWithFeatured viewAllTextWithFeatured;

@BeforeTest
 public void setUp() throws Exception{
	viewAllTextWithFeatured = new ViewAllTextWithFeatured();
}
@Test
public void verifyViewAllPageNavigatedForReference() throws Exception{
	doBasicSearchUsingSearchTerm("adoption");
	Assert.assertTrue(viewAllTextWithFeatured.verifyViewAllPageNavigated("Reference"));
}
@Test(dependsOnMethods={"verifyViewAllPageNavigatedForReference"})
public void verifyViewAllPageContainsHandPickedForReference() throws Exception{
	doBasicSearchUsingSearchTerm("adoption");
	Assert.assertTrue(viewAllTextWithFeatured.verifyViewAllPage("Reference"));
}
@Test(dependsOnMethods={"verifyViewAllPageContainsHandPickedForReference"})
public void verifyChangeSortToPubDateForReference() throws Exception{
	Assert.assertTrue(viewAllTextWithFeatured.verifyChangeSortToPubDate());
}
@Test(dependsOnMethods={"verifyChangeSortToPubDateForReference"})
public void verifyPaginationForReference()throws Exception{
	Assert.assertTrue(viewAllTextWithFeatured.verifyPagination());
}
@Test(dependsOnMethods={"verifyPaginationForReference"})
public void verifyHandpickedItemsForReference()throws Exception{
	Assert.assertTrue(viewAllTextWithFeatured.verifyHandpickedItems());
}
@Test(dependsOnMethods={"verifyHandpickedItemsForReference"})
public void verifyWhenBackToPortalForReference()throws Exception{
	Assert.assertTrue(viewAllTextWithFeatured.verifyWhenBackToPortal("Reference"));
}

}
