package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.SortByPublicationDates;
import webPageContainers4Testing.ViewAllAudioWithFeatured;

public class ViewAllAudioWithFeaturedTest extends BaseWebPageTest{
	
	
	ViewAllAudioWithFeatured viewAllAudioWithFeatured;
	SortByPublicationDates sortByPublDate;
	@BeforeTest
	public void setUP() throws Exception{
		viewAllAudioWithFeatured = new ViewAllAudioWithFeatured();
		sortByPublDate = new SortByPublicationDates();
	}
	@Test
	public void basicSearch() throws Exception {
		doBasicSearchUsingSearchTerm("audio");
	}
	@Test(dependsOnMethods={"basicSearch"})
	public void verifyPublicationDatesSortedForAudio() throws Exception{
		Assert.assertTrue(sortByPublDate.verifyResultsAreSortedByPublDate("audio"));
	}
	@Test(dependsOnMethods={"verifyPublicationDatesSortedForAudio"})
	public void verifyHandPickedAreNotDisplayedOnTop() throws Exception{
		Assert.assertTrue(viewAllAudioWithFeatured.ChangeTheSortOption());
	}
	@Test(dependsOnMethods={"verifyHandPickedAreNotDisplayedOnTop"})
	public void verifyHandPickedItemsOnTop() throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyHandPickedItemsOnTop());
	}
	@Test(dependsOnMethods={"verifyHandPickedItemsOnTop"})
	public void ChangeTheSortOption() throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.ChangeTheSortOption());
	}
	@Test(dependsOnMethods={"ChangeTheSortOption"})
	public void verifyWithDifferentSortOption() throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyWithDifferentSortOption());
	}
	@Test(dependsOnMethods={"verifyWithDifferentSortOption"})
	public void verifyWithDefaultSortOption() throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.ChangeTheSortOption());
	}
	@Test(dependsOnMethods={"verifyWithDefaultSortOption"})
	public void verifyWithOtherSortOption() throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyWithOtherSortOption());
	}
	@Test(dependsOnMethods={"verifyWithOtherSortOption"})
	public void verifyWhenTabsAreChanged()throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyWhenTabsAreChanged());
	}
	@Test(dependsOnMethods={"verifyWhenTabsAreChanged"})
	public void verifyPaginationOfHandPickedItems()throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyPaginationOfHandPickedItems());
	}
	@Test(dependsOnMethods={"verifyPaginationOfHandPickedItems"})
	public void verifyPaginationWithRelavence()throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyPaginationWithRelavence());
	}
	@Test(dependsOnMethods={"verifyPaginationWithRelavence"})
	public void verifyDetailedPageOfHandPickedItem()throws InterruptedException{
		Assert.assertTrue(viewAllAudioWithFeatured.verifyDetailedPageOfHandPickedItem());
	}
	@Test(dependsOnMethods={"verifyDetailedPageOfHandPickedItem"})
	public void verifyHandPickedItemsOnSearchPage()throws InterruptedException{
		doBasicSearchUsingSearchTerm("audio");
		Assert.assertTrue(viewAllAudioWithFeatured.verifyHandPickedItemsOnSearchPage());
	}
	
}
