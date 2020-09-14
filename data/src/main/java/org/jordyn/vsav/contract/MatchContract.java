package org.jordyn.vsav.contract;

import java.math.BigInteger;

/**
 * Contract to be used with {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
public class MatchContract {

	private BigInteger matchId;
	private String playerOneName;
	private String playerTwoName;
	private String playerOneChar;
	private String playerTwoChar;
	private String youtubeId;

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

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(final String youtubeId) {
		this.youtubeId = youtubeId;
	}

}
