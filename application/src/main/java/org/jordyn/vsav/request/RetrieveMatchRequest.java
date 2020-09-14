package org.jordyn.vsav.request;

import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to retrieve a particular match
 * @author jordyn
 *
 */
@ApiModel
public class RetrieveMatchRequest {

	@ApiModelProperty(value = "The match ID of the match to retrieve; this is returned by any service that retrieves a video (except this one, obviously)", required = true)
	private BigInteger matchId;

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(final BigInteger matchId) {
		this.matchId = matchId;
	}

}
