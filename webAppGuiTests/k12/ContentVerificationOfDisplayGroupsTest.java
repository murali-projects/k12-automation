package k12;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import webPageContainers4Testing.ContentVerificationOfDisplayGroups;

public class ContentVerificationOfDisplayGroupsTest extends BaseWebPageTest{
	
	private ContentVerificationOfDisplayGroups contentVerification;
	private String searchTerm;
	private String publicationDate;
	private String relevance;
	private String reference;
	private String news;
	private String magazines;
	private String statistics;
	private String primarysources;
	private String viewpoints;
	private String academicJournals;
	
	@Parameters({"searchTerm", "reference", "news", "magazines", "statistics", "primarysources", "viewpoints", "academicJournals", "publicationDate", "relevance"})	
	@BeforeTest
	public void setUp(String searchTerm, String reference, String news, String magazines, String statistics, String primarysources,
			String viewpoints,String academicJournals,String publicationDate, String relevance) throws Exception{
		contentVerification = new ContentVerificationOfDisplayGroups();
		this.searchTerm = searchTerm;
		this.publicationDate = publicationDate;
		this.relevance = relevance;
		this.reference = reference;
		this.news = news;
		this.magazines = magazines;
		this.statistics = statistics;
		this.primarysources = primarysources;
		this.viewpoints = viewpoints;
		this.academicJournals = academicJournals;
		doBasicSearchUsingSearchTerm(searchTerm);
	}
	
	@Test
	public void verifyReferenceDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(reference));
	}	
	
	@Test
	public void verifyReferenceViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(reference));
	}
	
	@Test
	public void verifyReferenceTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(reference));
	}
	
	@Test
	public void validateReferenceCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(reference, searchTerm));
	}
	
	@Test
	public void verifyReferenceThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(reference));
	}
	
	@Test
	public void verifyReferencePublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(reference));
	}
	
	@Test
	public void validateReferencePublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(reference, relevance, searchTerm));
	}
	
	@Test
	public void verifyReferencePublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(reference));
	}
	
	@Test
	public void validateReferencePublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(reference, relevance, searchTerm));
	}
	//////////////////////////////////////////
	@Test
	public void verifyNewsDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(news));
	}	
	
	@Test
	public void verifyNewsViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(news));
	}
	
	@Test
	public void verifyNewsTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(news));
	}
	
	@Test
	public void validateNewsCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(news, this.searchTerm));
	}
	
	@Test
	public void verifyNewsThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(news));
	}
	
	@Test
	public void verifyNewsPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(news));
	}
	
	@Test
	public void validateNewsPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(news, publicationDate, this.searchTerm));
	}
	
	@Test
	public void verifyNewsPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(news));
	}
	
	@Test
	public void validateNewsPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(news, publicationDate, this.searchTerm));
	}
//////////////////////////////////////////
	@Test
	public void verifyMagazinesDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(magazines));
	}	
	
	@Test
	public void verifyMagazinesViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(magazines));
	}
	
	@Test
	public void verifyMagazinesTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(magazines));
	}
	
	@Test
	public void validateMagazinesCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(magazines, searchTerm));
	}
	
	@Test
	public void verifyMagazinesThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(magazines));
	}
	
	@Test
	public void verifyMagazinesPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(magazines));
	}
	
	@Test
	public void validateMagazinesPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(magazines, publicationDate, searchTerm));
	}
	
	@Test
	public void verifyMagazinesPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(magazines));
	}
	
	@Test
	public void validateMagazinesPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(magazines, publicationDate, searchTerm));
	}
//////////////////////////////////////////
	@Test
	public void verifyAcademicJournalsDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(academicJournals));
	}	
	
	@Test
	public void verifyAcademicJournalsViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(academicJournals));
	}
	
	@Test
	public void verifyAcademicJournalsTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(academicJournals));
	}
	
	@Test
	public void validateAcademicJournalsCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(academicJournals, this.searchTerm));
	}
	
	@Test
	public void verifyAcademicJournalsThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(academicJournals));
	}
	
	@Test
	public void verifyAcademicJournalsPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(academicJournals));
	}
	
	@Test
	public void validateAcademicJournalsPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(academicJournals, publicationDate, this.searchTerm));
	}
	
	@Test
	public void verifyAcademicJournalsPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(academicJournals));
	}
	
	@Test
	public void validateAcademicJournalsPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(academicJournals, publicationDate, this.searchTerm));
	}
//////////////////////////////////////////
	@Test
	public void verifyPrimarySourcesDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(primarysources));
	}	
	
	@Test
	public void verifyPrimarySourcesViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(primarysources));
	}
	
	@Test
	public void verifyPrimarySourcesTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(primarysources));
	}
	
	@Test
	public void validatePrimarySourcesCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(primarysources, this.searchTerm));
	}
	
	@Test
	public void verifyPrimarySourcesThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(primarysources));
	}
	
	@Test
	public void verifyPrimarySourcesPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(primarysources));
	}
	
	@Test
	public void validatePrimarySourcesPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(primarysources, relevance, this.searchTerm));
	}
	
	@Test
	public void verifyPrimarySourcesPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(primarysources));
	}
	
	@Test
	public void validatePrimarySourcesPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(primarysources, relevance,  this.searchTerm));
	}
//////////////////////////////////////////
	@Test
	public void verifyStatisticsDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(statistics));
	}	
	
	@Test
	public void verifyStatisticsViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(statistics));
	}
	
	@Test
	public void verifyStatisticsTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(statistics));
	}
	
	@Test
	public void validateStatisticsCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(statistics, this.searchTerm));
	}
	
	@Test
	public void verifyStatisticsThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(statistics));
	}
	
	@Test
	public void verifyStatisticsPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(statistics));
	}
	
	@Test
	public void validateStatisticsPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(statistics, relevance, this.searchTerm));
	}
	
	@Test
	public void verifyStatisticsPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(statistics));
	}
	
	@Test
	public void validateStatisticsPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(statistics, relevance, this.searchTerm));
	}
//////////////////////////////////////////
	@Test
	public void verifyViewPointsDisplayGroupIsPresent()throws Exception {
		Assert.assertTrue(contentVerification.checkDisplayGroupIsPresent(viewpoints));
	}	
	
	@Test
	public void verifyViewPointsViewAllLinkDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.checkViewAllLinkIsPresent(viewpoints));
	}
	
	@Test
	public void verifyViewPointsTotalCountIsDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyCountIsDisplayed(viewpoints));
	}
	
	@Test
	public void validateViewPointsCount() throws Exception{
		Assert.assertTrue(contentVerification.validateCount(viewpoints, this.searchTerm));
	}
	
	@Test
	public void verifyViewPointsThreeLinksAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyLinkCountInFirstPage(viewpoints));
	}
	
	@Test
	public void verifyViewPointsPublicationDatesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationDateIsDisplayed(viewpoints));
	}
	
	@Test
	public void validateViewPointsPublicationDates() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationDates(viewpoints, publicationDate, this.searchTerm));
	}
	
	@Test
	public void verifyViewPointsPublicationNamesAreDisplayed() throws Exception{
		Assert.assertTrue(contentVerification.verifyPublicationNameIsDisplayed(viewpoints));
	}
	
	@Test
	public void validateViewPointsPublicationNames() throws Exception{
		Assert.assertTrue(contentVerification.validatePublicationNames(viewpoints, publicationDate, this.searchTerm));
	}

}
