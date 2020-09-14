package org.jordyn.vsav.db.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity object for the vs.VIDEO table
 * @author jordyn
 *
 */
@Entity(name = "video")
@Table(name = "VIDEOS", schema = "vs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "videoId")
public class Video {

	@Id
	@GeneratedValue
	@Column(name = "I_VID")
	private Integer videoId;

	@Column(name = "C_YT_ID")
	private String youtubeId;

	@OneToOne(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private VideoMetadata metadata;

	@OneToMany(mappedBy = "video", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//	@JsonManagedReference
	private Set<Match> matches;

	@Column(name = "Z_UPDT")
	private Date lastUpdate;

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(final Integer videoId) {
		this.videoId = videoId;
	}

	public String getYoutubeId() {
		return youtubeId;
	}

	public void setYoutubeId(final String youtubeId) {
		this.youtubeId = youtubeId;
	}

	public Set<Match> getMatches() {
		return matches;
	}

	public VideoMetadata getMetadata() {
		return metadata;
	}

	public void setMetdata(final VideoMetadata metadata) {
		this.metadata = metadata;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(final Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
