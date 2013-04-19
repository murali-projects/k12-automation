package webPageContainers4Testing;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;

public class TranslationsDisclaimer extends BasePageContainer{

	
	String ExpectedDesclaimer="Disclaimer: You have requested a machine translation of selected content from our database";
	String ExpectedbottomDesclaimer="Disclaimer: You have requested a machine translation of selected content from our databases. This functionality is provided solely for your convenience and is in no way intended to replace human translation. Neither Gale nor its licensors make any representations or warranties with respect to the translations. The translations are automatically generated \"AS IS\" and \"AS AVAILABLE\" and are not retained in our systems. GALE AND ITS LICENSORS SPECIFICALLY DISCLAIM ANY AND ALL EXPRESS OR IMPLIED WARRANTIES, INCLUDING WITHOUT LIMITATION, ANY WARRANTIES FOR AVAILABILITY, ACCURACY, TIMELINESS, COMPLETENESS, NON-INFRINGEMENT, MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Your use of the translations is subject to all use restrictions contained in The Gale Group Subscription and License Agreement and/or the Gale Virtual Reference Library Terms and Conditions and by using the translation functionality you agree to forgo any and all claims against Gale or its licensors for your use of the translation functionality and any output derived therefrom. ";
	public TranslationsDisclaimer() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean verifyTranslateOptionAvailable() throws Exception{
		new Link(properties.get("news_link")).click();
		return (new PageElementWithIdAttribute(properties.get("translate_link")).isPresent() );	
		
	}
	
	public boolean verifyNewWindowOpening()throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
	    selenium.close();		
		return true;
	}
	
	public boolean verifyDisclaimerOnTopOfWindow() throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(3000);
        String ActualDesclaimar=new TextLabel(properties.get("desclaimer")).getLabelText();
		if(ActualDesclaimar.contains(ExpectedDesclaimer)){
			selenium.close();
			return true;
		}
		return false ;
		
	}
	

	public boolean verifyDisclaimerIsLink() throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(3000);
		if(new PageElementWithIdAttribute(properties.get("desclaimer")).isPresent() ){
			
			selenium.close();
			return true;
		}
		return false;
		
	}
	
	public boolean verifyBottomDisclaimer() throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(3000);
        String ActualbottomDesclaimar=new TextLabel(properties.get("bottom_desclaimer")).getLabelText();
		
		return (ActualbottomDesclaimar.equalsIgnoreCase(ExpectedbottomDesclaimer));
		

	}
	
	public boolean verifyDisclaiamerLanguageIsEnglish()throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(3000);
		String desc="Disclaimer";
		String ActualDesclaimar=new TextLabel(properties.get("desclaimer")).getLabelText();
		if(ActualDesclaimar.contains(desc)){
			//selenium.close();
			return true;
		}
		return false ;
			
	}
	
	public boolean verifyBottomDesclaimerDoesntHaveLink()throws Exception{
		new Link(properties.get("news_link")).click();
		new Link(properties.get("translate_link")).clickWithoutWait();
		Thread.sleep(1000);
		new PageButton(properties.get("translate_popup_button")).click();
		new Url().selectWindow("translateDocument");
		Thread.sleep(3000);
		return (!new PageElementWithIdAttribute(properties.get("bottom_desclaimer")).isPresent() );
	}
	
	
	
	
	
	
	
}
