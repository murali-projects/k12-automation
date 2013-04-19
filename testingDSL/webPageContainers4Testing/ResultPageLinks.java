package webPageContainers4Testing;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;


public class ResultPageLinks extends BasePageContainer{

	private Link ReferenceArticles;
    private String ReferenceArticles_path="//div[@id=\"reference_bucket\"]/h3" ;
    		//"//div[@id='reference_bucket']/h3[contains(text(),'Reference')]";
    		//"//div[@id='reference_bucket']/h3[1]";
	private Link ViewAllLinkForReferenceArticle;
	private String ViewAllLinkForReferenceArticle_path="//div[@id='reference_bucket']/ul/li[4]/a";

		
	

	public ResultPageLinks() throws Exception{
		super();
		ReferenceArticles = new Link("//div[@id='reference_bucket']/h3");
		ViewAllLinkForReferenceArticle= new Link ("//div[@id='reference_bucket']/ul/li[4]/a");
		}
   
	public boolean DoCheckReferenceArticles (){
		if(ReferenceArticles.isPresent())
			return true;
   	return false;
	}
	public boolean doCheckViewAllReferenceArticlesLink (){
	
		if((ViewAllLinkForReferenceArticle.isPresent())&(ViewAllLinkForReferenceArticle.getLinkText(ViewAllLinkForReferenceArticle_path).equals("View All")))
			return true;
   	return false;
	}
	public boolean doCheckViewAllReferenceArticlesLinkWorking(){
		ViewAllLinkForReferenceArticle.click("//div[@id='reference_bucket']/ul/li[4]/a");
	/*	int allReferenceArticleRelatedLinks = (Integer)selenium.getXpathCount("//div[@id='reference_bucket']/ul/li"); 
		if (allReferenceArticleRelatedLinks>=1)
			return true;
		return false;*/
		
		return true;
	}
	
	public void DoClickonReferenceArticles(){
		//ReferenceArticles.click();
	}

	
	public int countOfReferenceArticlesLink() throws Exception{
		String referenceArticlesLinkText = new TextLabel(ReferenceArticles.toString()).getLabelText();		
		int noOfReferenceArticlesLinks = Integer.parseInt(referenceArticlesLinkText.substring(referenceArticlesLinkText.indexOf("("), referenceArticlesLinkText.indexOf(")"))); 
	return noOfReferenceArticlesLinks;
	}
	
	//check the count and reference
	public boolean referenceArticlesLinkCountIsPresent()throws Exception{
		System.out.println("ReferenceArticles_path  "+ReferenceArticles_path);
		TextLabel tl = new TextLabel(ReferenceArticles_path);
		String allLinkText=tl.getLabelText();
		System.out.println("allLinkText  "+allLinkText);
		String noOfAllLinks = allLinkText.substring(allLinkText.indexOf("("), allLinkText.indexOf(")"));
	if(noOfAllLinks!= null)
		return true;
	return false;
	}
	
//logic for occurence of count in each section(ex..All, News,Magazines...) to toatal links in each section	
public boolean referenceArticleRelatedLinks()throws Exception{
	//int allReferenceArticleRelatedLinks = selenium.getXpathCount("//div/ol/li[1]/h3/a").intValue(); //DivPathForReferenceArticles); 
	int allReferenceArticleRelatedLinks = new Link("//div[@id='reference_bucket']/ul/li").getLinkCount(); //DivPathForReferenceArticles);
	System.out.println("link count "+ allReferenceArticleRelatedLinks);
	if (4<=allReferenceArticleRelatedLinks)
		return true;
	else{
		if(countOfReferenceArticlesLink()==allReferenceArticleRelatedLinks)
			return true;
		else
			return false;
	}
}

}
