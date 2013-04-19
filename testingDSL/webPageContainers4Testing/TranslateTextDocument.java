package webPageContainers4Testing;
 
import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;
import webPageElements4Testing.Url;
 
public class TranslateTextDocument extends BasePageContainer{
private String SearchString ="war";
String[] ExpectedOptions={"Spanish","French","Japanese","German","Italian","Portuguese","Chinese (Simplified)","Korean"};
 public TranslateTextDocument() throws Exception {
  super();
  
 }
 
 public boolean verifyTranslateOptionAvailableFromViewAll() throws Exception{
  
  new Link(properties.get("view_all")).click();
  
  new Link(properties.get("detailed_link")).click();
  if(new PageElementWithIdAttribute(properties.get("translate_link")).isPresent())
   return true;  
  return false;
 }
 
 public boolean verifyTranslateOptionAvailableFromSearchResultsPage()throws Exception{
  
  new Link(properties.get("firstlink_news")).click();
  if(new PageElementWithIdAttribute(properties.get("translate_link")).isPresent())
   return true;  
  return false;
  
 }
    
 public boolean verifyTranslateOptionAvailableFromDetailedPage()throws Exception{
  new Link(properties.get("view_all")).click();
  new Link(properties.get("next_link")).click();
  new Link(properties.get("detailed_link")).click();
  if(new PageElementWithIdAttribute(properties.get("translate_link")).isPresent())
   return true;
  return false;
 }
 
