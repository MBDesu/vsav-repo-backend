package org.jordyn.vsav.request;

/**
 * Request POJO for retrieving matches (searchable)
 * @author jordyn
 *
 */
public class RetrieveMatchesRequest extends PaginatedApiRequest {

	private String playerOneName;
	private String playerTwoName;
	private String playerOneChar;
	private String playerTwoChar;

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

}
