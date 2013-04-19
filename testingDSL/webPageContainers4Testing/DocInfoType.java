package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;

import webPageElements4Testing.DropDownBox;
import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class DocInfoType extends BasePageContainer {

	private Link nextLink;
	int docInfoTypelinkCount;
	public DocInfoType() throws Exception {
		super();
		nextLink = new Link(properties.get("nextLink_Xpath"));
		new TextLabel(properties.get("multipleDocInfoType_Xpath"));
	}

	public boolean doCheckDocInfoTypeIsDisplayed(String displayGroup,String option) throws Exception {
		new Link(properties.get(displayGroup+"_viewAll")).click();
		new DropDownBox(properties.get("sort_by_xpath")).select(option);
		docInfoTypelinkCount = new Link(properties.get("docInfoType_Count_xpath")).getLinkCount();
		for (int i = 1; i <= docInfoTypelinkCount; i++) {
			if (!(new Link("//div/table[@id='searchResults']/tbody/tr[" + i
					+ "]/td[3]").isPresent()))
				return false;
		}
		return true;
	}


	public boolean doCheckDocInfoTypeIsDisplayedInAllLinks() throws Exception {
		if (nextLink.isPresent()) {
			nextLink.click();
			docInfoTypelinkCount = new Link(properties.get("docInfoType_Count_xpath")).getLinkCount();
			for (int i = 1; i <= docInfoTypelinkCount; i++) {
				if (!(new Link("//div/table[@id='searchResults']/tbody/tr[" + i
						+ "]/td[3]").isPresent()))
					return false;
			}
		}
		return true;
	}

	public boolean validateDocInfoTypeWithOcean(String displayGroup,String option) throws Exception {
		List<String> docTypesFromOcean = OceanDatabaseReadFile
				.readResultsFromFile(properties.get("all"), properties.get(displayGroup), properties
						.get("relevance"), properties.get("docTypes"), "test");
		List<String> docTypesInAppl = new ArrayList<String>();
		new DropDownBox(properties.get("sort_by_xpath")).select(option);
		docInfoTypelinkCount = new Link(properties.get("docInfoType_Count_xpath")).getLinkCount();
		for (int i = 1; i <= docInfoTypelinkCount; i++) {
			docTypesInAppl.add((new Link("//div/table[@id='searchResults']/tbody/tr[" + i + "]/td[3]")
					.getLinkText().replace(" ", "")));
		}

		return compareDocInfoTypes(docTypesFromOcean, docTypesInAppl);

	}

	public boolean compareDocInfoTypes(List<String> oceanList,
			List<String> applList) {
		for (int i = 0; i < applList.size(); i++) {
			if (oceanList.get(i) == null) {
				applList.get(i).equals(properties.get("expectedDocInfoType"));
			} else if (!applList.get(i).equals(oceanList.get(i))) {
				return false;
			}
		}
		return true;
	}

}