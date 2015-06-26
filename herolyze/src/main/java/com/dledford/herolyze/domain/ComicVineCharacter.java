/**
 * 
 */
package com.dledford.herolyze.domain;


import com.dledford.herolyze.utils.CharacterClassType;
/**
 * @author dledford
 *
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicVineCharacter extends BaseEntity{
	private String name;
	private String firstAppearance;
	private CharacterClassType characterClass;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstAppearance() {
		return firstAppearance;
	}
	public void setFirstAppearance(String firstAppearance) {
		this.firstAppearance = firstAppearance;
	}
	public CharacterClassType getCharacterClass() {
		return characterClass;
	}
	public void setCharacterClass(CharacterClassType characterClass) {
		this.characterClass = characterClass;
	}
	
}
