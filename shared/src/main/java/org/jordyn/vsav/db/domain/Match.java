package org.jordyn.vsav.db.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity object for the vs.VIDEO_METADATA table
 * @author jordyn
 *
 */
@Entity(name = "match")
@Table(name = "MATCHES", schema = "vs")
// @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "video")
public class Match implements Serializable {

	private static final long serialVersionUID = 6648537142705310265L;

	@Id
	@GeneratedValue
	@Column(name = "I_MTCH_ID")
	private Integer matchId;

	@Column(name = "C_PLYR_ONE")
	private String playerOneName;

	@Column(name = "C_PLYR_TWO")
	private String playerTwoName;

	@Column(name = "C_CHAR_ONE")
	private String playerOneChar;

	@Column(name = "C_CHAR_TWO")
	private String playerTwoChar;

	@GeneratedValue
	@Column(name = "C_PLYR_ONE_IDX")
	private String playerOneNameIndex;

	@GeneratedValue
	@Column(name = "C_PLYR_TWO_IDX")
	private String playerTwoNameIndex;

	@GeneratedValue
	@Column(name = "C_CHAR_ONE_IDX")
	private String playerOneCharIndex;

	@GeneratedValue
	@Column(name = "C_CHAR_TWO_IDX")
	private String playerTwoCharIndex;

	@Column(name = "C_YT_TIMESTAMP")
	private String youtubeTimestamp;

	@Column(name = "Z_UPDT")
	private Date lastUpdate;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "I_VID")
	//	@JsonBackReference
	private Video video;

	public Integer getMatchId() {
		return matchId;
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

	public Video getVideo() {
		return video;
	}

	public void setVideo(final Video video) {
		this.video = video;
	}

	public String getYoutubeTimestamp() {
		return youtubeTimestamp;
	}

	public void setYoutubeTimestamp(final String youtubeTimestamp) {
		this.youtubeTimestamp = youtubeTimestamp;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
