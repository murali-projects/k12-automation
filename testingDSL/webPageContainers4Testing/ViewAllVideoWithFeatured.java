package webPageContainers4Testing;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextLabel;

public class ViewAllVideoWithFeatured extends BasePageContainer{

	public ViewAllVideoWithFeatured() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean verifyViewallVideoSortOrder() throws Exception{
		new Link("").click();
		new Link("").click();
		String option=new DropDownBox(properties.get("")).getSelectedOptionLabel();
		return(option.equalsIgnoreCase(""));
	}
	
	public boolean verifyHandpickedItemsNotOntopOfList() throws Exception{
		new Link(properties.get("")).click();
		String element= new TextLabel(properties.get("")).getLabelText();
		new Link(properties.get("")).click();
		String newelemnet= new TextLabel(properties.get("")).getLabelText();
		return (!element.equalsIgnoreCase(newelemnet));
	}
	
	
	public boolean verifyHanpickedItemsByChangingSortOrder() throws Exception{
		new Link(properties.get("")).click();
		new Link(properties.get("")).click();
		String element= new TextLabel(properties.get("")).getLabelText();
		new DropDownBox(properties.get("")).select("label= Title");
		Thread.sleep(4000);
		String newelemnet= new TextLabel(properties.get("")).getLabelText();
		return (!element.equalsIgnoreCase(newelemnet));
	}
	
	
	public boolean verifyHandpickedForPubdate() throws Exception{
		new Link(properties.get("")).click();
		String element= new TextLabel(properties.get("")).getLabelText();
		new Link(properties.get("")).click();
		String newelemnet= new TextLabel(properties.get("")).getLabelText();
		return (!element.equalsIgnoreCase(newelemnet));
	}
	
   public boolean verifySortOrderForOtherOption() throws Exception{
	   new Link(properties.get("")).click();
		String element= new TextLabel(properties.get("")).getLabelText();
		new Link(properties.get("")).click();
		String newelemnet= new TextLabel(properties.get("")).getLabelText();
		return (!element.equalsIgnoreCase(newelemnet));
   }
	
   public boolean verifySortOrderForDefaultOne() throws Exception{
	   verifyHandpickedForPubdate();
	   return true;
   }
	
	public boolean verifyFeaturedItemsBynavigatingtoOtherDisplayGroups() throws Exception{
		 new Link(properties.get("")).click();
			String element= new TextLabel(properties.get("")).getLabelText();
			new Link(properties.get("")).click();
			new Link(properties.get("")).click();
			String newelemnet= new TextLabel(properties.get("")).getLabelText();
			return (element.equalsIgnoreCase(newelemnet));
	}
	
	public boolean verifyFeaturendItemsbyNavigationtoOtherPages() throws Exception{
		 new Link(properties.get("")).click();
			String element= new TextLabel(properties.get("")).getLabelText();
			new Link(properties.get("")).click();
			new Link(properties.get("")).click();
			String newelemnet= new TextLabel(properties.get("")).getLabelText();
			return (element.equalsIgnoreCase(newelemnet));
	}
	
	
	public boolean verifyFeaturedItemsbyDifferentPagesWithDifferentSortOrder() throws Exception{
		 new Link(properties.get("")).click();
			String element= new TextLabel(properties.get("")).getLabelText();
			new Link(properties.get("")).click();
			
			new Link(properties.get("")).click();
			new DropDownBox(properties.get("")).select("label=Document Title");
			String newelemnet= new TextLabel(properties.get("")).getLabelText();
			return (!element.equalsIgnoreCase(newelemnet));
	}
	
	public boolean verifyFeaturedItemsOnDetailedPage() throws Exception{
		new Link(properties.get("")).click();
		new Link(properties.get("")).click();
	return(!new PageElementWithIdAttribute(properties.get(""))
		.isPresent());
	}
	
	public boolean verifyFeaturedItemsOnSearchResultsPage() throws Exception{
		new Link(properties.get("")).click();
		String element= new TextLabel(properties.get("")).getLabelText();
		new Link(properties.get("")).click();
		String newelemnet= new TextLabel(properties.get("")).getLabelText();
		return (!element.equalsIgnoreCase(newelemnet));
	}
	
	
}
