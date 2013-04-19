package webPageContainers4Testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class OceanDatabaseReadFile {
	
	public static List<String> readResultsFromFile(String partition, String contentGroup, String sortBy,String dataBaseElement,String searchTerm) throws IOException{
		return readFromFile(new File(partition+"_"+contentGroup+"_"+sortBy+"_"+dataBaseElement+"_"+searchTerm+".txt"));
	}
	
	public static List<String> readResultsCountFromFile(String partition, String contentGroup, String sortBy, String searchTerm) throws IOException{
		return readFromFile(new File(partition+"_"+contentGroup+"_"+sortBy+"_"+searchTerm+".txt"));
	}	

	public static List<String> readRelatedSubjectsCountFromFile(String category, String partition, String contentGroup, String sortBy, String searchTerm) throws IOException{
		return readFromFile(new File(category+"_"+partition+"_"+contentGroup+"_"+sortBy+"_"+searchTerm.replace("\"","-")+".txt"));
	}	

	public static List<String> readAdvancedResultsCountFromFile(String index1,
			String searchTerm1, String connector1, String index2,
			String searchTerm2, String connector2, String index3,
			String searchTerm3, String partition, String contentGroup,
			String sortBy) throws IOException{
		return readFromFile(new File(index1
				+ "_" + searchTerm1 + "_" + connector1 + "_" + index2 + "_"
				+ searchTerm2 + "_" + connector2 + "_" + index3 + "_"
				+ searchTerm3 + "_" + partition + "_" + contentGroup + "_"
				+ sortBy + ".txt"));
	}
	
	public static List<String> readQueryResultsCountFromFile(String searchQuery, String limitQuery) throws IOException{
		return readFromFile(new File(searchQuery.replace(" ","_")+".txt"));
	}
	
	private static List<String> readFromFile(File file) {
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			List<String> sortResult = new ArrayList<String>();
	
			String line = "";
	 
			// reads the file line by line
			while ((line = reader.readLine()) != null) {
					sortResult.add(line);
			}
			// close the file
			reader.close();
			return sortResult;
		}catch (Exception e) {
			return new ArrayList<String>();
		}
	}	
	
	public static List<String> readResultsCountFromFilewithSearchType(String searchType, String partition, String contentGroup, String sortBy, String searchTerm) throws IOException{
		return readFromFile(new File(searchType+"_"+partition+"_"+contentGroup+"_"+sortBy+"_"+searchTerm+".txt"));
	}	
}
