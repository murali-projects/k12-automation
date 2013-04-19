package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {
	private Properties properties;
	private String fileName;
	
	public PropertyReader(String fileName){
		this.fileName = fileName;
		this.properties = new Properties();
		init();
	}
	
	public void init(){
		try {
			properties.load(new FileInputStream(new File(fileName)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String get(String key){
		return properties.getProperty(key);
	}
}
