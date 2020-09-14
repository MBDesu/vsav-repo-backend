package org.jordyn.vsav.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to update a match
 * @author jordyn
 *
 */
public class UpdateMatchRequest {

	@NotNull
	@ApiModelProperty(value = "The match ID of the match to update", required = true)
	private BigInteger matchId;

	@NotNull
	@Size(min = 1, max = 50)
	@ApiModelProperty(value = "Player one's name", required = true)
	private String playerOneName;

	@NotNull
	@Size(min = 1, max = 50)
	@ApiModelProperty(value = "Player two's name", required = true)
	private String playerTwoName;

	@NotNull
	@Size(min = 1, max = 24)
	@ApiModelProperty(value = "Player one's character", allowableValues = "Anakaris,Aulbath,Bishamon,Demitri,Felicia,Gallon,Lei-Lei,Morrigan,Sasquatch,Victor,Zabel", required = true)
	private String playerOneChar;

	@NotNull
	@Size(min = 1, max = 24)
	@ApiModelProperty(value = "Player two's character", allowableValues = "Anakaris,Aulbath,Bishamon,Demitri,Felicia,Gallon,Lei-Lei,Morrigan,Sasquatch,Victor,Zabel", required = true)
	private String playerTwoChar;

	@NotNull
	@Size(min = 1, max = 9)
	@ApiModelProperty(value = "The YouTube formatted timestamp of when this match occurs in the given video", required = true)
	private String youtubeTimestamp;

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(final BigInteger matchId) {
		this.matchId = matchId;
	}

	public String getPlayerOneName() {
		return playerOneName;
	}

	public void setPlayerOneName(final String playerOneName) {
		this.playerOneName = playerOneName;
	}

	public String getPlayerTwoName() {
		return playerTwoName;
	}

	public void setPlayerTwoName(final String playerTwoName) {
		this.playerTwoName = playerTwoName;
	}

	public String getPlayerOneChar() {
		return playerOneChar;
	}

	public void setPlayerOneChar(final String playerOneChar) {
		this.playerOneChar = playerOneChar;
	}

	public String getPlayerTwoChar() {
		return playerTwoChar;
	}

	public void setPlayerTwoChar(final String playerTwoChar) {
		this.playerTwoChar = playerTwoChar;
	}

	public String getYoutubeTimestamp() {
		return youtubeTimestamp;
	}

	public void setYoutubeTimestamp(final String youtubeTimestamp) {
		this.youtubeTimestamp = youtubeTimestamp;
	}

}
