package webPageContainers4Testing;

import java.awt.event.KeyEvent;

import utils.RobotWebBrowser;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.Url;

public class DisablePdfDownloadforImagesStatisticsAndWebsites extends BasePageContainer{

	public DisablePdfDownloadforImagesStatisticsAndWebsites() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public boolean verifyDownloadOptionAvailableForImageStatisticAndWebsites() throws Exception{
		new Link(properties.get("images_link")).click();
		if(new PageElementWithIdAttribute(properties.get("download_link"))
		.isPresent()){
			new Url().goBackToPreviousPage();
			new Link(properties.get("websites_link")).click();
		if(new PageElementWithIdAttribute(properties.get("download_link"))
		.isPresent())
		{new Url().goBackToPreviousPage();
			new Link(properties.get("statistics_link")).click();
		if(new PageElementWithIdAttribute(properties.get("download_link"))
		.isPresent())
			return true;	
		return false;
		}
		
		}
				return true;
	}
	
	
	public boolean verify3optionsavailableForDownload() throws Exception{
		
		new Link(properties.get("images_link")).click();
		Thread.sleep(10000);
		new Link(properties.get("download_link")).clickWithoutWait();
		Thread.sleep(10000);
		if(new PageElementWithIdAttribute(properties.get("html_download_Option"))
		.isPresent()){
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
			new Link(properties.get("websites_link")).click();
			new Link(properties.get("download_link")).clickWithoutWait();
		if(new PageElementWithIdAttribute(properties.get("html_download_Option"))
		.isPresent()&&new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent())
		{
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
			new Link(properties.get("statistics_link")).click();
			new Link(properties.get("download_link")).clickWithoutWait();
		if(new PageElementWithIdAttribute(properties.get("html_download_Option"))
		.isPresent())
		{
			new Url().goBackToPreviousPage();
			return true;
		}
			return false;	
		
		}
				}
				return true;
		}
	
	
	public boolean verifyPdfRadioButtonNotAvailable() throws Exception{
		new Link(properties.get("images_link")).click();
		new Link(properties.get("download_link")).clickWithoutWait();
		if(!new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent()){
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
			new Link(properties.get("websites_link")).click();
			new Link(properties.get("download_link")).clickWithoutWait();
		if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent())
		{
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
			new Link(properties.get("statistics_link")).click();
			new Link(properties.get("download_link")).clickWithoutWait();
			if(new PageElementWithIdAttribute(properties.get("download_link"))
			.isPresent())
			{
				new Url().goBackToPreviousPage();
				return true;
			}				
			return false;	
				}
		
		}
		new Url().goBackToPreviousPage();
		return true;
	}
	
public boolean verifyPdfOptionFromKeyboard() throws Exception{
	new Link(properties.get("images_link")).click();
	new Link(properties.get("download_link")).clickWithoutWait();
	
	RobotWebBrowser.typeKey(KeyEvent.VK_TAB,1000);
	RobotWebBrowser.typeKey(KeyEvent.VK_RIGHT, 1000);
	RobotWebBrowser.typeKey(KeyEvent.VK_RIGHT, 1000);
	if(!new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent()){
		new Url().goBackToPreviousPage();
		return true;	
	}		
	return false;
}



public boolean verifyRadioButtonForallOtherDisplayGroups() throws Exception{
	new Link(properties.get("news_link")).click();
	new Link(properties.get("download_link")).clickWithoutWait();
	if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent()){
		new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		new Link(properties.get("reference_first_link")).click();
		new Link(properties.get("download_link")).clickWithoutWait();
		if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent()){
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
		new Link(properties.get("magazines_first_link")).click();
		if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent())
		return true;	
	return false;
	}
	
	}
			return true;

}	
	
public boolean verifyPdfFunctionsForAllotherBuckets() throws Exception{
	new Link(properties.get("news_link")).click();
	new Link(properties.get("download_link")).clickWithoutWait();
	if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent()){
		new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		new Link(properties.get("reference_first_link")).click();
		new Link(properties.get("download_link")).clickWithoutWait();
		if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent()){
			new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
			new Url().goBackToPreviousPage();
		new Link(properties.get("magazines_first_link")).click();
		if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
		.isPresent())
		return true;	
	return false;
	}
	
	}
			return true;

	
}



public boolean verifyPdfNotSelectableForImageStatisticsAndWebsite() throws Exception{
	
	new Link(properties.get("images_link")).click();
	new Link(properties.get("download_link")).clickWithoutWait();
	
	if(!new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent()){
		new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		new Link(properties.get("websites_link")).click();
		new Link(properties.get("download_link")).clickWithoutWait();
	if(new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent())
	{
		new Link(properties.get("Download_cancel_Button")).clickWithoutWait();
		new Url().goBackToPreviousPage();
		new Link(properties.get("statistics_link")).click();
		new Link(properties.get("download_link")).clickWithoutWait();
	if(!new PageElementWithIdAttribute(properties.get("pdf_download_Option"))
	.isPresent())
		return true;	
	return false;
	}
	
	}
	return true;
	
}







	
}
