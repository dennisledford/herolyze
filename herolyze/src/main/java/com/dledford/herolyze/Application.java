/**
 * 
 */
package com.dledford.herolyze;

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
        MarvelCharacter character = new MarvelCharacter();
        ComicVineCharacter comicVine = new ComicVineCharacter();
		try {
	        Config.getInstance().load();
			character = new MarvelService().getCharacterById(1011334L);
			if(character != null){
			System.out.println("Name: " + character.getName());  
			}else{
				System.out.println("UNKNOWN SUPERHERO");
			}
			comicVine = new ComicVineService().getCharacterById(45116L);
			if(comicVine != null){
				System.out.println("Name: " + comicVine.getName());  
			}else{
				System.out.println("UNKNOWN SUPERHERO");
			}
			for(ComicVineCharacter nonMarvelChar : new ComicVineService().getCharacters("superman")){
				System.out.println("Name: " + nonMarvelChar.getName() + " ID = " + nonMarvelChar.getId());  
			}
			
			for(MarvelCharacter marvelcharacter : new MarvelService().getCharacters("spider-man")){
				System.out.println("Name: " + marvelcharacter.getName() + " ID = " + marvelcharacter.getId());  
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
      
    }

}