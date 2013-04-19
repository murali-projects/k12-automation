package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.CreateAPABookCitableType;

public class CreateAPABookCitableTypeTest extends BaseWebPageTest{

	private CreateAPABookCitableType apaBookCitableType;
	@Parameters( { "searchTerm" })
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		apaBookCitableType = new CreateAPABookCitableType();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyCitationToolsAvailable() throws Exception {
		Assert.assertTrue(apaBookCitableType.verifyCitationToolsAvailable());
	}

	@Test(dependsOnMethods = { "verifyCitationToolsAvailable" })
	public void verifyAPARadioButtonAvailable() throws Exception {
		Assert.assertTrue(apaBookCitableType.verifyAPARadioButtonAvailable());
	}
	
	@Test(dependsOnMethods = { "verifyAPARadioButtonAvailable" })
	public void verifyAPACitationInDocument() throws Exception {
		Assert.assertTrue(apaBookCitableType.verifyAPACitationInDocument());
	}
}
