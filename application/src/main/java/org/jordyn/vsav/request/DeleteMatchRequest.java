package org.jordyn.vsav.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to delete a match
 * by match ID
 * @author jordyn
 *
 */
public class DeleteMatchRequest {

	@NotNull
	@ApiModelProperty(value = "The ID of the match to delete")
	private BigInteger matchId;

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(final BigInteger matchId) {
		this.matchId = matchId;
	}

}
