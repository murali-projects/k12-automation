package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.IntegrateMLA7Service;

public class IntegrateMLA7ServiceTest extends BaseWebPageTest{
	private IntegrateMLA7Service integrateMLA7Service;


	@Parameters( { "searchTerm", "displayGroup"})
	@BeforeTest
	public void setUp(String searchTerm, String displayGroup) throws Exception {
		integrateMLA7Service = new IntegrateMLA7Service(displayGroup);
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void checkSourceCitationIsPresent() throws Exception {
		Assert.assertTrue(integrateMLA7Service.checkSourceCitationIsDisplayed());
	}

	@Test(dependsOnMethods = { "checkSourceCitationIsPresent" })
	public void verifyCitationFormat() throws Exception {
		Assert.assertTrue(integrateMLA7Service
				.checkSourceCitationFormat());
	}
	
	@Test(dependsOnMethods = { "verifyCitationFormat" })
	public void verifySourceCitationStartsWithAuthor() throws Exception {
		Assert.assertTrue(integrateMLA7Service.verifyCitationStartsWithAuthor());
	}
	
	@Test(dependsOnMethods = { "verifySourceCitationStartsWithAuthor" })
	public void verifyVolumeNumber() throws Exception {
		Assert.assertTrue(integrateMLA7Service.verifyVolumeNumber());
	}
	
	@Test(dependsOnMethods = { "verifyVolumeNumber" })
	public void verifyPageNumbersFormat() throws Exception {
		Assert.assertTrue(integrateMLA7Service.verifyPageNumbersFormat());
	}
}
