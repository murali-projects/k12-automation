package webPageElements4Testing;


public class CheckBox extends PageElementWithIdAttribute {
 
	public CheckBox(String locator) {
		super(locator);
	}

	public void click() {
		selenium.click(locator);
	}

	public boolean isChecked(){
		return selenium.isChecked(locator);
	}
}
