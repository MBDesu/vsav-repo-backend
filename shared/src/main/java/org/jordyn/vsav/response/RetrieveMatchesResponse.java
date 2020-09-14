package org.jordyn.vsav.response;

import java.util.List;

import org.jordyn.vsav.db.domain.Match;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response POJO for {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
public class RetrieveMatchesResponse {

	@JsonProperty
	private long totalResults;

	@JsonProperty
	private List<Match> matches;

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(final long totalResults) {
		this.totalResults = totalResults;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(final List<Match> matches) {
		this.matches = matches;
	}



}
