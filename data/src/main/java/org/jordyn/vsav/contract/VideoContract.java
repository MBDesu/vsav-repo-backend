package org.jordyn.vsav.contract;

import java.math.BigInteger;
import java.util.Date;

/**
 * Contract to be used with {@link org.jordyn.vsav.service.api.VideoService}
 * @author jordyn
 *
 */
public class VideoContract {

	private BigInteger videoId;
	private BigInteger matchId;
	private int page;
	private int pageSize;
	private String youtubeId;
	private String playerOneName;
	private String playerTwoName;
	private String playerOneChar;
	private String playerTwoChar;
	private String youtubeTimestamp;
	private String title;
	private String channel;
	private String description;
	private Date uploadDate;

	public BigInteger getVideoId() {
		return videoId;
	}

	public void setVideoId(final BigInteger videoId) {
		this.videoId = videoId;
	}

	public BigInteger getMatchId() {
		return matchId;
	}

	public void setMatchId(final BigInteger matchId) {
		this.matchId = matchId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(final int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(final String youtubeId) {
		this.youtubeId = youtubeId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(final String channel) {
		this.channel = channel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(final Date uploadDate) {
		this.uploadDate = uploadDate;
	}

}