 public boolean verifyOptionsInDropDownBox()throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(10000);
  String[] Actualoptions=new DropDownBox(properties.get("translate_dropdown")).getSelectOptions();
  new Link(properties.get("translate_cancel_button")).clickWithoutWait();  
  selenium.selectWindow("");
  //String[] ExpectedOptions={"Spanish","French","Japanese","German","Italian","Portuguese","Chinese (Simplified)","Korean"};
  for(int i=0; i< ExpectedOptions.length; i++){
   if(!(Actualoptions[i].equals(ExpectedOptions[i])))
    return false;
   }
  return true;
  }
 
 
 public boolean verifyTranslateNotAppliedWhenButtonNotPressed()throws Exception{
  new Link(properties.get("firstlink_news")).click();
  
  String sampletext=new TextLabel(properties.get("text_tile")).getLabelText();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=French");
  if((new TextLabel(properties.get("text_tile")).getLabelText()).equalsIgnoreCase(sampletext)){
	  new Link(properties.get("translate_cancel_button")).clickWithoutWait();  
	  selenium.selectWindow("");
	  return true;
  }
  return false;
 }
 
 
 public boolean verifyMultipleSelectNotAllowed() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  //new DropDownBox("//*[@id='translate_languages']").select("label=French");
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
 
  String labels[]=new DropDownBox("//*[@id='translate_languages']").getSelectedLabels();
  new Link(properties.get("translate_cancel_button")).clickWithoutWait();  
  selenium.selectWindow("");
  if(labels.length>1)
   return false;
  return true;
 }
 
 
 public boolean verifySelectingSpanishOption() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("translateDocument");
     selenium.close();  
  return true;
 }
 
 public boolean verifyLanguageChanged() throws Exception{
  
  new Link(properties.get("firstlink_news")).click();
  String ActualText=new TextLabel(properties.get("text_tile")).getLabelText();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("translateDocument");
  Thread.sleep(10000);
  String Text=new TextLabel(properties.get("popup_title")).getLabelText();
  selenium.close();
  if(ActualText.equalsIgnoreCase(Text))
   return false;
  return true;
 }
 
 public boolean verifyLanguageChangedForAllLanguages() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  int no=new DropDownBox(properties.get("translate_dropdown")).getSelectOptionsCount();
  for(int i=1;i<no;i++){
   String ActualText=new TextLabel(properties.get("text_tile")).getLabelText();
   new DropDownBox(properties.get("translate_dropdown")).select("label="+ExpectedOptions[i-1]);//get all the labels using for loob iteration number.
   new PageButton(properties.get("translate_popup_button")).click();
   new Url().selectWindow("translateDocument");
   String Text=new TextLabel(properties.get("text_tile")).getLabelText();
   if(!(ActualText.equalsIgnoreCase(Text))){
    selenium.close();//_blank
    return true;
   }
  }
  return false;
 }
 
 
 //Not Yet Implemented
 
 
 public boolean verifyPrintOptionForTranslatedText() throws Exception{
  new DropDownBox("//*[@id='translate_languages']").select("label=French");
  new PageButton("//input[@id='translate_button']").clickAndWait();
  new Url().selectWindow("printPreview");
  if(new PageElementWithIdAttribute("").isPresent())
   return true;  
  return false;
 }
 public boolean verifyPrintPopupForTranslatedText() throws Exception{
  new DropDownBox("").select("");
  new PageButton("").clickAndWait();
  new Url().selectWindow("");
  new Link("").click();
  if(new PageElementWithIdAttribute("").isPresent())
   return true;  
  return false;
 }
 
 public boolean verifyCancelPrintpopupForTranslatedText() throws Exception{
  new DropDownBox("").select("");
  new PageButton("").clickAndWait();
  new Url().selectWindow("");
  new Link("").click();
  new Link("").click();
  if(new PageElementWithIdAttribute("").isPresent())
   return true;
  return false;
 }
 public boolean verifyDownloadOptionAvailableForTranslatedText() throws Exception{
  new DropDownBox("").select("");
  new PageButton("").clickAndWait();
  new Url().selectWindow("");
  if(new PageElementWithIdAttribute("").isPresent())
   return true;
  return false;
 }
 
 public boolean verifyCancelDownloadPopupForTranslatedText() throws Exception{
  new DropDownBox("").select("");
  new PageButton("").clickAndWait();
  new Url().selectWindow("");
  new Link("").click();
  new Link("").click();
  if(new PageElementWithIdAttribute("").isPresent())
   return true;
  return false;
 }
 
 public boolean verifyMouseOverDropDown() throws Exception{
  //int no=new DropDownBox("").getSelectOptionsCount();
  //String[] Actualoptions=new DropDownBox("").getSelectOptions();
     
  //new Url().mouseOver(new DropDownBox("").select()))
   new Url().mouseOver(properties.get("translate_dropdown"));
  
  return true;
 }
 
 public boolean verifyDesclaimerForTranslatedDocument() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("translateDocument");
  Thread.sleep(3000);
  return (new PageElementWithIdAttribute("//div[@class='disclaimer']").isPresent());
 }
 
 public boolean verifyCitationForTranslatedDocument() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  String ActualText=new TextLabel(properties.get("source_citation")).getLabelText();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("translateDocument");
  Thread.sleep(9999);
  String Text=new TextLabel(properties.get("source_citation")).getLabelText();
  if(ActualText.equalsIgnoreCase(Text))
   return true;
  return false;
 }
 
 public boolean canTranlatedDocumentBeClosed() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=Spanish");
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("translateDocument");
  selenium.close();
  //new Url().closeWindow();
  selenium.selectWindow("");  
  if(new PageElementWithIdAttribute(properties.get("translate_dropdown")).isPresent())
   return true;
  return false;
 }
 public boolean isSelectedOptionAvailableinDropDownAfterActionPerformed() throws Exception{
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  Thread.sleep(1000);
  new DropDownBox(properties.get("translate_dropdown")).select("label=French");
  String Language=new DropDownBox(properties.get("translate_dropdown")).getSelectedOptionValue();
  new PageButton(properties.get("translate_popup_button")).click();
  new Url().selectWindow("");
  new Url().goBackToPreviousPage();
  new Link(properties.get("firstlink_news")).click();
  new Link(properties.get("translate_link")).clickWithoutWait();
  new Link(properties.get("translate_cancel_button")).clickWithoutWait();  
  selenium.selectWindow("");
  Thread.sleep(1000);
  if((new DropDownBox(properties.get("translate_dropdown")).getSelectedOptionValue()).equalsIgnoreCase(Language)){
	  new Link(properties.get("translate_cancel_button")).clickWithoutWait();  
	  selenium.selectWindow("");
	  return false;    
  }  
    return true;
 }
 
 
}
