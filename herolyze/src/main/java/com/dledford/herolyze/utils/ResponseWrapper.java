/**
 * 
 */
package com.dledford.herolyze.utils;



import java.util.List;

import com.dledford.herolyze.domain.BaseEntity;

/**
 * @author dledford
 *
 */
public class ResponseWrapper<E extends BaseEntity> {

	private MarvelContainer<E> data;
	
	private List<E> results;

	public MarvelContainer<E> getData() {
		return data;
	}

	public void setData(MarvelContainer<E> data) {
		this.data = data;
	}

	public List<E> getResults() {
		return results;
	}

	public void setResults(List<E> results) {
		this.results = results;
	}
	
}
