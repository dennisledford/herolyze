/**
 * 
 */
package com.dledford.herolyze.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dledford.herolyze.config.Config;
import com.dledford.herolyze.domain.BaseEntity;
import com.dledford.herolyze.domain.ComicVineCharacter;
import com.dledford.herolyze.utils.ComicVineContainer;
import com.dledford.herolyze.utils.ResponseWrapper;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author dledford
 *
 */
public class ComicVineService {

	private static final String API_KEY = "?api_key="+Config.getInstance().COMICVINE_API_KEY;
	private static final String ENDPOINT = "http://www.comicvine.com/api/";
	private static final String FORMAT = "&format=json";
	private static final Long CHARACTER_TYPE = 4005L;
	private static final String CHARACTER_API = "character/";
	private static final String CHARACTERS_API = "characters/";
	


	public ComicVineCharacter getCharacterById(Long id) throws Exception {
		String url = ENDPOINT + CHARACTER_API + CHARACTER_TYPE + "-" + id + "/"
				+ API_KEY + FORMAT;
		ComicVineCharacter character = call(
				url,
				new ParameterizedTypeReference<ComicVineContainer<ComicVineCharacter>>() {
				});
		if (character != null) {
			return character;
		}
		return new ComicVineCharacter();
	}
	
	public List<ComicVineCharacter> getCharacters() throws Exception {
		String url = ENDPOINT + CHARACTERS_API	+ API_KEY + FORMAT;
		List<ComicVineCharacter> characters = callCollection(
				url,
				new ParameterizedTypeReference<ResponseWrapper<ComicVineCharacter>>() {
				});
		
		return characters;
	}
	
	public List<ComicVineCharacter> getCharacters(String name) throws Exception{
		String url = ENDPOINT + CHARACTERS_API + API_KEY + "&filter=name:" + name + FORMAT;
		List<ComicVineCharacter> characters = callCollection(
				url,
				new ParameterizedTypeReference<ResponseWrapper<ComicVineCharacter>>() {
				});
		
		return characters;
	//,issue_number:3"
	}

	private <T extends BaseEntity> T call(
			String url,
			ParameterizedTypeReference<ComicVineContainer<T>> parameterizedTypeReference) {
		RestTemplate restTemplate = new RestTemplate();
		T result = null;
		ResponseEntity<ComicVineContainer<T>> responseEntity = restTemplate
				.exchange(url, HttpMethod.GET, null,
						parameterizedTypeReference, Collections.emptyMap());
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ComicVineContainer<T> response = responseEntity.getBody();
			if (response != null && response.getResults() != null) {
				result = response.getResults();
			}
		}
		return result;
	}
	
	
	private <T extends BaseEntity> List<T> callCollection(
			String url,
			ParameterizedTypeReference<ResponseWrapper<T>> parameterizedTypeReference) {
		RestTemplate restTemplate = new RestTemplate();
		List<T> result = new ArrayList<T>();
		ResponseEntity<ResponseWrapper<T>> responseEntity = restTemplate
				.exchange(url, HttpMethod.GET, null,
						parameterizedTypeReference, Collections.emptyMap());
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseWrapper<T> response = responseEntity.getBody();
			if (response != null && response.getResults() != null) {
				List<T> characters = response.getResults();
				if (characters != null) {
					result = characters;
				}
			}
		}
		return result;
	}

}
