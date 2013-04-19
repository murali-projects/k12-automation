package webPageContainers4Testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import utils.PropertyReader;
import webPageElements4Testing.TextLabel;

public class StopAndNaughtyWordsFormat extends BasePageContainer {

	private List<String> wordsList;
	private List<String> naughtyWordsList;
	private List<String> stopWordsList;

	private static final String naughtyWordsFile = "properties\\default.naughtywords.properties";
	private static final String stopWordsFile = "properties\\default.stopwords.properties";
	private static final String[] specialCharacters = { "+", "$", "#", "%",
			"^", "&", "," };

	private Properties properties1;

	public StopAndNaughtyWordsFormat() throws Exception {
		super();
		properties1 = new Properties();
		naughtyWordsList = loadWordsList(naughtyWordsFile);
		stopWordsList = loadWordsList(stopWordsFile);
	}

	private List<String> loadWordsList(String fileName)
			throws FileNotFoundException, IOException {
		wordsList = new ArrayList<String>();
		// Loads the given properties file
		properties1.load(new FileInputStream(new File(fileName)));

		// Adds values in the properties file to the list
		for (Object obj : properties1.keySet())
			wordsList.add(properties1.getProperty(obj.toString()));

		return wordsList;
	}

	public boolean verifySearchResultsKeywordWithStopwords(String searchKeyword)
			throws Exception {
		String echoText = new TextLabel(properties.get("searchResults_msg_xpath")).getLabelText();
		String expected = echoText.substring(18);
		String[] searchTerm = searchKeyword.split(" ");
		for(String searchValue : searchTerm){
			if(! stopWordsList.contains(searchValue)){
				if(!expected.contains(searchValue))
					return false;
			}
		}
		return true;

	}

	public boolean verifySearchResultsKeywordWithNaughtyWords(
			String searchKeyword) throws Exception {
		String echoMessageXpath = properties.get("searchResults_msg_xpath");
		String echoText = new TextLabel(echoMessageXpath).getLabelText();
		String[] expectedresult = echoText.split("\\:");
		Iterator<String> iterator = naughtyWordsList.iterator();
		while (iterator.hasNext()) {
			if (expectedresult[1].contains(iterator.next())) {
				return false;
			}
		}
		return true;
	}

	public List<String> getSearchTermsWithNaughtyWordsAndSpecialChar() {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i < specialCharacters.length; i++) {
			list.add(naughtyWordsList.get(i) + specialCharacters[i]
					+ naughtyWordsList.get(i));
		}
		return list;
	}

	public boolean verifySearchIdentifierWithNaughtyWordsAndSplChar(
			String searchString) throws Exception {
		String searchMesg = new TextLabel(properties.get("searchResults_msg_xpath"))
				.getLabelText();
		String firstWord = "";
		String secondWord = "";
		int indexOfSplChar = 0;
		for (String splCharacter : specialCharacters) {
			if (searchString.contains(splCharacter)) {
				indexOfSplChar = searchString.indexOf(splCharacter);
				firstWord = searchString.substring(0, indexOfSplChar).trim();
				secondWord = searchString.substring(indexOfSplChar + 1,
						searchString.length()).trim();
				break;
			}
		}

		return (!(searchMesg.contains(firstWord) || searchMesg
				.contains(secondWord)));

	}
}
