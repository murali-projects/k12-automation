package webPageContainers4Testing;

import webPageElements4Testing.PageButton;
import webPageElements4Testing.TextBox;

public class LoginPage extends BasePageContainer {
	 
	 private TextBox userGroupNameBox;
	 private TextBox passwordBox;
	 private PageButton submitButton;
	 
	 public LoginPage() throws Exception {
	  super();
	  userGroupNameBox = new TextBox("//input[@name='locid']");
	  passwordBox = new TextBox("//input[@name='locpword']");
	  submitButton = new PageButton("//input[@value='Authenticate']");
	 }
	 
	 public void logInAs(String userGroupName, String password) {
	  userGroupNameBox.type(userGroupName);
	  passwordBox.type(password);
	  submitButton.clickAndWait();
	 }
	 
	}
