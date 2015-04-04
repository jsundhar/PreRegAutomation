package com.o2.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Sundhar Jaganathan
 * This class is to read the externalised properties of the application
 *
 */
public class AppProperties {
	
	String filename = System.getProperty("user.dir")
			+ "/src/main/resources/appdata.properties";
	File propertyFile = null;
	Properties properties = null;
	FileInputStream fileInput = null;
	
	//Constructor which will loads the file as Java property
	public AppProperties() {				
		try {			
			propertyFile = new File(filename);
			fileInput = new FileInputStream(propertyFile);
			properties = new Properties();
			properties.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 		
		
	}
	
	//This function is used to get the property value based on key
	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
}
