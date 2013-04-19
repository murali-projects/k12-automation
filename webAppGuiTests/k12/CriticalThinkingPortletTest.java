package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import webPageContainers4Testing.CriticalThinkingPortlet;

public class CriticalThinkingPortletTest extends BaseWebPageTest{

	
	CriticalThinkingPortlet criticalThinkinghPortlet;
 	@BeforeTest
	public void setUP() throws Exception{
 		criticalThinkinghPortlet = new CriticalThinkingPortlet();
	}
	
 @Test
 public void basicSearch() throws InterruptedException{
	 doBasicSearchUsingSearchTerm("Emphasis on Diversity");
 }
 	
 @Test(dependsOnMethods={"basicSearch"})
 public void verifyCriticalPortlet(){
	 Assert.assertTrue(criticalThinkinghPortlet.verifyCriticalPortlet());
 }
 @Test(dependsOnMethods={"verifyCriticalPortlet"})
 public void verifyCriticalThinkingPortletDocuments(){
	 Assert.assertTrue(criticalThinkinghPortlet.verifyCriticalThinkingPortletDocuments());
 }
 @Test(dependsOnMethods={"verifyCriticalThinkingPortletDocuments"})
 public void verifyViewAllLink(){
	 Assert.assertTrue(criticalThinkinghPortlet.verifyViewAllLink());
 }
 @Test(dependsOnMethods={"verifyViewAllLink"})
 public void verifyVPublicationDateOrder() throws Exception{
	 Assert.assertTrue(criticalThinkinghPortlet.verifyPublicationDateOrder());
 }
 	
 @Test(dependsOnMethods={"verifyVPublicationDateOrder"})
 public void verifyDetailedDocument() throws InterruptedException{
	 Assert.assertTrue(criticalThinkinghPortlet.verifyDetailedDocument());
 }
 
 
}
