package org.jordyn.vsav.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

/**
 * Domain object representing a request to add a match to a video
 * @author jordyn
 *
 */
public class AddMatchRequest {

	@NotNull
	@ApiModelProperty(value = "The video ID of the video to add the match to; this is returned by any service that retrieves a video", required = true)
	private BigInteger videoId;

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

	public BigInteger getVideoId() {
		return videoId;
	}

	public void setVideoId(final BigInteger videoId) {
		this.videoId = videoId;
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
