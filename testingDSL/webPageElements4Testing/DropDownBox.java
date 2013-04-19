package webPageElements4Testing;

import webPageContainers4Testing.EnvironmentProperties;

public class DropDownBox  extends PageElementWithIdAttribute{
	private String[] optionLocators;
	
	public DropDownBox(String locator) {
		super(locator);
	}

	public void select(String option){
		selenium.select(locator, option);
	}
	
	public int getSelectOptionsCount(){
		return selenium.getXpathCount(this.locator + "/option").intValue();
	}
	
	//For multiSelect
	public void select(String []options){
		selenium.removeAllSelections(locator);
		for(String option : options)
			selenium.addSelection(locator, option);
	}
	
	public String getSelectedOptionLabel(){
		return selenium.getSelectedLabel(this.locator);
	}
	
	public String getSelectedOptionValue(){
		return selenium.getSelectedValue(this.locator);
	}
	
	public String getSelectedIndex(){
		return selenium.getSelectedIndex(locator);
	}
	
	public void setSelectedOptionValue(String selectedValue){
		this.select(selectedValue);
		selenium.waitForPageToLoad(EnvironmentProperties.getInstance().getStandardPageLoadWaitTime());
	}
	
	public String[] getSelectOptions(){
		return selenium.getSelectOptions(this.locator);
	}

	public String[] getSelectedLabels(){
		return selenium.getSelectedLabels(this.locator);
	}
}
