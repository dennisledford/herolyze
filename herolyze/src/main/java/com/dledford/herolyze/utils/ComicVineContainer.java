package com.dledford.herolyze.utils;

import com.dledford.herolyze.domain.BaseEntity;

public class ComicVineContainer <E extends BaseEntity> {
	
	private int offset;

	private int limit;

	private int total;

	private int call;

	private E results;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCall() {
		return call;
	}

	public void setCall(int call) {
		this.call = call;
	}

	public E getResults() {
		return results;
	}

	public void setResults(E results) {
		this.results = results;
	}


}
