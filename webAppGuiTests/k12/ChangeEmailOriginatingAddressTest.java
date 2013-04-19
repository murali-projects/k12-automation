package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.Email;

public class ChangeEmailOriginatingAddressTest extends BaseWebPageTest{
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
	public void verifySenderFieldIsEditable() throws Exception {
		Assert.assertTrue(email
				.verifySenderFieldIsEditable());
	}
	
	@Parameters({"emailId"})
	@Test(dependsOnMethods = { "verifySenderFieldIsEditable" })
	public void verifyEmailIdInSenderField(String emailId) throws Exception {
		Assert.assertTrue(email
				.verifyEmailIdInSenderField(emailId));
	}
	
	@Test(dependsOnMethods = { "verifyEmailIdInSenderField" })
	public void verifyEmailOptionForOtherDisplayGroup() throws Exception {
		Assert.assertTrue(email
				.verifyEmailOptionForOtherDisplayGroup());
	}
	
	@Parameters({"emailId"})
	@Test(dependsOnMethods = { "verifyEmailOptionForOtherDisplayGroup" })
	public void verifyEmailIdInSenderFieldForOtherDisplayGroup(String emailId) throws Exception {
		Assert.assertTrue(email
				.verifyEmailIdInSenderFieldForOtherDisplayGroup(emailId));
	}
}
