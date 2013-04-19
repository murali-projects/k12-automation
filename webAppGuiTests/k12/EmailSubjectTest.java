package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.Email;

public class EmailSubjectTest extends BaseWebPageTest{
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
	public void verifyDocumentTitleInSubjectField() throws Exception {
		Assert.assertTrue(email
				.verifyDocumentTitleInSubjectField());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentTitleInSubjectField" })
	public void verifyEmailOptionForOtherDisplayGroup() throws Exception {
		Assert.assertTrue(email
				.verifyEmailOptionForOtherDisplayGroup());
	}
	
	@Test(dependsOnMethods = { "verifyEmailOptionForOtherDisplayGroup" })
	public void verifyDocumentTitleForOtherDisplayGroup() throws Exception {
		Assert.assertTrue(email
				.verifyDocumentTitleForOtherDisplayGroup());
	}
	
	@Test(dependsOnMethods = { "verifyDocumentTitleForOtherDisplayGroup" })
	public void verifySubjectFieldIsEditable() throws Exception {
		Assert.assertTrue(email
				.verifySubjectFieldIsEditable());
	}
}
