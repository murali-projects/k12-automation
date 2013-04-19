package webPageElements4Testing;


public class TextBox extends PageElementWithIdAttribute {

	public TextBox(String locator) throws Exception {
		super(locator);
	}

	public void type(String text) {
		selenium.type(locator, text);
	}
	
	public boolean isEditable(){
		return selenium.isEditable(locator);
	}
	
	public String getValue(){
		return selenium.getValue(locator);
	}
	
	public void typeSearchText(String text) throws Exception {
		selenium.type(locator, "");
		selenium.typeKeys(locator, text);
	}
	
	public void typeKeys(String text) {
		selenium.typeKeys(locator, text);
	}
	
}
