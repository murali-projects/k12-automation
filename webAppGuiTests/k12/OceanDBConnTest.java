package k12;

import org.testng.annotations.Test;

import utils.PropertyReader;
import webPageContainers4Testing.OceanDatabaseConnectivity;

public class OceanDBConnTest {

	OceanDatabaseConnectivity oceanDatabaseConnectivity;
	PropertyReader properties;

	public OceanDBConnTest() throws Exception {
		
		properties = new PropertyReader("properties/default.search.properties");
		oceanDatabaseConnectivity = new OceanDatabaseConnectivity();
        oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("reference"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("reference"), properties.get("relevance"), "war");
		
		//Related to ViewPublicationDatesTest.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("primarySources"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("publicationDate"),"war");
		
		//Related to ViewPublicationTitlesTest.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("reference"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("academicJournals"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("images"), properties.get("relevance"),properties.get("documentId"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("reference"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("news"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("magazines"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("academicJournals"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("audio"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("video"),properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("images"),properties.get("relevance"), "war");
		
		//ContentLevelSort
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("reference"), properties.get("relevance"),properties.get("targetAudience"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("reference"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("reference"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("publicationTitle"),"war");
		
		//TODO Need to add once Content Level is placed 
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("contentLevel"),"war");

		//related to IntegrateVideoDisplayGroupTest.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("publicationDate"),"obama");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("documentId"),"obama");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),"obama");
		
		//IntegrateNewsDisplayGroupTest.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("docTypes"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("publisher"),"war");
		
		//ContentVerificationOfDisplayroups
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"), properties.get("news"), properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"), properties.get("academicJournals"), properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("K12-PrimarySources"), properties.get("relevance"), properties.get("publicationTitle"), "war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("K12-PrimarySources"), properties.get("relevance"), properties.get("publicationDate"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"), properties.get("K12-PrimarySources"), properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("statistics"), properties.get("relevance"), properties.get("publicationTitle"), "war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("statistics"), properties.get("relevance"), properties.get("publicationDate"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"), properties.get("statistics"), properties.get("relevance"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"), properties.get("viewPoints"), properties.get("relevance"), "war");
		
		//TODO:Sort by publication date not yet implemented in Ocean
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("news"), properties.get("publicationDate"), properties.get("publicationTitle"), "war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("news"), properties.get("publicationDate"), properties.get("publicationDate"), "war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("academicJournals"), properties.get("publicationDate"), properties.get("publicationTitle"), "war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("academicJournals"), properties.get("publicationDate"), properties.get("publicationDate"), "war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("viewPoints"), properties.get("publicationDate"), properties.get("publicationTitle"), "war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("viewPoints"), properties.get("publicationDate"), properties.get("publicationDate"), "war");

		
		//IntegrateMagazines.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("magazines"), properties.get("relevance"), properties.get("publicationDate"), "war");
		
		//IntegrateAcademicJournals.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"), properties.get("academicJournals"), properties.get("relevance"), properties.get("publicationDate"), "war");
		
		//ViewAudioDisplayGroup.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("p2"),properties.get("audio"),properties.get("publicationTitle"),properties.get("publicationDate"), "war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("p2"),properties.get("audio"), properties.get("publicationTitle"),"war");
	
		//Audio Podcast Document
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("documentId"),"obama");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("docTypes"),"obama");

		//DOCINFOType
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("statistics"), properties.get("relevance"),properties.get("docTypes"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("websites"), properties.get("relevance"),properties.get("docTypes"),"test");
		//HitCount
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),"test");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),"test");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),"test");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),"war");
	    oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("academicJournals"), properties.get("relevance"),"war");
	
		//IntegratePrimarySourcesDisplayGroup.java
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),"war");
		
		//IntegrateViewpointsDisplayGroup.java
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("documentTitle"),"war");
		
		//IntegrateWeblinksDisplayGroup.java
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("websites"), properties.get("relevance"),"war");
		
		//Functionality  has not yet implemented once it is dome we can uncomment this tests.
		//DefaultSortForEachDisplayGroup.java
		/*oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("relevance_wts"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("relevance_wts"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("relevance_wts"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),properties.get("relevance_wts"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("relevance_wts"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("statistics"), properties.get("relevance"),properties.get("relevance_wts"),"war");*/
		//TODO:PublicationDate not implemented in ocean
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("publicationDate"),properties.get("publicationDate"),"war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("publicationDate"),properties.get("publicationDate"),"war");
//		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("websites"), properties.get("publicationDate"),properties.get("publicationDate"),"war");
		
		//IntegrateVideoDisplayGroup.java
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("publicationDate"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("documentTitle"),"war");
		
		//AudioPodcast.java
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("docTypes"),"obama");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("documentId"),"obama");
		
		//ViewAllAudioOnSearchResultsPage
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("documentTitle"),"war");

		//ContentLevelOnSearchResults
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("academicJournals"), properties.get("relevance"),properties.get("targetAudience"),"test");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("statistics"), properties.get("relevance"),properties.get("targetAudience"),"test");

		//Integrate view All Tabs
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("news"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("magazines"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("statistics"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("statistics"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("images"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("images"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("audio"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("video"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("websites"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("websites"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("viewPoints"), properties.get("relevance"),"war");
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("academicJournals"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("academicJournals"), properties.get("relevance"),"war");

		//ViewImageDetails
		oceanDatabaseConnectivity.writeResultsToFile(properties.get("all"),properties.get("images"), properties.get("relevance"),properties.get("documentTitle"),"war");

		//ViewAllMagazinesOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("documentTitle"),properties.get("documentTitle"),"test");

		//ViewAllprimarySourceOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("K12-PrimarySources"), properties.get("documentTitle"),properties.get("documentTitle"),"war");

		//ViewAllNewsOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("news"), properties.get("documentTitle"),properties.get("documentTitle"),"war");

		//ViewAllViewPointsOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("viewPoints"), properties.get("documentTitle"),properties.get("documentTitle"),"war");

		//ViewAllWebsitesOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("websites"), properties.get("relevance"),properties.get("documentTitle"),"war");

		//ViewAllStatisticsOnSearchResultsPage
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("statistics"), properties.get("documentTitle"),properties.get("documentTitle"),"war");

		//BiographyInformationInDocumentDisplay.java
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"), properties.get("docTypes"),"civil rights");

		//IntegrateMagazinesDisplayGroup.java
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("magazines"), properties.get("documentTitle"),properties.get("documentTitle"),"war");

		//ViewAllPagination
		oceanDatabaseConnectivity.writeMoreResultsToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),properties.get("documentTitle"),"war");
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),"war");

		//HitcountAndSortingDocument
		oceanDatabaseConnectivity.writeRelatedSubjectsCountToFile(properties.get("subject"), properties.get("all"),properties.get("none"), properties.get("relevance"),"\"Law enforcement\"");
		
		//SubmitAdvancedSearchQuery
		//and combination
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "and", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("news"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "and", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("K12-Reference"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "and", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("statistics"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "and", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("viewpoints"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "and", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("magazines"), "");
		//or combination
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "or", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("news"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "or", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("K12-Reference"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "or", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("statistics"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "or", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("viewpoints"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("KE"), "war", "or", properties.get("SU"), "forest", "", "", "", properties.get("all"), properties.get("magazines"), "");
		//not, not combination
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "war", "not", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("news"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "war", "not", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("K12-Reference"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "war", "not", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("statistics"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "war", "not", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("viewpoints"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "war", "not", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("magazines"), "");
		//or, not combination
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "world war", "or", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("news"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "world war", "or", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("K12-Reference"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "world war", "or", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("statistics"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "world war", "or", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("viewpoints"), "");
		oceanDatabaseConnectivity.writeAdvancedSearchResultsCountToFile(properties.get("TI"), "world war", "or", properties.get("SU"), "war", "not", properties.get("PB"), "Los Angeles Times", properties.get("all"), properties.get("magazines"), "");
		//N4 searchOperator search
		oceanDatabaseConnectivity.writeResultsCountToFile(properties.get("all"),properties.get("K12-Reference"), properties.get("relevance"),"war N4 india");
		//Query results
		oceanDatabaseConnectivity.writeQueryResultsCountToFile("KE war Or SU soldiers And TX weapons Not TI peace", "TY Article and ACID (14223820)");
		
	}	

	@Test
	public void abc() {
	}

}
