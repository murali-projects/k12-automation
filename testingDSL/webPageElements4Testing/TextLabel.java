package webPageElements4Testing;

public class TextLabel extends PageElementWithIdAttribute {

	public TextLabel(String locator) {
		super(locator);
	}

	public String getLabelText(String locator) {
		return selenium.getText(locator);
	}
	public String getLabelText() {
		return selenium.getText(this.locator);
	}
	
	public int getXpathCount(){
		 Number number = selenium.getXpathCount(this.locator);	
		 return number.intValue();
	}
	
	public String getAttribute(String attributeValue){
		return selenium.getAttribute(this.locator+"@"+attributeValue);	
	}
	
	public String getAttribute(){
		return selenium.getAttribute(this.locator);	
	}
}
