/**
 * 
 */
package com.dledford.herolyze.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.dledford.herolyze.config.Config;
import com.dledford.herolyze.domain.BaseEntity;
import com.dledford.herolyze.domain.MarvelCharacter;
import com.dledford.herolyze.utils.MarvelContainer;
import com.dledford.herolyze.utils.ResponseWrapper;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * @author dledford
 *
 */
public class MarvelService {

	private static final String PUBLIC_KEY = Config.getInstance().MARVEL_PUBLIC_KEY;
	private static final String PRIVATE_KEY = Config.getInstance().MARVEL_PRIVATE_KEY;
	private static final String ENDPOINT = "http://gateway.marvel.com/";
	private static final String DELIMITER = "/";
	private static final String CHARACTERS_API = "v1/public/characters";
	private static final String QUERY_NAME ="?nameStartsWith=";
	
	private String generateAuth(String prefix) throws Exception {
		Long ts = System.currentTimeMillis();
		return prefix +"ts=" + ts + "&apikey=" + PUBLIC_KEY + "&hash=" + hash(ts);
	}

	private static String hash(Long ts) {
		String hash = DigestUtils.md5Hex(ts + PRIVATE_KEY + PUBLIC_KEY);
		return hash;
	}

	public MarvelCharacter getCharacterById(Long id) throws Exception {
		String url = ENDPOINT + CHARACTERS_API + DELIMITER +id + generateAuth("?");
		List<MarvelCharacter> characters = call(url,
				new ParameterizedTypeReference<ResponseWrapper<MarvelCharacter>>() {
				});
		if (characters.size() > 0) {
			return characters.get(0);
		}
		return null;
	}
	
	public List<MarvelCharacter> getCharacters(String name) throws Exception {
		String url = ENDPOINT + CHARACTERS_API + QUERY_NAME + name + generateAuth("&");
		return call(url,
				new ParameterizedTypeReference<ResponseWrapper<MarvelCharacter>>() {
				});
	}
	
	public List<MarvelCharacter> getCharacters() throws Exception {
		String url = ENDPOINT + CHARACTERS_API + generateAuth("?");
		return call(url,
				new ParameterizedTypeReference<ResponseWrapper<MarvelCharacter>>() {
				});
	}
	

	private <T extends BaseEntity> List<T> call(
			String url,
			ParameterizedTypeReference<ResponseWrapper<T>> parameterizedTypeReference) {
		RestTemplate restTemplate = new RestTemplate();
		List<T> result = new ArrayList<T>();
		ResponseEntity<ResponseWrapper<T>> responseEntity = restTemplate
				.exchange(url, HttpMethod.GET, null,
						parameterizedTypeReference, Collections.emptyMap());
		if (responseEntity.getStatusCode() == HttpStatus.OK) {
			ResponseWrapper<T> response = responseEntity.getBody();
			if (response != null && response.getData() != null) {
				MarvelContainer<T> marvelResponse = response.getData();
				if (marvelResponse.getResults() != null) {
					result = marvelResponse.getResults();
				}
			}
		}
		return result;
	}
	
}
