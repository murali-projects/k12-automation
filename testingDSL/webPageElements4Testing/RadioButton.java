package webPageElements4Testing;

public class RadioButton extends PageElementWithIdAttribute{
	
	public RadioButton(String locator) {
		super(locator);
	}

	public boolean isChecked(){
		return selenium.isChecked(this.locator);
	}
	
	public boolean isEditable(){
		return selenium.isEditable(this.locator);
	}
	
	public void check(){
		selenium.check(this.locator);
	}
	
	public void click() {
		selenium.click(this.locator);
	}

}
