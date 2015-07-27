/**
 * 
 */
package com.dledford.herolyze;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;

import com.dledford.herolyze.config.Config;
import com.dledford.herolyze.domain.ComicVineCharacter;
import com.dledford.herolyze.domain.MarvelCharacter;
import com.dledford.herolyze.service.ComicVineService;
import com.dledford.herolyze.service.MarvelService;

/**
 * @author dledford
 *
 */

public class Application {

	public static void main(String args[]) {

		try {
			Config.getInstance().load();
			CommandLineParser parser = new DefaultParser();
			CommandLine cmd = parser.parse( Config.getInstance().getInputOptions(), args);
			if(cmd.hasOption("t") || cmd.hasOption("test")) {
				test();
			}
			if(cmd.hasOption("n") || cmd.hasOption("name")){
				List<ComicVineCharacter> characters = new ComicVineService().getCharacters(cmd.getOptionValue("n"));
				for(ComicVineCharacter character : characters){
					System.out.println("Name: " + character.getName() + " ID = " + character.getId());
				}
			}
			if(cmd.hasOption("h") || cmd.hasOption("help")){
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "herolyze",  Config.getInstance().getInputOptions() );
			}
			else{
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp( "herolyze",  Config.getInstance().getInputOptions() );
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void test() throws Exception {
		MarvelCharacter character = new MarvelCharacter();
		ComicVineCharacter comicVine = new ComicVineCharacter();
		character = new MarvelService().getCharacterById(1011334L);
		if (character != null) {
			System.out.println("Name: " + character.getName());
		} else {
			System.out.println("UNKNOWN SUPERHERO");
		}
		comicVine = new ComicVineService().getCharacterById(45116L);
		if (comicVine != null) {
			System.out.println("Name: " + comicVine.getName());
		} else {
			System.out.println("UNKNOWN SUPERHERO");
		}
		for (ComicVineCharacter nonMarvelChar : new ComicVineService().getCharacters("superman")) {
			System.out.println("Name: " + nonMarvelChar.getName() + " ID = " + nonMarvelChar.getId());
		}

		for (MarvelCharacter marvelcharacter : new MarvelService().getCharacters("spider-man")) {
			System.out.println("Name: " + marvelcharacter.getName() + " ID = " + marvelcharacter.getId());
		}
	}

}