/**
 * 
 */
package com.dledford.herolyze.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.cli.Options;
import org.springframework.util.StringUtils;

/**
 * @author dledford
 *
 */
public class Config {
	public static String MARVEL_PUBLIC_KEY;
	public static String MARVEL_PRIVATE_KEY;
	public static String COMICVINE_API_KEY;
	private final String FILE_NAME="config.properties";
	private Parameters parameters = new Parameters();
	private static Config config = new Config();
	
	private Config(){}
	
	public static Config getInstance(){
		return config;
	}
	
	public void load() throws FileNotFoundException, IOException{
		Properties properties = new Properties( System.getProperties() );	
		String homePath = System.getProperty("user.home");
		File homeDir = new File(homePath);
		File config = new File(homeDir, FILE_NAME);
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		if (inputStream != null) {
			properties.load(inputStream);
			if(!keysExist(properties)){
				properties = new Properties( System.getProperties() );
				properties.load( new FileInputStream(config) );
				if(!keysExist(properties)){
					throw new FileNotFoundException("property file found '" + FILE_NAME + "' but keys not found in file");
				}
			}
		} else {
			properties = new Properties( System.getProperties() );
			properties.load( new FileInputStream(config) );
			if(!keysExist(properties)){
				throw new FileNotFoundException("property file found '" + FILE_NAME + "' but keys not found in file");
			}
		}
	}
	
	private Boolean keysExist(Properties properties) {
		if (!StringUtils.isEmpty((String) properties.get("marvel_public_key"))
				&& !StringUtils.isEmpty((String) properties.get("marvel_private_key"))
				&& !StringUtils.isEmpty((String) properties.get("comicvine_api_key"))) {
			Config.getInstance();
			Config.MARVEL_PRIVATE_KEY = (String) properties.get("marvel_private_key");
			Config.getInstance();
			Config.MARVEL_PUBLIC_KEY = (String) properties.get("marvel_public_key");
			Config.getInstance();
			Config.COMICVINE_API_KEY = (String) properties.get("comicvine_api_key");
			return true;
		}
		return false;
	}

	public Options getInputOptions(){
		return parameters.getOptions();
	}
	
}