/**
 * 
 */
package com.dledford.herolyze.config;

import org.apache.commons.cli.*;

/**
 * @author dledford
 *
 */
public class Parameters {
	
	private Options options = new Options();
	
	public Options getOptions() {
		return options;
	}

	public Parameters(){
		options.addOption("t","test",false,"Run test call against Marvel API and ComicVine API");
		options.addOption("n","name",true, "Character's name");
	}
}
