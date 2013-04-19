package webPageContainers4Testing;

import java.io.IOException;
import java.util.List;

import webPageElements4Testing.Link;
import webPageElements4Testing.TextLabel;

public class IntegrateMLA7Service extends BasePageContainer{

	String sourceCitation;
	public IntegrateMLA7Service(String displayGroup) throws Exception {
		super();
		new Link(properties.get(displayGroup+ "/h3/a")).click();
	}
	
	public boolean checkSourceCitationIsDisplayed(){
		boolean sourceCitationPresent = new TextLabel(properties.get("source_citation")).isPresent();
		if(sourceCitationPresent)
			sourceCitation = new TextLabel(properties.get("source_citation")).getLabelText();
		return sourceCitationPresent;
	}
	
	public boolean checkSourceCitationFormat(){
		return new TextLabel(properties.get("sourceCitation_documentUrl")).isPresent() &&
		new TextLabel(properties.get("sourceCitation_productName")).isPresent();
	}
	
	public boolean verifyVolumeNumber(){
		if(sourceCitation.contains("Vol."))
			return true;
		return false;
	}
	
	public boolean verifyPageNumbersFormat(){
		if(new TextLabel("").isPresent()){
			return new TextLabel("").getLabelText().matches("\\d{1,4}\\-\\d{1,4}");
		}
		return true;
	}
	
	public boolean verifyCitationStartsWithAuthor() throws IOException{
		String[] wordsInSourceCitation = sourceCitation.split(" ");
		List<String> authors = OceanDatabaseReadFile.readResultsFromFile("", "", "", "", "");
		if(authors.get(0).contains(wordsInSourceCitation[0]))
			return true;
		return false;
	}
	
}
