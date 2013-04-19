package webPageContainers4Testing;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import webPageElements4Testing.PageButton;
import webPageElements4Testing.PageElementWithIdAttribute;
import webPageElements4Testing.TextBox;
import webPageElements4Testing.TextLabel;

public class NaughtyWords extends BasePageContainer{

	private TextBox searchTextBox;
	private PageButton searchButton;
	private String[] naughtyWords;
	private String resultTextPath  = "//div[@class]";
	private String errorMessagePath = "//div[@class]";
	
	public NaughtyWords() throws Exception {
		super();
		
		// Gets search textbox and search button instances present in Homepage
		searchTextBox = new HomePage().basicSearchBox();
		searchButton = new HomePage().basicSearchButton();
		
		//Adding naughty words to array
		naughtyWords = new String[] { "naughtyword1", "naughtyword2" };
		
	}
	
	/**
	 * Searches with a single naughty word.
	 * @param searchString
	 * @return
	 * @throws Exception
	 */
	public boolean searchWithNaughtyWord(String searchString) throws Exception {
		boolean isNaughtyWord = false;
		boolean textPresent = false;
		String msg = "";
		
		//Enters search term in the search text field
		searchTextBox.type(searchString);
		
		//Clicks on search button
		searchButton.clickAndWait();
		
		for (String naughtyWord : naughtyWords) {
			if (searchString.equals(naughtyWord)) {
				isNaughtyWord = true;
				break;
			}
		}
		//if the entered search term is a naughty word
		if (isNaughtyWord) {
			textPresent = new PageElementWithIdAttribute(errorMessagePath).isPresent();
			if (textPresent){
				msg = new TextLabel("message xpath").getLabelText();
				if(msg.equals("The entered word is a naughty word"))
					return true;
				else{
					System.out.println("Incorrect message is displayed");
					return false;
				}
			}
			else{
				System.out.println("Message not displayed..");
				return false;
			}
		}
		System.out.println("The word you entered is not a naughty word");
		return false;
	}

	/**
	 * Searches with naughty words in a sentence with or without quotes
	 * @param searchString
	 * @return
	 * @throws Exception
	 */
	public boolean searchWithNaughtyWordsInSentence(String searchString) throws Exception{
		List<String> list = new ArrayList<String>();
		int count = 0;
		
		//Entering searchString in the search textBox
		searchTextBox.type(searchString);
		
		//Clicking the search button
		searchButton.clickAndWait();
		 
		StringTokenizer searchStringTokens = new StringTokenizer(searchString);
		while(searchStringTokens.hasMoreTokens()){
			String word = searchStringTokens.nextToken();
			
			//Assigning the text within double quotes to a String
			if(word.charAt(0) == '"' && word.charAt(word.length()-1) == '"'){
				word = word.substring(1, word.length()-2);
			}
			
			else if(word.charAt(0) == '"'){
				word = word.substring(1, word.length()-1);
			}
			
			else if(word.charAt(word.length()-1) == '"'){
				word = word.substring(0, word.length()-2);
			}
			
			for(String naughtyWord : naughtyWords){
				if(word.equals(naughtyWord)){
					list.add(word);
				}
			}
			
			// Gets the text from first displayed result.
			String resultText = new TextLabel(resultTextPath).getLabelText();

			// Splitting the result into words
			StringTokenizer resultTextTokens = new StringTokenizer(resultText);

			while (resultTextTokens.hasMoreTokens()) {
				for (String str : list) {

					// If the displayed resultText has the naughty word
					if (resultTextTokens.nextToken().equals(str)) {
						System.out.println("Result contains naughty word");
						break;
					}
					count++;
				}
			}

			//If no naughty word is present in the result text
			if(count == resultTextTokens.countTokens()){
				return true;
			}
		}
		return false;
	}
	
	public boolean searchWithStopAndNaughtyWordsInSentenceWithQuotes(){
		
		
		return false;
	}
	
}
