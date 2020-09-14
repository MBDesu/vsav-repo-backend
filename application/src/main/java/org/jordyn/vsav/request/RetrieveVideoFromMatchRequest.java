package org.jordyn.vsav.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to retrieve video by match
 * @author jordyn
 *
 */
@ApiModel
public class RetrieveVideoFromMatchRequest {

	@NotNull
	@ApiModelProperty(value = "The video ID of the video to add the match to; this is returned by any service that retrieves a video", required = true)
	private BigInteger matchId;

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(final BigInteger matchId) {
		this.matchId = matchId;
	}

}
