package org.jordyn.vsav.db.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Players implements Serializable {

	private static final long serialVersionUID = 1943336217683680110L;

	@JsonProperty
	private List<Object> players;

	public List<Object> getPlayers() {
		return players;
	}

	public void setPlayers(final List<Object> players) {
		this.players = players;
	}
	
	public Players(final List<Object> players) {
		this.players = players;
	}

}
