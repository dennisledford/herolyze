/**
 * 
 */
package com.dledford.herolyze.domain;


import java.util.Map;

import com.dledford.herolyze.utils.CharacterClassType;
/**
 * @author dledford
 *
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicVineCharacter extends BaseEntity{
	private String name;
	private Map<String,String> first_appeared_in_issue;
	private String firstAppearance;
	private CharacterClassType characterClass;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String,String> getFirst_appeared_in_issue() {
		return first_appeared_in_issue;
	}
	public void setFirst_appeared_in_issue(Map<String,String> first_appeared_in_issue) {
		this.first_appeared_in_issue = first_appeared_in_issue;
	}
	public CharacterClassType getCharacterClass() {
		return characterClass;
	}
	public void setCharacterClass(CharacterClassType characterClass) {
		this.characterClass = characterClass;
	}
	public String getFirstAppearance() {
		return firstAppearance;
	}
	public void setFirstAppearance(String firstAppearance) {
		this.firstAppearance = firstAppearance;
	}
	
}
