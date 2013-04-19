package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.Email;

public class EmailAttachmentsTest extends BaseWebPageTest{
	private Email email;

	@Parameters( { "searchTerm"})
	@BeforeTest
	public void setUp(String searchTerm) throws Exception {
		email = new Email();
		doBasicSearchUsingSearchTerm(searchTerm);
	}

	@Test
	public void verifyEmailOption() throws Exception {
		Assert.assertTrue(email.verifyEmailOption());
	}

	@Test(dependsOnMethods = { "verifyEmailOption" })
	public void verifyPresenceOfRadioButtons() throws Exception {
		Assert.assertTrue(email
				.verifyPresenceOfRadioButtons());
	}
	
	@Test(dependsOnMethods = { "verifyPresenceOfRadioButtons" })
	public void verifyDefaultOptionForRadioButton() throws Exception {
		Assert.assertTrue(email
				.verifyDefaultOptionForRadioButton());
	}
	
}
