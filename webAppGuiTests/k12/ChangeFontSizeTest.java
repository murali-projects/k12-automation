package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.ChangeFontSize;

public class ChangeFontSizeTest extends BaseWebPageTest {

	public ChangeFontSize changeFontSize;

	@BeforeTest
	public void setup() throws Exception {
		changeFontSize = new ChangeFontSize();
	}

	@Test
	public void basicSearch() throws InterruptedException {
		doBasicSearchUsingSearchTerm("war");
	}

	@Test(dependsOnMethods={"basicSearch"})
	public void verifyChangeFontSizeIsDisplayed() throws InterruptedException {
		Assert.assertTrue(changeFontSize.verifyChangeFontSizeIsPresent());
	}
    
	@Test(dependsOnMethods={"verifyChangeFontSizeIsDisplayed"})
	public void verifyLargerSizeIsWorking() throws InterruptedException{
		Assert.assertTrue(changeFontSize.verifyLargerSizeIsWorking());
	}
	@Test(dependsOnMethods={"verifyLargerSizeIsWorking"})
	public void verifySmallerSizeIsWorking() throws InterruptedException{
		Assert.assertTrue(changeFontSize.verifySmallerSizeIsWorking());
	}
	
	@Test(dependsOnMethods={"verifySmallerSizeIsWorking"})
	public void verifyFontSizeOptionsInDetailedNavigation() throws InterruptedException{
		doBasicSearchUsingSearchTerm("war");
		Assert.assertTrue(changeFontSize.verifyFontSizeOptionsInDetailedNavigation());
	}
	
}
